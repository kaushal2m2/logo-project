import java.awt.Color;

/*3d pyramid that automatically spins */
public class SpinningPyramid {
    public int sens, layers; // sens is the sensitivity, layers is the number of recursive layers
    public double thetax, thetay, thetaz; // angle of rotation in x,y,z direction

    public SpinningPyramid(int _sens, int _layers, double _thetax, double _thetay, double _thetaz) {
        sens = _sens;
        layers = _layers;
        thetax = _thetax;
        thetay = _thetay;
        thetaz = _thetaz;
    }

    public void start() {
        StdDraw.clear(StdDraw.WHITE);
        StdDraw.setCanvasSize(800, 800);
        StdDraw.enableDoubleBuffering();
        StdDraw.show();
        while (true) { // to avoid stackoverflow
            // thetax += sens;
            thetay += sens; // can use thetax or thetaz to spin in other directions
            // thetaz += sens;
            pyramid(layers, 0, 0, 1, 0.4);
            StdDraw.show();
            StdDraw.clear(Color.BLACK);
        }
    }

    /**
     * Recursively draws all the layers of the pyramid
     * 
     * @param n      The number of recursive layers still left
     * @param xc     The center x coord for where to draw the pyramid
     * @param yc     The center y coord for where to draw the pyramid
     * @param zc     The center z coord for where to draw the pyramid
     * @param length
     */
    public void pyramid(int n, double xc, double yc, double zc, double length) {
        if (n == 0)
            return;
        StdDraw.setXscale(-.5, .5);
        StdDraw.setYscale(-.5, .5);
        double[] x = { xc - length / 2, xc + length / 2, xc, xc - length / 2, xc + length / 2 };
        double[] y = { yc - length / 2, yc - length / 2, yc + length / 2, yc - length / 2, yc - length / 2 };
        double[] z = { zc - length / 2 - 1, zc - length / 2 - 1, zc - 1, zc + length / 2 - 1, zc + length / 2 - 1 };
        getCoords(x, z, Math.toRadians(thetay));
        getCoords(x, y, Math.toRadians(thetaz));
        getCoords(y, z, Math.toRadians(thetax));
        z = new double[] { z[0] + 1, z[1] + 1, z[2] + 1, z[3] + 1, z[4] + 1 };
        StdDraw.polygon(new double[] { x[0] / z[0], x[2] / z[2], x[1] / z[1] },
                new double[] { y[0] / z[0], y[2] / z[2], y[1] / z[1] });
        StdDraw.polygon(new double[] { x[3] / z[3], x[2] / z[2], x[0] / z[0] },
                new double[] { y[3] / z[3], y[2] / z[2], y[0] / z[0] });
        StdDraw.polygon(new double[] { x[4] / z[4], x[2] / z[2], x[1] / z[1] },
                new double[] { y[4] / z[4], y[2] / z[2], y[1] / z[1] });
        StdDraw.polygon(new double[] { x[4] / z[4], x[2] / z[2], x[1] / z[1] },
                new double[] { y[4] / z[4], y[2] / z[2], y[1] / z[1] });
        StdDraw.polygon(new double[] { x[4] / z[4], x[2] / z[2], x[3] / z[3] },
                new double[] { y[4] / z[4], y[2] / z[2], y[3] / z[3] });
        StdDraw.setPenColor(new Color(128, 0, 128));
        // StdDraw.setPenColor(StdDraw.BLUE);
        if (n % 2 == 0)
            StdDraw.setPenColor(new Color(212, 175, 55));
        // StdDraw.setPenColor(StdDraw.RED);
        pyramid(n - 1, xc - length / 4, yc - length / 4, zc - length / 4, length / 2);
        pyramid(n - 1, xc + length / 4, yc - length / 4, zc - length / 4, length / 2);
        pyramid(n - 1, xc - length / 4, yc - length / 4, zc + length / 4, length / 2);
        pyramid(n - 1, xc + length / 4, yc - length / 4, zc + length / 4, length / 2);
        pyramid(n - 1, xc, yc + length / 4, zc, length / 2);
    }

    /**
     * Gets the coordinates for the passed in arrays when they rotate about another
     * axis
     * 
     * @param x     The coordinates to change (given an ambiguous name x)
     * @param z     The other coordinates to change (given an ambiguous name x)
     * @param theta The angle these coordinates are rotated by
     */
    public void getCoords(double[] x, double[] z, double theta) {
        for (int j = 0; j < z.length; j++) {
            double xtemp = x[j], ztemp = z[j];
            x[j] = xtemp * Math.cos(theta) + ztemp * Math.sin(theta);
            z[j] = ztemp * Math.cos(theta) - xtemp * Math.sin(theta);
        }
    }
}