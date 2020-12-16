package ecommerceexemplo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Payment {
    private List<Produto> produtos;
    private LocalDateTime data;
    private Customer cliente;

    public Payment(List<Produto> produtos, LocalDateTime data, Customer cliente) {
        this.produtos = Collections.unmodifiableList(produtos);
        this.data = data;
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return this.produtos;
    }

    public LocalDateTime getData() {
        return this.data;
    }

    public Customer getCliente() {
        return this.cliente;
    }

    public String toString() {
        return "Payment{" +
                "produtos: " + produtos +
                 "Data: " + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                ", cliente: " + cliente +
                '}';
    }
}
