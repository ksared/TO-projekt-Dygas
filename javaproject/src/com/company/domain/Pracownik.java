package com.company.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Pracownicy")
@SecondaryTable(name = "Adresy", pkJoinColumns = @PrimaryKeyJoinColumn(name = "pracownik_id"))
public class Pracownik {
    @Id
    @GeneratedValue
    private int pracownik_id;
    @Column(columnDefinition = "VARCHAR(50) NOT NULL")
    private String imie;
    @Column(columnDefinition = "VARCHAR(60) NOT NULL")
    private String nazwisko;
    private java.sql.Date data_urodzenia;
    @Column(name = "zarobki", columnDefinition = "Decimal(10,2)")
    private double pensja;

    @Column(table = "Adresy", columnDefinition = "VARCHAR(6) NOT NULL")
    private String kod_pocztowy;
    @Column(table = "Adresy", columnDefinition = "VARCHAR(80)")
    private String adres;
    @OneToMany(mappedBy = "pracownik")
    private List<Naprawa> naprawy;



    public String getKod_pocztowy() {
        return kod_pocztowy;
    }

    public void setKod_pocztowy(String kod_pocztowy) {
        this.kod_pocztowy = kod_pocztowy;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }


    public Date getData_urodzenia() {
        return data_urodzenia;
    }

    public void setData_urodzenia(Date data_urodzenia) {
        this.data_urodzenia = data_urodzenia;
    }

    public void setPracownik_id(int pracownik_id) {
        this.pracownik_id = pracownik_id;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }


    public void setPensja(double pensja) {
        this.pensja = pensja;
    }

    public int getPracownik_id() {
        return pracownik_id;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }


    public double getPensja() {
        return pensja;
    }
}
