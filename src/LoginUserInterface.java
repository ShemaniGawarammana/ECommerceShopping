import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// Class for the login user interface
public class LoginUserInterface {

    JFrame loginFrame = new JFrame();
    JButton loginButton = new JButton("Login");
    JButton createAccountButton = new JButton("Create account");
    JTextField userNameField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userNameLabel = new JLabel("Username:");
    JLabel userPasswordLabel = new JLabel("Password:");
    JLabel messageLabel = new JLabel();

    ArrayList<User> usersList;

    // Constructor for the login user interface
    LoginUserInterface(ArrayList<User> usersList) {
        this.usersList = usersList;

        userNameLabel.setBounds(50, 70, 75, 25);
        userNameField.setBounds(120, 70, 200, 25);

        userPasswordLabel.setBounds(50, 120, 75, 25);
        userPasswordField.setBounds(120, 120, 200, 25);

        // Add Action Listener
        LoginUserInterface.MyListener handler = new LoginUserInterface.MyListener();
        loginButton.setBounds(70, 170, 90, 25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(handler);

        createAccountButton.setBounds(180, 170, 130, 25);
        createAccountButton.setFocusable(false);
        createAccountButton.addActionListener(handler);

        messageLabel.setText(" Welcome to the Westminster Shopping Portal ");
        messageLabel.setBounds(60, 213, 270, 35);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);


        loginFrame.add(userNameLabel);
        loginFrame.add(userPasswordLabel);
        loginFrame.add(messageLabel);
        loginFrame.add(userNameField);
        loginFrame.add(userPasswordField);
        loginFrame.add(loginButton);
        loginFrame.add(createAccountButton);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(390, 390);
        loginFrame.setResizable(false);
        loginFrame.setLayout(null);
        loginFrame.setVisible(true);
        loginFrame.setState(JFrame.NORMAL);
    }

    // Inner class for ActionListener
    private class MyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // creating a new user account
            if (e.getSource() == createAccountButton) {
                String username = userNameField.getText();
                String password = String.valueOf(userPasswordField.getPassword());

                if (!username.isEmpty() || !password.isEmpty()) {
                    User user = new User(username, password,false);
                    User.saveToFile();
                    messageLabel.setText("Account has been created.");
                    loginFrame.dispose();
                    UserShoppingInterface userShoppingInterface = new UserShoppingInterface(user);
                } else {
                    messageLabel.setText("Fill both fields");
                }
            }

            //logging in a user
            if (e.getSource() == loginButton) {
                String userID = userNameField.getText();
                String password = String.valueOf(userPasswordField.getPassword());

                boolean userFound = false;

                for (User user : usersList) {
                    if (user.getUsername().equals(userID)) {
                        userFound = true;
                        if (user.getPassword().equals(password)) {
                            messageLabel.setText("Login successful");
                            loginFrame.dispose();
                            UserShoppingInterface userShoppingInterface = new UserShoppingInterface(user);
                        } else {
                            messageLabel.setText("Wrong password");
                        }
                        break;
                    }
                }

                if (!userFound) {
                    messageLabel.setText("Username not found");
                }
            }
        }
    }
}
