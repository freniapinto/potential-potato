package com.example.potato.service;

import java.io.IOException;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.potato.model.Files;
import com.example.potato.repository.FileRepository;

@Service
public class CosineSimilarity implements FileService {
	
	@Autowired
	private FileRepository fileRepo;

	@Override
	public float compare(MultipartFile f1, MultipartFile f2) {
		String content1 = "";
		String content2 = "";

		try {
			content1 = readFile(f1);
			content2 = readFile(f2);
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return getScore(content1, content2);

	}

	private String readFile(MultipartFile file) throws IOException {
		byte[] encoded = file.getBytes();
		return new String(encoded, "UTF-8");
	}

	/**
	 * Function to implement Cosine Similarity and return the similarity score
	 * 
	 * @param c1
	 * @param c2
	 * @return float score
	 */
	private float getScore(String c1, String c2) {
		List<String> ngram1 = generateNGrams(c1);
		List<String> ngram2 = generateNGrams(c2);

		Map<String, Double> tf1 = getNgramCount(ngram1);
		Map<String, Double> tf2 = getNgramCount(ngram2);

		List<String> idf = new ArrayList<>(ngram1);
		idf.addAll(ngram2);

		Set<String> idfSet = new HashSet<>(idf);
		List<String> newIdf = new ArrayList<>(idfSet);

		Map<String, Double> idfmap = new HashMap<>();

		int occurences = 0;
		for (String each : newIdf) {
			if (tf1.containsKey(each) && tf2.containsKey(each)) {
				occurences = 2;
			} else if (tf1.containsKey(each) || tf2.containsKey(each)) {
				occurences = 1;
			}
			double freq = 1.0;
			try {
				freq = 1 + Math.log(2.0 / occurences);
			} catch (Exception e) {
				freq = 1.0;
			}
			idfmap.put(each, freq);
		}

		Map<String, Double> scores1 = new HashMap<>();
		Map<String, Double> scores2 = new HashMap<>();

		double denom1 = 0.0;

		for (Map.Entry<String, Double> entry : tf1.entrySet()) {
			double value = entry.getValue() * idfmap.get(entry.getKey());
			scores1.put(entry.getKey(), value);
			denom1 = denom1 + Math.pow(value, 2);
		}

		double denom2 = 0.0;

		for (Map.Entry<String, Double> entry : tf2.entrySet()) {
			double value = entry.getValue() * idfmap.get(entry.getKey());
			scores2.put(entry.getKey(), value);
			denom2 = denom2 + Math.pow(value, 2);
		}

		double numerator = 0.0;
		double denominator = Math.sqrt(denom1) * Math.sqrt(denom2);

		List<String> list1 = new ArrayList<>(tf1.keySet());
		List<String> list2 = new ArrayList<>(tf2.keySet());
		list1.retainAll(list2);

		for (String each : list1) {
			numerator = numerator + (scores1.get(each) * scores2.get(each));
		}

		return (float) (numerator / denominator);

	}

	/**
	 * Function to calculate the frequency of the ngrams and normalize the scores
	 * 
	 * @param str
	 * @return Map of ngram and count
	 */
	private Map<String, Double> getNgramCount(List<String> str) {
		Map<String, Double> hp = new HashMap<>();

		for (String each : str) {
			if (hp.containsKey(each)) {
				hp.put(each, hp.get(each) + 1.0);
			} else
				hp.put(each, 1.0);
		}
		double len = 0;
		for (Map.Entry<String, Double> entry : hp.entrySet()) {
			len = len + entry.getValue();
		}
		if (len == 0.0)
			len = 1.0;
		for (Map.Entry<String, Double> entry : hp.entrySet()) {
			hp.put(entry.getKey(), entry.getValue() / len);

		}
		return hp;
	}

	/**
	 * Functions that returns a list of Ngrams for a given String
	 * 
	 * @param s
	 * @return List of ngrams
	 */
	private List<String> generateNGrams(String s) {
		String[] line = s.split("\\s+");
		List<String> ngram = new ArrayList<>();
		for (int i = 0; i < line.length - 3; i++) {
			ngram.add(line[i] + " " + line[i + 1] + " " + line[i + 2]);
		}

		return ngram;
	}

	@Override
	public void save(Files f) {		
		fileRepo.save(f);
		
	}
	@Override
	public float retrieveScore(String f1, String f2) {
		Files f = fileRepo.getFileDetails(f1, f2);
		return f.getScore();
	}

}
