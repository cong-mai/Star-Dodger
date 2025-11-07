# Features Implementation Summary

## Feature 6: Improved Player Movement with Mouse

### Files Changed:
1. **Controller.java** - Complete rewrite of movement logic

### Code Changes:

#### Controller.java
- **Added new fields:**
  ```java
  private double targetX;
  private double targetY;
  private boolean hasTarget;
  ```

- **Modified `onMousePress()` method:**
  - Now stores mouse click position as target instead of moving immediately
  
- **Added new `movePlayer()` method:**
  ```java
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
  ```

- **Modified `update()` method:**
  - Now calls both `onMousePress()` and `movePlayer()`

---

## Feature 7: Scrolling Background Animation

### Files Changed:
1. **Scene.java** - Added background scrolling logic

### Code Changes:

#### Scene.java
- **Added new field:**
  ```java
  private double backgroundY; // For scrolling background
  ```

- **Modified constructor:**
  ```java
  backgroundY = height / 2; // Start background at center
  ```

- **Modified `draw()` method:**
  ```java
  // Draw scrolling background (draw twice for seamless scroll)
  StdDraw.picture(width/2, backgroundY, image);
  StdDraw.picture(width/2, backgroundY - height, image);
  ```
  - Draws background twice to create seamless scrolling effect

- **Modified `update()` method:**
  ```java
  // Scroll background downward
  backgroundY += 2;
  if (backgroundY >= height * 1.5) {
      backgroundY = height / 2;
  }
  ```
  - Moves background down by 2 pixels each frame
  - Resets position when it scrolls too far

---

## Feature 3: Different Types of Enemies That Damage Player

### Files Changed:
1. **Enemy.java** - Made fields protected, added damage system
2. **FastEnemy.java** - NEW FILE - Fast enemy subclass
3. **SlowEnemy.java** - NEW FILE - Slow enemy subclass
4. **Player.java** - Added health system
5. **Scene.java** - Modified to spawn different enemy types
6. **DodgerGame.java** - Added damage handling and health display

### Code Changes:

#### Enemy.java
- **Changed field visibility from `private` to `protected`:**
  ```java
  protected String image;
  protected int width;
  protected int height;
  protected double x;
  protected double y;
  protected int speed;
  protected int damage;  // NEW FIELD
  ```

- **Modified constructor:**
  ```java
  this.damage = 20; // Default damage
  ```

- **Added new method:**
  ```java
  public int getDamage() {
      return this.damage;
  }
  ```

#### FastEnemy.java (NEW FILE)
```java
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
```

#### SlowEnemy.java (NEW FILE)
```java
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
```

#### Player.java
- **Added new field:**
  ```java
  private int health;
  ```

- **Modified constructor:**
  ```java
  this.health = 100; // Start with 100 health
  ```

- **Added new methods:**
  ```java
  public int getHealth() {
      return this.health;
  }
  
  public void takeDamage(int damage) {
      this.health -= damage;
  }
  
  public boolean isAlive() {
      return this.health > 0;
  }
  ```

#### Scene.java
- **Modified `addMonster()` method:**
  ```java
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
  ```

#### DodgerGame.java
- **Modified collision detection in `update()` method:**
  ```java
  for (Enemy monster : scene.getMonsters() ) {
      if ( player.isTouching(monster) ) {
          player.takeDamage(monster.getDamage());
          if (!player.isAlive()) {
              isOver = true;
          }
      }
  }
  ```

- **Added health display in `render()` method:**
  ```java
  StdDraw.text(64,68,"Health: " + scene.getPlayer().getHealth() );
  ```

---

## Feature 2: Oxygen Counter System

### Files Changed:
1. **OxygenTank.java** - NEW FILE - Oxygen tank collectible
2. **Scene.java** - Added oxygen tank management
3. **Player.java** - Added oxygen tank collision detection
4. **DodgerGame.java** - Added oxygen system logic

### Code Changes:

#### OxygenTank.java (NEW FILE)
```java
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
```

#### Scene.java
- **Added new field:**
  ```java
  private ArrayList<OxygenTank> oxygenTanks;
  ```

- **Modified constructor:**
  ```java
  oxygenTanks = new ArrayList<OxygenTank>();
  ```

- **Modified `draw()` method:**
  ```java
  for (OxygenTank tank : oxygenTanks) {
      tank.draw();
  }
  ```

- **Added new method:**
  ```java
  public void addOxygenTank() {
      double x = 32 + (Math.random() * (width - 64));
      double y = -20;
      OxygenTank tank = new OxygenTank(x, y);
      oxygenTanks.add(tank);
  }
  ```

- **Modified `update()` method:**
  ```java
  for (OxygenTank tank : oxygenTanks) {
      tank.move();
  }
  ```

- **Added new getter method:**
  ```java
  public ArrayList<OxygenTank> getOxygenTanks() {
      return this.oxygenTanks;
  }
  ```

#### Player.java
- **Added new collision detection methods:**
  ```java
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
  ```

#### DodgerGame.java
- **Added new fields:**
  ```java
  private int oxygen;
  private long oxygenTankSpawnTime;
  ```

- **Modified constructor:**
  ```java
  this.oxygen = 100; // Start with 100 oxygen
  this.oxygenTankSpawnTime = System.currentTimeMillis();
  this.startTime = System.currentTimeMillis();
  ```

- **Modified `update()` method:**
  ```java
  // Decrease oxygen every 1/5 second
  this.oxygen--; 
  
  // Spawn oxygen tanks
  if (now - this.oxygenTankSpawnTime > 1500) { // spawn oxygen tank every 1.5 seconds
      scene.addOxygenTank();
      this.oxygenTankSpawnTime = now;
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
  ```

- **Added oxygen display in `render()` method:**
  ```java
  StdDraw.text(64,86,"Oxygen: " + oxygen );
  ```

---

## Summary

### Total Files Modified: 6
1. Controller.java
2. Scene.java
3. Enemy.java
4. Player.java
5. DodgerGame.java

### Total New Files Created: 3
1. FastEnemy.java
2. SlowEnemy.java
3. OxygenTank.java

### Object-Oriented Features Used:
- **Inheritance**: FastEnemy and SlowEnemy extend Enemy
- **Encapsulation**: Private/protected fields with public getters
- **Polymorphism**: Different enemy types with same interface
- **Abstraction**: Separate classes for different game objects

