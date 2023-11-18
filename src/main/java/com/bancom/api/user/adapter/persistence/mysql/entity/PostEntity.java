package com.bancom.api.user.adapter.persistence.mysql.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class PostEntity implements Serializable {

    @Id
    private Long id;

    @Column
    private String text;

    @Column
    @NotNull
    private LocalDateTime dateCreated;

    @Column
    private LocalDateTime dateUpdated;

}
