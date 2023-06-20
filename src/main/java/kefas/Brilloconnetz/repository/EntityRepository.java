package kefas.Brilloconnetz.repository;

import kefas.Brilloconnetz.Entities.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityRepository extends JpaRepository<Entity, Long> {

    Boolean existsByEmail(String email);
}
