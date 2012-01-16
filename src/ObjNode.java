import java.io.Serializable;

import controlP5.ControlP5;
import processing.core.*;

public abstract class ObjNode implements Comparable, Serializable {
  transient public Wall p55;
  transient public ObjList parent;
  transient public ControlP5 aController;	//TODO: 
  transient boolean isSelected=false;
  transient boolean  processed=false;
  public PVector center;
  public int id;  //hash key (used by backbuffer)
  public transient boolean isDragged=false;
  
public PVector getCenter() {
	return center;
}

public void setCenter(PVector center) {
	this.center = center;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}
  
  ObjNode(Wall aP55) {
	p55 = aP55;
    id = p55.theHWList.getNewHash();
    center = new PVector();
    center.z = 0;
  }
  
  
  public int getObjColor() {
    return parent.objColor; 
  }
  
  public void setObjColor(int aColor) {
    parent.objColor = aColor; 
  }
    
  //retourne le vecteur orthogonal a un vecteur definit par les 2 points en parametre
  protected PVector _getOrthogonalVector(PVector src, PVector dest) {
    float longueur;
    PVector vOrtho = new PVector();
    PVector vAxeZ = new PVector(0,0,-1);
    PVector vSegment = new PVector(dest.x-src.x,dest.y-src.y,0);
    longueur = vSegment.mag();
    vSegment.normalize();
    vAxeZ.normalize();
    vOrtho = vSegment.cross(vAxeZ);
    
    // retour aux longueurs d'origine
    vSegment.mult(longueur);
    return vOrtho;
  }
  
  public String toString() {
     StringBuilder aStr = new StringBuilder();
    aStr.append( this.getClass().getName()+"\r\n");
    aStr.append( "x:"+center.x+"\r\n");
    aStr.append( "y:"+center.y+"\r\n");
    aStr.append(" isSelected:"+ isSelected+"\r\n");
   return aStr.toString(); 
  }
  
  public int compareTo(Object anObjTemplate) {
    int res=0;
    ObjNode tObj= (ObjNode)anObjTemplate;
    float zValue = tObj.center.z;
     if ( this.center.z > zValue) {
       res = 1;
     }
     if ( this.center.z < zValue) {
       res = -1;
     }
     return res;
  }
  
  public abstract void setObjSize(int aSize);
  public abstract int getObjSize();
  public abstract void drawIt(PGraphics aBuffer, int contour, int typeBuffer);
  public abstract void toXml(StringBuilder aSB);
  
  
  public ObjNode getMyMate() {
    if ( parent.head == this) {
      return parent.queue;
    }
    if ( parent.queue == this) {
      return parent.head;
    }
   return null;
  }
  
  public int compareTo(ObjArrow anObjTemplate) {
    int res=0;
     if ( this.center.z > anObjTemplate.center.z ) {
       res = 1;
     }
     if ( this.center.z < anObjTemplate.center.z ) {
       res = -1;
     }
     return res;
  }
  
  public void loadParametersUI() {
     p55.aGUICtrl.ctrlControlP5.controller("size").setValue(this.getObjSize());
     p55.aGUICtrl.ctrlControlP5.controller("red").setValue(p55.red(parent.objColor));
     p55.aGUICtrl.ctrlControlP5.controller("green").setValue(p55.green(parent.objColor));
     p55.aGUICtrl.ctrlControlP5.controller("blue").setValue(p55.blue(parent.objColor));
     p55.aGUICtrl.ctrlControlP5.controller("depth").setValue(p55.selectedObjList.z);
  }
 
  public boolean isAtPos(int x, int y) {
    int res;
    res = p55.aGUICtrl.backBuf.get(x,y);
    if (p55.color(id) == res) {
      System.out.println("obj selected");
      return true;
    }
    return false;
  }
  
  public float getAngle() {
	  float angle = p55.atan2(this.center.y, this.center.x);
	  return ((angle+(p55.HALF_PI))/p55.PI)*360;
  }
  
}
