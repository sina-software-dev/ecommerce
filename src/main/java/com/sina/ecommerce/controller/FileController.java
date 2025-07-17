package com.sina.ecommerce.controller;

import com.sina.ecommerce.model.File;
import com.sina.ecommerce.service.FileService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sina Ramezani, 7/17/2025
 */
@RestController
@RequestMapping("/files")
@Validated
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("{id}")
    public ResponseEntity<File> getFile(@PathVariable Long id) {
        logger.info("Fetching file with id: {}", id);
        File file = fileService.getFileById(id);
        return ResponseEntity.ok(file);
    }

    @PostMapping
    public ResponseEntity<File> postFile(@Valid @RequestBody File file) {
        logger.info("Creating new file");
        File createdFile = fileService.createFile(file);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFile);
    }

    @PutMapping("{id}")
    public ResponseEntity<File> updateFile(@PathVariable Long id, @Valid @RequestBody File file) {
        logger.info("Updating file with id: {}", id);
        file.setId(id);
        File updatedFile = fileService.updateFile(file);
        return ResponseEntity.ok(updatedFile);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable Long id) {
        logger.info("Deleting file with id: {}", id);
        fileService.deleteFile(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
