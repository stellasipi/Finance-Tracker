package hu.tbs.ft.user.repository;

import hu.tbs.ft.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Override
    Optional<User> findById(UUID uuid);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndIdNot(String username, UUID id);

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndIdNot(String email, UUID id);

}
