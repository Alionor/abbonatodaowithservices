package it.prova.test;

import it.prova.model.Abbonato;
import it.prova.service.abbonato.AbbonatoService;
import it.prova.service.MyServiceFactory;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestAbbonato {

    static void main(String[] args) throws Exception {

        AbbonatoService abbonatoService = MyServiceFactory.getAbbonatoServiceImpl();

    //    testTrovaTutti(abbonatoService);
    //    testTrovaPerId(abbonatoService);
    //    testInserisciAbbonato(abbonatoService);
    //    testModificaAbbonato(abbonatoService);
    //    testCancellaAbbonato(abbonatoService);


    }

    public static void testTrovaTutti(AbbonatoService abbonatoService) throws Exception {
        System.out.println(".......testTrovaTutti inizio.............");

        List<Abbonato> abbonatiTrovati = abbonatoService.trovaTutti();

        if (abbonatiTrovati == null)
            throw new RuntimeException("testTrovaTutti FAILED ");

        System.out.println(abbonatiTrovati.size() + " abbonati presenti.");
        System.out.println("Abbonati presenti: " + abbonatiTrovati);
        System.out.println(".......testTrovaTutti PASSED.............");
    }

    public static void testTrovaPerId(AbbonatoService abbonatoService) throws Exception {
        System.out.println(".......testTrovaPerId inizio.............");

        List<Abbonato> abbonatiTrovati = abbonatoService.trovaTutti();
        if (abbonatiTrovati == null)
            throw new RuntimeException("testTrovaPerId FAILED ");

        System.out.println("Abbonato trovato: " + abbonatoService.trovaPerId(1L));

        System.out.println(abbonatiTrovati.size() + " abbonati presenti.");
        System.out.println("Abbonati presenti: " + abbonatiTrovati);
        System.out.println(".......testTrovaPerId PASSED.............");
    }

    public static void testInserisciAbbonato(AbbonatoService abbonatoService) throws Exception {
        System.out.println(".......testInserisciAbbonato inizio.............");

        List<Abbonato> abbonatiTrovati = abbonatoService.trovaTutti();
        if (abbonatiTrovati == null)
            throw new RuntimeException("testInserisciAbbonato FAILED ");
        System.out.println(abbonatiTrovati.size() + " abbonati presenti prima inserimento.");

        Abbonato abbonato = new Abbonato("Arturo", "Ronin", 30, Date.valueOf("2001-12-05"), Date.valueOf("2020-03-03"), null);
        System.out.println("Abbonato inserito: " + abbonato);

        int result = abbonatoService.inserisciAbbonato(abbonato);
        if (result != 1) throw new RuntimeException("testInserisciAbbonato FAILED ");

        abbonatiTrovati = abbonatoService.trovaTutti();
        System.out.println(abbonatiTrovati.size() + " abbonati presenti dopo inserimento.");
        System.out.println("Abbonato inserito: " + abbonatiTrovati);
        System.out.println(".......testInserisciAbbonato PASSED.............");
    }

    public static void testModificaAbbonato(AbbonatoService abbonatoService) throws Exception {
        System.out.println(".......testModificaAbbonato inizio.............");

        List<Abbonato> abbonatiTrovati = abbonatoService.trovaTutti();
        if (abbonatiTrovati == null)
            throw new RuntimeException("testModificaAbbonato FAILED ");

        abbonatoService.inserisciAbbonato(new Abbonato("Arturo", "Ronin", 30, Date.valueOf("2001-12-05"), Date.valueOf("2020-03-03"), Date.valueOf("2021-03-03")));
        abbonatiTrovati = abbonatoService.trovaTutti();
        Long id = abbonatiTrovati.get(abbonatiTrovati.size()-1).getId();
        System.out.println("Abbonato all'inizio: " + abbonatiTrovati.get(abbonatiTrovati.size()-1));
        Abbonato abbonatoModificato = new Abbonato(id, "Gennarino", "Pane", 50, Date.valueOf("2001-12-05"), Date.valueOf("2020-03-03"), null);

        int result = abbonatoService.modificaAbbonato(abbonatoModificato);

        if (result != 1) throw new RuntimeException("testModificaAbbonato FAILED ");

        abbonatiTrovati = abbonatoService.trovaTutti();
        System.out.println("Abbonato dopo la modifica: " + abbonatiTrovati.get(abbonatiTrovati.size() - 1));
        System.out.println(".......testModificaAbbonato PASSED.............");
    }

    public static void testCancellaAbbonato(AbbonatoService abbonatoService) throws Exception {
        System.out.println(".......testCancellaAbbonato inizio.............");

        List<Abbonato> abbonatiTrovati = abbonatoService.trovaTutti();
        if (abbonatiTrovati == null)
            throw new RuntimeException("testCancellaAbbonato FAILED ");

        abbonatoService.inserisciAbbonato(new Abbonato("Pollicino", "Cappero", 20, Date.valueOf("2001-12-05"), Date.valueOf("2020-03-03"), Date.valueOf("2021-03-03")));
        abbonatiTrovati = abbonatoService.trovaTutti();
        Long id = abbonatiTrovati.get(abbonatiTrovati.size()-1).getId();
        System.out.println("Ultimo abbonato prima della cancellazione: " + abbonatiTrovati.get(abbonatiTrovati.size()-1));

        int result = abbonatoService.cancellaAbbonato(id);

        if (result != 1) throw new RuntimeException("testCancellaAbbonato FAILED ");

        abbonatiTrovati = abbonatoService.trovaTutti();
        System.out.println("Ultimo abbonato dopo la cancellazione: " + abbonatiTrovati.get(abbonatiTrovati.size() - 1));
        System.out.println(".......testCancellaAbbonato PASSED.............");
    }



}
