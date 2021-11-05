package org.iesalandalus.programacion.torreajedrez;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.iesalandalus.programacion.torreajedrez.Posicion;
import org.junit.Test;

public class PosicionTest {

	private static final String COLUMNA_NO_ESPERADA = "La columna devuelta no es la misma que la pasada al constructor.";
	private static final String FILA_NO_ESPERADA = "La fila devuelta no es la misma que la pasada al constructor.";
	private static final String EXCEPCION_FILA_INCORRECTA = "Debería haber saltado una excepción indicando que la fila es incorrecta";
	private static final String EXCEPCION_NO_CORRECTA = "El mensaje devuelto por la excepción no es correcto.";
	private static final String ERROR_FILA_NO_VALIDA = "ERROR: Fila no válida.";
	private static final String OBJETO_DEBERIA_SER_NULL = "No se debería haber creado el objeto posición.";
	private static final String ERROR_COLUMNA_NO_VALIDA = "ERROR: Columna no válida.";
	private static final String EXCEPCION_COLUMNA_INCORRECTA = "Debería haber saltado una excepción indicando que la columna es incorrecta";
	private static final String POSICION_NO_ESPERADA = "La posición copiada debería ser la misma que la pasada como parámetro.";
	private static final String EXCEPCION_POSICION_NULA = "Debería haber saltado una excepción indicando que no se puede copiar una posición nula.";
	private static final String ERROR_COPIAR_POSICION_NULA = "ERROR: No es posible copiar una posición nula.";	
	private static final String CADENA_NO_ESPERADA = "La cadena devuelta no es la esperada.";	

	@Test
	public void constructorFilaValidaColumnaValidaCreaPosicionCorrectamente() {
		Posicion posicion;
		posicion = new Posicion(1, 'a');
		assertThat(FILA_NO_ESPERADA, posicion.getFila(), is(1));
		assertThat(COLUMNA_NO_ESPERADA, posicion.getColumna(), is('a'));
	}
	
	@Test
	public void constructorFilaNoValidaColumnaValidaLanzaExcepcion() {
		Posicion posicion = null;
		try {
			posicion = new Posicion(0, 'a');
			fail(EXCEPCION_FILA_INCORRECTA);
		} catch (IllegalArgumentException e) {
			assertThat(EXCEPCION_NO_CORRECTA, e.getMessage(), is(ERROR_FILA_NO_VALIDA));
			assertThat(OBJETO_DEBERIA_SER_NULL, posicion, is(nullValue()));
		}
		try {
			posicion = new Posicion(9, 'a');
			fail(EXCEPCION_FILA_INCORRECTA);
		} catch (IllegalArgumentException e) {
			assertThat(EXCEPCION_NO_CORRECTA, e.getMessage(), is(ERROR_FILA_NO_VALIDA));
			assertThat(OBJETO_DEBERIA_SER_NULL, posicion, is(nullValue()));
		}
	}
	
	@Test
	public void constructorFilaNoValidaColumnaNoValidaLanzaExcepcion() {
		Posicion posicion = null;
		try {
			posicion = new Posicion(0, '`');
			fail(EXCEPCION_FILA_INCORRECTA);
		} catch (IllegalArgumentException e) {
			assertThat(EXCEPCION_NO_CORRECTA, e.getMessage(), is(ERROR_FILA_NO_VALIDA));
			assertThat(OBJETO_DEBERIA_SER_NULL, posicion, is(nullValue()));
		}
		try {
			posicion = new Posicion(9, '`');
			fail(EXCEPCION_FILA_INCORRECTA);
		} catch (IllegalArgumentException e) {
			assertThat(EXCEPCION_NO_CORRECTA, e.getMessage(), is(ERROR_FILA_NO_VALIDA));
			assertThat(OBJETO_DEBERIA_SER_NULL, posicion, is(nullValue()));
		}
		try {
			posicion = new Posicion(0, 'i');
			fail(EXCEPCION_FILA_INCORRECTA);
		} catch (IllegalArgumentException e) {
			assertThat(EXCEPCION_NO_CORRECTA, e.getMessage(), is(ERROR_FILA_NO_VALIDA));
			assertThat(OBJETO_DEBERIA_SER_NULL, posicion, is(nullValue()));
		}
		try {
			posicion = new Posicion(9, 'i');
			fail(EXCEPCION_FILA_INCORRECTA);
		} catch (IllegalArgumentException e) {
			assertThat(EXCEPCION_NO_CORRECTA, e.getMessage(), is(ERROR_FILA_NO_VALIDA));
			assertThat(OBJETO_DEBERIA_SER_NULL, posicion, is(nullValue()));
		}
	}
	
	@Test
	public void constructorFilaValidaColumnaNoValidaLanzaExcepcion() {
		Posicion posicion = null;
		try {
			posicion = new Posicion(1, '`');
			fail(EXCEPCION_COLUMNA_INCORRECTA);
		} catch (IllegalArgumentException e) {
			assertThat(EXCEPCION_NO_CORRECTA, e.getMessage(), is(ERROR_COLUMNA_NO_VALIDA));
			assertThat(OBJETO_DEBERIA_SER_NULL, posicion, is(nullValue()));
		}
		try {
			posicion = new Posicion(1, 'i');
			fail(EXCEPCION_COLUMNA_INCORRECTA);
		} catch (IllegalArgumentException e) {
			assertThat(EXCEPCION_NO_CORRECTA, e.getMessage(), is(ERROR_COLUMNA_NO_VALIDA));
			assertThat(OBJETO_DEBERIA_SER_NULL, posicion, is(nullValue()));
		}
	}
	
	@Test
	public void constructorPosicionValidaDevuelveCopiaDeLaPosicion() {
		Posicion posicion = new Posicion(1, 'a');
		Posicion nuevaPosicion = new Posicion(posicion);
		assertThat(POSICION_NO_ESPERADA, nuevaPosicion, is(posicion));
	}
	
	@Test
	public void constructorPosicionNulaLanzaExcepcion() {
		Posicion posicion = null;
		try {
			posicion = new Posicion(null);
			fail(EXCEPCION_POSICION_NULA);
		} catch (NullPointerException e) {
			assertThat(EXCEPCION_NO_CORRECTA, e.getMessage(), is(ERROR_COPIAR_POSICION_NULA));
			assertThat(OBJETO_DEBERIA_SER_NULL, posicion, is(nullValue()));
		}
	}
	
	@Test
	public void toStringDevuelveLaCadenaEsperada() {
		Posicion posicion = new Posicion(1, 'a');
		assertThat(CADENA_NO_ESPERADA, posicion.toString(), is("fila=1, columna=a"));
	}

}
