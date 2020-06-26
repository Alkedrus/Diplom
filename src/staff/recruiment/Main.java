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
    private JButton удалитьButton;
    private JButton поискButton;
    private JTextField textField1;
    private JButton buttonCancel;
    private JButton button1;
    private JButton руководствоПользователяButton;
    static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // use specific driver for your database
    static final String DB_URL = "jdbc:sqlserver://DESKTOP-PC6BAG0\\SQLEXPRESS;databaseName=Diploma;integratedSecurity=true";
    Connection conn = null;
    Statement stmt = null;
    String query = "select * from Основное";
    static formuls formula;
    static Vector<Vector<String>> data = new Vector<Vector<String>>();

    public Main() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        Vector<String> columnNames = null;
        columnNames = new Vector<String>();


        columnNames.add("Код цеха");
        columnNames.add("Цех претендента");
        columnNames.add("Табельный номер претендента");
        columnNames.add("ФИО претендента");
        // columnNames.add("Дата рождения претендента");
        columnNames.add("Стаж");
        columnNames.add("Должность претендента");
        columnNames.add("В должности с даты");

        columnNames.add("Цех руководителя");
        columnNames.add("Табельный код руководителя");
        columnNames.add("ФИО руководителя");
        columnNames.add("Дата рождения руководителя");
        columnNames.add("Должность руководителя");
        columnNames.add("В должности с даты");
        columnNames.add("Пол");
        columnNames.add("Завод");
        columnNames.add("Вакантная должность");

        {

            try {


                conn = DriverManager.getConnection(DB_URL);
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {

                    Vector<String> vstring = new Vector<String>();


                    vstring.add(rs.getString("Код цеха"));
                    vstring.add(rs.getString("Цех_претендента"));
                    vstring.add(rs.getString("Табельный_номер_претендента"));
                    vstring.add(rs.getString("ФИО претендента"));
                    //vstring.add(rs.getString("Дата рождения претендента"));
                    vstring.add(String.valueOf(rs.getInt("Стаж")));
                    vstring.add(rs.getString("Должность претендента"));
                    vstring.add(rs.getString("В должности с даты"));
                    vstring.add(rs.getString("Цех руководителя"));
                    vstring.add(rs.getString("Табельный номер руководителя"));
                    vstring.add(rs.getString("ФИО руковуодителя"));
                    vstring.add(rs.getString("Дата рождения руководителя"));
                    vstring.add(rs.getString("Должность руководителя"));
                    vstring.add(rs.getString("в должности с даты"));
                    vstring.add(rs.getString("Пол"));
                    vstring.add(rs.getString("npr"));
                    vstring.add(rs.getString("Вакантная_дожность"));

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


        удалитьButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = "DELETE FROM Основное WHERE  Табельный_номер_претендента= ?";
                String query1 = "DELETE FROM Претендент WHERE TN =?";
                try {
                    conn = DriverManager.getConnection(DB_URL);
                    String sql1 = "DELETE from Основное WHERE Табельный_номер_претендента=?";
                    PreparedStatement pst1 = conn.prepareStatement(query);
                    pst1.setString(1, textField1.getText());
                    pst1.executeUpdate();
                    // pst1.close();
                    pst1 = conn.prepareStatement(query1);
                    pst1.setString(1, textField1.getText());
                    pst1.executeUpdate();
                    pst1.close();
                    conn.close();
                    JOptionPane.showMessageDialog(null, "Претендент удалён!");
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }


            }
        });

        Vector<String> finalColumnNames = columnNames;
        поискButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int qw=0;
                String query = "select * from Основное WHERE Цех_претендента=?";
                if((textField1.getText().matches("-?\\d+(\\.\\d+)?"))){qw=0;}
                else{qw=1;}{
                    if(qw==0){query = "select * from Основное WHERE Табельный_номер_претендента=?";}
                else{query = "select * from Основное WHERE Цех_претендента=?";}
                Vector<Vector<String>> data1 = new Vector<Vector<String>>();
                try {
                    conn = DriverManager.getConnection(DB_URL);
                    PreparedStatement pst1 = conn.prepareStatement(query);
                    pst1.setString(1, textField1.getText());
                    ResultSet rs = pst1.executeQuery();
                    while (rs.next()) {

                        Vector<String> vstring = new Vector<String>();


                        vstring.add(rs.getString("Код цеха"));
                        vstring.add(rs.getString("Цех_претендента"));
                        vstring.add(rs.getString("Табельный_номер_претендента"));
                        vstring.add(rs.getString("ФИО претендента"));
                        vstring.add(rs.getString("Стаж"));
                        vstring.add(rs.getString("Должность претендента"));
                        vstring.add(rs.getString("В должности с даты"));
                        vstring.add(rs.getString("Цех руководителя"));
                        vstring.add(rs.getString("Табельный номер руководителя"));
                        vstring.add(rs.getString("ФИО руковуодителя"));
                        vstring.add(rs.getString("Дата рождения руководителя"));
                        vstring.add(rs.getString("Должность руководителя"));
                        vstring.add(rs.getString("в должности с даты"));
                        vstring.add(rs.getString("Пол"));
                        vstring.add(rs.getString("npr"));
                        vstring.add(rs.getString("Вакантная_дожность"));

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
        руководствоПользователяButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "В таблице приведена вся основная информация о претендентах."+ "\n"
                        +"Для поиска определённого претендента, введите его табельный номер в поле и нажмите кнопку Поиск"+ "\n"
                        +"Для поиска претендентов в определённом цеху или определённом руководителе, введите нахвание цеха или табельный номер руководителя в поле и нажмите поиск"+ "\n"
                        +"Для удаления претендента введите его табельный номер в поле и нажмите кнопку удалить"+ "\n"
                        +"Для добавления нового пртендента нажмите кнопку 'Добавить претендента' и введите требуемые данные"+ "\n"
                        +"Для добавления новой вакантной должности нажмите кнопку 'Добавить вакантную должность' и введите требуемые данные"+ "\n"
                        +"Для анализирования претенденов и выбора лучшего из них нажмите н кнопку 'Создать графики сравнения претендентов' и введите требуемые коэффициенты"+ "\n"
                        +"После чего нажмите на кнопку 'Выбрать лучшего кандиадата'"
                        , "Справка пользователя",JOptionPane.INFORMATION_MESSAGE
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
        dialog.setTitle("Главная");
        dialog.setVisible(true);
        // System.exit(0);
    }

}
