package com.Iniebiyo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Iniebiyo Joshua on 3/23/2017.
 *
 */

// This program validates credit cards.
public class CCValidator extends JFrame {
    private JPanel rootPanel;
    private JTextField creditCardNumberTextField;
    private JButton validateButton;
    private JButton QuitButton;
    private JLabel validateMessageLabel;


    protected CCValidator() {
        super("Credit Card Validator");
        setContentPane(rootPanel); //Set the content pane of
        // the window rootPanel Jpanel.
        pack(); //Add the components to the window.
        //Close the program when you close this window.
        setSize(new Dimension(500,250));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); //Make the content visible.


        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ccNumber = creditCardNumberTextField.getText();
                boolean valid = isVisaCreditCardNumberValid(ccNumber);
                if (valid) {
                    validateMessageLabel.setText("Credit Card Number is Valid,");
                } else {
                    validateMessageLabel.setText("Credit Card Number is NOT  Valid,");
                }
            }
        });


        QuitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quit = JOptionPane.showConfirmDialog(
                        CCValidator.this, "Are you sure you want to quit?",
                        "Quit", JOptionPane.OK_CANCEL_OPTION);
                if (quit == JOptionPane.OK_OPTION) {

                    System.exit(0);
                }
            }
        });
    }

    private static boolean isVisaCreditCardNumberValid(String cc) {
        if (!cc.startsWith("4")) {
            // The number has to start with 4.
            System.out.println("Doesn't start with 4, invalid");
            return false;
        }
        //Check to see if the number is 16 digits long.
        if (cc.length() != 16) {
            System.out.println("Credit card must be 16 numbers, invalid");
            return false;
        }


        int sum = 0;

        for (int i = 0; i < 16; i++) {
            int thisDigit = Integer.parseInt((cc.substring(i, i + 1)));
            if (i % 2 == 1) {
                sum = sum + thisDigit;
            } else {
                int doubled = thisDigit * 2;
                if (doubled > 9) {
                    int toAdd = 1 + (doubled % 10);
                    sum = sum + toAdd;
                } else {
                    sum = sum + (thisDigit * 2);
                }
            }
        }

        if (sum % 10 == 0) {
            return true;
        }

        System.out.println("Check digit is wrong, card number is invalid");
        return false;

    }



}