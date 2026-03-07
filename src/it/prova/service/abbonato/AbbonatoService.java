package it.prova.service.abbonato;

import it.prova.dao.abbonato.AbbonatoDAO;
import it.prova.model.Abbonato;

import java.util.List;

public interface AbbonatoService {

    public void setAbbonatoDao(AbbonatoDAO abbonatoDao);

    public List<Abbonato> trovaTutti() throws Exception;

    public Abbonato trovaPerId(Long idInput) throws Exception;

    public int inserisciAbbonato(Abbonato abbonato) throws Exception;

    public int modificaAbbonato(Abbonato abbonato) throws Exception;

    public int cancellaAbbonato(Long idInput) throws Exception;



}
