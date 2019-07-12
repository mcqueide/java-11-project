package br.com.arqdev.swagger.api;

import br.com.arqdev.swagger.documentation.DocumentationContext;
import br.com.arqdev.swagger.documentation.DocumentationContextBuilder;
import br.com.arqdev.swagger.documentation.DocumentationPlugin;
import br.com.arqdev.swagger.documentation.DocumentationType;
import br.com.arqdev.swagger.operation.Operation;
import br.com.arqdev.swagger.parameter.Parameter;
import br.com.arqdev.swagger.response.ResponseMessage;
import br.com.arqdev.swagger.security.SecurityContext;
import br.com.arqdev.swagger.security.SecurityScheme;
import br.com.arqdev.swagger.tag.Tag;
import br.com.arqdev.swagger.type.CodeGenGenericTypeNamingStrategy;
import br.com.arqdev.swagger.type.DefaultGenericTypeNamingStrategy;
import br.com.arqdev.swagger.type.GenericTypeNamingStrategy;
import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.PathProvider;
import springfox.documentation.annotations.Incubating;
import springfox.documentation.builders.BuilderDefaults;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.WildcardType;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Docket implements DocumentationPlugin {
    public static final String DEFAULT_GROUP_NAME = "default";
    private final DocumentationType documentationType;
    private final List<SecurityContext> securityContexts = Lists.newArrayList();
    private final Map<RequestMethod, List<ResponseMessage>> responseMessages = Maps.newHashMap();
    private final List<Parameter> globalOperationParameters = Lists.newArrayList();
    private final List<Function<TypeResolver, AlternateTypeRule>> ruleBuilders = Lists.newArrayList();
    private final Set<Class> ignorableParameterTypes = Sets.newHashSet();
    private final Set<String> protocols = Sets.newHashSet();
    private final Set<String> produces = Sets.newHashSet();
    private final Set<String> consumes = Sets.newHashSet();
    private final Set<ResolvedType> additionalModels = Sets.newHashSet();
    private final Set<Tag> tags = Sets.newHashSet();
    private PathProvider pathProvider;
    private List<? extends SecurityScheme> securitySchemes;
    private Ordering<ApiListingReference> apiListingReferenceOrdering;
    private Ordering<ApiDescription> apiDescriptionOrdering;
    private Ordering<Operation> operationOrdering;
    private ApiInfo apiInfo;
    private String groupName;
    private boolean enabled;
    private GenericTypeNamingStrategy genericsNamingStrategy;
    private boolean applyDefaultResponseMessages;
    private String host;
    private Optional<String> pathMapping;
    private ApiSelector apiSelector;
    private boolean enableUrlTemplating;
    private List<VendorExtension> vendorExtensions;

    public Docket(DocumentationType documentationType) {
        this.apiInfo = ApiInfo.DEFAULT;
        this.groupName = "default";
        this.enabled = true;
        this.genericsNamingStrategy = new DefaultGenericTypeNamingStrategy();
        this.applyDefaultResponseMessages = true;
        this.host = "";
        this.pathMapping = Optional.absent();
        this.apiSelector = ApiSelector.DEFAULT;
        this.enableUrlTemplating = false;
        this.vendorExtensions = Lists.newArrayList();
        this.documentationType = documentationType;
    }

    public Docket extensions(List<VendorExtension> vendorExtensions) {
        this.vendorExtensions.addAll(vendorExtensions);
        return this;
    }

    public Docket apiInfo(ApiInfo apiInfo) {
        this.apiInfo = (ApiInfo)BuilderDefaults.defaultIfAbsent(apiInfo, apiInfo);
        return this;
    }

    public Docket securitySchemes(List<? extends SecurityScheme> securitySchemes) {
        this.securitySchemes = securitySchemes;
        return this;
    }

    public Docket securityContexts(List<SecurityContext> securityContexts) {
        this.securityContexts.addAll(securityContexts);
        return this;
    }

    public Docket groupName(String groupName) {
        this.groupName = (String)BuilderDefaults.defaultIfAbsent(groupName, this.groupName);
        return this;
    }

    public Docket pathProvider(PathProvider pathProvider) {
        this.pathProvider = pathProvider;
        return this;
    }

    public Docket globalResponseMessage(RequestMethod requestMethod, List<ResponseMessage> responseMessages) {
        this.responseMessages.put(requestMethod, responseMessages);
        return this;
    }

    public Docket globalOperationParameters(List<Parameter> operationParameters) {
        this.globalOperationParameters.addAll(BuilderDefaults.nullToEmptyList(operationParameters));
        return this;
    }

    public Docket ignoredParameterTypes(Class... classes) {
        this.ignorableParameterTypes.addAll(Arrays.asList(classes));
        return this;
    }

    public Docket produces(Set<String> produces) {
        this.produces.addAll(produces);
        return this;
    }

    public Docket consumes(Set<String> consumes) {
        this.consumes.addAll(consumes);
        return this;
    }

    @Incubating("2.3")
    public Docket host(String host) {
        this.host = (String)BuilderDefaults.defaultIfAbsent(host, this.host);
        return this;
    }

    public Docket protocols(Set<String> protocols) {
        this.protocols.addAll(protocols);
        return this;
    }

    public Docket alternateTypeRules(AlternateTypeRule... alternateTypeRules) {
        this.ruleBuilders.addAll(FluentIterable.from(Lists.newArrayList(alternateTypeRules)).transform(this.identityRuleBuilder()).toList());
        return this;
    }

    public Docket operationOrdering(Ordering<Operation> operationOrdering) {
        this.operationOrdering = operationOrdering;
        return this;
    }

    public Docket directModelSubstitute(Class clazz, Class with) {
        this.ruleBuilders.add(this.newSubstitutionFunction(clazz, with));
        return this;
    }

    public Docket genericModelSubstitutes(Class... genericClasses) {
        Class[] var2 = genericClasses;
        int var3 = genericClasses.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Class clz = var2[var4];
            this.ruleBuilders.add(this.newGenericSubstitutionFunction(clz));
        }

        return this;
    }

    public Docket useDefaultResponseMessages(boolean apply) {
        this.applyDefaultResponseMessages = apply;
        return this;
    }

    public Docket apiListingReferenceOrdering(Ordering<ApiListingReference> apiListingReferenceOrdering) {
        this.apiListingReferenceOrdering = apiListingReferenceOrdering;
        return this;
    }

    public Docket apiDescriptionOrdering(Ordering<ApiDescription> apiDescriptionOrdering) {
        this.apiDescriptionOrdering = apiDescriptionOrdering;
        return this;
    }

    public Docket enable(boolean externallyConfiguredFlag) {
        this.enabled = externallyConfiguredFlag;
        return this;
    }

    public Docket forCodeGeneration(boolean forCodeGen) {
        if (forCodeGen) {
            this.genericsNamingStrategy = new CodeGenGenericTypeNamingStrategy();
        }

        return this;
    }

    public Docket pathMapping(String path) {
        this.pathMapping = Optional.fromNullable(path);
        return this;
    }

    @Incubating("2.1.0")
    public Docket enableUrlTemplating(boolean enabled) {
        this.enableUrlTemplating = enabled;
        return this;
    }

    public Docket additionalModels(ResolvedType first, ResolvedType... remaining) {
        this.additionalModels.add(first);
        this.additionalModels.addAll(Sets.newHashSet(remaining));
        return this;
    }

    public Docket tags(Tag first, Tag... remaining) {
        this.tags.add(first);
        this.tags.addAll(Sets.newHashSet(remaining));
        return this;
    }

    public ApiSelectorBuilder select() {
        return new ApiSelectorBuilder(this);
    }

    public DocumentationContext configure(DocumentationContextBuilder builder) {
        return builder.apiInfo(this.apiInfo).selector(this.apiSelector).applyDefaultResponseMessages(this.applyDefaultResponseMessages).additionalResponseMessages(this.responseMessages).additionalOperationParameters(this.globalOperationParameters).additionalIgnorableTypes(this.ignorableParameterTypes).ruleBuilders(this.ruleBuilders).groupName(this.groupName).pathProvider(this.pathProvider).securityContexts(this.securityContexts).securitySchemes(this.securitySchemes).apiListingReferenceOrdering(this.apiListingReferenceOrdering).apiDescriptionOrdering(this.apiDescriptionOrdering).operationOrdering(this.operationOrdering).produces(this.produces).consumes(this.consumes).host(this.host).protocols(this.protocols).genericsNaming(this.genericsNamingStrategy).pathMapping(this.pathMapping).enableUrlTemplating(this.enableUrlTemplating).additionalModels(this.additionalModels).tags(this.tags).vendorExtentions(this.vendorExtensions).build();
    }

    public String getGroupName() {
        return this.groupName;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public DocumentationType getDocumentationType() {
        return this.documentationType;
    }

    public boolean supports(DocumentationType delimiter) {
        return this.documentationType.equals(delimiter);
    }

    private Function<AlternateTypeRule, Function<TypeResolver, AlternateTypeRule>> identityRuleBuilder() {
        return new Function<AlternateTypeRule, Function<TypeResolver, AlternateTypeRule>>() {
            public Function<TypeResolver, AlternateTypeRule> apply(AlternateTypeRule rule) {
                return Docket.this.identityFunction(rule);
            }
        };
    }

    private Function<TypeResolver, AlternateTypeRule> identityFunction(final AlternateTypeRule rule) {
        return new Function<TypeResolver, AlternateTypeRule>() {
            public AlternateTypeRule apply(TypeResolver typeResolver) {
                return rule;
            }
        };
    }

    Docket selector(ApiSelector apiSelector) {
        this.apiSelector = apiSelector;
        return this;
    }

    private Function<TypeResolver, AlternateTypeRule> newSubstitutionFunction(final Class clazz, final Class with) {
        return new Function<TypeResolver, AlternateTypeRule>() {
            public AlternateTypeRule apply(TypeResolver typeResolver) {
                return AlternateTypeRules.newRule(typeResolver.resolve(clazz, new Type[0]), typeResolver.resolve(with, new Type[0]), -2147480648);
            }
        };
    }

    private Function<TypeResolver, AlternateTypeRule> newGenericSubstitutionFunction(final Class clz) {
        return new Function<TypeResolver, AlternateTypeRule>() {
            public AlternateTypeRule apply(TypeResolver typeResolver) {
                return AlternateTypeRules.newRule(typeResolver.resolve(clz, new Type[]{WildcardType.class}), typeResolver.resolve(WildcardType.class, new Type[0]), -2147481648);
            }
        };
    }
}

