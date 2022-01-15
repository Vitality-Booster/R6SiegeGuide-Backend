package r6guidebackend.services;

import javassist.NotFoundException;
import r6guidebackend.models.GetAllNamesResponse;
import r6guidebackend.models.GetSingleOperatorRequest;
import r6guidebackend.models.Operator;
import r6guidebackend.models.UpdateSingleOperatorRequest;
import r6guidebackend.repositories.IOperatorRepository;
import r6guidebackend.services.interfaces.IOperatorService;

import java.text.SimpleDateFormat;
import java.util.concurrent.CompletableFuture;

public class OperatorService implements IOperatorService {
    private final IOperatorRepository operatorRepository;

    public OperatorService(IOperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    @Override
    public CompletableFuture<Operator> getSingleOperator(GetSingleOperatorRequest model) throws Exception {
        Operator operator = operatorRepository.findOperatorByName(model.getName());

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
    public CompletableFuture<Void> deleteSingleOperator(GetSingleOperatorRequest model) throws Exception {
        if (!operatorRepository.deleteOperatorByName(model.getName())) {
            throw new NotFoundException("The operator with this name does not exist");
        }
        return CompletableFuture.runAsync(()->{});
    }

    @Override
    public CompletableFuture<Void> updateSingleOperator(UpdateSingleOperatorRequest model) throws Exception {
        Operator operator = operatorRepository.findOperatorByName(model.getName());
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

            operator.setCityOfBirth(model.getCityOfBirth());
        }
        if (model.getBiography() != null) {
            operator.setBiography(model.getBiography());
        }
        if (model.getCityOfBirth() != null) {
            operator.setCityOfBirth(model.getCityOfBirth());
        }

        operatorRepository.save(operator);

        return null;
    }
}
