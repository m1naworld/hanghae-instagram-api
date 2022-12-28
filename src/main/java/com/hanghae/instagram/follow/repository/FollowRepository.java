package com.hanghae.instagram.follow.repository;

import com.hanghae.instagram.follow.entity.Follow;
import com.hanghae.instagram.follow.entity.FollowCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    void deleteById(FollowCompositeKey compositeKey);
}
