package com.model3d.user.service.domain.entity;

import com.model3d.system.domain.entity.AggregateRoot;
import com.model3d.system.domain.valueobject.ModelId;

public class Model extends AggregateRoot<ModelId> {
    private final String name;
    private boolean isActive;

    private Model(Builder builder) {
        super.setId(builder.modelId);
        name = builder.name;
        isActive = builder.isActive;
    }


    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }

    public static final class Builder {
        private ModelId modelId;
        private String name;
        private boolean isActive;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder modelId(ModelId val) {
            modelId = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder isActive(boolean val) {
            isActive = val;
            return this;
        }

        public Model build() {
            return new Model(this);
        }
    }
}
