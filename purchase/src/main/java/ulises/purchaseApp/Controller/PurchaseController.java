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

import ulises.purchaseApp.Entities.Model.Purchase;
import ulises.purchaseApp.Services.PurchaseService;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    // GET: GetAll purchases
    @GetMapping
    public List<Purchase> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    // POST: create a new purchase
    @PostMapping
    public Purchase createPurchase(@RequestBody Purchase purchase) {
        return purchaseService.createPurchase(purchase);
    }

    // POST: create many purchases
    @PostMapping("/bulk")
    public ResponseEntity<String> bulkCreatePurchases(@RequestBody List<Purchase> purchases) {
        try {
            purchaseService.bulkCreatePurchase(purchases);
            return ResponseEntity.ok("Created correctly");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar las compras");
        }
    }


    // PUT: edit a extisting purchase
    @PutMapping("/{id}")
    public ResponseEntity<Purchase> updatePurchase(@PathVariable UUID id, @RequestBody Purchase updatedPurchase) {
        try {
            Purchase updated = purchaseService.updatePurchase(id, updatedPurchase);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}


