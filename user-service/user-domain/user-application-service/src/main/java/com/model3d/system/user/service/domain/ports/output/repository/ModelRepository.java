package com.model3d.system.user.service.domain.ports.output.repository;

import com.model3d.system.domain.valueobject.ModelId;
import com.model3d.system.user.service.domain.dto.Model;

import java.util.Optional;

public interface ModelRepository {

    Optional<Model> findModelInformation(ModelId modelId);
}
