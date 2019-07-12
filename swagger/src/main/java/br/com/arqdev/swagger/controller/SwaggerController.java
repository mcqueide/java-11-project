package br.com.arqdev.swagger.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

@RestController
@ApiIgnore
public class SwaggerController {

    public static final String URL_API_DOC_DEFAULT = "/v2/api-docs";
    private static final String URL_PAGINA_SWAGGER = "/swagger-ui.html?url=";

    @GetMapping(value = "/rest/api-doc", produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView exibirDocumentacaoApi(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String uriApi = contextPath + "/v2/api-docs";
        return new ModelAndView(new RedirectView(contextPath + URL_PAGINA_SWAGGER + uriApi));
    }
}
