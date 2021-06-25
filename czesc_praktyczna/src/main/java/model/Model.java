package model;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;
import java.util.Map.Entry;

public class Model extends Thread {
	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bazadanych");


	public static void createPolishWord(String word, List<String> translations) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		createLackingEnglishWords(translations, entityManager);
		List<EnglishWord> wordTranslations = getEnglishTranslations(translations, entityManager);

		PolishWord polishWord = new PolishWord(word, new ArrayList<EnglishWord>());
		entityManager.getTransaction().begin();
		for (EnglishWord translation : wordTranslations) {
			polishWord.addTranslation(translation);
		}
		//Date endDate = new Date(Calendar.getInstance().getTime().getTime());
		//Date date = new Date(endDate.getTime() - 10 * 24 * 3600 * 1000l );
		polishWord.setCreationDate(new Date(Calendar.getInstance().getTime().getTime()));
		entityManager.persist(polishWord);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public static void createEnglishWord(String word, List<String> translations) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		createLackingPolishWords(translations, entityManager);
		List<PolishWord> wordTranslations = getPolishTranslations(translations, entityManager);

		EnglishWord englishWord = new EnglishWord(word, new ArrayList<PolishWord>());
		entityManager.getTransaction().begin();
		for (PolishWord translation : wordTranslations) {
			englishWord.addTranslation(translation);
		}
		
		//Date endDate = new Date(Calendar.getInstance().getTime().getTime());
		//Date date = new Date(endDate.getTime() - 10 * 24 * 3600 * 1000l );
		englishWord.setCreationDate(new Date(Calendar.getInstance().getTime().getTime()));
		entityManager.persist(englishWord);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public static void updatePolishWord(String word, String newWord, List<String> translations) {
		// sprawdzenie czy newWord ju¿ jest w bazie
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		PolishWord currentWord = getPolishWord(word, entityManager);
		createLackingEnglishWords(translations, entityManager);
		List<EnglishWord> wordTranslations = getEnglishTranslations(translations, entityManager);
		entityManager.getTransaction().begin();

		currentWord.setWord(newWord);

		List<EnglishWord> list = new ArrayList<EnglishWord>(currentWord.getTranslations());
		for (EnglishWord translation : list) {
			currentWord.removeTranslation(translation);
		}

		for (EnglishWord translation : wordTranslations) {
			currentWord.addTranslation(translation);
		}

		for (EnglishWord translation : list) {
			if (translation.getTranslations().size() == 0) {
				entityManager.remove(translation);
			}
		}

		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public static void updateEnglishWord(String word, String newWord, List<String> translations) {
		// sprawdzenie czy newWord ju¿ jest w bazie
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EnglishWord currentWord = getEnglishWord(word, entityManager);
		createLackingPolishWords(translations, entityManager);
		List<PolishWord> wordTranslations = getPolishTranslations(translations, entityManager);
		entityManager.getTransaction().begin();

		currentWord.setWord(newWord);

		List<PolishWord> list = new ArrayList<PolishWord>(currentWord.getTranslations());
		for (PolishWord translation : list) {
			currentWord.removeTranslation(translation);
		}

		for (PolishWord translation : wordTranslations) {
			currentWord.addTranslation(translation);
		}

		for (PolishWord translation : list) {
			if (translation.getTranslations().size() == 0) {
				entityManager.remove(translation);
			}
		}

		entityManager.getTransaction().commit();
	}
	
	public static Word getPolishWord(String word) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		PolishWord current = getPolishWord(word, entityManager);
		if (current != null) {
			List<String> translations = new ArrayList<String>();
			for (EnglishWord translation : current.getTranslations()) {
				translations.add(translation.getWord());
			}
			entityManager.close();
			return new Word(current.getWord(), translations);
		} else {
			return null;
		}
	}
	
