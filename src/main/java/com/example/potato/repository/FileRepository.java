package com.example.potato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.potato.model.Files;

public interface FileRepository extends JpaRepository<Files, Long>{
	
	@Query("SELECT f FROM Files f where f.file1=:file1 AND f.file2=:file2")
	Files getFileDetails(@Param("file1") String file1, @Param("file2") String file2);

}
