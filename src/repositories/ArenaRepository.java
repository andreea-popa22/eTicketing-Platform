package repositories;

import config.DatabaseConfiguration;
import entities.locations.Arena;
import services.Service;

import java.sql.*;

public class ArenaRepository {

    public ArenaRepository() {
    }

    //create
    public void insertArena(Arena arena) {
        String preparedSql = "INSERT INTO Arena (arena_id, arena_name, address, contact, capacity, price, surface) VALUES (?,?,?,?,?,?,?)";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement ps = databaseConnection.prepareStatement("SELECT COUNT(arena_id) + 1 FROM Arena");
            ResultSet rs = ps.executeQuery();
            int index = 0;
            if (rs.next()){
                index = rs.getInt(1);
            }
            if (index == 0){
                index += 1;
            }
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(preparedSql);
            preparedStatement.setInt(1, index);
            preparedStatement.setString(2, arena.getName());
            preparedStatement.setString(3, arena.getAddress());
            preparedStatement.setString(4, arena.getContact().toString());
            preparedStatement.setInt(5, arena.getCapacity());
            preparedStatement.setInt(6, arena.getPrice_per_hour());
            preparedStatement.setInt(7, arena.getSurface());
            preparedStatement.execute();
            System.out.println("Added arena with id = " + index);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //read
    public Arena getArenaById(int id) {
        String selectSql = "SELECT * FROM Arena WHERE arena_id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToArena(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //update
    public void updateArenaPrice(Integer id, Integer price) {
        String updateNameSql = "UPDATE Arena SET price=? WHERE arena_id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateNameSql);
            preparedStatement.setInt(1, price);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            System.out.println("Arena with id = " + id.toString() + " was modified.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Arena mapToArena(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Arena(resultSet.getString(2), resultSet.getString(3), Service.stringToPhone(resultSet.getString(4)), resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7));
        }
        return null;
    }

    //delete
    public void removeArena(Integer id) {
        String deleteSql = "DELETE FROM Arena WHERE arena_id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(deleteSql);
            preparedStatement.setInt(1, id);
            int resultSet = preparedStatement.executeUpdate();
            System.out.println("The arena with id = " + id.toString() + " was removed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayArenas() {
        String selectSql = "SELECT * FROM Arena";

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
