package com.phrases.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.common.collect.TreeMultimap;
import com.phrases.service.repository.PhraseRepository;

/**
 * Service class which interacts with the repository to find matching phrases.
 * 
 * @author Prakash Jha
 *
 */
@Service
public class PhraseService {

	private final PhraseRepository phraseRepository;

	/**
	 * Constructor method. It takes the PhraseRepository as the argument.
	 * 
	 * @param phraseRepository
	 */
	@Autowired
	public PhraseService(final PhraseRepository phraseRepository) {
		this.phraseRepository = phraseRepository;
	}

	/**
	 * Method which takes an input string and returns collection of matching
	 * phrases from the phrase dictionary. It returns the matching phrases in
	 * order of their appearance in the input String.
	 * 
	 * It return an empty collection if the input is empty or if it doesn't find
	 * any matching phrases.
	 * 
	 * @param input
	 *            String
	 * @return Collection<String> matching phrases in the form of Collection of
	 *         Strings
	 */
	public Collection<String> findPhrases(String input) {
		TreeMultimap<Integer, String> matchedPhraseMap = TreeMultimap.create();
		if (!StringUtils.isEmpty(input)) {
			for (String phrase : phraseRepository.getPhraseDictionary()) {
				int matchedPosition = input.indexOf(phrase);
				if (matchedPosition != -1) {
					matchedPhraseMap.put(matchedPosition, phrase);
				}
			}
		}
		return matchedPhraseMap.values();
	}

}
