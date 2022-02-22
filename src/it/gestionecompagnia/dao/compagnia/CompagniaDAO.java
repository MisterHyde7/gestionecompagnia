package it.gestionecompagnia.dao.compagnia;

import java.sql.Date;
import java.util.List;

import it.gestionecompagnia.dao.IBaseDAO;
import it.gestionecompagnia.model.Compagnia;
import it.gestionecompagnia.model.Impiegato;

public interface CompagniaDAO extends IBaseDAO<Compagnia> {
	
	public List<Compagnia> findAllByDataAssunzioneMaggioreDi(Date dataInput);
	public List<Compagnia> findAllByRagioneSocialeContiene(String input);
	public List<Impiegato> findAllByCodFisContiene(String input);

}
