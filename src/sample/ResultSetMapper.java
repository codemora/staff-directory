package sample;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetMapper<T> {
    // This method is already implemented in package
// but as far as I know it accepts only public class attributes
    private void setProperty(Object clazz, String fieldName, Object columnValue) {
        try {
            // get all fields of the class (including public/protected/private)
            Field field = clazz.getClass().getDeclaredField(fieldName);
            // this is necessary in case the field visibility is set at private
            field.setAccessible(true);
            field.set(clazz, columnValue);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<T> mapResultSetToObjectList(ResultSet rs, Class clazz) {
        List<T> outputList = null;
        try {
            // make sure resultset is not null
            if (rs != null) {

                // check if Class clazz has the 'Entity' annotation
//                if (clazz.isAnnotationPresent(Entity.class)) {

                    // get the resultset metadata
                    ResultSetMetaData rsmd = rs.getMetaData();

                    // get all the attributes of Class clazz
                    Field[] fields = clazz.getDeclaredFields();

                    while (rs.next()) {
                        T bean = (T) clazz.newInstance();
                        for (int _iterator = 0; _iterator < rsmd.getColumnCount(); _iterator++) {
                            // get the SQL column name
                            String columnName = rsmd.getColumnName(_iterator + 1);
//                            System.out.println(columnName);

                            // get the value of the SQL column
                            Object columnValue = rs.getObject(_iterator + 1);

                            // iterating over clazz attributes to check
                            // if any attribute has 'Column' annotation with matching 'name' value
                            for (Field field : fields) {
                                if (field.getName().equalsIgnoreCase(columnName)
                                        && columnValue != null) {
                                    this.setProperty(bean, field.getName(), columnValue);
                                    break;
                                }
                            } // EndOf for(Field field : fields)
                        } // EndOf for(_iterator...)
                        if (outputList == null) {
                            outputList = new ArrayList<>();
                        }
                        outputList.add(bean);
                    } // EndOf while(rs.next())
//                } else {
//                    // throw some error that Class clazz
//                    // does not have @Entity annotation
//                }
            } else {
                // ResultSet is empty
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputList;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public T mapResultSetToObject(ResultSet rs, Class clazz) {
        T output = null;
        try {
            // make sure resultset is not null
            if (rs != null) {

                // check if Class clazz has the 'Entity' annotation
//                if (clazz.isAnnotationPresent(Entity.class)) {

                    // get the resultset metadata
                    ResultSetMetaData rsmd = rs.getMetaData();

                    // get all the attributes of Class clazz
                    Field[] fields = clazz.getDeclaredFields();

                    if (rs.next()) {
                        T bean = (T) clazz.newInstance();
                        for (int _iterator = 0; _iterator < rsmd.getColumnCount(); _iterator++) {
                            // get the SQL column name
                            String columnName = rsmd.getColumnName(_iterator + 1);
//                            System.out.println(columnName);

                            // get the value of the SQL column
                            Object columnValue = rs.getObject(_iterator + 1);

                            // iterating over clazz attributes to check
                            // if any attribute has 'Column' annotation with matching 'name' value
                            for (Field field : fields) {
                                if (field.getName().equalsIgnoreCase(columnName)
                                        && columnValue != null) {
                                    this.setProperty(bean, field.getName(), columnValue);
                                    break;
                                }
                            } // EndOf for(Field field : fields)
                        } // EndOf for(_iterator...)
                output =bean;
                    } // EndOf while(rs.next())
            } else {
                // ResultSet is empty
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return output;
    }
}
