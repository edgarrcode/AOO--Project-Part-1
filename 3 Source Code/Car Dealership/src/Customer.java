/**
* This Customer class extends Person
*/
public class Customer extends Person{

        /**
     * Constructs a new Customer with the specified details.
     *
     * @param id the ID of the customer
     * @param fullName the full name of the customer
     * @param money the amount of money the customer has
     * @param carsBought the cars bought by the customer
     * @param membership the membership status of the customer
     * @param username the username of the customer
     * @param password the password of the customer
     */
    public Customer(String id, String fullName, double money,String carsBought, String membership,String username, String password) {
        super(id, fullName, money,carsBought , membership,username, password);
    }

    /**
     * Returns the username of the customer.
     *
     * @return the username of the customer
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the customer.
     *
     * @param username the new username of the customer
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password of the customer.
     *
     * @return the password of the customer
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the customer.
     *
     * @param password the new password of the customer
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the full name of the customer.
     *
     * @return the full name of the customer
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the full name of the customer.
     *
     * @param fullName the new full name of the customer
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Returns the amount of money the customer has.
     *
     * @return the amount of money the customer has
     */
    public double getMoney() {
        return money;
    }

    /**
     * Sets the amount of money the customer has.
     *
     * @param money the new amount of money the customer has
     */
    public void setMoney(double money){
        this.money = money;
    }

    /**
     * Returns the cars bought by the customer.
     *
     * @return the cars bought by the customer
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the cars bought by the customer.
     *
     * @param id the new cars bought by the customer
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the membership status of the customer.
     *
     * @return the membership status of the customer
     */
    public String isMembership() {
        return membership;
    }

    /**
     * Sets the membership status of the customer.
     *
     * @param membership the new membership status of the customer
     */
    public void setMembership(String membership) {
        this.membership = membership;
    }
}
