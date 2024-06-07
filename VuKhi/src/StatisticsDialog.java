import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StatisticsDialog extends JDialog {
    private JTextArea statisticsTextArea;

    public StatisticsDialog(JFrame parent, ArrayList<Weapon> weapons) {
        super(parent, "Thống kê", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        statisticsTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(statisticsTextArea);
        add(scrollPane);

        calculateStatistics(weapons);
    }

    private void calculateStatistics(ArrayList<Weapon> weapons) {
        // Tính toán thông tin thống kê từ danh sách vũ khí
        // Ví dụ: Tổng số loại vũ khí, tổng số lượng, giá trị tổng cộng, ngày nhập xuất, v.v.
        int totalNumberOfWeapons = weapons.size();
        int totalQuantity = 0;
        double totalValue = 0;
        // Tính toán các thông tin khác

        // Hiển thị thông tin thống kê
        String statisticsText = "Thống kê vũ khí:\n\n";
        statisticsText += "Tổng số loại vũ khí: " + totalNumberOfWeapons + "\n";
        statisticsText += "Tổng số lượng: " + totalQuantity + "\n";
        statisticsText += "Tổng giá trị: " + totalValue + "\n";
        // Thêm các thông tin khác vào đây

        statisticsTextArea.setText(statisticsText);
    }
}
