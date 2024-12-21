package First3D;

import javax.swing.*;
import java.awt.*;

public class Screen extends JPanel {

    // Defining our camera
    static double[] viewFrom = new double[] {10,10,10};
    static double[] viewTo = new double[] {0,0,0};

    static int numOfPolygons = 0;
    static PolygonObject[] DrawablePolygons = new PolygonObject[100];

    // 3D Polygon
    TDPolygon poly;

    public Screen() {
        poly = new TDPolygon(Color.BLUE, new double[] {2,4,2}, new double[] {2,4,2}, new double[] {5,5,5});
    }

    public void paintComponent(Graphics g) {
        for (PolygonObject p : DrawablePolygons ) {
            p.drawPolygon(g);
        }
    }
}
