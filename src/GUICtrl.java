import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

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
		    
		    // bouton save
		    ctrlControlP5.addButton("btnSave",128, p55.CanvasWidth+10,280,80,20).setId(110);
		    ctrlControlP5.addButton("btnLoad",128, p55.CanvasWidth+10,320,80,20).setId(111);
		    ctrlControlP5.addButton("btnExport",128, p55.CanvasWidth+10,350,80,20).setId(112);
		  
	  } 

	  public void drawBackground() {
		    screenBuf.fill(100);
		    screenBuf.rect(0,0,p55.CanvasWidth,p55.CanvasHeight);
		    screenBuf.fill(100);
		    screenBuf.rect(0,p55.CanvasWidth,p55.CanvasHeight,p55.CanvasWidth+p55.ToolBarWidth);
		    backBuf.background(255);  
		  }	  

	  public void dispatchEvent(ControlEvent theEvent) throws IOException, ClassNotFoundException {
		    ObjList tParent;
		    ObjNode aObjNode;
		    String xml;
		    if ( theEvent.isController()) {
		      if (p55.selectedNode != null) {
		      switch(theEvent.controller().id()) {
		        case(3):
		          int redValue = (int)theEvent.controller().value();
		          p55.selectedNode.setObjColor(p55.color(redValue,
		        		  						p55.green(p55.selectedNode.getObjColor()),
		        		  						p55.blue(p55.selectedNode.getObjColor())));
		          break;
		        case(4):
		          int greenValue = (int)theEvent.controller().value();
		          p55.selectedNode.setObjColor(p55.color(p55.red(p55.selectedNode.getObjColor()),
		        		  					greenValue,
		        		  					p55.blue(p55.selectedNode.getObjColor())));
		          break;
		        case(5):
		          int blueValue = (int)theEvent.controller().value();
		          p55.selectedNode.setObjColor(p55.color(p55.red(p55.selectedNode.getObjColor()),
		        		  						p55.green(p55.selectedNode.getObjColor()),
		        		  						blueValue));
		          break;
		        case(6):
		        	System.out.print(p55.theHWList.toXml());
		        	break;
		        case(8):
		        p55.selectedNode.setObjSize((int)theEvent.controller().value());
		          break;
		          
		        case(100):
		          tParent = p55.selectedNode.parent;
		          aObjNode =  new ObjArrow(p55, p55.selectedNode);
		          if (tParent.head == p55.selectedNode) {
		        	  tParent.head = aObjNode;
		        	  p55.selectedNode = aObjNode; 
		          } 
		          else if (tParent.queue == p55.selectedNode) {
		        	  tParent.queue = aObjNode;
		        	  p55.selectedNode = aObjNode; 
		          } 			          
		          break;
		          
		        case(101):
		          tParent = p55.selectedNode.parent;
		          aObjNode = new ObjCir(p55, p55.selectedNode);
		          if (tParent.head == p55.selectedNode) {
		        	  tParent.head = aObjNode;
		        	  p55.selectedNode = aObjNode; 			        	  
		          } 
		          else if (tParent.queue == p55.selectedNode) {
		        	  tParent.queue = aObjNode;
		        	  p55.selectedNode = aObjNode; 			        	  
		          } 
		          break;
		      }
		    }
		    switch ( theEvent.controller().id() ) {
	    	case 2:
	    		p55.theHWList.add(new ObjList(p55));
	    		break;		    
	    	case 110:
	    		System.out.println("save");
	    		if (p55.selectedNode != null) {

	    			p55.theHWList.serialize("wall.ser");
	    		}
				break;
	    	case 111:
	    		p55.theHWList.unserialize("wall.ser");
	    		break;
		    case 112:
		    	System.out.print(p55.theHWList.toXml());
		    	break;
		    }

		  }
	  }

}

