package co.com.udem.nomina.util;

import org.junit.Test;
import co.com.udem.nomina.dto.EmpleadoDTO;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import java.util.Hashtable;
import java.util.Scanner;

public class LecturaArchivoNominaTest {

	@Test
	public void testleerArchivo() {
		String mensaje = LecturaArchivoNomina.leerArchivo();
		assertEquals(null, mensaje);
	}

	@Test
	public void testTamanoHashTable() {
		LecturaArchivoNomina.leerArchivo();
		int cantidadRegistros = LecturaArchivoNomina.tamanoHashtable();
		assertEquals(3, cantidadRegistros);
	}

	@Test
	public void testParseLine() {

		Hashtable<String, EmpleadoDTO> listaEmpleadoTablaTest = new Hashtable<String, EmpleadoDTO>();
		String registro = "Nombre,Apellido,Cedula,Departamento,2222";
		Scanner scanner = new Scanner(registro);
		scanner.useDelimiter(",");
		EmpleadoDTO empleadoDTO = new EmpleadoDTO();
		empleadoDTO.setNombre(scanner.next());
		empleadoDTO.setApellido(scanner.next());
		String cedula = scanner.next();
		empleadoDTO.setCedula(cedula);
		empleadoDTO.setDepartamento(scanner.next());
		empleadoDTO.setSalario(Double.parseDouble(scanner.next()));
		listaEmpleadoTablaTest.put(empleadoDTO.getCedula(), empleadoDTO);
		scanner.close();
		assertThat(listaEmpleadoTablaTest.get(cedula), is(empleadoDTO));

	}

}
