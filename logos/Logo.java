
/**
 * Logo.java
 * Contains multiple recursive algorithms to draw patterns.
 * Several include animations.
 * 1) A windmill shape that spins
 * 2) A 3D pyramid imposed onto a 2D coordinate plane, rotates automatically.
 * 3) Pyramid variant that can be turned by user.
 * 4) A pattern with multiple Arc-like patterns that are animated.
 * 5) A simple stitch-like shape.
 * 6) A slide show containing all non-user-controlled elements of this class.
 * 7) The mouse can be clicked and held at any time to pause any ongoing animations.
 * Dependencies: This class depends on the class StdDraw for all graphical elements.
 * @author Kaushal Marimuthu
 * @version 1.0
 * @since 1/21/2020
 */
import java.awt.Color;
import java.awt.Font;
import java.util.Scanner;
import java.awt.event.KeyEvent;

public class Logo {
    /**
     * sens The sensitivity(speed), rotating components rotate at
     * thetax How much the pyramid is rotated about the x axis
     * thetay How much the pyramid is rotated about the y axis
     * thetaz How much the pyramid is rotated about the z axis
     */
    private int sens;
    private double thetax, thetay, thetaz;

    /**
     * Sets up and runs the program, with a white background.
     * 
     * @param args The number of recursive statements
     */
    public static void main(String[] args) {
        int temp = 3;
        if (args.length > 0) {
            temp = Integer.parseInt(args[0]);
        }
        Logo recursion = new Logo();
        StdDraw.clear(StdDraw.WHITE);
        StdDraw.setCanvasSize(800, 800);
        StdDraw.enableDoubleBuffering();
        StdDraw.setFont(new Font(Font.SERIF, Font.BOLD, 20));
        StdDraw.text(0.45, 0.5, "Check Terminal!");
        StdDraw.setFont();
        StdDraw.show();
        recursion.getInput(temp);
    }

    /**
     * Gets the user input, sens and design
     * 
     * @param n The number of recursive layers to draw
     */
    public void getInput(int n) {
        Scanner inp = new Scanner(System.in);
        System.out.println("\n What design? Type something from the quotes below");
        System.out.println("\n\"Windmill\" : a 2D spinning windmill");
        System.out.println("\"Pyramid\" : 3D Pyramid controlled by user ");
        System.out.println("\"AutoPyramid\" : 3D Automatically spinning pyramid");
        System.out.println("\"Stitch\" : a simple 2D stitch design");
        System.out.println("\"Curves\" : an animated curves design");
        System.out.println("\"Slideshow\" : a collage of these designs\n");
        String input = inp.nextLine().trim();
        System.out.println("\n\nWhat sensitivity? (1-10 works best)");
        try {
            sens = inp.nextInt();
        } catch (Exception e) {
            System.out.println("\n\nInvalidInput");
            input = "";
        }

        StdDraw.clear(Color.WHITE);
        switch (input) {
            case "Windmill":
                mainFunc(n);
                break;
            case "Pyramid":
                pyramidCaller(n);
                break;
            case "AutoPyramid":
                autoPyramid(n);
                break;
            case "Stitch":
                stitchesSquares(n);
                break;
            case "Slideshow":
                slideshowMain(n);
                break;
            case "Curves":
                StdDraw.clear(Color.BLACK);
                curvesMain(n);
                break;
            default:
                getInput(n);
                break;
        }
        inp.close();
    }

