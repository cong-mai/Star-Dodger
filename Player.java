public class Player {
    /*Attributes: Instance Variables*/
    private String image;
    private int width;
    private int height;
    private double x;
    private double y;
    private int speed;
    private int health;

    /* Constructor */
    public Player(double x, double y) {
        this.x = x; //set x position
        this.y = y; //set y position
        this.width = 32; //set width
        this.height = 32; //set height
        this.image = "assets/spaceman.png"; //set image filename
        this.speed = 10;
        this.health = 100; // Start with 100 health
    }

    public void draw() {
        StdDraw.picture(x, y, image, width, height);
    }

    public void move(double x, double y) {
        this.y = y;
        this.x = x;
    }
    
    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }
    public double getSpeed() {
        return this.speed;
    }

    public boolean isTouchingX( Enemy gameObject ) {
        int hitzone = 24;
        return this.x <= gameObject.getX()+hitzone && gameObject.getX() <= this.x+hitzone;
    }

    public boolean isTouchingY( Enemy gameObject ) {
        int hitzone = 24;
        return this.y <= gameObject.getY()+hitzone && gameObject.getY() <= this.y+hitzone;
    }

    public boolean isTouching( Enemy gameObject ) {
        return this.isTouchingX(gameObject) && this.isTouchingY(gameObject);
    }
    
    public boolean isTouchingGemX( Gem gem ) {
        int hitzone = 20;
        return this.x <= gem.getX() + hitzone && gem.getX() <= this.x + hitzone;
    }

    public boolean isTouchingGemY( Gem gem ) {
        int hitzone = 20;
        return this.y <= gem.getY() + hitzone && gem.getY() <= this.y + hitzone;
    }

    public boolean isTouchingGem( Gem gem ) {
        return this.isTouchingGemX(gem) && this.isTouchingGemY(gem);
    }
    
    public boolean isTouchingOxygenTankX( OxygenTank tank ) {
        int hitzone = 20;
        return this.x <= tank.getX() + hitzone && tank.getX() <= this.x + hitzone;
    }

    public boolean isTouchingOxygenTankY( OxygenTank tank ) {
        int hitzone = 20;
        return this.y <= tank.getY() + hitzone && tank.getY() <= this.y + hitzone;
    }

    public boolean isTouchingOxygenTank( OxygenTank tank ) {
        return this.isTouchingOxygenTankX(tank) && this.isTouchingOxygenTankY(tank);
    }
    
    public int getHealth() {
        return this.health;
    }
    
    public void takeDamage(int damage) {
        this.health -= damage;
    }
    
    public boolean isAlive() {
        return this.health > 0;
    }

    
}
