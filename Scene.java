import java.util.ArrayList;

public class Scene {
    /*Attributes: Instance Variables*/
    private String image;
    private int width;
    private int height;
    private ArrayList<Enemy> monsters;
    private ArrayList<Gem> gems;
    private ArrayList<OxygenTank> oxygenTanks;
    private Player player;
    private double backgroundY; // For scrolling background

    /* Constructor */
    public Scene() {
        width = 500; //set scene width
        height = 350; //set scene height
        image = "assets/space-background.png"; //set image filename
        monsters = new ArrayList<Enemy>(); //set a new list of enemies in this scene
        gems = new ArrayList<Gem>(); //set a new list of gems in this scene
        oxygenTanks = new ArrayList<OxygenTank>(); //set a new list of oxygen tanks
        backgroundY = height / 2; // Start background at center

        StdDraw.setCanvasSize(width, height); //set canvas size for image
        StdDraw.setXscale(0.0, width); //set x=0 from right to left
        StdDraw.setYscale(height, 0.0); //set y=0 from top to bottom
    }
    
    /*draw method*/
    public void draw() {
         // Draw scrolling background (draw twice for seamless scroll)
         StdDraw.picture(width/2, backgroundY, image);
         StdDraw.picture(width/2, backgroundY - height, image);
         for (Enemy monster : monsters) {
            monster.draw();
         }
         for (Gem gem : gems) {
            gem.draw();
         }
         for (OxygenTank tank : oxygenTanks) {
            tank.draw();
         }
         player.draw(); //draw player
    }
    
    public void addMonster() {
        double x = 32 + (Math.random() * (width - 64));
        double y = -32;
        Enemy enemy;
        // Randomly create different enemy types
        double rand = Math.random();
        if (rand < 0.4) {
            enemy = new FastEnemy(x, y); // 40% fast enemies
        } else if (rand < 0.7) {
            enemy = new SlowEnemy(x, y); // 30% slow enemies
        } else {
            enemy = new Enemy(x, y); // 30% normal enemies
        }
        monsters.add(enemy);
    }
    
    public void addGem() {
        double x = 32 + (Math.random() * (width - 64));
        double y = -20;
        Gem gem = new Gem(x, y);
        gems.add(gem);
    }
    
    public void addOxygenTank() {
        double x = 32 + (Math.random() * (width - 64));
        double y = -20;
        OxygenTank tank = new OxygenTank(x, y);
        oxygenTanks.add(tank);
    }

    public void update() {
        // Scroll background downward
        backgroundY += 2;
        if (backgroundY >= height * 1.5) {
            backgroundY = height / 2;
        }
        for (Enemy monster : monsters) {
        monster.move();
        }
        for (Gem gem : gems) {
            gem.move();
        }
        for (OxygenTank tank : oxygenTanks) {
            tank.move();
        }
    }

    public Player getPlayer() {
        return this.player;
    }
    
    public void setPlayer( Player player) {
        this.player = player;
    }

    public ArrayList<Enemy> getMonsters() {
        return this.monsters;
    }
    
    public ArrayList<Gem> getGems() {
        return this.gems;
    }
    
    public ArrayList<OxygenTank> getOxygenTanks() {
        return this.oxygenTanks;
    }
        
}
