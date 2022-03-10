package se.iths.springloppis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.iths.springloppis.entity.ItemEntity;

@Repository
public interface ItemRepository extends CrudRepository<ItemEntity, Long> {   // <Entity, IdType>
    // Spring Boot implements this repository for us
}

// @Repository -> Spring Boot equivalent of @Service