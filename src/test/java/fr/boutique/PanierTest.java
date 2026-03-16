package fr.boutique;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class PanierTest {

	@Test
	void ajouterArticleDeitAugmenterLeNombreDeArticles() {
		Panier panier = new Panier();
		Article article = new Article("REF-001", "Stylo bleu", 1.50);

		panier.ajouterArticle(article, 2);

		assertEquals(1, panier.nombreArticles());
	}

	@Test
	void calculerTotalDoitRetournerLaSommeDessousTotaux() {
		Panier panier = new Panier();
		Article article1 = new Article("REF-001", "Stylo bleu", 1.50);
		Article article2 = new Article("REF-002", "Stylo rouge", 1.50);

		panier.ajouterArticle(article1, 3);
		panier.ajouterArticle(article2, 3);

		assertEquals(9.00, panier.calculerTotal(), 0.0001);
	}

	@Test
	void panierVideDoitRetournerEstVideEgalTrue() {
		Panier panier = new Panier();

		assertTrue(panier.estVide());
	}

	@Test
	void panierAvecArticlesNeDoitPasEtreVide() {
		Panier panier = new Panier();
		Article article = new Article("REF-003", "Cahier", 2.00);

		panier.ajouterArticle(article, 1);

		assertFalse(panier.estVide());
	}

	@Test
	void articleNulDoitLeverException() {
		Panier panier = new Panier();

		try {
			panier.ajouterArticle(null, 1);
			fail("Une IllegalArgumentException etait attendue");
		} catch (IllegalArgumentException e) {
			// Exception attendue
		}
	}

	@Test
	void quantiteNulleDoitLeverException() {
		Panier panier = new Panier();
		Article article = new Article("REF-001", "Stylo", 1.50);

		try {
			panier.ajouterArticle(article, 0);
			fail("Une IllegalArgumentException etait attendue");
		} catch (IllegalArgumentException e) {
			// Exception attendue
		}
	}

	@Test
	void quantiteNegativeDoitLeverException() {
		Panier panier = new Panier();
		Article article = new Article("REF-001", "Stylo", 1.50);

		try {
			panier.ajouterArticle(article, -3);
			fail("Une IllegalArgumentException etait attendue");
		} catch (IllegalArgumentException e) {
			// Exception attendue
		}
	}

	@Test
	void codeReductionVideDoitLeverException() {
		Panier panier = new Panier();

		try {
			panier.appliquerCodeReduction("");
			fail("Une IllegalArgumentException etait attendue");
		} catch (IllegalArgumentException e) {
			// Exception attendue
		}
	}

	@Test
	void codeReductionNulDoitLeverException() {
		Panier panier = new Panier();

		try {
			panier.appliquerCodeReduction(null);
			fail("Une IllegalArgumentException etait attendue");
		} catch (IllegalArgumentException e) {
			// Exception attendue
		}
	}

	@Test
	void quantiteUneDoitEtreAcceptee() {
		Panier panier = new Panier();
		Article article = new Article("REF-010", "Classeur", 9.99);

		panier.ajouterArticle(article, 1);

		assertEquals(9.99, panier.calculerTotal(), 0.001);
	}

	@Test
	void articleGratuitDoitEtreAccepte() {
		Panier panier = new Panier();
		Article articleGratuit = new Article("OFFERT-01", "Stylo offert", 0.0);

		panier.ajouterArticle(articleGratuit, 1);

		assertEquals(0.0, panier.calculerTotal(), 0.001);
	}

	@Test
	void prixEleveDoitFonctionner() {
		Panier panier = new Panier();
		Article article = new Article("PREMIUM-001", "Imprimante", 999.99);

		panier.ajouterArticle(article, 1);

		assertEquals(999.99, panier.calculerTotal(), 0.001);
	}

	@Test
	void panierAvecUnSeulArticleDoitFonctionner() {
		Panier panier = new Panier();
		Article article = new Article("REF-777", "Agenda", 5.50);

		panier.ajouterArticle(article, 2);

		assertEquals(1, panier.nombreArticles());
	}

	@Test
	void plusieursArticlesDifferentsDansPanier() {
		Panier panier = new Panier();
		Article article1 = new Article("REF-A", "Crayon", 1.20);
		Article article2 = new Article("REF-B", "Cahier", 2.50);
		Article article3 = new Article("REF-C", "Gomme", 0.80);

		panier.ajouterArticle(article1, 2);
		panier.ajouterArticle(article2, 3);
		panier.ajouterArticle(article3, 1);

		assertEquals(10.70, panier.calculerTotal(), 0.001);
	}
}