    /**
     * Uses an infinite loop to keep the windmill spinning
     * 
     * @param n The layers of recursion to draw the windmill
     */
    public void mainFunc(int n) {
        while (true) {
            windmillSpin(n, 30);
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
        toPause();
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
        toPause();
    }

    /**
     * Uses an infinte loop to redraw the pyramid as the angles change for the
     * rotation effect
     * This method is used for automatic rotation
     * 
     * @param n The layers of recursion to draw the pyramid
     */
    public void autoPyramid(int n) {
        while (true) {
            // thetax += sens;
            thetay += sens;
            // thetaz += sens;
            pyramid(n, 0, 0, 1, 0.4);
            StdDraw.show();
            StdDraw.clear(Color.BLACK);
        }
    }

    /**
     * Uses an infinte loop to redraw the pyramid as the angles change for the
     * rotation effect
     * This method is used for manual rotation
     * 
     * @param n The layers of recursion to draw the pyramid
     */
    public void pyramidCaller(int n) {
        double z = 1;
        while (true) {
            StdDraw.setPenColor(Color.WHITE);
            StdDraw.text(0, 0.3, "WASD or Arrow Keys to rotate");
            pyramid(n, 0, 0, z, 0.4);
            StdDraw.show();
            StdDraw.clear(Color.BLACK);
            key();
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
        toPause();
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
            toPause();
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
        toPause();
        stitchesArcsRecursive(n / 5, i + 1, length, xc, yc);

    }

    /**
     * Uses an infinite while loop to redraw the curves pattern to give an animation
     * effect
     */
    public void curvesMain(int n) {
        double theta = 0;
        while (true) {
            curves(0, n, 0.5, 0.5, 0.5, theta);
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
        toPause();
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
        toPause();
        theta = Math.toDegrees(theta);
        curvesRecursive(i + 1, length, xc, yc, horiz, theta);

    }

    /**
     * Uses an infinte loop to call the slideshow method.
     * This keep sthe slideshow going infinitely.
     * 
     * @param n The number of layers of recursion
     */
    public void slideshowMain(int n) {
        while (true) {
            slideshow(n);
        }
    }

    /**
     * Creates a slideshow of the designs
     * 
     * @param n The number of layers of recursion.
     */
    public void slideshow(int n) {
        StdDraw.pause(1000);
        windmillSpin(n, 30);
        for (int i = 0; i < 90; i++) {
            thetay = i * 2;
            pyramid(n, 0, 0, 1, 0.4);
            StdDraw.show();
            StdDraw.clear(Color.BLACK);
        }
        double theta = 0;
        StdDraw.setXscale();
        StdDraw.setYscale();
        StdDraw.clear(Color.BLACK);
        StdDraw.show();
        for (int i = 0; i < 180; i++) {
            curves(0, n, 0.5, 0.5, 0.5, theta);
            StdDraw.show();
            StdDraw.clear(Color.BLACK);
            StdDraw.pause(10);
            theta = 2 * i;
        }
        stitchesSquares(n);
        StdDraw.pause(5000);
    }

    /**
     * Checks if the mouse is pressed to pause the drawing
     */
    public void toPause() {
        while (StdDraw.isMousePressed()) {
            StdDraw.pause(1);
        }
    }

    /**
     * Checks if a key is being pressed, and changes the angles to rotate it.
     */
    public void key() {
        if (StdDraw.isKeyPressed(KeyEvent.VK_UP) && StdDraw.isKeyPressed(KeyEvent.VK_LEFT)
                || StdDraw.isKeyPressed(KeyEvent.VK_W) && StdDraw.isKeyPressed(KeyEvent.VK_A))
            thetaz += sens;
        else if (StdDraw.isKeyPressed(KeyEvent.VK_UP) && StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)
                || StdDraw.isKeyPressed(KeyEvent.VK_W) && StdDraw.isKeyPressed(KeyEvent.VK_D))
            thetaz -= sens;
        else if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN) && StdDraw.isKeyPressed(KeyEvent.VK_LEFT)
                || StdDraw.isKeyPressed(KeyEvent.VK_S) && StdDraw.isKeyPressed(KeyEvent.VK_A))
            thetaz -= sens;
        else if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN) && StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)
                || StdDraw.isKeyPressed(KeyEvent.VK_S) && StdDraw.isKeyPressed(KeyEvent.VK_D))
            thetaz -= sens;
        else if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT) || StdDraw.isKeyPressed(KeyEvent.VK_A))
            thetay += sens;
        else if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT) || StdDraw.isKeyPressed(KeyEvent.VK_D))
            thetay -= sens;
        else if (StdDraw.isKeyPressed(KeyEvent.VK_UP) || StdDraw.isKeyPressed(KeyEvent.VK_W))
            thetax -= sens;
        else if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN) || StdDraw.isKeyPressed(KeyEvent.VK_S))
            thetax += sens;
    }
}
