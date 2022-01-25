package r6guidebackend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r6guidebackend.models.requests.CreateNewMapRequest;
import r6guidebackend.services.interfaces.IMapService;

@CrossOrigin
@RequestMapping("/maps")
@RestController
public class MapController {
    private final IMapService service;

    public MapController(IMapService service) {
        this.service = service;
    }

    @PostMapping("/{name}")
    public ResponseEntity createNewMap(@PathVariable String name, @RequestBody CreateNewMapRequest model) {
        try {
            service.createNewMap(name, model);

            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity getSingleMap(@PathVariable String name) {
        try {
            var res = service.getSingleMap(name).get();

            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/get-all-names")
    public ResponseEntity getAllMapNames() {
        try {
            var res = service.getAllMapNames().get();

            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
