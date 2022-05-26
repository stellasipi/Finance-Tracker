package hu.tbs.ft.pocket.repository;

import hu.tbs.ft.pocket.model.PocketUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PocketUserRepository extends JpaRepository<PocketUser, UUID> {
}
