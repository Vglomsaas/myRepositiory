import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SetupQuiz {

    //# FIELDS
    static Database database;

    private static ResultSet rsBinaryQuiz;
    private static ResultSet rsMultichoiceQuiz;

    private HashMap<Integer, String> binaryQuestions = new HashMap<>();
    private HashMap<Integer, String> multichoiceQuestions = new HashMap<>();

    //# STATIC INITIALIZER
    static {
        database = new Database("quizDB", "user", "password");

    }

    //# METHODS
    public void setup() {
        try (Connection connection = database.getConnection()) {
            String binaryQuery = "SELECT * FROM binaryQuiz";
            String multichoiceQuery = "SELECT * FROM multichoiceQuiz";

            Statement statement = connection.createStatement();

            rsBinaryQuiz = statement.executeQuery(binaryQuery);
            rsMultichoiceQuiz = statement.executeQuery(multichoiceQuery);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            try {
                binaryQuestions.put(
                        rsBinaryQuiz.getInt(1), //# Get question ID
                        rsBinaryQuiz.getString(2) //# Get question String
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }




}
