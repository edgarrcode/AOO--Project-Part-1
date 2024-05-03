/**
 * This interface represents a User with properties like username, password, fullName, money, id, membership status, and cars bought.
 */
public interface User {

    /**
     * Returns the username of the user.
     *
     * @return the username of the user
     */
    String getUsername();

    /**
     * Sets the username of the user.
     *
     * @param username the new username of the user
     */
    void setUsername(String username);

    /**
     * Returns the password of the user.
     *
     * @return the password of the user
     */
    String getPassword();

    /**
     * Sets the password of the user.
     *
     * @param password the new password of the user
     */
    void setPassword(String password);

    /**
     * Returns the full name of the user.
     *
     * @return the full name of the user
     */
    String getFullName();

    /**
     * Sets the full name of the user.
     *
     * @param fullName the new full name of the user
     */
    void setFullName(String fullName);

    /**
     * Returns the money of the user.
     *
     * @return the money of the user
     */
    double getMoney();

    /**
     * Sets the money of the user.
     *
     * @param money the new money of the user
     */
    void setMoney(double money);

    /**
     * Returns the ID of the user.
     *
     * @return the ID of the user
     */
    String getId();
    
    /**
     * Sets the ID of the user.
     *
     * @param id the new ID of the user
     */
    void setId(String id);

    /**
     * Returns the membership status of the user.
     *
     * @return the membership status of the user
     */
    String isMembership();

    /**
     * Sets the membership status of the user.
     *
     * @param membership the new membership status of the user
     */
    void setMembership(String membership);

    /**
     * Returns the cars bought by the user.
     *
     * @return the cars bought by the user
     */
    String getCarsBought();

    /**
     * Sets the cars bought by the user.
     *
     * @param carsBought the new cars bought by the user
     */
    void setCarsBought(String carsBought);
}