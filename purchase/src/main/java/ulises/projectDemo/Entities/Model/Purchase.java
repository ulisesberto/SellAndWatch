
package ulises.projectDemo.Entities.Model;

/**
 *
 * @author ubertolotti
 */

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import ulises.projectDemo.Entities.Enums.PaymentTypeEnum;
import ulises.projectDemo.Entities.Enums.PurchaseStatusEnum;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false)
    private LocalDate purchaseDate; // Fecha de la compra

    @Column(nullable = false)
    private BigDecimal totalAmount; // Total de la compra

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentTypeEnum paymentMethod; // Método de pago

    @Enumerated(EnumType.STRING)
    private PurchaseStatusEnum status; // Estado de la compra

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
}
