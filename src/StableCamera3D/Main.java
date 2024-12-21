package StableCamera3D;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Main {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(600,600);

        RenderPanel renderPanel = new RenderPanel();
        jFrame.add(renderPanel);

        renderPanel.addMouseMotionListener(new MouseMotionListener() {
            private int lastX = 0;
            private int lastY = 0;

            @Override
            public void mouseDragged(MouseEvent e) {
                int deltaX = e.getX() - lastX;
                int deltaY = e.getY() - lastY;

                double yi = 360.0 / renderPanel.getHeight();
                double xi = 360.0 / renderPanel.getWidth();

                renderPanel.x += -(deltaX * xi);
                renderPanel.y += (deltaY * yi);

                lastX = e.getX();
                lastY = e.getY();
                renderPanel.repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                lastX = e.getX();
                lastY = e.getY();
            }
        });

    }
}
