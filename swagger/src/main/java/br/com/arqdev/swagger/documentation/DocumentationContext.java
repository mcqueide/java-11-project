package br.com.arqdev.swagger.documentation;

import br.com.arqdev.swagger.api.ApiDescription;
import br.com.arqdev.swagger.api.ApiInfo;
import br.com.arqdev.swagger.api.ApiListingReference;
import br.com.arqdev.swagger.api.ApiSelector;
import br.com.arqdev.swagger.api.VendorExtension;
import br.com.arqdev.swagger.operation.Operation;
import br.com.arqdev.swagger.parameter.Parameter;
import br.com.arqdev.swagger.request.RequestHandler;
import br.com.arqdev.swagger.resource.ResourceGroupingStrategy;
import br.com.arqdev.swagger.response.ResponseMessage;
import br.com.arqdev.swagger.security.SecurityContext;
import br.com.arqdev.swagger.security.SecurityScheme;
import br.com.arqdev.swagger.tag.Tag;
import br.com.arqdev.swagger.type.AlternateTypeProvider;
import br.com.arqdev.swagger.type.GenericTypeNamingStrategy;
import com.fasterxml.classmate.ResolvedType;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Ordering;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.PathProvider;
import springfox.documentation.annotations.Incubating;
import springfox.documentation.schema.AlternateTypeRule;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class DocumentationContext {
    private final DocumentationType documentationType;
    private final List<RequestHandler> handlerMappings;
    private final ApiInfo apiInfo;
    private final String groupName;
    private final ApiSelector apiSelector;
    private final AlternateTypeProvider alternateTypeProvider;
    private final Set<Class> ignorableParameterTypes;
    private final Map<RequestMethod, List<ResponseMessage>> globalResponseMessages;
    private final List<Parameter> globalOperationParameters;
    private final ResourceGroupingStrategy resourceGroupingStrategy;
    private final PathProvider pathProvider;
    private final List<SecurityContext> securityContexts;
    private final List<? extends SecurityScheme> securitySchemes;
    private final Ordering<ApiListingReference> listingReferenceOrdering;
    private final Ordering<ApiDescription> apiDescriptionOrdering;
    private final Ordering<Operation> operationOrdering;
    private final GenericTypeNamingStrategy genericsNamingStrategy;
    private final Optional<String> pathMapping;
    private final Set<ResolvedType> additionalModels;
    private final Set<Tag> tags;
    private Set<String> produces;
    private Set<String> consumes;
    private String host;
    private Set<String> protocols;
    private boolean isUriTemplatesEnabled;
    private List<VendorExtension> vendorExtensions;

    public DocumentationContext(DocumentationType documentationType, List<RequestHandler> handlerMappings, ApiInfo apiInfo, String groupName, ApiSelector apiSelector, Set<Class> ignorableParameterTypes, Map<RequestMethod, List<ResponseMessage>> globalResponseMessages, List<Parameter> globalOperationParameter, ResourceGroupingStrategy resourceGroupingStrategy, PathProvider pathProvider, List<SecurityContext> securityContexts, List<? extends SecurityScheme> securitySchemes, List<AlternateTypeRule> alternateTypeRules, Ordering<ApiListingReference> listingReferenceOrdering, Ordering<ApiDescription> apiDescriptionOrdering, Ordering<Operation> operationOrdering, Set<String> produces, Set<String> consumes, String host, Set<String> protocols, GenericTypeNamingStrategy genericsNamingStrategy, Optional<String> pathMapping, boolean isUriTemplatesEnabled, Set<ResolvedType> additionalModels, Set<Tag> tags, List<VendorExtension> vendorExtensions) {
        this.documentationType = documentationType;
        this.handlerMappings = handlerMappings;
        this.apiInfo = apiInfo;
        this.groupName = groupName;
        this.apiSelector = apiSelector;
        this.ignorableParameterTypes = ignorableParameterTypes;
        this.globalResponseMessages = globalResponseMessages;
        this.globalOperationParameters = globalOperationParameter;
        this.resourceGroupingStrategy = resourceGroupingStrategy;
        this.pathProvider = pathProvider;
        this.securityContexts = securityContexts;
        this.securitySchemes = securitySchemes;
        this.listingReferenceOrdering = listingReferenceOrdering;
        this.apiDescriptionOrdering = apiDescriptionOrdering;
        this.operationOrdering = operationOrdering;
        this.produces = produces;
        this.consumes = consumes;
        this.host = host;
        this.protocols = protocols;
        this.genericsNamingStrategy = genericsNamingStrategy;
        this.pathMapping = pathMapping;
        this.isUriTemplatesEnabled = isUriTemplatesEnabled;
        this.additionalModels = additionalModels;
        this.tags = tags;
        this.alternateTypeProvider = new AlternateTypeProvider(alternateTypeRules);
        this.vendorExtensions = vendorExtensions;
    }

    public DocumentationType getDocumentationType() {
        return this.documentationType;
    }

    public List<RequestHandler> getRequestHandlers() {
        return this.handlerMappings;
    }

    public ApiInfo getApiInfo() {
        return this.apiInfo;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public ApiSelector getApiSelector() {
        return this.apiSelector;
    }

    public ImmutableSet<Class> getIgnorableParameterTypes() {
        return ImmutableSet.copyOf(this.ignorableParameterTypes);
    }

    public Map<RequestMethod, List<ResponseMessage>> getGlobalResponseMessages() {
        return this.globalResponseMessages;
    }

    public List<Parameter> getGlobalRequestParameters() {
        return this.globalOperationParameters;
    }

    /** @deprecated */
    @Deprecated
    public ResourceGroupingStrategy getResourceGroupingStrategy() {
        return this.resourceGroupingStrategy;
    }

    public PathProvider getPathProvider() {
        return this.pathProvider;
    }

    public List<SecurityContext> getSecurityContexts() {
        return this.securityContexts;
    }

    public List<? extends SecurityScheme> getSecuritySchemes() {
        return this.securitySchemes;
    }

    public Ordering<ApiListingReference> getListingReferenceOrdering() {
        return this.listingReferenceOrdering;
    }

    public Ordering<ApiDescription> getApiDescriptionOrdering() {
        return this.apiDescriptionOrdering;
    }

    public AlternateTypeProvider getAlternateTypeProvider() {
        return this.alternateTypeProvider;
    }

    public Ordering<Operation> operationOrdering() {
        return this.operationOrdering;
    }

    public Set<String> getProduces() {
        return this.produces;
    }

    public Set<String> getConsumes() {
        return this.consumes;
    }

    public String getHost() {
        return this.host;
    }

    public Set<String> getProtocols() {
        return this.protocols;
    }

    public GenericTypeNamingStrategy getGenericsNamingStrategy() {
        return this.genericsNamingStrategy;
    }

    public Optional<String> getPathMapping() {
        return this.pathMapping;
    }

    @Incubating("2.1.0")
    public boolean isUriTemplatesEnabled() {
        return this.isUriTemplatesEnabled;
    }

    public Set<ResolvedType> getAdditionalModels() {
        return this.additionalModels;
    }

    public Set<Tag> getTags() {
        return this.tags;
    }

    public List<VendorExtension> getVendorExtentions() {
        return this.vendorExtensions;
    }
}

