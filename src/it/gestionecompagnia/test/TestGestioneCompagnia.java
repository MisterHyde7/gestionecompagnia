package it.gestionecompagnia.test;

import java.sql.Connection;
import java.sql.Date;

import it.gestionecompagnia.connection.MyConnection;
import it.gestionecompagnia.dao.Constants;
import it.gestionecompagnia.dao.compagnia.CompagniaDAO;
import it.gestionecompagnia.dao.compagnia.CompagniaDAOImpl;
import it.gestionecompagnia.dao.impiegato.ImpiegatoDAO;
import it.gestionecompagnia.dao.impiegato.ImpiegatoDAOImpl;
import it.gestionecompagnia.model.Compagnia;

public class TestGestioneCompagnia {

	public static void main(String[] args) throws Exception {

		// Creo le istanze DAO
		CompagniaDAO compagniaDAOInstance = null;
		ImpiegatoDAO impiegatoDAOInstance = null;

		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			
			compagniaDAOInstance = new CompagniaDAOImpl(connection);
			impiegatoDAOInstance = new ImpiegatoDAOImpl(connection);

			testInsertCompagnia(compagniaDAOInstance);

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

}
