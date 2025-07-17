package com.sina.ecommerce.service;

import com.sina.ecommerce.model.File;
import com.sina.ecommerce.repository.FileRepository;
import org.springframework.stereotype.Service;

/**
 * @author Sina Ramezani, 7/17/2025
 */
@Service
public class FileService {
    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File createFile(File file) {
        return fileRepository.save(file);
    }

    public File getFileById(Long id) {
        return fileRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public File updateFile(File file) {
        return fileRepository.save(file);
    }

    public void deleteFile(Long id) {
        fileRepository.deleteById(id);
    }
}
