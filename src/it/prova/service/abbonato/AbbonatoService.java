package it.prova.service.abbonato;

import it.prova.dao.abbonato.AbbonatoDAO;
import it.prova.model.Abbonato;

import java.security.spec.ECField;
import java.util.List;

public interface AbbonatoService {

    public void setAbbonatoDao(AbbonatoDAO abbonatoDao);

    public List<Abbonato> trovaTutti() throws Exception;

    public Abbonato trovaPerId(Long idInput) throws Exception;

    public int inserisciAbbonato(Abbonato abbonato) throws Exception;

    public int modificaAbbonato(Abbonato abbonato) throws Exception;

    public int cancellaAbbonato(Long idInput) throws Exception;

    public Abbonato trovaAbbonatoAttivoChePagaDiPiu() throws Exception;

    public List<Abbonato> trovaAbbonatiAttiviInRangeDate(java.util.Date dataInizio, java.util.Date dataFine) throws Exception;

    public List<Abbonato> trovaAbbonatiAttiviNegliUltimiSeiMesi() throws Exception;

}
