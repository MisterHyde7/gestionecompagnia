package it.gestionecompagnia.dao.compagnia;

import java.sql.Date;
import java.util.List;

import it.gestionecompagnia.dao.AbstractMySQLDAO;
import it.gestionecompagnia.model.Compagnia;
import it.gestionecompagnia.model.Impiegato;

public class CompagniaDAOImpl extends AbstractMySQLDAO implements CompagniaDAO {

	@Override
	public List<Compagnia> list() throws Exception {
		return null;
	}

	@Override
	public Compagnia get(Long idInput) throws Exception {
		return null;
	}

	@Override
	public int update(Compagnia input) throws Exception {
		return 0;
	}

	@Override
	public int insert(Compagnia input) throws Exception {
		return 0;
	}

	@Override
	public int delete(Compagnia input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Compagnia> findByExample(Compagnia input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compagnia> findAllByDataAssunzioneMaggioreDi(Date dataInput) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compagnia> findAllByRagioneSocialeContiene(String input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Impiegato> findAllByCodFisContiene(String input) {
		// TODO Auto-generated method stub
		return null;
	}

}
