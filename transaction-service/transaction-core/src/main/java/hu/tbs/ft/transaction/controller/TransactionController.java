package hu.tbs.ft.transaction.controller;

import hu.tbs.ft.transaction.TransactionDTO;
import hu.tbs.ft.transaction.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {

    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactionsByPocketId(@RequestParam UUID pocketId) { //TODO + filterek
        return ResponseEntity.ok(transactionService.findAllByPocketId(pocketId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransaction(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionDTO> modifyTransaction(@PathVariable UUID id, @RequestBody TransactionDTO transactionDTO) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTransaction(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

}
