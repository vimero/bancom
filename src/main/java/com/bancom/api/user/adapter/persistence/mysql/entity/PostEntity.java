package com.bancom.api.user.adapter.persistence.mysql.entity;

import com.bancom.api.user.application.domain.User;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity implements Serializable {

    @Id
    private Long id;

    @Column
    private String text;

    @ManyToOne
    @JoinColumn(name="userId", nullable=false)
    private UserEntity user;

    @Column
    @NotNull
    private LocalDateTime dateCreated;

    @Column
    private LocalDateTime dateUpdated;

}
