package br.com.arqdev.swagger.request;

import br.com.arqdev.swagger.api.ApiDescriptionBuilder;
import br.com.arqdev.swagger.documentation.DocumentationContext;
import br.com.arqdev.swagger.operation.Operation;
import br.com.arqdev.swagger.operation.OperationModelContextsBuilder;
import br.com.arqdev.swagger.parameter.ResolvedMethodParameter;
import br.com.arqdev.swagger.type.GenericTypeNamingStrategy;
import com.fasterxml.classmate.ResolvedType;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.condition.NameValueExpression;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import springfox.documentation.RequestHandlerKey;
import springfox.documentation.schema.Model;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RequestMappingContext {
    private final OperationModelContextsBuilder operationModelContextsBuilder;
    private final DocumentationContext documentationContext;
    private final RequestHandler handler;
    private final String requestMappingPattern;
    private final ApiDescriptionBuilder apiDescriptionBuilder;
    private final Map<String, Model> modelMap = Maps.newHashMap();

    public RequestMappingContext(DocumentationContext context, RequestHandler handler) {
        this.documentationContext = context;
        this.handler = handler;
        this.requestMappingPattern = "";
        this.operationModelContextsBuilder = new OperationModelContextsBuilder(context.getGroupName(), context.getDocumentationType(), context.getAlternateTypeProvider(), context.getGenericsNamingStrategy(), context.getIgnorableParameterTypes());
        this.apiDescriptionBuilder = new ApiDescriptionBuilder(this.documentationContext.operationOrdering());
    }

    private RequestMappingContext(DocumentationContext context, RequestHandler handler, OperationModelContextsBuilder operationModelContextsBuilder, String requestMappingPattern) {
        this.documentationContext = context;
        this.handler = handler;
        this.operationModelContextsBuilder = operationModelContextsBuilder;
        this.requestMappingPattern = requestMappingPattern;
        this.apiDescriptionBuilder = new ApiDescriptionBuilder(this.documentationContext.operationOrdering());
    }

    private RequestMappingContext(DocumentationContext context, RequestHandler handler, OperationModelContextsBuilder operationModelContextsBuilder, String requestMappingPattern, Map<String, Model> knownModels) {
        this.documentationContext = context;
        this.handler = handler;
        this.operationModelContextsBuilder = operationModelContextsBuilder;
        this.requestMappingPattern = requestMappingPattern;
        this.apiDescriptionBuilder = new ApiDescriptionBuilder(this.documentationContext.operationOrdering());
        this.modelMap.putAll(knownModels);
    }

    public DocumentationContext getDocumentationContext() {
        return this.documentationContext;
    }

    public String getRequestMappingPattern() {
        return this.requestMappingPattern;
    }

    public ImmutableMap<String, Model> getModelMap() {
        return ImmutableMap.copyOf(this.modelMap);
    }

    public OperationModelContextsBuilder operationModelsBuilder() {
        return this.operationModelContextsBuilder;
    }

    public ApiDescriptionBuilder apiDescriptionBuilder() {
        return this.apiDescriptionBuilder;
    }

    public ResolvedType alternateFor(ResolvedType resolvedType) {
        return this.documentationContext.getAlternateTypeProvider().alternateFor(resolvedType);
    }

    public Ordering<Operation> operationOrdering() {
        return this.documentationContext.operationOrdering();
    }

    public RequestMappingContext copyPatternUsing(String requestMappingPattern) {
        return new RequestMappingContext(this.documentationContext, this.handler, this.operationModelContextsBuilder, requestMappingPattern);
    }

    public RequestMappingContext withKnownModels(Map<String, Model> knownModels) {
        return new RequestMappingContext(this.documentationContext, this.handler, this.operationModelContextsBuilder, this.requestMappingPattern, knownModels);
    }

    public ImmutableSet<Class> getIgnorableParameterTypes() {
        return this.documentationContext.getIgnorableParameterTypes();
    }

    public GenericTypeNamingStrategy getGenericsNamingStrategy() {
        return this.documentationContext.getGenericsNamingStrategy();
    }

    public ImmutableSet<ResolvedType> getAdditionalModels() {
        return ImmutableSet.copyOf(this.documentationContext.getAdditionalModels());
    }

    public PatternsRequestCondition getPatternsCondition() {
        return this.handler.getPatternsCondition();
    }

    public String getName() {
        return this.handler.getName();
    }

    public Set<RequestMethod> getMethodsCondition() {
        return this.handler.supportedMethods();
    }

    public Set<? extends MediaType> produces() {
        return this.handler.produces();
    }

    public Set<? extends MediaType> consumes() {
        return this.handler.consumes();
    }

    public Set<NameValueExpression<String>> headers() {
        return this.handler.headers();
    }

    public Set<NameValueExpression<String>> params() {
        return this.handler.params();
    }

    public String getGroupName() {
        return this.handler.groupName();
    }

    public List<ResolvedMethodParameter> getParameters() {
        return this.handler.getParameters();
    }

    public <T extends Annotation> Optional<T> findAnnotation(Class<T> annotation) {
        return this.handler.findAnnotation(annotation);
    }

    public <T extends Annotation> Optional<T> findControllerAnnotation(Class<T> annotation) {
        return this.handler.findControllerAnnotation(annotation);
    }

    public <T extends Annotation> List<T> findAnnotations(Class<T> annotation) {
        List<T> annotations = Lists.newArrayList();
        Optional<T> methodAnnotation = this.findAnnotation(annotation);
        Optional<T> controllerAnnotation = this.findControllerAnnotation(annotation);
        if (methodAnnotation.isPresent()) {
            annotations.add(methodAnnotation.get());
        }

        if (controllerAnnotation.isPresent()) {
            annotations.add(controllerAnnotation.get());
        }

        return annotations;
    }

    public ResolvedType getReturnType() {
        return this.handler.getReturnType();
    }

    public RequestHandlerKey key() {
        return this.handler.key();
    }
}

