package br.com.arqdev.swagger.parameter;

import com.fasterxml.classmate.ResolvedType;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import org.springframework.core.MethodParameter;

import java.lang.annotation.Annotation;
import java.util.List;

public class ResolvedMethodParameter {
    private final int parameterIndex;
    private final List<Annotation> annotations;
    private final String defaultName;
    private final ResolvedType parameterType;

    public ResolvedMethodParameter(String paramName, MethodParameter methodParameter, ResolvedType parameterType) {
        this(methodParameter.getParameterIndex(), paramName, Lists.newArrayList(methodParameter.getParameterAnnotations()), parameterType);
    }

    public ResolvedMethodParameter(int parameterIndex, String defaultName, List<Annotation> annotations, ResolvedType parameterType) {
        this.parameterIndex = parameterIndex;
        this.defaultName = defaultName;
        this.parameterType = parameterType;
        this.annotations = annotations;
    }

    public ResolvedType getParameterType() {
        return this.parameterType;
    }

    public boolean hasParameterAnnotations() {
        return !this.annotations.isEmpty();
    }

    public boolean hasParameterAnnotation(Class<? extends Annotation> annotation) {
        return FluentIterable.from(this.annotations).filter(annotation).size() > 0;
    }

    public <T extends Annotation> Optional<T> findAnnotation(Class<T> annotation) {
        return FluentIterable.from(this.annotations).filter(annotation).first();
    }

    public int getParameterIndex() {
        return this.parameterIndex;
    }

    public Optional<String> defaultName() {
        return Optional.fromNullable(this.defaultName);
    }

    public ResolvedMethodParameter replaceResolvedParameterType(ResolvedType parameterType) {
        return new ResolvedMethodParameter(this.parameterIndex, this.defaultName, this.annotations, parameterType);
    }

    public List<Annotation> getAnnotations() {
        return this.annotations;
    }

    public ResolvedMethodParameter annotate(Annotation annotation) {
        List<Annotation> annotations = Lists.newArrayList(this.annotations);
        annotations.add(annotation);
        return new ResolvedMethodParameter(this.parameterIndex, this.defaultName, annotations, this.parameterType);
    }
}

