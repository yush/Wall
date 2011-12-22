import processing.core.PApplet;
import processing.core.PGraphics;
import controlP5.*;

// Ajout de ObjHw et donc de ObjTemplate

public class Wall extends PApplet {
	
	GUICtrl aGUICtrl;
	public boolean lPressed=false;
	ObjHWCollection theHWList;
	ObjTemplate selectedObj;
	PGraphics backBuffer;
	
	public void setup(){ 
		  smooth();
		  size(800,400);
		  backBuffer = createGraphics(400,400,JAVA2D);
		  aGUICtrl = new GUICtrl(this, this.g, backBuffer);
		  aGUICtrl.createInterface();	
		  theHWList = new ObjHWCollection(aGUICtrl);
		  theHWList.add(new ObjHW(this, aGUICtrl));
		  stroke(0,0,0);		  
		} 
		 
	public void draw(){
		  int i;
		  ObjHW anObj;
		  aGUICtrl.drawBackground();
		  for(i=0; i < theHWList.size(); i++) {
//			    anObj = (ObjHW)theHWList.get(i);
//			    anObj.drawObj();
			  }
//			  theHWList.drawObjInBuffer();
//			  image(backBuffer, 600, 0);
	} 

}
