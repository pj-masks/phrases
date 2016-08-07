package com.phrases.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.common.base.Charsets;
import com.google.common.collect.FluentIterable;
import com.google.common.io.Files;
import com.phrases.service.repository.PhraseRepository;

public class PhraseServiceTest {
	@Mock
	private PhraseRepository phraseRepository;

	private PhraseService phraseService;

	@Before
	public void setupMock() throws IOException {
		MockitoAnnotations.initMocks(this);
		Collection<String> phraseDictionary = Files.readLines(new File("src/test/resources/phrases"), Charsets.UTF_8);
		when(phraseRepository.getPhraseDictionary()).thenReturn(phraseDictionary);
		phraseService = new PhraseService(phraseRepository);

	}

	@Test
	public void testFindPhrasesReturnsEmptyCollectionWhenInputStringIsNull() {
		assertEquals(0, phraseService.findPhrases(null).size());
	}

	@Test
	public void testFindPhrasesReturnsEmptyCollectionWhenInputStringIsEmpty() {
		assertEquals(0, phraseService.findPhrases("").size());
	}

	@Test
	public void testFindPhrasesReturnsEmptyCollectionWhenPhraseRepositoryReturnsEmptyCollection() {
		when(phraseRepository.getPhraseDictionary()).thenReturn(new ArrayList<String>());
		String input = "i have sore throat and headache";
		assertEquals(0, phraseService.findPhrases(input).size());
	}

	@Test
	public void testFindPhrasesReturnsEmptyCollectionWhenThereAreNoMatchedPhrases() {
		String input = "abcd";
		assertEquals(0, phraseService.findPhrases(input).size());
	}

	@Test
	public void testFindPhrasesReturnsMatchedPhrasesInOrderOfTheirAppearanceInTheInputString() {
		String input = "i have sore throat and headache";
		Collection<String> matchedPhrases = phraseService.findPhrases(input);
		assertEquals(2, matchedPhrases.size());
		assertEquals("sore throat", FluentIterable.from(matchedPhrases).get(0));
		assertEquals("headache", FluentIterable.from(matchedPhrases).get(1));
	}

	@Test
	public void testFindPhrasesReturnsMultipleMatchedPhrasesAtTheSameStartLocationInInputString() {
		String input = "glioblastoma multiforme";
		assertEquals(2, phraseService.findPhrases(input).size());
	}

}
