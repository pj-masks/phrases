package com.phrases;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phrases.model.Input;
import com.phrases.service.PhraseService;

@RestController
public class PhraseController {

	private final PhraseService phraseService;

	@Autowired
	public PhraseController(final PhraseService phraseService) {
		this.phraseService = phraseService;
	}

	@RequestMapping(value = "/phrase-matcher", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Collection<String> getPhrases(@RequestBody Input input) {
		return phraseService.findPhrases(input.getUri());
	}
}
