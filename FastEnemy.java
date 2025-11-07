public class FastEnemy extends Enemy {
    
    public FastEnemy(double x, double y) {
        super(x, y);
        this.damage = 10; // Fast enemies do less damage
        this.speed = 12; // Fast enemies move faster
    }
    
    public void move() {
        this.y += this.speed;
    }
}
