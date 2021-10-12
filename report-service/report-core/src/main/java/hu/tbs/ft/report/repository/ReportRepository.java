package hu.tbs.ft.report.repository;

import hu.tbs.ft.report.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReportRepository extends JpaRepository<Report, UUID> {
}
