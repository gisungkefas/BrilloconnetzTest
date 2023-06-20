package kefas.Brilloconnetz.repository;

import kefas.Brilloconnetz.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);
}
