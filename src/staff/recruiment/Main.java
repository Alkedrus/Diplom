package staff.recruiment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Vector;

public class Main extends JDialog {
    public JPanel contentPane;
    private JButton buttonOK;
    private JTable table1;
    private JButton �������Button;
    private JButton �����Button;
    private JTextField textField1;
    private JButton buttonCancel;
    private JButton button1;
    private JButton �����������������������Button;
    static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // use specific driver for your database
    static final String DB_URL = "jdbc:sqlserver://DESKTOP-PC6BAG0\\SQLEXPRESS;databaseName=Diploma;integratedSecurity=true";
    Connection conn = null;
    Statement stmt = null;
    String query = "select * from ��������";
    static formuls formula;
    static Vector<Vector<String>> data = new Vector<Vector<String>>();

    public Main() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        Vector<String> columnNames = null;
        columnNames = new Vector<String>();


        columnNames.add("��� ����");
        columnNames.add("��� �����������");
        columnNames.add("��������� ����� �����������");
        columnNames.add("��� �����������");
        // columnNames.add("���� �������� �����������");
        columnNames.add("����");
        columnNames.add("��������� �����������");
        columnNames.add("� ��������� � ����");

        columnNames.add("��� ������������");
        columnNames.add("��������� ��� ������������");
        columnNames.add("��� ������������");
        columnNames.add("���� �������� ������������");
        columnNames.add("��������� ������������");
        columnNames.add("� ��������� � ����");
        columnNames.add("���");
        columnNames.add("�����");
        columnNames.add("��������� ���������");

        {

            try {


                conn = DriverManager.getConnection(DB_URL);
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {

                    Vector<String> vstring = new Vector<String>();


                    vstring.add(rs.getString("��� ����"));
                    vstring.add(rs.getString("���_�����������"));
                    vstring.add(rs.getString("���������_�����_�����������"));
                    vstring.add(rs.getString("��� �����������"));
                    //vstring.add(rs.getString("���� �������� �����������"));
                    vstring.add(String.valueOf(rs.getInt("����")));
                    vstring.add(rs.getString("��������� �����������"));
                    vstring.add(rs.getString("� ��������� � ����"));
                    vstring.add(rs.getString("��� ������������"));
                    vstring.add(rs.getString("��������� ����� ������������"));
                    vstring.add(rs.getString("��� �������������"));
                    vstring.add(rs.getString("���� �������� ������������"));
                    vstring.add(rs.getString("��������� ������������"));
                    vstring.add(rs.getString("� ��������� � ����"));
                    vstring.add(rs.getString("���"));
                    vstring.add(rs.getString("npr"));
                    vstring.add(rs.getString("���������_��������"));

                    vstring.add("\n\n\n\n\n\n\n");

                    data.add(vstring);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            } finally {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException ex) {
                        // Logger.getLogger(FlashBuilderGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            DefaultTableModel model = new DefaultTableModel();
            table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            table1.setAutoCreateRowSorter(true);
            table1.setFillsViewportHeight(true);

            table1.setPreferredScrollableViewportSize(new Dimension(550, 200));
            model = new DefaultTableModel(data, columnNames);
            table1.setModel(model);
            TableColumnModel columnModel = table1.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(100);
            columnModel.getColumn(1).setPreferredWidth(100);
            columnModel.getColumn(2).setPreferredWidth(100);
            columnModel.getColumn(3).setPreferredWidth(300);
            columnModel.getColumn(4).setPreferredWidth(100);
            columnModel.getColumn(5).setPreferredWidth(100);
            columnModel.getColumn(6).setPreferredWidth(100);
            columnModel.getColumn(7).setPreferredWidth(100);
            columnModel.getColumn(8).setPreferredWidth(100);
            columnModel.getColumn(9).setPreferredWidth(100);
            columnModel.getColumn(10).setPreferredWidth(100);
            columnModel.getColumn(11).setPreferredWidth(100);
            columnModel.getColumn(12).setPreferredWidth(100);
            columnModel.getColumn(13).setPreferredWidth(100);
            columnModel.getColumn(14).setPreferredWidth(100);
            columnModel.getColumn(15).setPreferredWidth(100);



        }
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Vacant_position grading = new Vacant_position();
                grading.open();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Challenger grading = null;
                try {
                    grading = new Challenger();
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                grading.open();


            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Graphs grading = new Graphs();
                grading.open();
            }
        });


        �������Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = "DELETE FROM �������� WHERE  ���������_�����_�����������= ?";
                String query1 = "DELETE FROM ���������� WHERE TN =?";
                try {
                    conn = DriverManager.getConnection(DB_URL);
                    String sql1 = "DELETE from �������� WHERE ���������_�����_�����������=?";
                    PreparedStatement pst1 = conn.prepareStatement(query);
                    pst1.setString(1, textField1.getText());
                    pst1.executeUpdate();
                    // pst1.close();
                    pst1 = conn.prepareStatement(query1);
                    pst1.setString(1, textField1.getText());
                    pst1.executeUpdate();
                    pst1.close();
                    conn.close();
                    JOptionPane.showMessageDialog(null, "���������� �����!");
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }


            }
        });

        Vector<String> finalColumnNames = columnNames;
        �����Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int qw=0;
                String query = "select * from �������� WHERE ���_�����������=?";
                if((textField1.getText().matches("-?\\d+(\\.\\d+)?"))){qw=0;}
                else{qw=1;}{
                    if(qw==0){query = "select * from �������� WHERE ���������_�����_�����������=?";}
                else{query = "select * from �������� WHERE ���_�����������=?";}
                Vector<Vector<String>> data1 = new Vector<Vector<String>>();
                try {
                    conn = DriverManager.getConnection(DB_URL);
                    PreparedStatement pst1 = conn.prepareStatement(query);
                    pst1.setString(1, textField1.getText());
                    ResultSet rs = pst1.executeQuery();
                    while (rs.next()) {

                        Vector<String> vstring = new Vector<String>();


                        vstring.add(rs.getString("��� ����"));
                        vstring.add(rs.getString("���_�����������"));
                        vstring.add(rs.getString("���������_�����_�����������"));
                        vstring.add(rs.getString("��� �����������"));
                        vstring.add(rs.getString("����"));
                        vstring.add(rs.getString("��������� �����������"));
                        vstring.add(rs.getString("� ��������� � ����"));
                        vstring.add(rs.getString("��� ������������"));
                        vstring.add(rs.getString("��������� ����� ������������"));
                        vstring.add(rs.getString("��� �������������"));
                        vstring.add(rs.getString("���� �������� ������������"));
                        vstring.add(rs.getString("��������� ������������"));
                        vstring.add(rs.getString("� ��������� � ����"));
                        vstring.add(rs.getString("���"));
                        vstring.add(rs.getString("npr"));
                        vstring.add(rs.getString("���������_��������"));

                        vstring.add("\n\n\n\n\n\n\n");

                        data1.add(vstring);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                DefaultTableModel model = new DefaultTableModel();
                model = new DefaultTableModel(data1, finalColumnNames);
                table1.setModel(model);
            }
            }
        });
        �����������������������Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "� ������� ��������� ��� �������� ���������� � ������������."+ "\n"
                        +"��� ������ ������������ �����������, ������� ��� ��������� ����� � ���� � ������� ������ �����"+ "\n"
                        +"��� ������ ������������ � ����������� ���� ��� ����������� ������������, ������� �������� ���� ��� ��������� ����� ������������ � ���� � ������� �����"+ "\n"
                        +"��� �������� ����������� ������� ��� ��������� ����� � ���� � ������� ������ �������"+ "\n"
                        +"��� ���������� ������ ���������� ������� ������ '�������� �����������' � ������� ��������� ������"+ "\n"
                        +"��� ���������� ����� ��������� ��������� ������� ������ '�������� ��������� ���������' � ������� ��������� ������"+ "\n"
                        +"��� �������������� ����������� � ������ ������� �� ��� ������� � ������ '������� ������� ��������� ������������' � ������� ��������� ������������"+ "\n"
                        +"����� ���� ������� �� ������ '������� ������� ����������'"
                        , "������� ������������",JOptionPane.INFORMATION_MESSAGE
                        );
            }
        });
    }

    public static void open() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Main dialog = new Main();
                    dialog.setContentPane(new Main().contentPane);

                    dialog.setSize(800, 700);
                    dialog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void Search() {

    }
    private void Delete() {

    }
    public static void main(String[] args) {
        Main dialog = new Main();
        dialog.setContentPane(new Main().contentPane);
        dialog.setSize(800, 700);
        //dialog.pack();
        formula = new formuls();
        Thread myThready1 = new Thread(formula);
        myThready1.start();
        dialog.setTitle("�������");
        dialog.setVisible(true);
        // System.exit(0);
    }

}
