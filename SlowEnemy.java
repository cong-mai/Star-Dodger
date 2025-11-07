public class SlowEnemy extends Enemy {
    
    public SlowEnemy(double x, double y) {
        super(x, y);
        this.damage = 30; // Slow enemies do more damage
        this.speed = 4; // Slow enemies move slower
    }
    
    public void move() {
        this.y += this.speed;
    }
}

