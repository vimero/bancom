package com.bancom.api.user.application.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Post {

    private Long id;
    private String text;
    private Long user;

}
