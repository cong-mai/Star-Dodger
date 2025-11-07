public class Controller {
    private Player player;
    private double targetX;
    private double targetY;
    private boolean hasTarget;

    public Controller( Player player) {
        this.player = player;
        this.hasTarget = false;
    }

    public void onMousePress() {
        if ( StdDraw.mousePressed() ) {
            targetX = StdDraw.mouseX();
            targetY = StdDraw.mouseY();
            hasTarget = true;
        }
    }
    
    public void movePlayer() {
        if (hasTarget) {
            double playerX = player.getX();
            double playerY = player.getY();
            
            // Calculate distance from player to target
            double dx = targetX - playerX;
            double dy = targetY - playerY;
            double distance = Math.sqrt(dx * dx + dy * dy);
            
            // If distance is greater than speed, move toward target
            if (distance > player.getSpeed()) {
                // Calculate ratio to move by speed amount
                double ratio = player.getSpeed() / distance;
                double newX = playerX + dx * ratio;
                double newY = playerY + dy * ratio;
                player.move(newX, newY);
            } else {
                // Close enough, move directly to target
                player.move(targetX, targetY);
                hasTarget = false;
            }
        }
    }
    
    public void update() {
        onMousePress();
        movePlayer();
    }
        
}
