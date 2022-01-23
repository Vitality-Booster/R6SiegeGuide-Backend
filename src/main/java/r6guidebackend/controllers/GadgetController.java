package r6guidebackend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r6guidebackend.models.requests.CreateNewGadgetRequest;
import r6guidebackend.models.requests.UpdateSingleGadgetRequest;
import r6guidebackend.services.interfaces.IGadgetService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/gadgets")
public class GadgetController {
    private final IGadgetService gadgetService;

    public GadgetController(IGadgetService gadgetService) {
        this.gadgetService = gadgetService;
    }

    @GetMapping("/get-all-names")
    public ResponseEntity getAllGadgetNamesAndTypes() {
        try {
            var response = gadgetService.getAllGadgets().get();

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity getSingleGadget(@PathVariable String name) {
        try {
            var response = gadgetService.getGadget(name).get();

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity deleteSingleGadget(@PathVariable String name) {
        try {
            gadgetService.deleteGadget(name);

            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity updateSingleGadget(@PathVariable String name, @RequestBody UpdateSingleGadgetRequest model) {
        try {
            gadgetService.updateSingleGadget(name, model);

            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/{name}")
    public ResponseEntity createNewGadget(@PathVariable String name, @RequestBody CreateNewGadgetRequest model) {
        try {
            gadgetService.createNewGadget(name, model);

            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
