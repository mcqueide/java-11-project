package br.com.arqdev.principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
            "br.com.arqdev.autenticacao",
			"br.com.arqdev.pessoa",
            "br.com.arqdev.util"
        })
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
}
