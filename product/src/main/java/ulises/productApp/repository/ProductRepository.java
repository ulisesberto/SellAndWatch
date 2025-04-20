package ulises.productApp.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ulises.productApp.Entities.Model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
}

  

