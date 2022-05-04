package za.ac.up.cs.cos221;

import java.sql.*;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Database {
    public static String SAKILA_DB_PROTO = "";
    public static String SAKILA_DB_HOST = "";
    public static String SAKILA_DB_PORT = "";
    public static String SAKILA_DB_NAME = "";
    public static String SAKILA_DB_USERNAME = "";
    public static String SAKILA_DB_PASSWORD = "";
    public static String url =  SAKILA_DB_PROTO + "://" + SAKILA_DB_HOST+ ":" + SAKILA_DB_PORT+ "/" + SAKILA_DB_NAME;
    public static Connection conn = null;

    public Database() {
        try {
            File myObj = new File("config.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] line = data.split(":");

                switch(line[0]) {
                    case "SAKILA_DB_PROTO": SAKILA_DB_PROTO = line[1] + data; break;
                    case "SAKILA_DB_HOST": SAKILA_DB_HOST = line[1]; break;
                    case "SAKILA_DB_PORT": SAKILA_DB_PORT = line[1]; break;
                    case "SAKILA_DB_NAME": SAKILA_DB_NAME = line[1]; break;
                    case "SAKILA_DB_USERNAME": SAKILA_DB_USERNAME = line[1]; break;
                    case "SAKILA_DB_PASSWORD": SAKILA_DB_PASSWORD = line[1]; break;
                }
            }

            myReader.close();
            url =  SAKILA_DB_PROTO + "://" + SAKILA_DB_HOST+ ":" + SAKILA_DB_PORT+ "/" + SAKILA_DB_NAME;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void connect() {
        try {
//            System.out.println(url);
            // get a connection to the database
            conn = DriverManager.getConnection(url, SAKILA_DB_USERNAME, SAKILA_DB_PASSWORD);

            // create a statement
            Statement statement = conn.createStatement();

            // execute a sql query
            ResultSet result = statement.executeQuery("select * from film_text");

            // process the result set
            while (result.next()) {
                System.out.println(result.getString("film_id") + ", " + result.getString("title") + ", "
                        + result.getString("description"));
            }

            // close sql connection
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // sql operations
//    public static String
    // getter functions

    public static String[][] getStaffData() {
        String[][] data = null;
        try {
            // get a connection to the database
            conn = DriverManager.getConnection(url, SAKILA_DB_USERNAME, SAKILA_DB_PASSWORD);

            // make statement
            Statement statement = conn.createStatement();

            // get result
            ResultSet resultSet = statement.executeQuery("select staff.staff_id, staff.first_name, staff.last_name, address.address, address.address2, address.district, city.city, address.postal_code, address.phone, staff.store_id, staff.active from staff inner join address on staff.address_id = address.address_id inner join city on address.city_id = city.city_id");

            if(resultSet != null) {
                resultSet.last();
                int size = resultSet.getRow();
                resultSet.beforeFirst();
                data = new String[size][10];
                int index = 0;

                while(resultSet.next()) {
                    data[index] = new String[11];
                    data[index][0] = resultSet.getString("staff_id");
                    data[index][1] = resultSet.getString("first_name");
                    data[index][2] = resultSet.getString("last_name");
                    data[index][3] = resultSet.getString("address");
                    data[index][4] = resultSet.getString("address2");
                    data[index][5] = resultSet.getString("district");
                    data[index][6] = resultSet.getString("city");
                    data[index][7] = resultSet.getString("postal_code");
                    data[index][8] = resultSet.getString("phone");
                    data[index][9] = resultSet.getString("store_id");
                    data[index][10] = resultSet.getString("active");
                    index++;
                }
            }

            // close connection
            conn.close();

            return data;
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String[][] getClientsData() {
        String[][] data = null;
        try {
            // get a connection to the database
            conn = DriverManager.getConnection(url, SAKILA_DB_USERNAME, SAKILA_DB_PASSWORD);

            // make statement
            Statement statement = conn.createStatement();

            // get result
            ResultSet resultSet = statement.executeQuery("select customer.customer_id, customer.first_name, customer.last_name, customer.email, address.address, customer.active from customer inner join address on customer.address_id = address.address_id");

            if(resultSet != null) {
                resultSet.last();
                int size = resultSet.getRow();
                resultSet.beforeFirst();
                data = new String[size][5];
                int index = 0;

                while(resultSet.next()) {
                    data[index] = new String[6];
                    data[index][0] = resultSet.getString("customer_id");
                    data[index][1] = resultSet.getString("first_name");
                    data[index][2] = resultSet.getString("last_name");
                    data[index][3] = resultSet.getString("email");
                    data[index][4] = resultSet.getString("address");
                    data[index][5] = resultSet.getString("active");
                    index++;
                }
            }

            // close connection
            conn.close();

            return data;
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String[][] getFilmsData() {
        String[][] data = null;
        try {
            // get a connection to the database
            conn = DriverManager.getConnection(url, SAKILA_DB_USERNAME, SAKILA_DB_PASSWORD);

            // make statement
            Statement statement = conn.createStatement();

            // get result
            ResultSet resultSet = statement.executeQuery(" select film_id, title, description, release_year, rental_rate, special_features, language_id from film");

            if(resultSet != null) {
                resultSet.last();
                int size = resultSet.getRow();
                resultSet.beforeFirst();
                data = new String[size][5];
                int index = 0;

                while(resultSet.next()) {
                    data[index] = new String[7];
                    data[index][0] = resultSet.getString("film_id");
                    data[index][1] = resultSet.getString("title");
                    data[index][2] = resultSet.getString("description");
                    data[index][3] = resultSet.getString("release_year");
                    data[index][4] = resultSet.getString("rental_rate");
                    data[index][5] = resultSet.getString("special_features");
                    data[index][6] = resultSet.getString("language_id");
                    index++;
                }
            }

            // close connection
            conn.close();

            return data;
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
