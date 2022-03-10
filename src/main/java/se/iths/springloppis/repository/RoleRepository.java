package se.iths.springloppis.repository;

import org.springframework.data.repository.CrudRepository;
import se.iths.springloppis.entity.RoleEntity;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
    RoleEntity findByRole(String role);
}
