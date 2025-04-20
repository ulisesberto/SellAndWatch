package ulises.purchaseApp.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ulises.purchaseApp.Entities.Model.Purchase;


@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {
}

  

