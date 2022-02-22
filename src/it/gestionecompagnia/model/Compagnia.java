package it.gestionecompagnia.model;

import java.util.Date;

public class Compagnia {

	private int id;
	protected String ragioneSociale;
	protected Long fatturatoAnnuo;
	protected Date dataFondazone;

	public Compagnia() {
		super();
	}

	public Compagnia(String ragioneSociale, Long fatturatoAnnuo, Date dataFondazone) {
		super();
		this.ragioneSociale = ragioneSociale;
		this.fatturatoAnnuo = fatturatoAnnuo;
		this.dataFondazone = dataFondazone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public Long getFatturatoAnnuo() {
		return fatturatoAnnuo;
	}

	public void setFatturatoAnnuo(Long fatturatoAnnuo) {
		this.fatturatoAnnuo = fatturatoAnnuo;
	}

	public Date getDataFondazone() {
		return dataFondazone;
	}

	public void setDataFondazone(Date dataFondazone) {
		this.dataFondazone = dataFondazone;
	}

	@Override
	public String toString() {
		return ("Ragione sociale: " + this.ragioneSociale + " fatturato annuo: " + this.fatturatoAnnuo);
	}

}
