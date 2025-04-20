package ulises.projectDemo.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ulises.projectDemo.Entities.Model.Purchase;
import ulises.projectDemo.repository.PurchaseRepository;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    } 
    
    public List<Purchase> getByIdPurchase(UUID id) {
        return purchaseRepository.findAll();
    }

    public Purchase createPurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    public void bulkCreatePurchase(List<Purchase> purchases) {
        purchaseRepository.saveAll(purchases);
    }

    public Purchase updatePurchase(UUID id, Purchase updatedPurchase) {
        return purchaseRepository.findById(id).map(purchase -> {
            purchase.setCustomerId(updatedPurchase.getCustomerId());
            purchase.setPurchaseDate(updatedPurchase.getPurchaseDate());
            purchase.setProducts(updatedPurchase.getProducts());
            purchase.setTotalAmount(updatedPurchase.getTotalAmount());
            purchase.setPaymentMethod(updatedPurchase.getPaymentType());
            purchase.setStatus(updatedPurchase.getStatus());
            return purchaseRepository.save(purchase);
        }).orElseThrow(() -> new RuntimeException("Purchase not found"));
    }
}
