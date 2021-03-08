package pagamentos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Pagamento {

    private long id;
    private String nomeCliente;
    private BigDecimal valor;
    private LocalDateTime data;

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    Pagamento(long id, String nomeCliente, BigDecimal valor, LocalDateTime data) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.valor = valor;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "nomeCliente:'" + nomeCliente + '\'' +
                ", data:" + data +
                '}';
    }
}
