package br.com.arqdev.autenticacao;

import java.util.Arrays;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.arqdev.autenticacao.controller.JwtAuthenticationEntryPoint;
import br.com.arqdev.autenticacao.security.filters.JwtAuthenticationTokenFilter;

@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AutenticacaoConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	   return super.authenticationManagerBean();
	}
	
	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
		return new JwtAuthenticationTokenFilter();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		String[] urlsSwagger = {"/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**"};
		String[] urlLogin = {"/auth/**"};
		
		httpSecurity.csrf().disable().exceptionHandling()
				.authenticationEntryPoint(unauthorizedHandler).and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().authorizeRequests()
				.antMatchers(Stream.of(urlsSwagger, urlLogin)
						.flatMap(Arrays::stream)
						.toArray(String[]::new))
				.permitAll().anyRequest().authenticated();
		
		httpSecurity.addFilterBefore(authenticationTokenFilterBean(), 
				UsernamePasswordAuthenticationFilter.class);
		httpSecurity.headers().cacheControl();
	}

}
