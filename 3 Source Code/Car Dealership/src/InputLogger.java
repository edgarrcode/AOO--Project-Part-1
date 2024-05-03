import java.lang.reflect.Array;

/**
 * This class is responsible for logging user inputs.
 */
public class InputLogger {

    /**
     * Logs the username and password entered by the user.
     *
     * @param userName the username entered by the user
     * @param passWord the password entered by the user
     * @return an array containing the username and password
     */
    public String[] loginLogger(String userName, String passWord ){
        String[] loginInfo = new String[2];
        loginInfo[0]=userName;
        loginInfo[1]=passWord;
        //testing
        //System.out.println("in loginLogger Username: "+loginInfo[0]);
        //System.out.println("password "+loginInfo[1]);
        return loginInfo;
    }

    /**
     * Logs the menu choice made by the user.
     *
     * @param menuChoice the menu choice made by the user
     * @return the menu choice
     */
    public int menuLogger(int menuChoice){
        return menuChoice;
    }

    /**
     * Logs all inputs made by the user.
     *
     * @param input the input made by the user
     * @return the input
     */
    public String allInputs(String input){
        return input;
    }
}