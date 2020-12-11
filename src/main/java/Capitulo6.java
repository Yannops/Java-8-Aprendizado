import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.ToIntBiFunction;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;

public class Capitulo6 {
    public static void main(String[] args) {
        Usuario usuario = new Usuario("Leo", 2);
        Usuario usuario2 = new Usuario("Gabriel", 8);
        Usuario usuario3 = new Usuario("Pedro", 5);

        List<Usuario> users = Arrays.asList(usuario, usuario2, usuario3);

        users.forEach(System.out::println);


        users.sort(comparingInt(Usuario::getPontos).reversed());
        //users.subList(0, 10).forEach(Usuario::tornarModerador);

        Stream<Usuario> usuarios = users.stream();
        usuarios.filter(u -> {
            return u.getPontos() > 100;
        });

        users.stream().filter(u -> u.getPontos() > 100);

    }

    @FunctionalInterface
    public interface Supplier<T> {
        T get();
    }

    Function<String, Usuario> criadordeusuarios = Usuario::new;

    Usuario afonso = criadordeusuarios.apply("norman");
    Usuario alonso = criadordeusuarios.apply("rogerio");

    BiFunction<String, Integer, Usuario> criadordeusuarios2 = Usuario::new;
    Usuario rodrigo = criadordeusuarios2.apply("rodrigo", 12);
    Usuario dom = criadordeusuarios2.apply("dom", 10);

    BiFunction<Integer, Integer, Integer> max = Math::max;
    ToIntBiFunction<Integer, Integer> max2 = Math::max;
    IntBinaryOperator max3 = Math::max;



}
