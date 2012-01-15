import java.io.Serializable;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PGraphics;
import controlP5.ControlP5;

public class ObjList implements Serializable {
	  transient ControlP5 aController;
	  transient PGraphics ptrScreen;
	  transient private Wall p55;
	  transient private PGraphics _backBuffer;  //backbuffer a remonter dans objet controlleur (a creer?)
	  transient private int selectionWeight= 2;
	  transient private int contourWeight= 10;
	  transient public boolean selected;
	  transient public ObjNode selectedNode;
	  transient public ArrayList nodeList;
	  
	  public int objColor;
	  public ObjNode head;
	  public ObjNode queue;
	  transient public ObjLink aLink;

	public int getObjColor() {
		return objColor;
	}

	public void setObjColor(int objColor) {
		this.objColor = objColor;
	}

	public ObjNode getHead() {
		return head;
	}

	public void setHead(ObjNode head) {
		this.head = head;
	}

	public ObjNode getQueue() {
		return queue;
	}

	public void setQueue(ObjNode queue) {
		this.queue = queue;
	}

	public ObjLink getaLink() {
		return aLink;
	}

	public void setaLink(ObjLink aLink) {
		this.aLink = aLink;
	}

	public ObjList(Wall aP55) { 
	    p55 = aP55;
	    ptrScreen = p55.aGUICtrl.screenBuf;
	    _backBuffer = p55.aGUICtrl.backBuf;
	    aController = p55.aGUICtrl.ctrlControlP5;
	    nodeList = new ArrayList();
	    head = new ObjCir(p55, 200,200,50, this, aController);
	    queue = new ObjArrow(p55, 200,200,40,this, aController);
	    nodeList.add(head);
	    //aLink = new ObjLink(p55, 200,200,200,250,10);
	    aLink = new ObjLink(p55, (int)head.center.x,(int)head.center.y,(int)queue.center.x,(int)queue.center.y,10);
	    objColor = p55.color(255,220,0);
	    selectedNode = null; 
	  }
	
	public ObjList(Wall aP55, ObjList aObjListData) {
	    p55 = aP55;
	    ptrScreen = p55.aGUICtrl.screenBuf;
	    _backBuffer = p55.aGUICtrl.backBuf;
	    aController = p55.aGUICtrl.ctrlControlP5;
//	    nodeList = new ArrayList();
	    if (aObjListData.head.getClass() == ObjCir.class ) {
	    	head = new ObjCir(p55, (int)aObjListData.head.center.x, (int)aObjListData.head.center.y, aObjListData.head.getObjSize(), this, aController);
	    }
	    else if (aObjListData.head.getClass() == ObjArrow.class ) {
	    	head = new ObjArrow(p55, (int)aObjListData.head.center.x, (int)aObjListData.head.center.y, aObjListData.head.getObjSize(), this, aController);	    	
	    }
	    
	    if (aObjListData.queue.getClass() == ObjCir.class) {
	    	queue = new ObjCir(p55, (int)aObjListData.queue.center.x, (int)aObjListData.queue.center.y, aObjListData.queue.getObjSize(), this, aController);
	    }
	    else if (aObjListData.queue.getClass() == ObjArrow.class) {
    		queue = new ObjArrow(p55, (int)aObjListData.queue.center.x, (int)aObjListData.queue.center.y, aObjListData.queue.getObjSize(), this, aController);	    	
	    }
	    aLink = new ObjLink(p55, (int)head.center.x,(int)head.center.y,(int)queue.center.x,(int)queue.center.y,10);
	    objColor = aObjListData.objColor ;
	    selectedNode = null; 		
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

	  private ObjNode _isSelectedBackBuffer(int x, int y) {
//	    theSelectedObj = null;
	    drawObjInBuffer();
	    //on parcourt la liste des composants
	    if ( head.isAtPos(x,y) ) {
	      head.isSelected = true;
	      selectedNode = head;
	    }
	    else if (queue.isAtPos(x,y)) {
	      queue.isSelected = true;
	      selectedNode = queue;
	    }
	    else {
	    	selectedNode = null;
	    }
	    return selectedNode;
	  }

	  private void _drawSelection(PGraphics aBuffer) {
	      aBuffer.beginDraw();
	      aBuffer.fill(255,0,0);
	      _drawObj(aBuffer, contourWeight+selectionWeight, 0); 
	      aBuffer.endDraw(); 
	  }
	  
	  public boolean isSelected(int clickX, int clickY) {
		_isSelectedBackBuffer(clickX, clickY); 
	    return (selectedNode != null);
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
		  drawIt(p55.aGUICtrl.backBuf, p55.color(255) ,1);
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