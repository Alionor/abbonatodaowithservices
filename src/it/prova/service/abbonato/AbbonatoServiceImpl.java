package it.prova.service.abbonato;

import it.prova.connection.MyConnection;
import it.prova.dao.Constants;
import it.prova.dao.abbonato.AbbonatoDAO;
import it.prova.model.Abbonato;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AbbonatoServiceImpl implements AbbonatoService {

    private AbbonatoDAO abbonatoDao;

    public void setAbbonatoDao(AbbonatoDAO abbonatoDao) {
        this.abbonatoDao = abbonatoDao;
    }

    @Override
    public List<Abbonato> trovaTutti() throws Exception {
        List<Abbonato> result = new ArrayList<>();
        try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

            // inietto la connection nel dao
            abbonatoDao.setConnection(connection);

            // eseguo quello che realmente devo fare
            result = abbonatoDao.list();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }
    @Override
    public Abbonato trovaPerId(Long idInput) throws Exception {
        Abbonato result = null;
        try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

            // inietto la connection nel dao
            abbonatoDao.setConnection(connection);

            // eseguo quello che realmente devo fare
            result = abbonatoDao.findById(idInput);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }
    @Override
    public int inserisciAbbonato(Abbonato abbonato) throws Exception {
        int result = 0;
        try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

            // inietto la connection nel dao
            abbonatoDao.setConnection(connection);

            // eseguo quello che realmente devo fare
            result = abbonatoDao.insert(abbonato);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }
    @Override
    public int modificaAbbonato(Abbonato abbonato) throws Exception {
        int result = 0;
        try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

            // inietto la connection nel dao
            abbonatoDao.setConnection(connection);

            // eseguo quello che realmente devo fare
            result = abbonatoDao.update(abbonato);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }
    @Override
    public int cancellaAbbonato(Long idInput) throws Exception {
        int result = 0;
        try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

            // inietto la connection nel dao
            abbonatoDao.setConnection(connection);

            // eseguo quello che realmente devo fare
            result = abbonatoDao.delete(idInput);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    @Override
    public Abbonato trovaAbbonatoAttivoChePagaDiPiu() throws Exception {
        Abbonato result = null;
        try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

            // inietto la connection nel dao
            abbonatoDao.setConnection(connection);

            // eseguo quello che realmente devo fare
            result = abbonatoDao.findActiveWhoPaysMore();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    @Override
    public List<Abbonato> trovaAbbonatiAttiviInRangeDate(java.util.Date dataInizio, java.util.Date dataFine) throws Exception {
        List<Abbonato> result = new ArrayList<>();
        try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

            // inietto la connection nel dao
            abbonatoDao.setConnection(connection);

            // eseguo quello che realmente devo fare
            result = abbonatoDao.findActiveInADateRange(dataInizio, dataFine);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    @Override
    public List<Abbonato> trovaAbbonatiAttiviNegliUltimiSeiMesi() throws Exception {
        List<Abbonato> result = new ArrayList<>();
        try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

            // inietto la connection nel dao
            abbonatoDao.setConnection(connection);

            // eseguo quello che realmente devo fare
            result = abbonatoDao.findActiveInTheLastMonths(6);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    @Override
    public List<Abbonato> trovaPerCognomeOver60DisiscrittiDopo2020(String surname) throws Exception {
        List<Abbonato> result = new ArrayList<>();
        try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

            // inietto la connection nel dao
            abbonatoDao.setConnection(connection);
            Date date = Date.valueOf("2020-01-01");

            // eseguo quello che realmente devo fare
            result = abbonatoDao.findBySurnameOverAgeWhoUnsubscribedAfterDate(surname, 60, date);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    @Override
    public List<Abbonato> trovaErroreDate() throws Exception {
        List<Abbonato> result = new ArrayList<>();
        try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

            // inietto la connection nel dao
            abbonatoDao.setConnection(connection);

            // eseguo quello che realmente devo fare
            result = abbonatoDao.findDateError();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

}
