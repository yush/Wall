
import java.io.IOException;
import java.lang.annotation.Inherited;
import java.util.Collections;

import com.thoughtworks.xstream.XStream;

import processing.core.PApplet;
import processing.core.PGraphics;
import controlP5.*;

// Ajout de ObjHw et donc de ObjTemplate

public class Wall extends PApplet {
	
	GUICtrl aGUICtrl;
	int CanvasHeight = 400;
	int CanvasWidth = 600;
	int ToolBarWidth = 0;
	public boolean lPressed=false;
	public ObjHWCollection theHWList;
	PGraphics backBuffer;
	
	public ObjNode selectedNode;
	public ObjList selectedObjList;
	public int zIndex=0;
	
	public void setup(){
		  frameRate(30);
		  smooth();
		  size(CanvasWidth+ToolBarWidth, CanvasHeight*2);
		  backBuffer = createGraphics(CanvasWidth, CanvasHeight, JAVA2D);
		  aGUICtrl = new GUICtrl(this, this.g, backBuffer);
		  aGUICtrl.createInterface();	
		  theHWList = new ObjHWCollection(this);
		  theHWList.add(new ObjList(this));
		  stroke(0,0,0);		
		} 
		 
	public void draw(){
		  int i;
		  ObjList anObj;
		  aGUICtrl.drawBackground();
		  Collections.sort(theHWList);
		  for(i=0; i < theHWList.size(); i++) {
			    anObj = (ObjList)theHWList.get(i);
			    anObj.drawObj();
			  }
		  theHWList.drawObjInBuffer();
		  image(backBuffer, 0, CanvasHeight);
	}
	
	public void mouseDragged() {
	  if ( selectedNode != null) {
		selectedNode.isDragged = true;
	  }
	}
	
	public void mousePressed() {
		  ObjNode tObjNode;
//		  lPressed = true;
		  int i;
		  if (mouseX < CanvasWidth) {
			  tObjNode = theHWList.getObjectAtPos(mouseX, mouseY);
			  if (tObjNode != null) {
				  if ( selectedNode != null ) {
//					selectedNode.isDragged = false;
					selectedNode.isSelected = false;
					selectedNode.parent.selected = false;
					selectedNode = null;
					selectedObjList = null;
				  }				
//				tObjNode.isDragged = true;
				tObjNode.isSelected = true;
				tObjNode.parent.selected = true;
			    selectedNode = tObjNode;
			    selectedObjList = tObjNode.parent;
				selectedNode.loadParametersUI();
			   }
			  else {
				if (selectedNode != null) {
					selectedNode.isDragged = false;
					selectedNode.isSelected = false;
					selectedNode.parent.selected = false;
					selectedNode = null;
					selectedObjList = null;
				}
				System.out.println("no selection");
			  }			  
			}
		  //loop();
		}

	public void mouseReleased() {
	  if (selectedNode != null) {
		  selectedNode.isDragged = false;
	  }
	  //noLoop();
	  //draw();
	}

	public void controlEvent(ControlEvent theEvent) throws IOException, ClassNotFoundException {
	  aGUICtrl.dispatchEvent(theEvent);
	}

	public void keyPressed()
	{
		if (key == 'q') {
			exit();
		}
	}
}
