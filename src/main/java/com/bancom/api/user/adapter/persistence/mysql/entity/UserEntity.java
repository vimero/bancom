package com.bancom.api.user.adapter.persistence.mysql.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class UserEntity implements Serializable {

    @Id
    private Long id;

}
