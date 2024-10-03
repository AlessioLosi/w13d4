package org.example;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        List<Prodotti> prodotti = Arrays.asList(
                new Prodotti(9L, "Reputation", "Album", 120.0),
                new Prodotti(2L, "GUTS", "Books", 120.0),
                new Prodotti(4L, "Short n'sweet", "Album", 10.0),
                new Prodotti(7L, "The Story of us", "Books", 150.0),
                new Prodotti(1L, "Lover", "Books", 50.0),
                new Prodotti(1L, "Latte", "Baby", 70.0),
                new Prodotti(1L, "Pannolini", "Baby", 12.0),
                new Prodotti(1L, "Rasoio", "Boys", 15.0),
                new Prodotti(1L, " Schiuma da barba", "Boys", 20.0)
        );

        List<Utente> utente = Arrays.asList(
                new Utente(1L, "Taylor Swift", 4),
                new Utente(3L, "Olivia Rodrigo", 5),
                new Utente(9L, "Gracie Abrams", 2),
                new Utente(7L, "Tatiana McRae", 1),
                new Utente(3L, "Charles Leclerc", 2),
                new Utente(5L, "Travis Kelce", 4)
        );


        List<Carrello> carrelli = Arrays.asList(
                new Carrello(3L, "completo", LocalDateTime.now(), LocalDateTime.now().plusDays(8), Arrays.asList(prodotti.get(0), prodotti.get(4)), utente.get(0)),
                new Carrello(4L, "spedito", LocalDateTime.now().minusDays(3), LocalDateTime.now(), Arrays.asList(prodotti.get(6), prodotti.get(5)), utente.get(2)),
                new Carrello(5L, "in preparazione", LocalDateTime.now(), LocalDateTime.now().plusDays(12), Arrays.asList(prodotti.get(3), prodotti.get(2)), utente.get(1)),
                new Carrello(9L, "consegnato", LocalDateTime.now().minusDays(5), LocalDateTime.now().minusHours(5), Arrays.asList(prodotti.get(0), prodotti.get(6)), utente.get(3)),
                new Carrello(124L, "consegnato", LocalDateTime.now().minusDays(3), LocalDateTime.now().minusDays(2), Arrays.asList(prodotti.get(7), prodotti.get(1)), utente.get(4)),
                new Carrello(656L, "spedito", LocalDateTime.now().minusDays(1), LocalDateTime.now().minusMonths(1), Arrays.asList(prodotti.get(8), prodotti.get(0)), utente.get(5))
        );


        System.out.println("Esercizio 1");
        Map<Utente, List<Carrello>> ordiniUtente = carrelli.stream().collect(Collectors.groupingBy(Carrello::getUtente));
        ordiniUtente.forEach((utente1, carrello) -> {
            System.out.println("Nome utente: " + utente1.getNome());
            carrello.forEach(carrello1 -> System.out.println("Ordine N.: " + carrello1.getId() + "Effettuato il:" + carrello1.getData()));

        });
        System.out.println("Esercizio 2");
        Map<Utente, Double> utentetotaleAcquisti = carrelli.stream().collect(Collectors.groupingBy(Carrello::getUtente, Collectors.summingDouble(carrello -> carrello.getProdotti().stream().mapToDouble(Prodotti::getPrezzo).sum())));
        utentetotaleAcquisti.forEach((utente1, totale) -> System.out.println("Nome Utente: " + utente1.getNome() + " Totale speso: $" + totale));

        System.out.println("Esercizio 3");
        OptionalDouble piuCostoso = prodotti.stream().mapToDouble(prodotti1 -> prodotti1.getPrezzo()).max();
        if (piuCostoso.isPresent()) System.out.println("Il prezzo più alto è di : $" + piuCostoso.getAsDouble());
        else System.out.println("Non è possibile trovare il prodotto più costoso");

        System.out.println("Esercizio 4");
        OptionalDouble mediaOrdini = carrelli.stream().mapToDouble(carrelli1 -> carrelli1.getProdotti().stream().mapToDouble(Prodotti::getPrezzo).sum()).average();
        if (mediaOrdini.isPresent()) System.out.println("La media è: $" + mediaOrdini.getAsDouble());
        System.out.println("Esercizio 5");
        Map<String, Double> prodottiCat = prodotti.stream().collect(Collectors.groupingBy(Prodotti::getCategoria, Collectors.summingDouble(Prodotti::getPrezzo)));
        prodottiCat.forEach((categoria, somma) -> System.out.println("Categoria: " + categoria + " Totale : $" + somma));
        System.out.println("Esercizio 6");
        System.out.println("Esercizio 7");


    }
}
