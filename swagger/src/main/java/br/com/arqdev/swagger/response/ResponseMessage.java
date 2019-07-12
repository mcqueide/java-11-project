package br.com.arqdev.swagger.response;

import br.com.arqdev.swagger.api.VendorExtension;
import springfox.documentation.schema.ModelReference;
import springfox.documentation.service.Header;

import java.util.List;
import java.util.Map;

public class ResponseMessage {
    private final int code;
    private final String message;
    private final ModelReference responseModel;
    private final Map<String, Header> headers;
    private final List<VendorExtension> vendorExtensions;

    public ResponseMessage(int code, String message, ModelReference responseModel, Map<String, Header> headers, List<VendorExtension> vendorExtensions) {
        this.code = code;
        this.message = message;
        this.responseModel = responseModel;
        this.headers = headers;
        this.vendorExtensions = vendorExtensions;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public ModelReference getResponseModel() {
        return this.responseModel;
    }

    public Map<String, Header> getHeaders() {
        return this.headers;
    }

    public List<VendorExtension> getVendorExtensions() {
        return this.vendorExtensions;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            ResponseMessage that = (ResponseMessage)o;
            return this.code == that.code;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return this.code;
    }
}

