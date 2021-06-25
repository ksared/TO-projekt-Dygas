package model;

import java.util.List;

public class EditModelType {
	EditType type;
	String word;
	String newWord;
	List<String> translations;
	
	public EditModelType(EditType type) {
		super();
		this.type = type;
	}
	
	public EditModelType(EditType type, String word) {
		super();
		this.type = type;
		this.word = word;
	}
	
	public EditModelType(EditType type, String word, List<String> translations) {
		super();
		this.type = type;
		this.word = word;
		this.translations = translations;
	}
	
	public EditModelType(EditType type, String word, String newWord, List<String> translations) {
		super();
		this.type = type;
		this.word = word;
		this.newWord = newWord;
		this.translations = translations;
	}
	
	

}
