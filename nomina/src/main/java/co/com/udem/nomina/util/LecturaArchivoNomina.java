package co.com.udem.nomina.util;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import co.com.udem.nomina.dto.EmpleadoDTO;

public class LecturaArchivoNomina {

	private static Hashtable<String, EmpleadoDTO> listaEmpleadoTabla = new Hashtable<String, EmpleadoDTO>();
	private static final Logger logger = LogManager.getLogger(LecturaArchivoNomina.class);
	static InputStream archivoNomina = null;

	public static String leerArchivo() {

		archivoNomina = ClassLoader.class.getResourceAsStream("/nominaEmpleados.txt");
		Scanner scanner = null;
		String mensaje = null;

		try {
			scanner = new Scanner(archivoNomina);
			while (scanner.hasNextLine()) {
				String registro = scanner.nextLine();
				parseLine(registro);
			}
		} catch (Exception e) {
			mensaje = "El archivo no está en la ruta especificada";
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
		return mensaje;

	}

	public static void parseLine(String registro) {
		Scanner scanner = new Scanner(registro);
		scanner.useDelimiter(",");

		while (scanner.hasNext()) {
			EmpleadoDTO empleadoDTO = new EmpleadoDTO();
			empleadoDTO.setNombre(scanner.next());
			empleadoDTO.setApellido(scanner.next());
			String cedula = scanner.next();
			empleadoDTO.setCedula(cedula);
			empleadoDTO.setDepartamento(scanner.next());
			empleadoDTO.setSalario(Double.parseDouble(scanner.next()));
			listaEmpleadoTabla.put(cedula, empleadoDTO);
		}
		scanner.close();
	}

	public static void imprimirEmpleado() {
		Enumeration<String> nominaDTO = listaEmpleadoTabla.keys();
		Object cedula;
		while (nominaDTO.hasMoreElements()) {
			cedula = nominaDTO.nextElement();
			logger.info("La Cedula es: " + cedula);
			logger.info("El Nombre es: " + listaEmpleadoTabla.get(cedula).getNombre());
			logger.info("El Apellido es: " + listaEmpleadoTabla.get(cedula).getApellido());
			logger.info("El Departamento es: " + listaEmpleadoTabla.get(cedula).getDepartamento());
			logger.info("El Salario es: " + listaEmpleadoTabla.get(cedula).getSalario());
			logger.info("-------------------------------------------------");
		}
	}

	public static int tamanoHashtable() {
		return listaEmpleadoTabla.size();
	}

}
