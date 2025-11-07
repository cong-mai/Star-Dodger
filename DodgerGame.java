import java.util.ArrayList;

public class DodgerGame {
    private boolean isOver;
    private Scene scene;
    private long startTime;
    private Controller controller;
    private int timer;
    private int score;
    private long gemSpawnTime;
    private int oxygen;
    private long oxygenTankSpawnTime;

    /* Create a new DodgerGame */
    public DodgerGame() {
        isOver = false;
        scene = new Scene();
        Player player = new Player(250, 187.5);
        this.scene.setPlayer( player );
        controller = new Controller( player );
        this.timer = 0;
        this.score = 0;
        this.gemSpawnTime = System.currentTimeMillis();
        this.oxygen = 100; // Start with 100 oxygen
        this.oxygenTankSpawnTime = System.currentTimeMillis();
        this.startTime = System.currentTimeMillis();
    }

    public void update() {
        controller.update();
        long now = System.currentTimeMillis();
        if (now - this.startTime > 200 ) { // 1/5th sec duration //
            scene.addMonster();
            this.timer++;
            this.oxygen--; // Decrease oxygen every 1/5 second
            this.startTime = now;
        }
        // Spawn gems less frequently than enemies
        if (now - this.gemSpawnTime > 1000) { // spawn gem every second
            scene.addGem();
            this.gemSpawnTime = now;
        }
        // Spawn oxygen tanks
        if (now - this.oxygenTankSpawnTime > 1500) { // spawn oxygen tank every 1.5 seconds
            scene.addOxygenTank();
            this.oxygenTankSpawnTime = now;
        }
        scene.update(); //update scene
        Player player = scene.getPlayer();
        for (Enemy monster : scene.getMonsters() ) {
        if ( player.isTouching(monster) ) {
            player.takeDamage(monster.getDamage());
            if (!player.isAlive()) {
                isOver = true;
            }
            }
        }
        // Check for gem collection
        ArrayList<Gem> gems = scene.getGems();
        for (int i = gems.size() - 1; i >= 0; i--) {
            Gem gem = gems.get(i);
            if (player.isTouchingGem(gem)) {
                gems.remove(i);
                score++;
            }
        }
        // Check for oxygen tank collection
        ArrayList<OxygenTank> tanks = scene.getOxygenTanks();
        for (int i = tanks.size() - 1; i >= 0; i--) {
            OxygenTank tank = tanks.get(i);
            if (player.isTouchingOxygenTank(tank)) {
                tanks.remove(i);
                oxygen = oxygen + 20; // Add 20 oxygen when collected
            }
        }
        // Check if oxygen runs out
        if (oxygen <= 0) {
            isOver = true;
        }
    }

    public void render() {
        // game render code
        scene.draw(); //draw scene
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(64,32,"Time: " + timer/5 );
        StdDraw.text(64,50,"Score: " + score );
        StdDraw.text(64,68,"Health: " + scene.getPlayer().getHealth() );
        StdDraw.text(64,86,"Oxygen: " + oxygen );
        StdDraw.show(100); 

    }

    /* The main game loop */
    public static void main(String[] args) {
        DodgerGame game = new DodgerGame();
        while (game.isOver == false) {
            game.update();
            game.render();
        }
    }
}