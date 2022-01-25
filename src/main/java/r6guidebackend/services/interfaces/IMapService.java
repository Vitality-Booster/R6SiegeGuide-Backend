package r6guidebackend.services.interfaces;

import r6guidebackend.models.Map;
import r6guidebackend.models.requests.CreateNewMapRequest;
import r6guidebackend.models.responses.GetListOfNamesResponse;

import java.util.concurrent.CompletableFuture;

public interface IMapService {
    CompletableFuture<Void> createNewMap(String name, CreateNewMapRequest model) throws Exception;

    CompletableFuture<GetListOfNamesResponse> getAllMapNames() throws Exception;

    CompletableFuture<Map> getSingleMap(String name) throws Exception;
}
