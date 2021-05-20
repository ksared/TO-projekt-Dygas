package com.company.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "klienci")
public class Klient {
    @Id
    @GeneratedValue
    private int klient_id;
    @Column(columnDefinition = "VARCHAR(50) NOT NULL")
    private String imie;
    @Column(columnDefinition = "VARCHAR(60) NOT NULL")
    private String nazwisko;
    private java.sql.Date data_urodzenia;
    private int punkty;

    @OneToMany(mappedBy = "klient")
    private List<Samochod> samochody;

    public int getKlient_id() {
        return klient_id;
    }

    public void setKlient_id(int klient_id) {
        this.klient_id = klient_id;
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

    public int getPunkty() {
        return punkty;
    }

    public void setPunkty(int punkty) {
        this.punkty = punkty;
    }

    public List<Samochod> getSamochody() {
        return samochody;
    }

    public void setSamochody(List<Samochod> samochody) {
        this.samochody = samochody;
    }
}
