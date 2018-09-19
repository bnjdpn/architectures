package fr.xebia.architectures.layered.presentation.controller;

import fr.xebia.architectures.layered.business.service.OperationService;
import fr.xebia.architectures.layered.persistence.model.Operation;
import java.time.Instant;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operation")
public class OperationController {

    private OperationService operationService;

    @Inject
    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @PostMapping
    public void saveOperation(@Valid Operation operation) {
        operationService.saveOperation(operation);
    }

    @GetMapping
    public List<Operation> findOperations(@RequestParam("accountId") String accountId,
                                          @RequestParam("startOperationDate") Instant startOperationDate,
                                          @RequestParam("endOperationDate") Instant endOperationDate) {
        return operationService.findOperations(accountId, startOperationDate, endOperationDate);
    }

}
