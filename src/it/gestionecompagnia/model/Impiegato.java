package it.gestionecompagnia.model;

import java.util.Date;

public class Impiegato {

	private int id;
	protected String nome;
	protected String cognome;
	protected String codiceFiscale;
	protected Date dataDiNascita;
	protected Date dataAssunzione;
	protected int compagnia_id;

	public Impiegato() {
		super();
	}

	public Impiegato(String nome, String cognome, String codiceFiscale, Date dataDiNascita, Date dataAssunzione) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dataDiNascita = dataDiNascita;
		this.dataAssunzione = dataAssunzione;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public Date getDataAssunzione() {
		return dataAssunzione;
	}

	public void setDataAssunzione(Date dataAssunzione) {
		this.dataAssunzione = dataAssunzione;
	}

	public int getCompagnia_id() {
		return compagnia_id;
	}

	public void setCompagnia_id(int compagnia_id) {
		this.compagnia_id = compagnia_id;
	}

	@Override
	public String toString() {
		return ("Nome: " + this.nome + " cognome: " + this.cognome + " codice fiscale " + codiceFiscale);
	}

}
