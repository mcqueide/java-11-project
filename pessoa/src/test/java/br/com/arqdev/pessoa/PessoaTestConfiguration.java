package br.com.arqdev.pessoa;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"br.com.arqdev.autenticacao", "br.com.arqdev.util", 
		"br.com.arqdev.pessoa.repository", "br.com.arqdev.pessoa.entity"})
public class PessoaTestConfiguration {

}
