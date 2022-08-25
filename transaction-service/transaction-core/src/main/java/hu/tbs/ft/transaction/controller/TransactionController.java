package hu.tbs.ft.transaction.controller;

import hu.tbs.ft.transaction.TransactionDTO;
import hu.tbs.ft.transaction.service.TransactionService;
import hu.tbs.ft.transaction.util.TransactionException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
@Slf4j
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
    public ResponseEntity<TransactionDTO> createTransaction(@Valid @RequestBody TransactionDTO transactionDTO, UriComponentsBuilder uriComponentsBuilder) {
        try {
            TransactionDTO newTransactionDTO = transactionService.createTransaction(transactionDTO);
            UriComponents uriComponents = uriComponentsBuilder.path("/user/{id}").buildAndExpand(newTransactionDTO.getId());
            return ResponseEntity.created(uriComponents.toUri()).body(newTransactionDTO);
        } catch (TransactionException ex) {
            log.debug(ex.getMessage());
            return ResponseEntity.badRequest().build();
        }
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
