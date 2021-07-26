package rga.test.task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rga.test.task.entities.Item;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Long> {
}
