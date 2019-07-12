package br.com.arqdev.swagger.security;

import br.com.arqdev.swagger.operation.OperationContext;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.http.HttpMethod;
import springfox.documentation.service.SecurityReference;

import java.util.ArrayList;
import java.util.List;

public class SecurityContext {
    private final List<SecurityReference> securityReferences;
    private final Predicate<String> selector;
    private final Predicate<HttpMethod> methodSelector;

    public SecurityContext(List<SecurityReference> securityReferences, Predicate<String> selector) {
        this.securityReferences = securityReferences;
        this.selector = selector;
        this.methodSelector = Predicates.alwaysTrue();
    }

    public SecurityContext(List<SecurityReference> securityReferences, Predicate<String> selector, Predicate<HttpMethod> methodSelector) {
        this.securityReferences = securityReferences;
        this.selector = selector;
        this.methodSelector = methodSelector;
    }

    /** @deprecated */
    @Deprecated
    public List<SecurityReference> securityForPath(String path) {
        return (List)(this.selector.apply(path) ? this.securityReferences : new ArrayList());
    }

    public List<SecurityReference> securityForOperation(OperationContext operationContext) {
        return (List)(this.selector.apply(operationContext.requestMappingPattern()) && this.methodSelector.apply(operationContext.httpMethod()) ? this.securityReferences : new ArrayList());
    }

    public List<SecurityReference> getSecurityReferences() {
        return this.securityReferences;
    }

    public Predicate<HttpMethod> getMethodSelector() {
        return this.methodSelector;
    }

    public static SecurityContextBuilder builder() {
        return new SecurityContextBuilder();
    }
}

