package it.gestionecompagnia.dao.compagnia;

import java.sql.Date;
import java.util.List;

import it.gestionecompagnia.dao.IBaseDAO;
import it.gestionecompagnia.model.Compagnia;
import it.gestionecompagnia.model.Impiegato;

public interface CompagniaDAO extends IBaseDAO<Compagnia> {
	
	public List<Compagnia> findAllByDataAssunzioneMaggioreDi(Date dataInput) throws Exception;
	public List<Compagnia> findAllByRagioneSocialeContiene(String input) throws Exception;
	public List<Impiegato> findAllByCodFisContiene(String input) throws Exception;

}
