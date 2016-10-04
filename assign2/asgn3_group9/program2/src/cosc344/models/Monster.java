package cosc344.models;

/**
 * Created by albertgao on 11/09/16.
 */
public class Monster {
    private String name;
    private int level;
    private int hitpoints;
    private String areaname;
    
    /**
     * Empty default constructor sets base hitpoints
     */
    public Monster() {
        //in case that forget to JOIN TABLE
        this.hitpoints = 10;
    }
    
    /**
     * 4-arg constructor sets 4 monster variables
     * @param name - name of the monster
     * @param level - level of the monster
     * @param hitpoints - hitpoint/health of the monster
     * @param areaname - area that the monster resides
     */
    public Monster(String name, int level, int hitpoints, String areaname) {
        this.name = name;
        this.level = level;
        this.hitpoints = hitpoints;
        this.areaname = areaname;
    }
    
    /**
     * Gets monster name
     * @return name - The monster's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets monster name
     * @param name - monster's name to be set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets monster level
     * @return level - monster's level
     */
    public int getLevel() {
        return level;
    }
    
    /**
     * Sets monster level
     * @param level - monster's level to be set
     */
    public void setLevel(int level) {
        this.level = level;
    }
    
    /**
     * Gets monster hitpoints
     * @return hitpoints - monster's hitpoints
     */
    public int getHitpoints() {
        return hitpoints;
    }
    
    /**
     * Sets monster hitpoints. If hitpoints to be set is less than 0,
     * the hitpoints for the monster is set to 0 otherwise the value
     * of the param
     * @param hitpoints - monster's hitpoints to be set
     */
    public void setHitpoints(int hitpoints) {
     if ( hitpoints < 0 ){
      this.hitpoints = 0; 
     } else {
      this.hitpoints = hitpoints;
     }
    }
    
    /**
     * Gets area name that the monster resides in
     * @return areaname - areaname that the monster resides
     */
    public String getAreaname() {
        return areaname;
    }
    
    /**
     * Sets area that the monster resides
     * @param areaname - the area that the monster should reside in
     */
    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }
    
    /**
     * Override toString
     * @Return String representation of the monster's variables
     */
    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", hitpoints=" + hitpoints +
                ", areaname='" + areaname + '\'' +
                '}';
    }
}
