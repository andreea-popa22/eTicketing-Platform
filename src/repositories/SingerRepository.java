package repositories;

import config.DatabaseConfiguration;
import entities.events.Singer;
import entities.events.SingerType;

import java.sql.*;

public class SingerRepository {

    //create
    public void insertSinger(Singer singer) {
        String preparedSql = "INSERT INTO Singer (singer_id, singer_name, singer_type, singer_price) VALUES (?,?,?,?)";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(preparedSql);
            preparedStatement.setInt(1, singer.getId());
            preparedStatement.setString(2, singer.getName());
            preparedStatement.setString(3, singer.getMusic_type().toString());
            preparedStatement.setInt(4, singer.getPrice_per_hour());
            preparedStatement.execute();
            System.out.println("Added singer with id = " + singer.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //read
    public Singer getSingerById(int id) {
        String selectSql = "SELECT * FROM Singer WHERE singer_id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToSinger(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //update
    public void updateSingerName(Integer id, String name, Integer price, SingerType music_type) {
        String updateNameSql = "UPDATE Singer SET singer_name=? , singer_type=? , singer_price=? WHERE singer_id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateNameSql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, music_type.toString());
            preparedStatement.setInt(3, price);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
            System.out.println("Singer with id = " + id.toString() + " was modified.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Singer mapToSinger(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Singer(resultSet.getString(2), resultSet.getInt(4), SingerType.valueOf(resultSet.getString(3)));
        }
        return null;
    }

    //delete
    public void removeSinger(Integer id) {
        String deleteSql = "DELETE FROM Singer WHERE singer_id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(deleteSql);
            preparedStatement.setInt(1, id);
            int resultSet = preparedStatement.executeUpdate();
            System.out.println("The singer with id = " + id.toString() + " was removed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displaySingers() {
        String selectSql = "SELECT * FROM Singer";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try {
            ResultSet resultSet = repositoryHelper.executeQuerySql(databaseConnection, selectSql);
            while (resultSet.next()) {
                System.out.println("Id:" + resultSet.getString(1) + ";  Name:" + resultSet.getString(2) + ";  Type: " + resultSet.getString(3) + ";  Price:" + resultSet.getDouble(4));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
