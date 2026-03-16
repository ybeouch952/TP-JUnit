package fr.boutique;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

class ServiceCommandeTest {

    private DepotStock stockDisponible;
    private ServiceCommande service;
    private Panier panier;
    private Article articleTest;

    @BeforeEach
    void initialiser() {
        stockDisponible = reference -> 100;
        service = new ServiceCommande(stockDisponible);
        panier = new Panier();
        articleTest = new Article("REF-001", "Cahier", 3.50);
    }

    @Test
    void commandeValideDoitRetournerUneCommande() {
        panier.ajouterArticle(articleTest, 2);

        Commande commande = service.passerCommande(panier, "CLIENT-42");

        assertNotNull(commande);
        assertEquals(7.0, commande.total(), 0.001);
    }

    @Test
    void panierVideDoitLeverIllegalStateException() {
        try {
            service.passerCommande(panier, "C1");
            fail("Une IllegalStateException etait attendue");
        } catch (IllegalStateException e) {
            // Exception attendue
        }
    }

    @Test
    void identifiantClientNulDoitLeverException() {
        panier.ajouterArticle(articleTest, 1);

        try {
            service.passerCommande(panier, null);
            fail("Une IllegalArgumentException etait attendue");
        } catch (IllegalArgumentException e) {
            // Exception attendue
        }
    }

    @Test
    void identifiantClientVideDoitLeverException() {
        panier.ajouterArticle(articleTest, 1);

        try {
            service.passerCommande(panier, "");
            fail("Une IllegalArgumentException etait attendue");
        } catch (IllegalArgumentException e) {
            // Exception attendue
        }
    }

    @Test
    void stockInsuffisantDoitLeverStockInsuffisantException() {
        DepotStock stockInsuffisant = reference -> 1;
        ServiceCommande serviceStockInsuffisant = new ServiceCommande(stockInsuffisant);
        panier.ajouterArticle(articleTest, 2);

        try {
            serviceStockInsuffisant.passerCommande(panier, "CLIENT-42");
            fail("Une StockInsuffisantException etait attendue");
        } catch (StockInsuffisantException e) {
            // Exception attendue
        }
    }

    @Test
    void totalCommandeDoitCorrespondreAuTotalDuPanier() {
        panier.ajouterArticle(articleTest, 2);
        panier.ajouterArticle(new Article("REF-002", "Regle", 2.50), 1);

        Commande commande = service.passerCommande(panier, "CLIENT-99");

        assertEquals(panier.calculerTotal(), commande.total(), 0.001);
    }
}
