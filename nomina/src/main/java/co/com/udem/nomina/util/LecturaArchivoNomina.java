package co.com.udem.nomina.util;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

import co.com.udem.nomina.dto.EmpleadoDTO;

public class LecturaArchivoNomina {

	private static Hashtable<String, EmpleadoDTO> listaEmpleadoTabla = new Hashtable<String, EmpleadoDTO>();
	static InputStream archivoNomina = null;

	public static String leerArchivo() {

		archivoNomina = ClassLoader.class.getResourceAsStream("/nominaEmpleados.txt");
		Scanner scanner = null;
		String mensaje = "";

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

	private static void parseLine(String registro) {
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
			System.out.println("La Cedula es: " + cedula);
			System.out.println("El Nombre es: " + listaEmpleadoTabla.get(cedula).getNombre());
			System.out.println("El Apellido es: " + listaEmpleadoTabla.get(cedula).getApellido());
			System.out.println("El Departamento es: " + listaEmpleadoTabla.get(cedula).getDepartamento());
			System.out.println("El Salario es: " + listaEmpleadoTabla.get(cedula).getSalario());
			System.out.println("---------------------------------------------------------------------");
		}
	}
	
	public static int tamanoHashMap() {
		return listaEmpleadoTabla.size();
	}

}
