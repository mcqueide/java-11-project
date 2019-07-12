package br.com.arqdev.swagger.request;

import br.com.arqdev.swagger.parameter.ResolvedMethodParameter;
import com.fasterxml.classmate.ResolvedType;
import com.google.common.base.Optional;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.NameValueExpression;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import springfox.documentation.RequestHandlerKey;
import springfox.documentation.annotations.Incubating;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

public interface RequestHandler {
    /** @deprecated */
    @Deprecated
    Class<?> declaringClass();

    boolean isAnnotatedWith(Class<? extends Annotation> var1);

    PatternsRequestCondition getPatternsCondition();

    String groupName();

    String getName();

    Set<RequestMethod> supportedMethods();

    Set<? extends MediaType> produces();

    Set<? extends MediaType> consumes();

    Set<NameValueExpression<String>> headers();

    Set<NameValueExpression<String>> params();

    <T extends Annotation> Optional<T> findAnnotation(Class<T> var1);

    RequestHandlerKey key();

    List<ResolvedMethodParameter> getParameters();

    ResolvedType getReturnType();

    <T extends Annotation> Optional<T> findControllerAnnotation(Class<T> var1);

    /** @deprecated */
    @Deprecated
    RequestMappingInfo getRequestMapping();

    /** @deprecated */
    @Deprecated
    HandlerMethod getHandlerMethod();

    @Incubating
    RequestHandler combine(RequestHandler var1);
}

