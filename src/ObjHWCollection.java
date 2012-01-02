import java.util.ArrayList;
import processing.core.PApplet;

public class ObjHWCollection extends ArrayList {
	  public Wall p55;
	  public GUICtrl tController;
	  public ObjList selectedObj;
	  
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
	    return aXml.toString(); 
	  }
	  
	  public ObjNode getObjectAtPos(int x, int y) {
	    ObjList aObjList;
	    ObjNode tObjSelected;
	    int i;
	    drawObjInBuffer();
	    for (i =0; i < this.size(); i = i+1 ) {
	      aObjList = (ObjList)this.get(i);
	      System.out.println("==HWobj "+i);
	      if (aObjList.isSelected(p55.mouseX,p55.mouseY))
	      {
		    return aObjList.selectedNode;
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
