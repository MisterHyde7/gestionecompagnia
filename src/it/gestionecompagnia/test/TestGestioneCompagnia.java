package it.gestionecompagnia.test;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import it.gestionecompagnia.connection.MyConnection;
import it.gestionecompagnia.dao.Constants;
import it.gestionecompagnia.dao.compagnia.CompagniaDAO;
import it.gestionecompagnia.dao.compagnia.CompagniaDAOImpl;
import it.gestionecompagnia.dao.impiegato.ImpiegatoDAO;
import it.gestionecompagnia.dao.impiegato.ImpiegatoDAOImpl;
import it.gestionecompagnia.model.Compagnia;
import it.gestionecompagnia.model.Impiegato;

public class TestGestioneCompagnia {

	public static void main(String[] args) throws Exception {

		// Creo le istanze DAO
		CompagniaDAO compagniaDAOInstance = null;
		ImpiegatoDAO impiegatoDAOInstance = null;

		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			compagniaDAOInstance = new CompagniaDAOImpl(connection);
			impiegatoDAOInstance = new ImpiegatoDAOImpl(connection);

			testInsertCompagnia(compagniaDAOInstance);

			testListCompagnia(compagniaDAOInstance);

			testGetById(compagniaDAOInstance);

			Compagnia compagniaPerTestUpdate = new Compagnia("com", 500L, new Date(2022, 01, 01));
			compagniaPerTestUpdate.setId(1);
			testUpdateCompagnia(compagniaDAOInstance, compagniaPerTestUpdate);

			testDeleteCompagnia(compagniaDAOInstance);

			testFindByExampleCompagnia(compagniaDAOInstance);
			
			testListImpiegato(impiegatoDAOInstance);
			
			testFindByIdImpiegato(impiegatoDAOInstance);

		}

	}

	private static void testInsertCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception {
		System.out.println("========== Inizio test inserimento compagnia ==========");

		int conteggioInserimenti = compagniaDAOInstance.insert(new Compagnia("provami", 100L, new Date(2022, 01, 01)));
		if (conteggioInserimenti < 1) {
			throw new RuntimeException("Inserimento non avvenuto");
		}
		System.out.println("========== test eseguito con successo ==========");
	}

	private static void testListCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception {

		System.out.println("========== Avvio test list di compagnia ==========");

		int conteggioInserimenti = compagniaDAOInstance.insert(new Compagnia("provami", 100L, new Date(2022, 01, 01)));
		if (conteggioInserimenti < 1) {
			throw new RuntimeException("Inserimento non avvenuto");
		}
		List<Compagnia> elencoVociPresenti = compagniaDAOInstance.list();
		if (elencoVociPresenti.size() < 1)
			throw new RuntimeException("testList : FAILED, non ci sono voci sul DB");

		System.out.println("Nella lista ci sono " + compagniaDAOInstance.list().size() + " compagnie");
		System.out.println("========== test eseguito con successo ==========");

	}

	private static void testGetById(CompagniaDAO compagniaDAOInstance) throws Exception {
		System.out.println("========== Avvio test get di compagnia ==========");
		List<Compagnia> elencoVociPresenti = compagniaDAOInstance.list();
		if (elencoVociPresenti.size() < 1)
			throw new RuntimeException("testList : FAILED, non ci sono voci sul DB");
		if (compagniaDAOInstance.get(1L) == null) {
			throw new RuntimeException("testList : FAILED, non ci sono voci sul DB");
		}
		System.out.println("========== test eseguito con successo ==========");
	}

	private static void testUpdateCompagnia(CompagniaDAO compagniaDAO, Compagnia compagniaInput) throws Exception {
		System.out.println("========== Avvio test update di compagnia ==========");
		List<Compagnia> elencoVociPresenti = compagniaDAO.list();
		if (elencoVociPresenti.size() < 1)
			throw new RuntimeException("testUpdate : FAILED, non ci sono voci sul DB");
		if (compagniaDAO.update(compagniaInput) < 1) {
			throw new RuntimeException("testUpdate : FAILED, non ci sono voci sul DB");
		}
		System.out.println("========== test eseguito con successo ==========");
	}

	private static void testDeleteCompagnia(CompagniaDAO compagniaDAO) throws Exception {
		System.out.println("========== Avvio test Delete di compagnia ==========");
		int conteggioInserimenti = compagniaDAO.insert(new Compagnia("provami", 100L, new Date(2022, 01, 01)));
		if (conteggioInserimenti < 1) {
			throw new RuntimeException("Inserimento non avvenuto");
		}
		List<Compagnia> elencoVociPresenti = compagniaDAO.list();
		if (elencoVociPresenti.size() < 1)
			throw new RuntimeException("testDelete : FAILED, non ci sono voci sul DB");
		if (compagniaDAO.delete(elencoVociPresenti.get(elencoVociPresenti.size() - 1)) < 1) {
			throw new RuntimeException("testDelete : FAILED, non ci sono voci sul DB");
		}
		System.out.println("========== test eseguito con successo ==========");
	}

	private static void testFindByExampleCompagnia(CompagniaDAO compagniaDAO) throws Exception {
		System.out.println("========== Avvio test example di compagnia ==========");
		List<Compagnia> elencoVociPresenti = compagniaDAO.list();
		if (elencoVociPresenti.size() < 1)
			throw new RuntimeException("testExample : FAILED, non ci sono voci sul DB");
		Compagnia compagniaPerTestExample = new Compagnia("provami", 100L, new Date(2022, 01, 01));
		int conteggioInserimenti = compagniaDAO.insert(compagniaPerTestExample);
		if (conteggioInserimenti < 1) {
			throw new RuntimeException("Inserimento non avvenuto");
		}
		Compagnia compagniaPerTest = new Compagnia("provami", 100L, new Date(2022, 01, 01));
		if (compagniaDAO.findByExample(compagniaPerTest) == null) {
			throw new RuntimeException("testExample : FAILED, non ci sono voci sul DB");
		}
		System.out.println("========== test eseguito con successo ==========");
	}
	
	private static void testListImpiegato(ImpiegatoDAO impiegatoDAO) throws Exception {
		System.out.println("========== Avvio list di impiegato ==========");
		
		List<Impiegato> elencoVociPresenti = impiegatoDAO.list();
		if (elencoVociPresenti.size() < 1)
			throw new RuntimeException("testList : FAILED, non ci sono voci sul DB");

		System.out.println("Nella lista ci sono " + impiegatoDAO.list().size() + " compagnie");
		System.out.println("========== test eseguito con successo ==========");
	}
	
	private static void testFindByIdImpiegato(ImpiegatoDAO impiegatoDAO) throws Exception {
		System.out.println("========== Avvio test get di impiegato ==========");
		List<Impiegato> elencoVociPresenti = impiegatoDAO.list();
		if (elencoVociPresenti.size() < 1)
			throw new RuntimeException("testList : FAILED, non ci sono voci sul DB");
		if (impiegatoDAO.get(1L) == null) {
			throw new RuntimeException("testList : FAILED, non ci sono voci sul DB");
		}
		System.out.println("========== test eseguito con successo ==========");
	}
}
