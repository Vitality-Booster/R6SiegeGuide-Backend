package r6guidebackend.services;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import r6guidebackend.models.*;
import r6guidebackend.models.requests.RegisterRequest;
import r6guidebackend.models.requests.UpdateSingleOperatorRequest;
import r6guidebackend.models.requests.VerifyTokenRequest;
import r6guidebackend.models.responses.GetAllNamesResponse;
import r6guidebackend.repositories.IGadgetRepository;
import r6guidebackend.repositories.IOperatorRepository;
import r6guidebackend.repositories.IWeaponRepository;
import r6guidebackend.services.interfaces.IOperatorService;

import java.text.SimpleDateFormat;
import java.util.concurrent.CompletableFuture;

@Service
public class OperatorService implements IOperatorService {
    private final IOperatorRepository operatorRepository;
    private final IGadgetRepository gadgetRepository;
    private final IWeaponRepository weaponRepository;

    public OperatorService(IOperatorRepository operatorRepository,
                           IGadgetRepository gadgetRepository,
                           IWeaponRepository weaponRepository) {
        this.operatorRepository = operatorRepository;
        this.gadgetRepository = gadgetRepository;
        this.weaponRepository = weaponRepository;
    }

    @Override
    public CompletableFuture<Operator> getSingleOperator(String name) throws Exception {
        Operator operator = operatorRepository.findOperatorByName(name);

        if (operator == null) {
            throw new NotFoundException("The operator with this name does not exist");
        }
        return CompletableFuture.completedFuture(operator);
    }

    @Override
    public CompletableFuture<GetAllNamesResponse> getAllNames() {
        GetAllNamesResponse resp = new GetAllNamesResponse();

        operatorRepository.findAll().stream().forEach(operator -> resp.getNames().add(operator.getName()));
        return CompletableFuture.completedFuture(resp);
    }

    @Override
    public CompletableFuture<Void> deleteSingleOperator(String name) throws Exception {
        if (!operatorRepository.deleteOperatorByName(name)) {
            throw new NotFoundException("The operator with this name does not exist");
        }
        return CompletableFuture.runAsync(()->{});
    }

    @Override
    public CompletableFuture<Void> updateSingleOperator(String name, UpdateSingleOperatorRequest model) throws Exception {
        Operator operator = operatorRepository.findOperatorByName(name);
        if (model.getBiography() != null) {
            operator.setBiography(model.getBiography());
        }
        if (model.getCityOfBirth() != null) {
            operator.setCityOfBirth(model.getCityOfBirth());
        }
        if (model.getCountryOfBirth() != null) {
            operator.setCountryOfBirth(model.getCountryOfBirth());
        }
        if (model.getDateOfBirth() != null) {
            operator.setDateOfBirth(SimpleDateFormat.getInstance().parse(model.getDateOfBirth()));
        }
        if (model.getDifficultyPoints() != 0) {
            operator.setDifficultyPoints(model.getDifficultyPoints());
        }
        if (model.getGadget1Name() != null) {
            Gadget gadget = gadgetRepository.findGadgetByName(model.getGadget1Name());
            assert gadget != null : "Gadget with name" + model.getGadget1Name() + "exist";
            operator.setGadget1(gadget);
        }
        if (model.getGadget2Name() != null) {
            Gadget gadget = gadgetRepository.findGadgetByName(model.getGadget2Name());
            assert gadget != null : "Gadget with name" + model.getGadget2Name() + "exist";
            operator.setGadget2(gadget);
        }
        if (model.getPrimaryWeapon1Name() != null) {
            Weapon weapon = weaponRepository.findWeaponByName(model.getPrimaryWeapon1Name());
            assert weapon != null : "Weapon with name" + model.getPrimaryWeapon1Name() + "exist";
            operator.setPrimaryWeapon1(weapon);
        }
        if (model.getPrimaryWeapon2Name() != null) {
            Weapon weapon = weaponRepository.findWeaponByName(model.getPrimaryWeapon2Name());
            assert weapon != null : "Weapon with name" + model.getPrimaryWeapon2Name() + "exist";
            operator.setPrimaryWeapon2(weapon);
        }
        if (model.getSecondaryWeapon1Name() != null) {
            Weapon weapon = weaponRepository.findWeaponByName(model.getSecondaryWeapon1Name());
            assert weapon != null : "Weapon with name" + model.getSecondaryWeapon1Name() + "exist";
            operator.setSecondaryWeapon1(weapon);
        }
        if (model.getSecondaryWeapon2Name() != null) {
            Weapon weapon = weaponRepository.findWeaponByName(model.getSecondaryWeapon2Name());
            assert weapon != null : "Weapon with name" + model.getSecondaryWeapon2Name() + "exist";
            operator.setSecondaryWeapon2(weapon);
        }
        if (model.getHealthPoints() != 0) {
            operator.setHealthPoints(model.getHealthPoints());
        }
        if (model.getNationality() != null) {
            operator.setNationality(model.getNationality());
        }
        if (model.getRealFullName() != null) {
            operator.setRealFullName(model.getRealFullName());
        }
        if (model.getSide() != null) {
            operator.setSide(model.getSide());
        }
        if (model.getSpeedPoints() != 0) {
            operator.setSpeedPoints(model.getSpeedPoints());
        }
        if (model.getSpecialUnit() != null) {
            operator.setSpecialUnit(model.getSpecialUnit());
        }
        if (model.getUniqueAbility() != null) {
            operator.setUniqueAbility(model.getUniqueAbility());
        }

        operatorRepository.save(operator);

        return CompletableFuture.runAsync(() -> {});
    }
}
