package Jdbc1;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class Main {
    // CREATE DATABASE mydb;
    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/mydb?serverTimezone=Europe/Kiev";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "Chibis_1{^38";

    static Connection conn;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            try {
                // create connection
                conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
                initDB();

                while (true) {
                    System.out.println("1: add client");
                    System.out.println("2: add random clients");
                    System.out.println("3: delete client");
                    System.out.println("4: change client");
                    System.out.println("5: view clients");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            addClient(sc);
                            break;
                        case "2":
                            insertRandomClients(sc);
                            break;
                        case "3":
                            deleteClient(sc);
                            break;
                        case "4":
                            changeClient(sc);
                            break;
                        case "5":
                            viewClients();
                            break;
                        default:
                            return;
                    }
                }
            } finally {
                sc.close();
                if (conn != null) conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return;
        }
    }

    private static void initDB() throws SQLException {
        Statement st = conn.createStatement();
        try {
            st.execute("DROP TABLE IF EXISTS Clients");
            st.execute("CREATE TABLE Clients (id INT NOT NULL " +
                    "AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20) " +
                    "NOT NULL, age INT)");
        } finally {
            st.close();
        }

        /*
        try (Statement st1 = conn.createStatement()) {
            st1.execute("DROP TABLE IF EXISTS Clients");
            st1.execute("CREATE TABLE Clients (id INT NOT NULL " +
                    "AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20) " +
                    "NOT NULL, age INT)");
        }
         */
    }

    private static void addClient(Scanner sc) throws SQLException {
        System.out.print("Enter client name: ");
        String name = sc.nextLine();
        System.out.print("Enter client age: ");
        String sAge = sc.nextLine();
        int age = Integer.parseInt(sAge);

        //String sql = "INSERT INTO Clients (name, age) " +
          //      "VALUES(" + name + ", " + age + ")";

        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO Clients (name, age) VALUES(?, ?)");
        try {
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.executeUpdate(); // for INSERT, UPDATE & DELETE

            ps.setString(1, name + "1");
            ps.setInt(2, age);
            ps.executeUpdate();
        } finally {
            ps.close();
        }
    }

    private static void deleteClient(Scanner sc) throws SQLException {
        System.out.print("Enter client name: ");
        String name = sc.nextLine();

        PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM Clients WHERE name = ?");
        try {
            ps.setString(1, name);
            ps.executeUpdate(); // for INSERT, UPDATE & DELETE
        } finally {
            ps.close();
        }
    }

    private static void changeClient(Scanner sc) throws SQLException {
        System.out.print("Enter client name: ");
        String name = sc.nextLine();
        System.out.print("Enter new age: ");
        String sAge = sc.nextLine();
        int age = Integer.parseInt(sAge);

        PreparedStatement ps = conn.prepareStatement("UPDATE Clients SET age = ? WHERE name = ?");
        try {
            ps.setInt(1, age);
            ps.setString(2, name);
            ps.executeUpdate(); // for INSERT, UPDATE & DELETE
        } finally {
            ps.close();
        }
    }

    private static void insertRandomClients(Scanner sc) throws SQLException {
        System.out.print("Enter clients count: ");
        String sCount = sc.nextLine();
        int count = Integer.parseInt(sCount);
        Random rnd = new Random();

        conn.setAutoCommit(false); // enable transactions
        try {
            try {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO Clients (name, age) VALUES(?, ?)");
                try {
                    for (int i = 0; i < count; i++) {
                        ps.setString(1, "Name" + i);
                        ps.setInt(2, rnd.nextInt(100));
                        ps.executeUpdate();
                    }
                    conn.commit();
                } finally {
                    ps.close();
                }
            } catch (Exception ex) {
                conn.rollback();
            }
        } finally {
            conn.setAutoCommit(true); // return to default mode
        }
    }

    /*

    1 2 3
    -----
    -----
    -----

     */

    private static void viewClients() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Clients");
        try {
            // table of data representing a database result set,
            ResultSet rs = ps.executeQuery();

            try {
                // can be used to get information about the types and properties of the columns in a ResultSet object
                ResultSetMetaData md = rs.getMetaData();

                for (int i = 1; i <= md.getColumnCount(); i++)
                    System.out.print(md.getColumnName(i) + "\t\t");
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t\t");
                    }
                    System.out.println();
                }
            } finally {
                rs.close(); // rs can't be null according to the docs
            }
        } finally {
            ps.close();
        }
    }
}
