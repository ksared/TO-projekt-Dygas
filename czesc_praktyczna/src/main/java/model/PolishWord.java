package model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "polskie_slowa")
public class PolishWord {
	@Id
	@GeneratedValue
	private long id;

	@Column(name = "slowo")
	private String word;

	@ManyToMany
	@JoinTable(name = "t³uamczenia", joinColumns = {
			@JoinColumn(name = "id_polskiego_slow") }, inverseJoinColumns = {
					@JoinColumn(name = "id_angielskiego_slowa") })
	private List<EnglishWord> translations;
	
	@Column(name = "data")
	private Date creationDate;

	public PolishWord() {

	}

	public PolishWord(String word, List<EnglishWord> translations) {
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

	public List<EnglishWord> getTranslations() {
		return translations;
	}

	public void setTranslations(List<EnglishWord> translations) {
		this.translations = translations;
	}

	public void addTranslation(EnglishWord englishWord) {
		this.translations.add(englishWord);
		englishWord.getTranslations().add(this);
	}

	public void removeTranslation(EnglishWord englishWord) {
		this.translations.remove(englishWord);
		englishWord.getTranslations().remove(this);
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public boolean equals(Object o) {
		if(o == this)
			return true;
		
		if (!(o instanceof PolishWord))
			return false;

		PolishWord compareWord = (PolishWord) o;

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
