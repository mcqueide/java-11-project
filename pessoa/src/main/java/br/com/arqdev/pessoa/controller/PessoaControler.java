package br.com.arqdev.pessoa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.arqdev.pessoa.dto.PessoaDto;
import br.com.arqdev.pessoa.service.PessoaServico;


@RestController
@RequestMapping("pessoa")
public class PessoaControler {

    @Autowired
    private PessoaServico servico;

    @GetMapping
    public List<PessoaDto> obtemPessoas() {
        return servico.obtemPessoas();
    }

    @GetMapping("{id}")
    public Optional<PessoaDto> obtemPessoa(@PathVariable(value = "id") Integer id) {
        return servico.obtemPessoa(id);
    }
}