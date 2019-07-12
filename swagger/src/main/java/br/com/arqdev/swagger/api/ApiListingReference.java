package br.com.arqdev.swagger.api;

public class ApiListingReference {
    private final String path;
    private final String description;
    private final int position;

    public ApiListingReference(String path, String description, int position) {
        this.path = path;
        this.description = description;
        this.position = position;
    }

    public String getPath() {
        return this.path;
    }

    public String getDescription() {
        return this.description;
    }

    public int getPosition() {
        return this.position;
    }
}

