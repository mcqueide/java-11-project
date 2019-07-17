package br.com.arqdev.autenticacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.arqdev.autenticacao.dto.UsuarioDto;
import br.com.arqdev.autenticacao.security.JwtUserFactory;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioDto usuario = usuarioService.buscaPorEmail(username);

		if (usuario != null) {
			return JwtUserFactory.create(usuario);
		}

		throw new UsernameNotFoundException("Email não encontrado.");
	}

}
