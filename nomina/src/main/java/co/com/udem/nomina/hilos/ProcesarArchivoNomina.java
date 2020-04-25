package co.com.udem.nomina.hilos;

import java.io.FileNotFoundException;
import co.com.udem.nomina.dto.EmpleadoDTO;
import co.com.udem.nomina.util.LecturaArchivoNomina;

public class ProcesarArchivoNomina implements Runnable {

	EmpleadoDTO empleadoDTO = new EmpleadoDTO();

	public void invocaProcesarArchivo() throws FileNotFoundException {
		Thread thread = new Thread(new ProcesarArchivoNomina());
		thread.start();

	}

	public void run() {
		while (true) {

			LecturaArchivoNomina lecturaArchivoNomina = new LecturaArchivoNomina();
			try {
				lecturaArchivoNomina.leerArchivo();
			} catch (FileNotFoundException e) {
				System.out.println("Por favor verifique que el archivo exista.");
			}

			if (lecturaArchivoNomina.cantidadRegistros() == 3) {
				lecturaArchivoNomina.imprimirEmpleado();
				break;
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
