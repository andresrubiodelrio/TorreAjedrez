package org.iesalandalus.programacion.torreajedrez;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.fail;

import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.torreajedrez.Torre;
import org.iesalandalus.programacion.torreajedrez.Color;
import org.iesalandalus.programacion.torreajedrez.Direccion;
import org.iesalandalus.programacion.torreajedrez.Posicion;
import org.junit.BeforeClass;

public class TorreTest {
	
	private static final String COLOR_NO_ESPERADO = "El color no es el esperado.";
	private static final String POSICION_NO_ESPERADA = "La posición no es la esperada.";
	private static final String COLOR_NO_NULO = "Debería haber saltado una excepción indicando que el color no puede ser nulo.";
	private static final String EXCEPCION_MENSAJE_ADECUADO = "Debería haber saltado una excepción con el mensaje adecuado.";
	private static final String ERROR_COLOR_NULO = "ERROR: No se puede asignar un color nulo.";
	private static final String OBJETO_DEBERIA_SER_NULL = "No se debería haber creado el objeto torre.";
	private static final String NO_EXCEPCION_MOVIMIENTO_VALIDO = "No debería saltar ninguna excepción ya que los movimientos son válidos.";	
	private static final String DIRECCION_NO_NULA = "Debería haber saltado una excepción indicando que no se puede mover en una dirección nula.";
	private static final String ERROR_DIRECCION_NULA = "ERROR: La dirección no puede ser nula.";
	
	private static final String PASOS_NO_POSTIVOS = "Debería haber saltado una excepción indicando que no se puede mover pasos no positivos.";
	private static final String CADENA_NO_ESPERADA = "La cadena devuelta no es la esperada.";
	
	
	private static final String ERROR_MOVIMIENTO_NO_VALIDO = "ERROR: Movimiento no válido (se sale del tablero).";
	private static final String ERROR_ENROQUE_NO_VALIDO = "ERROR: Movimiento de enroque no válido.";

	private static final String ERROR_PASOS_NO_POSITIVOS = "ERROR: El número de pasos debe ser positivo.";
	private static final String ERROR_COLUMNA_NO_VALIDA = "ERROR: Columna no válida.";
	
	private static final String EXCEPCION_MOVIMIENTO_NO_VALIDO = "Debería haber saltado una excepción indicando que el movimiento no es válido.";
	private static final String NO_EXCEPCION = "No debería haber saltado este tipo de excepción.";
	
	private static Posicion posicionDefectoNegro;
	private static Posicion posicionDefectoBlanco;
	
	@BeforeClass
	public static void asignarValoresAtributos() {
		posicionDefectoNegro = new Posicion(8, 'h');
		posicionDefectoBlanco = new Posicion(1, 'h');
	}

	@Test
	public void constructorDefectoCreaTorreNegraEnPosicion8h() {
		Torre torre = new Torre();
		assertThat(COLOR_NO_ESPERADO, torre.getColor(), is(Color.NEGRO));
		assertThat(POSICION_NO_ESPERADA, torre.getPosicion(), is(posicionDefectoNegro));
	}
	
	@Test
	public void constructorColorBlancoCreaTorreBlancaEnPosicion1h() {
		Torre torre = new Torre(Color.BLANCO);
		assertThat(COLOR_NO_ESPERADO, torre.getColor(), is(Color.BLANCO));
		assertThat(POSICION_NO_ESPERADA, torre.getPosicion(), is(posicionDefectoBlanco));
	}
	
	@Test
	public void constructorColorNegroCreaTorreNegraEnPosicion8h() {
		Torre torre = new Torre(Color.NEGRO);
		assertThat(COLOR_NO_ESPERADO, torre.getColor(), is(Color.NEGRO));
		assertThat(POSICION_NO_ESPERADA, torre.getPosicion(), is(posicionDefectoNegro));
	}
		
