package dao;

import controller.Connector;
import domain.CollectionDvdMapping;

import java.sql.*;
import java.util.ArrayList;

public class CollectionDvdMappingDAO {

    private Connection connection = null;
    private int statementSuccessfull = 0;
    private PreparedStatement preparedStatement = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private ArrayList<CollectionDvdMapping> collectionDvdMappings;


    // insert
    public int createCollectionDvdMapping(CollectionDvdMapping collectionDvdMapping) {
        try {
            // create connection
            connection = Connector.getInstance().getConnection();
            connection.setAutoCommit(false);

            // create prepared statement
            preparedStatement = connection.prepareStatement("INSERT INTO tbl_collection_dvd_mapping (id, fk_collection, fk_dvd) values (???);");
            // move values to preparedstatement
            preparedStatement.setInt(1, collectionDvdMapping.getId());
            preparedStatement.setInt(2, collectionDvdMapping.getFkCollection());
            preparedStatement.setInt(3, collectionDvdMapping.getFkDvd());

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
    public int updateCollectionDvdMapping(CollectionDvdMapping collectionDvdMapping) {
        try {
            // create connection
            connection = Connector.getInstance().getConnection();
            connection.setAutoCommit(false);

            // create prepared statement
            preparedStatement = connection.prepareStatement("UPDATE tbl_collection_dvd_mapping set fk_collection = ?, fk_dvd = ? WHERE id = ?;");
            // move values to preparedstatement
            preparedStatement.setInt(1, collectionDvdMapping.getFkCollection());
            preparedStatement.setInt(2, collectionDvdMapping.getFkDvd());
            preparedStatement.setInt(3, collectionDvdMapping.getId());

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
    public ArrayList<CollectionDvdMapping> getCollectionDvdMappings() {
        try {
            // create connection
            connection = Connector.getInstance().getConnection();
            connection.setAutoCommit(false);

            // create prepared statement
            preparedStatement = connection.prepareStatement("SELECT id, fk_collection, fk_dvd FROM tbl_collection_dvd_mapping;");

            // execute select
            resultSet = preparedStatement.executeQuery();

            // prepare and fill results to list
            while (resultSet.next()) {
                CollectionDvdMapping collectionDvdMapping = new CollectionDvdMapping();
                collectionDvdMapping.setId(resultSet.getInt(1));
                collectionDvdMapping.setFkCollection(resultSet.getInt(2));
                collectionDvdMapping.setFkDvd(resultSet.getInt(3));
                collectionDvdMappings.add(collectionDvdMapping);
            }

        } catch (SQLException sqle) {

            sqle.printStackTrace();
        } finally {
            releaseConnection();
        }
        return collectionDvdMappings;
    }

    public CollectionDvdMapping getCollectionDvdMapping(int id) {
        CollectionDvdMapping collectionDvdMapping = new CollectionDvdMapping();
        try {
            // create connection
            connection = Connector.getInstance().getConnection();
            connection.setAutoCommit(false);

            // create prepared statement
            preparedStatement = connection.prepareStatement("SELECT id, fk_collection, fk_dvd FROM tbl_collection_dvd_mapping WHERE id = ?;");
            // move values to preparedstatement
            preparedStatement.setInt(1, id);

            // execute select
            resultSet = preparedStatement.executeQuery();

            // fill result set to collectionDvdMapping
            if (resultSet.next()) {
                collectionDvdMapping.setId(resultSet.getInt(1));
                collectionDvdMapping.setFkCollection(resultSet.getInt(2));
                collectionDvdMapping.setFkDvd(resultSet.getInt(3));
            }

        } catch (SQLException sqle) {

            sqle.printStackTrace();
        } finally {
            releaseConnection();
        }
        return collectionDvdMapping;
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
