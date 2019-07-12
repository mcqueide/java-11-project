package br.com.arqdev.swagger.security;

import com.google.common.collect.Lists;
import springfox.documentation.builders.BuilderDefaults;

import java.util.List;

public class SecurityReference {
    private final String reference;
    private final List<AuthorizationScope> scopes;

    public SecurityReference(String reference, AuthorizationScope[] scopes) {
        this.scopes = Lists.newArrayList(scopes);
        this.reference = reference;
    }

    public String getReference() {
        return this.reference;
    }

    public List<AuthorizationScope> getScopes() {
        return this.scopes;
    }

    public static SecurityReference.SecurityReferenceBuilder builder() {
        return new SecurityReference.SecurityReferenceBuilder();
    }

    public static class SecurityReferenceBuilder {
        private String reference;
        private AuthorizationScope[] scopes;

        SecurityReferenceBuilder() {
        }

        public SecurityReference.SecurityReferenceBuilder reference(String reference) {
            this.reference = (String)BuilderDefaults.defaultIfAbsent(reference, this.reference);
            return this;
        }

        public SecurityReference.SecurityReferenceBuilder scopes(AuthorizationScope[] scopes) {
            this.scopes = (AuthorizationScope[])BuilderDefaults.defaultIfAbsent(scopes, this.scopes);
            return this;
        }

        public SecurityReference build() {
            return new SecurityReference(this.reference, this.scopes);
        }
    }
}

