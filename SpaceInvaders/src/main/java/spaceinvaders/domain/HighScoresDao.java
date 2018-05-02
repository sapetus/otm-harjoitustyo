package spaceinvaders.domain;

import java.sql.*;

public class HighScoresDao {
    public String[] listOfNames = new String[5];
    public int[] listOfPoints = new int[5];
    
    public HighScoresDao() throws Exception{
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:highscores.db")) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM highscore");
            
            ResultSet resultSet = statement.executeQuery();
            
            int index = 0;
            if (resultSet.next()) {
                listOfNames[index] = resultSet.getString("name");
                listOfPoints[index] = resultSet.getInt("points");
                index++;
            }
            connection.close();
        }
    }
    
    public String getNamesAsString() {
        String names = new String();
        
        for (String name : this.listOfNames) {
            names += name + "\n\n";
        }
        
        return names;
    }
    
    public String getPointsAsString() {
        String points = new String();
        
        for (int point : this.listOfPoints) {
            points += point + " \n\n";
        }
        
        return points;
    }
    
    public void insertHighScore(String name, int points) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:highscores.db")) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO highscore(name, points) VALUES ('" + name + "'," + points + ")");
        }
    }
}
