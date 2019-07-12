package br.com.arqdev.swagger.security;

import br.com.arqdev.swagger.api.VendorExtension;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import springfox.documentation.builders.BuilderDefaults;

import java.util.List;

public abstract class SecurityScheme {
    protected final String name;
    protected final String type;
    private final List<VendorExtension> vendorExtensions = Lists.newArrayList();

    protected SecurityScheme(String name, String type) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public List<VendorExtension> getVendorExtensions() {
        return ImmutableList.copyOf(this.vendorExtensions);
    }

    protected void addValidVendorExtensions(List<VendorExtension> vendorExtensions) {
        this.vendorExtensions.addAll(FluentIterable.from(BuilderDefaults.nullToEmptyList(vendorExtensions)).filter(new Predicate<VendorExtension>() {
            public boolean apply(VendorExtension input) {
                return input.getName().toLowerCase().startsWith("x-");
            }
        }).toList());
    }
}

