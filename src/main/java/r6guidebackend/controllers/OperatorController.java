package r6guidebackend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import r6guidebackend.models.requests.CreateNewOperatorRequest;
import r6guidebackend.models.requests.UpdateSingleOperatorRequest;
import r6guidebackend.models.responses.GetListOfNamesResponse;
import r6guidebackend.models.Operator;
import r6guidebackend.services.interfaces.IOperatorService;
import r6guidebackend.services.interfaces.IUserChecker;

@CrossOrigin
@RestController
@RequestMapping("/operators")
public class OperatorController {
    private final IOperatorService operatorService;
    private final IUserChecker userChecker;
    
    public OperatorController(IOperatorService operatorService, IUserChecker userChecker) {
        this.operatorService = operatorService;
        this.userChecker = userChecker;
    }
    
    @GetMapping("/{name}")
    public ResponseEntity getSingleOperator(@PathVariable String name) {
        try {
            Operator operator = operatorService.getSingleOperator(name).get();

            return ResponseEntity.status(HttpStatus.OK).body(operator);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/get-all-names")
    public ResponseEntity getAllOperatorNames() {
        try {
            userChecker.checkIfAdminServerSide();
            GetListOfNamesResponse response = operatorService.getAllNames().get();

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity deleteSingleOperator(@PathVariable String name) {
        try {
            operatorService.deleteSingleOperator(name);

            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity updateSingleOperatorInformation(@PathVariable String name,
                                                          @RequestBody UpdateSingleOperatorRequest model) {
        try {
            operatorService.updateSingleOperator(name, model);

            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/{name}")
    public ResponseEntity createNewOperator(@PathVariable String name, @RequestBody CreateNewOperatorRequest model) {
        try {
            operatorService.createNewOperator(name, model);

            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
