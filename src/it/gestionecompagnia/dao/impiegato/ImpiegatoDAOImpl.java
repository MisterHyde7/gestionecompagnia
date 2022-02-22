package it.gestionecompagnia.dao.impiegato;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.gestionecompagnia.dao.AbstractMySQLDAO;
import it.gestionecompagnia.model.Compagnia;
import it.gestionecompagnia.model.Impiegato;

public class ImpiegatoDAOImpl extends AbstractMySQLDAO implements ImpiegatoDAO {

	public ImpiegatoDAOImpl(Connection connection) {
		super(connection);
	}

	@Override
	public List<Impiegato> list() throws Exception {if (isNotActive())
		throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

	ArrayList<Impiegato> result = new ArrayList<Impiegato>();
	Impiegato impiegatoTemp = null;

	try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from impiegato")) {

		while (rs.next()) {
			impiegatoTemp = new Impiegato();
			impiegatoTemp.setId(rs.getInt("id"));
			impiegatoTemp.setNome(rs.getString("nome"));
			impiegatoTemp.setCognome(rs.getString("cognome"));
			impiegatoTemp.setCodiceFiscale(rs.getString("codiceFiscale"));
			impiegatoTemp.setDataDiNascita(rs.getDate("dataNascita"));
			impiegatoTemp.setDataAssunzione(rs.getDate("dataAssunzione"));
			result.add(impiegatoTemp);
		}

	} catch (Exception e) {
		e.printStackTrace();
		throw e;
	}
	return result;
	}

	@Override
	public Impiegato get(Long idInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Impiegato input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Impiegato input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Impiegato input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Impiegato> findByExample(Impiegato input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Impiegato> findAllByCompagnia(Compagnia compagniaInput) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByDataFondazioneCompagniaGreaterThan(Date dataInput) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Impiegato> findAllByCompagniaConFatturatoMaggioreDi(Long fatturatoInput) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Impiegato> findAllByErroreAssunzione() {
		// TODO Auto-generated method stub
		return null;
	}

}
