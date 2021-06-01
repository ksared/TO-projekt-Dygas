package com.company.TableMappingClasses;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "NaprawyTM")
public class NaprawaTM {

    @Id
    @GeneratedValue
    private int Naprawa_id;
    @Column(columnDefinition = "VARCHAR(255)")
    private String opis_naprawy;
    @Column(nullable = false, columnDefinition = "Decimal(10,2)")
    private double wartosc;
    private java.sql.Date data_naprawy;
    @ManyToMany()
    List<PracownikTM> pracownicy;


    public NaprawaTM(){

    }
    public NaprawaTM(String opis_naprawy, double wartosc, Date data_naprawy) {
        super();
        this.opis_naprawy = opis_naprawy;
        this.wartosc = wartosc;
        this.data_naprawy = data_naprawy;
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

    public List<PracownikTM> getPracownicy() {
        return pracownicy;
    }

    public void setPracownicy(List<PracownikTM> pracownicy) {
        this.pracownicy = pracownicy;
    }

}
