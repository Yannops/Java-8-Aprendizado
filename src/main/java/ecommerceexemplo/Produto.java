package ecommerceexemplo;

import java.math.BigDecimal;
import java.nio.file.Path;

public class Produto {
    private String name;
    private Path file;
    private BigDecimal price;

    public Produto(String name, Path file, BigDecimal price) {
        this.name = name;
        this.file = file;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Path getFile() {
        return file;
    }

    public String toString() {
        return this.name;
    }
}
