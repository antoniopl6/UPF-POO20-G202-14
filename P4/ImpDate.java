/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ojitos Rizados
 */
public class ImpDate {

    private final int day;
    private final int month;
    private final int year;

    public ImpDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
    
    public String getString(){
        return (day+"/"+month+"/"+year);
    }
}
