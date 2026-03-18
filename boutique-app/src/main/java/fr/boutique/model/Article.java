package fr.boutique.model;

public class Article {
    private final String reference;
    private final String nom;
    private final double prix;

    public Article(String reference, String nom, double prix) {
        if (reference == null || reference.isBlank()) {
            throw new IllegalArgumentException("La reference ne peut pas etre vide");
        }
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("Le nom ne peut pas etre vide");
        }
        if (prix < 0) {
            throw new IllegalArgumentException("Le prix ne peut pas etre negatif");
        }
        this.reference = reference;
        this.nom = nom;
        this.prix = prix;
    }

    public String getReference() {
        return reference;
    }

    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }
}
