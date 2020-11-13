import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que implementa el listener de los botones del Buscaminas.
 * De alguna manera tendrá que poder acceder a la ventana principal.
 * Se puede lograr pasando en el constructor la referencia a la ventana.
 * Recuerda que desde la ventana, se puede acceder a la variable de tipo ControlJuego
 * @author jesusredondogarcia
 **
 */
public class ActionBoton implements ActionListener{
VentanaPrincipal ventana;
private int x;
private int y;
	

	public ActionBoton(VentanaPrincipal ventana, int posicionX,int posicionY) {
		this.ventana = ventana;
		this.x = posicionX;
		this.y = posicionY;	
	}
	
	/**
	 *Acción que ocurrirá cuando pulsamos uno de los botones.
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
