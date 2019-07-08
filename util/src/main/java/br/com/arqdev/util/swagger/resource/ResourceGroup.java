package br.com.arqdev.util.swagger.resource;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Optional;

public class ResourceGroup {
    private final String groupName;
    private final Class<?> controllerClazz;
    private final Integer position;

    public ResourceGroup(String groupName, Class<?> controllerClazz) {
        this(groupName, controllerClazz, 0);
    }

    public ResourceGroup(String groupName, Class<?> controllerClazz, Integer position) {
        this.groupName = groupName;
        this.controllerClazz = controllerClazz;
        this.position = position;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public Integer getPosition() {
        return this.position;
    }

    public Optional<? extends Class<?>> getControllerClass() {
        return Optional.fromNullable(this.controllerClazz);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj == this) {
            return true;
        } else if (obj.getClass() != this.getClass()) {
            return false;
        } else {
            ResourceGroup rhs = (ResourceGroup)obj;
            return Objects.equal(this.groupName, rhs.groupName) && Objects.equal(this.position, rhs.position) && Objects.equal(this.controllerClazz, rhs.controllerClazz);
        }
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.groupName, this.controllerClazz, this.position});
    }

    public String toString() {
        return String.format("ResourceGroup{groupName='%s', position=%d, controller=%s}", this.groupName, this.position, this.getControllerClass().transform(this.toName()).or(""));
    }

    private Function<Class<?>, String> toName() {
        return new Function<Class<?>, String>() {
            public String apply(Class<?> input) {
                return input.getName();
            }
        };
    }
}

