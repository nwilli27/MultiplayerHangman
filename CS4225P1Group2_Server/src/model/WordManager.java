package model;

import java.util.ArrayList;
import java.util.List;

public class WordManager {

	private String sentence;
	private List<Character> guessedCharacters;
	
	public WordManager()
	{
		this.guessedCharacters = new ArrayList<Character>();
	}
	
	public boolean guessCharacter(char guessedCharacter)
	{
		if (!this.guessedCharacters.contains(guessedCharacter))
		{
			this.guessedCharacters.add(guessedCharacter);
		}
		else
		{
			// TODO send message back to client to guess again
		}
		
		return this.isCharacterInSentence(guessedCharacter);
	}
	
	private boolean isCharacterInSentence(char character)
	{
		var charIndex = this.sentence.indexOf(character);
		
		if (charIndex >= 0)
		{
			return true;
		}
		
		return false;
	}

	public String getSentence() {
		return this.sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public List<Character> getGuessedCharacters() {
		return guessedCharacters;
	}
	
	
}
