package repositories;

import config.DatabaseConfiguration;
import entities.locations.Outdoor;
import services.Service;

import java.sql.*;

public class OutdoorRepository {

    public OutdoorRepository() {
    }

    //create
    public void insertOutdoor(Outdoor outdoor) {
        String preparedSql = "INSERT INTO Outdoor (outdoor_id, outdoor_name, address, contact, capacity, price, surface) VALUES (?,?,?,?,?,?,?)";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(preparedSql);
            preparedStatement.setInt(1, outdoor.getId());
            preparedStatement.setString(2, outdoor.getName());
            preparedStatement.setString(3, outdoor.getAddress());
            preparedStatement.setString(4, outdoor.getContact().toString());
            preparedStatement.setInt(5, outdoor.getCapacity());
            preparedStatement.setInt(6, outdoor.getPrice_per_hour());
            preparedStatement.setInt(7, outdoor.getSurface());
            preparedStatement.execute();
            System.out.println("Added outdoor with id = " + outdoor.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //read
    public Outdoor getOutdoorById(int id) {
        String selectSql = "SELECT * FROM Outdoor WHERE outdoor_id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToOutdoor(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //update
    public void updateOutdoorPrice(Integer id, Integer price) {
        String updateNameSql = "UPDATE Outdoor SET price=? WHERE outdoor_id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateNameSql);
            preparedStatement.setInt(1, price);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            System.out.println("Outdoor with id = " + id.toString() + " was modified.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Outdoor mapToOutdoor(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Outdoor(resultSet.getString(2), resultSet.getString(3), Service.stringToPhone(resultSet.getString(4)), resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7));
        }
        return null;
    }

    //delete
    public void removeOutdoor(Integer id) {
        String deleteSql = "DELETE FROM Outdoor WHERE outdoor_id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(deleteSql);
            preparedStatement.setInt(1, id);
            int resultSet = preparedStatement.executeUpdate();
            System.out.println("The outdoor location with id = " + id.toString() + " was removed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayOutdoors() {
        String selectSql = "SELECT * FROM Outdoor";

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
