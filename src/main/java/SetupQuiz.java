import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SetupQuiz {
    static {
        database = new Database("quizDB", "user", "password");
    }
    static Database database;
    private HashMap<Integer, String> binaryQuestions = new HashMap<>();
    private HashMap<Integer, String> multichoiceQuestions = new HashMap<>();

    try (Connection connection = database.getConnection()) {
        String query = "SELECT * FROM binaryQuiz";

        Statement statement = connection.createStatement();

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
