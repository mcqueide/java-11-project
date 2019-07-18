package br.com.arqdev.util.swagger.api;

import br.com.arqdev.util.swagger.operation.Operation;
import com.google.common.base.Optional;

import java.util.List;

public class ApiDescription {
    private final String groupName;
    private final String path;
    private final String description;
    private final List<Operation> operations;
    private final Boolean hidden;

    /** @deprecated */
    @Deprecated
    public ApiDescription(String path, String description, List<Operation> operations, Boolean hidden) {
        this((String)null, path, description, operations, hidden);
    }

    public ApiDescription(String groupName, String path, String description, List<Operation> operations, Boolean hidden) {
        this.groupName = groupName;
        this.path = path;
        this.description = description;
        this.operations = operations;
        this.hidden = hidden;
    }

    public String getPath() {
        return this.path;
    }

    public String getDescription() {
        return this.description;
    }

    public List<Operation> getOperations() {
        return this.operations;
    }

    public Boolean isHidden() {
        return this.hidden;
    }

    public Optional<String> getGroupName() {
        return Optional.fromNullable(this.groupName);
    }
}

