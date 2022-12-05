package com.trungntm.infrastructure.repository;

import com.trungntm.domain.entity.user.User;
import com.vladmihalcea.spring.repository.HibernateRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends HibernateRepository<User>, JpaRepository<User, Long> {

    Optional<User> findByUsernameAndEnabled(String username, boolean enable);
}
