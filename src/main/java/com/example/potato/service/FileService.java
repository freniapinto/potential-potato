package com.example.potato.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.potato.model.Files;

public interface FileService {
	float compare(MultipartFile file1, MultipartFile file2);
	
	float retrieveScore(String f1, String f2);

	void save(Files f);

}
