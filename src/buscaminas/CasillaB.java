package buscaminas;

import javax.swing.JButton;

/**
 * Clase que representa un botón de la clase {@link JButton} añadiendo la fila y
 * columna, o sea, la posición de un determinado botón en el campo de minas.
 * 
 * @author monto
 */
@SuppressWarnings("serial")
public class CasillaB extends javax.swing.JButton {

	private int fila;
	private int columna;

	/**
	 * Constructor.
	 * 
	 * @param f Fila.
	 * @param c Columna.
	 */
	public CasillaB(int f, int c) {
		this.fila = f;
		this.columna = c;
	}

	/**
	 * @return the fila
	 */
	public int getFila() {
		return fila;
	}

	/**
	 * @param fila the fila to set
	 */
	public void setFila(int fila) {
		this.fila = fila;
	}

	/**
	 * @return the columna
	 */
	public int getColumna() {
		return columna;
	}

	/**
	 * @param columna the columna to set
	 */
	public void setColumna(int columna) {
		this.columna = columna;
	}

}
