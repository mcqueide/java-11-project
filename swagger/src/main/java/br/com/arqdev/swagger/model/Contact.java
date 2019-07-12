package br.com.arqdev.swagger.model;

public class Contact {
    private final String name;
    private final String url;
    private final String email;

    public Contact(String name, String url, String email) {
        this.name = name;
        this.url = url;
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }

    public String getEmail() {
        return this.email;
    }
}