package juego;
import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Regalo {
		private double x;
		private double y;
		private double ancho;
		private double alto;
		double escala = 0.30;
		Image imgRegalo;
		Entorno e;
		double bordeDerecho;	
		//double bordeIzquierdo;
		public Regalo (double x, double y, Entorno e) {
			this.x = x;
			this.y = y;
			this.imgRegalo= Herramientas.cargarImagen("imagenes/regalo.PNG");
			this.alto = imgRegalo.getHeight(null)*this.escala;
			this.ancho = imgRegalo.getWidth(null)*this.escala;
			this.e=e;
			this.bordeDerecho=this.x+this.ancho/2;
			//this.bordeIzquierdo=this.x-this.ancho/2;
		}


		public void dibujar()
		{
			//entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.GREEN);
			e.dibujarImagen(imgRegalo, this.x, this.y, 0,this.escala);
		}
		
		
	}



