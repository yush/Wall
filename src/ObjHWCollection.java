import java.util.ArrayList;
import processing.core.PApplet;

public class ObjHWCollection extends ArrayList {
	  public Wall p55;
	  public GUICtrl tController;
	  
	  ObjHWCollection(Wall aWall, GUICtrl aGUICtrl) {
	    p55 = aWall;
	    tController = aGUICtrl;
	  }
	  
	  public String toXml() {
	    StringBuilder aXml = new StringBuilder();
	    ObjList anHWObj;
	    aXml.append("<HWList numObject=\""+this.size()+"\">");
	    for(int i=0; i<this.size(); i++) {
	      anHWObj = (ObjList)this.get(i);
	      anHWObj.xml(aXml);
	    }
	    aXml.append("</HWList>");
	    System.out.println(aXml.toString());
	    return aXml.toString(); 
	  }
	  
	  public ObjTemplate getObjectAtPos(int x, int y) {
	    ObjList aHWObj;
	    ObjTemplate tObjSelected;
	    int i;
	    drawObjInBuffer();
	    for (i =0; i < this.size(); i = i+1 ) {
	      aHWObj = (ObjList)this.get(i);
	      System.out.println("==HWobj "+i);
	      if (aHWObj.isSelected(p55.mouseX,p55.mouseY))
	      {
	    	aHWObj.selected = true;
		    aHWObj.theSelectedObj.loadParametersUI();
		    p55.selectedObj = aHWObj.theSelectedObj;	        
	        p55.selectedObjList = aHWObj;
		    return aHWObj.theSelectedObj;
	      }
	    }
	    return null;
	  }
	  
	  public void drawObjInBuffer() {
	    int i;
	    ObjList aHWObj;
	    for (i =0; i < this.size(); i++ ) {
	      aHWObj = (ObjList)this.get(i);
	      aHWObj.drawObjInBuffer();
	    }        
	  }
	}
