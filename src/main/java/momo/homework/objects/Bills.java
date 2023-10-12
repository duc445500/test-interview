package momo.homework.objects;

import java.io.Serializable;
import java.time.LocalDate;

public class Bills implements Serializable {

    private static final long serialVersionUID = -8578281733307012968L;
    private Long id;
    private String type;
    private String provider;
    private Double amount;
    private boolean isPaid;
    private LocalDate paymentDate;
    private LocalDate dueDate;

    public Bills(Long id, String type, String provider, Double amount, boolean isPaid, LocalDate paymentDate, LocalDate dueDate) {
        this.id = id;
        this.type = type;
        this.provider = provider;
        this.amount = amount;
        this.isPaid = isPaid;
        this.paymentDate = paymentDate;
        this.dueDate = dueDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        this.isPaid = paid;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Bills{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", provider='" + provider + '\'' +
                ", amount=" + amount +
                ", isPaid=" + isPaid +
                ", paymentDate=" + paymentDate +
                ", dueDate=" + dueDate +
                '}';
    }
}
