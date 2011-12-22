import java.util.ArrayList;

public class ObjHWCollection extends ArrayList {
	  
	  public GUICtrl tController;
	  
	  ObjHWCollection(GUICtrl aGUICtrl) {
	    tController = aGUICtrl;
	  }
	  
	  /*
	  public String toXml() {
	    StringBuilder aXml = new StringBuilder();
	    ObjHW anHWObj;
	    aXml.append("<HWList numObject=\""+theHWList.size()+"\">");
	    for(int i=0; i<theHWList.size(); i++) {
	      anHWObj = (ObjHW)theHWList.get(i);
	      anHWObj.xml(aXml);
	    }
	    aXml.append("</HWList>");
	    println(aXml.toString());
	    return aXml.toString(); 
	  }
	  
	  public ObjTemplate getSelectedObject(int x, int y) {
	    ObjHW aHWObj;
	    ObjTemplate tObjSelected;
	    int i;
	    drawObjInBuffer();
	    for (i =0; i < theHWList.size(); i++ ) {
	      aHWObj = (ObjHW)theHWList.get(i);
	      println("==HWobj "+i);
	      tObjSelected = aHWObj.isSelected(mouseX,mouseY);
	      if ( tObjSelected != null ) {
	        selectedObj = aHWObj.theSelectedObj;     
	        //selectedObj.loadParametersUI();
	        return tObjSelected;
	      }
//	      else {
//	        aHWObj.setUnselected();
//	        return null;
//	      }
	    }
	    return null;
	  }
	  
	  public void drawObjInBuffer() {
	    int i;
	    ObjHW aHWObj;
	    for (i =0; i < theHWList.size(); i++ ) {
	      aHWObj = (ObjHW)theHWList.get(i);
	      aHWObj.drawObjInBuffer();
	    }        
	  }
*/
	}
