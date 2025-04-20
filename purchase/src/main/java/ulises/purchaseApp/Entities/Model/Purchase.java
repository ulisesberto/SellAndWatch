
package ulises.purchaseApp.Entities.Model;

/**
 *
 * @author ubertolotti
 */

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import ulises.purchaseApp.Entities.Enums.PaymentTypeEnum;
import ulises.purchaseApp.Entities.Enums.PurchaseStatusEnum;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

     @ElementCollection
    @CollectionTable(
        name = "purchase_products", 
        joinColumns = @JoinColumn(name = "purchase_id")
    )
    @Column(name = "product_id")  
    private Set<UUID> productIds;  
    
    @Column(nullable = false)
    private LocalDate purchaseDate; 

    @Column(nullable = false)
    private BigDecimal totalAmount; 

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentTypeEnum paymentMethod; 

    @Enumerated(EnumType.STRING)
    private PurchaseStatusEnum status; 

    // Getters y Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public PaymentTypeEnum getPaymentType() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentTypeEnum paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PurchaseStatusEnum getStatus() {
        return status;
    }

    public void setStatus(PurchaseStatusEnum status) {
        this.status = status;
    }

    public Set<UUID> getProductIds() {
        return productIds;
    }

    public void setProductIds(Set<UUID> productIds) {
        this.productIds = productIds;
    }
}
