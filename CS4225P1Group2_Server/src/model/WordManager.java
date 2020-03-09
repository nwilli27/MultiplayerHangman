package model;

import java.util.ArrayList;
import java.util.List;

public class WordManager {

	private String sentence;
	private List<String> guessedCharacters;
	
	public WordManager()
	{
		this.guessedCharacters = new ArrayList<String>();
	}
	
	/**
	 * Handles guessing a character.
	 * @precondition: none
	 * @postcondition: none
	 * @param guessedCharacter the character guessed
	 * @return true if valid guess; otherwise false
	 */
	public boolean guessCharacter(String guessedCharacter)
	{
		if (this.hasGuessedAlready(guessedCharacter))
		{
			this.guessedCharacters.add(guessedCharacter);
		}
		
		return this.sentence.contains(guessedCharacter);
	}
	
	public boolean hasGuessedAlready(String guessedCharacter)
	{
		return this.guessedCharacters.contains(guessedCharacter);
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
