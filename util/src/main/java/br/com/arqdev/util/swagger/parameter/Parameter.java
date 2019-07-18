package br.com.arqdev.util.swagger.parameter;

import br.com.arqdev.util.swagger.api.VendorExtension;
import com.fasterxml.classmate.ResolvedType;
import com.google.common.base.Optional;
import com.google.common.collect.Multimap;
import java.util.List;
import org.springframework.core.Ordered;
import springfox.documentation.schema.Example;
import springfox.documentation.schema.ModelReference;

public class Parameter implements Ordered {
    public static final int DEFAULT_PRECEDENCE = 0;
    private final String name;
    private final String description;
    private final String defaultValue;
    private final Boolean required;
    private final Boolean allowMultiple;
    private final ModelReference modelRef;
    private final Optional<ResolvedType> type;
    private final AllowableValues allowableValues;
    private final String paramType;
    private final String paramAccess;
    private final Boolean hidden;
    private final String pattern;
    private final String collectionFormat;
    private final int order;
    private final Object scalarExample;
    private final Multimap<String, Example> examples;
    private final List<VendorExtension> vendorExtensions;
    private final Boolean allowEmptyValue;

    public Parameter(String name, String description, String defaultValue, boolean required, boolean allowMultiple, Boolean allowEmptyValue, ModelReference modelRef, Optional<ResolvedType> type, AllowableValues allowableValues, String paramType, String paramAccess, boolean hidden, String pattern, String collectionFormat, int order, Object scalarExample, Multimap<String, Example> examples, List<VendorExtension> vendorExtensions) {
        this.description = description;
        this.defaultValue = defaultValue;
        this.required = required;
        this.allowMultiple = allowMultiple;
        this.allowEmptyValue = allowEmptyValue;
        this.modelRef = modelRef;
        this.type = type;
        this.allowableValues = allowableValues;
        this.paramType = paramType;
        this.paramAccess = paramAccess;
        this.name = name;
        this.hidden = hidden;
        this.pattern = pattern;
        this.collectionFormat = collectionFormat;
        this.order = order;
        this.scalarExample = scalarExample;
        this.examples = examples;
        this.vendorExtensions = vendorExtensions;
    }

    public Optional<ResolvedType> getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public Boolean isRequired() {
        return this.required;
    }

    public Boolean isAllowMultiple() {
        return this.allowMultiple;
    }

    public AllowableValues getAllowableValues() {
        return this.allowableValues;
    }

    public String getParamType() {
        return this.paramType;
    }

    public String getParamAccess() {
        return this.paramAccess;
    }

    public ModelReference getModelRef() {
        return this.modelRef;
    }

    public Boolean isHidden() {
        return this.hidden;
    }

    public String getPattern() {
        return this.pattern;
    }

    public List<VendorExtension> getVendorExtentions() {
        return this.vendorExtensions;
    }

    public String getCollectionFormat() {
        return this.collectionFormat;
    }

    public Boolean isAllowEmptyValue() {
        return this.allowEmptyValue;
    }

    public Object getScalarExample() {
        return this.scalarExample;
    }

    public Multimap<String, Example> getExamples() {
        return this.examples;
    }

    public int getOrder() {
        return this.order;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("Parameter{");
        sb.append("name='").append(this.name).append('\'');
        sb.append(", description='").append(this.description).append('\'');
        sb.append(", order='").append(this.order).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
