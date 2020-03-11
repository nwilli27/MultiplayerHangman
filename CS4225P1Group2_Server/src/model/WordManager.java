package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class manages the chosen word and the guesses.
 * @author Nolan W, Carson B, Tristen R
 */
public class WordManager {

	private static final int MAX_GUESS_COUNT = 6;
	
	private String word;
	private String previousGuessedWord;
	private List<String> guessedCharacters;
	private List<String> validCharacters;
	private int invalidGuessCount;
	
	/**
	 * Constructor for the word manager.
	 * @precondition none
	 * @postcondition this.guessedCharacters.size() == 0
	 * 				 this.validCharacters.size() == 0
	 */
	public WordManager() {
		this.guessedCharacters = new ArrayList<String>();
		this.validCharacters = new ArrayList<String>();
	}
	
	/**
	 * Handles a guess. Distinguishes guess between character guess or word guess,
	 * checks if word or character is invalid, else adds to invalidGuessCount. 
	 * @precondition: none
	 * @postcondition: none
	 * @param guess the character guessed
	 */
	public void makeGuess(String guess) {
		
		if (guess.length() > 1) {
			
			this.previousGuessedWord = guess;
			var guessedWordRight = this.word.equalsIgnoreCase(guess);
			if (!guessedWordRight) {
				this.invalidGuessCount++;
			}
		} else {

			this.guessedCharacters.add(guess);
			
			if (this.word.contains(guess)) {
				this.validCharacters.add(guess);
			} else {
				this.invalidGuessCount++;
			}
		}
	}
	
	/**
	 * Returns the number of invalid guesses.
	 * @return the number of invalid guesses.
	 */
	public int getInvalidGuessCount() {
		return this.invalidGuessCount;
	}
	
	/**
	 * Returns whether the invalid guess counter equals the max.
	 * @return true = invalid guesses = max; otherwise false
	 */
	public boolean isOutOfGuesses() {
		
		return this.invalidGuessCount == MAX_GUESS_COUNT;
	}

	/**
	 * Returns the chosen word formatted with only valid characters,
	 * the rest as underscores. "B_nn_n_" for word bannana
	 * @precondition none
	 * @return the formatted word
	 */
	public String getFormattedWord() {
		
		var output = "";
		
		for (char character : this.word.toCharArray()) {
			
			if (this.validCharacters.contains(String.valueOf(character))) {
				output += character + " ";
			} else if (character == ' ') {
				output += " ";
			} else {
				output += "_ ";
			}
		}
		
		return output;
	}
	
	/**
	 * Returns whether the word is complete or not.
	 * @precondition: none
	 * @return true if valid characters form word; otherwise false;
	 */
	public boolean isWordComplete() {
		
		var wordCharacters = this.getWordCharacters();
		return this.validCharacters.size() == wordCharacters.size() || this.previousGuessedWord.equalsIgnoreCase(this.word);
	}
	
	/**
	 * Sets the word.
	 * @precondition: word != null
	 * @postcondition: this.word = word
	 * @param word to set
	 */
	public void setWord(String word) {
		
		if (word == null) {
			throw new IllegalArgumentException("Word can not be null.");
		}
		
		this.word = word;
	}
	
	private List<Character> getWordCharacters() {
		
		List<Character> characters = new ArrayList<Character>();
		var word = this.word.replace(" ", "");
		
		for (var currChar : word.toCharArray()) {
			
			if (!characters.contains(currChar)) {
				characters.add(currChar);
			}
		}
		
		return characters;
	}

	/**
	 * Gets all the guessed characters and combines them into a string
	 * @return The combined character string
	 */
	public String getCombinedGuessedCharacters() {
		
		var combined = "";
		
		for (var character : this.guessedCharacters) {
			combined += character;
		}
		
		return combined;
	}
	
}
