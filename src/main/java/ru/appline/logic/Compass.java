package ru.appline.logic;

import java.io.Serializable;

/**
 * Класс компаса. имплементация Serializable спорна
 */
public class Compass implements Serializable {

    private final Site[] sites = new Site[8];

    public Compass() {
        setPositionsSites(0);
    }

    /**
     * метод устанавливает позиции сторон света относительно тригонометрической окнружности
     * @param northPositions - нуль-позиция для Севера
     */
    public void setPositionsSites(int northPositions) {

        northPositions = northPositions % 360;
        northPositions = northPositions < 0 ? (360 + northPositions) : northPositions;

        for (int i = 0; i < SitesOfTheWorld.values().length; i++) {
            if (i == 0) sites[i] = new Site(SitesOfTheWorld.values()[i].toString(), northPositions);
            else sites[i] = new Site(SitesOfTheWorld.values()[i].toString(), (sites[i - 1].getPosition() + 45) % 360);
        }
    }

    /**
     * Возвращает имя стороны светы по указанному параметру
     * @param degree - параметр/градус
     * @return имя стороны света
     */
    public String getSiteByDegree(double degree) {

        degree = degree % 360;
        degree = degree < 0 ? (360 + degree) : degree;

        double delta = (double) 360 / (sites.length * 2);

        for (Site a : sites) {
            double left = a.getPosition() - delta;
            double right = a.getPosition() + delta;
            if ((degree <= right) & (degree > left)) return a.getName();
        }
        return sites[0].getName();
    }

    /**
     * @return Возвращает массив сторон света (указанные по заданию)
     */
    public Site[] getSites() {
        return sites;
    }

    /**
     * Вложенный енум предложенных сторон света, всего 8
     */
    private enum SitesOfTheWorld {
        NORTH,
        NORTH_EAST,
        EAST,
        SOUTH_EAST,
        SOUTH,
        SOUTH_WEST,
        WEST,
        NORTH_WEST;
    }
}
