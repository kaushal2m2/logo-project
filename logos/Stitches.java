import java.awt.Color;

/* a 2D 'stitches' logo (not animated) */
public class Stitches {
    public int layers; // layers is the number of recursive layers

    public Stitches(int _layers) {
        layers = _layers;
    }

    public void start() {
        StdDraw.clear(StdDraw.WHITE);
        StdDraw.setCanvasSize(800, 800);
        StdDraw.enableDoubleBuffering();
        StdDraw.show();
        stitchesSquares(layers);
    }

    /**
     * Draws the squares and lines for the initial stitch shape, then calls the
     * recursiveStitch method.
     * 
     * @param n The number of layers of recursion
     */
    public void stitchesSquares(int n) {
        for (int i = 0; i < n; i++) {
            double length = (1 / 4.0) * Math.pow(0.5, i);
            StdDraw.polygon(new double[] { 0.5, 0.5 - length, 0.5, 0.5 + length },
                    new double[] { 0.5 - length, 0.5, 0.5 + length, 0.5 });
            StdDraw.line(0.5, 0.5 + length, 0.5 - length, 0.5 + 2 * length);
            StdDraw.line(0.5, 0.5 + length, 0.5 + length, 0.5 + 2 * length);
            StdDraw.line(0.5, 0.5 - length, 0.5 - length, 0.5 - 2 * length);
            StdDraw.line(0.5, 0.5 - length, 0.5 + length, 0.5 - 2 * length);
            StdDraw.show();
            stitchesArcsRecursive(n, 1, length, 0.5, 0.5);
        }
    }

    /**
     * Draws the lines to create a curve through recursion
     * 
     * @param n      The max layer of recursion
     * @param i      The current layer of recursion *5
     * @param length The length of the stitch pattern
     * @param xc     The xcoord for the center
     * @param yc     The ycoord for the center
     */
    public void stitchesArcsRecursive(int n, int i, double length, double xc, double yc) {
        n *= 5;
        if (n == i)
            return;

        StdDraw.line(xc - (n - i) * length / (n), yc + i * length / (n), xc - i * length / (n),
                yc + (n + i) * length / (n));
        StdDraw.line(xc + (n - i) * length / (n), yc + i * length / (n), xc + i * length / (n),
                yc + (n + i) * length / (n));
        StdDraw.line(xc - (n - i) * length / (n), yc - i * length / (n), xc - i * length / (n),
                yc - (n + i) * length / (n));
        StdDraw.line(xc + (n - i) * length / (n), yc - i * length / (n), xc + i * length / (n),
                yc - (n + i) * length / (n));
        StdDraw.show();
        stitchesArcsRecursive(n / 5, i + 1, length, xc, yc);

    }
}