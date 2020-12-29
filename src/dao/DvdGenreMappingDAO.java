package dao;

import controller.Connector;
import domain.CollectionDvdMapping;
import domain.DvdGenreMapping;

import java.sql.*;
import java.util.ArrayList;

public class DvdGenreMappingDAO {

    private Connection connection = null;
    private int statementSuccessfull = 0;
    private PreparedStatement preparedStatement = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private ArrayList<DvdGenreMapping> dvdGenreMappings = null;

    // insert
    public int createDvdGenreMapping(DvdGenreMapping dvdGenreMapping) {
        try {
            // create connection
            connection = Connector.getInstance().getConnection();
            connection.setAutoCommit(false);

            // create prepared statement
            preparedStatement = connection.prepareStatement("INSERT INTO tbl_dvd_genre_mapping (id, fk_dvd, fk_genre) values (???)");
            // move values to preparedstatement
            preparedStatement.setInt(1, dvdGenreMapping.getId());
            preparedStatement.setInt(2, dvdGenreMapping.getFkDvd());
            preparedStatement.setInt(3, dvdGenreMapping.getFkGenre());

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
    public int updateDvdGenreMapping(DvdGenreMapping dvdGenreMapping) {
        try {
            // create connection
            connection = Connector.getInstance().getConnection();
            connection.setAutoCommit(false);

            // create prepared statement
            preparedStatement = connection.prepareStatement("UPDATE tbl_collection_dvd_mapping set fk_dvd = ?, fk_genre = ? WHERE id = ?;");
            // move values to preparedstatement
            preparedStatement.setInt(1, dvdGenreMapping.getFkDvd());
            preparedStatement.setInt(2, dvdGenreMapping.getFkGenre());
            preparedStatement.setInt(3, dvdGenreMapping.getId());

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
    public ArrayList<DvdGenreMapping> getDvdGenreMappings() {
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
                DvdGenreMapping dvdGenreMapping = new DvdGenreMapping();
                dvdGenreMapping.setId(resultSet.getInt(1));
                dvdGenreMapping.setFkDvd(resultSet.getInt(2));
                dvdGenreMapping.setFkGenre(resultSet.getInt(3));
                dvdGenreMappings.add(dvdGenreMapping);
            }

        } catch (SQLException sqle) {

            sqle.printStackTrace();
        } finally {
            releaseConnection();
        }
        return dvdGenreMappings;
    }

    public DvdGenreMapping dvdGenreMapping(int id) {
        DvdGenreMapping dvdGenreMapping = new DvdGenreMapping();
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
                dvdGenreMapping.setId(resultSet.getInt(1));
                dvdGenreMapping.setFkDvd(resultSet.getInt(2));
                dvdGenreMapping.setFkGenre(resultSet.getInt(3));
            }

        } catch (SQLException sqle) {

            sqle.printStackTrace();
        } finally {
            releaseConnection();
        }
        return dvdGenreMapping;
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
