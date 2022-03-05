package se.iths.springloppis.repository;

import org.springframework.data.repository.CrudRepository;
import se.iths.springloppis.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
