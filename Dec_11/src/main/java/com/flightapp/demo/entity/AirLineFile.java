package com.flightapp.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "airline_files")
public class AirLineFile {

	@Id
	private String id;

	private String fileName;
	private String contentType;
	private long size;
	private String filePath;
}
