package sample;

import java.sql.*;
import java.util.List;

public  class DB<T> {

    private static final String DB_NAME="staff_directory.db";
    private static final String CONNECTION_STRING="jdbc:sqlite:"+DB_NAME;
    private static final String TABLE_NAME="staffs";
    private static final String COL_ID="id";
    private static final String COL_FIRST_NAME="firstName";
    private static final String COL_LAST_NAME="lastName";
    private static final String COL_DATE_OF_BIRTH="dateOfBirth";
    private static final String COL_GENDER="gender";
    private static final String COL_RELIGION="religion";
    private static final String COL_POSITION="position";
    private static final String COL_EMAIL="email";
    private static final String COL_PHONE="phone";
    private static final String COL_ADDRESS="address";
    private static final String COL_DATE_OF_APPOINTMENT="dateOfAppointment";

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+
                                            COL_ID+" INTEGER PRIMARY KEY, "+
                                            COL_FIRST_NAME+" TEXT, "+
                                            COL_LAST_NAME+" TEXT, "+
                                            COL_DATE_OF_BIRTH+" TEXT, "+
                                            COL_GENDER+" TEXT, "+
                                            COL_RELIGION+" TEXT, "+
                                            COL_POSITION+" TEXT, "+
                                            COL_EMAIL+" TEXT, "+
                                            COL_PHONE+" TEXT, "+
                                            COL_ADDRESS+" TEXT, "+
                                            COL_DATE_OF_APPOINTMENT+" TEXT "+")";

    private static Connection connection ;
    private static Statement statement;
    private static ResultSet results;



    private void open(){
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
            statement = connection.createStatement();
            statement.executeUpdate(CREATE_TABLE);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private  void close(){
        try
        {
            if(results != null)
                results.close();
            if(statement != null)
                statement.close();
            if(connection != null)
                connection.close();
        }
        catch(SQLException e)
        {
            // connection close failed.
            System.err.println(e.getMessage());
        }
    }

    public  List<T> query(String queryString){
        try {
          open();
            results=statement.executeQuery(queryString);
            ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
            return resultSetMapper.mapResultSetToObjectList(results, Staff.class);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
        finally {
            close();
        }
    }

    public  T find(String queryString){
        try {
          open();
            results=statement.executeQuery(queryString);
            ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
            return resultSetMapper.mapResultSetToObject(results, Staff.class);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
        finally {
            close();
        }
    }

    public  boolean run(String queryString){
        try {
            open();
            statement.executeUpdate(queryString);
            return true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
        finally {
            close();
        }
    }
}
