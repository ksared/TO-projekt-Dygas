package com.company;
import com.company.TableMappingClasses.NaprawaTM;
import com.company.TableMappingClasses.PracownikTM;
import com.company.domain.Klient;
import com.company.domain.Naprawa;
import com.company.domain.Pracownik;
import com.company.domain.Samochod;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ExampleTableMapping {

    public void makeExample(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bazadanych");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        NaprawaTM naprawa1 = new NaprawaTM("Klocki", 200, Date.valueOf("2020-04-11"));
        NaprawaTM naprawa2 = new NaprawaTM("amortyzatory", 1300, Date.valueOf("2021-05-16"));
        NaprawaTM naprawa3 = new NaprawaTM("Zmiana opon", 80, Date.valueOf("2021-04-29"));

        PracownikTM pracownik1 = new PracownikTM("Adam", "Abacki" ,java.sql.Date.valueOf("1995-06-12") ,2000);
        PracownikTM pracownik2 = new PracownikTM("Bogdan", "Bogdanski" ,java.sql.Date.valueOf("1981-02-22") ,4000);
        PracownikTM pracownik3 = new PracownikTM("Cezary", "Cezarecki" ,java.sql.Date.valueOf("1990-11-11") ,6000);

        List<PracownikTM> pracownicy = new ArrayList<PracownikTM>();
        pracownicy.add(pracownik1);
        pracownicy.add(pracownik2);



        List<PracownikTM> pracownicy2 = new ArrayList<PracownikTM>();
        pracownicy2.add(pracownik2);
        pracownicy2.add(pracownik3);

        naprawa1.setPracownicy(pracownicy);
        naprawa2.setPracownicy(pracownicy2);

        entityManager.getTransaction().begin();

        entityManager.persist(pracownik1);
        entityManager.persist(pracownik2);
        entityManager.persist(pracownik3);

        entityManager.persist(naprawa1);
        entityManager.persist(naprawa2);
        entityManager.persist(naprawa3);

        entityManager.getTransaction().commit();


        entityManager.close();
        entityManagerFactory.close();
    }

}
