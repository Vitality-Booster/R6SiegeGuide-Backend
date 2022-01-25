package r6guidebackend.services;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import r6guidebackend.exceptions.AlreadyExistException;
import r6guidebackend.models.Map;
import r6guidebackend.models.requests.CreateNewMapRequest;
import r6guidebackend.models.responses.GetListOfNamesResponse;
import r6guidebackend.repositories.IMapRepository;
import r6guidebackend.services.interfaces.IMapService;

import java.util.concurrent.CompletableFuture;

@Service
public class MapService implements IMapService {

    private final IMapRepository repository;

    public MapService(IMapRepository repository) {
        this.repository = repository;
    }

    @Override
    public CompletableFuture<Void> createNewMap(String name, CreateNewMapRequest model) throws Exception {
        Map map = new Map();

        if (repository.findByName(name) != null) {
            throw new AlreadyExistException("The map with name " + name + " already exists");
        }

        map.setName(name);
        map.setInfo(model.getInfo());
        map.setLocationCity(model.getLocationCity());
        map.setLocationCountry(model.getLocationCountry());

        repository.save(map);

        return CompletableFuture.runAsync(() -> {});
    }

    @Override
    public CompletableFuture<GetListOfNamesResponse> getAllMapNames() throws Exception {
        GetListOfNamesResponse response = new GetListOfNamesResponse();

        repository.findAll().forEach(map -> response.getNames().add(map.getName()));

        return CompletableFuture.completedFuture(response);
    }

    @Override
    public CompletableFuture<Map> getSingleMap(String name) throws Exception {
        Map map = repository.findByName(name);

        if (map == null) {
            throw new NotFoundException("Map with name " + name + " does not exist");
        }

        return CompletableFuture.completedFuture(map);
    }
}
