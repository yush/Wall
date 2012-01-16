import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import processing.core.PApplet;

public class ObjHWCollection extends ArrayList implements Serializable {
	  transient private int iHash;
	  transient public Wall p55;
	  transient public GUICtrl tController;
	  transient public ObjList selectedObj;
	  
	  ObjHWCollection(Wall aWall) {
	    p55 = aWall;
	    tController = p55.aGUICtrl;
	    iHash = 0;
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
	  
	  public void serialize(String tFilename) throws IOException {
			FileOutputStream fichier = new FileOutputStream(tFilename);
			ObjectOutputStream oos = new ObjectOutputStream(fichier);
			oos.writeObject(p55.theHWList);
			oos.close();	    			
	  }
	  
	  public void unserialize(String tFilename) throws IOException, ClassNotFoundException {
			FileInputStream f = new FileInputStream(tFilename);
			ObjectInputStream o = new ObjectInputStream(f);
			ObjHWCollection aColl = (ObjHWCollection)o.readObject();
			o.close();
			System.out.println("coll size:" + aColl.size() );
			p55.theHWList.clear();
			for(int i=0; i < aColl.size(); i++) {
				ObjList aListNew = (ObjList)aColl.get(i);
				p55.theHWList.add(new ObjList(p55, aListNew));					
			}
	  }
	  
	  public int getNewHash() {
		  iHash++;
		  return iHash;
	  }
	  
	}
