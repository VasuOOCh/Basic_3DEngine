package StableCamera3D;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;

public class RenderPanel extends JPanel {

    ArrayList tris = new ArrayList<>();

    // these represent the x and y rotation in degrees
    int x = 0; // Degree rotated along the y-axis
    int y = 0; // Degree rotated along the x-axis

    double zoomFactor = 1.0;

    public RenderPanel() {

        // here we have added the 4 triangle to the tris arraylist
        // each triangle has a different color

        tris.add(new Plane(
                new Vertex(-100,-100,-100),
                new Vertex(100,-100,-100),
                new Vertex(100,100,-100),
                new Vertex(-100,100,-100)
        ));
        tris.add(new Plane(
                new Vertex(-100,-100,100),
                new Vertex(100,-100,100),
                new Vertex(100,100,100),
                new Vertex(-100,100,100)
        ));

        tris.add(new Plane(
                new Vertex(100,-100,-100),
                new Vertex(100,-100,100),
                new Vertex(100,100,100),
                new Vertex(100,100,-100)
        ));

        tris.add(new Plane(
                new Vertex(-100,-100,-100),
                new Vertex(-100,-100,100),
                new Vertex(-100,100,100),
                new Vertex(-100,100,-100)
        ));

        tris.add(new Plane(
                new Vertex(-100,100,100),
                new Vertex(100,100,100),
                new Vertex(100,100,-100),
                new Vertex(-100,100, -100)
        ));

        tris.add(new Plane(
                new Vertex(-100,-100,100),
                new Vertex(100,-100,100),
                new Vertex(100,-100,-100),
                new Vertex(-100,-100, -100)
        ));


//        tris.add(new Triangle(new Vertex(100, 100, 100),
//                new Vertex(-100, -100, 100),
//                new Vertex(-100, 100, -100),
//                Color.WHITE));
//        tris.add(new Triangle(new Vertex(100, 100, 100),
//                new Vertex(-100, -100, 100),
//                new Vertex(100, -100, -100),
//                Color.RED));
//        tris.add(new Triangle(new Vertex(-100, 100, -100),
//                new Vertex(100, -100, -100),
//                new Vertex(100, 100, 100),
//                Color.GREEN));
//        tris.add(new Triangle(new Vertex(-100, 100, -100),
//                new Vertex(100, -100, -100),
//                new Vertex(-100, -100, 100),
//                Color.BLUE));
        addMouseWheelListener(e -> {
            // Zoom in/out by 10% per wheel tick
            if (e.getWheelRotation() < 0) {
                zoomFactor *= 1.1; // Zoom in
            } else {
                zoomFactor *= 0.9; // Zoom out
            }
            repaint();
        });

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // setting g -> Graphics 2D
        Graphics2D g2 = (Graphics2D) g;

        // first we need to shift the origin to the center rather than the top-left corner
        g2.translate(getWidth()/2, getHeight()/2);
        g2.setColor(Color.black);

        double heading = Math.toRadians(x);

        // we are forming a transformation matrix, for rotation along y-axis | Angle : heading
        Matrix3 headingTransform = new Matrix3(new double[]{
                Math.cos(heading), 0, -Math.sin(heading),
                0, 1, 0,
                Math.sin(heading), 0, Math.cos(heading)
        });
        double pitch = Math.toRadians(y);
        // we are forming a transformation matrix, for rotation along x-axis | Angle : pitch
        Matrix3 pitchTransform = new Matrix3(new double[]{
                1, 0, 0,
                0, Math.cos(pitch), Math.sin(pitch),
                0, -Math.sin(pitch), Math.cos(pitch)
        });

        // we are forming a transformation matrix, zoom-in/out
        Matrix3 zoomTransform = new Matrix3(new double[]{
                zoomFactor, 0, 0,
                0, zoomFactor, 0,
                0, 0, zoomFactor
        });

        // merge the matrix for a final transformation matrix
        Matrix3 transform = headingTransform.multiply(pitchTransform).multiply(zoomTransform);

        for (int i = 0; i < tris.size(); i++) {
            Plane t = (Plane) tris.get(i);
            Vertex v1 = transform.transform(t.v1);
            Vertex v2 = transform.transform(t.v2);
            Vertex v3 = transform.transform(t.v3);
            Vertex v4 = transform.transform(t.v4);
            Path2D path = new Path2D.Double();
            path.moveTo(v1.x, v1.y);
            path.lineTo(v2.x, v2.y);
            path.lineTo(v3.x, v3.y);
            path.lineTo(v4.x, v4.y);
            path.closePath();
            g2.draw(path);
        }

    }
}
