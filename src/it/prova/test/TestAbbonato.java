package it.prova.test;

import it.prova.service.abbonato.AbbonatoService;
import it.prova.service.MyServiceFactory;

public class TestAbbonato {

    static void main(String[] args) {

        AbbonatoService abbonatoService = MyServiceFactory.getAbbonatoServiceImpl();


    }
}
