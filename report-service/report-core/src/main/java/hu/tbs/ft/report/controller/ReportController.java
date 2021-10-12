package hu.tbs.ft.report.controller;

import hu.tbs.ft.report.ReportDTO;
import hu.tbs.ft.report.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/report")
@AllArgsConstructor
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public ResponseEntity<List<ReportDTO>> getAllReports() { //TODO + filterek
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

    @GetMapping("/generate/{id}")
    public ResponseEntity getTransaction(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

    @PostMapping
    public ResponseEntity<ReportDTO> createReport(@RequestBody ReportDTO reportDTO) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteReport(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }
}
