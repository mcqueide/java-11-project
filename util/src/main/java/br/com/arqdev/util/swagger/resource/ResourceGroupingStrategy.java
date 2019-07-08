package br.com.arqdev.util.swagger.resource;

import java.util.Set;

import br.com.arqdev.util.swagger.documentation.DocumentationType;
import org.springframework.plugin.core.Plugin;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import springfox.documentation.service.ResourceGroup;

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

