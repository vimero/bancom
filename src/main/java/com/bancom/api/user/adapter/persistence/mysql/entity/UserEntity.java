package com.bancom.api.user.adapter.persistence.mysql.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable {

    @Id
    private Long id;

    @Column
    private String cellphone;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column
    private String password;

    @Column
    @NotNull
    private LocalDateTime dateCreated;

    @Column
    private LocalDateTime dateUpdated;

}
