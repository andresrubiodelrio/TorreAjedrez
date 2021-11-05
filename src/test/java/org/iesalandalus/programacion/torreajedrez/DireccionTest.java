package org.iesalandalus.programacion.torreajedrez;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.iesalandalus.programacion.torreajedrez.Direccion;
import org.junit.Test;

public class DireccionTest {
	
	private static final String ERROR_DIRECCION_NO_VALIDA = "ERROR: La dirección no es válida.";

	@Test
	public void nombresValidosDireccionesCreanDireccionesValidas() {
		Direccion direccion;
		direccion = Direccion.ARRIBA;
		assertThat(ERROR_DIRECCION_NO_VALIDA, direccion, is(Direccion.ARRIBA));
		direccion = Direccion.ABAJO;
		assertThat(ERROR_DIRECCION_NO_VALIDA, direccion, is(Direccion.ABAJO));
		direccion = Direccion.IZQUIERDA;
		assertThat(ERROR_DIRECCION_NO_VALIDA, direccion, is(Direccion.IZQUIERDA));
		direccion = Direccion.DERECHA;
		assertThat(ERROR_DIRECCION_NO_VALIDA, direccion, is(Direccion.DERECHA));
		direccion = Direccion.ENROQUE_CORTO;
		assertThat(ERROR_DIRECCION_NO_VALIDA, direccion, is(Direccion.ENROQUE_CORTO));
		direccion = Direccion.ENROQUE_LARGO;
		assertThat(ERROR_DIRECCION_NO_VALIDA, direccion, is(Direccion.ENROQUE_LARGO));
	}

}
