import java.util.ArrayList;
import java.util.Random;

/**
 * Clase gestora del tablero de juego.
 * Guarda una matriz de enteros representado el tablero.
 * Si hay una mina en una posición guarda el número -1
 * Si no hay una mina, se guarda cuántas minas hay en su alrededor.
 * Almacena la puntuación de la partida
 * 
 *{@link #inicializarPartida()} //Este metodo se encarga de repartir las minas del tablero
 * @author Mario Gracia Torres
 * @version 1.0
 * @since 1.0
 * @see Esta clase VentanaPrincipal se encarga del funcionamiento de la logica del juego
 *
 */
public class ControlJuego {
	//atributos del ControlJuego,minas y forma del tablero
	private final static int MINA = -1;
	final int MINAS_INICIALES = 20;
	final int LADO_TABLERO = 10;

	private int [][] tablero;
	private int puntuacion;
	
	
	public ControlJuego() {
		//Creamos el tablero que posee un total de 100 celdas:
		tablero = new int[LADO_TABLERO][LADO_TABLERO];
		
	}
	
	
	/**Método para generar un nuevo tablero de partida,añade aleatoriamente las minas
	 * sin que coincidan la misma posicion con la mina añadida
	 * @post: El tablero se inicializa añadiendo las inas aleaorias en las celdas
	 * 			se crea una variable que almacene las minas iniciales y no parara de añadr minas hasta que llegue a cero.
	 * 			El resto de posiciones que no son minas guardan  cuántas minas hay alrededor de la celda
	 */
	public void inicializarPartida(){

		//Se reparte las minas e inicializar puntacion. Si hubiese un tablero anterior,se incicializa a cero.
		//Poner la puntuacion y  las posiciones a cero

		//Para colocar las minas, mientras queden minas por colocar,se saca una posicion aleatoria que no coincida con una mina ya puesta y se añade
		tablero = new int [LADO_TABLERO][LADO_TABLERO];
		puntuacion = 0;
		int posicionX = 0;
		int posicionY = 0; 
		Random azar = new Random();
		int minasAñadir = MINAS_INICIALES;
		while(minasAñadir > 0){
			posicionX = azar.nextInt(tablero.length);
			posicionY = azar.nextInt(tablero.length);
			if(tablero[posicionX][posicionY] != MINA){
				tablero[posicionX][posicionY] = MINA;
				minasAñadir--;
			}
		}
		
	
				
		
		//Al final del metodo,se guarda el numero de minas para las casillas que no hay minas,indicando cuantas hay alrededor.
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				if (tablero[i][j] != MINA){
					tablero[i][j] = calculoMinasAdjuntas(i,j);
				}
			}
		}
	}
	
	
	
	/*
	 *Cálculo de las minas adjuntas: 
	 * Para calcular el número de minas tenemos que tener en cuenta que no nos salimos nunca del tablero.
	 * Por lo tanto, como mucho la i y la j valdrán LADO_TABLERO-1.
	 * Por lo tanto, como poco la i y la j valdrán 0.
	 * Una vez evitado salirse del tabero,si encontramos una celda que hay una mina,se incrementara el contador
	 * @param i: posición vertical de la casilla a rellenar
	 * @param j: posición horizontal de la casilla a rellenar
	 * @return : El número de minas que hay alrededor de la casilla escogida
	 **/
	private int calculoMinasAdjuntas(int i, int j){

		int contadorMinasAdjuntas = 0;

		int inicialX = Math.max(0, i-1);

		int finalX =  Math.min(LADO_TABLERO-1,i+1);
		int inicialY =  Math.max(0, j-1);
	
		int finalY =  Math.min(LADO_TABLERO-1,j+1);

		for(int a = inicialX; a <= finalX;a++){
			for(int b = inicialY; b <= finalY;b++){
				if(tablero[a][b] == MINA){
						contadorMinasAdjuntas++;
					}
				}
			}
			return contadorMinasAdjuntas;	
		}	
	/**
	 * Método que permite al abrir una casilla,devuelve true y suma 1 punto sino hay una mina
	 * @pre : La casilla nunca debe haber sido abierta antes, no es controlado por el ControlJuego. Por lo tanto siempre sumaremos puntos
	 * @param i: posición verticalmente de la casilla a abrir
	 * @param j: posición horizontalmente de la casilla a abrir
	 * @return : booleano que retorna si no ha explotado una mina. False en caso contrario.
	 */
	public boolean abrirCasilla(int i, int j){
		boolean noHayMina = false;
		if(tablero[i][j] != MINA){
			noHayMina = true;
			puntuacion++;
		}
		return noHayMina;
	}
	
	
	
	/**
	 * Método que comprueba si se ha terminado el juego, porque se han abierto todas las casillas del tablero.
	 * @return Devuelve true si se han abierto todas las celdas que no son minas.
	 **/
	public boolean esFinJuego(){
		boolean finJuegoGanado = false;
		int puntuacionTotal = (LADO_TABLERO *LADO_TABLERO) - MINAS_INICIALES;
		if(puntuacion == puntuacionTotal){
			finJuegoGanado =true;
		}
		return finJuegoGanado;
	}
	
	
	/**
	 * Método que pinta por pantalla toda la información del tablero, se utiliza para depurar y comprobar 
	 * si los metodos estan funcionando correctamente
	 */
	public void depurarTablero(){
		System.out.println("---------TABLERO--------------");
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				System.out.print(tablero[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println("\nPuntuación: "+puntuacion);
	}

	/**
	 * Método que se utiliza para retornar las minas que hay alrededor de una celda
	 * @pre : El tablero tiene que estar inicializado, solo consultarlo
	 * @param i : posición vertical de la celda.
	 * @param j : posición horizontal de la cela.
	 * @return Un entero que representa el número de minas alrededor que posee la celda
	 */
	public int getMinasAlrededor(int i, int j) {
		return tablero[i][j];	
	}

	/**
	 * Método que retorna la puntuación actual
	 * @return Un entero con la puntuación actual
	 */
	public int getPuntuacion() {
		return puntuacion;
	}
	
}
