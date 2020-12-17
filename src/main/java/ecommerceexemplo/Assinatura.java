package ecommerceexemplo;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class Assinatura {
    private BigDecimal mesGratis;
    private LocalDateTime inicio;
    private Optional<LocalDateTime> fim;
    private Customer cliente;

    public Assinatura(BigDecimal mesGratis, LocalDateTime inicio, Customer cliente) {
        this.mesGratis = mesGratis;
        this.inicio = inicio;
        this.fim = Optional.empty();
        this.cliente = cliente;
    }

    public Assinatura(BigDecimal mesGratis, LocalDateTime inicio, LocalDateTime fim, Customer cliente) {
        this.mesGratis = mesGratis;
        this.inicio = inicio;
        this.fim = Optional.of(fim);
        this.cliente = cliente;
    }

    public Customer getCliente() {
        return cliente;
    }

    public BigDecimal getMesGratis() {
        return mesGratis;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public Optional<LocalDateTime> getFim() {
        return fim;
    }

    public BigDecimal getTotalPaid() {
        return getMesGratis().multiply(new BigDecimal(ChronoUnit.MONTHS.between(getInicio(),
                getFim().orElse(LocalDateTime.now()))));
    }
}
