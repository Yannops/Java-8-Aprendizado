import ecommerceexemplo.Customer;
import ecommerceexemplo.Payment;
import ecommerceexemplo.Produto;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;

public class TestesEcommerce {
    public static void main(String[] args) {


        Customer paulo = new Customer("Paulo Cesar");
        Customer yann = new Customer("Yann Silveira");
        Customer igor = new Customer("Igor Labras");
        Customer gabriel = new Customer("Gabriel Coelho");

        Produto bach = new Produto("Bach Completo", Paths.get("/music/bach.mp3"), new BigDecimal(100));
        Produto poderosas = new Produto("Poderosas Anita", Paths.get("/music/poderosas.mp3"), new BigDecimal(10));
        Produto bandeira = new Produto("Bandeira Brasil", Paths.get("/music/bandeira.jpg"), new BigDecimal(70));
        Produto beauty = new Produto("Beleza Americana", Paths.get("/music/beleza.mov"), new BigDecimal(80));
        Produto vingadores = new Produto("Os Vingadores", Paths.get("/music/vingadores.mov"), new BigDecimal(95));
        Produto amelie = new Produto("Amelie Poulman", Paths.get("/music/amelie.mov"), new BigDecimal(42));

        LocalDateTime hoje = LocalDateTime.now();
        LocalDateTime ontem = LocalDateTime.now().minusDays(1);
        LocalDateTime mespassado = LocalDateTime.now().minusMonths(1);

        Payment pagamento = new Payment(asList(bach, poderosas), hoje, paulo);
        Payment pagamento2 = new Payment(asList(bach, bandeira, amelie), ontem, yann);
        Payment pagamento3 = new Payment(asList(beauty, vingadores, bach), hoje, igor);
        Payment pagamento4 = new Payment(asList(amelie, bach, poderosas), mespassado, gabriel);
        Payment pagamento5 = new Payment(asList(amelie, beauty), ontem, yann);

        List<Payment> pagamentos = asList(pagamento, pagamento2, pagamento3, pagamento4, pagamento5);

        pagamentos.stream().sorted(comparing(Payment::getData)).forEach(System.out::println);

        pagamento.getProdutos().stream().map(Produto::getPrice).reduce(BigDecimal::add).ifPresent(System.out::println);

        Stream<BigDecimal> precoslista = pagamentos.stream().map(p -> p.getProdutos().stream().map(Produto::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add));

        BigDecimal total = pagamentos.stream().map(p -> p.getProdutos().stream().map(Produto::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)).reduce(BigDecimal.ZERO, BigDecimal::add);

        Stream<BigDecimal> precodecadaproduto = pagamentos.stream().flatMap(p -> p.getProdutos().stream()
                .map(Produto::getPrice));

        Function<Payment, Stream<BigDecimal>> mapper = p -> p.getProdutos().stream().map(Produto::getPrice);

        BigDecimal totalflat = pagamentos.stream().flatMap(p -> p.getProdutos().stream().map(Produto::getPrice))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Stream<Produto> maisvendido = pagamentos.stream().map(Payment::getProdutos).flatMap(List::stream);
        Stream<Produto> maisvendido2 = pagamentos.stream().flatMap(p -> p.getProdutos().stream());

        Map<Produto, Long> topProdutos = pagamentos.stream().flatMap(p -> p.getProdutos().stream())
                .collect(groupingBy(Function.identity(), Collectors.counting()));

        topProdutos.entrySet().stream().forEach(System.out::println);

        topProdutos.entrySet().stream().max(comparing(Map.Entry::getValue)).ifPresent(System.out::println);

        Map<Produto, BigDecimal> totalvalorporproduto = pagamentos.stream().flatMap(p -> p.getProdutos().stream())
                .collect(groupingBy(Function.identity(),
                        reducing(BigDecimal.ZERO, Produto::getPrice, BigDecimal::add)));

        totalvalorporproduto.entrySet().stream().sorted(comparing(Map.Entry::getValue))
                .forEach(System.out::println);

        Map<Produto, BigDecimal> totalvalorporproduto2 = pagamentos.stream().flatMap(p -> p.getProdutos().stream())
                .collect(Collectors.toMap(Function.identity(), Produto::getPrice, BigDecimal::add));

        Map<Customer, List<List<Produto>>> clienteparaprodutoslista = pagamentos.stream()
                .collect(groupingBy(Payment::getCliente,
                        Collectors.mapping(Payment::getProdutos, Collectors.toList())));

        clienteparaprodutoslista.entrySet().stream().sorted(comparing(r -> r.getKey().getName()))
                .forEach(System.out::println);

        Map<Customer, List<Produto>> clienteparaprodutoslista2 = clienteparaprodutoslista.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().stream().flatMap(List::stream)
                        .collect(Collectors.toList())));

        clienteparaprodutoslista2.entrySet().stream().sorted(comparing(e -> e.getKey().getName()))
                .forEach(System.out::println);

        Map<Customer, List<Produto>> clientesparaprodutos = pagamentos.stream()
                .collect(groupingBy(Payment::getCliente, reducing(Collections.emptyList(),
                        Payment::getProdutos, (l1, l2) -> {
                            List<Produto> l = new ArrayList<>();
                            l.addAll(l1);
                            l.addAll(l2);
                            return l;
                        })));

        Function<Payment, BigDecimal> pagamentototal = p -> p.getProdutos().stream().map(Produto::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<Customer, BigDecimal> totalvalorporcliente = pagamentos.stream().collect(groupingBy(Payment::getCliente,
                reducing(BigDecimal.ZERO, pagamentototal, BigDecimal::add)));

        totalvalorporcliente.entrySet().stream().sorted(comparing(Map.Entry::getValue)).forEach(System.out::println);

        
    }
}
