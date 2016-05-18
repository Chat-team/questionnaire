package com.sighingnow.questionnaire;

import java.lang.reflect.Executable;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.sql.*;

public class Sqlite3 {
    private static String dbfile = "questionnaire.db";
    public static String [] attrs = new String[] {
            "s1", "s2", "s4", "s7",
            "a1", "a2", "b1", "b2", "b3", "g1", "h1", "h2", "h3", "h4", "h5", "h6", "h7",
            "h7a", "h7b", "h8"
    };

    private static Connection connectdb(String db) throws SQLException, ClassNotFoundException{
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection("jdbc:sqlite:" + db);
    }

    public static void initialize(String rootdir) {
        try {
            Connection conn = connectdb(rootdir + dbfile);
            Statement stmt = conn.createStatement();
            String sql = "create table if not exists questionnaire ("
                    + "id               INTEGER         not null    primary key    AUTOINCREMENT,"
                    + "s1               text         default '',"
                    + "s2               text         default '',"
                    + "s4               text         default '',"
                    + "s7               text         default '',"
                    + "a1               text         default '',"
                    + "a2               text         default '',"
                    + "b1               text         default '',"
                    + "b2               text         default '',"
                    + "b3               text         default '',"
                    + "g1               text         default '',"
                    + "h1               text         default '',"
                    + "h2               text         default '',"
                    + "h3               text         default '',"
                    + "h4               text         default '',"
                    + "h5               text         default '',"
                    + "h6               text         default '',"
                    + "h7               text         default '',"
                    + "h7a              text         default '',"
                    + "h7b              text         default '',"
                    + "h8               text         default '')";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Table created successfully");
    }

    public static void insert(String [] record, String rootdir) {
        initialize(rootdir);
        try {
            Connection conn = connectdb(rootdir + dbfile);
            PreparedStatement stmt = conn.prepareStatement("insert into questionnaire (s1, s2, s4, s7, a1, a2, b1, b2, b3, g1, h1, h2, h3, h4, h5, h6, h7, h7a, h7b, h8) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            for (int i = 0; i < attrs.length; ++i) {
                stmt.setString(i + 1, record[i]);
            }
            stmt.execute();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Insert into table successfully");
    }

    public static List<Map<String, String>> query(String rootdir) {
        List<Map<String, String>> rs = new LinkedList<>();
        try {
            Connection conn = connectdb(rootdir + dbfile);
            Statement stmt = conn.createStatement();
            String sql = "select * from questionnaire";
            ResultSet set = stmt.executeQuery(sql);
            while (set.next()) {
                Map<String, String> r = new TreeMap<>();
                for (String s : attrs) {
                    r.put(s, set.getString(s));
                }
                rs.add(r);
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Query from table successfully");
        return rs;
    }
}
