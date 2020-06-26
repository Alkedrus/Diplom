package staff.recruiment;




import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;
import java.util.List;

public class Graphs extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox1;
    private JTextField a10TextField3;
    private JTextField a10TextField1;
    private JTextField a10TextField13;
    private JTextField a10TextField6;
    private JTextField a10TextField4;
    private JTextField a10TextField8;
    private JTextField a10TextField11;
    private JTextField a10TextField5;
    private JTextField a10TextField12;
    private JTextField a10TextField;
    private JTextField a10TextField14;
    private JTextField a10TextField2;
    private JTextField a10TextField7;
    private JTextField a10TextField15;
    private JTextField a10TextField10;
    private JTextField a10TextField9;
    private JTextField a10TextField16;
    private JTextField textField1;
    static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // use specific driver for your database
    static final String DB_URL = "jdbc:sqlserver://DESKTOP-PC6BAG0\\SQLEXPRESS;databaseName=Diploma;integratedSecurity=true";
    static Osnovnoe osnovnoe;
    static Best_challenger best_challenger;
    static formuls formula;
    final int rows = 3;
    Connection conn = null;


    public Graphs() {


        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Analyse();
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

    private void Analyse() {
        try {
            double[][] nums = new double[100][100];
            conn = DriverManager.getConnection(DB_URL);
            String vacant_position = (String) comboBox1.getSelectedItem();
            String sql = "select * from Оценки WHERE Вакантная_должность=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            int numver=0;
            pst.setString(1, vacant_position);
            ResultSet rs = pst.executeQuery();

            FileInputStream fs = (new FileInputStream("./template/graf.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(fs);
            XSSFSheet sheet = workbook.getSheetAt(1);
            Cell cell = null;
            int i = 17;
            int j = 92;
            int i1 = 0;
            int j1 = 0;
            int q = 0;

            while (rs.next()) {numver++;
                if (rs.getInt(3) == 1) {
                    cell = sheet.getRow(j).getCell(16);
                    cell.setCellValue("Руководитель");
                } else if (rs.getInt(3) == 2) {
                    cell = sheet.getRow(j).getCell(16);
                    cell.setCellValue("Эксперт");
                } else if (rs.getInt(3) == 3) {
                    cell = sheet.getRow(j).getCell(16);
                    cell.setCellValue("Коллега №1");
                } else if (rs.getInt(3) == 4) {
                    cell = sheet.getRow(j).getCell(16);
                    cell.setCellValue("Коллега №2");
                }

                cell = sheet.getRow(j).getCell(11);
                cell.setCellValue(rs.getString(4));
                cell = sheet.getRow(j).getCell(1);
                cell.setCellValue(rs.getString(21));
                cell = sheet.getRow(j).getCell(i++);
                cell.setCellValue(rs.getInt(5));
                cell = sheet.getRow(j).getCell(i++);
                cell.setCellValue(rs.getInt(6));
                cell = sheet.getRow(j).getCell(i++);
                cell.setCellValue(rs.getInt(7));
                cell = sheet.getRow(j).getCell(i++);
                cell.setCellValue(rs.getInt(8));
                cell = sheet.getRow(j).getCell(i++);
                cell.setCellValue(rs.getInt(9));
                cell = sheet.getRow(j).getCell(i++);
                cell.setCellValue(rs.getInt(10));
                cell = sheet.getRow(j).getCell(i++);
                cell.setCellValue(rs.getInt(11));
                cell = sheet.getRow(j).getCell(i++);
                cell.setCellValue(rs.getInt(12));
                cell = sheet.getRow(j).getCell(i++);
                cell.setCellValue(rs.getInt(13));
                cell = sheet.getRow(j).getCell(i++);
                cell.setCellValue(rs.getInt(14));
                cell = sheet.getRow(j).getCell(i++);
                cell.setCellValue(rs.getInt(15));
                cell = sheet.getRow(j).getCell(i++);
                cell.setCellValue(rs.getInt(16));
                cell = sheet.getRow(j).getCell(i++);
                cell.setCellValue(rs.getInt(17));
                cell = sheet.getRow(j).getCell(i++);
                cell.setCellValue(rs.getInt(18));
                cell = sheet.getRow(j).getCell(i++);
                cell.setCellValue(rs.getInt(19));
                cell = sheet.getRow(j).getCell(i++);
                cell.setCellValue(rs.getInt(20));
                j++;
                i = 17;
                if(numver==40){break;}
            }
          /* for (i = 93; i < 130; i++) {
                for (j = 18; j < 30; j++) {
                    cell = sheet.getRow(j).getCell(i);
                    nums[i][j] = cell.getNumericCellValue();
                }
            }
            for (i = 132; i < 143; i++) {
                for (j = 17; j < 33; j++) {
                    cell = sheet.getRow(j).getCell(i);
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(nums[i1][j1] * 0.4 + nums[i1++][j1] * 0.4 + nums[i1++][j1] * 0.1 + nums[i1++][j1]);
                }
            }
*/
            fs.close();
            FileOutputStream outFile = new FileOutputStream(new File("./template/graf.xlsx"));
            workbook.write(outFile);
            outFile.close();
            osnovnoe = new Osnovnoe();
            Thread myThready = new Thread(osnovnoe);
            myThready.start();

            myThready.join();
            best_challenger = new Best_challenger();
            Thread myThready1 = new Thread(best_challenger);
            myThready1.start();


        } catch (IOException | SQLException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
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
    class Osnovnoe
            implements Runnable {
        public void run() {
            try {
                int numver=0;
                String[] tab_nom1 = new String[100];
                conn = DriverManager.getConnection(DB_URL);
                String vacant_position = (String) comboBox1.getSelectedItem();
                String sql = "select * from Основное WHERE Вакантная_дожность=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, vacant_position);
                ResultSet rs = pst.executeQuery();
                FileInputStream fs = (new FileInputStream("./template/graf.xlsx"));
                XSSFWorkbook workbook = new XSSFWorkbook(fs);
                XSSFSheet sheet = workbook.getSheetAt(1);
                Cell cell = null;
                int j = 92;
                int i = 1;
                String sex = null;

                while (rs.next()) {
                    numver++;
                    tab_nom1[numver]=rs.getString(3);
                    while (i < 5) {


                        cell = sheet.getRow(j).getCell(4)   ;
                        cell.setCellValue(rs.getString(18));
                        cell = sheet.getRow(j).getCell(5);
                        cell.setCellValue(rs.getString(1));
                        /*cell = sheet.getRow(j).getCell(2);
                        cell.setCellValue(rs.getString(6));*/
                        cell = sheet.getRow(j).getCell(6);
                        cell.setCellValue(rs.getString(2));
                        cell = sheet.getRow(j).getCell(7);
                        if (rs.getInt(17) == 2) {
                            sex = "Ж";
                        } else {
                            sex = "М";
                        }
                        cell.setCellValue(sex);
                        cell = sheet.getRow(j).getCell(2);
                        cell.setCellValue(rs.getString(3));

                        cell = sheet.getRow(j).getCell(8);
                        cell.setCellValue(rs.getString(7));
                        cell = sheet.getRow(j).getCell(9);
                        cell.setCellValue(rs.getString(20));
                        cell = sheet.getRow(j).getCell(10);
                        cell.setCellValue(rs.getString(6));
                        cell = sheet.getRow(j).getCell(12);
                        cell.setCellValue(rs.getString(4));
                        cell = sheet.getRow(j).getCell(13);
                        cell.setCellValue(rs.getString(10));
                        cell = sheet.getRow(j).getCell(14);
                        cell.setCellValue(rs.getString(11));
                        cell = sheet.getRow(j).getCell(15);
                        cell.setCellValue(rs.getString(17));

                        cell = sheet.getRow(j).getCell(15);
                        cell.setCellValue(rs.getString(14));
                        i++;
                        j++;
                    }
                    i = 1;
                if(numver==41){break;}
                }
                String sql1 = "select * from Опыт WHERE Табельный_номер=?";
                PreparedStatement pst1 = conn.prepareStatement(sql1);
                int q=0;
                int qq=0;
                j=94;
                while(q<5){
                pst.setString(1, tab_nom1[q]);
                q++;
                ResultSet rs1 = pst.executeQuery();
                if (rs1.next()) {
                    while(qq<4) {
                        cell = sheet.getRow(j++).getCell(3);
                        cell.setCellValue(rs1.getString(7));
                    qq++;}
                }
                }
                fs.close();
                FileOutputStream outFile = new FileOutputStream(new File("./template/graf.xlsx"));
                workbook.write(outFile);
                outFile.close();

            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        Graphs dialog = new Graphs();
        dialog.setSize(800, 700);
        dialog.comboBox1.removeAllItems();
        dialog.setTitle("Выбор лучшего претендента");
        for (String employee : dialog.listeEmployee()) {
            dialog.comboBox1.addItem(employee);
        }

        dialog.setVisible(true);
    }



    class Best_challenger
            implements Runnable {
        public void run() {
            try {
                double[][] nums = new double[40][16];
                double[][] nums1 = new double[10][17];
                double[] nums2 = new double[10];
                String[] tab_nom = new String[100];
                conn = DriverManager.getConnection(DB_URL);
                String vacant_position = (String) comboBox1.getSelectedItem();


                FileInputStream fs = (new FileInputStream("./template/graf.xlsx"));
                XSSFWorkbook workbook = new XSSFWorkbook(fs);
                Cell cell = null;
                int j = 132;
                int ji = 92;
                int i = 0;
                double num = 0;
                num = 0;
                int i2 = 0;
                int j2 = 0;
                int q = 0;
                int w = 1;
                String sql1 = "select * from Оценки WHERE Вакантная_должность=?";
                PreparedStatement pst1 = conn.prepareStatement(sql1);
                int numver=0;
                pst1.setString(1, vacant_position);
                ResultSet rs1 = pst1.executeQuery();
                while (rs1.next()) {
                    numver++;
                    if (q == 4) {
                        tab_nom[w] = rs1.getString(2);
                        q = 0;
                        w++;
                    }
                    nums[i2][j2++] = rs1.getInt(5);
                    nums[i2][j2++] = rs1.getInt(6);
                    nums[i2][j2++] = rs1.getInt(7);
                    nums[i2][j2++] = rs1.getInt(8);
                    nums[i2][j2++] = rs1.getInt(9);
                    nums[i2][j2++] = rs1.getInt(10);
                    nums[i2][j2++] = rs1.getInt(11);
                    nums[i2][j2++] = rs1.getInt(12);
                    nums[i2][j2++] = rs1.getInt(13);
                    nums[i2][j2++] = rs1.getInt(14);
                    nums[i2][j2++] = rs1.getInt(15);
                    nums[i2][j2++] = rs1.getInt(16);
                    nums[i2][j2++] = rs1.getInt(17);
                    nums[i2][j2++] = rs1.getInt(18);
                    nums[i2][j2++] = rs1.getInt(19);
                    nums[i2][j2++] = rs1.getInt(20);

                    i2++;
                    j2 = 0;
                    q++;
                    if(numver==41){break;}
                }
                pst1.close();
                i2 = 0;
                j2 = 0;
                /*for (int qw = 0; qw < 40; qw++) {
                    for (int qwe = 0; qwe < 16; qwe++) {
                        System.out.print(nums[qw][qwe] + "   ");
                    }
                    System.out.println("");
                }*/
                while (true) {
                    nums1[0][j2] = nums[i2++][j2] * 0.4 + nums[i2++][j2] * 0.4 + nums[i2++][j2] * 0.1 + nums[i2++][j2] * 0.1;
                    nums1[1][j2] = nums[i2++][j2] * 0.4 + nums[i2++][j2] * 0.4 + nums[i2++][j2] * 0.1 + nums[i2++][j2] * 0.1;
                    nums1[2][j2] = nums[i2++][j2] * 0.4 + nums[i2++][j2] * 0.4 + nums[i2++][j2] * 0.1 + nums[i2++][j2] * 0.1;
                    nums1[3][j2] = nums[i2++][j2] * 0.4 + nums[i2++][j2] * 0.4 + nums[i2++][j2] * 0.1 + nums[i2++][j2] * 0.1;
                    nums1[4][j2] = nums[i2++][j2] * 0.4 + nums[i2++][j2] * 0.4 + nums[i2++][j2] * 0.1 + nums[i2++][j2] * 0.1;
                    nums1[5][j2] = nums[i2++][j2] * 0.4 + nums[i2++][j2] * 0.4 + nums[i2++][j2] * 0.1 + nums[i2++][j2] * 0.1;
                    nums1[6][j2] = nums[i2++][j2] * 0.4 + nums[i2++][j2] * 0.4 + nums[i2++][j2] * 0.1 + nums[i2++][j2] * 0.1;
                    nums1[7][j2] = nums[i2++][j2] * 0.4 + nums[i2++][j2] * 0.4 + nums[i2++][j2] * 0.1 + nums[i2++][j2] * 0.1;
                    nums1[8][j2] = nums[i2++][j2] * 0.4 + nums[i2++][j2] * 0.4 + nums[i2++][j2] * 0.1 + nums[i2++][j2] * 0.1;
                    nums1[9][j2] = nums[i2++][j2] * 0.4 + nums[i2++][j2] * 0.4 + nums[i2++][j2] * 0.1 + nums[i2++][j2] * 0.1;
                    j2++;
                    i2 = 0;

                    if (j2 == 16) {
                        break;
                    }
                }
                /*for (int qw = 0; qw < 10; qw++) {
                    for (int qwe = 0; qwe < 17; qwe++) {
                        System.out.print(nums1[qw][qwe] + "   ");
                    }
                    System.out.println("");
                }*/
                double sum = 0;
                String sql11 = "select Образование from Опыт WHERE Табельный_номер=?";
                PreparedStatement pst11 = conn.prepareStatement(sql11);
                int obr=0;
                for (int qw = 0; qw < 10; qw++) {
                    pst11.setString(1, tab_nom[qw]);
                    ResultSet rs11 = pst11.executeQuery();
                    sum += nums1[qw][0] * Double.parseDouble(a10TextField3.getText());
                    sum += nums1[qw][1] * Double.parseDouble(a10TextField6.getText());
                    sum += nums1[qw][2] * Double.parseDouble(a10TextField4.getText());
                    sum += nums1[qw][3] * Double.parseDouble(a10TextField8.getText());
                    sum += nums1[qw][4] * Double.parseDouble(a10TextField11.getText());
                    sum += nums1[qw][5] * Double.parseDouble(a10TextField13.getText());
                    sum += nums1[qw][6] * Double.parseDouble(a10TextField1.getText());
                    sum += nums1[qw][7] * Double.parseDouble(a10TextField5.getText());
                    sum += nums1[qw][8] * Double.parseDouble(a10TextField9.getText());
                    sum += nums1[qw][9] * Double.parseDouble(a10TextField12.getText());
                    sum += nums1[qw][10] * Double.parseDouble(a10TextField.getText());
                    sum += nums1[qw][11] * Double.parseDouble(a10TextField14.getText());
                    sum += nums1[qw][12] * Double.parseDouble(a10TextField2.getText());
                    sum += nums1[qw][13] * Double.parseDouble(a10TextField7.getText());
                    sum += nums1[qw][14] * Double.parseDouble(a10TextField15.getText());
                    sum += nums1[qw][15] * Double.parseDouble(a10TextField10.getText());
                    sum+=10*Double.parseDouble(a10TextField16.getText());
/*                    if(rs11.getString(1).equalsIgnoreCase("Среднее")){obr=10;}
                    else if(rs11.getString(2).equalsIgnoreCase("Среднее специальное")){obr=20;}
                    else if(rs11.getString(2).equalsIgnoreCase("Проффессионально-техническое")){obr=30;}
                    else if(rs11.getString(2).equalsIgnoreCase("Высшее проффессиональное")){obr=40;}
                    sum+=obr*Double.parseDouble(textField1.getText());//высшее образование*/
//ПОМЕНЯТЬ БЛЯТЬ И БЫСТРО!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    nums2[qw] = sum;
                    sum = 0;
                }
                pst11.close();

                num = 0;
                for (i = 0; i < 10; i++) {
                    if (num < nums2[i]) {
                        num = nums2[i];
                        i2 = i;
                        System.out.println(num);
                    }
                }

                String best = tab_nom[i2];
                System.out.println(best);
                String sql = "select * from Оценки WHERE Табельный_код_кандидата=? AND Вакантная_должность=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, best);
                pst.setString(2, vacant_position);
                ResultSet rs = pst.executeQuery();

               /* String sql3 = "select * from Оценки WHERE Табельный_код_кандидата=? AND Вакантная_должность=?";
                PreparedStatement pst3 = conn.prepareStatement(sql3);
                pst3.setString(1, best);
                pst3.setString(2, vacant_position);
                ResultSet rs3 = pst.executeQuery();*/

                XSSFSheet sheet = workbook.getSheetAt(0);
                j = 32;
                i = 17;
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDate localDate = LocalDate.now();
                cell = sheet.getRow(3).getCell(11);
                cell.setCellValue(dtf.format(localDate));
                //JOptionPane.showMessageDialog(null, best);
                while (rs.next()) {
                    cell = sheet.getRow(j).getCell(1);
                    cell.setCellValue(rs.getString(21));
                    cell = sheet.getRow(j).getCell(2);
                    cell.setCellValue(rs.getString(2));
                    cell = sheet.getRow(j).getCell(3);
                    cell.setCellValue(rs.getString(1));
                    cell = sheet.getRow(j).getCell(15);
                    cell.setCellValue(rs.getString(4));

                    if (rs.getInt(3) == 1) {
                        cell = sheet.getRow(j).getCell(16);
                        cell.setCellValue("Руководитель");
                    } else if (rs.getInt(3) == 2) {
                        cell = sheet.getRow(j).getCell(16);
                        cell.setCellValue("Эксперт");
                    } else if (rs.getInt(3) == 3) {
                        cell = sheet.getRow(j).getCell(16);
                        cell.setCellValue("Коллега №1");
                    } else if (rs.getInt(3) == 4) {
                        cell = sheet.getRow(j).getCell(16);
                        cell.setCellValue("Коллега №2");
                    }
                    cell = sheet.getRow(j).getCell(i++);
                    cell.setCellValue(rs.getInt(5));
                    cell = sheet.getRow(j).getCell(i++);
                    cell.setCellValue(rs.getInt(6));
                    cell = sheet.getRow(j).getCell(i++);
                    cell.setCellValue(rs.getInt(7));
                    cell = sheet.getRow(j).getCell(i++);
                    cell.setCellValue(rs.getInt(8));
                    cell = sheet.getRow(j).getCell(i++);
                    cell.setCellValue(rs.getInt(9));
                    cell = sheet.getRow(j).getCell(i++);
                    cell.setCellValue(rs.getInt(10));
                    cell = sheet.getRow(j).getCell(i++);
                    cell.setCellValue(rs.getInt(11));
                    cell = sheet.getRow(j).getCell(i++);
                    cell.setCellValue(rs.getInt(12));
                    cell = sheet.getRow(j).getCell(i++);
                    cell.setCellValue(rs.getInt(13));
                    cell = sheet.getRow(j).getCell(i++);
                    cell.setCellValue(rs.getInt(14));
                    cell = sheet.getRow(j).getCell(i++);
                    cell.setCellValue(rs.getInt(15));
                    cell = sheet.getRow(j).getCell(i++);
                    cell.setCellValue(rs.getInt(16));
                    cell = sheet.getRow(j).getCell(i++);
                    cell.setCellValue(rs.getInt(17));
                    cell = sheet.getRow(j).getCell(i++);
                    cell.setCellValue(rs.getInt(18));
                    cell = sheet.getRow(j).getCell(i++);
                    cell.setCellValue(rs.getInt(19));
                    cell = sheet.getRow(j).getCell(i++);
                    cell.setCellValue(rs.getInt(20));
                    j++;
                    i = 17;
                }
                pst.close();
                String sql2 = "select * from Основное WHERE Табельный_номер_претендента=? AND Вакантная_дожность=?";
                PreparedStatement pst2 = conn.prepareStatement(sql2);
                pst2.setString(1, best);
                pst2.setString(2, vacant_position);
                ResultSet rs2 = pst2.executeQuery();
                j = 32;
                i = 17;
                String sex = null;
                String fio = null;
                int r = 0;
                while (rs2.next()) {
                    while (r < 4) {
                        cell = sheet.getRow(j).getCell(5);
                        cell.setCellValue(rs2.getInt(1));
                        cell = sheet.getRow(j).getCell(7);
                        cell.setCellValue(rs2.getString(5));
                        cell = sheet.getRow(j).getCell(8);
                        cell.setCellValue(rs2.getString(8));
                        cell = sheet.getRow(j).getCell(9);
                        cell.setCellValue(rs2.getString(7));
                        cell = sheet.getRow(j).getCell(10);
                        cell.setCellValue(rs2.getString(18));
                        cell = sheet.getRow(j).getCell(11);
                        cell.setCellValue(rs2.getString(2));
                        cell = sheet.getRow(j).getCell(6);
                        if (rs2.getInt(17) == 2) {
                            sex = "Ж";
                        } else {
                            sex = "М";
                        }
                        cell.setCellValue(sex);
                        cell = sheet.getRow(j).getCell(12);
                        cell.setCellValue(rs2.getString(4));
                        cell = sheet.getRow(j).getCell(13);
                        cell.setCellValue(rs2.getInt(6));
                        cell = sheet.getRow(j).getCell(14);
                        cell.setCellValue(rs2.getString(20));
                        cell = sheet.getRow(3).getCell(4);
                        cell.setCellValue(rs2.getString(19));
                        fio = rs2.getString(4);
                        cell = sheet.getRow(4).getCell(10);
                        cell.setCellValue(fio);
                        j++;
                        i = 17;
                        r++;
                    }
                }
                pst2.close();
                fs.close();
                FileOutputStream outFile = new FileOutputStream(new File("./template/graf.xlsx"));
                workbook.write(outFile);
                outFile.close();
                conn.close();
                JOptionPane.showMessageDialog(null,
                        "<html><h2>Лучшим претендентом на должность </h2>" + vacant_position +
                                "<html><h2>явлется </h2>" + fio + "<html><h2>С итоговым баллом </h2>" + num);
                Desktop desktop = null;
                if (Desktop.isDesktopSupported()) {
                    desktop = Desktop.getDesktop();
                }
                try {
                    desktop.open(new File("./template/graf.xlsx"));
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
               /* File dirFrom = new File("./template/graf.xlsx");
                File dirTo = new File("./src/graf.xlsx");

                try {
                    copyFile(dirFrom, dirTo);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }*/

            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
