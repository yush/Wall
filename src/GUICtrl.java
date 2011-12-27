import processing.core.PApplet;
import processing.core.PGraphics;
import controlP5.*;

public class GUICtrl {
	  public Wall p55;
	  public ControlP5 ctrlControlP5;
	  public PGraphics screenBuf;
	  public PGraphics backBuf;
	  
	  GUICtrl(Wall aP55, PGraphics aBuffer, PGraphics aBackBuffer) {
	    p55 = aP55;
		ctrlControlP5 = new ControlP5(p55);
	    screenBuf = aBuffer;
	    backBuf = aBackBuffer;
	  }	
	  
	  public void createInterface() {
		    ScrollList l;
		    controlP5.Button bt;
		    
		    ctrlControlP5.addSlider("size",0,255,100, p55.CanvasWidth+10,270,100,14).setId(8);
		    
		    ctrlControlP5.addButton("addObject",128, p55.CanvasWidth+10,110,80,20).setId(2);
		    
		    ctrlControlP5.addSlider("red",0,255,100, p55.CanvasWidth+10,50,100,14).setId(3);
		    ctrlControlP5.addSlider("green",0,255,100, p55.CanvasWidth+10,70,100,14).setId(4);
		    ctrlControlP5.addSlider("blue",0,255,100, p55.CanvasWidth+10,90,100,14).setId(5);
		    
		    ctrlControlP5.addButton("print",128, p55.CanvasWidth+10,140,80,20).setId(6);
		    /*
		     * Boutons changement forme
		     */
		    l = ctrlControlP5.addScrollList("headTypeList", p55.CanvasWidth+10,180,80,100);
		    bt = l.addItem("circle",0);
		    bt.setId(100);
		    bt = l.addItem("triangle",1);
		    bt.setId(101);   
		    
		    /*
		     * Boutons Z buffer
		     */
		    ctrlControlP5.addSlider("hwZBuffer",-100,100,0, p55.CanvasWidth+10,250,80,14).setId(7);
		  } 

	  public void drawBackground() {
		    screenBuf.fill(0);
		    screenBuf.rect(0,0,p55.CanvasWidth,p55.CanvasHeight);
		    screenBuf.fill(100);
		    screenBuf.rect(0,p55.CanvasWidth,p55.CanvasHeight,p55.CanvasWidth+p55.ToolBarWidth);
		    backBuf.background(255);  
		  }	  

	  public void dispatchEvent(ControlEvent theEvent) {
		    ObjList tParent;
		    if ((p55.selectedObj != null) && theEvent.isController()) {
		      switch(theEvent.controller().id()) {
		        case(2):
		          p55.theHWList.add(new ObjList(p55, this));
		          break;
		        case(3):
		          int redValue = (int)theEvent.controller().value();
		          p55.selectedObj.setObjColor(p55.color(redValue,
		        		  						p55.green(p55.selectedObj.getObjColor()),
		        		  						p55.blue(p55.selectedObj.getObjColor())));
		          break;
		        case(4):
		          int greenValue = (int)theEvent.controller().value();
		          p55.selectedObj.setObjColor(p55.color(p55.red(p55.selectedObj.getObjColor()),
		        		  					greenValue,
		        		  					p55.blue(p55.selectedObj.getObjColor())));
		          break;
		        case(5):
		          int blueValue = (int)theEvent.controller().value();
		          p55.selectedObj.setObjColor(p55.color(p55.red(p55.selectedObj.getObjColor()),
		        		  						p55.green(p55.selectedObj.getObjColor()),
		        		  						blueValue));
		          break;
		        case(6):
		        	p55.theHWList.toString();
		  	break;
		  /*
		        case(7):
		          selectedObj.queue.center.z = theEvent.controller().value();
		          break;
		          */
		        case(8):
		        p55.selectedObj.setObjSize((int)theEvent.controller().value());
		          break;
		        case(100):
		          tParent = p55.selectedObj.parent;
		          tParent.head = new ObjArrow(p55, p55.selectedObj);
		          break;
		        case(101):
		          tParent = p55.selectedObj.parent;
		          tParent.head = new ObjCir(p55, p55.selectedObj);
		          break;
		      }
		    }
		  }

}

