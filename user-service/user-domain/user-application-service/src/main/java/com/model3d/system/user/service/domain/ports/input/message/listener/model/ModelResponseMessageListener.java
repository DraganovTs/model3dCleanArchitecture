package com.model3d.system.user.service.domain.ports.input.message.listener.model;

import com.model3d.system.user.service.domain.dto.message.ModelResponse;

public interface ModelResponseMessageListener {

    void modelAddCompleted(ModelResponse modelResponse);
    void modelAddCancelled(ModelResponse modelResponse);
}
