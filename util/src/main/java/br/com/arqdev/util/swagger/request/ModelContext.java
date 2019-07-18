package br.com.arqdev.util.swagger.request;

import br.com.arqdev.util.swagger.documentation.DocumentationType;
import br.com.arqdev.util.swagger.type.AlternateTypeProvider;
import br.com.arqdev.util.swagger.type.GenericTypeNamingStrategy;
import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.lang.reflect.Type;
import java.util.Set;
import springfox.documentation.builders.ModelBuilder;

public class ModelContext {
    private final Type type;
    private final boolean returnType;
    private final String groupName;
    private final DocumentationType documentationType;
    private final ModelContext parentContext;
    private final Set<ResolvedType> seenTypes = Sets.newHashSet();
    private final ModelBuilder modelBuilder;
    private final AlternateTypeProvider alternateTypeProvider;
    private final GenericTypeNamingStrategy genericNamingStrategy;
    private final ImmutableSet<Class> ignorableTypes;

    private ModelContext(String groupName, Type type, boolean returnType, DocumentationType documentationType, AlternateTypeProvider alternateTypeProvider, GenericTypeNamingStrategy genericNamingStrategy, ImmutableSet<Class> ignorableTypes) {
        this.groupName = groupName;
        this.documentationType = documentationType;
        this.alternateTypeProvider = alternateTypeProvider;
        this.genericNamingStrategy = genericNamingStrategy;
        this.ignorableTypes = ignorableTypes;
        this.parentContext = null;
        this.type = type;
        this.returnType = returnType;
        this.modelBuilder = new ModelBuilder();
    }

    private ModelContext(ModelContext parentContext, ResolvedType input) {
        this.parentContext = parentContext;
        this.type = input;
        this.groupName = parentContext.groupName;
        this.returnType = parentContext.isReturnType();
        this.documentationType = parentContext.getDocumentationType();
        this.modelBuilder = new ModelBuilder();
        this.alternateTypeProvider = parentContext.alternateTypeProvider;
        this.ignorableTypes = parentContext.ignorableTypes;
        this.genericNamingStrategy = parentContext.getGenericNamingStrategy();
    }

    public Type getType() {
        return this.type;
    }

    public ResolvedType resolvedType(TypeResolver resolver) {
        return resolver.resolve(this.getType(), new Type[0]);
    }

    public boolean isReturnType() {
        return this.returnType;
    }

    public AlternateTypeProvider getAlternateTypeProvider() {
        return this.alternateTypeProvider;
    }

    public ResolvedType alternateFor(ResolvedType resolved) {
        return this.alternateTypeProvider.alternateFor(resolved);
    }

    public String getGroupName() {
        return this.groupName;
    }

    public static ModelContext inputParam(String group, Type type, DocumentationType documentationType, AlternateTypeProvider alternateTypeProvider, GenericTypeNamingStrategy genericNamingStrategy, ImmutableSet<Class> ignorableTypes) {
        return new ModelContext(group, type, false, documentationType, alternateTypeProvider, genericNamingStrategy, ignorableTypes);
    }

    public static ModelContext returnValue(String groupName, Type type, DocumentationType documentationType, AlternateTypeProvider alternateTypeProvider, GenericTypeNamingStrategy genericNamingStrategy, ImmutableSet<Class> ignorableTypes) {
        return new ModelContext(groupName, type, true, documentationType, alternateTypeProvider, genericNamingStrategy, ignorableTypes);
    }

    public static ModelContext fromParent(ModelContext context, ResolvedType input) {
        return new ModelContext(context, input);
    }

    public boolean hasSeenBefore(ResolvedType resolvedType) {
        return this.seenTypes.contains(resolvedType) || this.seenTypes.contains((new TypeResolver()).resolve(resolvedType.getErasedType(), new Type[0])) || this.parentHasSeenBefore(resolvedType);
    }

    public DocumentationType getDocumentationType() {
        return this.documentationType;
    }

    private boolean parentHasSeenBefore(ResolvedType resolvedType) {
        return this.parentContext == null ? false : this.parentContext.hasSeenBefore(resolvedType);
    }

    public GenericTypeNamingStrategy getGenericNamingStrategy() {
        return this.parentContext == null ? this.genericNamingStrategy : this.parentContext.getGenericNamingStrategy();
    }

    public ModelBuilder getBuilder() {
        return this.modelBuilder;
    }

    public void seen(ResolvedType resolvedType) {
        this.seenTypes.add(resolvedType);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            ModelContext that = (ModelContext)o;
            return Objects.equal(this.groupName, that.groupName) && Objects.equal(this.type, that.type) && Objects.equal(this.documentationType, that.documentationType) && Objects.equal(this.returnType, that.returnType) && Objects.equal(this.namingStrategy(), that.namingStrategy());
        } else {
            return false;
        }
    }

    private String namingStrategy() {
        return this.genericNamingStrategy != null ? this.genericNamingStrategy.getClass().getName() : "";
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.groupName, this.type, this.documentationType, this.returnType, this.namingStrategy()});
    }

    public String description() {
        return MoreObjects.toStringHelper(ModelContext.class).add("groupName", this.getGroupName()).add("type", this.getType()).add("isReturnType", this.isReturnType()).toString();
    }

    public boolean canIgnore(ResolvedType type) {
        return this.ignorableTypes.contains(type.getErasedType());
    }
}

