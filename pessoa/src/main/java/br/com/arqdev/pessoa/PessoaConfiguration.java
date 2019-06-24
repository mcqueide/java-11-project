package br.com.arqdev.pessoa;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"br.com.arqdev.pessoa"})
@EnableAutoConfiguration
public class PessoaConfiguration {

}
