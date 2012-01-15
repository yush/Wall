import controlP5.ControlP5;
import processing.core.*;


public class ObjArrow extends ObjNode implements Comparable {
	  int arrowSize;
	
	public int getArrowSize() {
		return arrowSize;
	}

	public void setArrowSize(int arrowSize) {
		this.arrowSize = arrowSize;
	}

	private transient PVector _tp1, _tp2, _tp3;

	  ObjArrow(Wall aP55, int tX, int tY, int aSize, ObjList theParent, ControlP5 tController) {
	    super(aP55);
		System.out.println("constructeur ObjArrow");
	    super.aController = tController;
	    center.x = tX;
	    center.y = tY;	    
	    parent = theParent; 
	    arrowSize = aSize;
	  }
	
	  
	  public ObjArrow(Wall aP55, ObjNode anObj) {
		super(aP55);
		parent = anObj.parent;
	    super.aController =  anObj.aController;
	    center.x = (int)anObj.center.x;
	    center.y = (int)anObj.center.y;
	    System.out.println("center "+center.toString());
	    arrowSize = anObj.getObjSize();
	  }

	  private void processCoord(PVector aP1, PVector aP2, int tSize) {
	    PVector orthoVector, vSegment, aVector, arrowBase;
	    vSegment = new PVector(aP2.x-aP1.x,aP2.y-aP1.y,0);
	    orthoVector = _getOrthogonalVector(aP1, aP2);
	    orthoVector.mult(tSize/2);
	    aVector = vSegment;
	    aVector.normalize();
	    aVector.mult(tSize/2);
	    arrowBase = PVector.sub(aP2, aVector);
	    _tp1 = PVector.add(arrowBase, orthoVector);
	    _tp2 = PVector.sub(arrowBase, orthoVector);
	    aVector.normalize();
	    aVector.mult(tSize/2);
	    _tp3 = PVector.add(aP2, aVector );
	  }

	  public void drawIt(PGraphics aBuffer, int contourWeight, int typeBuffer){
	    if (typeBuffer ==  1) {
	      aBuffer.fill(p55.color(id));
	    }
	    ObjNode myMate = getMyMate();    
	    if (myMate != null) {
	      processCoord(myMate.center, center, arrowSize+contourWeight);
	      aBuffer.beginDraw();
	      aBuffer.noStroke();
	      aBuffer.triangle(_tp1.x, _tp1.y,_tp2.x, _tp2.y,_tp3.x, _tp3.y);
	      aBuffer.endDraw();
	    }
	  }
	  
	  public void toXml(StringBuilder aSB) {
	    aSB.append("<ObjArrow id='"+id+"'>");
	    aSB.append("<arrowSize>"+arrowSize+"</arrowSize>");
	    aSB.append("</ObjArrow>");
	  }
	  
	  public void setObjSize(int aSize) {
	    arrowSize = aSize; 
	  }
	  
	  public int getObjSize() {
	    return arrowSize; 
	  }
}


