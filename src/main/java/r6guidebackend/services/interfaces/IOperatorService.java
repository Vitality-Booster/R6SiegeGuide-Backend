package r6guidebackend.services.interfaces;

import org.springframework.scheduling.annotation.Async;
import r6guidebackend.models.GetAllNamesResponse;
import r6guidebackend.models.GetSingleOperatorRequest;
import r6guidebackend.models.Operator;
import r6guidebackend.models.UpdateSingleOperatorRequest;

import java.util.concurrent.CompletableFuture;

public interface IOperatorService {
    @Async
    CompletableFuture<Operator> getSingleOperator(GetSingleOperatorRequest model) throws Exception;

    @Async
    CompletableFuture<GetAllNamesResponse> getAllNames();

    @Async
    CompletableFuture<Void> deleteSingleOperator(GetSingleOperatorRequest model) throws Exception;

    @Async
    CompletableFuture<Void> updateSingleOperator(UpdateSingleOperatorRequest model) throws  Exception;
}
