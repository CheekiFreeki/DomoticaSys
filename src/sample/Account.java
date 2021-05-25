package sample;

import javafx.scene.control.TextField;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Account {
    private String username;
    private RaspberryPi rpi;
    private MyArduino myArduino;

    public Account(String username) {
        this.username = username;
        Main.mainAccount = this;
    }

    public static Account signUp(String username, String password, String firstname, String surname) throws SQLException, NoSuchAlgorithmException {
        String hashedPassword = hashPassword(password);

        Database.insertNewAccount(username, hashedPassword, firstname, surname);

        return new Account(username);
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] resultByteArray = md.digest();

            StringBuilder sb = new StringBuilder();

            for (byte b : resultByteArray) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
            return "";
        }
    }

    public String getUsername() {
        return username;
    }

    public RaspberryPi getRpi() {
        return rpi;
    }

    public void setRpi(RaspberryPi rpi) {
        this.rpi = rpi;
    }

    public MyArduino getMyArduino() {
        return myArduino;
    }

    public void setMyArduino(MyArduino myArduino) {
        this.myArduino = myArduino;
    }

    public void setUsername(String newUsername){
        this.username = newUsername;
    };

}