	@Test
	public void constructorColorNuloLanzaExcepcion() {
		Torre torre = null;
		try {
			torre = new Torre(null);
			fail(COLOR_NO_NULO);
		} catch (NullPointerException e) {
			assertThat(EXCEPCION_MENSAJE_ADECUADO, e.getMessage(), is(ERROR_COLOR_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULL, torre, is(nullValue()));
		}
	}
	
	@Test
	public void constructorColorValidoColumnaValidaCreaTorreCorrectamente() {
		Torre torreBlanco = new Torre(Color.BLANCO, 'a');
		assertThat(COLOR_NO_ESPERADO, torreBlanco.getColor(), is(Color.BLANCO));
		assertThat(POSICION_NO_ESPERADA, torreBlanco.getPosicion(), is(new Posicion(1, 'a')));
		torreBlanco = new Torre(Color.BLANCO, 'h');
		assertThat(COLOR_NO_ESPERADO, torreBlanco.getColor(), is(Color.BLANCO));
		assertThat(POSICION_NO_ESPERADA, torreBlanco.getPosicion(), is(new Posicion(1, 'h')));
		Torre torreNegro = new Torre(Color.NEGRO, 'h');
		assertThat(COLOR_NO_ESPERADO, torreNegro.getColor(), is(Color.NEGRO));
		assertThat(POSICION_NO_ESPERADA, torreNegro.getPosicion(), is(new Posicion(8, 'h')));
		torreNegro = new Torre(Color.NEGRO, 'a');
		assertThat(COLOR_NO_ESPERADO, torreNegro.getColor(), is(Color.NEGRO));
		assertThat(POSICION_NO_ESPERADA, torreNegro.getPosicion(), is(new Posicion(8, 'a')));
	}
	
	@Test
	public void constructorColorNuloColumnaValidaLanzaExcepcion() {
		Torre torre = null;
		try {
			torre = new Torre(null, 'g');
			fail(EXCEPCION_MENSAJE_ADECUADO);
		} catch (NullPointerException e) {
			assertThat(COLOR_NO_NULO, e.getMessage(), is(ERROR_COLOR_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULL, torre, is(nullValue()));
		}
	}
	
	@Test
	public void constructorColorValidoColumnaNoValidaLanzaExcepcion() {
		Torre torre = null;
		try {
			torre = new Torre(Color.BLANCO, '`');
			fail(EXCEPCION_MENSAJE_ADECUADO);
		} catch (IllegalArgumentException e) {
			assertThat(EXCEPCION_MENSAJE_ADECUADO, e.getMessage(), is(ERROR_COLUMNA_NO_VALIDA));
			assertThat(OBJETO_DEBERIA_SER_NULL, torre, is(nullValue()));
		}
		try {
			torre = new Torre(Color.BLANCO, 'i');
			fail(EXCEPCION_MENSAJE_ADECUADO);
		} catch (IllegalArgumentException e) {
			assertThat(EXCEPCION_MENSAJE_ADECUADO, e.getMessage(), is(ERROR_COLUMNA_NO_VALIDA));
			assertThat(OBJETO_DEBERIA_SER_NULL, torre, is(nullValue()));
		}
		try {
			torre = new Torre(Color.NEGRO, '`');
			fail(EXCEPCION_MENSAJE_ADECUADO);
		} catch (IllegalArgumentException e) {
			assertThat(EXCEPCION_MENSAJE_ADECUADO, e.getMessage(), is(ERROR_COLUMNA_NO_VALIDA));
			assertThat(OBJETO_DEBERIA_SER_NULL, torre, is(nullValue()));
		}
		try {
			torre = new Torre(Color.NEGRO, 'i');
			fail(EXCEPCION_MENSAJE_ADECUADO);
		} catch (IllegalArgumentException e) {
			assertThat(EXCEPCION_MENSAJE_ADECUADO, e.getMessage(), is(ERROR_COLUMNA_NO_VALIDA));
			assertThat(OBJETO_DEBERIA_SER_NULL, torre, is(nullValue()));
		}
	}
	
	@Test
	public void moverDireccionValidaPasosValidosCambiaPosicionCorrectamente() {
		Torre torreBlanca = new Torre(Color.BLANCO);
		Torre torreNegra = new Torre(Color.NEGRO);
		try {
			torreBlanca.mover(Direccion.ARRIBA, 2);
			assertThat(POSICION_NO_ESPERADA, torreBlanca.getPosicion(), is(new Posicion(3, 'h')));
			torreBlanca.mover(Direccion.ARRIBA, 5);
			assertThat(POSICION_NO_ESPERADA, torreBlanca.getPosicion(), is(new Posicion(8, 'h')));
			torreBlanca.mover(Direccion.IZQUIERDA, 4);
			assertThat(POSICION_NO_ESPERADA, torreBlanca.getPosicion(), is(new Posicion(8, 'd')));
			torreBlanca.mover(Direccion.ABAJO, 3);
			assertThat(POSICION_NO_ESPERADA, torreBlanca.getPosicion(), is(new Posicion(5, 'd')));
			torreBlanca.mover(Direccion.DERECHA, 1);
			assertThat(POSICION_NO_ESPERADA, torreBlanca.getPosicion(), is(new Posicion(5, 'e')));
			torreNegra.mover(Direccion.ARRIBA, 4);
			assertThat(POSICION_NO_ESPERADA, torreNegra.getPosicion(), is(new Posicion(4, 'h')));
			torreNegra.mover(Direccion.ABAJO, 1);
			assertThat(POSICION_NO_ESPERADA, torreNegra.getPosicion(), is(new Posicion(5, 'h')));
			torreNegra.mover(Direccion.DERECHA, 2);
			assertThat(POSICION_NO_ESPERADA, torreNegra.getPosicion(), is(new Posicion(5, 'f')));
			torreNegra.mover(Direccion.ARRIBA, 2);
			assertThat(POSICION_NO_ESPERADA, torreNegra.getPosicion(), is(new Posicion(3, 'f')));
			torreNegra.mover(Direccion.IZQUIERDA, 2);
			assertThat(POSICION_NO_ESPERADA, torreNegra.getPosicion(), is(new Posicion(3, 'h')));
		} catch (OperationNotSupportedException e) {
			fail(NO_EXCEPCION_MOVIMIENTO_VALIDO);
		}
	}
	
	@Test
	public void moverDireccionNulaPasosValidosLanzaExcepcionYNoMueveTorre() {
		Torre torre = new Torre();
		try {
			torre.mover(null, 1);
			fail(EXCEPCION_MOVIMIENTO_NO_VALIDO);
		} catch (NullPointerException e) {
			assertThat(DIRECCION_NO_NULA, e.getMessage(), is(ERROR_DIRECCION_NULA));
			assertThat(POSICION_NO_ESPERADA, torre.getPosicion(), is(posicionDefectoNegro));
		} catch (OperationNotSupportedException e) {
			fail(NO_EXCEPCION);
		}
	}
	
	@Test
	public void moverDireccionValidaPasosNoValidosLanzaExcepcionYNoMueveTorre() {
		Torre torre = new Torre();
		try {
			torre.mover(Direccion.DERECHA, 0);
			fail(EXCEPCION_MOVIMIENTO_NO_VALIDO);
		} catch (IllegalArgumentException e) {
			assertThat(PASOS_NO_POSTIVOS, e.getMessage(), is(ERROR_PASOS_NO_POSITIVOS));
			assertThat(POSICION_NO_ESPERADA, torre.getPosicion(), is(posicionDefectoNegro));
		} catch (OperationNotSupportedException e) {
			fail(NO_EXCEPCION);
		}
		try {
			torre.mover(Direccion.DERECHA, -1);
			fail(EXCEPCION_MOVIMIENTO_NO_VALIDO);
		} catch (IllegalArgumentException e) {
			assertThat(PASOS_NO_POSTIVOS, e.getMessage(), is(ERROR_PASOS_NO_POSITIVOS));
			assertThat(POSICION_NO_ESPERADA, torre.getPosicion(), is(posicionDefectoNegro));
		} catch (OperationNotSupportedException e) {
			fail(NO_EXCEPCION);
		}
	}
	
	@Test
	public void moverMovimientoNoValidoLanzaExcepcionYNoMueveTorre() {
		Torre torre = new Torre();
		try {
			torre.mover(Direccion.IZQUIERDA, 3);
			fail(EXCEPCION_MOVIMIENTO_NO_VALIDO);
		} catch (OperationNotSupportedException e) {
			assertThat(EXCEPCION_MENSAJE_ADECUADO, e.getMessage(), is(ERROR_MOVIMIENTO_NO_VALIDO));
			assertThat(POSICION_NO_ESPERADA, torre.getPosicion(), is(posicionDefectoNegro));
		}
	}
	
	@Test
	public void enrocarDireccionNulaLanzaExcepcionYNoMueveTorre() {
		Torre torre = new Torre();
		try {
			torre.enrocar(null);
			fail(EXCEPCION_MOVIMIENTO_NO_VALIDO);
		} catch (NullPointerException e) {
			assertThat(DIRECCION_NO_NULA, e.getMessage(), is(ERROR_DIRECCION_NULA));
			assertThat(POSICION_NO_ESPERADA, torre.getPosicion(), is(posicionDefectoNegro));
		} catch (OperationNotSupportedException e) {
			fail(NO_EXCEPCION);
		}
	}
	
	@Test
	public void enrocarMovimientoNoValidoTorreBlancaLanzaExcepcionYNoMueveTorre() {
		Torre torre = new Torre(Color.BLANCO);
		try {
			torre.enrocar(Direccion.ENROQUE_LARGO);
			fail(EXCEPCION_MOVIMIENTO_NO_VALIDO);
		} catch (OperationNotSupportedException e) {
			assertThat(EXCEPCION_MENSAJE_ADECUADO, e.getMessage(), is(ERROR_ENROQUE_NO_VALIDO));
			assertThat(POSICION_NO_ESPERADA, torre.getPosicion(), is(posicionDefectoBlanco));
		}
		
		torre=new Torre(Color.BLANCO,'a');
		try {
			torre.enrocar(Direccion.ENROQUE_CORTO);
			fail(EXCEPCION_MOVIMIENTO_NO_VALIDO);
		} catch (OperationNotSupportedException e) {
			assertThat(EXCEPCION_MENSAJE_ADECUADO, e.getMessage(), is(ERROR_ENROQUE_NO_VALIDO));
			assertThat(POSICION_NO_ESPERADA, torre.getPosicion(), is(new Posicion(1,'a')));
		}
		
		try 
		{
			torre.mover(Direccion.DERECHA, 1);
		} 
		catch (OperationNotSupportedException e) 
		{
			fail(NO_EXCEPCION_MOVIMIENTO_VALIDO);
		}
		
		try {
			torre.enrocar(Direccion.ENROQUE_LARGO);
			fail(EXCEPCION_MOVIMIENTO_NO_VALIDO);
		} catch (OperationNotSupportedException e) {
			assertThat(EXCEPCION_MENSAJE_ADECUADO, e.getMessage(), is(ERROR_ENROQUE_NO_VALIDO));
			assertThat(POSICION_NO_ESPERADA, torre.getPosicion(), is(new Posicion(1,'b')));
		}
		
		try 
		{
			torre.mover(Direccion.DERECHA, 2);
		} 
		catch (OperationNotSupportedException e) 
		{
			fail(NO_EXCEPCION_MOVIMIENTO_VALIDO);
		}
		
		try {
			torre.enrocar(Direccion.ENROQUE_CORTO);
			fail(EXCEPCION_MOVIMIENTO_NO_VALIDO);
		} catch (OperationNotSupportedException e) {
			assertThat(EXCEPCION_MENSAJE_ADECUADO, e.getMessage(), is(ERROR_ENROQUE_NO_VALIDO));
			assertThat(POSICION_NO_ESPERADA, torre.getPosicion(), is(new Posicion(1,'d')));
		}
		
		
	}
	
	@Test
	public void enrocarMovimientoNoValidoTorreNegraLanzaExcepcionYNoMueveTorre() {
		Torre torre = new Torre(Color.NEGRO);
		try {
			torre.enrocar(Direccion.ENROQUE_LARGO);
			fail(EXCEPCION_MOVIMIENTO_NO_VALIDO);
		} catch (OperationNotSupportedException e) {
			assertThat(EXCEPCION_MENSAJE_ADECUADO, e.getMessage(), is(ERROR_ENROQUE_NO_VALIDO));
			assertThat(POSICION_NO_ESPERADA, torre.getPosicion(), is(posicionDefectoNegro));
		}
		
		torre=new Torre(Color.NEGRO,'a');
		try {
			torre.enrocar(Direccion.ENROQUE_CORTO);
			fail(EXCEPCION_MOVIMIENTO_NO_VALIDO);
		} catch (OperationNotSupportedException e) {
			assertThat(EXCEPCION_MENSAJE_ADECUADO, e.getMessage(), is(ERROR_ENROQUE_NO_VALIDO));
			assertThat(POSICION_NO_ESPERADA, torre.getPosicion(), is(new Posicion(8,'a')));
		}
		
		try 
		{
			torre.mover(Direccion.IZQUIERDA, 1);
		} 
		catch (OperationNotSupportedException e) 
		{
			fail(NO_EXCEPCION_MOVIMIENTO_VALIDO);
		}
		
		try {
			torre.enrocar(Direccion.ENROQUE_LARGO);
			fail(EXCEPCION_MOVIMIENTO_NO_VALIDO);
		} catch (OperationNotSupportedException e) {
			assertThat(EXCEPCION_MENSAJE_ADECUADO, e.getMessage(), is(ERROR_ENROQUE_NO_VALIDO));
			assertThat(POSICION_NO_ESPERADA, torre.getPosicion(), is(new Posicion(8,'b')));
		}
		
		try 
		{
			torre.mover(Direccion.IZQUIERDA, 2);
		} 
		catch (OperationNotSupportedException e) 
		{
			fail(NO_EXCEPCION_MOVIMIENTO_VALIDO);
		}
		
		try {
			torre.enrocar(Direccion.ENROQUE_CORTO);
			fail(EXCEPCION_MOVIMIENTO_NO_VALIDO);
		} catch (OperationNotSupportedException e) {
			assertThat(EXCEPCION_MENSAJE_ADECUADO, e.getMessage(), is(ERROR_ENROQUE_NO_VALIDO));
			assertThat(POSICION_NO_ESPERADA, torre.getPosicion(), is(new Posicion(8,'d')));
		}
	}
	
	@Test
	public void toStringTest() {
		Torre torre = new Torre();
		assertThat(CADENA_NO_ESPERADA, torre.toString(), is("fila=8, columna=h, color=NEGRO"));
	}

}
