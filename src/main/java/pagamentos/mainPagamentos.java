package pagamentos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class mainPagamentos {
    public static void main(String[] args) {

        List<Pagamento> pagamentos = new LinkedList<>();
        pagamentos.add(new Pagamento(1, "Cliente 1", new BigDecimal("100"), LocalDateTime.now().minusDays(2)));
        pagamentos.add(new Pagamento(2, "Cliente 2", new BigDecimal("50"), LocalDateTime.now().minusDays(1)));
        pagamentos.add(new Pagamento(3, "Cliente 3", new BigDecimal("150"), LocalDateTime.now().minusDays(2)));
        pagamentos.add(new Pagamento(4, "Cliente 4", new BigDecimal("120"), LocalDateTime.now().minusDays(1)));
        pagamentos.add(new Pagamento(5, "Cliente 5", new BigDecimal("30"), LocalDateTime.now().minusDays(1)));


        //escrever o nome de cada cliente no console, deve ser feito em java 8 e anterior a ele
        System.out.println("\n");
        for (Pagamento pagamento: pagamentos) {
            System.out.println(pagamento.getNomeCliente());
        }

        System.out.println("\n");
        pagamentos.stream().map(c -> c.getNomeCliente()).forEach(System.out::println);

        //escreva uma função que filtre os pagamentos maiores que 100 e printe o nome do cliente e a data de cada um dos pagamentos no seguinte formato: "dd/mm/yyyy"
        System.out.println("\n");
        pagamentos.stream().filter(c -> c.getValor().compareTo(new BigDecimal("100")) > 0) .forEach(System.out::println);

        //escreva uma função que ordene a lista por data de pagamento e gere uma nova lista ordenada, print essa lista ordenada.
        System.out.println("\n");
        List<Pagamento> pagamentos1 = pagamentos.stream().sorted(Comparator.comparing(Pagamento::getData)).collect(Collectors.toList());
        pagamentos1.stream().forEach(System.out::println);

        //escreva uma função que apresente a soma de todos os pagamentos printe em seguida
        System.out.println("\n");
        pagamentos.stream().map(p -> p.getValor()).reduce(BigDecimal::add).ifPresent(System.out::println);

        //escreva uma função que agrupe os pagamentos em um mapa por nome do cliente e em seguida calcule a soma dos pagamentos de cada um dos clientes, printe o nome e o valor total dos pagamentos

    }
}
