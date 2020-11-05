package ru.appline.logic;

public class Compass {

    private final Site[] sites = new Site[8];

    Compass() {
        setPositionsSites(0);
    }

    public void setPositionsSites(int northPositions) {

        northPositions = northPositions < 0 ? (360 + northPositions) : northPositions;

        for (int i=0; i < SitesOfTheWorld.values().length; i++) {
            if (i == 0) sites[i] = new Site(SitesOfTheWorld.values()[i].toString(),northPositions % 360);
            else sites[i] = new Site(SitesOfTheWorld.values()[i].toString(), (sites[i-1].getPosition()+45) % 360);
        }
    }

    public Site getSiteByDegree(double degree) {

        degree = degree < 0 ? (360 + degree) : degree;

        double delta = (double) 360/(sites.length*2);

        for (Site a : sites) {
            double left = a.getPosition()-delta;
            double right = a.getPosition() + delta;
            if ((degree <= right) & (degree > left)) return a;
        }
        return sites[0];
    }

    private class Site {

        private String name;
        private int position;

        Site(String name, int position) {
            this.name = name;
            this.position = position;
        }

        public String getName() {
            return name;
        }

        public int getPosition() {
            return position;
        }
    }
}
