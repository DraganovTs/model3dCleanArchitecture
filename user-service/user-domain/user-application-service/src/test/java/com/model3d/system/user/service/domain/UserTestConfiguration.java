package com.model3d.system.user.service.domain;

import com.model3d.system.user.service.domain.ports.output.message.publisher.user.UserCreatedMessagePublisher;
import com.model3d.system.user.service.domain.ports.output.repository.UserRepository;
import com.model3d.user.service.domain.UserDomainService;
import com.model3d.user.service.domain.UserDomainServiceImpl;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.model3d.system")
public class UserTestConfiguration {

    @Bean
    public UserCreatedMessagePublisher userCreatedMessagePublisher(){
        return Mockito.mock(UserCreatedMessagePublisher.class);
    }

    @Bean
    public UserRepository userRepository(){
        return Mockito.mock(UserRepository.class);
    }

    @Bean
    public UserDomainService userDomainService(){
        return new UserDomainServiceImpl();
    }


}
