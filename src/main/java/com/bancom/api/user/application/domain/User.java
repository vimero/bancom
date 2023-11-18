package com.bancom.api.user.application.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private Long id;
    private String cellphone;
    private String name;
    private String lastName;
    private String password;

}
