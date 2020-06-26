package staff.recruiment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Grading extends JDialog {
    public JPanel contentPane;
    private JButton �����������������Button;
    private JButton �������Button;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField textField12;
    private JTextField textField13;
    private JTextField textField14;
    private JTextField textField15;
    private JTextField textField16;
    private JTextField textField17;
    private JLabel label1;
    static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // use specific driver for your database
    static final String DB_URL = "jdbc:sqlserver://DESKTOP-PC6BAG0\\SQLEXPRESS;databaseName=Diploma;integratedSecurity=true";
    Connection conn = null;
    String sql = null;
    int access = Autorization.Access_level;
    String tab_number = Autorization.Tab_Number;
    String vacant_pisition = null;
    String who = null;


    public Grading() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(�����������������Button);
        if (access == 1) {
            who = "������������";
        } else if (access == 2) {
            who = "�������";
        } else if (access == 3) {
            who = "������� �1";
        } else if (access == 4) {
            who = "������� #2";
        }
        label1.setText("�� ����� ��� " + who);
        textField1.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if ((textField1.getText() + e.getKeyChar()).length() > 2) {
                    e.consume();
                }
            }
        });

        textField2.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if ((textField2.getText() + e.getKeyChar()).length() > 2) {
                    e.consume();
                }
            }
        });
        textField3.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if ((textField3.getText() + e.getKeyChar()).length() > 2) {
                    e.consume();
                }
            }
        });
        textField4.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if ((textField4.getText() + e.getKeyChar()).length() > 2) {
                    e.consume();
                }
            }
        });
        textField5.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if ((textField5.getText() + e.getKeyChar()).length() > 2) {
                    e.consume();
                }
            }
        });
        textField6.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if ((textField6.getText() + e.getKeyChar()).length() > 2) {
                    e.consume();
                }
            }
        });
        textField7.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if ((textField7.getText() + e.getKeyChar()).length() > 2) {
                    e.consume();
                }
            }
        });
        textField8.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if ((textField8.getText() + e.getKeyChar()).length() > 2) {
                    e.consume();
                }
            }
        });
        textField9.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if ((textField9.getText() + e.getKeyChar()).length() > 2) {
                    e.consume();
                }
            }
        });
        textField10.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if ((textField10.getText() + e.getKeyChar()).length() > 2) {
                    e.consume();
                }
            }
        });
        textField11.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if ((textField11.getText() + e.getKeyChar()).length() > 2) {
                    e.consume();
                }
            }
        });
        textField12.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if ((textField12.getText() + e.getKeyChar()).length() > 2) {
                    e.consume();
                }
            }
        });
        textField13.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if ((textField13.getText() + e.getKeyChar()).length() > 2) {
                    e.consume();
                }
            }
        });
        textField14.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if ((textField14.getText() + e.getKeyChar()).length() > 2) {
                    e.consume();
                }
            }
        });
        textField15.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if ((textField15.getText() + e.getKeyChar()).length() > 2) {
                    e.consume();
                }
            }
        });
        textField16.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if ((textField16.getText() + e.getKeyChar()).length() > 2) {
                    e.consume();
                }
            }
        });

        �����������������Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Add();
            }
        });

        �������Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Delete();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
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
                try {
                    Delete();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void Add() {
        try {
            conn = DriverManager.getConnection(DB_URL);
            String sql1 = "DELETE from ������ WHERE Tabkod=? AND ���������_���_���������=?";
            PreparedStatement pst1 = conn.prepareStatement(sql1);
            pst1.setString(1, tab_number);
            pst1.setString(2, textField17.getText());
            pst1.executeUpdate();
            pst1.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "���� ������ �������");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void Delete() throws SQLException {
        int year = getCurrentYear();
        //(Tabkod,���_������,�11,�12,�13,�14,�15,�16,�17,�18,�21,�22,�23,�24,�31,�32,�33,�34,���)
        //text17,text18,text1,text2,text3,text4,text5,text6,text7,text8,text9,text10,text11,text12,text13,text14,text15,text16,year
        conn = DriverManager.getConnection(DB_URL);
        String sql1 = "select ���������_��������� from ���������� WHERE TN=?";
        PreparedStatement pst1 = conn.prepareStatement(sql1);
        pst1.setString(1, textField17.getText());
        ResultSet rs = pst1.executeQuery();
        rs.next();
        vacant_pisition = rs.getString(1);
        pst1.close();
        sql = "INSERT INTO ������ VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, tab_number);
        pst.setString(2, textField17.getText());
        pst.setInt(3, access);
        pst.setString(4, vacant_pisition);
        pst.setString(5, textField1.getText());
        pst.setString(6, textField2.getText());
        pst.setString(7, textField3.getText());
        pst.setString(8, textField4.getText());
        pst.setString(9, textField5.getText());
        pst.setString(10, textField6.getText());
        pst.setString(11, textField7.getText());
        pst.setString(12, textField8.getText());
        pst.setString(13, textField9.getText());
        pst.setString(14, textField10.getText());
        pst.setString(15, textField11.getText());
        pst.setString(16, textField12.getText());
        pst.setString(17, textField13.getText());
        pst.setString(18, textField14.getText());
        pst.setString(19, textField15.getText());
        pst.setString(20, textField16.getText());
        pst.setString(21, String.valueOf(year));
        pst.executeUpdate();
        pst.close();
        conn.close();
        JOptionPane.showMessageDialog(null, "���� ������ ���������");
    }


    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        calendar.setTime(new Date());
        return calendar.get(Calendar.YEAR);
    }

    public static void Open() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Grading dialog = new Grading();
                    dialog.setContentPane(new Grading().contentPane);
                    dialog.setSize(800, 700);
                    dialog.pack();
                    dialog.setTitle("���������� ������������");
                    dialog.setVisible(true);
                    System.exit(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        Grading dialog = new Grading();
        dialog.setContentPane(new Grading().contentPane);
        dialog.setTitle("���������� ������������");
        dialog.setSize(800, 700);
        dialog.pack();

        dialog.setVisible(true);
    }
}