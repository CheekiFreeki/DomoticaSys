package sample;

import java.sql.*;

public class Database {
    public Connection databaseLink;

    private static final String url = "jdbc:mysql://localhost/snailsol";
    private static final String user = "root";
    private static final String pass = "";

    public Connection getConnection(){
        String databaseName = "snailsolutions";
        String databaseUser = "root";
        String databasePassword = "";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        }catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return databaseLink;
    }

    public static void insertNewAccount(String username, String hashedPassword, String firstname, String surname) throws SQLException
    {
        Connection connection = DriverManager.getConnection(url, user, pass);
        Statement statement = connection.createStatement();

        PreparedStatement ps= connection.prepareStatement("INSERT INTO account (firstname, surname, username, password)" +
                                                              "VALUES (?,?,?,?) " );
        ps.setString(1, firstname);
        ps.setString(2, surname);
        ps.setString(3, username);
        ps.setString(4, hashedPassword);
        ps.executeUpdate();
        connection.close();
    }

    public static boolean checkPassword(String username, String hashedPassword) throws SQLException
    {
        boolean bl;
        Connection connection = DriverManager.getConnection(url, user, pass);
        Statement statement = connection.createStatement();

        PreparedStatement ps = connection.prepareStatement( "SELECT password " +
                                                                "FROM account " +
                                                                "WHERE username = ?");
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        if(rs.next())
        {
            if (rs.getString("password").equals(hashedPassword))
            {
                bl = true;
            }
            else
            {
                bl = false;
            }
        }
        else
        {
            bl = false;
        }
        connection.close();
        return bl;
    }
    public static void insertRpi(String raspberryPiIp, String username) throws SQLException
    {
        Connection connection = DriverManager.getConnection(url, user, pass);
        Statement statement = connection.createStatement();

        PreparedStatement ps= connection.prepareStatement( "UPDATE account " +
                                                                "SET raspberry=? " +
                                                                "WHERE username=?" );
        ps.setString(1, raspberryPiIp);
        ps.setString(2, username);
        ps.executeUpdate();
        connection.close();
    }
    public static String getRpiIp(String username) throws SQLException
    {
        String ip;
        Connection connection = DriverManager.getConnection(url, user, pass);
        Statement statement = connection.createStatement();

        PreparedStatement ps = connection.prepareStatement( "SELECT raspberry " +
                                                                "FROM account " +
                                                                "WHERE username = ?");
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
        {
            ip = rs.getString("raspberry");
        }
        else
        {
            ip = null;
        }
        connection.close();
        return ip;
    }
    public static void insertArduino(String arduinoPort, String username) throws SQLException
    {
        Connection connection = DriverManager.getConnection(url, user, pass);
        Statement statement = connection.createStatement();

        PreparedStatement ps= connection.prepareStatement("UPDATE account " +
                                                              "SET arduino = ? " +
                                                              "WHERE username = ?" );
        ps.setString(1, arduinoPort);
        ps.setString(2, username);
        ps.executeUpdate();
        connection.close();
    }
    public static String getArduino(String username) throws SQLException
    {
        String port;
        Connection connection = DriverManager.getConnection(url, user, pass);
        Statement statement = connection.createStatement();

        PreparedStatement ps = connection.prepareStatement( "SELECT arduino " +
                                                                "FROM account " +
                                                                "WHERE username = ?");
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
        {
            port = rs.getString("arduino");
        }
        else
        {
            port = null;
        }
        connection.close();
        return port;
    }
    public static void insertLight(int light_pref, String username) throws SQLException
    {
        Connection connection = DriverManager.getConnection(url, user, pass);
        Statement statement = connection.createStatement();

        PreparedStatement ps= connection.prepareStatement("UPDATE account " +
                                                              "SET light_pref = ? " +
                                                              "WHERE username = ?" );
        ps.setInt(1, light_pref);
        ps.setString(2, username);
        ps.executeUpdate();
        connection.close();
    }
    public static void insertTemp(int temp_pref, String username) throws SQLException
    {
        Connection connection = DriverManager.getConnection(url, user, pass);
        Statement statement = connection.createStatement();

        PreparedStatement ps= connection.prepareStatement("UPDATE account " +
                                                              "SET temp_pref = ? " +
                                                              "WHERE username = ?" );
        ps.setInt(1, temp_pref);
        ps.setString(2, username);
        ps.executeUpdate();
        connection.close();
    }
    public static String getLight(String username) throws SQLException
    {
        String light;
        Connection connection = DriverManager.getConnection(url, user, pass);
        Statement statement = connection.createStatement();

        PreparedStatement ps = connection.prepareStatement( "SELECT light_pref " +
                                                                "FROM account " +
                                                                "WHERE username = ?");
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
        {
            light = rs.getString("light_pref");
        }
        else
        {
            light = null;
        }
        connection.close();
        return light;
    }
    public static String getTemp(String username) throws SQLException
    {
        String temp;
        Connection connection = DriverManager.getConnection(url, user, pass);
        Statement statement = connection.createStatement();

        PreparedStatement ps = connection.prepareStatement( "SELECT temp_pref " +
                                                                "FROM account " +
                                                                "WHERE username = ?");
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (rs.next())
        {
            temp = rs.getString("temp_pref");
        }
        else
        {
            temp = null;
        }
        connection.close();
        return temp;
    }
}
