package br.com.arqdev.util.swagger.operation;

import br.com.arqdev.util.swagger.type.AlternateTypeProvider;
import br.com.arqdev.util.swagger.type.GenericTypeNamingStrategy;
import br.com.arqdev.util.swagger.request.ModelContext;
import br.com.arqdev.util.swagger.documentation.DocumentationType;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.lang.reflect.Type;
import java.util.Set;

public class OperationModelContextsBuilder {
    private final String group;
    private final DocumentationType documentationType;
    private final AlternateTypeProvider alternateTypeProvider;
    private final GenericTypeNamingStrategy genericsNamingStrategy;
    private final ImmutableSet<Class> ignorableTypes;
    private final Set<ModelContext> contexts = Sets.newHashSet();

    public OperationModelContextsBuilder(String group, DocumentationType documentationType, AlternateTypeProvider alternateTypeProvider, GenericTypeNamingStrategy genericsNamingStrategy, ImmutableSet<Class> ignorableParameterTypes) {
        this.group = group;
        this.documentationType = documentationType;
        this.alternateTypeProvider = alternateTypeProvider;
        this.genericsNamingStrategy = genericsNamingStrategy;
        this.ignorableTypes = ignorableParameterTypes;
    }

    public OperationModelContextsBuilder addReturn(Type type) {
        ModelContext returnValue = ModelContext.returnValue(this.group, type, this.documentationType, this.alternateTypeProvider, this.genericsNamingStrategy, this.ignorableTypes);
        this.contexts.add(returnValue);
        return this;
    }

    public OperationModelContextsBuilder addInputParam(Type type) {
        ModelContext inputParam = ModelContext.inputParam(this.group, type, this.documentationType, this.alternateTypeProvider, this.genericsNamingStrategy, this.ignorableTypes);
        this.contexts.add(inputParam);
        return this;
    }

    public Set<ModelContext> build() {
        return ImmutableSet.copyOf(this.contexts);
    }
}