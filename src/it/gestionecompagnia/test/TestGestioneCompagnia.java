package it.gestionecompagnia.test;

import java.sql.Connection;

import it.gestionecompagnia.connection.MyConnection;
import it.gestionecompagnia.dao.Constants;

public class TestGestioneCompagnia {

	public static void main(String[] args) throws Exception {

		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

		}

	}

}
