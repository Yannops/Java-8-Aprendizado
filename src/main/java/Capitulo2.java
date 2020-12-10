import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Capitulo2 {
    public static void main(String[] args) {
        Usuario usuario = new Usuario("Yann", 10);
        Usuario usuario2 = new Usuario("João", 8);
        Usuario usuario3 = new Usuario("Guilherme", 10);

        List<Usuario> usuarios = Arrays.asList(usuario, usuario2, usuario3);

        for (Usuario users: usuarios) {
            System.out.println(users.getNome());
        }

        usuarios.forEach(new Consumer<Usuario>() {
            public void accept(Usuario usuario) {
                System.out.println(usuario.getNome());
            }
        });

        Consumer<Usuario> mostrador = u -> System.out.println(u.getNome());

        usuarios.forEach(u -> System.out.println(u.getNome()));

        usuarios.forEach(u -> u.tornarModerador());

        Consumer<Usuario> mostraMensagem = u -> System.out.println("Seu Nome é: ");

        usuarios.forEach(mostraMensagem.andThen(mostrador));

        Predicate<Usuario> predicado = new Predicate<Usuario>() {
            public boolean test(Usuario u) {
                return u.getPontos() > 6;
            }
        };

        List<Usuario> usuarios = new ArrayList<>();

        usuarios.add(usuario);
        usuarios.add(usuario2);
        usuarios.add(usuario3);

        System.out.println(usuarios.removeIf(u -> u.getPontos() > 6));

    }
}
