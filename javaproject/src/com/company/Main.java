package com.company;

import com.company.domain.Klient;
import com.company.domain.Naprawa;
import com.company.domain.Pracownik;
import com.company.domain.Samochod;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        /*
        ExampleForeignKey exampleForeignKey = new ExampleForeignKey();
        //exampleForeignKey.makeExample();
        exampleForeignKey.findNaprawa();
        exampleForeignKey.findSamochod();
        */

        ExampleTableMapping exampleTableMapping = new ExampleTableMapping();
        exampleTableMapping.makeExample();


    }
}
