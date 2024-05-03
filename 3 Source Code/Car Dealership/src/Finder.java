import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents a finder that finds data in a 2D array.
 */
public class Finder {

    /**
     * Finds the index of a column in a 2D array based on the column name.
     *
     * @param data the 2D array containing the data
     * @param columnName the name of the column to find
     * @return the index of the column, or -1 if the column is not found
     */
    public int findColumnIndex(String[][] data, String columnName) {
        for (int i = 0; i < data[0].length; i++) {
            if (data[0][i].equalsIgnoreCase(columnName)) {

                return i;
            }
        }
        System.out.println("Error: Column '" + columnName + "' not found");
        return -1; // Column not found
    }

    /**
     * Gets the values of a column in a 2D array based on the column index.
     *
     * @param data the 2D array containing the data
     * @param columnIndex the index of the column to get the values from
     * @return a String array containing the values of the column
     */
    public String[] getColumnValues(String[][] data, int columnIndex) {
        String[] columnValues = new String[data.length];
        for (int i = 0; i < data.length; i++) {
            // Start from row 1 (skip header)
            if (columnIndex < data[i].length) {

                columnValues[i] = data[i][columnIndex];
            }
        }
        return columnValues;
    }

    /**
     * Checks if a user's input is found in a new column.
     *
     * @param info the user's input
     * @param columns the new column
     * @param inputType the type of the user's input
     * @return true if the user's input is found in the new column, false otherwise
     */
    public boolean findUserInputInTheNewColumn(String[] info, String[] columns, int inputType) {

        //System.out.println("In findUserInputInTheNewColumn "+ info[inputType]);
        //System.out.println("In findUserInputInTheNewColumn index: "+ inputType);

        for (int i = 1; i < columns.length; i++) {
            if (info[inputType].equals(columns[i])) {
                return true;
            }
        }
        return false;

    }

    /**
     * Finds data in a column and returns the row index and the data.
     *
     * @param dataToFind the data to find
     * @param columnData the column to search in
     * @return a String array containing the row index and the data
     */
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

    /**
     * Finds a row in a 2D array based on the row index and returns the contents of the row.
     *
     * @param data the 2D array containing the data
     * @param rowIndex0and a String array containing the row index
     * @return a String array containing the contents of the row
     */
    public String[] rowFinder(String[][] data, String[] rowIndex0and) {
        String[] rowContents = new String[15];
        System.arraycopy(data[Integer.parseInt(rowIndex0and[0])], 0, rowContents, 0, data[Integer.parseInt(rowIndex0and[0])].length);
return rowContents;
    }

    /**
     * Finds a value in a row based on the requested information and returns the value.
     *
     * @param infoRequested the requested information
     * @param rowToCheckIn the row to check in
     * @param data the 2D array containing the data
     * @return a String array containing the value
     */
    public String[] valueFinderInRow(String infoRequested,String[] rowToCheckIn, String[][] data ){
        String[] valueForPersonFields =new String[1];
        int newIndex=0;
        valueForPersonFields[newIndex]=rowToCheckIn[findColumnIndex(data,infoRequested)];
        return valueForPersonFields;
    }

}
