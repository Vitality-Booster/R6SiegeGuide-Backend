package r6guidebackend.services;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import r6guidebackend.models.Gadget;
import r6guidebackend.models.Weapon;
import r6guidebackend.models.requests.CreateNewGadgetRequest;
import r6guidebackend.models.requests.UpdateSingleGadgetRequest;
import r6guidebackend.models.responses.GetMapOfGadgetsResponse;
import r6guidebackend.repositories.IGadgetRepository;
import r6guidebackend.services.interfaces.IGadgetService;

import java.util.concurrent.CompletableFuture;

@Service
public class GadgetService implements IGadgetService {
    
    @Autowired
    private IGadgetRepository repository;
    
    @Override
    public CompletableFuture<Gadget> getGadget(String name) throws Exception {
        Gadget gadget = repository.findGadgetByName(name);

        if (gadget == null) {
            throw new NotFoundException("A gadget with name " + name + " does not exist");
        }

        return CompletableFuture.completedFuture(gadget);
    }

    @Override
    public CompletableFuture<Void> deleteGadget(String name) throws Exception {
        if (!repository.deleteGadgetByName(name)) {
            throw new NotFoundException("A gadget with name " + name + " does not exist");
        }
        
        return CompletableFuture.runAsync(() -> {});
    }

    @Override
    public CompletableFuture<GetMapOfGadgetsResponse> getAllGadgets() throws Exception {
        GetMapOfGadgetsResponse response = new GetMapOfGadgetsResponse();
        
        repository.findAll().forEach(gadget -> response.getNameAndTypes().put(gadget.getName(), gadget.getType()));
        
        return CompletableFuture.completedFuture(response);
    }

    @Override
    public CompletableFuture<Void> updateSingleGadget(String name, UpdateSingleGadgetRequest model) throws Exception {
        Gadget gadget = repository.findGadgetByName(name);

        if (gadget == null) {
            throw new NotFoundException("A gadget with name " + name + " does not exist");
        }

        if (model.getType() != null) {
            gadget.setType(model.getType());
        }
        if (model.getDescription() != null) {
            gadget.setDescription(model.getDescription());
        }

        repository.save(gadget);

        return CompletableFuture.runAsync(() -> {});
    }

    @Override
    public CompletableFuture<Void> createNewGadget(String name, CreateNewGadgetRequest model) throws Exception {
        Gadget gadget = new Gadget();
        if(repository.findGadgetByName(name) != null) {
            throw new IllegalArgumentException("The gadget with name " + name + " already exists");
        }
        gadget.setName(name);
        gadget.setType(model.getType());
        gadget.setDescription(model.getDescription());

        repository.save(gadget);

        return CompletableFuture.runAsync(() -> {});
    }
}
