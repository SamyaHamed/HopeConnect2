package com.example.Software_Advance.externalApi;

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
        // إذا لم يُرسل publishedAt، يعين timestamp
        if (article.getPublishedAt() == null || article.getPublishedAt().isEmpty()) {
            article.setPublishedAt(Instant.now().toString());
        }
        NewsArticleEntity saved = newsArticleRepository.save(article);

        // إعداد موضوع ونص الإيميل
        String subject = "📢 New Article Published: " + saved.getTitle();
        String message = new StringBuilder()
                .append("Hello,\n\n")
                .append("A new article has just been published:\n")
                .append(saved.getTitle()).append("\n\n")
                .append(saved.getDescription()).append("\n\n")
                .append("Read more: ").append(saved.getUrl()).append("\n\n")
                .append("Regards,\nHopeConnect Team")
                .toString();

        // إرسال لكل المستخدمين
        List<User> users = userRepository.findAll();
        for (User u : users) {
            emailService.sendEmail(u.getEmail(), subject, message);
        }

        return saved;
    }

    public void deleteArticle(Long id) {
        newsArticleRepository.deleteById(id);
    }
}
