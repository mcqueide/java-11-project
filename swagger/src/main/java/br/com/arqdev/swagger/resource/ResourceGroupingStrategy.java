package br.com.arqdev.swagger.resource;

import br.com.arqdev.swagger.documentation.DocumentationType;
import org.springframework.plugin.core.Plugin;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import springfox.documentation.service.ResourceGroup;

import java.util.Set;

/** @deprecated */
@Deprecated
public interface ResourceGroupingStrategy extends Plugin<DocumentationType> {
    Set<ResourceGroup> getResourceGroups(RequestMappingInfo var1, HandlerMethod var2);

    /** @deprecated */
    @Deprecated
    String getResourceDescription(RequestMappingInfo var1, HandlerMethod var2);

    /** @deprecated */
    @Deprecated
    Integer getResourcePosition(RequestMappingInfo var1, HandlerMethod var2);
}

