import java.awt.Color;

/* a 2D spinning windmill animation */
public class Windmill {
    public int sens, layers; // sens is the sensitivity, layers is the number of recursive layers

    public Windmill(int _sens, int _layers) {
        sens = _sens;
        layers = _layers;
    }

    public void start() {
        StdDraw.clear(StdDraw.WHITE);
        StdDraw.setCanvasSize(800, 800);
        StdDraw.enableDoubleBuffering();
        StdDraw.show();
        while (true) { // to avoid stackoverflow
            windmillSpin(layers, 30);
        }
    }

    /**
     * Calls the drawing method recursively 360 times, then returns
     * 
     * @param n     The layers of recursion to draw the windmill
     * @param theta The angle at which to draw, to animate
     */
    public void windmillSpin(int n, double theta) {
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenRadius();
        recursiveWindmill(n, Math.sqrt(Math.pow(0.5, 2) + Math.pow(0.25, 2)), 0.5, 0.5, theta);
        theta -= sens;
        StdDraw.show();
        StdDraw.clear(Color.WHITE);
        if (theta <= -329 * sens)
            return;
        windmillSpin(n, theta);
    }

    /**
     * Recursively draws triangles at multiple areas based on the number of
     * recursive layers
     * 
     * @param n      The layer of recursion the program is currently on
     * @param length The length of the triangle
     * @param xc     The xcoord for the center where the triangles should be drawn
     * @param yc     The ycoord for the center where the triangles should be drawn
     * @param theta  The angle at which to draw
     */
    public void recursiveWindmill(int n, double length, double xc, double yc, double theta) {
        if (n == 0)
            return;
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        if (n % 2 == 0)
            StdDraw.setPenColor(StdDraw.BOOK_BLUE);
        drawTriangle(length, xc, yc, theta);
        recursiveWindmill(n - 1, length / 2, xc - (length / 2) * Math.cos(Math.toRadians(theta)),
                yc + (length / 2) * Math.sin(Math.toRadians(theta)), theta);
        recursiveWindmill(n - 1, length / 2, xc + (length / 2) * Math.sin(Math.toRadians(theta)),
                yc + (length / 2) * Math.cos(Math.toRadians(theta)), theta);
        recursiveWindmill(n - 1, length / 2, xc + (length / 2) * Math.cos(Math.toRadians(theta)),
                yc - (length / 2) * Math.sin(Math.toRadians(theta)), theta);
        recursiveWindmill(n - 1, length / 2, xc - (length / 2) * Math.sin(Math.toRadians(theta)),
                yc - (length / 2) * Math.cos(Math.toRadians(theta)), theta);
    }

    /**
     * Draws the triangles on all 4 sides of the center
     * 
     * @param length Twice the horizontal length of a triangle
     * @param xc     The xcoord for the center where the triangles should be drawn
     * @param yc     The ycoord for the center where the triangles should be drawn
     */
    public void drawTriangle(double length, double xc, double yc, double theta) {
        StdDraw.filledPolygon(
                new double[] { xc, xc - (length / 2) * Math.cos(Math.toRadians(theta)),
                        xc - (length / 2) * Math.cos(Math.toRadians(theta)) },
                new double[] { yc, yc, yc + length / 2 * Math.sin(Math.toRadians(theta)) });
        StdDraw.filledPolygon(
                new double[] { xc, xc - (length / 2) * Math.cos(Math.toRadians(theta)),
                        xc - (length / 2) * Math.cos(Math.toRadians(theta)) },
                new double[] { yc, yc, yc - length / 2 * Math.sin(Math.toRadians(theta)) });
        StdDraw.filledPolygon(new double[] { xc, xc, xc + length / 2 * Math.sin(Math.toRadians(theta)) },
                new double[] { yc, yc + (length / 2) * Math.cos(Math.toRadians(theta)),
                        yc + (length / 2) * Math.cos(Math.toRadians(theta)) });
        StdDraw.filledPolygon(new double[] { xc, xc, xc - length / 2 * Math.sin(Math.toRadians(theta)) },
                new double[] { yc, yc + (length / 2) * Math.cos(Math.toRadians(theta)),
                        yc + (length / 2) * Math.cos(Math.toRadians(theta)) });
        StdDraw.filledPolygon(
                new double[] { xc, xc + (length / 2) * Math.cos(Math.toRadians(theta)),
                        xc + (length / 2) * Math.cos(Math.toRadians(theta)) },
                new double[] { yc, yc, yc - length / 2 * Math.sin(Math.toRadians(theta)) });
        StdDraw.filledPolygon(
                new double[] { xc, xc + (length / 2) * Math.cos(Math.toRadians(theta)),
                        xc + (length / 2) * Math.cos(Math.toRadians(theta)) },
                new double[] { yc, yc, yc + length / 2 * Math.sin(Math.toRadians(theta)) });
        StdDraw.filledPolygon(new double[] { xc, xc, xc - length / 2 * Math.sin(Math.toRadians(theta)) },
                new double[] { yc, yc - (length / 2) * Math.cos(Math.toRadians(theta)),
                        yc - (length / 2) * Math.cos(Math.toRadians(theta)) });
        StdDraw.filledPolygon(new double[] { xc, xc, xc + length / 2 * Math.sin(Math.toRadians(theta)) },
                new double[] { yc, yc - (length / 2) * Math.cos(Math.toRadians(theta)),
                        yc - (length / 2) * Math.cos(Math.toRadians(theta)) });
    }
}