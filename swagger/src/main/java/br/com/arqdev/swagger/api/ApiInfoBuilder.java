package br.com.arqdev.swagger.api;

import br.com.arqdev.swagger.model.Contact;
import com.google.common.collect.Lists;
import springfox.documentation.builders.BuilderDefaults;

import java.util.List;

public class ApiInfoBuilder {
    private String title;
    private String description;
    private String termsOfServiceUrl;
    private Contact contact;
    private String license;
    private String licenseUrl;
    private String version;
    private List<VendorExtension> vendorExtensions = Lists.newArrayList();

    public ApiInfoBuilder() {
    }

    public ApiInfoBuilder title(String title) {
        this.title = title;
        return this;
    }

    public ApiInfoBuilder description(String description) {
        this.description = description;
        return this;
    }

    public ApiInfoBuilder termsOfServiceUrl(String termsOfServiceUrl) {
        this.termsOfServiceUrl = termsOfServiceUrl;
        return this;
    }

    public ApiInfoBuilder version(String version) {
        this.version = version;
        return this;
    }

    /** @deprecated */
    @Deprecated
    public ApiInfoBuilder contact(String contact) {
        this.contact = new Contact(contact, "", "");
        return this;
    }

    public ApiInfoBuilder contact(Contact contact) {
        this.contact = contact;
        return this;
    }

    public ApiInfoBuilder license(String license) {
        this.license = license;
        return this;
    }

    public ApiInfoBuilder licenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
        return this;
    }

    public ApiInfoBuilder extensions(List<VendorExtension> extensions) {
        this.vendorExtensions.addAll(BuilderDefaults.nullToEmptyList(extensions));
        return this;
    }

    public ApiInfo build() {
        return new ApiInfo(this.title, this.description, this.version, this.termsOfServiceUrl, this.contact, this.license, this.licenseUrl, this.vendorExtensions);
    }
}

