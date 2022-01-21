package r6guidebackend.services.interfaces;

import org.springframework.scheduling.annotation.Async;
import r6guidebackend.models.Weapon;
import r6guidebackend.models.requests.UpdateSingleWeaponRequest;
import r6guidebackend.models.responses.GetListOfNamesResponse;

import java.util.concurrent.CompletableFuture;

public interface IWeaponService {
    @Async
    CompletableFuture<Weapon> getWeapon(String name) throws Exception;

    @Async
    CompletableFuture<Void> deleteWeapon(String name) throws Exception;

    @Async
    CompletableFuture<GetListOfNamesResponse> getAllWeaponNames() throws Exception;

    @Async
    CompletableFuture<Void> updateSingleWeapon(String name, UpdateSingleWeaponRequest model) throws Exception;
}
