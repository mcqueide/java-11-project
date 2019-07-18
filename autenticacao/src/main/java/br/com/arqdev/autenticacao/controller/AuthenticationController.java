package br.com.arqdev.autenticacao.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.arqdev.autenticacao.dto.JwtAuthenticationDto;
import br.com.arqdev.autenticacao.dto.TokenDto;
import br.com.arqdev.autenticacao.utils.JwtTokenUtil;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	private static final String TOKEN_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer ";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * Gera e retorna um novo token JWT.
	 * 
	 * @param authenticationDto
	 * @param result
	 * @return ResponseEntity<TokenDto>
	 * @throws AuthenticationException
	 */
	@PostMapping
	public ResponseEntity<TokenDto> gerarTokenJwt(
			@Valid @RequestBody JwtAuthenticationDto authenticationDto, BindingResult result)
			throws AuthenticationException {

		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(null);
		}

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationDto.getEmail(), authenticationDto.getSenha()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDto.getEmail());
		String token = jwtTokenUtil.obterToken(userDetails);

		return ResponseEntity.ok(new TokenDto(token));
	}

	/**
	 * Gera um novo token com uma nova data de expiração.
	 * 
	 * @param request
	 * @return ResponseEntity<TokenDto>
	 */
	@PostMapping(value = "/refresh")
	public ResponseEntity<TokenDto> gerarRefreshTokenJwt(HttpServletRequest request) {
		Optional<String> token = Optional.ofNullable(request.getHeader(TOKEN_HEADER));
		
		if (token.isPresent() && token.get().startsWith(BEARER_PREFIX)) {
			token = Optional.of(token.get().substring(7));
        }
		
		if (!token.isPresent()) {
			return ResponseEntity.badRequest().body(null);
		} else if (!jwtTokenUtil.tokenValido(token.get())) {
			return ResponseEntity.badRequest().body(null);
		}
		
		String refreshedToken = jwtTokenUtil.refreshToken(token.get());
		return ResponseEntity.ok(new TokenDto(refreshedToken));
	}

}
