package r6guidebackend.services.interfaces;

import org.springframework.scheduling.annotation.Async;
import r6guidebackend.models.Weapon;
import r6guidebackend.models.requests.GetWeaponRequest;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IWeaponService {
    @Async
    CompletableFuture<Weapon> getWeapon(GetWeaponRequest model);

    @Async
    CompletableFuture<Void> deleteWeapon(GetWeaponRequest.DeleteWeaponRequest model);

    @Async
    CompletableFuture<List<Weapon>> getAllWeapons();

    // Decide if I should have a lot of DTOs or should I just pass DAO models as they are
    // As a possible solution for some models, I may use generics
//    @Async
//    CompletableFuture<>
}
