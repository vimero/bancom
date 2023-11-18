package com.bancom.api.user.application.port.output;

import com.bancom.api.user.application.domain.User;

import java.util.List;

public interface UserApiPort {

   List<User> list();

}
