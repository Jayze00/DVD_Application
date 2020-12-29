package dao;

import controller.Connector;
import domain.Collection;

import java.sql.*;
import java.util.ArrayList;

public class CollectionDAO {

    private Connection connection = null;
    private int statementSuccessfull = 0;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private ArrayList<Collection> collections;

    // insert
    public int createCollection(Collection collection) {
        try {
            // create connection
            connection = Connector.getInstance().getConnection();
            connection.setAutoCommit(false);

            // create prepared statement
            preparedStatement = connection.prepareStatement("INSERT INTO tbl_collection (id, colleciton_name, amount, completed) values (????);");
            // move values to preparedstatement
            preparedStatement.setInt(1, collection.getId());
            preparedStatement.setString(2, collection.getCollectionName());
            preparedStatement.setInt(3, collection.getAmount());
            preparedStatement.setInt(4, collection.getIsCompleted());

            // execute update
            preparedStatement.executeUpdate();

            statementSuccessfull = 1;
        } catch (SQLException sqle) {

            sqle.printStackTrace();
        } finally {
            releaseConnection();
        }
        return statementSuccessfull;
    }

    // update
    public int updateCollection(Collection collection) {
        try {
            // create connection
            connection = Connector.getInstance().getConnection();
            connection.setAutoCommit(false);

            // create prepared statement
            preparedStatement = connection.prepareStatement("UPDATE tbl_collection set collection_name = ?, amount = ?, completed = ? where id = ?;");
            // move values to preparedstatement
            preparedStatement.setString(1, collection.getCollectionName());
            preparedStatement.setInt(2, collection.getAmount());
            preparedStatement.setInt(3, collection.getIsCompleted());
            preparedStatement.setInt(4, collection.getId());

            // execute update
            preparedStatement.executeUpdate();

            statementSuccessfull = 1;
        } catch (SQLException sqle) {

            sqle.printStackTrace();
        } finally {
            releaseConnection();
        }
        return statementSuccessfull;
    }

    // select all
    public ArrayList<Collection> getCollections() {
        try {
            // create connection
            connection = Connector.getInstance().getConnection();
            connection.setAutoCommit(false);

            // create prepared statement
            preparedStatement = connection.prepareStatement("SELECT id, collection_name, amount, completed FROM tbl_collection;");

            // execute select
            resultSet = preparedStatement.executeQuery();

            // prepare and fill results to list
            while (resultSet.next()) {
                Collection col = new Collection();
                col.setId(resultSet.getInt(1));
                col.setCollectionName(resultSet.getString(2));
                col.setAmount(resultSet.getInt(3));
                col.setIsCompleted(resultSet.getInt(4));
                collections.add(col);
            }

        } catch (SQLException sqle) {

            sqle.printStackTrace();
        } finally {
            releaseConnection();
        }
        return collections;
    }

    // select one
    public Collection selectCollection(int id) {
        Collection col = new Collection();
        try {
            // create connection
            connection = Connector.getInstance().getConnection();
            connection.setAutoCommit(false);

            // create prepared statement
            preparedStatement = connection.prepareStatement("SELECT id, collection_name, amount, completed FROM tbl_collection where id = ?;");

            // execute select
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            // prepare and fill results to list
            if (resultSet.next()) {

                col.setId(resultSet.getInt(1));
                col.setCollectionName(resultSet.getString(2));
                col.setAmount(resultSet.getInt(3));
                col.setIsCompleted(resultSet.getInt(4));
            }

        } catch (SQLException sqle) {

            sqle.printStackTrace();
        } finally {
            releaseConnection();
        }
        return col;
    }

    private void releaseConnection() {
        try {
            // clear the connections
            if (resultSet != null)
                resultSet.close();

            if (preparedStatement != null)
                preparedStatement.close();

            Connector.getInstance().close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }
}
