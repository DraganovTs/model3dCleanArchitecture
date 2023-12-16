package com.model3d.system.user.service.domain;

import com.model3d.system.domain.valueobject.UserId;
import com.model3d.system.user.service.domain.dto.createandupdate.CreateUserCommand;
import com.model3d.system.user.service.domain.dto.createandupdate.CreateUserResponse;
import com.model3d.system.user.service.domain.mapper.UserDataMapper;
import com.model3d.system.user.service.domain.ports.input.service.UserApplicationService;
import com.model3d.system.user.service.domain.ports.output.repository.UserRepository;
import com.model3d.user.service.domain.entity.User;
import com.model3d.user.service.domain.exception.UserDomainException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = UserTestConfiguration.class)
public class UserApplicationServiceTest {

    @Autowired
    private UserApplicationService userApplicationService;
    @Autowired
    private UserDataMapper userDataMapper;
    @Autowired
    private UserRepository userRepository;

    private final UUID USER_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb41");
    private final String EXIST_EMAIL = "exist@example.com";
    private final String EXIST_USERNAME = "existusername";
    private CreateUserCommand createUserCommand;
    private CreateUserCommand createUserCommandWrongEmail;
    private CreateUserCommand createUserCommandOffenciveUsername;
    private CreateUserCommand createUserCommandEmptyEmail;
    private CreateUserCommand createUserCommandEmptyUsername;
    private CreateUserCommand createUserCommandExistEmail;
    private CreateUserCommand createUserCommandExistUsername;


    @BeforeAll
    private void init() {
        createUserCommand = CreateUserCommand.builder()
                .email("usertest@example.com")
                .username("usertest")
                .build();
        createUserCommandWrongEmail = CreateUserCommand.builder()
                .email("usertestexamplecom")
                .username("usertest")
                .build();
        createUserCommandOffenciveUsername = CreateUserCommand.builder()
                .email("usertest@example.com")
                .username("offensive1")
                .build();
        createUserCommandEmptyEmail = CreateUserCommand.builder()
                .email("")
                .username("usertest")
                .build();
        createUserCommandEmptyUsername = CreateUserCommand.builder()
                .email("usertest@example.com")
                .username("")
                .build();
        createUserCommandExistEmail = CreateUserCommand.builder()
                .email(EXIST_EMAIL)
                .username("usertest")
                .build();
        createUserCommandExistUsername = CreateUserCommand.builder()
                .email("usertest@example.com")
                .username(EXIST_USERNAME)
                .build();

        User user = userDataMapper.createUserCommandToUser(createUserCommand);
        user.setId(new UserId(USER_ID));
        User userExistEmail = userDataMapper.createUserCommandToUser(createUserCommandExistEmail);
        userExistEmail.setId(new UserId(USER_ID));
        User userExistUsername = userDataMapper.createUserCommandToUser(createUserCommandExistUsername);
        userExistUsername.setId(new UserId(USER_ID));


        when(userRepository.findByEmail(EXIST_EMAIL)).thenReturn(Optional.of(userExistEmail));
        when(userRepository.findByUsername(EXIST_USERNAME)).thenReturn(Optional.of(userExistUsername));
        when(userRepository.save(any(User.class))).thenReturn(user);
    }


    @Test
    public void testCreateUser() {
        CreateUserResponse createUserResponse = userApplicationService.createUser(createUserCommand);
        assertEquals("usertest@example.com", createUserResponse.getEmail());
        assertEquals("usertest", createUserResponse.getUsername());
        assertEquals("User created Successfully", createUserResponse.getMessage());
    }

    @Test
    public void createUserWrongEmail(){
      UserDomainException userDomainException = assertThrows(UserDomainException.class,
               () -> userApplicationService.createUser(createUserCommandWrongEmail));
      assertEquals(userDomainException.getMessage(),"Email is not valid");
    }


    @Test
    public void createUserOffenciveUsername(){
        UserDomainException userDomainException = assertThrows(UserDomainException.class,
                () -> userApplicationService.createUser(createUserCommandOffenciveUsername));
        assertEquals(userDomainException.getMessage(),"Username is not valid");
    }

    @Test
    public void createUserEmptyEmail(){
        UserDomainException userDomainException = assertThrows(UserDomainException.class,
                () -> userApplicationService.createUser(createUserCommandEmptyEmail));
        assertEquals(userDomainException.getMessage(),"Email is not valid");
    }

    @Test
    public void createUserEmptyUsername(){
        UserDomainException userDomainException = assertThrows(UserDomainException.class,
                () -> userApplicationService.createUser(createUserCommandEmptyUsername));
        assertEquals(userDomainException.getMessage(),"Username is not valid");
    }

    @Test
    public void createUserExistEmail(){
        UserDomainException userDomainException = assertThrows(UserDomainException.class,
                () -> userApplicationService.createUser(createUserCommandExistEmail));
        assertEquals(userDomainException.getMessage(),"User with email: exist@example.com already exist");
    }

    @Test
    public void createUserExistUsername(){
        UserDomainException userDomainException = assertThrows(UserDomainException.class,
                () -> userApplicationService.createUser(createUserCommandExistUsername));
        assertEquals(userDomainException.getMessage(),"User with username: existusername already exist");
    }
}
