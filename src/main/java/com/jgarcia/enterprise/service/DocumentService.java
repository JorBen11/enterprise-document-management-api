package com.jgarcia.enterprise.service;

import com.jgarcia.enterprise.dto.DocumentRequest;
import com.jgarcia.enterprise.entity.Document;
import com.jgarcia.enterprise.entity.DocumentStatus;
import com.jgarcia.enterprise.repository.DocumentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class DocumentService {

    private final DocumentRepository repository;

    public DocumentService(DocumentRepository repository) {
        this.repository = repository;
    }

    public List<Document> findAllApproved() {
        return repository.findByStatus(DocumentStatus.APPROVED);
    }

    public Document create(DocumentRequest request, Long userId) {
        Document doc = new Document();
        doc.setTitle(request.getTitle());
        doc.setDescription(request.getDescription());
        doc.setOwnerId(userId);
        return repository.save(doc);
    }

    public void sendToApprove(Long id) {
        Document doc = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        doc.setStatus(DocumentStatus.PENDING_APPROVAL);
        doc.setUpdatedAt(LocalDateTime.now());
    }
}
