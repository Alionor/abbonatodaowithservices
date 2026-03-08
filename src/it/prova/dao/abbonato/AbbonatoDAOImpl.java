package it.prova.dao.abbonato;

import it.prova.dao.AbstractMySQLDAO;
import it.prova.model.Abbonato;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AbbonatoDAOImpl extends AbstractMySQLDAO implements AbbonatoDAO {

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Abbonato> list() throws Exception {
        if (isNotActive())
            throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

        ArrayList<Abbonato> result = new ArrayList<Abbonato>();

        try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from abbonato")) {

            while (rs.next()) {
                Abbonato abbonato = new Abbonato();
                abbonato.setNome(rs.getString("nome"));
                abbonato.setCognome(rs.getString("cognome"));
                abbonato.setImportomensile(rs.getInt("importomensile"));
                abbonato.setDataDiNascita(rs.getDate("datadinascita"));
                abbonato.setDataStipula(rs.getDate("datastipula"));
                abbonato.setDataCessazione(rs.getDate("datacessazione"));
                abbonato.setId(rs.getLong("id"));
                result.add(abbonato);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    @Override
    public Abbonato findById(Long idInput) throws Exception {
        if (isNotActive())
            throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

        if (idInput == null || idInput < 1)
            throw new Exception("Valore di input non ammesso.");

        Abbonato abbonato = null;
        try (PreparedStatement ps = connection.prepareStatement("select * from abbonato where id=?")) {
            ps.setLong(1, idInput);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    abbonato = new Abbonato();
                    abbonato.setNome(rs.getString("nome"));
                    abbonato.setCognome(rs.getString("cognome"));
                    abbonato.setImportomensile(rs.getInt("importomensile"));
                    abbonato.setDataDiNascita(rs.getDate("datadinascita"));
                    abbonato.setDataStipula(rs.getDate("datastipula"));
                    abbonato.setDataCessazione(rs.getDate("datacessazione"));
                    abbonato.setId(rs.getLong("id"));
                } else {
                    abbonato = null;
                }
            } // niente catch qui

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return abbonato;
    }

    @Override
    public int insert(Abbonato abbonatoInput) throws Exception {
        if (isNotActive())
            throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

        if (abbonatoInput == null)
            throw new Exception("Valore di input non ammesso.");

        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO abbonato (nome, cognome, importomensile, datadinascita, datastipula, datacessazione) VALUES (?, ?, ?, ?, ?, ?);")) {
            ps.setString(1, abbonatoInput.getNome());
            ps.setString(2, abbonatoInput.getCognome());
            ps.setInt(3, abbonatoInput.getImportomensile());
            ps.setDate(4, abbonatoInput.getDataDiNascita() != null ? new java.sql.Date(abbonatoInput.getDataDiNascita().getTime()) : null);
            ps.setDate(5, abbonatoInput.getDataStipula() != null ? new java.sql.Date(abbonatoInput.getDataStipula().getTime()) : null);
            ps.setDate(6, abbonatoInput.getDataCessazione() != null ? new java.sql.Date(abbonatoInput.getDataCessazione().getTime()) : null);
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    @Override
    public int update(Abbonato abbonatoInput) throws Exception {
        if (isNotActive())
            throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

        if (abbonatoInput == null || abbonatoInput.getId() == null || abbonatoInput.getId() < 1)
            throw new Exception("Valore di input non ammesso.");

        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE abbonato SET nome=?, cognome=?, importomensile=?, datadinascita=?, datastipula=?, datacessazione=? where id=?;")) {
            ps.setString(1, abbonatoInput.getNome());
            ps.setString(2, abbonatoInput.getCognome());
            ps.setInt(3, abbonatoInput.getImportomensile());
            ps.setDate(4, abbonatoInput.getDataDiNascita() != null ? new java.sql.Date(abbonatoInput.getDataDiNascita().getTime()) : null);
            ps.setDate(5, abbonatoInput.getDataStipula() != null ? new java.sql.Date(abbonatoInput.getDataStipula().getTime()) : null);
            ps.setDate(6, abbonatoInput.getDataCessazione() != null ? new java.sql.Date(abbonatoInput.getDataCessazione().getTime()) : null);
            ps.setLong(7, abbonatoInput.getId());
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    @Override
    public int delete(Long idDaRimuovere) throws Exception {
        if (isNotActive())
            throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

        if (idDaRimuovere == null || idDaRimuovere < 1)
            throw new Exception("Valore di input non ammesso.");

        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM abbonato WHERE id=?")) {
            ps.setLong(1, idDaRimuovere);
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    @Override
    public Abbonato findActiveWhoPaysMore() throws Exception {
        if (isNotActive())
            throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

        Abbonato abbonato = null;
        try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from abbonato where datacessazione is null order by importomensile desc limit 1;")) {

            if (rs.next()) {
                abbonato = new Abbonato();
                abbonato.setNome(rs.getString("nome"));
                abbonato.setCognome(rs.getString("cognome"));
                abbonato.setImportomensile(rs.getInt("importomensile"));
                abbonato.setDataDiNascita(rs.getDate("datadinascita"));
                abbonato.setDataStipula(rs.getDate("datastipula"));
                abbonato.setDataCessazione(rs.getDate("datacessazione"));
                abbonato.setId(rs.getLong("id"));
            } else {
                abbonato = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return abbonato;
    }

    public List<Abbonato> findActiveInADateRange(java.util.Date dataInizio, java.util.Date dataFine) throws Exception {
        if (isNotActive())
            throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

        if (dataInizio == null || dataFine == null || dataInizio.getTime() > dataFine.getTime())
            throw new Exception("Input inserito non valido.");

        List<Abbonato> abbonati = new ArrayList<>();
        Abbonato abbonato = null;
        try (PreparedStatement ps = connection.prepareStatement("select * from abbonato where datastipula <= ? and (datacessazione >= ? || datacessazione is null);")) {
            ps.setDate(1, new java.sql.Date(dataInizio.getTime()));
            ps.setDate(2, new java.sql.Date(dataFine.getTime()));

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    abbonato = new Abbonato();
                    abbonato.setNome(rs.getString("nome"));
                    abbonato.setCognome(rs.getString("cognome"));
                    abbonato.setImportomensile(rs.getInt("importomensile"));
                    abbonato.setDataDiNascita(rs.getDate("datadinascita"));
                    abbonato.setDataStipula(rs.getDate("datastipula"));
                    abbonato.setDataCessazione(rs.getDate("datacessazione"));
                    abbonato.setId(rs.getLong("id"));
                    abbonati.add(abbonato);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return abbonati;
    }

    public List<Abbonato> findActiveInTheLastMonths(int monthsToCheck) throws Exception {
        if (isNotActive())
            throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

        if (monthsToCheck < 0)
            throw new Exception("Input inserito non valido.");

        List<Abbonato> abbonati = new ArrayList<>();
        Abbonato abbonato = null;
        try (PreparedStatement ps = connection.prepareStatement("select distinct * from abbonato where datastipula >= date_sub(curdate(), interval ? month) and datastipula <= curdate();")) {
            ps.setInt(1, monthsToCheck);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    abbonato = new Abbonato();
                    abbonato.setNome(rs.getString("nome"));
                    abbonato.setCognome(rs.getString("cognome"));
                    abbonato.setImportomensile(rs.getInt("importomensile"));
                    abbonato.setDataDiNascita(rs.getDate("datadinascita"));
                    abbonato.setDataStipula(rs.getDate("datastipula"));
                    abbonato.setDataCessazione(rs.getDate("datacessazione"));
                    abbonato.setId(rs.getLong("id"));
                    abbonati.add(abbonato);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return abbonati;
    }

    public List<Abbonato> findBySurnameOverAgeWhoUnsubscribedAfterDate(String surname, int age, Date date) throws Exception {
        if (isNotActive())
            throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

        if (surname == null || age < 0 || date == null)
            throw new Exception("Input inserito non valido.");

        List<Abbonato> abbonati = new ArrayList<>();
        Abbonato abbonato = null;
        try (PreparedStatement ps = connection.prepareStatement("select * from abbonato where cognome = ? and datadinascita <= date_sub(curdate(), interval ? year) and datacessazione >= ?;")) {
            ps.setString(1, surname);
            ps.setInt(2, age);
            ps.setDate(3, new java.sql.Date(date.getTime()));

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    abbonato = new Abbonato();
                    abbonato.setNome(rs.getString("nome"));
                    abbonato.setCognome(rs.getString("cognome"));
                    abbonato.setImportomensile(rs.getInt("importomensile"));
                    abbonato.setDataDiNascita(rs.getDate("datadinascita"));
                    abbonato.setDataStipula(rs.getDate("datastipula"));
                    abbonato.setDataCessazione(rs.getDate("datacessazione"));
                    abbonato.setId(rs.getLong("id"));
                    abbonati.add(abbonato);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return abbonati;
    }


}
