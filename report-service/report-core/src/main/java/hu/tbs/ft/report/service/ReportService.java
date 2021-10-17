package hu.tbs.ft.report.service;

import hu.tbs.ft.report.repository.ReportRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ReportService {

    private ReportRepository reportRepository;

}
