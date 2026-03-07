package it.prova.model;

import java.util.Date;

public class Abbonato {

    private Long id;
    private String nome;
    private String cognome;
    private int importomensile;
    private Date dataDiNascita;
    private Date dataStipula;
    private Date dataCessazione;

    public Abbonato(Long id, String nome, String cognome, int importomensile, Date dataDiNascita, Date dataStipula, Date dataCessazione) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.importomensile = importomensile;
        this.dataDiNascita = dataDiNascita;
        this.dataStipula = dataStipula;
        this.dataCessazione = dataCessazione;
    }

    public Abbonato(String nome, String cognome, int importomensile, Date dataDiNascita, Date dataStipula, Date dataCessazione) {
        this.nome = nome;
        this.cognome = cognome;
        this.importomensile = importomensile;
        this.dataDiNascita = dataDiNascita;
        this.dataStipula = dataStipula;
        this.dataCessazione = dataCessazione;
    }

    public Abbonato() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getImportomensile() {
        return importomensile;
    }

    public void setImportomensile(int importomensile) {
        this.importomensile = importomensile;
    }

    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public Date getDataStipula() {
        return dataStipula;
    }

    public void setDataStipula(Date dataStipula) {
        this.dataStipula = dataStipula;
    }

    public Date getDataCessazione() {
        return dataCessazione;
    }

    public void setDataCessazione(Date dataCessazione) {
        this.dataCessazione = dataCessazione;
    }

    @Override
    public String toString() {
        return "Abbonato{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", importomensile=" + importomensile +
                ", dataDiNascita=" + dataDiNascita +
                ", dataStipula=" + dataStipula +
                ", dataCessazione=" + dataCessazione +
                '}';
    }

}
