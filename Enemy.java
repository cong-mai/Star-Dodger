public class Enemy {
    /*Attributes: Instance Variables*/
    protected String image;
    protected int width;
    protected int height;
    protected double x;
    protected double y;
    protected int speed;
    protected int damage;

    /* Constructor */
    public Enemy(double x, double y) {
        this.x = x; //set x position
        this.y = y; //set y position
        this.width = 32; //set width
        this.height = 32; //set height
        this.image = "assets/asteroid.png"; //set image filename
        this.speed = (int) (3 + Math.random() * 10);
        this.damage = 20; // Default damage
    }
    public void draw() {
        StdDraw.picture(x, y, image, width, height);
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
    
    public int getDamage() {
        return this.damage;
    }
           
        
}
