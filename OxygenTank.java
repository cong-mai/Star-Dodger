public class OxygenTank {
    private int width;
    private int height;
    private double x;
    private double y;
    private int speed;

    public OxygenTank(double x, double y) {
        this.x = x;
        this.y = y;
        this.width = 24;
        this.height = 30;
        this.speed = (int) (2 + Math.random() * 5);
    }
    
    public void draw() {
        // Draw an oxygen tank
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledRectangle(x, y, width/2, height/2);
        StdDraw.setPenColor(StdDraw.CYAN);
        StdDraw.filledEllipse(x, y - height/2, width/2, 4);
        StdDraw.filledEllipse(x, y + height/2, width/2, 4);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(x, y, "O2");
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
}
