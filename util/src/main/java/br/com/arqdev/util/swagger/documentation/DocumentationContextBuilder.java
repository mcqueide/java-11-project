package br.com.arqdev.util.swagger.documentation;

import br.com.arqdev.util.swagger.api.*;
import br.com.arqdev.util.swagger.operation.Operation;
import br.com.arqdev.util.swagger.parameter.Parameter;
import br.com.arqdev.util.swagger.request.RequestHandler;
import br.com.arqdev.util.swagger.resource.ResourceGroupingStrategy;
import br.com.arqdev.util.swagger.response.ResponseMessage;
import br.com.arqdev.util.swagger.security.SecurityContext;
import br.com.arqdev.util.swagger.security.SecurityScheme;
import br.com.arqdev.util.swagger.tag.Tag;
import br.com.arqdev.util.swagger.tag.Tags;
import br.com.arqdev.util.swagger.type.GenericTypeNamingStrategy;
import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.core.OrderComparator;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.PathProvider;
import springfox.documentation.builders.BuilderDefaults;
import springfox.documentation.schema.AlternateTypeRule;

public class DocumentationContextBuilder {
    private final List<SecurityContext> securityContexts = Lists.newArrayList();
    private final Set<Class> ignorableParameterTypes = Sets.newHashSet();
    private final Map<RequestMethod, List<ResponseMessage>> responseMessageOverrides = Maps.newTreeMap();
    private final List<Parameter> globalOperationParameters = Lists.newArrayList();
    private final List<AlternateTypeRule> rules = Lists.newArrayList();
    private final Map<RequestMethod, List<ResponseMessage>> defaultResponseMessages = Maps.newHashMap();
    private final Set<String> protocols = Sets.newHashSet();
    private final Set<String> produces = Sets.newHashSet();
    private final Set<String> consumes = Sets.newHashSet();
    private final Set<ResolvedType> additionalModels = Sets.newHashSet();
    private final Set<Tag> tags = Sets.newTreeSet(Tags.tagComparator());
    private List<VendorExtension> vendorExtensions = new ArrayList();
    private TypeResolver typeResolver;
    private List<RequestHandler> handlerMappings;
    private ApiInfo apiInfo;
    private String groupName;
    private ResourceGroupingStrategy resourceGroupingStrategy;
    private PathProvider pathProvider;
    private List<? extends SecurityScheme> securitySchemes;
    private Ordering<ApiListingReference> listingReferenceOrdering;
    private Ordering<ApiDescription> apiDescriptionOrdering;
    private DocumentationType documentationType;
    private Ordering<Operation> operationOrdering;
    private boolean applyDefaultResponseMessages;
    private ApiSelector apiSelector;
    private String host;
    private GenericTypeNamingStrategy genericsNamingStrategy;
    private Optional<String> pathMapping;
    private boolean isUrlTemplatesEnabled;

    public DocumentationContextBuilder(DocumentationType documentationType) {
        this.apiSelector = ApiSelector.DEFAULT;
        this.documentationType = documentationType;
    }

    public DocumentationContextBuilder requestHandlers(List<RequestHandler> handlerMappings) {
        this.handlerMappings = handlerMappings;
        return this;
    }

    public DocumentationContextBuilder apiInfo(ApiInfo apiInfo) {
        this.apiInfo = (ApiInfo)BuilderDefaults.defaultIfAbsent(apiInfo, this.apiInfo);
        return this;
    }

    public DocumentationContextBuilder groupName(String groupName) {
        this.groupName = (String)BuilderDefaults.defaultIfAbsent(groupName, this.groupName);
        return this;
    }

    public DocumentationContextBuilder additionalIgnorableTypes(Set<Class> ignorableParameterTypes) {
        this.ignorableParameterTypes.addAll(ignorableParameterTypes);
        return this;
    }

    public DocumentationContextBuilder additionalResponseMessages(Map<RequestMethod, List<ResponseMessage>> additionalResponseMessages) {
        this.responseMessageOverrides.putAll(additionalResponseMessages);
        return this;
    }

    public DocumentationContextBuilder additionalOperationParameters(List<Parameter> globalRequestParameters) {
        this.globalOperationParameters.addAll(BuilderDefaults.nullToEmptyList(globalRequestParameters));
        return this;
    }

    /** @deprecated */
    @Deprecated
    public DocumentationContextBuilder withResourceGroupingStrategy(ResourceGroupingStrategy resourceGroupingStrategy) {
        this.resourceGroupingStrategy = resourceGroupingStrategy;
        return this;
    }

    public DocumentationContextBuilder pathProvider(PathProvider pathProvider) {
        this.pathProvider = (PathProvider)BuilderDefaults.defaultIfAbsent(pathProvider, this.pathProvider);
        return this;
    }

    public DocumentationContextBuilder securityContexts(List<SecurityContext> securityContext) {
        this.securityContexts.addAll(BuilderDefaults.nullToEmptyList(securityContext));
        return this;
    }

    public DocumentationContextBuilder securitySchemes(List<? extends SecurityScheme> securitySchemes) {
        this.securitySchemes = securitySchemes;
        return this;
    }

