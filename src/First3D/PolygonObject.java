package First3D;

import java.awt.*;

public class PolygonObject {
    // this object is to create a polygon
    Polygon p;
    Color c;

    public PolygonObject(double[] x, double[] y, Color c) {
        Screen.numOfPolygons++;
        p = new Polygon();
        for (int i = 0; i < x.length; i++) {
            p.addPoint((int)x[i], (int)y[i]);
        }
        this.c = c;
    }

    public void drawPolygon(Graphics g) {
        g.setColor(c);
        g.drawPolygon(p);
    }
}
