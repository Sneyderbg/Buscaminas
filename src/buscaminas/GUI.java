package buscaminas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Clase que se encarga de crear y manejar un campo de minas, así como también
 * de dibujar el campo.
 *
 * @author monto
 */
@SuppressWarnings("serial")
public class GUI extends javax.swing.JFrame {

	public CasillaB matrizBotones[][];
	public CampoDeMinas campoMinas;
	public int columnasP;
	public int filasP;
	public int minasP;

	/**
	 * Creates new form GUI
	 */
	public GUI() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 350, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 350, Short.MAX_VALUE));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	/**
	 * Genera el campo de minas segun la informacion ingresada.
	 * 
	 * @param f     Número de filas del campo.
	 * @param c     Número de columnas del campo.
	 * @param minas Número de minas del campo.
	 */
	public void iniciarJuego(int f, int c, int minas) {

		// se inicializa un objeto de la clase CampoDeMinas
		campoMinas = new CampoDeMinas(f, c, minas);
		campoMinas.iniciar();

		// Se limpia la interfaz y se le asigna un gridLayout del tamaño correspondiente
		this.getContentPane().removeAll();
		this.getContentPane().setLayout(new java.awt.GridLayout(f, c));

		// se inicializa una matriz que contenga todos los botones que forman el campo
		// de minas
		matrizBotones = new CasillaB[f][c];

		for (int i = 0; i < f; i++) {
			for (int j = 0; j < c; j++) {
				CasillaB aux = new CasillaB(i, j);

				aux.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent ae) {
						CasillaB aux2 = (CasillaB) ae.getSource();
						// si se le da click Izquierdo llama al metodo procesar casilla
						if (ae.getModifiers() == ActionEvent.MOUSE_EVENT_MASK) {
							procesarCasilla(aux2);
						}
					}
				});
				// a cada boton generado se le asigna un Mouselistener
				aux.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						CasillaB aux3 = (CasillaB) e.getSource();

						// Por una razon que desconozco con el mouseListener se le tenia que
						// clickear(Click izquierdo) mas de una vez
						// en algunas casilla y con el actionListener no detectaba la interaccion de
						// click derecho con el
						// boton;debido a esto se usan ambos, uno para cada uno

						// Si se da click derecho se marca esa casilla como posible mina
						if (e.getButton() == MouseEvent.BUTTON3) {
							campoMinas.posibleMina(aux3.getFila(), aux3.getColumna());
							if (campoMinas.getEstadoDeCasilla(aux3.getFila(),
									aux3.getColumna()) == CampoDeMinas.EstadoDeCasilla.posibleMina) {
								aux3.setIcon(new ImageIcon(getClass().getResource("/Imagenes/posibleMina.png")));
							} else {
								aux3.setIcon(null);
							}

						}

					}
				});

				aux.setVisible(true);
				aux.setBackground(Color.decode("#4F65FF"));
				this.getContentPane().add(aux);
				matrizBotones[i][j] = aux;
			}
		}
		this.getContentPane().paintAll(this.getGraphics());
	}

	/**
	 * Se toma la casilla x,si es una mina se informa al usuario que perdió si la
	 * casilla no es una mina, se despejan las casilla correspondientes
	 * 
	 * @param x Casilla a la que se le dió click, sea izquierdo o derecho.
	 */
	public void procesarCasilla(CasillaB x) {
		campoMinas.tocarCasilla(x.getFila(), x.getColumna());
		if (campoMinas.getEstado() == CampoDeMinas.Estado.perdido) {
			// informa al usuario de que perdio y pregunta si desea seguir jugando
			if (JOptionPane.showConfirmDialog(null, "Has perdido,deseas jugar de nuevo?", "Game Over",
					JOptionPane.YES_NO_OPTION) == 0) {
				this.dispose();
			} else {
				System.exit(0);
			}
		}
		if (campoMinas.getEstado() == CampoDeMinas.Estado.ganado) {
			// Informa al jugador de que ha ganado
			if (JOptionPane.showConfirmDialog(null, "Has completado este nivel,deseas continuar jugando?",
					"Felicidades!!", JOptionPane.YES_NO_OPTION) == 0) {
				this.dispose();
			} else {
				System.exit(0);
			}
		} else {
			// si no se ha ganado ni perdido, se llama al metodo despejar botones
			despejarbotones();
		}
	}

	/**
	 * Segun sea el valor de n se asignara un nivel de los 4 posibles
	 * 
	 * @param n numero de identificacion de nivel
	 */
	public void seleccionNivel(int n) {
		switch (n) {
		case 1:
			// Se asignan Los tamaños correspondientes al nivel
			this.setSize(new Dimension(380, 350));
			this.getContentPane().setMaximumSize(new Dimension(380, 350));
			this.getContentPane().setMinimumSize(new Dimension(380, 350));
			this.getContentPane().setLayout(null);
			// Se inicia el juego
			iniciarJuego(9, 9, 10);
			break;
		case 2:
			// Se asignan Los tamaños correspondientes al nivel
			this.setSize(new Dimension(608, 560));
			this.getContentPane().setMaximumSize(new Dimension(608, 560));
			this.getContentPane().setMinimumSize(new Dimension(608, 560));
			this.getContentPane().setLayout(null);
			// Se inicia el juego
			iniciarJuego(16, 16, 40);
			break;
		case 3:
			// Se asignan Los tamaños correspondientes al nivel
			this.setSize(new Dimension(1200, 560));
			this.getContentPane().setMaximumSize(new Dimension(1200, 560));
			this.getContentPane().setMinimumSize(new Dimension(1200, 560));
			this.getContentPane().setLayout(null);
			// Se inicia el juego
			iniciarJuego(16, 30, 99);
			break;
		case 4:
			// Se asignan Los tamaños correspondientes al nivel
			this.setSize(new Dimension(columnasP * 38, filasP * 35));
			this.getContentPane().setMaximumSize(new Dimension(columnasP * 38, filasP * 35));
			this.getContentPane().setMinimumSize(new Dimension(columnasP * 38, filasP * 35));
			this.getContentPane().setLayout(null);

			// Se inicia el juego
			iniciarJuego(filasP, columnasP, minasP);
			break;

		}
	}

	/**
	 * Actualiza los botones según sea su estado.
	 */
	public void despejarbotones() {
		// Se recorre la matriz de botones
		for (int i = 0; i < matrizBotones.length; i++) {
			for (int j = 0; j < matrizBotones[i].length; j++) {
				// Se comprueba si el boton esta decubierto
				if (campoMinas.getEstadoDeCasilla(i, j) == CampoDeMinas.EstadoDeCasilla.descubierta) {

					// En caso de estar descubierto se comprueba cual es es valor de esa casilla
					// y se asigna el icono correspondiente
					CasillaB boton = matrizBotones[i][j];
					switch ((int) campoMinas.get(i, j)) {
					case 0:
						boton.setBackground(Color.decode("#ffffff"));
						break;

					case 1:
						boton.setIcon(new ImageIcon(getClass().getResource("/Imagenes/1.png")));
						break;

					case 2:
						boton.setIcon(new ImageIcon(getClass().getResource("/Imagenes/2.png")));
						break;
					case 3:
						boton.setIcon(new ImageIcon(getClass().getResource("/Imagenes/3.png")));
						break;
					case 4:
						boton.setIcon(new ImageIcon(getClass().getResource("/Imagenes/4.png")));
						break;
					case 5:
						boton.setIcon(new ImageIcon(getClass().getResource("/Imagenes/5.png")));
						break;
					case 6:
						boton.setIcon(new ImageIcon(getClass().getResource("/Imagenes/6.png")));
						break;
					case 7:
						boton.setIcon(new ImageIcon(getClass().getResource("/Imagenes/7.png")));
						break;
					case 8:
						boton.setIcon(new ImageIcon(getClass().getResource("/Imagenes/8.png")));
						break;

					}

				}
			}
		}
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	// End of variables declaration//GEN-END:variables
}
