package it.prova.test;

import it.prova.model.Abbonato;
import it.prova.service.abbonato.AbbonatoService;
import it.prova.service.MyServiceFactory;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TestAbbonato {

    static void main(String[] args) throws Exception {

        AbbonatoService abbonatoService = MyServiceFactory.getAbbonatoServiceImpl();

        //    testTrovaTutti(abbonatoService);
        //    testTrovaPerId(abbonatoService);
        //    testInserisciAbbonato(abbonatoService);
        //    testModificaAbbonato(abbonatoService);
        //    testCancellaAbbonato(abbonatoService);
        //    testTrovaAbbonatoAttivoChePagaDiPiu(abbonatoService);
        //    testTrovaAbbonatiAttiviInRangeDate(abbonatoService);
        //    testTrovaAbbonatiAttiviNegliUltimiSeiMesi(abbonatoService);
        //    testTrovaPerCognomeOver60DisiscrittiDopo2020(abbonatoService);
              testTrovaErroreDate(abbonatoService);

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
        Long id = abbonatiTrovati.get(abbonatiTrovati.size() - 1).getId();
        System.out.println("Abbonato all'inizio: " + abbonatiTrovati.get(abbonatiTrovati.size() - 1));
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
        Long id = abbonatiTrovati.get(abbonatiTrovati.size() - 1).getId();
        System.out.println("Ultimo abbonato prima della cancellazione: " + abbonatiTrovati.get(abbonatiTrovati.size() - 1));

        int result = abbonatoService.cancellaAbbonato(id);

        if (result != 1) throw new RuntimeException("testCancellaAbbonato FAILED ");

        abbonatiTrovati = abbonatoService.trovaTutti();
        System.out.println("Ultimo abbonato dopo la cancellazione: " + abbonatiTrovati.get(abbonatiTrovati.size() - 1));
        System.out.println(".......testCancellaAbbonato PASSED.............");
    }

    public static void testTrovaAbbonatoAttivoChePagaDiPiu(AbbonatoService abbonatoService) throws Exception {
        System.out.println(".......testTrovaAbbonatoAttivoChePagaDiPiu inizio.............");

        List<Abbonato> abbonatiTrovati = abbonatoService.trovaTutti();
        if (abbonatiTrovati == null)
            throw new RuntimeException("testTrovaAbbonatoAttivoChePagaDiPiu FAILED ");

        Abbonato result = abbonatoService.trovaAbbonatoAttivoChePagaDiPiu();

        if (result == null) throw new RuntimeException("testTrovaAbbonatoAttivoChePagaDiPiu FAILED ");

        System.out.println("Abbonato attivo che paga di più: " + result);
        System.out.println(".......testTrovaAbbonatoAttivoChePagaDiPiu PASSED.............");
    }

    public static void testTrovaAbbonatiAttiviInRangeDate(AbbonatoService abbonatoService) throws Exception {
        System.out.println(".......testTrovaAbbonatiAttiviInRangeDate inizio.............");

        List<Abbonato> abbonatiTrovati = abbonatoService.trovaTutti();
        if (abbonatiTrovati == null)
            throw new RuntimeException("testTrovaAbbonatiAttiviInRangeDate FAILED ");

        java.util.Date dataInizio = Date.valueOf("2023-05-01");
        java.util.Date dataFine = Date.valueOf("2024-01-01");

        List<Abbonato> controprova = new ArrayList<>();
        for (Abbonato abb : abbonatiTrovati) {
            if (abb.getDataStipula() != null && abb.getDataStipula().getTime() <= dataInizio.getTime() &&
                    (abb.getDataCessazione() == null || abb.getDataCessazione().getTime() >= dataFine.getTime())) {
                controprova.add(abb);
            }
        }

        List<Abbonato> result = abbonatoService.trovaAbbonatiAttiviInRangeDate(dataInizio, dataFine);

        if (result == null) throw new RuntimeException("testTrovaAbbonatiAttiviInRangeDate FAILED ");

        System.out.println("Abbonati attivi in quel range, controprova: " + controprova.size() + "      " + controprova);
        System.out.println("Abbonati attivi nel range di date inserito: " + result.size() + "      " + result);
        System.out.println(".......testTrovaAbbonatiAttiviInRangeDate PASSED.............");
    }

    public static void testTrovaAbbonatiAttiviNegliUltimiSeiMesi(AbbonatoService abbonatoService) throws Exception {
        System.out.println(".......testTrovaAbbonatiAttiviNegliUltimiSeiMesi inizio.............");

        List<Abbonato> abbonatiTrovati = abbonatoService.trovaTutti();
        if (abbonatiTrovati == null)
            throw new RuntimeException("testTrovaAbbonatiAttiviNegliUltimiSeiMesi FAILED ");

        Date dataCorrente = Date.valueOf(LocalDate.now());
        LocalDate seiMesiFa = LocalDate.now().minusMonths(6);
        Date data = Date.valueOf(seiMesiFa);
        List<Abbonato> controprova = new ArrayList<>();
        for (Abbonato abb : abbonatiTrovati) {
            if (abb.getDataStipula() != null && abb.getDataStipula().getTime() >= data.getTime() &&
                    abb.getDataStipula().getTime() <= dataCorrente.getTime()) {
                controprova.add(abb);
            }
        }

        List<Abbonato> result = abbonatoService.trovaAbbonatiAttiviNegliUltimiSeiMesi();

        if (result == null) throw new RuntimeException("testTrovaAbbonatiAttiviNegliUltimiSeiMesi FAILED ");

        System.out.println("Abbonati attivi in range, controprova: " + controprova.size() + "      " + controprova);
        System.out.println("Abbonati attivi negli ultimi 6 mesi  : " + result.size() + "      " + result);
        System.out.println(".......testTrovaAbbonatiAttiviNegliUltimiSeiMesi PASSED.............");
    }

    public static void testTrovaPerCognomeOver60DisiscrittiDopo2020(AbbonatoService abbonatoService) throws Exception {
        System.out.println(".......testTrovaPerCognomeOver60DisiscrittiDopo2020 inizio.............");

        List<Abbonato> abbonatiTrovati = abbonatoService.trovaTutti();
        if (abbonatiTrovati == null)
            throw new RuntimeException("testTrovaPerCognomeOver60DisiscrittiDopo2020 FAILED ");

        Date data = Date.valueOf("2020-01-01");
        LocalDate dataNascitaOver60 = LocalDate.now().minusYears(60);
        Date over60 = Date.valueOf(dataNascitaOver60);
        List<Abbonato> controprova = new ArrayList<>();

        for (Abbonato abb : abbonatiTrovati) {
            if (abb.getCognome().equals("Polli") && abb.getDataDiNascita() != null && abb.getDataCessazione() != null
                    && abb.getDataDiNascita().getTime() <= over60.getTime() &&
                    abb.getDataCessazione().getTime() >= data.getTime()) {
                controprova.add(abb);
            }
        }

        List<Abbonato> result = abbonatoService.trovaPerCognomeOver60DisiscrittiDopo2020("Polli");

        if (result == null) throw new RuntimeException("testTrovaPerCognomeOver60DisiscrittiDopo2020 FAILED ");

        System.out.println("Abbonati attivi over60, controprova: " + controprova.size() + "      " + controprova);
        System.out.println("Abbonati attivi over 60 dopo 2020  : " + result.size() + "      " + result);
        System.out.println(".......testTrovaPerCognomeOver60DisiscrittiDopo2020 PASSED.............");
    }

    public static void testTrovaErroreDate(AbbonatoService abbonatoService) throws Exception {
        System.out.println(".......testTrovaErroreDate inizio.............");

        List<Abbonato> abbonatiTrovati = abbonatoService.trovaTutti();
        if (abbonatiTrovati == null)
            throw new RuntimeException("testTrovaErroreDate FAILED ");

        List<Abbonato> controprova = new ArrayList<>();

        for (Abbonato abb : abbonatiTrovati) {
            if (abb.getDataStipula() != null && abb.getDataCessazione() != null
                    && abb.getDataStipula().getTime() > abb.getDataCessazione().getTime()) {
                controprova.add(abb);
            }
        }

        List<Abbonato> result = abbonatoService.trovaErroreDate();

        if (result == null) throw new RuntimeException("testTrovaErroreDate FAILED ");

        System.out.println("Abbonati attivi over60, controprova: " + controprova.size() + "      " + controprova);
        System.out.println("Abbonati attivi over 60 dopo 2020  : " + result.size() + "      " + result);
        System.out.println(".......testTrovaErroreDate PASSED.............");
    }


}
