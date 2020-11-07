package ru.appline.controller;

import org.springframework.web.bind.annotation.*;
import ru.appline.logic.Compass;
import ru.appline.logic.Degree;
import ru.appline.logic.Site;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {

    private Compass compass = Compass.getInstance();

    /**
     * Я считаю, что немного странно указывать диапазоны сторон света, если они являются постояннными (см. любой компас).
     * Вмсето этого в запрос передается параметр (градус на тригонометрической окружности), на который будет указывать север
     * Следовательно, остальные стороны света также смещаются на определенную величину
     * @param degree - параметр/нулевая точка северной стороны
     * @return - возвращает json с новыми позициями сторон света
     */
    @PutMapping(value = "/setNorthPosition", consumes = "application/json", produces = "application/json")
    public Site[] setCompassPositionNorth(@RequestBody Degree degree) {
        compass.setPositionsSites((int) degree.getDegree());
        return compass.getSites();
    }

    /**
     * @return возвращает json с текущими позициями сторон света
     */
    @GetMapping(value = "/getAllSites", produces = "application/json")
    public Site[] getAllSites() {
        return compass.getSites();
    }

    /**
     * Метод определяет сторону света по переданному параметру
     * @param degree - параметр/ произвольный градус на тригонометрической окружеости
     * @return json, сторона света.
     */
    @GetMapping(value = "/getSiteByDegree", consumes = "application/json", produces = "application/json")
    public Map<String, String> getSiteByDegree(@RequestBody Degree degree) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Side", compass.getSiteByDegree(degree.getDegree()));
        return map;
    }
}
