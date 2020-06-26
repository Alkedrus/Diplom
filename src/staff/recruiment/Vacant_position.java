package staff.recruiment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Vacant_position extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JTextField textField2;
    private JComboBox comboBox2;
    private JComboBox comboBox3;

    static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // use specific driver for your database
    static final String DB_URL = "jdbc:sqlserver://DESKTOP-PC6BAG0\\SQLEXPRESS;databaseName=Diploma;integratedSecurity=true";
    Connection conn = null;
    String sql = null;

    public Vacant_position() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        String [] petStrings = {"Экономическая","Техническая","Психологическая","Физико-математическая","Химическая","Прочее"};
        for (String employee : petStrings) {
            comboBox1.addItem(employee);
        }
        String [] petStrings1 = {"Базовое","Среднее","Проффессионально-техническое","Среднее специальное","Высшее проффессиональное"};
        for (String employee : petStrings1) {
            comboBox3.addItem(employee);
        }
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Add();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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

    private void Add() {
        // add your code here
        try {
            conn = DriverManager.getConnection(DB_URL);
            sql = "INSERT INTO Вакантная_должности(Вакантная_должности.Должность) VALUES (?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, textField1.getText());
            pst.executeUpdate();
            pst.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Добавление прошло успешно");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void open() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Vacant_position dialog = new Vacant_position();
                    dialog.setContentPane(new Vacant_position().contentPane);
                    dialog.setTitle("Добавление вакантной позиции");
                    dialog.pack();
                    dialog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        Vacant_position dialog = new Vacant_position();
        dialog.pack();
        dialog.setTitle("Добавление вакантной позиции");
        dialog.setVisible(true);
        System.exit(0);
    }
}