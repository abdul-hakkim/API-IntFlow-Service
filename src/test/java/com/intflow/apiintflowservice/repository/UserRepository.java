package com.intflow.apiintflowservice.repository;

import com.intflow.apiintflowservice.model.User;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long id);
}