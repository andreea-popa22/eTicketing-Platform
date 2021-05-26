package repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RepositoryHelper {

    private static RepositoryHelper repositoryHelper = new RepositoryHelper();

    private RepositoryHelper() {}

    public static RepositoryHelper getRepositoryHelper() {
        return repositoryHelper;
    }

    public void executeSql(Connection connection, String sql) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute(sql);
    }

    public void executeUpdateSql(Connection connection, String sql) throws SQLException {
        Statement stmt = connection.createStatement();
        int i = stmt.executeUpdate(sql);
    }

    public ResultSet executeQuerySql(Connection connection, String sql) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(sql);
    }

}