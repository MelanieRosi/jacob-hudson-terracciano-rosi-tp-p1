package juego;
import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Planta {
	private double x;
	private double y;
	private double ancho;
	private double alto;
	double escala;
	boolean direccion;
	Image imgRosa;
	Entorno e;
	double bordeSuperior;
	double bordeInferior;
	double bordeIzquierdo;
	double bordeDerecho;
	boolean rosaSeleccionada;
	 
	//public Planta(int x, int y, int ancho, int alto,Entorno e) {
	public Planta(double x, double y, Entorno e) {
		//super();
		this.x = x;
		this.y = y;
		this.direccion = false;
		this.escala = 0.09;
		this.imgRosa = Herramientas.cargarImagen("imagenes/rosa.png");
		this.alto = imgRosa.getHeight(null) * this.escala;
		this.ancho = imgRosa.getWidth(null) * this.escala;
		this.e=e;
		this.rosaSeleccionada = false;
	}

	//public void dibujar(Entorno entorno)
	public void dibujar()
	{
		e.dibujarImagen(imgRosa, this.x, this.y, 0,this.escala);
		//para probar el correcto funcionamiento de la seleccion, borrar para la entrega
//		if (rosaSeleccionada) {
//		    e.dibujarRectangulo(this.x, this.y, this.ancho + 10, this.alto + 10, 0, Color.yellow);
//		}
	}
	
	public void mover(double dh,double dv) {
	//delimitacion de bordes con los que colisiona la rosa al moverse	
	if(dh >0.0) {this.direccion =true;}
	if(dh < 0.0) {this.direccion=false;}
	this.x +=dh;
	this.y +=dv;
	this.bordeDerecho=this.x+this.ancho/2;
	this.bordeIzquierdo=this.x-this.ancho/2;
	this.bordeSuperior=this.y-this.alto/2;
	this.bordeInferior=this.y+this.alto/2;
	
	 if (bordeSuperior < 110) {
	        this.y = 110 +this.alto / 2;
	    }
	 if (bordeInferior > 600) {
	        this.y = 600 - this.alto / 2;
	    }
	 
	 if (bordeIzquierdo < 65) {
	        this.x = 65 + this.ancho / 2;
	    }
	 if (bordeDerecho > 800) {
	        this.x = 800 - this.ancho / 2;
	    }
	    
	}
	public double getX() {
	    return this.x;
	}

	public double getY() {
	    return this.y;
	}

    public void seleccionar() {
        this.rosaSeleccionada = true;
    }

    public void deseleccionar() {
        this.rosaSeleccionada = false;
    }
//	public BolaDeFuego disparar() {
//		return new BolaDeFuego(this.x, this.y, 10, 10, 3);
//	}
	
    public BolaDeFuego disparar() {
        return new BolaDeFuego(this.x + 25, (int)this.y, 10, 10, 3);
    }
}
