package com.bancom.api.user.adapter.persistence.mysql.repository;

import com.bancom.api.user.adapter.persistence.mysql.entity.PostEntity;
import com.bancom.api.user.adapter.persistence.mysql.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository <PostEntity, Long>{
}
