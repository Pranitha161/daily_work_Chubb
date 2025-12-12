package com.flightapp.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.demo.service.AirLineFileService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/airline/file")
@RequiredArgsConstructor
public class AirLineFileController {

    private final AirLineFileService fileService;

    @PostMapping("/upload")
    public Mono<ResponseEntity<String>> uploadFile(@RequestPart("file") FilePart filePart) {

        return fileService.uploadFile(filePart)
                .map(saved -> ResponseEntity.ok("Uploaded: " + saved.getFileName()))
                .onErrorResume(e -> Mono.just(
                        ResponseEntity.internalServerError()
                                .body("Upload failed: " + e.getMessage())
                ));
    }

}
