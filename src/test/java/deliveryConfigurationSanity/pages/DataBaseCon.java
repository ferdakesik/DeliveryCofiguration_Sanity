package deliveryConfigurationSanity.pages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseCon extends BasePage {


    private static Connection connect = null;
    private static Statement statement = null;
    private ResultSet resultSet = null;
    private static List<String> list = new ArrayList<String>();
    public static Connection dbCon = null;


    //DDK Database
    private void connectToDatabaseDDK() throws SQLException, ClassNotFoundException {
        String driverClass = "oracle.jdbc.OracleDriver";
        //String url = "jdbc:oracle:thin:@lpwracdkdqu34-scan.npd.com:1521/srv_ddk";
        String url = "jdbc:oracle:thin:@lpwracdkdqu34-scan.npd.com:1521/srv_ddk";
        String userName = "QA_User";
        String password = "npd12qa_user";
        Class.forName(driverClass);
        connect = DriverManager.getConnection(url, userName, password);
        //System.out.println("Database is connected");
    }

    public List<String> readDataBaseDDK(String SQLQuery, String ColumnLabel) {
        try {
            connectToDatabaseDDK();
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement
                    .executeQuery(SQLQuery);
            list = getResultSetData(resultSet, ColumnLabel);

            //System.out.println(list.size());
            //System.out.println(list);
            //System.out.println("Security key is: "+list.get(0));
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            close();
        }
        return list;
    }

    //Only Execute
    public void OnlyExecuteDDK(String SQLQuery) {
        try {
            connectToDatabaseDDK();
            statement = connect.createStatement();
            statement.executeQuery(SQLQuery);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    private List<String> getResultSetData(ResultSet resultSet, String ColumnLabel) throws SQLException {
        List<String> dataList = new ArrayList<String>();
        while (resultSet.next()) {
            String itemName = resultSet.getString(ColumnLabel);
            dataList.add(itemName);
        }
        return dataList;
    }

    //PDK Database
    private void connectToDatabasePDK() throws SQLException, ClassNotFoundException {
        String driverClass = "oracle.jdbc.OracleDriver";
        String url = "jdbc:oracle:thin:@lpwracdkp-scan.npd.com:1521/SRV_PDK";
        String userName = "QA_User";
        String password = "npd08qa_user";
        Class.forName(driverClass);
        connect = DriverManager.getConnection(url, userName, password);
        //System.out.println("Database is connected");
    }

    public Connection connectToDB_PDK() throws SQLException, ClassNotFoundException {
        String driverClass = "oracle.jdbc.OracleDriver";
        String url = "jdbc:oracle:thin:@lpscracdkp-scan.npd.com:1521/SRV_PDK";
        String userName = "QA_User";
        String password = "npd08qa_user";
        Class.forName(driverClass);
        dbCon = DriverManager.getConnection(url, userName, password);
        //System.out.println("Database is connected");
        return dbCon;
    }

    public List<String> readDataBasePDK(String SQLQuery, String ColumnLabel) {

        try {
            connectToDatabasePDK();
            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement
                    .executeQuery(SQLQuery);
            list = getResultSetData(resultSet, ColumnLabel);

            //System.out.println(list.size());
            //System.out.println(list);
            //System.out.println("Security key is: "+list.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }


    //Only Execute
    public void OnlyExecutePDK(String SQLQuery) {
        try {
            connectToDatabasePDK();
            statement = connect.createStatement();
            statement.executeQuery(SQLQuery);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public Connection getDBConnectionString() throws SQLException, ClassNotFoundException {
        try {
            dbCon = connectToDB_PDK();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dbCon;
    }

    public void closeConnection(Connection dbCon) throws Exception {
        if (dbCon != null) try {
            dbCon.close();
        } catch (Exception ignore) {
        }
    }

    public void RunUpdateQueryPDK(String SQLQuery) {
        try {
            connectToDatabasePDK();
            statement = connect.createStatement();
            statement.executeQuery(SQLQuery);
            statement.executeQuery("commit");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }






}
