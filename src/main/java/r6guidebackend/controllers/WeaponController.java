package r6guidebackend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import r6guidebackend.models.requests.CreateNewWeaponRequest;
import r6guidebackend.models.requests.UpdateSingleWeaponRequest;
import r6guidebackend.services.interfaces.IWeaponService;

@RestController
@CrossOrigin
@RequestMapping("/weapons")
public class WeaponController {
    private final IWeaponService weaponService;

    public WeaponController(IWeaponService weaponService) {
        this.weaponService = weaponService;
    }

    @GetMapping("/get-all-names")
    public ResponseEntity getAllWeaponNames() {
        try {
            var response = weaponService.getAllWeaponNames().get();

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity getSingleWeapon(@PathVariable String name) {
        try {
            var response = weaponService.getWeapon(name).get();

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity deleteSingleWeapon(@PathVariable String name) {
        try {
            weaponService.deleteWeapon(name);

            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity updateSingleWeapon(@PathVariable String name, @RequestBody UpdateSingleWeaponRequest model) {
        try {
            weaponService.updateSingleWeapon(name, model);

            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/{name}")
    public ResponseEntity createNewWeapon(@PathVariable String name, @RequestBody CreateNewWeaponRequest model) {
        try {
            weaponService.createNewWeapon(name, model);

            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
