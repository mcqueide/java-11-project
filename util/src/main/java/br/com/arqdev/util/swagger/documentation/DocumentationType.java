package br.com.arqdev.util.swagger.documentation;

import org.springframework.http.MediaType;
import org.springframework.plugin.metadata.SimplePluginMetadata;

public class DocumentationType extends SimplePluginMetadata {
    public static final DocumentationType SWAGGER_12 = new DocumentationType("swagger", "1.2");
    public static final DocumentationType SWAGGER_2 = new DocumentationType("swagger", "2.0");
    public static final DocumentationType SPRING_WEB = new DocumentationType("spring-web", "1.0");
    private final MediaType mediaType;

    public DocumentationType(String name, String version, MediaType mediaType) {
        super(name, version);
        this.mediaType = mediaType;
    }

    public DocumentationType(String name, String version) {
        this(name, version, MediaType.APPLICATION_JSON);
    }

    public MediaType getMediaType() {
        return this.mediaType;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof DocumentationType)) {
            return false;
        } else if (!super.equals(o)) {
            return false;
        } else {
            DocumentationType that = (DocumentationType)o;
            return super.equals(that) && this.mediaType.equals(that.mediaType);
        }
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + this.mediaType.hashCode();
        return result;
    }
}

