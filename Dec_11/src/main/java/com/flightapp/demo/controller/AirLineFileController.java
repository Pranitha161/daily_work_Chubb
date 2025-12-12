package com.flightapp.demo.controller;

import java.nio.charset.StandardCharsets;

import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightapp.demo.entity.AirLineFile;
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
  

    @PostMapping("/upload-json")
    public Mono<ResponseEntity<String>> uploadJson(@RequestPart("file") FilePart filePart) {

        return DataBufferUtils.join(filePart.content())
                .flatMap(dataBuffer -> {
                    String json = dataBuffer.toString(StandardCharsets.UTF_8);
                    DataBufferUtils.release(dataBuffer);

                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        AirLineFile airLine = mapper.readValue(json, AirLineFile.class);

                        return fileService.addAirline(airLine)
                                .thenReturn(ResponseEntity.ok("JSON uploaded and saved"));
                    } catch (Exception e) {
                        return Mono.just(ResponseEntity.badRequest()
                                .body("Invalid JSON: " + e.getMessage()));
                    }
                });
    }


}
