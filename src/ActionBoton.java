import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que implementa el listener de los botones del Buscaminas.
 * Acceder a la clase VentanaPrincipal.
 * Se  pasa en el constructor la referencia a la ventana.
 * Se  accede a la variable de tipo ControlJuego
 * {@link #actionPerformed(ActionEvent)} Este metodo se encarga de inicializar las llamadas de todos los botones
 * @author Mario Gracia Torres
 * @version 1.0
 * @since   1.0
 * @see Esta clase esta unida a VentanaPrincipal y a ControlJuego,para que podamos
 * acceder a los metodos de esas clases de forma sencilla
 */
public class ActionBoton implements ActionListener{
VentanaPrincipal ventana;
private int x;
private int y;
	
	//Constructor parametrizado de la clase ActionBoton
	public ActionBoton(VentanaPrincipal ventana, int posicionX,int posicionY) {
		this.ventana = ventana;
		this.x = posicionX;
		this.y = posicionY;	
	}
	
	/**
	 *Acción que ocurrirá cuando pulsamos uno de los botones del tablero.
	 *Creamos un evento listener que detecte la casilla que pulsamos,
	 *Si en la casilla que hemos pulsado hay una mina,sera fin de la partida
	 *En caso contrario se mostrara el numero de minas alrededor.
	 *Y si se abren todas las casillas sin minas,se dara el juego por finalizado dando la enhorabuena.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!ventana.juego.abrirCasilla(x, y)){
			ventana.mostrarFinJuego(true);
		}else{
			ventana.mostrarNumMinasAlrededor(x, y);
			if(ventana.juego.esFinJuego()){
				ventana.mostrarFinJuego(false);
			}
		}
	}

}
