package hu.tbs.ft.planning.repository;

import hu.tbs.ft.planning.model.Repetition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepetitionRepository extends JpaRepository<Repetition, UUID> {
}
