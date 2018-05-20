package com.example.potato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.potato.model.Files;
import com.example.potato.service.FileService;

@RestController
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	@RequestMapping(value="/getScore", params= {"file1", "file2"}, method= RequestMethod.GET)
	public float getFiles(@RequestParam("file1") String file1, @RequestParam("file2") String file2) {
		return fileService.retrieveScore(file1, file2);
	}
	
	@RequestMapping(value="/postFiles", method= RequestMethod.POST)
	public void postUser(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2) {
		Files f = new Files();
		f.setFile1(file1.getName());
		f.setFile2(file2.getName());
		float score = fileService.compare(file1, file2);
		f.setScore(score);
		
		fileService.save(f);
	}

}
