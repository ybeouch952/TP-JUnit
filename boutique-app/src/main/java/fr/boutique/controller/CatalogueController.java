package fr.boutique.controller;

import fr.boutique.model.Article;
import fr.boutique.service.CatalogueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CatalogueController {

    private final CatalogueService catalogueService;

    public CatalogueController(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    @GetMapping("/articles")
    public List<Article> listerArticles() {
        return catalogueService.listerArticles();
    }

    @GetMapping("/articles/{ref}")
    public ResponseEntity<Article> detailArticle(@PathVariable("ref") String reference) {
        return catalogueService.trouverParReference(reference)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/health")
    public Map<String, String> healthSimple() {
        return Map.of(
                "status", "UP",
                "service", "boutique",
                "version", "1.0.0"
        );
    }
}
