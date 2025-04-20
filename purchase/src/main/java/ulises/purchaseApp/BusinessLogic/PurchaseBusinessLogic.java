package ulises.purchaseApp.BusinessLogic;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import ulises.purchaseApp.Entities.Enums.PurchaseStatusEnum;
import ulises.purchaseApp.Entities.Model.Purchase;
import ulises.purchaseApp.Services.PurchaseService;

@Component
public class PurchaseBusinessLogic {

    private final PurchaseService purchaseService;

    public PurchaseBusinessLogic(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    public Purchase createPurchase(Purchase purchase) {
        validatePurchase(purchase);
        return purchaseService.createPurchase(purchase);
    }

    public Purchase updatePurchase(UUID id, Purchase updatedPurchase) {
        Optional<Purchase> existingOpt = purchaseService.getByIdPurchase(id);

        if (existingOpt == null) {
            throw new IllegalArgumentException("Purchase not found with the Id: " + id);
        }

        Purchase existing = existingOpt.get();

        if (existing.getStatus() == PurchaseStatusEnum.FAILED ||
            existing.getStatus() == PurchaseStatusEnum.CANCELLED) {
            throw new IllegalStateException("Isn't possible update an failed or cancelled operation");
        }

        validatePurchase(updatedPurchase);

        existing.setProductIds(updatedPurchase.getProductIds());
        existing.setTotalAmount(updatedPurchase.getTotalAmount());
        existing.setPaymentMethod(updatedPurchase.getPaymentType());
        existing.setPurchaseDate(updatedPurchase.getPurchaseDate());
        existing.setStatus(updatedPurchase.getStatus());

        return purchaseService.updatePurchase(id,existing);
    }

    private void validatePurchase(Purchase purchase) {
      if (purchase.getProductIds() == null || purchase.getProductIds().isEmpty()) {
          throw new IllegalArgumentException("The purchase must contain at least one product.");
      }
  
      if (purchase.getTotalAmount() == null || purchase.getTotalAmount().compareTo(BigDecimal.ZERO) <= 0) {
          throw new IllegalArgumentException("The total amount must be greater than 0.");
      }
  
      if (purchase.getPaymentType() == null) {
          throw new IllegalArgumentException("A payment method must be specified.");
      }
  
      if (purchase.getPurchaseDate() == null) {
          throw new IllegalArgumentException("The purchase date must be specified.");
      }
  }
  
}
