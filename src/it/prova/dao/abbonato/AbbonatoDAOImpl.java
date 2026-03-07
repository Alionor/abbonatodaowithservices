package it.prova.dao.abbonato;

import it.prova.dao.AbstractMySQLDAO;
import it.prova.model.Abbonato;

import java.sql.*;
import java.util.ArrayList;
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
                abbonato.setId(rs.getLong("ID"));
                result.add(abbonato);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    @Override
    public Abbonato get(Long idInput) throws Exception {
        if (isNotActive())
            throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

        if (idInput == null || idInput < 1)
            throw new Exception("Valore di input non ammesso.");

        Abbonato abbonato = null;
        try (PreparedStatement ps = connection.prepareStatement("select * from abbonato where id=?")) {

            ps.setLong(1, idInput);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    abbonato.setNome(rs.getString("nome"));
                    abbonato.setCognome(rs.getString("cognome"));
                    abbonato.setImportomensile(rs.getInt("importomensile"));
                    abbonato.setDataDiNascita(rs.getDate("datadinascita"));
                    abbonato.setDataStipula(rs.getDate("datastipula"));
                    abbonato.setDataCessazione(rs.getDate("datacessazione"));
                    abbonato.setId(rs.getLong("ID"));
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
                "INSERT INTO abbonato (nome, cognome, importomensile, datadinascita, datastipula, datacessazione) VALUES (?, ?, ?, ?, ?);")) {
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

}
