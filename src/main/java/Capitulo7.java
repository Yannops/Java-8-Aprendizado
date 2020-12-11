import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Capitulo7 {
    public static void main(String[] args) {
        Usuario usuario = new Usuario("yann", 70);
        Usuario usuario2 = new Usuario("fillipe", 90);

        List<Usuario> usuarios = Arrays.asList(usuario, usuario2);

        usuarios.stream().filter(u -> u.getPontos() > 85);
        usuarios.forEach(System.out::println);

        System.out.println("\n");

        Stream<Usuario> stream = usuarios.stream().filter(u -> u.getPontos() > 85);
        stream.forEach(System.out::println);

        System.out.println("\n");

        usuarios.stream().filter(u -> u.getPontos() > 88).forEach(System.out::println);

        usuarios.stream().filter(u -> u.getNome().equals("yann")).forEach(Usuario::tornarModerador);

        usuarios.stream().filter(Usuario::isModerador);
        usuarios.stream().filter(u -> u.isModerador());

        
    }
}
