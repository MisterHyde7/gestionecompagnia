package it.gestionecompagnia.dao.compagnia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.gestionecompagnia.dao.AbstractMySQLDAO;
import it.gestionecompagnia.model.Compagnia;
import it.gestionecompagnia.model.Impiegato;

public class CompagniaDAOImpl extends AbstractMySQLDAO implements CompagniaDAO {

	public CompagniaDAOImpl(Connection connection) {
		super(connection);
	}

	@Override
	public List<Compagnia> list() throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		ArrayList<Compagnia> result = new ArrayList<Compagnia>();
		Compagnia compagniaTemp = null;

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from compagnia")) {

			while (rs.next()) {
				compagniaTemp = new Compagnia();
				compagniaTemp.setId(rs.getInt("id"));
				compagniaTemp.setRagioneSociale(rs.getString("ragioneSociale"));
				compagniaTemp.setFatturatoAnnuo(rs.getLong("fatturatoAnnuo"));
				compagniaTemp.setDataFondazone(rs.getDate("dataFondazione"));
				result.add(compagniaTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Compagnia get(Long idInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Compagnia result = null;
		try (PreparedStatement ps = connection.prepareStatement("select * from compagnia where id=?")) {

			ps.setLong(1, idInput);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = new Compagnia();
					result.setId(rs.getInt("id"));
					result.setRagioneSociale(rs.getString("ragioneSociale"));
					result.setFatturatoAnnuo(rs.getLong("fatturatoAnnuo"));
					result.setDataFondazone(rs.getDate("dataFondazione"));
				} else {
					result = null;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int update(Compagnia input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"UPDATE compagnia SET ragioneSociale=?, fatturatoAnnuo=?, dataFondazione=? where id=?;")) {
			ps.setString(1, input.getRagioneSociale());
			ps.setLong(2, input.getFatturatoAnnuo());
			ps.setDate(3, new java.sql.Date(input.getDataFondazone().getTime()));
			ps.setLong(4, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int insert(Compagnia input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO compagnia (ragioneSociale, fatturatoAnnuo, dataFondazione) VALUES (?, ?, ?);")) {
			ps.setString(1, input.getRagioneSociale());
			ps.setLong(2, input.getFatturatoAnnuo());
			ps.setDate(3, new java.sql.Date(input.getDataFondazone().getTime()));
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Compagnia input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("DELETE FROM compagnia WHERE ID=?")) {
			ps.setLong(1, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Compagnia> findByExample(Compagnia input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		ArrayList<Compagnia> result = new ArrayList<Compagnia>();
		Compagnia compagniaTemp = null;

		String query = "select * from compagnia where 1=1 ";
		if (input.getRagioneSociale() != null && !input.getRagioneSociale().isEmpty()) {
			query += " and ragioneSociale like '" + input.getRagioneSociale() + "%' ";
		}

		if (input.getFatturatoAnnuo() != null) {
			query += " and fatturatoAnnuo = " + input.getFatturatoAnnuo();
		}

		if (input.getDataFondazone() != null) {
			query += " and dataFondazione = '" + input.getDataFondazone() + "' ";
		}

		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery(query);

			while (rs.next()) {
				compagniaTemp = new Compagnia();
				compagniaTemp.setRagioneSociale(rs.getString("ragioneSociale"));
				compagniaTemp.setFatturatoAnnuo(rs.getLong("fatturatoAnnuo"));
				compagniaTemp.setDataFondazone(rs.getDate("dataFondazione"));
				result.add(compagniaTemp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Compagnia> findAllByDataAssunzioneMaggioreDi(Date dataInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		ArrayList<Compagnia> result = new ArrayList<Compagnia>();
		Compagnia compagniaTemp = null;

		try (PreparedStatement ps = connection.prepareStatement(
				"select distinct compagnia_id from compagnia inner join impiegato on compagnia_id = compagnia.id where dataAssunzione > ? ")) {

			ps.setDate(1, new java.sql.Date(dataInput.getTime()));

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					compagniaTemp = new Compagnia();
					compagniaTemp.setId(rs.getInt("id"));
					compagniaTemp.setRagioneSociale(rs.getString("ragioneSociale"));
					compagniaTemp.setFatturatoAnnuo(rs.getLong("fatturatoAnnuo"));
					compagniaTemp.setDataFondazone(rs.getDate("dataFondazione"));
					result.add(compagniaTemp);
				}

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			return result;
		}
	}

	@Override
	public List<Compagnia> findAllByRagioneSocialeContiene(String input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		ArrayList<Compagnia> result = new ArrayList<Compagnia>();
		Compagnia compagniaTemp = null;

		try (PreparedStatement ps = connection
				.prepareStatement("select * from compagnia where ragioneSociale like ?")) {

			ps.setString(1, "'%" + input + "%'");

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					compagniaTemp = new Compagnia();
					compagniaTemp.setId(rs.getInt("id"));
					compagniaTemp.setRagioneSociale(rs.getString("ragioneSociale"));
					compagniaTemp.setFatturatoAnnuo(rs.getLong("fatturatoAnnuo"));
					compagniaTemp.setDataFondazone(rs.getDate("dataFondazione"));
					result.add(compagniaTemp);
				}

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			return result;
		}
	}

	@Override
	public List<Compagnia> findAllByCodFisContiene(String input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		ArrayList<Compagnia> result = new ArrayList<Compagnia>();
		Compagnia compagniaTemp = null;

		try (PreparedStatement ps = connection.prepareStatement("select * from compagnia inner join impiegato on compagnia_id= compagnia.id where codiceFiscale like ?")) {

			ps.setString(1, "'%" + input + "%'");

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					compagniaTemp = new Compagnia();
					compagniaTemp.setId(rs.getInt("id"));
					compagniaTemp.setRagioneSociale(rs.getString("ragioneSociale"));
					compagniaTemp.setFatturatoAnnuo(rs.getLong("fatturatoAnnuo"));
					compagniaTemp.setDataFondazone(rs.getDate("dataFondazione"));
					result.add(compagniaTemp);
				}

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			return result;
		}
	}

}
