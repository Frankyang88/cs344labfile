package cosc344.models;

/**
 * Created by albertgao on 11/09/16.
 */
public class Consumable {
    private String name;
    private int heroid;
    private String backpackname;
    
    /**
     * Default Consumable constructor
     */
    public Consumable() {
    }
    
    /**
     * 3-arg Consumable constructor
     * @param name   The name of the consumable
     * @param heroid    The heroid that the consumable belongs to
     * @param backpackname The name of the backpack that the consumable resides
     */
    public Consumable(String name, int heroid, String backpackname) {
        this.name = name;
        this.heroid = heroid;
        this.backpackname = backpackname;
    }
    
    /**
     * Gets consumable name
     * @return name of the consumable
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets consumable name
     * @param name that the consumable will be set to
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Get heroid that the consumable belongs to
     * @return heroid of the consumable
     */
    public int getHeroid() {
        return heroid;
    }
    
    /**
     * Set heroid of the consumable
     * @param heroid of the consumable
     */
    public void setHeroid(int heroid) {
        this.heroid = heroid;
    }
    
    /**
     * Gets backpackname of the consumable
     * @return backpackname of the consumable
     */
    public String getBackpackname() {
        return backpackname;
    }
    
    /**
     * Sets backpackname of the consumable
     * @param backpackname  The backpackname that is going to be set to the consumable
     */
    public void setBackpackname(String backpackname) {
        this.backpackname = backpackname;
    }
    
    /**
     * Override toString
     * @return String representation of the Consumable object with name,heroid,backpackname.
     */
    @Override
    public String toString() {
        return "Consumable{" +
                "name='" + name + '\'' +
                ", heroid=" + heroid +
                ", backpackname='" + backpackname + '\'' +
                '}';
    }
}
