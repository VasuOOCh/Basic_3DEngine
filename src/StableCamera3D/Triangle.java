package StableCamera3D;

import java.awt.*;

public class Triangle {
    Vertex v1,v2,v3;
    Color color;

    public Triangle(Vertex v1, Vertex v2, Vertex v3,Color c) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.color = c;
    }
}
