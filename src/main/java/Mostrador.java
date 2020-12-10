import java.util.function.Consumer;

public class Mostrador implements Consumer<Usuario> {
    public void accept(Usuario usuario) {
        System.out.println(usuario.getNome());
    }

}
