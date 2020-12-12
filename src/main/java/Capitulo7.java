import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

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


        List<Usuario> listaFiltrada = new ArrayList<>();

        usuarios.stream().filter(u -> u.getPontos() > 90).forEach(u -> listaFiltrada.add(u));
        usuarios.stream().filter(u -> u.getPontos() > 90).forEach(listaFiltrada::add);

        List<Usuario> listaFiltrada2 = usuarios.stream().filter(u -> u.getPontos() > 68).collect(toList());
        listaFiltrada2.forEach(System.out::println);

        Set<Usuario> lista = usuarios.stream().filter(u -> u.getPontos()).collect(Collectors.toSet());

        Set<Usuario> lista2 = stream.collect(Collectors.toCollection(HashSet::new));

        List<Integer> lista3 = usuarios.stream().map(u -> u.getPontos()).collect(Collectors.toList());
        List<Integer> lista4 = usuarios.stream().map(Usuario::getPontos).collect(Collectors.toList());

        IntStream stream1 = usuarios.stream().mapToInt(Usuario::getPontos);

        double mediapontos = usuarios.stream().mapToInt(Usuario::getPontos).average().getAsDouble();

        OptionalDouble mediapontos2 = usuarios.stream().mapToDouble(Usuario::getPontos).average();
        double media = mediapontos2.orElse(0.0);

        double media2 = usuarios.stream().mapToDouble(Usuario::getPontos).average().orElse(0.0);

        double pontuacaomedia = usuarios.stream().mapToInt(Usuario::getPontos).average().orElseThrow(IllegalAccessError::new);

        //usuarios.stream().mapToInt(Usuario::getPontos).average().ifPresent(valor -> atualizavalor(valor));

        Optional<Usuario> maiorpontuacao = usuarios.stream().max(Comparator.comparingInt(Usuario::getPontos));

        Optional<String> nomeusuariomaispontos = usuarios.stream().max(Comparator.comparingInt(Usuario::getPontos)).map(Usuario::getNome);
        Optional<String> nomeusuariomaispontos2 = usuarios.stream().max(Comparator.comparingInt(u -> u.getPontos())).map(u -> u.getNome());

        usuarios.sort(Comparator.comparing(Usuario::getNome));
        usuarios.stream().filter(u -> u.getPontos() > 80).sorted(Comparator.comparing(Usuario::getNome));

        List<Usuario> usuariospornomecommaisde80pontos = usuarios.stream().filter(u -> u.getPontos() > 80)
                .sorted(Comparator.comparing(Usuario::getNome))
                .collect(toList());

        Optional<Usuario> qualquerusuariocommaisde80pontos = usuarios.stream().filter(u -> u.getPontos() > 80)
        .findAny();

        Optional<Usuario> primeirousuariocommaisde80pontos = usuarios.stream().filter(u -> u.getPontos() > 80)
                .findFirst();

        //printa no console cada iteração na lista para facilitar o monitoramento dos elementos que estão passando na lista
        //e facilitam a execução!
        usuarios.stream().filter(u -> u.getPontos() > 80).peek(System.out::println).findAny();

        //sorted é um método stateful, significa que ele sempre precisa percorrer toda a stream independente de
        //sua operação terminal mande que ela encerre antes usando por exemplo o findAny ou findFirst
        usuarios.stream().sorted(Comparator.comparing(Usuario::getNome)).peek(System.out::println).findAny();

        Optional<Usuario> maiorpontuacao = usuarios.stream().max(Comparator.comparingInt(Usuario::getPontos));
        Usuario maximapontuacao = maiorpontuacao.get();

        int somadospontos = usuarios.stream().mapToInt(Usuario::getPontos).sum();

        int totalpontos = usuarios.stream().mapToInt(Usuario::getPontos).reduce(0, (a, b) -> a + b);
        int totalpontos2 = usuarios.stream().mapToInt(Usuario::getPontos).reduce(0, Integer::sum);
        int multiplicapontos = usuarios.stream().mapToInt(Usuario::getPontos).reduce(0, (a, b) -> a * b);
        int multiplicapontos = usuarios.stream().mapToDouble(Usuario::getPontos).reduce(0, (a, b) -> a / b);

        //operação de soma sem o map
        int somapontos = usuarios.stream().reduce(0, (atual, u) -> atual + u.getPontos(), Integer::sum);

        
    }
}
