package juego;


import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
//	Zombie[] zombies;
//	Planta planta;
//	BolaDeFuego bola;
	
	
	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "La Invasión de los Zombies Grinch", 800, 600);
		
		

//		this.zombies = new Zombie[5];
//		this.planta = new Planta(50, 300, 40, 60);
//		
//		for(int i = 0; i < this.zombies.length; i++)
//				this.zombies[i] = new Zombie(750, 60 + 120*i, 60, 60, Math.random()+0.1);
		
		
		

		// Inicializar lo que haga falta para el juego
		// ...

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
	{
		// Procesamiento de un instante de tiempo
		// ...
		
		
//			this.planta.dibujar(entorno);
//					
//					for(int i = 0; i < this.zombies.length; i++)
//					{
//						this.zombies[i].mover();
//						this.zombies[i].dibujar(this.entorno);
//					}
//					
//					if(this.entorno.sePresiono(this.entorno.TECLA_ESPACIO))
//					{
//						this.bola = this.planta.disparar();
//					}
//					if(this.bola != null) {
//						this.bola.dibujar(entorno);
//						this.bola.mover();
//					}
//		
	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
