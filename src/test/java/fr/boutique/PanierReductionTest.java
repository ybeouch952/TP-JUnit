package fr.boutique;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class PanierReductionTest {

    @Test
    void calculerTotalDoitAppliquerLaBonneReductionSansCode() {
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Classeur", 10.0);
        panier.ajouterArticle(article, 10);

        assertEquals(100.0, panier.calculerTotal(), 0.001);
    }

    @Test
    void calculerTotalDoitAppliquerLaBonneReductionAvecReduc10() {
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Classeur", 10.0);
        panier.ajouterArticle(article, 10);
        panier.appliquerCodeReduction("REDUC10");

        assertEquals(90.0, panier.calculerTotal(), 0.001);
    }

    @Test
    void calculerTotalDoitAppliquerLaBonneReductionAvecReduc20() {
        Panier panier = new Panier();
        Article article = new Article("REF-001", "Classeur", 10.0);
        panier.ajouterArticle(article, 10);
        panier.appliquerCodeReduction("REDUC20");

        assertEquals(80.0, panier.calculerTotal(), 0.001);
    }

    @Test
    void reductionDoitFonctionnerAvecPlusieursArticlesEtQuantitesSansCode() {
        Panier panier = new Panier();
        panier.ajouterArticle(new Article("REF-A", "Cahier", 5.0), 4);
        panier.ajouterArticle(new Article("REF-B", "Classeur", 8.0), 2);
        panier.ajouterArticle(new Article("REF-C", "Stylo", 2.0), 5);

        assertEquals(46.0, panier.calculerTotal(), 0.001);
    }

    @Test
    void reductionDoitFonctionnerAvecPlusieursArticlesEtQuantitesReduc10() {
        Panier panier = new Panier();
        panier.ajouterArticle(new Article("REF-A", "Cahier", 5.0), 4);
        panier.ajouterArticle(new Article("REF-B", "Classeur", 8.0), 2);
        panier.ajouterArticle(new Article("REF-C", "Stylo", 2.0), 5);
        panier.appliquerCodeReduction("REDUC10");

        assertEquals(41.4, panier.calculerTotal(), 0.001);
    }

    @Test
    void reductionDoitFonctionnerAvecPlusieursArticlesEtQuantitesReduc20() {
        Panier panier = new Panier();
        panier.ajouterArticle(new Article("REF-A", "Cahier", 5.0), 4);
        panier.ajouterArticle(new Article("REF-B", "Classeur", 8.0), 2);
        panier.ajouterArticle(new Article("REF-C", "Stylo", 2.0), 5);
        panier.appliquerCodeReduction("REDUC20");

        assertEquals(36.8, panier.calculerTotal(), 0.001);
    }

    @Test
    void ajouterArticleParametreInvalideReferenceNulleDoitLeverException() {
        Panier panier = new Panier();

        try {
            Article article = new Article(null, "Produit test", 1.0);
            panier.ajouterArticle(article, 1);
            fail("Une IllegalArgumentException etait attendue");
        } catch (IllegalArgumentException e) {
            // Exception attendue
        }
    }

    @Test
    void ajouterArticleParametreInvalideQuantiteNulleDoitLeverException() {
        Panier panier = new Panier();

        try {
            Article article = new Article("REF-001", "Produit test", 1.0);
            panier.ajouterArticle(article, 0);
            fail("Une IllegalArgumentException etait attendue");
        } catch (IllegalArgumentException e) {
            // Exception attendue
        }
    }

    @Test
    void ajouterArticleParametreInvalidePrixNegatifDoitLeverException() {
        Panier panier = new Panier();

        try {
            Article article = new Article("REF-001", "Produit test", -1.0);
            panier.ajouterArticle(article, 1);
            fail("Une IllegalArgumentException etait attendue");
        } catch (IllegalArgumentException e) {
            // Exception attendue
        }
    }
}
