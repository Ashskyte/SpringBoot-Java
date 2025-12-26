package org.cna.user.service.userservice.repositories;

import org.cna.user.service.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
