package repositories;

import config.DatabaseConfiguration;
import entities.locations.Theatre;
import services.Service;

import java.sql.*;

public class TheatreRepository {

    public TheatreRepository() {
    }

    //create
    public void insertTheatre(Theatre theatre) {
        String preparedSql = "INSERT INTO Theatre (theatre_id, theatre_name, address, contact, capacity, price, surface) VALUES (?,?,?,?,?,?,?)";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(preparedSql);
            preparedStatement.setInt(1, theatre.getId());
            preparedStatement.setString(2, theatre.getName());
            preparedStatement.setString(3, theatre.getAddress());
            preparedStatement.setString(4, theatre.getContact().toString());
            preparedStatement.setInt(5, theatre.getCapacity());
            preparedStatement.setInt(6, theatre.getPrice_per_hour());
            preparedStatement.setInt(7, theatre.getSurface());
            preparedStatement.execute();
            System.out.println("Added theatre with id = " + theatre.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //read
    public Theatre getTheatreById(int id) {
        String selectSql = "SELECT * FROM Theatre WHERE theatre_id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToTheatre(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //update
    public void updateTheatrePrice(Integer id, Integer price) {
        String updateNameSql = "UPDATE Theatre SET price=? WHERE theatre_id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateNameSql);
            preparedStatement.setInt(1, price);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            System.out.println("Theatre with id = " + id.toString() + " was modified.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Theatre mapToTheatre(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Theatre(resultSet.getString(2), resultSet.getString(3), Service.stringToPhone(resultSet.getString(4)), resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7));
        }
        return null;
    }

    //delete
    public void removeTheatre(Integer id) {
        String deleteSql = "DELETE FROM Theatre WHERE theatre_id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(deleteSql);
            preparedStatement.setInt(1, id);
            int resultSet = preparedStatement.executeUpdate();
            System.out.println("The theatre with id = " + id.toString() + " was removed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayTheatres() {
        String selectSql = "SELECT * FROM Theatre";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try {
            ResultSet resultSet = repositoryHelper.executeQuerySql(databaseConnection, selectSql);
            while (resultSet.next()) {
                System.out.println("Id:" + resultSet.getString(1) + ";  Name:" + resultSet.getString(2) + ";  Price:" + resultSet.getDouble(6));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
