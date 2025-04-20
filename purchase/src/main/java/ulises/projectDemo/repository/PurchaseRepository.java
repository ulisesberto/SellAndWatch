package ulises.projectDemo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ulises.projectDemo.Entities.Model.Purchase;


@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {
}

  

