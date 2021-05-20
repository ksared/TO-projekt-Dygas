package com.company.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Samochody")
public class Samochod {
    @Id
    @GeneratedValue
    private int samochod_id;
    @Column(columnDefinition = "VARCHAR(50) NOT NULL")
    private String marka;
    @Column(columnDefinition = "VARCHAR(60) NOT NULL")
    private String model;
    private int rok_produkcji;

    @ManyToOne
    @JoinColumn(name = "klient_id")
    private Klient klient;

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    @OneToMany(mappedBy = "samochod")
    private List<Naprawa> naprawy;

    public List<Naprawa> getNaprawy() {
        return naprawy;
    }

    public void setNaprawy(List<Naprawa> naprawy) {
        this.naprawy = naprawy;
    }



    public int getSamochod_id() {
        return samochod_id;
    }

    public void setSamochod_id(int samochod_id) {
        this.samochod_id = samochod_id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getRok_produkcji() {
        return rok_produkcji;
    }

    public void setRok_produkcji(int rok_produkcji) {
        this.rok_produkcji = rok_produkcji;
    }

}
