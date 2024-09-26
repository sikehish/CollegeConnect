package com.sikehish.collegeconnect.repository;
import com.sikehish.collegeconnect.model.Role;
import com.sikehish.collegeconnect.model.WebUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WebUserRepository extends JpaRepository<WebUser, Long> {

    // Custom query methods

    Optional<WebUser> findByUsername(String username);

    Optional<WebUser> findByEmail(String email);

    List<WebUser> findByRole(Role role);
}
