import java.lang.reflect.Array;
public class InputLogger {
    public String[] loginLogger(String userName, String passWord ){
        String[] loginInfo = new String[2];
        loginInfo[0]=userName;
        loginInfo[1]=passWord;
        return loginInfo;
    }
    public int menuLogger(int menuChoice){
        return menuChoice;
    }

    public String allInputs(String input){
        return input;
    }
}