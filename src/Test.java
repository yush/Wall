import processing.core.PApplet;
import processing.core.PGraphics;
import controlP5.*;



public class Test extends PApplet {
	
	GUICtrl aGUICtrl;
	PGraphics backBuffer;
	
	public void setup(){ 
		  smooth();
		  size(800,400);
		  backBuffer = createGraphics(400,400,JAVA2D);
		  aGUICtrl = new GUICtrl(this, this.g, backBuffer);
		  aGUICtrl.createInterface();	
		} 
		 
	public void draw(){ 
		rect(50,50,80,80); 
	} 
}
