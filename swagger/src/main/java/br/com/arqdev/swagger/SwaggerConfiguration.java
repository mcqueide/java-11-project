package br.com.arqdev.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.arqdev.swagger.api.ApiInfoBuilder;
import br.com.arqdev.swagger.api.Docket;
import br.com.arqdev.swagger.documentation.DocumentationType;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerConfiguration {

    @Value("${swagger.titulo:ADICIONE PROPRIEDADE swagger.titulo NO APPLICATION.PROPERTIES/YAML}")
    private String titulo;
    @Value("${swagger.descricao:ADICIONE PROPRIEDADE swagger.descricao NO APPLICATION.PROPERTIES/YAML}")
    private String descricao;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.arqdev"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .version("1.0")
                        .title(titulo)
                        .description(descricao)
                        .build());
    }

}
