package cosc344.models;

import java.sql.Date;
/**
 * Created by albertgao on 11/09/16.
 */


public class Hero {
    private int id;
    private String name;
    private Date birthday;
    private int exp;
    private int level;
    
    /**
     * no-arg constructor for Hero class
     * sets Hero object variables level and exp to 1
     */
    public Hero() {
        //in case forget to JOIN TABLE
        this.level = 1;
        this.exp = 1;
    }
    
    /**
     * 5-arg Hero constructor
     * @param id - the id of the hero
     * @param name - the name of the hero
     * @param birthday - the birthdate of the hero
     * @param exp - the exp amount of the hero
     * @param level - the level of the hero
     */
    public Hero(int id, String name, Date birthday, int exp, int level) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.exp = exp;
        this.level = level;
    }
    
    /**
     * Gets hero id
     * @return id of the hero
     */
    public int getId() {
        return id;
    }
    
    /**
     * Sets hero id
     * @param id of the hero to be set as
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Gets hero name
     * @return name of the hero
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets hero name
     * @param name of the hero to be set as
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets hero birthday
     * @return birthday of the hero
     */
    public Date getBirthday() {
        return birthday;
    }
    
    /**
     * Sets hero birthday
     * @param birthday of the hero to be set as
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    /**
     * Gets hero exp
     * @return exp of the hero
     */
    public int getExp() {
        return exp;
    }
    
    /**
     * Sets hero exp
     * @param exp of the hero to be set
     */
    public void setExp(int exp) {
        this.exp = exp;
    }
    
    /**
     * Gets hero level
     * @return level of the hero
     */
    public int getLevel() {
        return level;
    }
    
    /**
     * Sets hero level
     * @param level of the hero to be set as
     */
    public void setLevel(int level) {
        this.level = level;
    }
    
    /**
     * Override toString
     * @return String representation of the Hero object with variables id,name,birthday,exp,level.
     */
    @Override
    public String toString() {
        return "Hero {" +
                "id =" + id +
                ", name ='" + name + '\'' +
                ", birthday =" + birthday +
                ", exp =" + exp +
                ", level =" + level +
                '}';
    }
}
