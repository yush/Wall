import processing.core.PApplet;
import processing.core.PGraphics;
import controlP5.ControlP5;

public class ObjCir extends ObjNode {
  int radius;

public int getRadius() {
	return radius;
}

public void setRadius(int radius) {
	this.radius = radius;
}

public ObjCir(Wall aP55, int tX, int tY, int tRad, ObjList theParent, ControlP5 tController) {
	super(aP55);
    System.out.println("constructeur ObjCir");
    parent = theParent;
    super.aController = tController;
    center.x = tX;
    center.y = tY;
    radius = tRad;
  }
  
  public ObjCir(Wall aP55, ObjNode anObj) {
	super(aP55);  
    parent = anObj.parent;
    super.aController =  anObj.aController;
    center.x = (int)anObj.center.x;
    center.y = (int)anObj.center.y;
    radius = anObj.getObjSize();
  }
  
  
//  public void setSelected(boolean tSelected) {
//    if ((isSelected == true)  && ( isSelected != tSelected)) {
//      System.out.println("set selected radius:"+radius);
//      aController.controller("headRadius").setValue(radius); 
//    }
//    isSelected = tSelected; 
//  }

  /*
  private void _drawSelection(PGraphics aBuffer) {
    aBuffer.pushStyle();
    aBuffer.noFill();
    aBuffer.stroke(255,0,0);
    aBuffer.ellipse(center.x,center.y,radius,radius);
    aBuffer.popStyle();
  } 
*/  
// dessine l'élement
  public void drawIt(PGraphics aBuffer, int contour, int typeBuffer){
    if (typeBuffer ==  1) {
      aBuffer.fill(p55.color(id));
    }
    aBuffer.beginDraw();
    aBuffer.noStroke();
    aBuffer.ellipse(center.x,center.y,radius+contour,radius+contour);
    aBuffer.endDraw();
  }
  
  public void toXml(StringBuilder aSB) {
    aSB.append("<ObjCir id='"+id+"'>");
    aSB.append("<radius>"+radius+"</radius>");
    aSB.append("<position x='"+center.x+"' y='"+center.y+"'/>");
    aSB.append("</ObjCir>");
  }
  
  public void setObjSize(int aSize) {
    radius = aSize; 
  }
  
  public int getObjSize() {
     return (int)radius;
  }

}
  

