package fr.boutique;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PanierReductionTest {

    @ParameterizedTest
    @CsvSource({
        "'', 100.0",
        "REDUC10, 90.0",
        "REDUC20, 80.0"
    })
    void calculerTotalDoitAppliquerLaBonneReduction(String code, double totalAttendu) {
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Classeur", 10.0);
        panier.ajouterArticle(article, 10);

        if (code != null && !code.isBlank()) {
            panier.appliquerCodeReduction(code.trim());
        }

        assertEquals(totalAttendu, panier.calculerTotal(), 0.001);
    }

    @ParameterizedTest
    @CsvSource({
        "'', 46.0",
        "REDUC10, 41.4",
        "REDUC20, 36.8"
    })
    void reductionDoitFonctionnerAvecPlusieursArticlesEtQuantites(String code, double totalAttendu) {
        Panier panier = new Panier();
        panier.ajouterArticle(new Article("REF-A", "Cahier", 5.0), 4);
        panier.ajouterArticle(new Article("REF-B", "Classeur", 8.0), 2);
        panier.ajouterArticle(new Article("REF-C", "Stylo", 2.0), 5);

        if (code != null && !code.isBlank()) {
            panier.appliquerCodeReduction(code.trim());
        }

        assertEquals(totalAttendu, panier.calculerTotal(), 0.001);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "NULL, 1, 1.0",
        "REF-001, 0, 1.0",
        "REF-001, 1, -1.0"
    }, nullValues = "NULL")
    void ajouterArticleParametreInvalideDoitLeverException(String reference, int quantite, double prix) {
        Panier panier = new Panier();

        assertThrows(IllegalArgumentException.class, () -> {
            Article article = new Article(reference, "Produit test", prix);
            panier.ajouterArticle(article, quantite);
        });
    }
}
