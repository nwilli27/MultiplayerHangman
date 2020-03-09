package model;

import java.util.ArrayList;
import java.util.List;

public class WordManager {

	private String sentence;
	private List<String> guessedCharacters;
	private List<String> validCharacters;
	
	public WordManager() {
		this.guessedCharacters = new ArrayList<String>();
		this.validCharacters = new ArrayList<String>();
	}
	
	/**
	 * Handles guessing a character.
	 * @precondition: none
	 * @postcondition: none
	 * @param guessedCharacter the character guessed
	 * @return true if valid guess; otherwise false
	 */
	public boolean guessCharacter(String guessedCharacter) {
		
		var hasBeenGuessed = checkToAddGuess(guessedCharacter);
		var isValid = this.sentence.contains(guessedCharacter); 
		
		if (!hasBeenGuessed && isValid) {
			this.validCharacters.add(guessedCharacter);
			return true;
		}
		
		return false;
	}

	private boolean checkToAddGuess(String guessedCharacter) {
		
		if (!this.hasGuessedAlready(guessedCharacter)) {
			return this.guessedCharacters.add(guessedCharacter);
		} else {
			return false;
		}
	}
	
	public boolean isSentenceComplete() {
		
		var sentenceCharacters = this.getSentenceCharacters();
		return this.validCharacters.size() == sentenceCharacters.size();
	}
	
	public List<Character> getSentenceCharacters() {
		
		List<Character> characters = new ArrayList<Character>();
		var sentence = this.sentence.replace(" ", "");
		
		for (var currChar : sentence.toCharArray()) {
			
			if (!characters.contains(currChar)) {
				characters.add(currChar);
			}
		}
		
		return characters;
	}
	
	public boolean hasGuessedAlready(String guessedCharacter) {
		return this.guessedCharacters.contains(guessedCharacter);
	}
	
	public boolean guessSentence(String guessedSentence) {
		return this.sentence.equalsIgnoreCase(guessedSentence);
	}

	public String getSentence() {
		return this.sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public List<String> getGuessedCharacters() {
		return guessedCharacters;
	}
	
	
}
