package r6guidebackend.services.interfaces;

import org.springframework.scheduling.annotation.Async;
import r6guidebackend.models.requests.GetOperatorsFromOneSideRequest;
import r6guidebackend.models.requests.UpdateSingleOperatorRequest;
import r6guidebackend.models.responses.GetListOfNamesResponse;
import r6guidebackend.models.Operator;
import r6guidebackend.models.requests.GetOperatorsFromSpecialUnitRequest;

import java.util.concurrent.CompletableFuture;

public interface IOperatorService {
    @Async
    CompletableFuture<Operator> getSingleOperator(String model) throws Exception;

    @Async
    CompletableFuture<GetListOfNamesResponse> getAllNames() throws Exception;

    @Async
    CompletableFuture<Void> deleteSingleOperator(String name) throws Exception;

    @Async
    CompletableFuture<Void> updateSingleOperator(String name, UpdateSingleOperatorRequest model) throws  Exception;

    @Async
    CompletableFuture<GetListOfNamesResponse> getAllOperatorsFromSpecialUnit(GetOperatorsFromSpecialUnitRequest model) throws Exception;

    @Async
    CompletableFuture<GetListOfNamesResponse> getAllOperatorsFromOneSide(GetOperatorsFromOneSideRequest model) throws Exception;
}
