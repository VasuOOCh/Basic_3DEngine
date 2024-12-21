package StableCamera3D;

public class Matrix3 {
    double[] values;
    // we are assuming that the matrix is 3X3
    // so the length of values if 9

    public Matrix3(double[] values) {
        this.values = values;
    }

    Matrix3 multiply(Matrix3 other) {
        double[] result = new double[9];

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                // row,col refers to the (row,col) value of the final matrix
                double val = 0;
                for (int i = 0; i < 3; i++) {
                    val+= this.values[row*3 + i]* other.values[col + i*3];
                }
                result[row*3 + col] = val;
            }
        }

        return new Matrix3(result);
    }
    Vertex transform(Vertex in) {
        // this transformation needs to be understood
        return new Vertex(
                in.x*values[0] + in.y*values[3] + in.z*values[6],
                in.x*values[1] + in.y*values[4] + in.z*values[7],
                in.x*values[2] + in.y*values[5] + in.z*values[8]
        );
    }

}
