package br.com.arqdev.autenticacao.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.arqdev.autenticacao.dto.PerfilDto;
import br.com.arqdev.autenticacao.dto.UsuarioDto;

public class JwtUserFactory {

	private JwtUserFactory() {
	}

	/**
	 * Converte e gera um JwtUser com base nos dados de um usuário.
	 * 
	 * @param usuario
	 * @return JwtUser
	 */
	public static JwtUser create(UsuarioDto usuario) {
		return new JwtUser(usuario.getId().longValue(), usuario.getEmail(), usuario.getSenha(),
				mapToGrantedAuthorities(usuario.getPerfis()));
	}

	/**
	 * Converte o perfil do usuário para o formato utilizado pelo Spring Security.
	 * 
	 * @param perfis
	 * @return List<GrantedAuthority>
	 */
	private static List<GrantedAuthority> mapToGrantedAuthorities(List<PerfilDto> perfis) {
		return perfis.stream()
				.map(p -> new SimpleGrantedAuthority(p.getNome()))
				.collect(Collectors.toList());
	}

}
