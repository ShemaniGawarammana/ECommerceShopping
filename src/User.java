import java.io.*;
import java.util.ArrayList;

public class User {
    private static ArrayList<User> usersList = new ArrayList<>();
    // Declaring variables
    private String username;
    private String password;
    private boolean hasShopped;

    // Constructors
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.hasShopped = false;
        addToUserList(this);
    }

    public User(String username, String password, boolean hasShopped) {
        this.username = username;
        this.password = password;
        this.hasShopped = hasShopped;
        addToUserList(this);
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHasShopped(boolean hasShopped) {
        this.hasShopped = hasShopped;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean getHasShopped() {
        return hasShopped;
    }
    public static ArrayList<User> getUserList() {
        return usersList;
    }

    // Method to add a user to the static list
    private static void addToUserList(User user) {
        usersList.add(user);
    }

    // Save the list of users to a file
    public static void saveToFile() {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("UserListOf Details.txt", false));
            for (User user : usersList) {
                writer.write(user.getUsername() + "-" + user.getPassword() + "-" + user.getHasShopped());
                writer.newLine();
            }
            writer.close();

        }catch (FileNotFoundException e){
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Unable to read document");
        } catch (NumberFormatException e) {
            System.out.println("Unable to convert to an Integer");
        }
    }

    // Method to restore users from a file
    public static void restoreUsersFromFile() {
        usersList.clear();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("UserListOf Details.txt"));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split("-");

                if (userData.length >= 3) {
                    User user = new User(userData[0], userData[1], Boolean.parseBoolean(userData[2]));
                    usersList.add(user);
                } else {
                    System.out.println("Invalid data format in the file: " + line);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Unable to read document");
        } catch (NumberFormatException e) {
            System.out.println("Unable to convert to an Integer");
        }
    }



    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", hasShopped=" + hasShopped +
                '}';
    }
}
