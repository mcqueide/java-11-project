package br.com.arqdev.util.swagger.tag;

import br.com.arqdev.util.swagger.api.VendorExtension;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import java.util.List;
import org.springframework.core.Ordered;

public class Tag implements Ordered {
    private final String name;
    private final String description;
    private final int order;
    private final List<VendorExtension> vendorExtensions;

    public Tag(String name, String description) {
        this(name, description, 2147483647);
    }

    public Tag(String name, String description, int order) {
        this(name, description, order, Lists.newArrayList());
    }

    public Tag(String name, String description, List<VendorExtension> vendorExtensions) {
        this(name, description, 2147483647, vendorExtensions);
    }

    public Tag(String name, String description, int order, List<VendorExtension> vendorExtensions) {
        this.name = (String)Preconditions.checkNotNull(Strings.emptyToNull(name));
        this.description = description;
        this.order = order;
        this.vendorExtensions = Lists.newArrayList(vendorExtensions);
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getOrder() {
        return this.order;
    }

    public List<VendorExtension> getVendorExtensions() {
        return this.vendorExtensions;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Tag tag = (Tag)o;
            return Objects.equal(this.name, tag.name) && Objects.equal(this.description, tag.description);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.name, this.description});
    }
}

