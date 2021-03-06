package se.iths.springloppis.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.iths.springloppis.entity.ItemEntity;
import se.iths.springloppis.repository.ItemRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ItemService {

    // Field injection is not recommended
    // @Autowired      // = @Inject
    private final ItemRepository itemRepository;

    //@Autowired    ->  constructor inject is slightly better BUT best to provide own repository in the constructor
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemEntity createItem(ItemEntity itemEntity) {
        return itemRepository.save(itemEntity);
    }

    public void deleteItem(Long id) {
        ItemEntity foundItem = itemRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item with ID " + id + " not found"));
        itemRepository.deleteById(foundItem.getId());
    }

    public Optional<ItemEntity> findItemById(Long id) {
        return itemRepository.findById(id);
    }

    public Iterable<ItemEntity> findAllItems() {
        return itemRepository.findAll();
    }

}




