package com.example.potato.model;

import javax.persistence.*;

@Entity
@Table(name = "Files")
public class Files {
	private Long id;
	
	@Column(name="file1")
	private String file1;
	
	@Column(name="file2")
	private String file2;
	
	@Column(name="score")
	private float score;
	
	/**
     * @return id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    
    /**
     * The function sets id associated with the File object
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * @return the name of file1
     */
    public String getFile1() {
        return file1;
    }
    
    /**
     * The function sets the filename associated with the first file
     * @param file1
     */
    public void setFile1(String file1) {
        this.file1 = file1;
    }
    
    /**
     * @return the name of file2
     */
    public String getFile2() {
        return file2;
    }
    
    /**
     * The function sets the filename associated with the second file
     * @param file2
     */
    public void setFile2(String file2) {
        this.file2 = file2;
    }
    
    /**
     * @return similarity score
     */
    public Float getScore() {
    	return score;
    }
    
    /**
     * The function sets the similarity score of the two files
     * @param sc
     */
    public void setScore(Float sc) {
    	this.score = sc;
    }
	
	
}
