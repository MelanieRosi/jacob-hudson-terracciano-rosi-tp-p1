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
 Image fondo;//AGREGADO
 Image imgGrinch;
 Image imgRosa; 
 Image imgRegalo;
 Zombie[] zombies;
 Planta planta;
 BolaDeFuego bola;
 Regalo regalo;

	
 // Variables y métodos propios de cada grupo
 // ...
 
 Juego()
 {
  // Inicializa el objeto entorno
  this.entorno = new Entorno(this, "La Invasión de los Zombies Grinch", 800, 600);
  fondo = Herramientas.cargarImagen("imagenes/fondo.png");;
  
  this.zombies = new Zombie[5];
  this.planta = new Planta(100, 357, 40, 60); //cordenadas de la rosa  (100x357 px + su tamañp
  this.regalo = new Regalo (35, 160, 40, 60);
   
  
  for(int i = 0; i < this.zombies.length; i++)
   this.zombies[i] = new Zombie(750, 159 + 98*i, 60, 60, Math.random()+0.1); 
  //cada celda es de 98px, y la barra de menu es de 110px
  //así que 110+(98/2)=159(la mitad de la primera celda) 
  //sumando 98 cada vez, cada zombie queda en el medio de las celdas de cada fila
  
  //750 zombies empiezan de forma horizontal
  //60 + 120*i aparecen en filas fijas no aleatorias
  //velocidad aleatoria
 
  // Inicia el juego!
  this.entorno.iniciar();
 }

 /**
  * Durante el juego, el método tick() será ejecutado en cada instante y 
  * por lo tanto es el método más importante de esta clase. Aquí se debe 
  * actualizar el estado interno del juego para simular el paso del tiempo 
  * (ver el enunciado del TP para mayor detalle).
  */
 public void tick() //cada tick actualiza posiciones de zombies y dibuja todo de nuevo plantas y zombies
 {
  // Procesamiento de un instante de tiempo
  // ...
     entorno.dibujarImagen(fondo, 400, 300, 0); // 800x600 → centro en 400,300
     this.planta.dibujar(entorno);
   // Dibujar fondo en el centro
     
     this.regalo.dibujar(entorno);
  

  for(int i = 0; i < this.zombies.length; i++)
  {
   this.zombies[i].mover();
   this.zombies[i].dibujar(this.entorno);
  }
     
  if(this.entorno.sePresiono(this.entorno.TECLA_ESPACIO))
  {
   this.bola = this.planta.disparar();
  }
  
  if(this.bola != null) {
   this.bola.dibujar(entorno);
   this.bola.mover();
  }
 }
 
 @SuppressWarnings("unused")
 public static void main(String[] args)
 {
  Juego juego = new Juego();
 }
}
