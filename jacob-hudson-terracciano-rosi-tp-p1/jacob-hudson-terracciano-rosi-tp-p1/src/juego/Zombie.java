package juego;
import java.awt.Color;
import entorno.Entorno;
import entorno.Herramientas;
import java.awt.Image;


public class Zombie {

	private double x;
	private int y;
	private int ancho;
	private int alto;
	private double velocidad;
	double escala = 0.08;
	Image imgGrinch;
	
	public Zombie(int x, int y, int ancho, int alto, double velocidad) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.velocidad = velocidad;
		this.imgGrinch = Herramientas.cargarImagen("imagenes/grinch.png");
		  
	}

	public double getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}
	
	public void dibujar(Entorno entorno)
	{
		//entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.GRAY);
		entorno.dibujarImagen(imgGrinch, this.x, this.y, 0,escala);
	}
	
	public void mover()
	{
		this.x -= this.velocidad;
	}
	
	
}
