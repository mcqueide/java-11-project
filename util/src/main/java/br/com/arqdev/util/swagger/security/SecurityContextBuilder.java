package br.com.arqdev.util.swagger.security;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import java.util.List;
import org.springframework.http.HttpMethod;
import springfox.documentation.service.SecurityReference;

public class SecurityContextBuilder {
    private List<SecurityReference> securityReferences = Lists.newArrayList();
    private Predicate<String> pathSelector = Predicates.alwaysTrue();
    private Predicate<HttpMethod> methodSelector;

    SecurityContextBuilder() {
    }

    public SecurityContextBuilder securityReferences(List<SecurityReference> securityReferences) {
        this.securityReferences = securityReferences;
        return this;
    }

    public SecurityContextBuilder forPaths(Predicate<String> selector) {
        this.pathSelector = selector;
        return this;
    }

    public SecurityContextBuilder forHttpMethods(Predicate<HttpMethod> methodSelector) {
        this.methodSelector = methodSelector;
        return this;
    }

    public SecurityContext build() {
        if (this.securityReferences == null) {
            this.securityReferences = Lists.newArrayList();
        }

        if (this.methodSelector == null) {
            this.methodSelector = Predicates.alwaysTrue();
        }

        return new SecurityContext(this.securityReferences, this.pathSelector, this.methodSelector);
    }
}
