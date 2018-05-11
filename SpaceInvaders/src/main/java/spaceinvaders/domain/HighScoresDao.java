package spaceinvaders.domain;

import java.sql.*;
import java.util.ArrayList;

public class HighScoresDao {
    public String[] listOfNames = new String[5];
    public int[] listOfPoints = new int[5];
    
    String namess = new String();
    
    public HighScoresDao() throws Exception{
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:highscores.db")) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM highscore");
            
            ResultSet resultSet = statement.executeQuery();
            
            int index = 0;
            while (resultSet.next() && index < 5) {
                listOfNames[index] = resultSet.getString("name");
                listOfPoints[index] = resultSet.getInt("points");
                index++;
            }
            
            connection.close();
        }
    }
    
    /**
     * @return returns all names in the database as one long string
     */
    public String getNamesAsString() {
        String names = new String();
        
        for (String name : this.listOfNames) {
            names += name + "\n\n";
        }
        
        return names;
    }
    
    /**
     * @return returns all points in the database as one long string
     */
    public String getPointsAsString() {
        String points = new String();
        
        for (int point : this.listOfPoints) {
            points += point + " \n\n";
        }
        
        return points;
    }
    
    /**
     * Inserts a highscore in to the database and deletes the smallest one
     * 
     * @param name name of the highscore
     * @param points points of the highscore
     * @throws SQLException 
     */
    public void insertHighScore(String name, int points) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:highscores.db")) {
            
            PreparedStatement getScores = connection.prepareStatement("SELECT * FROM highscore WHERE points < " + points);
            ResultSet rs = getScores.executeQuery();
            
            ArrayList<Integer> searchedPoints = new ArrayList<>();
            while(rs.next()) {
                searchedPoints.add(rs.getInt("points"));
            }
            int smallestPoint = 99999;
            for (int i = 0; i < searchedPoints.size(); i++) {
                if (searchedPoints.get(i) < smallestPoint) {
                    smallestPoint = searchedPoints.get(i);
                }
            }
            
            PreparedStatement removeScore = connection.prepareStatement("DELETE FROM highscore WHERE points = " + smallestPoint);
            removeScore.executeUpdate();
            
            PreparedStatement insert = connection.prepareStatement(
                    "INSERT INTO highscore(name, points) VALUES ('" + name + "'," + points + ")");
            insert.executeUpdate();
        }
    }
}
