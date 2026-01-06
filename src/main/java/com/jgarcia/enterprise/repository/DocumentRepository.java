package com.jgarcia.enterprise.repository;

import com.jgarcia.enterprise.entity.Document;
import com.jgarcia.enterprise.entity.DocumentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByStatus(DocumentStatus status);
}
