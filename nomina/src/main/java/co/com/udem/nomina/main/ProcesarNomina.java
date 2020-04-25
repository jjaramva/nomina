package co.com.udem.nomina.main;

import java.io.FileNotFoundException;
import co.com.udem.nomina.hilos.ProcesarArchivoNomina;

public class ProcesarNomina {

	public static void main(String[] args) throws FileNotFoundException {
		ProcesarArchivoNomina procesaArchivoNomina = new ProcesarArchivoNomina();

		procesaArchivoNomina.invocaProcesarArchivo();

	}

}
