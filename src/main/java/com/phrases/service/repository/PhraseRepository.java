package com.phrases.service.repository;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.springframework.stereotype.Repository;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

@Repository
public class PhraseRepository {

	private Collection<String> phraseDictionary;

	private final String FILE_LOCATION = "src/main/resources/phrases";

	public PhraseRepository() throws IOException {
		phraseDictionary = Files.readLines(new File(FILE_LOCATION), Charsets.UTF_8);
	}

	public Collection<String> getPhraseDictionary() {
		return phraseDictionary;
	}

}
