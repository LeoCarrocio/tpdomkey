package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Mario {
	
	//variables de instancia
	private int x;
	private int y;
	private int alto;
	private int ancho;
	private Image img1;
	private Image img2;
	private Image img3;
	private Image img4;
	private boolean avanzando;
	private boolean cayendo;
	private boolean escala;
	
	public Mario(int x, int y) 
	{
		this.x = x;
		this.y = y;
		this.alto = 20;
		this.ancho = 20;
		this.img1 = Herramientas.cargarImagen("marioida.png");
		this.img2 = Herramientas.cargarImagen("mariovuelta.png");
		this.img3 = Herramientas.cargarImagen("caida.png");
		this.img4 = Herramientas.cargarImagen("sube.png");
		this.avanzando=false;
		this.cayendo=false;
		this.escala=false;
	}
	
	public void dibujarse(Entorno entorno) 
	{
		//entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.cyan);
		if (cayendo) 
		{
			entorno.dibujarImagen(img3, x, y, 0, 0.7);
		}
		if (avanzando && !cayendo)
		{
			entorno.dibujarImagen(img1, x, y, 0, 0.7);
		}
		if(avanzando==false && !cayendo) {
			entorno.dibujarImagen(img2, x, y, 0, 0.7);
		}
		if(escala) {
			entorno.dibujarImagen(img4, x, y, 0, 0.7);
		}

	}
	
	public void moverH(int direccion) //mueve a mario de forma horizontal y setea su estado con los booleanos para las imagenes
	{
		if(direccion>=0) {
			this.x = this.x + direccion;
			avanzando=true;
			cayendo=false;
			escala=false;
		}
		else {
			this.x = this.x + direccion;
			avanzando=false;
			cayendo=false;
			escala=false;
		}
	}
	
	public void moverV(int direccion) //mueve a mario en direccion vertical, esto va a funcionar solo cuando este en la escalera
	{
		this.y = this.y + direccion;
		escala=true;
	}
	
	public void saltar()
	{
		this.y=this.y-50;
	}
	public boolean saltando(Entorno entorno) //el estado de mario si esta saltando o no
	{
		if((entorno.sePresiono(entorno.TECLA_ESPACIO)))
			return true;
		else
			return false;
	}
	
	public void caer() {
		this.y=this.y +3;
		cayendo=true;
	}
		
	public boolean sobrePlataforma(Plataforma [] plataforma) //booleano q dice si mario esta o no en las plataformas
	{
		for(int i=0;i<plataforma.length;i++) 
		{
			if (this.x>=plataforma[i].getX()-plataforma[i].getAncho()/2  && this.x<=plataforma[i].getX()+plataforma[i].getAncho()/2 &&
				this.y<=plataforma[i].getY()-plataforma[i].getAlto()/2 && this.y>= plataforma[i].getY()-plataforma[i].getAlto() )
					return true;	
		}
		return false;
	}
	
	public boolean sobreEscalera(Escalera [] escalera) //otro booleano, este indica si mario esta o no sobre la escalera
	{
		for(int i=0;i<escalera.length;i++) 
		{
			if(this.x>=escalera[i].getX()-escalera[i].getAncho()/2 && this.x<=escalera[i].getX()+escalera[i].getAncho()/2 
					&& this.y+this.alto/2<=escalera[i].getY()+escalera[i].getAlto()/2 && this.y+this.alto/2>=escalera[i].getY()-escalera[i].getAlto()/2)
				return true;
		}
		return false;	
	}
	
    
	public boolean lePega(Barril barril) //cualquier similitud son lePega de pajaros es pura coincidencia :P
	{
		int xx = this.x - barril.getX();
		int yy = this.y - barril.getY();
		double dist = Math.sqrt(xx*xx + yy*yy); 
				
		return dist <= this.ancho/2;
	}
	
//	public boolean quemado(FugaDeGas fuego){
//		
//		int xx = this.x - fuego.getX();
//		int yy = this.y - fuego.getY();
//		double dist = Math.sqrt(xx*xx + yy*yy);				
//		return dist <= this.ancho/2;
//	}
//	
//	public boolean quemado(FugaDeGas[] fuego){
//		for(int i =0; i<fuego.length;i++){
//			if(quemado(fuego[i])==true){
//				return true;
//			}
//		
//		}
//		return false;
//	}

	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getAncho() {
		return this.ancho;
	}
	
	public int getAlto() {
		return this.alto;
	}
	
	public void setX(int a) {
		this.x=a;
	}
	
	public void setY(int b) {
		this.x=b;
	}
}
