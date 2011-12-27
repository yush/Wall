import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PGraphics;
import controlP5.ControlP5;

public class ObjList {
	  //TODO: z buffer
	  ControlP5 aController;
	  PGraphics ptrScreen;
	  private PApplet p55;
	  private PGraphics _backBuffer;  //backbuffer a remonter dans objet controlleur (a creer?)
	  private int selectionWeight= 2;
	  private int contourWeight= 10;
	  
	  public GUICtrl theGUICtrl;
	  public int objColor;
	  public boolean selected;
	  public ObjTemplate theSelectedObj;
	  
	  public ObjTemplate head;
	  public ObjTemplate queue;
	  public ObjLink aLink;
	  
	  public ArrayList nodeList;
	  
	  ObjList(PApplet aP55, GUICtrl tGUICtrl) { 
	    p55 = aP55;
		theGUICtrl = tGUICtrl;
	    ptrScreen = tGUICtrl.screenBuf;
	    _backBuffer = tGUICtrl.backBuf;
	    aController = tGUICtrl.ctrlControlP5;
	    nodeList = new ArrayList();
	    head = new ObjCir(p55, 200,200,50, this, aController);
	    queue = new ObjArrow(p55, 200,200,40,this, aController);
	    nodeList.add(head);
	    aLink = new ObjLink(p55, 200,200,200,250,10);
	    objColor = p55.color(255,220,0);
	    theSelectedObj = null; 
	  }

	  
	  public String toString() {
	    StringBuilder aStr = new StringBuilder();
	    aStr.append(head.toString());
	    aStr.append(queue.toString());
	    return aStr.toString(); 
	  }
	  
	  private void _drawObj(PGraphics aBuffer, int contourWeight, int typeBuffer) {
	    
	    if (head.isDragged) {
	      head.center.x = p55.mouseX;
	      head.center.y = p55.mouseY;
	      aLink.processed = false;
	    }
	    else if (queue.isDragged) {
	      queue.center.x = p55.mouseX;
	      queue.center.y = p55.mouseY;
	      aLink.processed = false;
	    }
	    
	    aLink.psrc1.x = head.center.x;
	    aLink.psrc1.y = head.center.y;   
	    aLink.psrc2.x = queue.center.x;
	    aLink.psrc2.y = queue.center.y;
	    
	    aLink.drawIt(aBuffer, contourWeight, typeBuffer);    
	    head.drawIt(aBuffer, contourWeight, typeBuffer);
	    queue.drawIt(aBuffer, contourWeight, typeBuffer);
	  } 

	  private ObjTemplate _isSelectedBackBuffer(int x, int y) {
//	    theSelectedObj = null;
	    drawObjInBuffer();
	    //on parcourt la liste des composants
	    if ( head.isAtPos(x,y) ) {
	      head.isSelected = true;
	      theSelectedObj = head;
	    }
	    else if (queue.isAtPos(x,y)) {
	      queue.isSelected = true;
	      theSelectedObj = queue;
	    }
	    else {
	    	theSelectedObj = null;
	    }
	    return theSelectedObj;
	  }

	  private void _drawSelection(PGraphics aBuffer) {
	      aBuffer.beginDraw();
	      aBuffer.fill(255,0,0);
	      _drawObj(aBuffer, contourWeight+selectionWeight, 0); 
	      aBuffer.endDraw(); 
	  }
	  
	  public boolean isSelected(int clickX, int clickY) {
		_isSelectedBackBuffer(clickX, clickY); 
	    return (theSelectedObj != null);
	  }
	  
	  
	  public void drawIt(PGraphics aBuffer, int aColor, int typeBuffer) {    
	    aBuffer.beginDraw();  
	    aBuffer.pushStyle();
	    aBuffer.noStroke();
	    aBuffer.fill(0);
	    _drawObj(aBuffer, contourWeight, typeBuffer);
	    aBuffer.fill(aColor);
	    _drawObj(aBuffer, 0, typeBuffer);
	    aBuffer.popStyle();
	    aBuffer.endDraw();
	  }
	  
	  public void drawObj() {
	    if (selected) {
	      _drawSelection(ptrScreen);
	    }
	    drawIt(ptrScreen, objColor, 0);
	  }
	  
	  public void drawObjInBuffer() {
	    drawIt(theGUICtrl.backBuf, p55.color(255) ,1);
	  }
	  
	  public void setUnselected() {
	    head.isSelected = false;
	    queue.isSelected = false;
	  }
	  
	  public void loadParametersUI() {
	     aController.controller("headRadius").setValue(head.getObjSize());
	     aController.controller("queueRadius").setValue(queue.getObjSize());
	     aController.controller("red").setValue(p55.red(objColor));
	     aController.controller("green").setValue(p55.green(objColor));
	     aController.controller("blue").setValue(p55.blue(objColor));
	  }
	  
	  public void xml(StringBuilder tStrXml) {
	     tStrXml.append("<objHW id='test'>");
	     head.toXml(tStrXml);
	     queue.toXml(tStrXml);
	     tStrXml.append("</objHW>");
	  }
}