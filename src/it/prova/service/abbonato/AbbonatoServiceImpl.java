package it.prova.service.abbonato;

import it.prova.dao.abbonato.AbbonatoDAO;

public class AbbonatoServiceImpl implements AbbonatoService {

    private AbbonatoDAO abbonatoDao;

    public void setAbbonatoDao(AbbonatoDAO abbonatoDao) {
        this.abbonatoDao = abbonatoDao;
    }


}