	public static Word getEnglishWord(String word) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EnglishWord current = getEnglishWord(word, entityManager);
		if (current != null) {
			List<String> translations = new ArrayList<String>();
			for (PolishWord translation : current.getTranslations()) {
				translations.add(translation.getWord());
			}
			entityManager.close();
			return new Word(current.getWord(), translations);
		} else {
			return null;
		}
	}
	
	public static List<Word> getAllPolishWordsString()  {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<PolishWord> list = getAllPolishWords(entityManager);
		List<Word> wordList = new ArrayList<Word>();
		for (PolishWord word : list) {
			List<String> translationList = new ArrayList<String>();
			for (EnglishWord translation : word.getTranslations()) {
				translationList.add(translation.getWord());
			}
			wordList.add(new Word(word.getWord(), translationList));
		}
		entityManager.close();
		return wordList;
	}
	
	public static List<Word> getAllEnglishWordsString() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<EnglishWord> list = getAllEnglishWords(entityManager);

		List<Word> wordList = new ArrayList<Word>();
		for (EnglishWord word : list) {
			List<String> translationList = new ArrayList<String>();
			for (PolishWord translation : word.getTranslations()) {
				translationList.add(translation.getWord());
			}
			wordList.add(new Word(word.getWord(), translationList));
		}
		entityManager.close();
		return wordList;
	}
	
	public static List<Word> getAllPolishWordsLike(String word)  {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String query = "SELECT W FROM PolishWord W WHERE W.word LIKE " + "'" + word + "%'";
		ArrayList<PolishWord> results = polishWordSearchQueryResults(query, entityManager);
		ArrayList<Word> wordList = new ArrayList<Word>();

		for (PolishWord polishWord : results) {
			List<String> translations = new ArrayList<String>();
			for (EnglishWord translation : polishWord.getTranslations()) {
				translations.add(translation.getWord());
			}
			wordList.add(new Word(polishWord.getWord(), translations));
		}
		return wordList;
	}
	
	public static List<Word> getAllEnglishWordsLike(String word) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String query = "SELECT W FROM EnglishWord W WHERE W.word LIKE " + "'" + word + "%'";
		ArrayList<EnglishWord> results = englishWordSearchQueryResults(query, entityManager);
		ArrayList<Word> wordList = new ArrayList<Word>();

		for (EnglishWord englishWord : results) {
			List<String> translations = new ArrayList<String>();
			for (PolishWord translation : englishWord.getTranslations()) {
				translations.add(translation.getWord());
			}
			wordList.add(new Word(englishWord.getWord(), translations));
		}
		return wordList;
	}
	
	public static List<Word> getAllPolishWordsFrom7Days()  {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Map<String, Object> parameterNameAndValues = new HashMap<String, Object>();
		
		Date endDate = new Date(Calendar.getInstance().getTime().getTime());
		Date startDate = new Date(endDate.getTime() - 7 * 24 * 3600 * 1000l );

		parameterNameAndValues.put("startDate", startDate);
		parameterNameAndValues.put("endDate", endDate);

		String hqlQuery = "FROM PolishWord WHERE creationDate BETWEEN :startDate AND :endDate";

		Query typedQuery = entityManager.createQuery(hqlQuery);

		for (Entry<String, Object> e : parameterNameAndValues.entrySet()) {
			typedQuery.setParameter(e.getKey(), e.getValue());
		}
		List<PolishWord>results = typedQuery.getResultList();
		ArrayList<Word> wordList = new ArrayList<Word>();

		for (PolishWord polishWord : results) {
			List<String> translations = new ArrayList<String>();
			for (EnglishWord translation : polishWord.getTranslations()) {
				translations.add(translation.getWord());
			}
			wordList.add(new Word(polishWord.getWord(), translations));
		}
		return wordList;
	}
	
	public static List<Word> getAllEnglishWordsFrom7Days()  {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Map<String, Object> parameterNameAndValues = new HashMap<String, Object>();

		
		Date endDate = new Date(Calendar.getInstance().getTime().getTime());
		Date startDate = new Date(endDate.getTime() - 7 * 24 * 3600 * 1000l );

		parameterNameAndValues.put("startDate", startDate);
		parameterNameAndValues.put("endDate", endDate);

		String hqlQuery = "FROM EnglishWord WHERE creationDate BETWEEN :startDate AND :endDate";

		Query typedQuery = entityManager.createQuery(hqlQuery);

		for (Entry<String, Object> e : parameterNameAndValues.entrySet()) {
			typedQuery.setParameter(e.getKey(), e.getValue());
		}
		List<EnglishWord>results = typedQuery.getResultList();
		ArrayList<Word> wordList = new ArrayList<Word>();

		for (EnglishWord englishWord : results) {
			List<String> translations = new ArrayList<String>();
			for (PolishWord translation : englishWord.getTranslations()) {
				translations.add(translation.getWord());
			}
			wordList.add(new Word(englishWord.getWord(), translations));
		}
		return wordList;
	}
	
	public static List<Word> getAllPolishWordsFrom30Days()  {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Map<String, Object> parameterNameAndValues = new HashMap<String, Object>();

		
		Date endDate = new Date(Calendar.getInstance().getTime().getTime());
		Date startDate = new Date(endDate.getTime() - 30 * 24 * 3600 * 1000l );

		parameterNameAndValues.put("startDate", startDate);
		parameterNameAndValues.put("endDate", endDate);

		String hqlQuery = "FROM PolishWord WHERE creationDate BETWEEN :startDate AND :endDate";

		Query typedQuery = entityManager.createQuery(hqlQuery);

		for (Entry<String, Object> e : parameterNameAndValues.entrySet()) {
			typedQuery.setParameter(e.getKey(), e.getValue());
		}
		List<PolishWord>results = typedQuery.getResultList();
		ArrayList<Word> wordList = new ArrayList<Word>();

		for (PolishWord polishWord : results) {
			List<String> translations = new ArrayList<String>();
			for (EnglishWord translation : polishWord.getTranslations()) {
				translations.add(translation.getWord());
			}
			wordList.add(new Word(polishWord.getWord(), translations));
		}
		return wordList;
	}
	
	public static List<Word> getAllEnglishWordsFrom30Days()  {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Map<String, Object> parameterNameAndValues = new HashMap<String, Object>();

		
		Date endDate = new Date(Calendar.getInstance().getTime().getTime());
		Date startDate = new Date(endDate.getTime() - 30 * 24 * 3600 * 1000l );

		parameterNameAndValues.put("startDate", startDate);
		parameterNameAndValues.put("endDate", endDate);

		String hqlQuery = "FROM EnglishWord WHERE creationDate BETWEEN :startDate AND :endDate";

		Query typedQuery = entityManager.createQuery(hqlQuery);

		for (Entry<String, Object> e : parameterNameAndValues.entrySet()) {
			typedQuery.setParameter(e.getKey(), e.getValue());
		}
		List<EnglishWord>results = typedQuery.getResultList();
		ArrayList<Word> wordList = new ArrayList<Word>();

		for (EnglishWord englishWord : results) {
			List<String> translations = new ArrayList<String>();
			for (PolishWord translation : englishWord.getTranslations()) {
				translations.add(translation.getWord());
			}
			wordList.add(new Word(englishWord.getWord(), translations));
		}
		return wordList;
	}

	public static void deletePolishWord(String word) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		PolishWord currentWord = getPolishWord(word, entityManager);
		entityManager.getTransaction().begin();

		List<EnglishWord> list = new ArrayList<EnglishWord>(currentWord.getTranslations());
		for (EnglishWord translation : list) {
			currentWord.removeTranslation(translation);
			if (translation.getTranslations().size() == 0)
				entityManager.remove(translation);
		}

		entityManager.remove(currentWord);

		entityManager.getTransaction().commit();
	}

	public static void deleteEnglishWord(String word) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EnglishWord currentWord = getEnglishWord(word, entityManager);
		entityManager.getTransaction().begin();

		List<PolishWord> list = new ArrayList<PolishWord>(currentWord.getTranslations());
		for (PolishWord translation : list) {
			currentWord.removeTranslation(translation);
			if (translation.getTranslations().size() == 0)
				entityManager.remove(translation);
		}
		entityManager.remove(currentWord);

		entityManager.getTransaction().commit();
	}
	
	

	public static List<PolishWord> getAllPolishWords() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String query = "SELECT W FROM PolishWord W";
		List<PolishWord> list = polishWordSearchQueryResults(query, entityManager);
		entityManager.close();
		return list;
	}

	public static List<EnglishWord> getAllEnglishWords() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String query = "SELECT W FROM EnglishWord W";
		List<EnglishWord> list = englishWordSearchQueryResults(query, entityManagerFactory.createEntityManager());
		entityManager.close();
		return list;
	}

	public static List<PolishWord> getAllPolishWords(EntityManager entityManager) {
		String query = "SELECT W FROM PolishWord W";
		List<PolishWord> list = polishWordSearchQueryResults(query, entityManager);
		return list;
	}

	public static List<EnglishWord> getAllEnglishWords(EntityManager entityManager) {
		String query = "SELECT W FROM EnglishWord W";
		List<EnglishWord> list = englishWordSearchQueryResults(query, entityManagerFactory.createEntityManager());
		return list;
	}

	private static List<EnglishWord> getEnglishTranslations(List<String> translations, EntityManager entityManager) {
		List<EnglishWord> list = new ArrayList<EnglishWord>();
		for (String translation : translations) {
			list.add(getEnglishWord(translation, entityManager));
		}
		return list;
	}

	private static List<PolishWord> getPolishTranslations(List<String> translations, EntityManager entityManager) {
		List<PolishWord> list = new ArrayList<PolishWord>();
		for (String translation : translations) {
			list.add(getPolishWord(translation, entityManager));
		}
		return list;
	}

	private static void createLackingEnglishWords(List<String> translations, EntityManager entityManager) {
		for (String word : translations) {
			if (getEnglishWord(word, entityManager) == null) {
				createEnglishWord(word, entityManager);
			}
		}
	}

	private static void createLackingPolishWords(List<String> translations, EntityManager entityManager) {
		for (String word : translations) {
			if (getPolishWord(word, entityManager) == null) {
				createPolishWord(word, entityManager);
			}
		}
	}

	private static void createPolishWord(String word, EntityManager entityManager) {
		PolishWord polishWord = new PolishWord(word, new ArrayList<EnglishWord>());
		entityManager.getTransaction().begin();
		//Date endDate = new Date(Calendar.getInstance().getTime().getTime());
		//Date date = new Date(Calendar.getInstance().getTime().getTime());
		polishWord.setCreationDate(new Date(Calendar.getInstance().getTime().getTime()));
		entityManager.persist(polishWord);
		entityManager.getTransaction().commit();
	}

	private static void createEnglishWord(String word, EntityManager entityManager) {
		EnglishWord englishWord = new EnglishWord(word, new ArrayList<PolishWord>());
		entityManager.getTransaction().begin();
		//Date endDate = new Date(Calendar.getInstance().getTime().getTime());
		//Date date = new Date(Calendar.getInstance().getTime().getTime());
		englishWord.setCreationDate(new Date(Calendar.getInstance().getTime().getTime()));
		entityManager.persist(englishWord);
		entityManager.getTransaction().commit();
	}

	private static PolishWord getPolishWord(String word, EntityManager entityManager) {
		String query = "SELECT W FROM PolishWord W WHERE W.word = " + "'" + word + "'";
		ArrayList<PolishWord> results = polishWordSearchQueryResults(query, entityManager);
		if (results.size() == 1) {
			return results.get(0);
		}
		return null;
	}

	private static EnglishWord getEnglishWord(String word, EntityManager entityManager) {
		String query = "SELECT W FROM EnglishWord W WHERE W.word = " + "'" + word + "'";
		ArrayList<EnglishWord> results = englishWordSearchQueryResults(query, entityManager);
		if (results.size() == 1) {
			return results.get(0);
		}
		return null;
	}


	private static ArrayList<PolishWord> polishWordSearchQueryResults(String query, EntityManager entityManager) {
		TypedQuery<PolishWord> typedQuery = entityManager.createQuery(query, PolishWord.class);
		return (ArrayList<PolishWord>) typedQuery.getResultList();
	}

	private static ArrayList<EnglishWord> englishWordSearchQueryResults(String query, EntityManager entityManager) {
		TypedQuery<EnglishWord> typedQuery = entityManager.createQuery(query, EnglishWord.class);
		return (ArrayList<EnglishWord>) typedQuery.getResultList();
	}
	
	
	public static void closeEntityManager() {
		entityManagerFactory.close();
	}
}
