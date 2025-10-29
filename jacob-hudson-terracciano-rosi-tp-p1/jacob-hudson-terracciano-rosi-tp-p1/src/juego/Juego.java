package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
 // El objeto Entorno que controla el tiempo y otros
 private Entorno entorno;
 Image fondo;
 Image imgGrinch;
 Image imgRosa; 
 Image imgRegalo;
 //Image imgVictoria;
 //Image imgDerrota;
 //-----------------
 Zombie[] zombies;
 //-----------------
 Planta planta;
 Planta[] plantas;//plantas plantadas
 //----------------- 
 BolaDeFuego bola;
 BolaDeFuego[] bolas;
 long ultimoDisparo = 0;
 final long intervaloDisparo = 1000; // cada 1 segundo
 //-----------------
 Regalo[] regalo;
 //-----------------
 long tiempoInicio; //momento en que comienza el juego
 //-----------------
 boolean inhArriba;
 boolean inhAbajo;
 boolean inhIzquierda;
 boolean inhDerecha;
//-----------------
 boolean rosaSeleccionada =false;
 boolean puedeSeleccionar = true;
 long tiempoSeleccion = 0;
 final long tiempoEspera = 5000;// 5 segundos, esta configurado en milisegundos
 
//delimitación de las casillas para que las rosas nunca sobrepasen los bordes o zombies
 int filas = 5;
 int columnas = 10;
 double altoCelda = 98;
 double anchoCelda =  (800 - 130) / (double) columnas;  //ancho del campo útil (~67 px por columna)
 double inicioX = 130 / 2.0; //pequeño margen desde la izquierda
 double inicioY = 110;//debajo del menú
 
 
//boolean juegoTerminado = false;
//boolean plantaGano = false;
 
 
 // Variables y métodos propios de cada grupo
 Juego()
 {
  // Inicializa el objeto entorno
  this.entorno = new Entorno(this, "La Invasión de los Zombies Grinch", 800, 600);
  fondo = Herramientas.cargarImagen("imagenes/fondo.png");;
  
  this.zombies = new Zombie[5];

  //this.planta = new Planta (100, 357,entorno);   //cordenadas de la rosa que aparece primera (100x357 px + su tamaño)
  this.plantas = new Planta[50];// Arreglo fijo de plantas colocadas (por ejemplo, hasta 10)
 

   for(int i = 0; i < this.zombies.length; i++)
   this.zombies[i] = new Zombie(750, 159 + 98*i, 60, 60, Math.random()+0.1); 
   
   
   this.regalo = new Regalo[5];
   for(int i = 0; i < this.regalo.length; i++)
	   this.regalo[i] = new Regalo(30, 150 + 98*i, entorno); 
 
  //cada celda es de 98px, y la barra de menu es de 110px
  //así que 110+(98/2)=159(la mitad de la primera celda) 
  //sumando 98 cada vez, cada regalo queda en el medio de las celdas de cada fila
 
   this.bolas = new BolaDeFuego[200];// Por ejemplo, hasta 200 bolas activas al mismo tiempo
   
   this.tiempoInicio = System.currentTimeMillis(); //cronómetro empieza desde que se ejecuta el juego.
   
   
  //imgVictoria = Herramientas.cargarImagen("imagenes/victoria.png");
  //imgDerrota = Herramientas.cargarImagen("imagenes/derrota.png"); 
  
  // Inicia el juego!
 this.entorno.iniciar(); 
 }

 /**
  * Durante el juego, el método tick() será ejecutado en cada instante y 
  * por lo tanto es el método más importante de esta clase. Aquí se debe 
  * actualizar el estado interno del juego para simular el paso del tiempo 
  * (ver el enunciado del TP para mayor detalle).
  */
 public void tick() 
 
 //cada tick actualiza posiciones de zombies y dibuja todo de nuevo plantas y zombies
 {
  // Procesamiento de un instante de tiempo

   //entorno.dibujarImagen(fondo, 400, 300, 0); // 800x600 → centro en 400,300
	 entorno.dibujarImagen(fondo, entorno.ancho() / 2, entorno.alto() / 2, 0, 1);
	 
//dibujar planta:planta principal de prueba, borrar para entrega	 
//     this.planta.dibujar();
	 
//dibujar zombie	    
	  for(int i = 0; i < this.zombies.length; i++)
	  {
		  this.zombies[i].mover();
		  this.zombies[i].dibujar(this.entorno);
	  }
//dibujar regalo
	  for(int i = 0; i < this.regalo.length; i++)
	  {
		 this.regalo[i].dibujar();
	  }
///////////////////////////////

//detectar clic izquierdo en rosas:
	  if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
		  int mx = entorno.mouseX();
		  int my = entorno.mouseY();
		  boolean clicEnPlanta = false;

//verificar si el click fue sobre una planta
	  for (int i = 0; i < plantas.length; i++) {
		  if (plantas[i] != null) {
			  double dx = Math.abs(plantas[i].getX() - mx);
			  double dy = Math.abs(plantas[i].getY() - my);
		  if (dx < 30 && dy < 30) { // margen de clic
		                // Selecciona solo esa planta
	  for (int j = 0; j < plantas.length; j++) {
		  if (plantas[j] != null) plantas[j].deseleccionar();
		   }
		       plantas[i].seleccionar();
		       clicEnPlanta = true;
		       break;
		  }
	 }
}

//si no clickeó una planta ni la carta de rosa --> deseleccionar todas
	  if (!clicEnPlanta && !(mx >= 0 && mx <= 90 && my >= 0 && my <= 110)) {
		 for (int i = 0; i < plantas.length; i++) {
		     if (plantas[i] != null) plantas[i].deseleccionar();
		}
	}
}

