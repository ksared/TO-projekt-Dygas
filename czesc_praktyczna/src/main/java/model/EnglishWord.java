package model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "angielskie_slowa")
public class EnglishWord {
	@Id
	@GeneratedValue
	private long id;

	@Column(name = "slowo")
	private String word;

	@ManyToMany(mappedBy = "translations")
	private List<PolishWord> translations;
	
	@Column(name = "data")
	private Date creationDate;

	public EnglishWord() {

	}

	public EnglishWord(String word, List<PolishWord> translations) {
		super();
		this.word = word;
		this.translations = translations;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public List<PolishWord> getTranslations() {
		return translations;
	}

	public void setTranslations(List<PolishWord> translations) {
		this.translations = translations;
	}

	public void addTranslation(PolishWord polishWord) {
		this.translations.add(polishWord);
		polishWord.getTranslations().add(this);
	}

	public void removeTranslation(PolishWord polishWord) {
		this.translations.remove(polishWord);
		polishWord.getTranslations().remove(this);
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (!(o instanceof EnglishWord))
			return false;

		EnglishWord compareWord = (EnglishWord) o;

		if (this.word.equals(compareWord.getWord()) && this.id == compareWord.getId()
				&& this.translations.equals(compareWord.getTranslations()))
			return true;
		return false;
	}

	@Override
	public String toString() {
		return word;
	}
}
