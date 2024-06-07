import javax.swing.table.DefaultTableModel;
public class CustomTableModel extends DefaultTableModel {
    public CustomTableModel(String[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
