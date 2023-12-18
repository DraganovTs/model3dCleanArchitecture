package com.model3d.user.service.domain.entity;

import com.model3d.system.domain.entity.AggregateRoot;
import com.model3d.system.domain.valueobject.ModelId;
import com.model3d.system.domain.valueobject.UserId;

public class Model extends AggregateRoot<ModelId> {
    private final String name;
    private boolean isActive;
    private UserId ownedBy;
    private UserId downloadedBy;
    private UserId  likedBy;

    private Model(Builder builder) {
        super.setId(builder.modelId);
        name = builder.name;
        isActive = builder.isActive;
        ownedBy = builder.ownedBy;
        downloadedBy = builder.downloadedBy;
        likedBy = builder.likedBy;
    }


    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }

    public UserId getOwnedBy() {
        return ownedBy;
    }

    public UserId getDownloadedBy() {
        return downloadedBy;
    }

    public UserId getLikedBy() {
        return likedBy;
    }

    public static final class Builder {
        private ModelId modelId;
        private String name;
        private boolean isActive;
        private UserId ownedBy;
        private UserId downloadedBy;
        private UserId likedBy;

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

        public Builder ownedBy(UserId val) {
            ownedBy = val;
            return this;
        }

        public Builder downloadedBy(UserId val) {
            downloadedBy = val;
            return this;
        }

        public Builder likedBy(UserId val) {
            likedBy = val;
            return this;
        }

        public Model build() {
            return new Model(this);
        }
    }
}
