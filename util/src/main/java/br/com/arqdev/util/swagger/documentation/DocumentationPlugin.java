package br.com.arqdev.util.swagger.documentation;

import org.springframework.plugin.core.Plugin;

public interface DocumentationPlugin extends Plugin<DocumentationType> {
    boolean isEnabled();

    DocumentationType getDocumentationType();

    DocumentationContext configure(DocumentationContextBuilder var1);

    String getGroupName();
}
