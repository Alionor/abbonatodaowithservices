package it.prova.dao.abbonato;

import it.prova.model.Abbonato;

import java.util.List;

public interface AbbonatoDAO {

    public List<Abbonato> list() throws Exception;

    public Abbonato get(Long idInput) throws Exception;

    public int insert(Abbonato abbonatoInput) throws Exception;

    public int update(Abbonato abbonatoInput) throws Exception;

    public int delete(Long idDaRimuovere) throws Exception;


}
