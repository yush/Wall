import processing.core.PApplet;
import processing.core.PGraphics;
import controlP5.*;

// Ajout de ObjHw et donc de ObjTemplate

public class Wall extends PApplet {
	
	GUICtrl aGUICtrl;
	int CanvasHeight = 400;
	int CanvasWidth = 600;
	int ToolBarWidth = 150;
	public boolean lPressed=false;
	public ObjHWCollection theHWList;
	PGraphics backBuffer;
	
	public ObjTemplate selectedObj;
	public ObjList selectedObjList;
	
	public void setup(){ 
		  smooth();
		  size(CanvasWidth+ToolBarWidth, CanvasHeight*2);
		  backBuffer = createGraphics(CanvasWidth, CanvasHeight, JAVA2D);
		  aGUICtrl = new GUICtrl(this, this.g, backBuffer);
		  aGUICtrl.createInterface();	
		  theHWList = new ObjHWCollection(this, aGUICtrl);
		  theHWList.add(new ObjList(this, aGUICtrl));
		  stroke(0,0,0);		  
		} 
		 
	public void draw(){
		  int i;
		  ObjList anObj;
		  aGUICtrl.drawBackground();
		  for(i=0; i < theHWList.size(); i++) {
			    anObj = (ObjList)theHWList.get(i);
			    anObj.drawObj();
			  }
			  theHWList.drawObjInBuffer();
			  image(backBuffer, 0, CanvasHeight);
	}
	
	public void mousePressed() {
		  ObjList aHWObj;
//		  lPressed = true;
		  int i;
		  if (mouseX < CanvasWidth) {
			  selectedObj = theHWList.getObjectAtPos(mouseX, mouseY);
			  if ( selectedObj != null ) {   
			    selectedObj.isDragged = true;
				selectedObj.loadParametersUI();
			  }
			  else {
				selectedObj = null;
				selectedObjList = null;
			    System.out.println("no selection");
			  }
			}
		}

	public	void mouseReleased() {
		  ObjList anObj;
		  int i=0;
//		  lPressed = false;
		  if (selectedObj != null) {
			  selectedObj.isDragged = false;
		  }
		}

		public void controlEvent(ControlEvent theEvent) {
		  aGUICtrl.dispatchEvent(theEvent);
		}

}
