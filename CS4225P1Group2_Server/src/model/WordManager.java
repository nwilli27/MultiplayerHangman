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
	
	public boolean guessCharacter(String guessedCharacter)
	{
		if (!this.guessedCharacters.contains(guessedCharacter))
		{
			this.guessedCharacters.add(guessedCharacter);
		}
		else
		{
			// TODO send message back to client to guess again
		}
		
		return this.sentence.contains(guessedCharacter);
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
