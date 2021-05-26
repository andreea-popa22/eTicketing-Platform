package repositories;

import config.DatabaseConfiguration;
import entities.events.Actor;

import java.sql.*;

public class ActorRepository {

    public ActorRepository() {
    }

    //create
    public void insertActor(Actor actor) {
        String preparedSql = "INSERT INTO Actor (actor_id, actor_name, actor_price) VALUES (?,?,?)";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(preparedSql);
            preparedStatement.setInt(1, actor.getId());
            preparedStatement.setString(2, actor.getName());
            preparedStatement.setInt(3, actor.getPrice_per_play());
            preparedStatement.execute();
            System.out.println("Added actor with id = " + actor.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //read
    public Actor getActorById(int id) {
        String selectSql = "SELECT * FROM Actor WHERE actor_id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToActor(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //update
    public void updateActorName(Integer id, String name, Integer price) {
        String updateNameSql = "UPDATE Actor SET actor_name=? , actor_price=? WHERE actor_id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateNameSql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, price);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
            System.out.println("Actor with id = " + id.toString() + " was modified.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Actor mapToActor(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Actor(resultSet.getString(2), resultSet.getInt(3));
        }
        return null;
    }

    //delete
    public void removeActor(Integer id) {
        String deleteSql = "DELETE FROM Actor WHERE actor_id=?";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(deleteSql);
            preparedStatement.setInt(1, id);
            int resultSet = preparedStatement.executeUpdate();
            System.out.println("The actor with id = " + id.toString() + " was removed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayActors() {
        String selectSql = "SELECT * FROM Actor";

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        try {
            ResultSet resultSet = repositoryHelper.executeQuerySql(databaseConnection, selectSql);
            while (resultSet.next()) {
                System.out.println("Id:" + resultSet.getString(1) + ";  Name:" + resultSet.getString(2) + ";  Price:" + resultSet.getDouble(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