//disparo automático de todas las rosas
	  long ahora = System.currentTimeMillis();
	  if (ahora - ultimoDisparo > intervaloDisparo) {
		  for (int i = 0; i < plantas.length; i++) {
			  if (plantas[i] != null) {
				  BolaDeFuego nueva = plantas[i].disparar();

	      for (int j = 0; j < bolas.length; j++) {
	          if (bolas[j] == null) {
	        	  bolas[j] = nueva;
	              	break;
	                }
	            }
	        }
	    }
	    ultimoDisparo = ahora;
}
	
//Mover y dibujar las bolas

	  for (int i = 0; i < bolas.length; i++) {
		  if (bolas[i] != null) {
			  bolas[i].mover();
			  bolas[i].dibujar(entorno);

	        // Si sale de la pantalla, eliminarla
	      if (bolas[i].getX() > 800) {
	          bolas[i] = null;
	        }
	  }
}
	  
//dibujar plantas plantadas
	  
	  for (int i = 0; i < plantas.length; i++) {
		if (plantas[i] != null)
			plantas[i].dibujar();
		}

//seleccionar "carta" de la rosa
	  
	  if (entorno.sePresionoBoton(entorno.BOTON_IZQUIERDO)) {
		  int mx = entorno.mouseX();
		  int my = entorno.mouseY();

//click del botón del mouse izquierdo en la "carta" de la rosa (zona 0–90 x 0–110 pixeles)
		  
	  if (puedeSeleccionar && mx >= 0 && mx <= 90 && my >= 0 && my <= 110) {
		  rosaSeleccionada = true;
		  puedeSeleccionar = false;
		  tiempoSeleccion = System.currentTimeMillis(); // arranca la recarga
	  }
	  else if (rosaSeleccionada && my > 110) {
		  boolean ocupado = false;
		  
//verificar que no haya zombie cerca
		  
	  for (int i = 0; i < zombies.length; i++) {
		  if (Math.abs(zombies[i].getX() - mx) < 50 && Math.abs(zombies[i].getY() - my) < 50) {
			  ocupado = true;
			      break;
		  }
	  }
	  
//alinear posición de las rosas a las casillas 5x10
	  if (!ocupado) {
		int filas = 5;
		int columnas = 10;
		double altoCelda = 98;
		double anchoCelda = (800 - 130) / (double) columnas; // ancho útil del campo
		double inicioX = 130 / 2.0; // margen izquierdo (65 px)
		double inicioY = 110;// menú superior

//determinar celda clickeada
		int fila = (int) ((my - inicioY) / altoCelda);
		int columna = (int) ((mx - inicioX) / anchoCelda);

//limitar dentro del campo
		if (fila < 0) fila = 0;
		if (fila >= filas) fila = filas - 1;
		if (columna < 0) columna = 0;
		if (columna >= columnas) columna = columnas - 1;

//calcular centro de la celda
		double celdaX = inicioX + (columna * anchoCelda) + (anchoCelda / 2);
		double celdaY = inicioY + (fila * altoCelda) + (altoCelda / 2);

//buscar espacio libre
		for (int i = 0; i < plantas.length; i++) {
			if (plantas[i] == null) {
				plantas[i] = new Planta(celdaX, celdaY, entorno);
			    	break;
			 }
		}
	}

			rosaSeleccionada = false;
		}
	}
//actualizar plantas:
	  
		for (int i = 0; i < plantas.length; i++) {
			if (plantas[i] != null) {
				plantas[i].dibujar();
			    }
			}

