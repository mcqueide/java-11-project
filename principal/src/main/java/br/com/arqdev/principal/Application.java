package br.com.arqdev.principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.arqdev.util.swagger.api.ApiInfoBuilder;
import br.com.arqdev.util.swagger.api.Docket;
import br.com.arqdev.util.swagger.documentation.DocumentationType;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication(scanBasePackages = {
            "br.com.arqdev.autenticacao",
			"br.com.arqdev.pessoa",
            "br.com.arqdev.util",
            "br.com.arqdev.swagger"
        })
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public Docket swaggerEmployeeApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.arqdev"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder().version("1.0").title("Employee API").description("Documentation Employee API v1.0").build());
    }
}
