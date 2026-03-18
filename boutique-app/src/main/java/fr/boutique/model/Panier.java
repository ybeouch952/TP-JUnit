package fr.boutique.model;

import java.util.ArrayList;
import java.util.List;

public class Panier {
    private final List<LignePanier> lignes = new ArrayList<>();

    public void ajouterArticle(Article article, int quantite) {
        lignes.add(new LignePanier(article, quantite));
    }

    public List<LignePanier> getLignes() {
        return lignes;
    }

    public double calculerTotal() {
        return lignes.stream().mapToDouble(LignePanier::sousTotal).sum();
    }
}
