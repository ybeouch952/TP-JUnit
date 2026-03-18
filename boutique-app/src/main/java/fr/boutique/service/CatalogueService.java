package fr.boutique.service;

import fr.boutique.model.Article;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogueService {

    private final List<Article> articles = List.of(
            new Article("REF-001", "Stylo bille bleu", 1.50),
            new Article("REF-002", "Cahier A4 96 pages", 3.20),
            new Article("REF-003", "Crayon HB", 0.90),
            new Article("REF-004", "Gomme blanche", 0.70),
            new Article("REF-005", "Regle 30 cm", 1.10),
            new Article("REF-006", "Surligneur jaune", 1.80),
            new Article("REF-007", "Classeur rigide", 4.90),
            new Article("REF-008", "Feutres (x12)", 6.50)
    );

    public List<Article> listerArticles() {
        return articles;
    }

    public Optional<Article> trouverParReference(String reference) {
        return articles.stream()
                .filter(a -> a.getReference().equals(reference))
                .findFirst();
    }
}
