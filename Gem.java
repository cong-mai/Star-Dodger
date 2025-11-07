public class Gem {
    /*Attributes: Instance Variables*/
    private int width;
    private int height;
    private double x;
    private double y;
    private int speed;

    /* Constructor */
    public Gem(double x, double y) {
        this.x = x; //set x position
        this.y = y; //set y position
        this.width = 20; //set width
        this.height = 20; //set height
        this.speed = (int) (2 + Math.random() * 5);
    }
    
    public void draw() {
        // Draw a gem as a diamond shape using StdDraw
        StdDraw.setPenColor(StdDraw.CYAN);
        double[] xPoints = {x, x + width/2, x, x - width/2};
        double[] yPoints = {y - height/2, y, y + height/2, y};
        StdDraw.filledPolygon(xPoints, yPoints);
        // Add a highlight
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledCircle(x - width/4, y - height/4, width/6);
    }

    public void move() {
        this.y += this.speed;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
}

