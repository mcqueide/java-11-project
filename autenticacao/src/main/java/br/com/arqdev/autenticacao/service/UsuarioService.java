package br.com.arqdev.autenticacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arqdev.autenticacao.dto.UsuarioDto;
import br.com.arqdev.autenticacao.repository.UsuarioRepository;
import br.com.arqdev.util.Conversor;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private Conversor conversor;
	
	public UsuarioDto buscaPorEmail(String email) {
		return conversor.converter(repository.findByEmail(email), UsuarioDto.class);
	}
	
	
}
