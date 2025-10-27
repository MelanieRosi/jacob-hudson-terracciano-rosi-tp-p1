package juego;
import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Regalo {
		private double x;
		private int y;
		private int ancho;
		private int alto;
		double escala = 0.30;
		Image imgRegalo;
			
		public Regalo (int x, int y, int ancho, int alto) {
			this.x = x;
			this.y = y;
			this.ancho = ancho;
			this.alto = alto;
			
			this.imgRegalo= Herramientas.cargarImagen("imagenes/regalo.PNG");
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
			//entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.GREEN);
			entorno.dibujarImagen(imgRegalo, this.x, this.y, 0,escala);
		}
		
		
	}



