package com.prabhav.LibraryManagement.Repository;

import com.prabhav.LibraryManagement.Entity.Role;
import com.prabhav.LibraryManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

   // List<User> findByRoles(String role);
//
//    @Query("SELECT u FROM User u WHERE u.role = 'USER'")
   // List<User> findUserByRoleName(String role);

   // List<User> findAllByRoles(String role);
}
