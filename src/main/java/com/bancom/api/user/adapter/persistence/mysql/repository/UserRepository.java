package com.bancom.api.user.adapter.persistence.mysql.repository;

import com.bancom.api.user.adapter.persistence.mysql.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <UserEntity, Long>{
}
