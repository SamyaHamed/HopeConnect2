package com.example.Software_Advance.externalApi;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsArticleRepository extends JpaRepository<NewsArticleEntity, Long> {
    // JpaRepository يوفر CRUD بشكل جاهز
}
