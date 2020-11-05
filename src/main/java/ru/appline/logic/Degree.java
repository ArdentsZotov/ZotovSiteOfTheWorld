package ru.appline.logic;

/**
 * Вспомогательный класс для формирования json запроса
 */
public class Degree {

    private double degree;

    public Degree() {}

    public Degree(double degree) {
        this.degree = degree;
    }

    public void setDegree(double degree) {
        this.degree = degree;
    }

    public double getDegree() {
        return degree;
    }


}
