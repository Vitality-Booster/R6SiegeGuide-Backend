package r6guidebackend.services.interfaces;

import org.springframework.scheduling.annotation.Async;
import r6guidebackend.exceptions.AlreadyExistException;
import r6guidebackend.models.Gadget;
import r6guidebackend.models.requests.CreateNewGadgetRequest;
import r6guidebackend.models.requests.UpdateSingleGadgetRequest;
import r6guidebackend.models.responses.GetListOfGadgetPreviewsResponse;

import java.util.concurrent.CompletableFuture;

public interface IGadgetService {
    @Async
    CompletableFuture<Gadget> getGadget(String name) throws Exception;

    @Async
    CompletableFuture<Void> deleteGadget(String name) throws Exception;

    @Async
    CompletableFuture<GetListOfGadgetPreviewsResponse> getAllGadgets() throws Exception;

    @Async
    CompletableFuture<Void> updateSingleGadget(String name, UpdateSingleGadgetRequest model) throws Exception;

    @Async
    CompletableFuture<Void> createNewGadget(String name, CreateNewGadgetRequest model) throws AlreadyExistException;
}
