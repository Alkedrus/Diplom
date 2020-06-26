package staff.recruiment;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.List;

public class Challenger extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JComboBox comboBox1;
    private JTextField textField6;
    private JTextField textfield7;
    private JFormattedTextField formattedTextField1;
    private JTextField textField7;
    private JTextField textField9;
    private JComboBox comboBox2;
    private JComboBox comboBox3;

    static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // use specific driver for your database
    static final String DB_URL = "jdbc:sqlserver://DESKTOP-PC6BAG0\\SQLEXPRESS;databaseName=Diploma;integratedSecurity=true";
    Connection conn = null;

    String sql = null;
    String sql1 = null;
    String fio = null;
    int sex = 1;


    public Challenger() throws ParseException {
        setContentPane(contentPane);
        setModal(true);
        String [] petStrings1 = {"Базовое","Среднее","Проффессионально-техническое","Среднее специальное","Высшее проффессиональное"};
        for (String employee : petStrings1) {
            comboBox2.addItem(employee);
        }
        String [] petStrings = {"Экономическая","Техническая","Психологическая","Физико-математическая","Химическая","Прочее"};
        for (String employee : petStrings) {
            comboBox3.addItem(employee);
        }
        getRootPane().setDefaultButton(buttonOK);

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
        try {
            JOptionPane.showMessageDialog(null,
                    "Ошибка!"+"Уровень образования слишком низок для данной должности", "Ошибка", JOptionPane.ERROR_MESSAGE);

            if (!textField5.getText().equalsIgnoreCase("М")) {
                sex = 2;
            }
            fio = textField2.getText() + textField3.getText() + textField4.getText();
            conn = DriverManager.getConnection(DB_URL);
            sql = "INSERT INTO Претендент VALUES (?,?,?,?,?,?,?,?,?)";
            sql1 = "INSERT INTO Основное VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";//19
            PreparedStatement pst = conn.prepareStatement(sql);
            PreparedStatement pst1 = conn.prepareStatement(sql1);
            Statement stm = conn.createStatement();

            String query = "SELECT * FROM Руководитель WHERE Цех=?";
            PreparedStatement pst2 = conn.prepareStatement(query);
            pst2.setString(1, textField6.getText());
            ResultSet resultat = pst2.executeQuery();
            if (resultat.next()) {
                String string1 = formattedTextField1.getText();
                int sub = Integer.parseInt(string1.substring(6));

                pst1.setString(1, resultat.getString(1));
                pst1.setString(2, textField6.getText());
                pst1.setString(3, textField1.getText());
                pst1.setString(4, fio);
                pst1.setString(5, ("1980-12-09 "));
                pst1.setString(6, String.valueOf((getCurrentYear() - sub)));
                pst1.setString(8, formattedTextField1.getText());
                pst1.setString(7, textfield7.getText());
                pst1.setString(9, "009852003088");
                pst1.setString(10, resultat.getString(2));
                pst1.setString(11, resultat.getString(3));
                pst1.setString(12, resultat.getString(4));
                pst1.setString(13, resultat.getString(5));
                pst1.setString(14, resultat.getString(6));
                pst1.setString(15, resultat.getString(7));
                pst1.setString(16, "1");
                pst1.setString(18, "БСЗ");
                pst1.setInt(17, sex);
                pst1.setString(19, (String) comboBox1.getSelectedItem());

            }
            pst.setString(1, textField1.getText());
            pst.setString(2, textField2.getText());
            pst.setString(3, textField3.getText());
            pst.setString(4, textField4.getText());
            pst.setInt(5, sex);
            pst.setString(6, textfield7.getText());
            pst.setString(7, formattedTextField1.getText());
            pst.setString(8, (String) comboBox1.getSelectedItem());
            pst.setString(9, textField6.getText());
            pst.executeUpdate();
            pst.close();
            pst1.executeUpdate();
            pst1.close();
            conn.close();
            JOptionPane.showMessageDialog(null,
                    "Ошибка!"+"Уровень образования слишком низок для данной должности", "Ошибка", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<String> listeEmployee() {
        List<String> list = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(DB_URL);
            Statement stm = conn.createStatement();

            String query = "SELECT Должность FROM Вакантная_должности";

            ResultSet resultat = stm.executeQuery(query);
            while (resultat.next()) {
                list.add(resultat.getString("Должность"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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
                    main(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) throws ParseException {

        Challenger dialog = new Challenger();
        dialog.comboBox1.removeAllItems();
        dialog.setTitle("Добавление претендентов");

        for (String employee : dialog.listeEmployee()) {
            dialog.comboBox1.addItem(employee);
        }
        dialog.pack();
        dialog.setVisible(true);
        // System.exit(0);
    }

   public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        calendar.setTime(new Date());
        return calendar.get(Calendar.YEAR);
    }
}