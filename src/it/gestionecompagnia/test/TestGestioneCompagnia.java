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
			compagniaPerTestUpdate.setId(129);
			testUpdateCompagnia(compagniaDAOInstance, compagniaPerTestUpdate);

			testDeleteCompagnia(compagniaDAOInstance);

			testFindByExampleCompagnia(compagniaDAOInstance);

			testListImpiegato(impiegatoDAOInstance);

			testFindByIdImpiegato(impiegatoDAOInstance);

			Impiegato impiegatoPerTestUpdate = new Impiegato("luca", "martucci", "mrtlcu99c05h501x",
					new java.util.Date(1999, 03, 05), new java.util.Date(2022, 03, 05));
			impiegatoPerTestUpdate.setId(1);
			testUpdateImpiegato(impiegatoDAOInstance, impiegatoPerTestUpdate);

			testInsertImpiegato(impiegatoDAOInstance);

			testDeleteImpiegato(impiegatoDAOInstance);

//			testFindByExampleImpiegato(impiegatoDAOInstance);

			testFindAllByDataAssunzioneMaggioreDi(compagniaDAOInstance);

			testFindAllByRagioneSocialeContiene(compagniaDAOInstance);

			testFindAllByCodicefiscaleContiene(compagniaDAOInstance);

			testAllFindByCompagnia(compagniaDAOInstance, impiegatoDAOInstance);

			testCountByDataFondazioneGreaterThan(compagniaDAOInstance, impiegatoDAOInstance);

			testFindAllByFatturatoMaggioreDi(compagniaDAOInstance, impiegatoDAOInstance);

			testFindAllByErroreAssunzione(compagniaDAOInstance, impiegatoDAOInstance);

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
		if (compagniaDAOInstance.get(117L) == null) {
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

	private static void testUpdateImpiegato(ImpiegatoDAO impiegatoDAO, Impiegato impiegatoInput) throws Exception {
		System.out.println("========== Avvio test update di impiegato ==========");
		List<Impiegato> elencoVociPresenti = impiegatoDAO.list();
		if (elencoVociPresenti.size() < 1)
			throw new RuntimeException("testUpdate : FAILED, non ci sono voci sul DB");
		if (impiegatoDAO.update(impiegatoInput) < 1) {
			throw new RuntimeException("testUpdate : FAILED, non ci sono voci sul DB");
		}
		System.out.println("========== test eseguito con successo ==========");
	}

	private static void testInsertImpiegato(ImpiegatoDAO impiegatoDAO) throws Exception {
		System.out.println("========== Inizio test inserimento impiegato ==========");

		int conteggioInserimenti = impiegatoDAO.insert(new Impiegato("prova", "prova", "h501x",
				new java.util.Date(1900, 01, 01), new java.util.Date(2000, 01, 01)));
		if (conteggioInserimenti < 1) {
			throw new RuntimeException("Inserimento non avvenuto");
		}
		System.out.println("========== test eseguito con successo ==========");
	}

	private static void testDeleteImpiegato(ImpiegatoDAO impiegatoDAO) throws Exception {
		System.out.println("========== Avvio test Delete di compagnia ==========");
		int conteggioInserimenti = impiegatoDAO.insert(new Impiegato("cosa", "cosa", "h501x",
				new java.util.Date(1900, 01, 01), new java.util.Date(2000, 01, 01)));
		if (conteggioInserimenti < 1) {
			throw new RuntimeException("Inserimento non avvenuto");
		}
		List<Impiegato> elencoVociPresenti = impiegatoDAO.list();
		if (elencoVociPresenti.size() < 1)
			throw new RuntimeException("testDelete : FAILED, non ci sono voci sul DB");
		if (impiegatoDAO.delete(elencoVociPresenti.get(elencoVociPresenti.size() - 1)) < 1) {
			throw new RuntimeException("testDelete : FAILED, non ci sono voci sul DB");
		}
		System.out.println("========== test eseguito con successo ==========");
	}

	private static void testFindByExampleImpiegato(ImpiegatoDAO impiegatoDAO) throws Exception {
		System.out.println("========== Avvio test example di impiegato ==========");
		List<Impiegato> elencoVociPresenti = impiegatoDAO.list();
		if (elencoVociPresenti.size() < 1)
			throw new RuntimeException("testExample : FAILED, non ci sono voci sul DB");
		Impiegato impiegatoPerTestExample = new Impiegato("test", "test", "h501x", new java.util.Date(2000, 01, 01),
				new java.util.Date(2000, 01, 01));
		int conteggioInserimenti = impiegatoDAO.insert(impiegatoPerTestExample);
		if (conteggioInserimenti < 1) {
			throw new RuntimeException("Inserimento non avvenuto");
		}
		Impiegato impiegatoPerTest = new Impiegato("test", "test", "h501x", new java.util.Date(2000, 01, 01),
				new java.util.Date(2000, 01, 01));
		if (impiegatoDAO.findByExample(impiegatoPerTest) == null) {
			throw new RuntimeException("testExample : FAILED, non ci sono voci sul DB");
		}
		System.out.println("========== test eseguito con successo ==========");
	}

	private static void testFindAllByDataAssunzioneMaggioreDi(CompagniaDAO compagniaDAO) throws Exception {
		System.out.println("========== Avvio test find by assunzione di compagnia ==========");
		List<Compagnia> elencoCompagniePresenti = compagniaDAO.list();
		if (elencoCompagniePresenti.size() < 1) {
			throw new RuntimeException("lista di compagnie vuota");
		}
		Date dataInput = new java.sql.Date(2000, 01, 01);
		if (compagniaDAO.findAllByDataAssunzioneMaggioreDi(dataInput) == null) {
			throw new RuntimeException("errore nel db");
		}
		System.out.println("========== test eseguito con successo ==========");
	}

	private static void testFindAllByRagioneSocialeContiene(CompagniaDAO compagniaDAO) throws Exception {
		System.out.println("========== Avvio test find by ragione sociale di compagnia ==========");
		List<Compagnia> elencoCompagniePresenti = compagniaDAO.list();
		if (elencoCompagniePresenti.size() < 1) {
			throw new RuntimeException("lista di compagnie vuota");
		}
		String inputString = "pro";
		if (compagniaDAO.findAllByRagioneSocialeContiene(inputString) == null) {
			throw new RuntimeException("errore nel db");
		}
		System.out.println("========== test eseguito con successo ==========");
	}

	private static void testFindAllByCodicefiscaleContiene(CompagniaDAO compagniaDAO) throws Exception {
		System.out.println("========== Avvio test find by codice fiscale di compagnia ==========");
		List<Compagnia> elencoCompagniePresenti = compagniaDAO.list();
		if (elencoCompagniePresenti.size() < 1) {
			throw new RuntimeException("lista di compagnie vuota");
		}
		String inputString = "h501";
		if (compagniaDAO.findAllByCodFisContiene(inputString) == null) {
			throw new RuntimeException("errore nel db");
		}
		System.out.println("========== test eseguito con successo ==========");
	}

	private static void testAllFindByCompagnia(CompagniaDAO compagniaDAO, ImpiegatoDAO impiegatoDAO) throws Exception {
		System.out.println("========== Avvio test find by compagnia di impiegato ==========");
		List<Impiegato> elencoCompagniePresenti = impiegatoDAO.list();
		if (elencoCompagniePresenti.size() < 1) {
			throw new RuntimeException("lista di compagnie vuota");
		}
		Impiegato impiegatoTest = new Impiegato("test", "testCognome", "testh501test", new java.util.Date(2000, 01, 01),
				new java.util.Date(2000, 01, 01));
		Compagnia compagniaTest = new Compagnia("prova", 777L, new Date(1000, 01, 01));
		compagniaDAO.insert(compagniaTest);
		impiegatoTest.setCompagnia_id(117);
		System.out.println("========== test eseguito con successo ==========");
	}

	private static void testCountByDataFondazioneGreaterThan(CompagniaDAO compagniaDAO, ImpiegatoDAO impiegatoDAO)
			throws Exception {
		System.out.println("========== Avvio test count by data fondazione di impiegato ==========");
		List<Compagnia> elencoCompagniePresenti = compagniaDAO.list();
		if (elencoCompagniePresenti.size() < 1) {
			throw new RuntimeException("lista di compagnie vuota");
		}
		Date dataInput = new Date(1000, 01, 01);
		if (impiegatoDAO.countByDataFondazioneCompagniaGreaterThan(dataInput) < 0) {
			throw new RuntimeException("errore nel db");
		}
		System.out.println("========== test eseguito con successo ==========");
	}

	private static void testFindAllByFatturatoMaggioreDi(CompagniaDAO compagniaDAO, ImpiegatoDAO impiegatoDAO)
			throws Exception {
		System.out.println("========== Avvio test find by fatturato annuo maggiore di impiegato ==========");
		List<Compagnia> elencoCompagniePresenti = compagniaDAO.list();
		if (elencoCompagniePresenti.size() < 1) {
			throw new RuntimeException("lista di compagnie vuota");
		}
		Long fatturatoMinimo = 10L;
		if (impiegatoDAO.findAllByCompagniaConFatturatoMaggioreDi(fatturatoMinimo) == null) {
			throw new RuntimeException("errore nel db");
		}
		System.out.println("========== test eseguito con successo ==========");
	}

	private static void testFindAllByErroreAssunzione(CompagniaDAO compagniaDAO, ImpiegatoDAO impiegatoDAO)
			throws Exception {
		System.out.println("========== Avvio test find by errore assunzione di impiegato ==========");
		List<Compagnia> elencoCompagniePresenti = compagniaDAO.list();
		if (elencoCompagniePresenti.size() < 1) {
			throw new RuntimeException("lista di compagnie vuota");
		}
		if (impiegatoDAO.findAllByErroreAssunzione() == null) {
			throw new RuntimeException("errore nel db");
		}
		System.out.println("========== test eseguito con successo ==========");
	}
}
