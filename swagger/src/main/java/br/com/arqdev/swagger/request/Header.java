package br.com.arqdev.swagger.request;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import springfox.documentation.schema.ModelReference;

public class Header {
    private final String name;
    private final ModelReference modelReference;
    private final String description;

    public Header(String name, String description, ModelReference modelReference) {
        this.name = name;
        this.modelReference = modelReference;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public ModelReference getModelReference() {
        return this.modelReference;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Header header = (Header)o;
            return Objects.equal(this.name, header.name) && Objects.equal(this.modelReference, header.modelReference) && Objects.equal(this.description, header.description);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.name, this.modelReference, this.description});
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("name", this.name).add("modelReference", this.modelReference).add("description", this.description).toString();
    }
}

