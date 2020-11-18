import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.midi.Synthesizer;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Esta clase se encarga de crear la interfaz principal del Buscaminas se
 * encarga tambien de la sustitucion de los componentes y posee Listener de los
 * botones 
 * 
 * {@link #inicializar()} //Este metodo se encarga de inicializar crear la ventana
 *  y la llamada a los otros metodos que componen el frame 
 * 
 * {@code
    public void inicializar(){
		ventana.setVisible(true);
		inicializarComponentes();	
		inicializarListeners(); 
	} }
 * 
 * @author Mario Gracia Torres
 * @version 1.0
 * @since   1.0
 * @see Esta clase VentanaPrincipal esta unida a la clase
 *      ControlJuego,declarando un atributo de esa clase y lleva la logica del
 *      buscaminas,para poder acceder a sus metodos de forma sencilla
 */

public class VentanaPrincipal {

	// La ventana principal guarda la mayoria de los componentes, poseemos la
	// ventana raiz,paneles y botones,:
	JFrame ventana;
	JPanel panelImagen;
	JPanel panelEmpezar;
	JPanel panelPuntuacion;
	JPanel panelJuego;
	JLabel imagen;

	// Todos los botones se meten en un panel de forma independiente.
	// Se hace esto para despues los botones se puedan cambiar los componentes
	// facilmente
	JPanel[][] panelesJuego;
	JButton[][] botonesJuego;

	// Array Correspondencia de colores para las casillas de las minas,segun la mina
	// que haya en la casilla:
	Color correspondenciaColores[] = { Color.BLACK, Color.CYAN, Color.GREEN, Color.ORANGE, Color.RED, Color.RED,
			Color.RED, Color.RED, Color.RED, Color.RED };

	//
	JButton botonEmpezar;
	JTextField pantallaPuntuacion;

	// La ventana Principal guarda un atributo dirigido de la clase
	// ControlJuego,para llevar la logica del Buscaminas:
	ControlJuego juego;

	// Constructor parametrizado, marca el tamaño del frame, cierre del frame y
	// declara el controlJuego
	public VentanaPrincipal() {
		ventana = new JFrame();
		ventana.setBounds(100, 100, 700, 500);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		juego = new ControlJuego();
		imagen = new JLabel();
	}

	// Inicializa todos los componentes del frame principal
	public void inicializarComponentes() {

		// Definimos el layout,es un GribBagLayout:
		ventana.setLayout(new GridBagLayout());

		// Inicializamos componentes, con los paneles del Juego le damos un GridLayout
		// estableciendole una division de celdas y una separacion entre los componentes
		panelImagen = new JPanel();
		panelEmpezar = new JPanel();
		panelEmpezar.setLayout(new GridLayout(1, 1));
		panelPuntuacion = new JPanel();
		panelPuntuacion.setLayout(new GridLayout(1, 1));
		panelJuego = new JPanel();
		panelJuego.setLayout(new GridLayout(10, 10, 4, 4));

		// Crear el BotonEmpezar y añadirlo a pantallaPuntuacion
		botonEmpezar = new JButton("Go!");
		pantallaPuntuacion = new JTextField("0");
		pantallaPuntuacion.setEditable(false);
		pantallaPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);

		// Bordes y colores de los paneles:
		panelImagen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		panelEmpezar.setBorder(BorderFactory.createTitledBorder("Empezar"));
		panelPuntuacion.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		panelJuego.setBorder(BorderFactory.createTitledBorder("Juego"));

		// Colocamos y modificamos los componentes:

		// PanelImagen
		GridBagConstraints settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		panelImagen.setBackground(Color.WHITE);
		settings.fill = GridBagConstraints.BOTH;

		imagen.setIcon(new ImageIcon("mario.jpg"));
		imagen.setVisible(false);
		panelImagen.add(imagen);

		
		ventana.add(panelImagen, settings);

		// PanelEmpezar
		
		settings = new GridBagConstraints();
		settings.gridx = 1;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelEmpezar, settings);


		// PanelPuntuacion
		settings = new GridBagConstraints();
		settings.gridx = 2;
		settings.gridy = 0;
		settings.weightx = 1;
		settings.weighty = 1;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelPuntuacion, settings);

		// PanelJuego
		settings = new GridBagConstraints();
		settings.gridx = 0;
		settings.gridy = 1;
		settings.weightx = 1;
		settings.weighty = 10;
		settings.gridwidth = 3;
		settings.fill = GridBagConstraints.BOTH;
		ventana.add(panelJuego, settings);

		// Paneles Internos del panelJuego,usando una matriz y añadiendolos en PanelJuego
		panelesJuego = new JPanel[10][10];
		for (int i = 0; i < panelesJuego.length; i++) {
			for (int j = 0; j < panelesJuego[i].length; j++) {
				panelesJuego[i][j] = new JPanel();
				panelesJuego[i][j].setLayout(new GridLayout(1, 1));
				panelJuego.add(panelesJuego[i][j]);
			}
		}

		// Botones Internos de panelJuego,una vez creados, se les desabilita su uso.
		botonesJuego = new JButton[10][10];
		for (int i = 0; i < botonesJuego.length; i++) {
			for (int j = 0; j < botonesJuego[i].length; j++) {
				botonesJuego[i][j] = new JButton("-");
				panelesJuego[i][j].add(botonesJuego[i][j]);
				botonesJuego[i][j].setEnabled(false);
			}
		}

		// BotónEmpezar se le añade en panelEmpezar y la pantallaPuntuacion en
		// panelPuntuacion:
		panelEmpezar.add(botonEmpezar);
		panelPuntuacion.add(pantallaPuntuacion);

	}

	/**
	 * Método que inicializa todos los lísteners que necesita inicialmente el programa
	 */
	//Los listeners de los botones del panelJuego,se recorre y se les añade dependiendo de la posicion que se encuentra

	public void inicializarListeners() {
		for (int i = 0; i < panelesJuego.length; i++) {
			for (int j = 0; j < panelesJuego[i].length; j++) {
				botonesJuego[i][j].addActionListener(new ActionBoton(this, i, j));
			}
		}
		//Se crea un nuevo listener al ser pulsado el BotonEmpezar.
		//Este listener borra los paneles del juego,llama al metodo inicializarPartida.
		//Añade los botones a los paneles,los habilita para su uso, actualiza la puntuacion a 0.
		//Y finalmete llama al metodo refrescarPantalla cada vez que pulsemos un boton del panelJuego
		botonEmpezar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < panelesJuego.length; i++) {
					for (int j = 0; j < panelesJuego[i].length; j++) {
						panelesJuego[i][j].removeAll();
					}
				}
				juego.inicializarPartida();
				actualizarPuntuacion();
				imagen.setIcon(new ImageIcon("mario.jpg"));
				imagen.setVisible(true);
				for (int i = 0; i < panelesJuego.length; i++) {
					for (int j = 0; j < panelesJuego[i].length; j++) {
						panelesJuego[i][j].add(botonesJuego[i][j]);
						botonesJuego[i][j].setEnabled(true);
						actualizarPuntuacion();
					}
				}
				refrescarPantalla();
			}
		});

	}

	/**
	 * Pinta en la pantalla el número de minas que hay alrededor de las celdas.Borra el boton del panel 
	 * de la posicion escogida pasada por parametros. Se le añade un JLabel con el
	 * numero de minas alrededor de forma centrada,se añade un fondo dependiendo
	 * del numero de minas que hay,y se hace por posicion del array de corespondenciaColor
	 * se añade el nuevo panel moficiado,se actaliza la puntuacion y se vuelve a refrescar la pantalla
	 * 
	 * @param i: posición vertical de la celda.
	 * @param j: posición horizontal de la celda.
	 */
	public void mostrarNumMinasAlrededor(int i, int j) {
		panelesJuego[i][j].remove(botonesJuego[i][j]);

		JLabel panelMinaAlrededor = new JLabel();
		panelMinaAlrededor.setHorizontalAlignment(SwingConstants.CENTER);
		panelMinaAlrededor.setText("" + juego.getMinasAlrededor(i, j));
		panelMinaAlrededor.setForeground(correspondenciaColores[juego.getMinasAlrededor(i, j)]);

		panelesJuego[i][j].add(panelMinaAlrededor);
		actualizarPuntuacion();
		refrescarPantalla();
	}

	/**
	 * Muestra una ventana que indica el fin del juego,muestra la puntuacion obtenida,
	 *  los botones del juego se desactivan excepto el botonEmpezar
	 * 
	 * @param porExplosion :booleano que indica true si es final del juego porque ha
	 * explotado una mina ha explotado,en caso contrario sera false
	 * 
	 * @post : Todos los botones se desactivan excepto el de volver a iniciar el
	 *       juego.
	 */
	public void mostrarFinJuego(boolean porExplosion) {
		if (porExplosion == true) {
			for (int i = 0; i < panelesJuego.length; i++) {
				for (int j = 0; j < panelesJuego[i].length; j++) {
					botonesJuego[i][j].setEnabled(false);
				}
			}
			imagen.setIcon(new ImageIcon("mariodeath.jpg"));
			JOptionPane.showMessageDialog(ventana, "Game Over \n" + "Puntacion Conseguida: " + juego.getPuntuacion());
		} else {
			if (juego.esFinJuego()) {
				for (int i = 0; i < panelesJuego.length; i++) {
					for (int j = 0; j < panelesJuego[i].length; j++) {
						botonesJuego[i][j].setEnabled(false);

					}
				}
				imagen.setIcon(new ImageIcon("mariojump.jpg"));
				JOptionPane.showMessageDialog(ventana,
						"¡Enhorabuena,has ganado! \n" + "Puntacion Conseguida: " + juego.getPuntuacion());
			}
		}
	}

	/**
	 * Método que muestra la puntuación por pantalla.
	 * Se va actualizando en caso de como vaya avanzando el jugador
	 */
	public void actualizarPuntuacion() {
		pantallaPuntuacion.setText(Integer.toString(juego.getPuntuacion()));
	}

	/**
	 * Método para refrescar la pantalla del juego
	 */
	public void refrescarPantalla() {
		ventana.revalidate();
		ventana.repaint();
	}

	/**
	 * Método que devuelve el atributo de ControlJuego del juego de una ventana
	 * 
	 * @return un componente de la clase  ControlJuego de la ventana
	 */
	public ControlJuego getJuego() {
		return juego;
	}

	/**
	 * Método para inicializar el programa del frame
	 */
	public void inicializar() {
		//Se hace que la ventana pueda ser visible
		//Y se inicializa los componentes y listeners del frame
		ventana.setVisible(true);
		inicializarComponentes();
		inicializarListeners();
	}

}