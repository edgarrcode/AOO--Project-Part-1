/**
* This Customer class extends Person
*/
public class Customer extends Person{

    public Customer(String id, String fullName, double money,String carsBought, boolean membership,String username, String password) {
        super(id, fullName, money,carsBought , membership,username, password);
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money){
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isMembership() {
        return membership;
    }

    public void setMembership(boolean membership) {
        this.membership = membership;
    }
}
