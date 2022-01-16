package r6guidebackend.services.interfaces;

import org.springframework.scheduling.annotation.Async;
import r6guidebackend.models.requests.UpdateSingleOperatorRequest;
import r6guidebackend.models.responses.GetAllNamesResponse;
import r6guidebackend.models.Operator;
import r6guidebackend.models.requests.RegisterRequest;
import r6guidebackend.models.requests.VerifyTokenRequest;

import java.util.concurrent.CompletableFuture;

public interface IOperatorService {
    @Async
    CompletableFuture<Operator> getSingleOperator(String model) throws Exception;

    @Async
    CompletableFuture<GetAllNamesResponse> getAllNames();

    @Async
    CompletableFuture<Void> deleteSingleOperator(String name) throws Exception;

    @Async
    CompletableFuture<Void> updateSingleOperator(String name, UpdateSingleOperatorRequest model) throws  Exception;
}
