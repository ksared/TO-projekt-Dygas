package com.company;

import com.company.domain.Klient;
import com.company.domain.Naprawa;
import com.company.domain.Pracownik;
import com.company.domain.Samochod;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ExampleForeignKey {
    public void makeExample() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bazadanych");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        Pracownik pracownik1 = new Pracownik();
        Pracownik pracownik2 = new Pracownik();

        Naprawa naprawa1 = new Naprawa();
        Naprawa naprawa2 = new Naprawa();
        Naprawa naprawa3 = new Naprawa();

        Samochod samochod1 = new Samochod();
        Samochod samochod2 = new Samochod();
        Samochod samochod3 = new Samochod();

        Klient klient1 = new Klient();
        Klient klient2 = new Klient();


        klient1.setImie("Adam");
        klient1.setNazwisko("Adamski");
        klient1.setData_urodzenia(java.sql.Date.valueOf("1995-06-12"));
        klient1.setPunkty(60);


        klient2.setImie("Jakub");
        klient2.setNazwisko("Wysocki");
        klient2.setData_urodzenia(java.sql.Date.valueOf("1970-12-14"));
        klient2.setPunkty(15);

        samochod1.setMarka("BMW");
        samochod1.setModel("seria 3");
        samochod1.setRok_produkcji(2012);
        samochod1.setKlient(klient1);

        samochod2.setMarka("Fiat");
        samochod2.setModel("500");
        samochod2.setRok_produkcji(2015);
        samochod2.setKlient(klient1);

        samochod3.setMarka("Opel");
        samochod3.setModel("Astra");
        samochod3.setRok_produkcji(1997);
        samochod3.setKlient(klient2);

        pracownik1.setImie("Grzegorz");
        pracownik1.setNazwisko("Babicki");
        pracownik1.setData_urodzenia(java.sql.Date.valueOf("1984-03-24"));
        pracownik1.setPensja(4500.3589);
        pracownik1.setAdres("Armii Krajowej 5");
        pracownik1.setKod_pocztowy("27-200");

        pracownik2.setImie("Mariusz");
        pracownik2.setNazwisko("Młynarczyk");
        pracownik2.setData_urodzenia(java.sql.Date.valueOf("1992-01-04"));
        pracownik2.setPensja(4000.0);
        pracownik2.setAdres("Armii Krajowej 13");
        pracownik2.setKod_pocztowy("27-200");


        naprawa1.setData_naprawy(java.sql.Date.valueOf("2020-01-01"));
        naprawa1.setOpis_naprawy("Tarcze, klocki hamulcowe");
        naprawa1.setWartosc(900);
        naprawa1.setPracownik(pracownik1);
        naprawa1.setSamochod(samochod1);

        naprawa2.setData_naprawy(java.sql.Date.valueOf("2019-01-01"));
        naprawa2.setOpis_naprawy("Tarcze, klocki hamulcowe");
        naprawa2.setWartosc(700);
        naprawa2.setPracownik(pracownik1);
        naprawa2.setSamochod(samochod2);

        naprawa3.setData_naprawy(java.sql.Date.valueOf("2019-02-03"));
        naprawa3.setOpis_naprawy("Zawieszenie");
        naprawa3.setWartosc(1100);
        naprawa3.setPracownik(pracownik2);
        naprawa3.setSamochod(samochod3);


        entityManager.getTransaction().begin();
        entityManager.persist(klient1);
        entityManager.persist(klient2);

        entityManager.persist(pracownik1);
        entityManager.persist(pracownik2);

        entityManager.persist(samochod1);
        entityManager.persist(samochod2);
        entityManager.persist(samochod3);

        entityManager.persist(naprawa1);
        entityManager.persist(naprawa2);
        entityManager.persist(naprawa3);

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    public void findNaprawa(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bazadanych");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Naprawa naprawa = entityManager.find(Naprawa.class, 8);
        System.out.println("Dane naprawy o ID równym 8: \nOpis naprawy" + naprawa.getOpis_naprawy()
                + "\nNazwisko pracownika: " + naprawa.getPracownik().getNazwisko() + "\nSamochod: " + naprawa.getSamochod().getMarka()
                + " " + naprawa.getSamochod().getModel());

        entityManager.close();
        entityManagerFactory.close();
    }

    public void findSamochod(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bazadanych");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Samochod samochod = entityManager.find(Samochod.class, 5);
        System.out.println("Dane samochodzie o ID równym 5: "  + samochod.getModel() +
                "\nDane o właścicielu: " + samochod.getKlient().getNazwisko());

        entityManager.close();
        entityManagerFactory.close();
    }


}
