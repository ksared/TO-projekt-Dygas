package com.company.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Naprawy")
public class Naprawa {
    @Id
    @GeneratedValue
    private int Naprawa_id;
    @Column(columnDefinition = "VARCHAR(255)")
    private String opis_naprawy;
    @Column(nullable = false, columnDefinition = "Decimal(10,2)")
    private double wartosc;
    private java.sql.Date data_naprawy;
    @ManyToOne
    @JoinColumn(name = "pracownik_id")
    private Pracownik pracownik;

    @ManyToOne
    @JoinColumn(name = "samochod_id")
    private Samochod samochod;

    public Samochod getSamochod() {
        return samochod;
    }

    public void setSamochod(Samochod samochod) {
        this.samochod = samochod;
    }

    public Pracownik getPracownik() {
        return pracownik;
    }

    public void setPracownik(Pracownik pracownik) {
        this.pracownik = pracownik;
    }



    //Gettery i settery

    public int getNaprawa_id() {
        return Naprawa_id;
    }


    public void setNaprawa_id(int naprawa_id) {
        Naprawa_id = naprawa_id;
    }

    public String getOpis_naprawy() {
        return opis_naprawy;
    }

    public void setOpis_naprawy(String opis_naprawy) {
        this.opis_naprawy = opis_naprawy;
    }

    public double getWartosc() {
        return wartosc;
    }

    public void setWartosc(double wartosc) {
        this.wartosc = wartosc;
    }

    public Date getData_naprawy() {
        return data_naprawy;
    }

    public void setData_naprawy(Date data_naprawy) {
        this.data_naprawy = data_naprawy;
    }


}
