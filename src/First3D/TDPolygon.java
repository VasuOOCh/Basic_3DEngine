package First3D;

import java.awt.*;

public class TDPolygon {
    Color c;
    double[] x;
    double[] y;
    double[] z;

    // (x,y,z) represents a point in the 3D field

    public TDPolygon(Color c, double[] x, double[] y, double[] z) {
        this.c = c;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void createPolygon() {
        // this 3D polygon will be represented by a 2D polygon according to the camera view
        double[] newX = new double[x.length];
        double[] newY = new double[y.length];

        // each 3D point will be mapped to a particular 2D point in the First3D.Screen

        for (int i = 0; i < x.length; i++) {
            newX[i] = Calculator.calculateX(Screen.viewFrom, Screen.viewTo, x[i], y[i], z[i]);
            newY[i] = Calculator.calculateY(Screen.viewFrom, Screen.viewTo, x[i], y[i], z[i]);
        }
        
        Screen.DrawablePolygons[Screen.numOfPolygons] = new PolygonObject(newX,newY,c);
    }
}
