import processing.core.PApplet;
import processing.core.PGraphics;
import controlP5.*;

public class GUICtrl {
	
	  public ControlP5 ctrlControlP5;
	  public PGraphics screenBuf;
	  public PGraphics backBuf;
	  
	  GUICtrl(Test aTest, PGraphics aBuffer, PGraphics aBackBuffer) {
	    ctrlControlP5 = new ControlP5(aTest);
	    screenBuf = aBuffer;
	    backBuf = aBackBuffer;
	  }	
	  
	  public void createInterface() {
		    ScrollList l;
		    controlP5.Button bt;
		    
		    ctrlControlP5.addSlider("size",0,255,100,10,270,100,14).setId(8);
		    
		    ctrlControlP5.addButton("addObject",128,10,110,80,20).setId(2);
		    
		    ctrlControlP5.addSlider("red",0,255,100,10,50,100,14).setId(3);
		    ctrlControlP5.addSlider("green",0,255,100,10,70,100,14).setId(4);
		    ctrlControlP5.addSlider("blue",0,255,100,10,90,100,14).setId(5);
		    
		    ctrlControlP5.addButton("print",128,10,140,80,20).setId(6);
		    /*
		     * Boutons changement forme
		     */
		    l = ctrlControlP5.addScrollList("headTypeList",10,180,80,100);
		    bt = l.addItem("circle",0);
		    bt.setId(100);
		    bt = l.addItem("triangle",1);
		    bt.setId(101);   
		    
		    /*
		     * Boutons Z buffer
		     */
		    ctrlControlP5.addSlider("hwZBuffer",-100,100,0,10,250,80,14).setId(7);
		  } 

}
