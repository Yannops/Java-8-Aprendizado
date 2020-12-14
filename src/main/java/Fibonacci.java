import java.util.function.IntSupplier;
import java.util.stream.IntStream;

class Fibonacci implements IntSupplier {
    private int anterior = 0;
    private int proximo = 1;


    public int getAsInt() {
        proximo += anterior;
        anterior -= proximo;
        return anterior;
    }
}
