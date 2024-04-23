import java.util.ArrayList;
import java.util.List;

public class Finder {

    public int findColumnIndex(String[][] data, String columnName) {
        for (int i = 0; i < data[0].length; i++) {
            if (data[0][i].equalsIgnoreCase(columnName)) {

                return i;
            }
        }
        System.out.println("Error: Column '" + columnName + "' not found");
        return -1; // Column not found
    }


    public String[] getColumnValues(String[][] data, int columnIndex) {
        String[] columnValues = new String[38];
        for (int i = 0; i < data.length; i++) {
            // Start from row 1 (skip header)
            if (columnIndex < data[i].length) {
                columnValues[i] = data[i][columnIndex];
            }
        }
        return columnValues;
    }

    public boolean findUserInputInTheNewColumn(String[] info, String[] columns, int inputType) {
        for (int i = 1; i < columns.length; i++) {
            if (info[inputType].equals(columns[i])) {
                return true;
            }
        }
        return false;

    }

    public String[] findDataInColumn(String dataToFind, String[] columnData) {
        String[] rowIndexAndRequestedInfo = new String[2];
        for (int i = 1; i < columnData.length; i++) {
            // Start from row 1 (skip header)
            if (dataToFind.equals(columnData[i])) {
                rowIndexAndRequestedInfo[0] = Integer.toString(i);
                rowIndexAndRequestedInfo[1] = columnData[i];

                return rowIndexAndRequestedInfo;
            }
        }

        return null;
    }

    public String[] rowFinder(String[][] data, String[] rowIndex0and) {
        String[] rowContents = new String[15];
        System.arraycopy(data[Integer.parseInt(rowIndex0and[0])], 0, rowContents, 0, data[Integer.parseInt(rowIndex0and[0])].length);
return rowContents;
    }
    public String[] valueFinderInRow(String infoRequested,String[] rowToCheckIn, String[][] data ){
        String[] valueForPersonFields =new String[1];
        int newIndex=0;
        valueForPersonFields[newIndex]=rowToCheckIn[findColumnIndex(data,infoRequested)];
        return valueForPersonFields;
    }

}
