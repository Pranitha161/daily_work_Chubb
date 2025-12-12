package com.flightapp.demo.service;


import com.flightapp.demo.entity.AirLineFile;
import com.flightapp.demo.repository.AirLineFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

import java.io.File;

@Service
@RequiredArgsConstructor
public class AirLineFileService {

    private final AirLineFileRepository repository;

    private static final String UPLOAD_DIR = "uploads/";

    public Mono<AirLineFile> uploadFile(FilePart filePart) {

        File uploadFolder = new File(UPLOAD_DIR);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

        String filePath = UPLOAD_DIR + filePart.filename();

        return filePart.transferTo(new File(filePath))
                .then(Mono.defer(() -> {
                    AirLineFile file = new AirLineFile();
                    file.setFileName(filePart.filename());
                    file.setContentType(filePart.headers().getContentType().toString());
                    file.setSize(filePart.headers().getContentLength());
                    file.setFilePath(filePath);
                    return repository.save(file);
                }));
    }
}
