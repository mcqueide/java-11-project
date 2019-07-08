package br.com.arqdev.util.swagger.operation;

import br.com.arqdev.util.swagger.documentation.DocumentationContext;
import br.com.arqdev.util.swagger.documentation.DocumentationType;
import br.com.arqdev.util.swagger.parameter.Parameter;
import br.com.arqdev.util.swagger.parameter.ResolvedMethodParameter;
import br.com.arqdev.util.swagger.request.RequestMappingContext;
import br.com.arqdev.util.swagger.response.ResponseMessage;
import br.com.arqdev.util.swagger.security.SecurityContext;
import br.com.arqdev.util.swagger.type.AlternateTypeProvider;
import br.com.arqdev.util.swagger.type.GenericTypeNamingStrategy;
import com.fasterxml.classmate.ResolvedType;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.condition.NameValueExpression;
import springfox.documentation.builders.BuilderDefaults;
import springfox.documentation.builders.OperationBuilder;

public class OperationContext {
    private final OperationBuilder operationBuilder;
    private final RequestMethod requestMethod;
    private final RequestMappingContext requestContext;
    private final int operationIndex;

    public OperationContext(OperationBuilder operationBuilder, RequestMethod requestMethod, RequestMappingContext requestContext, int operationIndex) {
        this.operationBuilder = operationBuilder;
        this.requestMethod = requestMethod;
        this.requestContext = requestContext;
        this.operationIndex = operationIndex;
    }

    public OperationBuilder operationBuilder() {
        return this.operationBuilder;
    }

    public HttpMethod httpMethod() {
        return HttpMethod.valueOf(this.requestMethod.toString());
    }

    public int operationIndex() {
        return this.operationIndex;
    }

    public List<ResponseMessage> getGlobalResponseMessages(String forHttpMethod) {
        DocumentationContext documentationContext = this.getDocumentationContext();
        return (List)(documentationContext.getGlobalResponseMessages().containsKey(RequestMethod.valueOf(forHttpMethod)) ? (List)documentationContext.getGlobalResponseMessages().get(RequestMethod.valueOf(forHttpMethod)) : Lists.newArrayList());
    }

    public List<Parameter> getGlobalOperationParameters() {
        return BuilderDefaults.nullToEmptyList(this.getDocumentationContext().getGlobalRequestParameters());
    }

    public List<SecurityContext> securityContext() {
        return FluentIterable.from(this.getDocumentationContext().getSecurityContexts()).filter(this.pathMatches()).toList();
    }

    private Predicate<SecurityContext> pathMatches() {
        return new Predicate<SecurityContext>() {
            public boolean apply(SecurityContext input) {
                return input.securityForOperation(OperationContext.this) != null;
            }
        };
    }

    public String requestMappingPattern() {
        return this.requestContext.getRequestMappingPattern();
    }

    public DocumentationContext getDocumentationContext() {
        return this.requestContext.getDocumentationContext();
    }

    public DocumentationType getDocumentationType() {
        return this.getDocumentationContext().getDocumentationType();
    }

    public AlternateTypeProvider getAlternateTypeProvider() {
        return this.getDocumentationContext().getAlternateTypeProvider();
    }

    public ResolvedType alternateFor(ResolvedType resolved) {
        return this.getAlternateTypeProvider().alternateFor(resolved);
    }

    public Set<? extends MediaType> produces() {
        return this.requestContext.produces();
    }

    public Set<? extends MediaType> consumes() {
        return this.requestContext.consumes();
    }

    public ImmutableSet<Class> getIgnorableParameterTypes() {
        return ImmutableSet.copyOf(this.getDocumentationContext().getIgnorableParameterTypes());
    }

    public GenericTypeNamingStrategy getGenericsNamingStrategy() {
        return this.getDocumentationContext().getGenericsNamingStrategy();
    }

    public Set<NameValueExpression<String>> headers() {
        return this.requestContext.headers();
    }

    public Set<NameValueExpression<String>> params() {
        return this.requestContext.params();
    }

    public String getName() {
        return this.requestContext.getName();
    }

    public String getGroupName() {
        return this.requestContext.getGroupName();
    }

    public List<ResolvedMethodParameter> getParameters() {
        return this.requestContext.getParameters();
    }

    public <T extends Annotation> Optional<T> findAnnotation(Class<T> annotation) {
        return this.requestContext.findAnnotation(annotation);
    }

    public ResolvedType getReturnType() {
        return this.requestContext.getReturnType();
    }

    public <T extends Annotation> Optional<T> findControllerAnnotation(Class<T> annotation) {
        return this.requestContext.findControllerAnnotation(annotation);
    }

    public <T extends Annotation> List<T> findAllAnnotations(Class<T> annotation) {
        return this.requestContext.findAnnotations(annotation);
    }
}
