package dao;

import controller.Connector;
import domain.Dvd;

import java.sql.*;
import java.util.ArrayList;

public class DvdDAO {

    private Connection connection = null;
    private int statementSuccessfull = 0;
    private PreparedStatement preparedStatement = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private ArrayList<Dvd> dvds = null;

    // insert
    public int createDvd(Dvd dvd) {
        try {
            // create connection
            connection = Connector.getInstance().getConnection();
            connection.setAutoCommit(false);

            // create prepared statement
            preparedStatement = connection.prepareStatement("INSERT INTO tbl_collection (id, title, release_date, trailer_link, cover) values (?????)");
            // move values to preparedstatement
            preparedStatement.setInt(1, dvd.getId());
            preparedStatement.setString(2, dvd.getTitle());
            preparedStatement.setDate(3, dvd.getReleaseDate());
            preparedStatement.setString(4, dvd.getTrailerLink());
            preparedStatement.setBlob(5, dvd.getCover());

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
    public int updateDvd(Dvd dvd) {
        try {
            // create connection
            connection = Connector.getInstance().getConnection();
            connection.setAutoCommit(false);

            // create prepared statement
            preparedStatement = connection.prepareStatement("UPDATE tbl_dvd set title = ?, release_date = ?, trailer_link = ?, cover = ? WHERE id = ?;");
            // move values to preparedstatement
            preparedStatement.setString(1, dvd.getTitle());
            preparedStatement.setDate(2, dvd.getReleaseDate());
            preparedStatement.setString(3, dvd.getTrailerLink());
            preparedStatement.setBlob(4, dvd.getCover());

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
    public ArrayList<Dvd> getDvds() {
        try {
            // create connection
            connection = Connector.getInstance().getConnection();
            connection.setAutoCommit(false);

            // create prepared statement
            preparedStatement = connection.prepareStatement("SELECT id, title, release_date, trailer_link, cover FROM tbl_dvd;");

            // execute select
            resultSet = preparedStatement.executeQuery();

            // prepare and fill results to list
            while (resultSet.next()) {
                Dvd dvd = new Dvd();
                dvd.setId(resultSet.getInt(1));
                dvd.setTitle(resultSet.getString(2));
                dvd.setReleaseDate(resultSet.getDate(3));
                dvd.setTrailerLink(resultSet.getString(4));
                dvd.setCover(resultSet.getBlob(5));
                dvds.add(dvd);
            }

        } catch (SQLException sqle) {

            sqle.printStackTrace();
        } finally {
            releaseConnection();
        }
        return dvds;
    }

    public Dvd dvd(int id) {
        Dvd dvd = new Dvd();
        try {
            // create connection
            connection = Connector.getInstance().getConnection();
            connection.setAutoCommit(false);

            // create prepared statement
            preparedStatement = connection.prepareStatement("SELECT id, title, release_date, trailer_link, cover from tbl_dvd WHERE id = ?;");
            // move values to preparedstatement
            preparedStatement.setInt(1, id);

            // execute select
            resultSet = preparedStatement.executeQuery();

            // fill result set to collectionDvdMapping
            if (resultSet.next()) {
                dvd.setId(resultSet.getInt(1));
                dvd.setTitle(resultSet.getString(2));
                dvd.setReleaseDate(resultSet.getDate(3));
                dvd.setTrailerLink(resultSet.getString(4));
                dvd.setCover(resultSet.getBlob(5));
            }

        } catch (SQLException sqle) {

            sqle.printStackTrace();
        } finally {
            releaseConnection();
        }
        return dvd;
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
