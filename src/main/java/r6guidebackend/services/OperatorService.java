package r6guidebackend.services;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import r6guidebackend.exceptions.AlreadyExistException;
import r6guidebackend.models.*;
import r6guidebackend.models.requests.CreateNewOperatorRequest;
import r6guidebackend.models.requests.GetOperatorsFromOneSideRequest;
import r6guidebackend.models.requests.GetOperatorsFromSpecialUnitRequest;
import r6guidebackend.models.requests.UpdateSingleOperatorRequest;
import r6guidebackend.models.responses.GetListOfNamesResponse;
import r6guidebackend.repositories.IGadgetRepository;
import r6guidebackend.repositories.IOperatorRepository;
import r6guidebackend.repositories.IWeaponRepository;
import r6guidebackend.services.interfaces.IOperatorService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class OperatorService implements IOperatorService {

    @Autowired
    private IOperatorRepository operatorRepository;

    @Autowired
    private IGadgetRepository gadgetRepository;

    @Autowired
    private IWeaponRepository weaponRepository;

    @Override
    public CompletableFuture<Operator> getSingleOperator(String name) throws Exception {
        Operator operator = operatorRepository.findOperatorByName(name);

        if (operator == null) {
            throw new NotFoundException("The operator with this name does not exist");
        }
        return CompletableFuture.completedFuture(operator);
    }

    @Override
    public CompletableFuture<GetListOfNamesResponse> getAllNames()  throws Exception{
        GetListOfNamesResponse resp = new GetListOfNamesResponse();
        List<Operator> operators = operatorRepository.findAll();

        if (operators == null || operators.isEmpty()) {
            throw new NotFoundException("There were no any operators found");
        }

        operators.forEach(operator -> resp.getNames().add(operator.getName()));
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
        setGadgetsAndWeapons(operator, model.getGadget1Name(), model.getGadget2Name(), model.getPrimaryWeapon1Name(), model.getPrimaryWeapon2Name(), model.getSecondaryWeapon1Name(), model.getSecondaryWeapon2Name());
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

    @Override
    public CompletableFuture<GetListOfNamesResponse> getAllOperatorsFromSpecialUnit(GetOperatorsFromSpecialUnitRequest model)  throws Exception{
        List<Operator> operators = operatorRepository.findAllBySpecialUnit(model.getSpecialUnit());

        if (operators == null || operators.isEmpty()) {
            throw new NotFoundException("There are no any operators in Special Unit: " + model.getSpecialUnit());
        }

        ArrayList<String> operatorNames = new ArrayList<>();

        operators.forEach(o -> operatorNames.add(o.getName()));

        GetListOfNamesResponse response = new GetListOfNamesResponse();
        response.setNames(operatorNames);

        return CompletableFuture.completedFuture(response);
    }

    @Override
    public CompletableFuture<GetListOfNamesResponse> getAllOperatorsFromOneSide(GetOperatorsFromOneSideRequest model) throws Exception{
        List<Operator> operators = operatorRepository.findAllBySide(model.getSide());

        if (operators == null || operators.isEmpty()) {
            throw new NotFoundException("There are no any operators in Special Unit: " + model.getSide());
        }

        ArrayList<String> operatorNames = new ArrayList<>();

        operators.forEach(o -> operatorNames.add(o.getName()));

        GetListOfNamesResponse response = new GetListOfNamesResponse();
        response.setNames(operatorNames);

        return CompletableFuture.completedFuture(response);
    }

    @Override
    public CompletableFuture<Void> createNewOperator(String name, CreateNewOperatorRequest model) throws Exception {
        Operator operator = new Operator();

        System.out.println("Got there");
        if (operatorRepository.findOperatorByName(name) != null) {
            throw new AlreadyExistException("An operator with name " + name + "already exists");
        }
        System.out.println("passed if statement");
        operator.setName(name);
        operator.setSide(model.getSide());
        operator.setSpecialUnit(model.getSpecialUnit());
        operator.setHealthPoints(model.getHealthPoints());
        operator.setSpeedPoints(model.getSpeedPoints());
        operator.setDifficultyPoints(model.getDifficultyPoints());
        operator.setNationality(model.getNationality());
        operator.setUniqueAbility(model.getUniqueAbility());
        operator.setRealFullName(model.getRealFullName());
        System.out.println("got to birthdate");
        operator.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse(model.getDateOfBirth()));
        System.out.println("passed the birthdate");
        operator.setCountryOfBirth(model.getCountryOfBirth());
        operator.setCityOfBirth(model.getCityOfBirth());
        operator.setBiography(model.getBiography());


        try {
            setGadgetsAndWeapons(operator, model.getGadget1Name(), model.getGadget2Name(),
                    model.getPrimaryWeapon1Name(), model.getPrimaryWeapon2Name(),
                    model.getSecondaryWeapon1Name(), model.getSecondaryWeapon2Name());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println(operator);
        operatorRepository.save(operator);

        return CompletableFuture.runAsync(() -> {});
    }

    private void setGadgetsAndWeapons(Operator operator, String gadget1Name, String gadget2Name,
                                      String primaryWeapon1Name, String primaryWeapon2Name,
                                      String secondaryWeapon1Name, String secondaryWeapon2Name) throws Exception{

        if (gadget1Name != null) {
            Gadget gadget = gadgetRepository.findGadgetByName(gadget1Name);
            if (gadget == null) {
                throw new NotFoundException("Gadget with name " + gadget1Name + " does not exist");
            }
            operator.setGadget1(gadget);
        }
        if (gadget2Name != null) {
            Gadget gadget = gadgetRepository.findGadgetByName(gadget2Name);
            if (gadget == null) {
                throw new NotFoundException("Gadget with name " + gadget2Name + " does not exist");
            }
            operator.setGadget2(gadget);
        }
        if (primaryWeapon1Name != null) {
            Weapon weapon = weaponRepository.findWeaponByName(primaryWeapon1Name);
            if (weapon == null) {
                throw new NotFoundException("Gadget with name " + primaryWeapon1Name + " does not exist");
            }
            operator.setPrimaryWeapon1(weapon);
        }
        if (primaryWeapon2Name != null) {
            Weapon weapon = weaponRepository.findWeaponByName(primaryWeapon2Name);
            if (weapon == null) {
                throw new NotFoundException("Gadget with name " + primaryWeapon2Name + " does not exist");
            }
            operator.setPrimaryWeapon2(weapon);
        }
        if (secondaryWeapon1Name != null) {
            Weapon weapon = weaponRepository.findWeaponByName(secondaryWeapon1Name);
            if (weapon == null) {
                throw new NotFoundException("Gadget with name " + secondaryWeapon1Name + " does not exist");
            }
            operator.setSecondaryWeapon1(weapon);
        }
        if (secondaryWeapon2Name != null) {
            Weapon weapon = weaponRepository.findWeaponByName(secondaryWeapon2Name);
            if (weapon == null) {
                throw new NotFoundException("Gadget with name " + secondaryWeapon2Name + " does not exist");
            }
            operator.setSecondaryWeapon2(weapon);
        }
        System.out.println("gets to the end");
    }
}
