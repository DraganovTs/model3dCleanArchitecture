package com.model3d.system.user.service.domain;

import com.model3d.system.user.service.domain.dto.message.ModelResponse;
import com.model3d.system.user.service.domain.ports.input.message.listener.model.ModelResponseMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class ModelResponseMessageListenerImpl implements ModelResponseMessageListener {


    @Override
    public void modelAddCompleted(ModelResponse modelResponse) {

    }

    @Override
    public void modelAddCancelled(ModelResponse modelResponse) {

    }
}
