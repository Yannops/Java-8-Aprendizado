import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import static java.util.Comparator.*;

public class Capitulo2 {
    public static void main(String[] args) {
        Usuario usuario = new Usuario("Yann", 10);
        Usuario usuario2 = new Usuario("João", 8);
        Usuario usuario3 = new Usuario("Guilherme", 10);

        List<Usuario> usuarios = Arrays.asList(usuario, usuario2, usuario3);

        for (Usuario users : usuarios) {
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

        List<Usuario> usuarios2 = new ArrayList<>();

        usuarios2.add(usuario);
        usuarios2.add(usuario2);
        usuarios2.add(usuario3);

        System.out.println(usuarios2.removeIf(u -> u.getPontos() > 6));

        Collections.sort(usuarios2, (u1, u2) -> u1.getNome().compareTo(u2.getNome()));

        usuarios2.sort((u1, u2) -> u1.getNome().compareTo(u2.getNome()));

        usuarios2.sort(comparing(user -> user.getNome()));

        Function<Usuario, String> extraiNome = u -> u.getNome();
        Comparator<Usuario> comparator = comparing(extraiNome);
        usuarios2.sort(comparator);

        ToIntFunction<Usuario> extrairPontos = u -> u.getPontos();
        Comparator<Usuario> comparator1 = comparingInt(extrairPontos);
        usuarios2.sort(comparator1);

        usuarios2.sort(comparingInt(u -> u.getPontos()));

        usuarios2.forEach(Usuario::tornarModerador);

        usuarios2.sort(comparing(Usuario::getNome));
        usuarios2.sort(comparingInt(Usuario::getPontos));

        Comparator<Usuario> comparator2 = comparingInt(Usuario::getPontos)
                .thenComparing(Usuario::getNome);

        usuarios2.sort(nullsLast(comparing(Usuario::getNome)));
        usuarios2.sort(nullsLast(comparingInt(Usuario::getPontos)));
        usuarios2.sort(nullsFirst(comparing(Usuario::getNome)));
        usuarios2.sort(nullsFirst(comparingInt(Usuario::getPontos)));

        usuarios2.sort(comparing(Usuario::getNome).reversed());
        usuarios2.sort(comparingInt(Usuario::getPontos).reversed());

        Usuario yann = new Usuario("Yannzin", 9);
        Runnable bloco = yann::tornarModerador;
        bloco.run();

        Runnable bloco1 = yann::tornarModerador;
        Runnable bloco2 = () -> yann.tornarModerador();

        usuarios2.forEach(System.out::println);
        usuarios2.forEach(u -> System.out.println(u));



    }
}
