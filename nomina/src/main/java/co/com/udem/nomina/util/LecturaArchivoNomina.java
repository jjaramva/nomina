package co.com.udem.nomina.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

import co.com.udem.nomina.dto.EmpleadoDTO;

public class LecturaArchivoNomina {

	Hashtable<String, EmpleadoDTO> listaEmpleadoTabla = new Hashtable<String, EmpleadoDTO>();
	private int cantidadRegistros = 0;

	public void leerArchivo() throws FileNotFoundException {

		File file = new File("J:\\Dirtrab\\nominaEmpleados.txt");
		Scanner scanner = new Scanner(file);

		try {
			while (scanner.hasNextLine()) {
				String registro = scanner.nextLine();
				parseLine(registro);
				cantidadRegistros++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}

	}

	private void parseLine(String registro) {
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

	public void imprimirEmpleado() {
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

	public int cantidadRegistros() {
		return cantidadRegistros;
	}

}
