import processing.core.*;

public class ObjLink extends ObjTemplate {
  PVector pf1,pf2,pf3,pf4;    //coordonnÃ©es finale du rectangle
  PVector psrc1, psrc2;    //coordonnÃ©es du segment a suivre
  int weight;
  boolean processed;
  boolean dragged;
  
  ObjLink(int tX1,int tY1,int tX2,int tY2,int tWeight) {
    psrc1 = new PVector(tX1, tY1);
    psrc2 = new PVector(tX2, tY2);
    weight = tWeight; 
    processed=false;
  }
  
  /*
// determine les coordonnÃ©es du rectangle a partir de 2 points
  private void processCoord(int contour) {
    PVector vOrtho;
    PVector vSegment = new PVector(psrc2.x-psrc1.x,psrc2.y-psrc1.y,0);
    vOrtho = _getOrthogonalVector(psrc1, psrc2);
    // orthogonal fait 1/2 epaisseur
    vOrtho.mult((weight+contour)/2);

    //recup coordonnÃ©es finales
    pf1 = PVector.add(psrc1, vOrtho);
    pf4 = PVector.sub(pf1, vOrtho);
    pf4.sub(vOrtho); 
    pf2 = PVector.add(pf1, vSegment);
    pf3 = PVector.add(pf4, vSegment);
    processed = true;
  }
  
  public void drawIt(PGraphics aBuffer, int contour, int typeBuffer) {
    aBuffer.pushStyle();
    processCoord(contour);  //TODO: ne recalculer que si necessaire, mais il faut calculer le contour, puis l'interieur
    if (typeBuffer ==  1) {
      aBuffer.fill(color(id));
    }
    aBuffer.quad(pf1.x, pf1.y, pf2.x, pf2.y, pf3.x, pf3.y, pf4.x, pf4.y);
    aBuffer.popStyle();
  }
  
  public void setObjSize(int aSize) {
     
  }
  
  public int getObjSize() { 
    return 0;
  }

  public void toXml(StringBuilder aSB) {
    
  }
  
  */
  
}

