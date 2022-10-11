package hu.tbs.ft.transaction.controller;

import hu.tbs.ft.transaction.ModifyTransactionDTO;
import hu.tbs.ft.transaction.TransactionDTO;
import hu.tbs.ft.transaction.service.TransactionService;
import hu.tbs.ft.transaction.util.TransactionException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
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

    @GetMapping("/all")
    public ResponseEntity<List<TransactionDTO>> getAllTransactionsByPocketId(@RequestParam UUID pocketId, JwtAuthenticationToken principal) {
        try {
            List<TransactionDTO> transactions = transactionService.findAllByPocketId(pocketId, principal.getName());
            return ResponseEntity.ok(transactions);
        }catch (TransactionException ex){
            log.debug(ex.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<TransactionDTO> getTransaction(@RequestParam UUID id, JwtAuthenticationToken principal) {
        try {
            TransactionDTO transaction = transactionService.getTransactionById(id, principal.getName());
            return ResponseEntity.ok(transaction);
        } catch (TransactionException ex) {
            log.debug(ex.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@Valid @RequestBody TransactionDTO transactionDTO, UriComponentsBuilder uriComponentsBuilder) {
        try {
            TransactionDTO newTransactionDTO = transactionService.createTransaction(transactionDTO);
            UriComponents uriComponents = uriComponentsBuilder.path("/transaction?id={id}").buildAndExpand(newTransactionDTO.getId());
            return ResponseEntity.created(uriComponents.toUri()).body(newTransactionDTO);
        } catch (TransactionException ex) {
            log.debug(ex.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<TransactionDTO> modifyTransaction(@RequestParam UUID transactionId, @RequestBody ModifyTransactionDTO dto, JwtAuthenticationToken principal) {
        try {
            TransactionDTO modifiedTransaction = transactionService.modifyTransaction(transactionId, dto, principal.getName());
            return ResponseEntity.ok(modifiedTransaction);
        } catch (TransactionException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteTransaction(@RequestParam UUID id, JwtAuthenticationToken principal) {
        try {
            transactionService.deleteTransaction(id, principal.getName());
            return ResponseEntity.ok().build();
        } catch (TransactionException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

}
