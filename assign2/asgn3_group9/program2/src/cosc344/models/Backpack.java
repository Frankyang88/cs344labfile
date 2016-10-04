package cosc344.models;

/**
 * Created by albertgao on 11/09/16.
 */
public class Backpack {
    private String name;
    private int heroid;
    private int size;
    private int gold;
    //private ArrayList<Consumable> consumables;

    /**
     * default Backpack constructor
     */
    public Backpack() {
    }
    
    /**
     * 4-arg Backpack constructor sets Backpack object variables name, heroid, size, gold.
     * @param name - of the backpack
     * @param heroid - heroid that the backpack belongs to
     * @param size - size of the backpack
     * @param gold - the amount of gold in the backpack
     */
    public Backpack(String name, int heroid, int size, int gold /*, ArrayList<Consumable> consumables*/) {
        this.name = name;
        this.heroid = heroid;
        this.size = size;
        this.gold = gold;
        //this.consumables = consumables;
    }
    
    /**
     * Gets backpack name
     * @return name of the backpack
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets backpack name
     * @param name that the backpack will be set to
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the heroid corresponding to the backpack
     * @return heroid of the backpack owner
     */
    public int getHeroid() {
        return heroid;
    }
    
    /**
     * Sets heroid for the backpack
     * @param heroid of the hero that is going to own the backpack
     */
    public void setHeroid(int heroid) {
        this.heroid = heroid;
    }
    
    /**
     * Gets size of the backpack
     * @return size of the backpack
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Sets size of the backpack
     * @param size the value that the backpack size will be set to
     */
    public void setSize(int size) {
        this.size = size;
    }
    
    /**
     * Gets gold amount in the backpack
     * @return gold amound in the backpack
     */
    public int getGold() {
        return gold;
    }
    
    /**
     * Sets gold amount in the backpack
     * @param gold - the amound of gold that is going to be in the backpack
     */
    public void setGold(int gold) {
        this.gold = gold;
    }

    /*
    public List<Consumable> getConsumables() {
        return consumables;
    }

    public void setConsumables(ArrayList<Consumable> consumables) {
        this.consumables = consumables;
    }
    */
    
    /**
     * Override toString
     * prints String representation of the backpack
     * @return String representation of the backpack variables name,heroid,size,gold
     */
    @Override
    public String toString() {
        return "Backpack{" +
                "name='" + name + '\'' +
                ", heroid=" + heroid +
                ", size=" + size +
                ", gold=" + gold +
                //", consumables=" + consumables +
                '}';
    }
}
