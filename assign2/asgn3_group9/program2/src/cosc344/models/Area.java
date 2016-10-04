 package cosc344.models;

/**
 * Created by albertgao on 11/09/16.
 */
public class Area {
    private String name;
    private int size;
    private int restricted;

    public Area() {
    }
    
    /**
     * Constructor for Area - sets its associated variables
     */
    public Area(String name, int size, int restricted) {
        this.name = name;
        this.size = size;
        this.restricted = restricted;
    }
    
    /**
     * Gets area name
     * @return name of area
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets area name
     * @param name to be set to the area
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets area size
     * @return size of the area
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Sets area size
     * @param size of the area to be set
     */
    public void setSize(int size) {
        this.size = size;
    }
    
    /**
     * Gets restricted value of the area
     * @return restricted the restriction value
     */
    public int getRestricted() {
        return restricted;
    }
    
    /**
     * Sets restriction value of the area
     * @param restricted the restriction value to be set to the area
     */
    public void setRestricted(int restricted) {
        this.restricted = restricted;
    }
    
    /**
     * Override toString
     * Returns format of the area variables
     * @return String representation of the area
     */
    @Override
    public String toString() {
        return "Area {" +
                "name ='" + name + '\'' +
                ", size =" + size +
                ", restricted =" + restricted +
                '}';
    }
}
