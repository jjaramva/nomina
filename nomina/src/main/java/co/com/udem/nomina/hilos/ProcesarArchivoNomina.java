package co.com.udem.nomina.hilos;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import co.com.udem.nomina.util.LecturaArchivoNomina;

public class ProcesarArchivoNomina implements Runnable {

	String mensaje = "";
	private static final Logger logger = LogManager.getLogger(ProcesarArchivoNomina.class);
	Thread t;

	public void invocaProcesarArchivo() {
		t = new Thread(this);
		t.start();
	}

	public void run() {
		BasicConfigurator.configure();
		while (true) {
			try {
				Thread.sleep(10000);
				mensaje = LecturaArchivoNomina.leerArchivo();
				if (mensaje != null) {
					logger.info(mensaje);
				}
				int cantidadRegistros = LecturaArchivoNomina.tamanoHashMap();
				if (cantidadRegistros == 3) {
					LecturaArchivoNomina.imprimirEmpleado();
					break;
				}
			} catch (InterruptedException e) {
				logger.error(e.getMessage());
				Thread.currentThread().interrupt();
			}
		}

	}

}
