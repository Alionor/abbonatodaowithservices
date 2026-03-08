package it.prova.dao.abbonato;

import it.prova.dao.IBaseDAO;
import it.prova.model.Abbonato;

import java.util.List;

public interface AbbonatoDAO extends IBaseDAO<Abbonato> {

    public Abbonato findActiveWhoPaysMore() throws Exception;

    public List<Abbonato> findActiveInADateRange(java.util.Date dataInizio, java.util.Date dataFine) throws Exception;

    public List<Abbonato> findActiveInTheLastMonths(int monthsToCheck) throws Exception;

}
