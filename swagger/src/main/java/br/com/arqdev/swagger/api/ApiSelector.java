package br.com.arqdev.swagger.api;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import springfox.documentation.RequestHandler;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;

public class ApiSelector {
    public static final ApiSelector DEFAULT = new ApiSelector(Predicates.and(Predicates.not(RequestHandlerSelectors.withClassAnnotation(ApiIgnore.class)), Predicates.not(RequestHandlerSelectors.withMethodAnnotation(ApiIgnore.class))), PathSelectors.any());
    private final Predicate<RequestHandler> requestHandlerSelector;
    private final Predicate<String> pathSelector;

    public ApiSelector(Predicate<RequestHandler> requestHandlerSelector, Predicate<String> pathSelector) {
        this.requestHandlerSelector = requestHandlerSelector;
        this.pathSelector = pathSelector;
    }

    public Predicate<RequestHandler> getRequestHandlerSelector() {
        return this.requestHandlerSelector;
    }

    public Predicate<String> getPathSelector() {
        return this.pathSelector;
    }
}
