package com.company.TableMappingClasses;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "PracownicyTM")
public class PracownikTM {
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
    @ManyToMany(mappedBy = "pracownicy")
    List<NaprawaTM> naprawy;


    public PracownikTM(){

    }
    public PracownikTM(String imie, String nazwisko, Date data_urodzenia, double pensja) {
        super();
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.data_urodzenia = data_urodzenia;
        this.pensja = pensja;
    }



    public int getPracownik_id() {
        return pracownik_id;
    }

    public void setPracownik_id(int pracownik_id) {
        this.pracownik_id = pracownik_id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Date getData_urodzenia() {
        return data_urodzenia;
    }

    public void setData_urodzenia(Date data_urodzenia) {
        this.data_urodzenia = data_urodzenia;
    }

    public double getPensja() {
        return pensja;
    }

    public void setPensja(double pensja) {
        this.pensja = pensja;
    }

    public List<NaprawaTM> getNaprawy() {
        return naprawy;
    }

    public void setNaprawy(List<NaprawaTM> naprawy) {
        this.naprawy = naprawy;
    }
}
