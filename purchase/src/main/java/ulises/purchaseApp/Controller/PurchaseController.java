package ulises.purchaseApp.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ulises.purchaseApp.BusinessLogic.PurchaseBusinessLogic;
import ulises.purchaseApp.Entities.Model.Purchase;
import ulises.purchaseApp.Services.PurchaseService;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private PurchaseBusinessLogic purchaseBusinessLogic;

    @GetMapping
    public List<Purchase> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    @PostMapping
    public ResponseEntity<Purchase> createPurchase(@RequestBody Purchase purchase) {
        try {
            Purchase created = purchaseBusinessLogic.createPurchase(purchase);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/bulk")
    public ResponseEntity<String> bulkCreatePurchases(@RequestBody List<Purchase> purchases) {
        try {
            purchaseService.bulkCreatePurchase(purchases);
            return ResponseEntity.ok("Created correctly");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while saving purchases");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Purchase> updatePurchase(@PathVariable UUID id, @RequestBody Purchase updatedPurchase) {
        try {
            Purchase updated = purchaseBusinessLogic.updatePurchase(id, updatedPurchase);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
