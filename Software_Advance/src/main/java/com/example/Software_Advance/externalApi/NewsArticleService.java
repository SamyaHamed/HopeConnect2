package com.example.Software_Advance.externalApi;

import com.example.Software_Advance.exceptions.EmailSendingException;
import com.example.Software_Advance.exceptions.ResourceNotFoundException;
import com.example.Software_Advance.models.Tables.User;
import com.example.Software_Advance.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsArticleService {

    private final NewsArticleRepository newsArticleRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public List<NewsArticleEntity> getAllArticles() {
        return newsArticleRepository.findAll();
    }

    public Optional<NewsArticleEntity> getArticleById(Long id) {
        return newsArticleRepository.findById(id);
    }
    public NewsArticleEntity saveArticle(NewsArticleEntity article) {
        if (article.getPublishedAt() == null || article.getPublishedAt().isEmpty()) {
            article.setPublishedAt(Instant.now().toString());
        }

        NewsArticleEntity saved = newsArticleRepository.save(article);

        String subject = "📢 New Article Published: " + saved.getTitle();
        String message = new StringBuilder()
                .append("Hello,\n\n")
                .append("A new article has just been published:\n")
                .append(saved.getTitle()).append("\n\n")
                .append(saved.getDescription()).append("\n\n")
                .append("Read more: ").append(saved.getUrl()).append("\n\n")
                .append("Regards,\nHopeConnect Team")
                .toString();

        List<User> users = userRepository.findAll();
        for (User u : users) {
            try {
                emailService.sendEmail(u.getEmail(), subject, message);
            } catch (EmailSendingException e) {
                System.err.println("Failed to send to: " + u.getEmail() + " -> " + e.getMessage());
            }
        }

        return saved;
       /* List<User> users = userRepository.findAll();
        for (User u : users) {
            emailService.sendEmail(u.getEmail(), subject, message);
        }

        return saved;*/
    }


    public void deleteArticle(Long id) {
        boolean exists = newsArticleRepository.existsById(id);
        if (!exists) {
            throw new ResourceNotFoundException("NewsArticle with ID " + id + " not found");
        }
        newsArticleRepository.deleteById(id);
    }
}
