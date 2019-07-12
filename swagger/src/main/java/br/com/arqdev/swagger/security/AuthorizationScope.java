package br.com.arqdev.swagger.security;

public class AuthorizationScope {
    private final String scope;
    private final String description;

    public AuthorizationScope(String scope, String description) {
        this.description = description;
        this.scope = scope;
    }

    public String getScope() {
        return this.scope;
    }

    public String getDescription() {
        return this.description;
    }
}
