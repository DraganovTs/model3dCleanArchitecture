package com.model3d.user.service.domain;

import com.model3d.user.service.domain.entity.Model;
import com.model3d.user.service.domain.entity.User;
import com.model3d.user.service.domain.event.*;

public interface UserDomainService {

    UserCreatedEvent createUser(User user);

    UserUpdatedEvent updateUser(User user);

    UserDeletedEvent deleteUser(User user);

    UserDownloadedModelEvent userDownloadModel(User user,Model model);

    UserLikedModelEvent userLikeModel(User user, Model model);

    UserUploadModelEvent userUploadModel(User user,Model model);
}
