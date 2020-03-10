package model;

import java.util.ArrayList;
import java.util.List;

public class WordManager {

	private static final int MAX_GUESS_COUNT = 6;
	
	private String word;
	private List<String> guessedCharacters;
	private List<String> validCharacters;
	private int invalidGuessCounter;
	
	public WordManager() {
		this.guessedCharacters = new ArrayList<String>();
		this.validCharacters = new ArrayList<String>();
	}
	
	/**
	 * Handles guessing a character.
	 * @precondition: none
	 * @postcondition: none
	 * @param guess the character guessed
	 * @return true if valid guess; otherwise false
	 */
	public void makeGuess(String guess) {
		
		if (guess.length() > 1) {
			
			var guessedWordRight = this.word.equalsIgnoreCase(guess);
			if (!guessedWordRight) {
				this.invalidGuessCounter++;
			}
		} else {

			var hasBeenGuessed = this.checkToAddGuess(guess);
			var isValid = this.word.contains(guess); 
			
			if (!hasBeenGuessed && isValid) {
				this.validCharacters.add(guess);
			} else {
				this.invalidGuessCounter++;
			}
		}
	}

	public int getInvalidGuessCounter() {
		return this.invalidGuessCounter;
	}
	
	public boolean isOutOfGuesses() {
		
		return this.invalidGuessCounter == MAX_GUESS_COUNT;
	}

	private boolean checkToAddGuess(String guessedCharacter) {
		
		if (!this.hasGuessedAlready(guessedCharacter)) {
			return this.guessedCharacters.add(guessedCharacter);
		} else {
			return false;
		}
	}
	
	public String formattedCurrentWord() {
		
		var output = "";
		
		for (char character : this.word.toCharArray()) {
			
			if (this.validCharacters.contains(String.valueOf(character))) {
				output += character;
			} else {
				output += "_";
			}
		}
		
		return output;
	}
	
	public boolean isWordComplete() {
		
		var wordCharacters = this.getWordCharacters();
		return this.validCharacters.size() == wordCharacters.size();
	}
	
	public List<Character> getWordCharacters() {
		
		List<Character> characters = new ArrayList<Character>();
		var word = this.word.replace(" ", "");
		
		for (var currChar : word.toCharArray()) {
			
			if (!characters.contains(currChar)) {
				characters.add(currChar);
			}
		}
		
		return characters;
	}
	
	public boolean hasGuessedAlready(String guessedCharacter) {
		return this.guessedCharacters.contains(guessedCharacter);
	}

	public String getWord() {
		return this.word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public List<String> getGuessedCharacters() {
		return this.guessedCharacters;
	}
	
}
