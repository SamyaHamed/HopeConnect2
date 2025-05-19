package com.example.Software_Advance.externalApi;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsArticleController {

    private final NewsArticleService newsArticleService;
    private final EmailService emailService;

    @GetMapping
    public List<NewsArticleEntity> getAll() {
        return newsArticleService.getAllArticles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsArticleEntity> getById(@PathVariable Long id) {
        return newsArticleService.getArticleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<NewsArticleEntity> add(@RequestBody NewsArticleEntity article) {
        NewsArticleEntity saved = newsArticleService.saveArticle(article);
        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,
                                         @RequestBody NewsArticleEntity updated) {
        return newsArticleService.getArticleById(id)
                .map(existing -> {
                    if (updated.getTitle() != null)        existing.setTitle(updated.getTitle());
                    if (updated.getDescription() != null)  existing.setDescription(updated.getDescription());
                    if (updated.getUrl() != null)          existing.setUrl(updated.getUrl());
                    if (updated.getSourceName() != null)   existing.setSourceName(updated.getSourceName());
                    if (updated.getPublishedAt() == null || updated.getPublishedAt().isEmpty())
                        existing.setPublishedAt(Instant.now().toString());
                    else
                        existing.setPublishedAt(updated.getPublishedAt());
                    newsArticleService.saveArticle(existing);
                    return ResponseEntity.ok("Update success");
                })
                .orElse(ResponseEntity.status(404).body("News with ID " + id + " not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        newsArticleService.deleteArticle(id);
        return ResponseEntity.ok("Delete success");
    }

}
