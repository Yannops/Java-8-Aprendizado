import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.nio.file.Files.lines;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class Capitulo7 {
    public static void main(String[] args) throws IOException {
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

        Set<Usuario> lista = usuarios.stream().filter(u -> u.getPontos() > 80).collect(Collectors.toSet());

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

        Optional<Usuario> maiorpontuacao2 = usuarios.stream().max(Comparator.comparingInt(Usuario::getPontos));
        Usuario maximapontuacao = maiorpontuacao.get();

        int somadospontos = usuarios.stream().mapToInt(Usuario::getPontos).sum();

        int totalpontos = usuarios.stream().mapToInt(Usuario::getPontos).reduce(0, (a, b) -> a + b);
        int totalpontos2 = usuarios.stream().mapToInt(Usuario::getPontos).reduce(0, Integer::sum);
        int multiplicapontos = usuarios.stream().mapToInt(Usuario::getPontos).reduce(0, (a, b) -> a * b);
        int multiplicapontos2 = usuarios.stream().mapToInt(Usuario::getPontos).reduce(0, (a, b) -> a / b);

        //operação de soma sem o map
        int somapontos = usuarios.stream().reduce(0, (atual, u) -> atual + u.getPontos(), Integer::sum);

        Iterator<Usuario> i = usuarios.stream().iterator();

        usuarios.stream().iterator().forEachRemaining(System.out::println);

        boolean possuimoderador = usuarios.stream().anyMatch(Usuario::isModerador);

        Random randomnumbers = new Random(0);
        Supplier<Integer> supplier = () -> randomnumbers.nextInt();
        Stream<Integer> stream2 = Stream.generate(supplier);

        //Sempre utilizar operações de curto-circuito em streams deste tipo!!!, caso contrario, a operação nunca será
        //finalizada.
        Random randomnumbers2 = new Random(0);
        IntStream stream3 = IntStream.generate(() -> randomnumbers2.nextInt());
        //correto... pois desse modo limitamos a stream e assim a operação pode ser finalizada!!!
        List<Integer> numerosinteiros = stream3.limit(100).boxed().collect(toList());

        IntStream.generate(new Fibonacci()).limit(10).forEach(System.out::println);

        //pegando o primeiro numero maior que 100
        int maiorque100 = IntStream.generate(new Fibonacci()).filter(f -> f > 100).findFirst().getAsInt();

        System.out.println(maiorque100);

        IntStream.iterate(0, x -> x + 1).limit(10).forEach(System.out::println);

        Files.list(Paths.get("./src/main/java")).forEach(System.out::println);

        Files.list(Paths.get("./src/main/java")).filter(p -> p.toString().endsWith(".java")).map(p -> {
            try {
                return lines(p);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        })
                .forEach(System.out::println);

        Stream<Stream<String>> strings = Files.list(Paths.get("./src/main/java")).filter(p -> p.toString().endsWith(".java"))
                .map(p -> {
                    try {
                        return lines(p);
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });

        Stream<String> strings2 = Files.list(Paths.get("./src/main/java")).filter(p -> p.toString().endsWith(".java"))
                .flatMap(p -> {
                    try {
                        return lines(p);
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });


        IntStream chars = Files.list(Paths.get("./src/main/java")).filter(p -> p.toString().endsWith(".java"))
                .flatMap(p -> {
                    try {
                        return lines(p);
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                }).flatMapToInt((String s) -> s.chars());


        Grupo englishSpeakers = new Grupo();
        englishSpeakers.add(usuario);
        englishSpeakers.add(usuario2);
        Grupo spanishSpeakers = new Grupo();
        spanishSpeakers.add(usuario2);
        spanishSpeakers.add(usuario);

        List<Grupo> grupos = Arrays.asList(englishSpeakers, spanishSpeakers);

        grupos.stream().flatMap(g -> g.getUsuarios().stream()).forEach(System.out::println);

        List<Long> linhas = Files.list(Paths.get("./src/main/java")).filter(p -> p.toString().endsWith(".java"))
                .map(p -> {
                    try {
                        return lines(p).count();
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                }).collect(toList());

        Map<Path, Long> linhasporarquivo = new HashMap<>();
        Files.list(Paths.get("./src/main/java")).filter(p -> p.toString().endsWith(".java"))
                .forEach(p -> {
                    try {
                        linhasporarquivo.put(p, lines(p).count());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        System.out.println(linhasporarquivo);

        Map<Path, Long> linhas2 = Files.list(Paths.get("./src/main/java")).filter(p -> p.toString().endsWith(".java"))
                .collect(toMap(p -> p, p -> {
                    try {
                        return lines(p).count();
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                }));

        System.out.println(linhas2);

        Map<Path, List<String>> conteudo = Files.list(Paths.get("./src/main/java")).filter(p -> p.toString().endsWith(".java"))
                .collect(toMap(Function.identity(), p -> {
                    try {
                        return lines(p).collect(toList());
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                }));

        Map<String, Usuario> nomesdosusuarios = usuarios.stream().collect(toMap(Usuario::getNome, Function.identity()));

        Usuario usuario1 = new Usuario("Yann", 60);
        Usuario usuario3 = new Usuario("sergio", 90);
        Usuario usuario4 = new Usuario("fillipe", 40);
        Usuario usuario5 = new Usuario("kaique", 70);
        Usuario usuario6 = new Usuario("roger", 80);

        List<Usuario> alunosfatec = Arrays.asList(usuario1, usuario3, usuario4, usuario5, usuario6);

        Map<Integer, List<Usuario>> pontuacao = new HashMap<>();

        for (Usuario u : alunosfatec) {
            if (!pontuacao.containsKey(u.getPontos())) {
                pontuacao.put(u.getPontos(), new ArrayList<>());
            }
            pontuacao.get(u.getPontos()).add(u);
        }

        System.out.println(pontuacao);

        Map<Integer, List<Usuario>> pontuacao2 = new HashMap<>();

        for (Usuario u : alunosfatec) {
            pontuacao.computeIfAbsent(u.getPontos(), user -> new ArrayList<>()).add(u);
        }

        System.out.println(pontuacao2);

        Map<Integer, List<Usuario>> pontuacao3 = alunosfatec.stream().collect(Collectors.groupingBy(Usuario::getPontos));

        Map<Boolean, List<Usuario>> moderadoresenaomoderadores = alunosfatec.stream().collect(Collectors.partitioningBy(Usuario::isModerador));

        Map<Boolean, List<String>> nomesalunosportipo = alunosfatec.stream().collect(Collectors.partitioningBy(Usuario::isModerador,
                Collectors.mapping(Usuario::getNome, Collectors.toList())));

        Map<Boolean, Integer> pontuacaoportipo = alunosfatec.stream().collect(Collectors.partitioningBy(Usuario::isModerador,
                Collectors.summingInt(Usuario::getPontos)));

        System.out.println(pontuacaoportipo);

        String nomesjuntos = alunosfatec.stream().map(Usuario::getNome).collect(Collectors.joining(", "));


        List<Usuario> filtrareordenar = usuarios.stream().filter(p -> p.getPontos() > 100)
                .sorted(Comparator.comparing(Usuario::getNome))
                .collect(toList());

        List<Usuario> filtrareordenar2 = usuarios.parallelStream().filter(p -> p.getPontos() > 100)
                .sorted(Comparator.comparing(Usuario::getNome))
                .collect(toList());

        long sum = LongStream.range(0, 10000000).parallel().filter(x -> x % 2 == 0).sum();

        System.out.println(sum);

        //maneira antiga de adicionar 1 mês a partir da data atual.
        Calendar mesquevem = Calendar.getInstance();
        mesquevem.add(Calendar.MONTH, 1);

        //maneira atual de adicionar 1 mês a partir da data atual.
        LocalDate mesquevem2 = LocalDate.now().plusMonths(1);
        LocalDate menosumano = LocalDate.now().minusYears(1);

        LocalTime agora = LocalTime.now();
        LocalDate hoje = LocalDate.now();
        LocalDateTime dataehora = hoje.atTime(agora);

        ZonedDateTime datacomhora = dataehora.atZone(ZoneId.of("America/Sao_Paulo"));

        LocalDateTime semtimezone = datacomhora.toLocalDateTime();

        LocalDate data = LocalDate.of(2014,12,25);
        LocalDateTime horario = LocalDateTime.of(2014, 12, 25, 10, 30);

        LocalDate anopassado = LocalDate.now().withYear(2000);

        LocalDate anopassado2 = LocalDate.now().withYear(1998);
        System.out.println(anopassado2.getYear());

        LocalDate hoje2 = LocalDate.now();
        LocalDate amanha = LocalDate.now().plusDays(1);
        System.out.println(hoje2.isBefore(amanha));
        System.out.println(hoje2.isAfter(amanha));
        System.out.println(hoje2.isEqual(amanha));

        ZonedDateTime tokyo = ZonedDateTime.of(2011, 5, 2, 10, 30, 0, 0, ZoneId.of("Asia/Tokyo"));
        ZonedDateTime saoPaulo = ZonedDateTime.of(2011, 5, 2, 10, 30, 0, 0, ZoneId.of("America/Sao_Paulo"));
        tokyo = tokyo.plusHours(12);
        System.out.println(tokyo.isEqual(saoPaulo));

        System.out.println("Hoje é dia " + MonthDay.now().getDayOfMonth());
        YearMonth ym = YearMonth.from(data);
        System.out.println(ym.getMonth() + " " + ym.getYear());

        System.out.println(Month.DECEMBER.firstMonthOfQuarter());
        System.out.println(Month.DECEMBER.plus(2));
        System.out.println(Month.DECEMBER.minus(1));

        Locale lc = new Locale("pt");

        System.out.println(Month.DECEMBER.getDisplayName(TextStyle.FULL, lc));
        //SAIDA: Dezembro
        System.out.println(Month.DECEMBER.getDisplayName(TextStyle.SHORT, lc));
        //SAIDA: Dez

        LocalDateTime agora1 = LocalDateTime.now();
        String resultado = agora1.format(DateTimeFormatter.ISO_LOCAL_TIME);
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String resultado3 = agora1.format(formatador);
        LocalDate agoraemdata = LocalDate.parse(resultado3, formatador);

        LocalDate dataqualquer = LocalDate.of(1989, Month.JANUARY, 25);
        long dias = ChronoUnit.DAYS.between(dataqualquer, agora1);
        long meses = ChronoUnit.MONTHS.between(dataqualquer, agora1);
        long anos = ChronoUnit.YEARS.between(dataqualquer, agora1);


        Period periodo = Period.between(dataqualquer, agora1);
        System.out.println(periodo.getDays() + " " + periodo.getMonths() + " " + periodo.getYears());

        if (periodo.isNegative()) {
            periodo = periodo.negated();
        }

        System.out.println(periodo.getDays() + " " + periodo.getMonths() + " " + periodo.getYears());

        Period periodo2 = Period.of(5, 4, 1);

        LocalDateTime agora4 = LocalDateTime.now();
        LocalDateTime daquiumahora = LocalDateTime.now().plusHours(1);

        Duration duration = Duration.between(agora4, daquiumahora);

        if (duration.isNegative()) {
            duration = duration.negated();
        }

        System.out.println(duration.toHours() + " " + duration.toMinutes() + " " + duration.getSeconds());

        

    }
}