    public DocumentationContextBuilder apiListingReferenceOrdering(Ordering<ApiListingReference> listingReferenceOrdering) {
        this.listingReferenceOrdering = (Ordering)BuilderDefaults.defaultIfAbsent(listingReferenceOrdering, this.listingReferenceOrdering);
        return this;
    }

    public DocumentationContextBuilder apiDescriptionOrdering(Ordering<ApiDescription> apiDescriptionOrdering) {
        this.apiDescriptionOrdering = (Ordering)BuilderDefaults.defaultIfAbsent(apiDescriptionOrdering, this.apiDescriptionOrdering);
        return this;
    }

    private Map<RequestMethod, List<ResponseMessage>> aggregateResponseMessages() {
        Map<RequestMethod, List<ResponseMessage>> responseMessages = Maps.newHashMap();
        if (this.applyDefaultResponseMessages) {
            responseMessages.putAll(this.defaultResponseMessages);
        }

        responseMessages.putAll(this.responseMessageOverrides);
        return responseMessages;
    }

    public DocumentationContextBuilder applyDefaultResponseMessages(boolean applyDefaultResponseMessages) {
        this.applyDefaultResponseMessages = applyDefaultResponseMessages;
        return this;
    }

    public DocumentationContextBuilder ruleBuilders(List<Function<TypeResolver, AlternateTypeRule>> ruleBuilders) {
        this.rules.addAll(FluentIterable.from(ruleBuilders).transform(this.evaluator(this.typeResolver)).toList());
        return this;
    }

    public DocumentationContextBuilder typeResolver(TypeResolver typeResolver) {
        this.typeResolver = typeResolver;
        return this;
    }

    public DocumentationContextBuilder operationOrdering(Ordering<Operation> operationOrdering) {
        this.operationOrdering = (Ordering)BuilderDefaults.defaultIfAbsent(operationOrdering, this.operationOrdering);
        return this;
    }

    public DocumentationContextBuilder rules(List<AlternateTypeRule> rules) {
        this.rules.addAll(rules);
        return this;
    }

    public DocumentationContextBuilder defaultResponseMessages(Map<RequestMethod, List<ResponseMessage>> defaultResponseMessages) {
        this.defaultResponseMessages.putAll(defaultResponseMessages);
        return this;
    }

    public DocumentationContextBuilder produces(Set<String> produces) {
        this.produces.addAll(produces);
        return this;
    }

    public DocumentationContextBuilder consumes(Set<String> consumes) {
        this.consumes.addAll(consumes);
        return this;
    }

    public DocumentationContextBuilder genericsNaming(GenericTypeNamingStrategy genericsNamingStrategy) {
        this.genericsNamingStrategy = genericsNamingStrategy;
        return this;
    }

    public DocumentationContextBuilder host(String host) {
        this.host = (String)BuilderDefaults.defaultIfAbsent(host, this.host);
        return this;
    }

    public DocumentationContextBuilder protocols(Set<String> protocols) {
        this.protocols.addAll(protocols);
        return this;
    }

    public DocumentationContextBuilder selector(ApiSelector apiSelector) {
        this.apiSelector = apiSelector;
        return this;
    }

    public DocumentationContextBuilder pathMapping(Optional<String> pathMapping) {
        this.pathMapping = pathMapping;
        return this;
    }

    public DocumentationContextBuilder enableUrlTemplating(boolean isUrlTemplatesEnabled) {
        this.isUrlTemplatesEnabled = isUrlTemplatesEnabled;
        return this;
    }

    public DocumentationContextBuilder additionalModels(Set<ResolvedType> additionalModels) {
        this.additionalModels.addAll(additionalModels);
        return this;
    }

    public DocumentationContextBuilder tags(Set<Tag> tags) {
        this.tags.addAll(tags);
        return this;
    }

    public DocumentationContextBuilder vendorExtentions(List<VendorExtension> vendorExtensions) {
        this.vendorExtensions.addAll(vendorExtensions);
        return this;
    }

    public DocumentationContext build() {
        Map<RequestMethod, List<ResponseMessage>> responseMessages = this.aggregateResponseMessages();
        OrderComparator.sort(this.rules);
        return new DocumentationContext(
                this.documentationType,
                this.handlerMappings,
                this.apiInfo,
                this.groupName,
                this.apiSelector,
                this.ignorableParameterTypes,
                responseMessages,
                this.globalOperationParameters,
                this.resourceGroupingStrategy,
                this.pathProvider,
                this.securityContexts,
                this.securitySchemes,
                this.rules,
                this.listingReferenceOrdering,
                this.apiDescriptionOrdering,
                this.operationOrdering,
                this.produces,
                this.consumes,
                this.host,
                this.protocols,
                this.genericsNamingStrategy,
                this.pathMapping,
                this.isUrlTemplatesEnabled,
                this.additionalModels,
                this.tags,
                this.vendorExtensions);
    }

    private Function<Function<TypeResolver, AlternateTypeRule>, AlternateTypeRule> evaluator(final TypeResolver typeResolver) {
        return new Function<Function<TypeResolver, AlternateTypeRule>, AlternateTypeRule>() {
            public AlternateTypeRule apply(Function<TypeResolver, AlternateTypeRule> input) {
                return (AlternateTypeRule)input.apply(typeResolver);
            }
        };
    }
}

