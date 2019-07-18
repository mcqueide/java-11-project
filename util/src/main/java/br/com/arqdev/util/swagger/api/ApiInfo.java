package br.com.arqdev.util.swagger.api;

import br.com.arqdev.util.swagger.model.Contact;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ApiInfo {
    public static final Contact DEFAULT_CONTACT = new Contact("", "", "");
    public static final ApiInfo DEFAULT;
    private final String version;
    private final String title;
    private final String description;
    private final String termsOfServiceUrl;
    private final String license;
    private final String licenseUrl;
    private final Contact contact;
    private final List<VendorExtension> vendorExtensions;

    /** @deprecated */
    @Deprecated
    public ApiInfo(String title, String description, String version, String termsOfServiceUrl, String contactName, String license, String licenseUrl) {
        this(title, description, version, termsOfServiceUrl, new Contact(contactName, "", ""), license, licenseUrl, new ArrayList());
    }

    public ApiInfo(String title, String description, String version, String termsOfServiceUrl, Contact contact, String license, String licenseUrl, Collection<VendorExtension> vendorExtensions) {
        this.title = title;
        this.description = description;
        this.version = version;
        this.termsOfServiceUrl = termsOfServiceUrl;
        this.contact = contact;
        this.license = license;
        this.licenseUrl = licenseUrl;
        this.vendorExtensions = Lists.newArrayList(vendorExtensions);
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTermsOfServiceUrl() {
        return this.termsOfServiceUrl;
    }

    public Contact getContact() {
        return this.contact;
    }

    public String getLicense() {
        return this.license;
    }

    public String getLicenseUrl() {
        return this.licenseUrl;
    }

    public String getVersion() {
        return this.version;
    }

    public List<VendorExtension> getVendorExtensions() {
        return this.vendorExtensions;
    }

    static {
        DEFAULT = new ApiInfo("Api Documentation", "Api Documentation", "1.0", "urn:tos", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
    }
}

