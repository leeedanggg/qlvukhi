import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainUI extends JFrame {
    private JTable weaponTable;
    private JTable statisticsTable;
    private ArrayList<Weapon> weapons = new ArrayList<>();
    private DefaultTableModel weaponTableModel;
    private DefaultTableModel statisticsTableModel;
    private int serialNumber = 1;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
        displayAllWeapons();
    }

    public void removeWeapon(int index) {
        if (index >= 0 && index < weapons.size()) {
            weapons.remove(index);
            displayAllWeapons();
        }
    }

    public void searchWeapon(String name) {
        weaponTableModel.setRowCount(0);
        for (Weapon weapon : weapons) {
            if (weapon.getName().equalsIgnoreCase(name)) {
                weaponTableModel.addRow(new Object[]{
                        weapon.getName(),
                        weapon.getType(),
                        weapon.getNumber(),
                        weapon.getPrice(),
                        weapon.getDateIn(),
                        weapon.getDateOut()
                });
            }
        }
    }

    private void displayAllWeapons() {
        weaponTableModel.setRowCount(0);
        for (Weapon weapon : weapons) {
            try {
                Date dateIn = null;
                Date dateOut = null;
                if (!weapon.getDateIn().isEmpty()) {
                    dateIn = dateFormat.parse(weapon.getDateIn());
                }
                if (!weapon.getDateOut().isEmpty()) {
                    dateOut = dateFormat.parse(weapon.getDateOut());
                }
                weaponTableModel.addRow(new Object[]{
                        weapon.getName(),
                        weapon.getType(),
                        weapon.getNumber(),
                        weapon.getPrice(),
                        dateIn != null ? dateFormat.format(dateIn) : "",
                        dateOut != null ? dateFormat.format(dateOut) : ""
                });
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public MainUI() {
        setTitle("Quản lý vũ khí");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(8, 1));
        JButton btnAddWeapon = new JButton("Thêm vũ khí");
        JButton btnEditWeapon = new JButton("Chỉnh sửa vũ khí");
        JButton btnRemoveWeapon = new JButton("Xóa vũ khí");
        JButton btnSearchWeapon = new JButton("Tìm kiếm vũ khí");
        JButton btnStatistics = new JButton("Thống kê");
        JButton btnManageWeapon = new JButton("Xuất/Thu hồi vũ khí");
        JButton btnDisplayAllWeapons = new JButton("Hiển thị toàn bộ vũ khí");

        panel.add(btnAddWeapon);
        panel.add(btnEditWeapon);
        panel.add(btnRemoveWeapon);
        panel.add(btnSearchWeapon);
        panel.add(btnStatistics);
        panel.add(btnManageWeapon);
        panel.add(btnDisplayAllWeapons);

        btnAddWeapon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddWeaponDialog(MainUI.this).setVisible(true);
            }
        });

        btnEditWeapon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = getSelectedRow();
                if (selectedRow != -1) {
                    Weapon weapon = weapons.get(selectedRow);
                    new EditWeaponDialog(MainUI.this, weapon).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(MainUI.this, "Vui lòng chọn vũ khí để chỉnh sửa.");
                }
            }
        });

        btnRemoveWeapon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = getSelectedRow();
                if (selectedRow != -1) {
                    removeWeapon(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(MainUI.this, "Vui lòng chọn vũ khí để xóa.");
                }
            }
        });

        btnSearchWeapon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(MainUI.this, "Nhập tên vũ khí để tìm kiếm:");
                if (name != null && !name.isEmpty()) {
                    searchWeapon(name);
                } else {
                    JOptionPane.showMessageDialog(MainUI.this, "Vui lòng nhập tên vũ khí.");
                }
            }
        });

        btnManageWeapon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(MainUI.this, "Nhập tên vũ khí:");
                String quantityStr = JOptionPane.showInputDialog(MainUI.this, "Nhập số lượng:");
                String action = JOptionPane.showInputDialog(MainUI.this, "Nhập hành động (export/import):");
                if (name != null && !name.isEmpty() && quantityStr != null && !quantityStr.isEmpty() && action != null && !action.isEmpty()) {
                    try {
                        int quantity = Integer.parseInt(quantityStr);
                        if (action.equalsIgnoreCase("export")) {
                            manageWeapon(name, quantity, true);
                        } else if (action.equalsIgnoreCase("import")) {
                            manageWeapon(name, quantity, false);
                        } else {
                            JOptionPane.showMessageDialog(MainUI.this, "Hành động không hợp lệ.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(MainUI.this, "Số lượng phải là số nguyên.");
                    }
                } else {
                    JOptionPane.showMessageDialog(MainUI.this, "Vui lòng nhập đầy đủ thông tin.");
                }
            }
        });

        btnDisplayAllWeapons.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAllWeapons();
            }
        });

        weaponTableModel = new DefaultTableModel(new String[]{"Tên", "Loại", "Số lượng", "Giá", "Ngày nhập", "Ngày xuất"}, 0);
        weaponTable = new JTable(weaponTableModel);
        JScrollPane scrollPane = new JScrollPane(weaponTable);

        statisticsTableModel = new DefaultTableModel(new String[]{"Tên vũ khí", "Số lượng xuất", "Số lượng thu hồi"}, 0);
        statisticsTable = new JTable(statisticsTableModel);
        JScrollPane statisticsScrollPane = new JScrollPane(statisticsTable);

        TableColumn column = weaponTable.getColumnModel().getColumn(0);
        column.setPreferredWidth(50);

        add(panel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        add(statisticsScrollPane, BorderLayout.EAST);
    }

    public void updateWeapon(int index, Weapon weapon) {
        weapons.set(index, weapon);
        try {
            Date dateIn = null;
            Date dateOut = null;
            if (!weapon.getDateIn().isEmpty()) {
                dateIn = dateFormat.parse(weapon.getDateIn());
            }
            if (!weapon.getDateOut().isEmpty()) {
                dateOut = dateFormat.parse(weapon.getDateOut());
            }
            weaponTableModel.setValueAt(weapon.getName(), index, 0);
            weaponTableModel.setValueAt(weapon.getType(), index, 1);
            weaponTableModel.setValueAt(weapon.getNumber(), index, 2);
            weaponTableModel.setValueAt(weapon.getPrice(), index, 3);
            weaponTableModel.setValueAt(dateIn != null ? dateFormat.format(dateIn) : "", index, 4);
            weaponTableModel.setValueAt(dateOut != null ? dateFormat.format(dateOut) : "", index, 5);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void addExportRecord(String name, int quantity, String exportDate) {
        for (int i = 0; i < statisticsTableModel.getRowCount(); i++) {
            if (statisticsTableModel.getValueAt(i, 0).equals(name)) {
                int currentExport = (int) statisticsTableModel.getValueAt(i, 1);
                statisticsTableModel.setValueAt(currentExport + quantity, i, 1);
                return;
            }
        }
        statisticsTableModel.addRow(new Object[]{name, quantity, 0});
    }

    public void addReturnRecord(String name, int quantity, String returnDate) {
        for (int i = 0; i < statisticsTableModel.getRowCount(); i++) {
            if (statisticsTableModel.getValueAt(i, 0).equals(name)) {
                int currentReturn = (int) statisticsTableModel.getValueAt(i, 2);
                statisticsTableModel.setValueAt(currentReturn + quantity, i, 2);
                return;
            }
        }
        statisticsTableModel.addRow(new Object[]{name, 0, quantity});
    }

    public int getSelectedRow() {
        return weaponTable.getSelectedRow();
    }

    public void manageWeapon(String name, int quantity, boolean isExport) {
        for (Weapon weapon : weapons) {
            if (weapon.getName().equalsIgnoreCase(name)) {
                if (isExport) {
                    weapon.setNumber(weapon.getNumber() - quantity);
                    addExportRecord(name, quantity, new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
                } else {
                    weapon.setNumber(weapon.getNumber() + quantity);
                    addReturnRecord(name, quantity, new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
                }
                displayAllWeapons();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Vũ khí không tìm thấy.");
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainUI().setVisible(true);
            }
        });
    }
}
