package dao;

import controller.Connector;
import domain.CollectionDvdMapping;
import domain.Genre;

import java.sql.*;
import java.util.ArrayList;

public class GenreDAO {

    private Connection connection = null;
    private int insertSuccesfull = 0;
    private PreparedStatement preparedStatement = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private ArrayList<Genre> genres = null;

    // insert
    public int createGenre(Genre genre) {
        try {
            // create connection
            connection = Connector.getInstance().getConnection();
            connection.setAutoCommit(false);

            // create prepared statement
            preparedStatement = connection.prepareStatement("INSERT INTO tbl_genre (id, genre) values (??)");
            // move values to preparedstatement
            preparedStatement.setInt(1, genre.getId());
            preparedStatement.setString(2, genre.getGenre());

            // execute update
            preparedStatement.executeUpdate();

            insertSuccesfull = 1;
        } catch (SQLException sqle) {

            sqle.printStackTrace();
        } finally {
            releaseConnection();
        }
        return insertSuccesfull;
    }

    // update
    public int updateGenre(Genre genre){
        try {
            // create connection
            connection = Connector.getInstance().getConnection();
            connection.setAutoCommit(false);

            // create prepared statement
            preparedStatement = connection.prepareStatement("UPDATE tbl_genre set genre = ? WHERE id = ?;");
            // move values to preparedstatement
            preparedStatement.setString(1, genre.getGenre());
            preparedStatement.setInt(2, genre.getId());

            // execute update
            preparedStatement.executeUpdate();

            insertSuccesfull = 1;
        } catch (SQLException sqle) {

            sqle.printStackTrace();
        } finally {
            releaseConnection();
        }
        return insertSuccesfull;
    }

    // select all
    public ArrayList<Genre> genres(){
        try {
            // create connection
            connection = Connector.getInstance().getConnection();
            connection.setAutoCommit(false);

            // create prepared statement
            preparedStatement = connection.prepareStatement("SELECT id, genre FROM tbl_genre;");

            // execute select
            resultSet = preparedStatement.executeQuery();

            // prepare and fill results to list
            while (resultSet.next()) {
                Genre genre = new Genre();
                genre.setId(resultSet.getInt(1));
                genre.setGenre(resultSet.getString(2));
                genres.add(genre);
            }

        } catch (SQLException sqle) {

            sqle.printStackTrace();
        } finally {
            releaseConnection();
        }
        return genres;
    }

    public Genre getGenre(int id){
        Genre genre = new Genre();
        try {
            // create connection
            connection = Connector.getInstance().getConnection();
            connection.setAutoCommit(false);

            // create prepared statement
            preparedStatement = connection.prepareStatement("SELECT id, genre FROM tbl_genre where id = ?;");
            // move values to preparedstatement
            preparedStatement.setInt(1, id);

            // execute select
            resultSet = preparedStatement.executeQuery();

            // fill result set to collectionDvdMapping
            if (resultSet.next()) {
                genre.setId(resultSet.getInt(1));
                genre.setGenre(resultSet.getString(2));
            }

        } catch (SQLException sqle) {

            sqle.printStackTrace();
        } finally {
            releaseConnection();
        }
        return genre;
    }

    private void releaseConnection (){
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
