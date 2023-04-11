import java.awt.Color;

/* a 2D 'curves' animation */
public class Curves {
    public int sens, layers; // sens is the sensitivity, layers is the number of recursive layers

    public Curves(int _sens, int _layers) {
        sens = _sens;
        layers = _layers;
    }

    public void start() {
        StdDraw.clear(StdDraw.WHITE);
        StdDraw.setCanvasSize(800, 800);
        StdDraw.enableDoubleBuffering();
        StdDraw.show();
        double theta = 0;
        while (true) {
            curves(0, layers, 0.5, 0.5, 0.5, theta);
            StdDraw.show();
            StdDraw.clear(Color.BLACK);
            theta += sens;
        }
    }

    /**
     * Calls the recursive curves method through another layer of recursion in order
     * to create
     * the proper amount of lines for curves.
     * 
     * @param n    The current layer of recursion
     * @param nmax The max layers of recursion
     */
    public void curves(int n, int nmax, double xc, double yc, double length, double theta) {
        if (n == nmax + 1)
            return;
        StdDraw.setPenColor(Color.RED);
        if (n % 2 == 0)
            StdDraw.setPenColor(Color.BLUE);
        int n1 = (int) Math.pow(2, n);
        for (double i = xc / n1; i + xc / n1 <= xc + length; i += 2 * length / n1) {
            curvesRecursive(0, length / n1, i, yc, true, theta);
            curvesRecursive(0, length / n1, xc, xc + length - i, false, theta);
        }
        // StdDraw.show();
        curves(n + 1, nmax, xc, yc, length, theta);
    }

    /**
     * Draws the lines tangent to the curve to create the curve shape
     * 
     * @param i      The number of the line being drawn
     * @param length The length of the axes about which the lines are drawn
     * @param xc     The xcoord for the center
     * @param yc     The ycoord for the center
     * @param horiz  Whether it should be drawn horizontally or vertically
     */
    public void curvesRecursive(int i, double length, double xc, double yc, boolean horiz, double theta) {
        if (20 == i)
            return;
        theta = Math.toRadians(theta);
        double cos = Math.cos(theta);
        if (horiz) {
            StdDraw.line(xc - (20 - i) * length / (20) * cos, yc + i * length / 20 * cos, xc + i * length / (20) * cos,
                    yc + (20 - i) * length / (20) * cos);
            StdDraw.line(xc + (20 - i) * length / (20) * cos, yc + i * length / 20 * cos, xc - i * length / (20) * cos,
                    yc + (20 - i) * length / (20) * cos);
            StdDraw.line(xc - (20 - i) * length / (20) * cos, yc - i * length / 20 * cos, xc + i * length / (20) * cos,
                    yc - (20 - i) * length / (20) * cos);
            StdDraw.line(xc + (20 - i) * length / (20) * cos, yc - i * length / 20 * cos, xc - i * length / (20) * cos,
                    yc - (20 - i) * length / (20) * cos);
        } else {
            StdDraw.line(xc + i * length / (20) * cos, yc - (20 - i) * length / 20 * cos,
                    xc + (20 - i) * length / (20) * cos, yc + i * length / (20) * cos);
            StdDraw.line(xc + i * length / (20) * cos, yc + (20 - i) * length / 20 * cos,
                    xc + (20 - i) * length / (20) * cos, yc - i * length / (20) * cos);
            StdDraw.line(xc - i * length / (20) * cos, yc - (20 - i) * length / 20 * cos,
                    xc - (20 - i) * length / (20) * cos, yc + i * length / (20) * cos);
            StdDraw.line(xc - i * length / (20) * cos, yc + (20 - i) * length / 20 * cos,
                    xc - (20 - i) * length / (20) * cos, yc - i * length / (20) * cos);
        }
        // StdDraw.show();
        theta = Math.toDegrees(theta);
        curvesRecursive(i + 1, length, xc, yc, horiz, theta);

    }
}