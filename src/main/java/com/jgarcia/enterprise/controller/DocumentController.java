package com.jgarcia.enterprise.controller;

import com.jgarcia.enterprise.dto.DocumentRequest;
import com.jgarcia.enterprise.entity.Document;
import com.jgarcia.enterprise.service.DocumentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService service;

    public DocumentController(DocumentService service){
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
    public List<Document> list(){
        return service.findAllApproved();
    }

    @PostMapping("/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public Document create(@RequestBody DocumentRequest request, @PathVariable Long userId){
        return service.create(request, userId);
    }
}
