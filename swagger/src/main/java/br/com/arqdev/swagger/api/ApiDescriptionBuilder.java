package br.com.arqdev.swagger.api;

import br.com.arqdev.swagger.operation.Operation;
import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.Ordering;
import springfox.documentation.builders.BuilderDefaults;

import java.util.List;

public class ApiDescriptionBuilder {
    private String groupName;
    private String path;
    private String description;
    private List<Operation> operations;
    private Ordering<Operation> operationOrdering;
    private Boolean hidden;
    private Function<String, String> pathDecorator = Functions.identity();

    public ApiDescriptionBuilder(Ordering<Operation> operationOrdering) {
        this.operationOrdering = operationOrdering;
    }

    public ApiDescriptionBuilder path(String path) {
        this.path = (String)BuilderDefaults.defaultIfAbsent(path, this.path);
        return this;
    }

    public ApiDescriptionBuilder description(String description) {
        this.description = (String)BuilderDefaults.defaultIfAbsent(description, this.description);
        return this;
    }

    public ApiDescriptionBuilder operations(List<Operation> operations) {
        if (operations != null) {
            this.operations = this.operationOrdering.sortedCopy(operations);
        }

        return this;
    }

    public ApiDescriptionBuilder hidden(boolean hidden) {
        this.hidden = hidden;
        return this;
    }

    public ApiDescriptionBuilder pathDecorator(Function<String, String> pathDecorator) {
        this.pathDecorator = (Function)BuilderDefaults.defaultIfAbsent(pathDecorator, this.pathDecorator);
        return this;
    }

    public ApiDescriptionBuilder groupName(String groupName) {
        this.groupName = (String)BuilderDefaults.defaultIfAbsent(groupName, this.groupName);
        return this;
    }

    public ApiDescription build() {
        return new ApiDescription(this.groupName, (String)this.pathDecorator.apply(this.path), this.description, this.operations, this.hidden);
    }
}