//barra de carga que indicara el tiempo de espera restante para seleccionar otra rosa; marca 5 segundos 
		if (!puedeSeleccionar) {
			long tiempoActual = System.currentTimeMillis();
			long transcurrido = tiempoActual - tiempoSeleccion;

		if (transcurrido >= tiempoEspera) {
			puedeSeleccionar = true;
		}

//posición de la barra (debajo de la carta)
		double barraX = 55;       // centro horizontal (0–110 px)
		double barraY = 100;      // justo debajo de la carta
		double anchoTotal = 110;  // largo total de la barra
		double alto = 20;         // alto de la barra
		double progreso = Math.min(1.0, (double) transcurrido / tiempoEspera);
			    
//progreso rojo (de izquierda a derecha)
		double anchoProgreso = anchoTotal * progreso;
			entorno.dibujarRectangulo(
				barraX - (anchoTotal / 2) + (anchoProgreso / 2),barraY,anchoProgreso,alto,0,Color.red);
		}
		
//bolas de fuego que tiran las rosas
//  if(this.entorno.sePresiono(this.entorno.TECLA_ESPACIO))
//  {
//   this.bola = this.planta.disparar();
//  }
//  
//  if(this.bola != null) {
//   this.bola.dibujar(entorno);
//   this.bola.mover();
//  }
	  
	  
//inhibiciones, para el movimiento de la rosa en relacion con los bordes:
  this.inhAbajo=false;
  this.inhArriba=false;
  this.inhDerecha = false;
  this.inhIzquierda = false;

//mover rosas con las flechas del teclado
//  if (entorno.estaPresionada(entorno.TECLA_DERECHA) && !inhDerecha) planta.mover(1, 0);
//  if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA) && !inhIzquierda) planta.mover(-1, 0);
//  if (entorno.estaPresionada(entorno.TECLA_ARRIBA) && !inhArriba) planta.mover(0, -1);
//  if (entorno.estaPresionada(entorno.TECLA_ABAJO) && !inhAbajo) planta.mover(0, 1); 
 
  
//mover la planta seleccionada con las flechas del teclado
  	for (int i = 0; i < plantas.length; i++) {
  		if (plantas[i] != null && plantas[i].rosaSeleccionada) {

  			double nuevoX = plantas[i].getX();
  			double nuevoY = plantas[i].getY();

       if (entorno.estaPresionada(entorno.TECLA_DERECHA)) nuevoX += 5;
       if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) nuevoX -= 5;
       if (entorno.estaPresionada(entorno.TECLA_ARRIBA)) nuevoY -= 5;
       if (entorno.estaPresionada(entorno.TECLA_ABAJO)) nuevoY += 5;

       //comprobar si la nueva posición es válida antes de mover:
       if (posicionValida(nuevoX, nuevoY, i)) {
           plantas[i].mover(nuevoX - plantas[i].getX(), nuevoY - plantas[i].getY());
       }

       break; //solo una planta se mueve
   }
}
  
  
  
///////////////////////////////////////////////////////////////////////////////
//informacion del juego estatica para ejemplo de posicion y tamaño
  	entorno.cambiarFont("Times New Roman", 25, Color.white);
	entorno.escribirTexto("30", 430, 20); 
	entorno.cambiarFont("Times New Roman", 25, Color.white);
	entorno.escribirTexto("15", 420, 60);
	
//reloj
//calcular tiempo transcurrido en segundos
  	long tiempoActual = System.currentTimeMillis();
  	long tiempoTranscurrido = (tiempoActual - tiempoInicio) / 1000; // en segundos

//convertir a minutos y segundos
  	long minutos = tiempoTranscurrido / 60;
  	long segundos = tiempoTranscurrido % 60;

//formatear en mm:ss (ej: 03:07)
  	String tiempoFormateado = String.format("%02d:%02d", minutos, segundos);
  	
//mostrar reloj  	
  	entorno.cambiarFont("Times New Roman", 25, Color.white);
  	entorno.escribirTexto(tiempoFormateado, 428, 100);
  	
  	
  	
  	
 
//fin del tick()  
}
 
 private boolean posicionValida(double x, double y, int indicePlanta) {
//límites del tablero
	  if (x < 65 || x > 800 - 30) return false;
	  if (y < 110 || y > 600 - 30) return false;
	  
//colisión con otras plantas cuando la rosa seleccionada esta en movimiento
	  for (int i = 0; i < plantas.length; i++) {
	      if (i != indicePlanta && plantas[i] != null) {
	          double dx = Math.abs(plantas[i].getX() - x);
	          double dy = Math.abs(plantas[i].getY() - y);
	          if (dx < 40 && dy < 40) {
	              return false; //ocupado por otra planta
	        }
	  }
 }

//colisión con zombies cuandp la rosa esta en movimiento
	  for (int i = 0; i < zombies.length; i++) {
	      if (zombies[i] != null) {
	          double dx = Math.abs(zombies[i].getX() - x);
	          double dy = Math.abs(zombies[i].getY() - y);
	          if (dx < 40 && dy < 40) {
	              return false; //ocupado por un zombie
	          }
	   }
 }

	  return true; //se puede mover
}
 @SuppressWarnings("unused")
 public static void main(String[] args)
 {
  Juego juego = new Juego();
 }
}
