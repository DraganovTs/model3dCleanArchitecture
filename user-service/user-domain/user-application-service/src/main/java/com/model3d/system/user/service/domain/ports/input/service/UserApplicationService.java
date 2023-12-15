package com.model3d.system.user.service.domain.ports.input.service;

import com.model3d.system.user.service.domain.dto.createandupdate.CreateUserCommand;
import com.model3d.system.user.service.domain.dto.createandupdate.CreateUserResponse;
import com.model3d.system.user.service.domain.dto.createandupdate.UpdateUserCommand;
import com.model3d.system.user.service.domain.dto.createandupdate.UpdateUserResponse;

import javax.validation.Valid;

public interface UserApplicationService {

  CreateUserResponse createUser(@Valid CreateUserCommand createUserCommand);
  UpdateUserResponse updateUser(@Valid UpdateUserCommand updateUserCommand);


}
