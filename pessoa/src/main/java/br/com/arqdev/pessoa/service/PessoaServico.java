package br.com.arqdev.pessoa.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import br.com.arqdev.pessoa.dto.PessoaDto;
import br.com.arqdev.pessoa.entity.Pessoa;
import br.com.arqdev.pessoa.repository.PessoaRepository;
import br.com.arqdev.util.Conversor;

@Service
public class PessoaServico {

	@Autowired
	private PessoaRepository repository;

	@Autowired
	private Conversor conversor;

	public Optional<PessoaDto> obtemPessoa(Integer id) {
		return repository.findById(id).map(p -> conversor.converter(p, PessoaDto.class));
	}

	public List<PessoaDto> obtemPessoas() {
		return repository.findAll().stream().map(p -> conversor.converter(p, PessoaDto.class))
				.collect(Collectors.toList());
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
    public void cadastrarPessoa(PessoaDto dto) {
    	repository.save(conversor.converter(dto, Pessoa.class));
    }
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	public void removerPessoa(Integer id) {
		repository.deleteById(id);
	}

}