/**
 * This class represents an Admin user in the system.
 * Admin inherits from the User interface.
 */
public class Admin implements User {

// Member variables
String username;
String password;
String fullName;
double money;
String id;
String membership;
String carsBought;

    /**
     * Constructs a new Admin with the specified details.
     *
     * @param id the ID of the admin
     * @param fullName the full name of the admin
     * @param money the amount of money the admin has
     * @param carsBought the cars bought by the admin
     * @param membership the membership status of the admin
     * @param username the username of the admin
     * @param password the password of the admin
     */
    public Admin (String id, String fullName, double money,String carsBought, String membership,String username, String password) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.money = money;
        this.id = id;
        this.carsBought=carsBought;
        this.membership = membership;
    }

    /**
     * Returns the username of the admin.
     *
     * @return the username of the admin
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the admin.
     *
     * @param username the new username of the admin
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password of the admin.
     *
     * @return the password of the admin
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the admin.
     *
     * @param password the new password of the admin
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the full name of the admin.
     *
     * @return the full name of the admin
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the full name of the admin.
     *
     * @param fullName the new full name of the admin
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Returns the amount of money the admin has.
     *
     * @return the amount of money the admin has
     */
    public double getMoney() {
        return money;
    }

    /**
     * Sets the amount of money the admin has.
     *
     * @param money the new amount of money the admin has
     */
    public void setMoney(double money) {
        this.money = money;
    }

    /**
     * Returns the ID of the admin.
     *
     * @return the ID of the admin
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the admin.
     *
     * @param id the new ID of the admin
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the membership status of the admin.
     *
     * @return the membership status of the admin
     */
    public String isMembership() {
        return membership;
    }

    /**
     * Sets the membership status of the admin.
     *
     * @param membership the new membership status of the admin
     */
    public void setMembership(String membership) {
        this.membership = membership;
    }

    /**
     * Returns the cars bought by the admin.
     *
     * @return the cars bought by the admin
     */
    public String getCarsBought() {
        return carsBought;
    }

    /**
     * Sets the cars bought by the admin.
     *
     * @param carsBought the new cars bought by the admin
     */
    public void setCarsBought(String carsBought) {
        this.carsBought = carsBought;
    }
}