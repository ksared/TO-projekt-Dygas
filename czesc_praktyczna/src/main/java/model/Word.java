package model;

import java.util.List;

public class Word {
	String word;
	List<String> translations;

	public Word(String word, List<String> translations) {
		super();
		this.word = word;
		this.translations = translations;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public List<String> getTranslations() {
		return translations;
	}

	public void setTranslations(List<String> translations) {
		this.translations = translations;
	}

	@Override
	public String toString() {
		return "Word [word=" + word + ", translations=" + translations + "]";
	}

}
