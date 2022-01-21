package r6guidebackend.services;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import r6guidebackend.models.Weapon;
import r6guidebackend.models.requests.UpdateSingleWeaponRequest;
import r6guidebackend.models.responses.GetListOfNamesResponse;
import r6guidebackend.repositories.IWeaponRepository;
import r6guidebackend.services.interfaces.IWeaponService;

import java.util.concurrent.CompletableFuture;

@Service
public class WeaponService implements IWeaponService {

    @Autowired
    private IWeaponRepository repository;

    @Override
    public CompletableFuture<Weapon> getWeapon(String name) throws Exception{
        Weapon weapon = repository.findWeaponByName(name);

        if (weapon == null) {
            throw new NotFoundException("A weapon with name " + name + " does not exist");
        }

        return CompletableFuture.completedFuture(weapon);
    }

    @Override
    public CompletableFuture<Void> deleteWeapon(String name) throws Exception{
        if (!repository.deleteWeaponByName(name)) {
            throw new NotFoundException("A weapon with name " +name + " does not exist");
        }

        return CompletableFuture.runAsync(() -> {});
    }

    @Override
    public CompletableFuture<GetListOfNamesResponse> getAllWeaponNames() throws Exception{
        GetListOfNamesResponse response = new GetListOfNamesResponse();

        repository.findAll().forEach(weapon -> response.getNames().add(weapon.getName()));

        return CompletableFuture.completedFuture(response);
    }

    @Override
    public CompletableFuture<Void> updateSingleWeapon(String name, UpdateSingleWeaponRequest model) throws Exception {
        Weapon weapon = repository.findWeaponByName(name);

        if (weapon == null) {
            throw new NotFoundException("A weapon with name " + name + " does not exist");
        }

        if (model.getType() != null) {
            weapon.setType(model.getType());
        }
        if (model.getDamage() != 0) {
            weapon.setDamage(model.getDamage());
        }
        if (model.getMagazine() != 0) {
            weapon.setMagazine(model.getMagazine());
        }

        repository.save(weapon);

        return CompletableFuture.runAsync(() -> {});
    }
}
