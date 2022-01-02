package r6guidebackend.repositories;

import r6guidebackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    User findUserByEmail(String email);

    User findUserByUsername(String username);
}
