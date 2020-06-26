package staff.recruiment;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.Arrays;

public class Autorization extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // use specific driver for your database
    static final String DB_URL = "jdbc:sqlserver://DESKTOP-PC6BAG0\\SQLEXPRESS;databaseName=Diploma;integratedSecurity=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    Connection conn = null;
    String sql = "SELECT Имя,Пароль,Права,Табельный_номер FROM Логин ";
    public static int Access_level = 0;
    public static String Tab_Number = null;
    private Cipher cipher;
    private String transformation = "AES/ECB/PKCS5Padding";

    public Autorization() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Autorization();


            }
        });


        // call onCancel() when cross is clicked
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private SecretKeySpec createSecretKey() {
        SecretKeySpec sks = null;
        byte[] bytes = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);

//  System.out.println("random : " +
//          DatatypeConverter.printHexBinary(bytes));
        try {
            MessageDigest md;
            byte[] key;
            md = MessageDigest.getInstance("SHA-1");

            key = md.digest(bytes);
            key = Arrays.copyOf(key, 16);
            sks = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
        }
        return sks;
    }

    public byte[] encrypt(SecretKeySpec secretKey,
                          byte[] plainText) {
        try {
            cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(plainText);
//          return Base64.getEncoder()
//                       .encode(cipher.doFinal(plainText));
        } catch (Exception e) {
        }
        return null;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public byte[] decrypt(SecretKeySpec secretKey,
                          byte[] encryptedText) {
        try {
            cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(encryptedText);
//          return cipher.doFinal(Base64.getDecoder()
//                                  .decode(encryptedText));
        } catch (Exception e) {
        }
        return null;
    }

    private void Autorization() {
        try {
            conn = DriverManager.getConnection(DB_URL);


            SecretKeySpec key = this.createSecretKey();
            String text = String.valueOf(passwordField1.getPassword());
            byte[] enc = this.encrypt(key, text.getBytes());
            byte[] bytes = this.decrypt(key, enc);
            String plainAfter = null;
            if (bytes != null) {
                plainAfter = new String(bytes);

            }
            PreparedStatement pst2 = conn.prepareStatement(sql);
            ResultSet rs2 = pst2.executeQuery();//
            while (rs2.next()) {
                if ((textField1.getText().equalsIgnoreCase(rs2.getString(1))) && plainAfter.equalsIgnoreCase(rs2.getString(2))) {
                    JOptionPane.showMessageDialog(null, "Авторизация прошла успешно");
                    Access_level = rs2.getInt(3);
                    Tab_Number = rs2.getString(4);
                    if (Access_level == 0) {
                        dispose();
                        Main grading = new Main();
                        grading.open();
                    } else {
                        dispose();
                        Grading grading = new Grading();
                        grading.Open();
                    }

                    break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        Autorization dialog = new Autorization();
        dialog.pack();
        dialog.setTitle("Авторизация");
        dialog.setVisible(true);
        //System.exit(0);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        contentPane.setBackground(new Color(-13948117));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-13948117));
        contentPane.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        textField1 = new JTextField();
        textField1.setBackground(new Color(-13948117));
        textField1.setForeground(new Color(-14959872));
        panel1.add(textField1, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        passwordField1 = new JPasswordField();
        passwordField1.setBackground(new Color(-13948117));
        passwordField1.setForeground(new Color(-14959872));
        panel1.add(passwordField1, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setBackground(new Color(-13948117));
        label1.setForeground(new Color(-14959872));
        label1.setText("Логин");
        panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setBackground(new Color(-13948117));
        label2.setForeground(new Color(-14959872));
        label2.setText("Пароль");
        panel1.add(label2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonOK = new JButton();
        buttonOK.setBackground(new Color(-13948117));
        buttonOK.setForeground(new Color(-14959872));
        buttonOK.setText("Войти");
        contentPane.add(buttonOK, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
