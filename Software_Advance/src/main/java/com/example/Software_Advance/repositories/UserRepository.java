package com.example.Software_Advance.repositories;

import com.example.Software_Advance.models.Tables.User;
import com.example.Software_Advance.models.Enums.UserRole;
import com.example.Software_Advance.models.Enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findById(long id);

    List<User> findByRole(UserRole role);

    List<User> findByType(UserType type);

    List <User> findByName(String name);

    boolean existsByEmail(String email) ;

}