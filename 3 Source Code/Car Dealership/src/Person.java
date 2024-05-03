/**
 * This class represents a Person in the application.
 * It implements the User interface and includes properties and methods specific to a Person.
 */
public class Person implements User {

//public abstract class Person {
String username;
String password;
String fullName;
double money;
String id;
String membership;
String carsBought;

    /**
     * Constructs a new Person with the specified details.
     *
     * @param id the ID of the person
     * @param fullName the full name of the person
     * @param money the amount of money the person has
     * @param carsBought the cars bought by the person
     * @param membership the membership status of the person
     * @param username the username of the person
     * @param password the password of the person
     */
    public Person(String id, String fullName, double money,String carsBought, String membership,String username, String password) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.money = money;
        this.id = id;
        this.carsBought=carsBought;
        this.membership = membership;
    }

    /**
     * Returns the username of the person.
     *
     * @return the username of the person
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the person.
     *
     * @param username the new username of the person
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password of the person.
     *
     * @return the password of the person
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the person.
     *
     * @param password the new password of the person
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the full name of the person.
     *
     * @return the full name of the person
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the full name of the person.
     *
     * @param fullName the new full name of the person
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Returns the amount of money the person has.
     *
     * @return the amount of money the person has
     */
    public double getMoney() {
        return money;
    }

    /**
     * Sets the amount of money the person has.
     *
     * @param money the new amount of money the person has
     */
    public void setMoney(double money) {
        this.money = money;
    }

    /**
     * Returns the ID of the person.
     *
     * @return the ID of the person
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the person.
     *
     * @param id the new ID of the person
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * Returns the membership status of the person.
     *
     * @return the membership status of the person
     */
    public String isMembership() {
        return membership;
    }

    /**
     * Sets the membership status of the person.
     *
     * @param membership the new membership status of the person
     */
    public void setMembership(String membership) {
        this.membership = membership;
    }

    /**
     * Returns the cars bought by the person.
     *
     * @return the cars bought by the person
     */
    public String getCarsBought() {
        return carsBought;
    }

    /**
     * Sets the cars bought by the person.
     *
     * @param carsBought the new cars bought by the person
     */
    public void setCarsBought(String carsBought) {
        this.carsBought = carsBought;
    }
}
