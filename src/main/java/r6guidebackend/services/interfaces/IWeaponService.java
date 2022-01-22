package r6guidebackend.services.interfaces;

import org.springframework.scheduling.annotation.Async;
import r6guidebackend.models.Weapon;
import r6guidebackend.models.requests.CreateNewWeaponRequest;
import r6guidebackend.models.requests.UpdateSingleWeaponRequest;
import r6guidebackend.models.responses.GetListOfNamesResponse;
import r6guidebackend.models.responses.GetMapOfWeaponsResponse;

import java.util.concurrent.CompletableFuture;

public interface IWeaponService {
    @Async
    CompletableFuture<Weapon> getWeapon(String name) throws Exception;

    @Async
    CompletableFuture<Void> deleteWeapon(String name) throws Exception;

    @Async
    CompletableFuture<GetMapOfWeaponsResponse> getAllWeaponNames() throws Exception;

    @Async
    CompletableFuture<Void> updateSingleWeapon(String name, UpdateSingleWeaponRequest model) throws Exception;

    @Async
    CompletableFuture<Void> createNewWeapon(String name, CreateNewWeaponRequest model) throws Exception;
}
