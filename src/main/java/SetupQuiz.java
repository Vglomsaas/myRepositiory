import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class SetupQuiz {

    //# FIELDS
    static Database database;

    private static ResultSet rsBinaryQuiz;
    private static ResultSet rsMultichoiceQuiz;

    private HashMap<Integer, String> binaryQuestions = new HashMap<>();
    private HashMap<Integer, String> binaryAnswers = new HashMap<>();
    private HashMap<Integer, String> multichoiceQuestions = new HashMap<>();
    private HashMap<Integer, String> multichoiceAnswers = new HashMap<>();

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

            statement.close();

            while (rsBinaryQuiz.next()) {
                binaryQuestions.put(
                    rsBinaryQuiz.getInt("id"), //# Get question ID
                    rsBinaryQuiz.getString("question") //# Get question String
                );

                binaryAnswers.put(
                        rsBinaryQuiz.getInt("id"),
                        rsBinaryQuiz.getString("correctAnswer")
                );
            }

            while (rsMultichoiceQuiz.next()) {
                multichoiceQuestions.put(
                    rsMultichoiceQuiz.getInt("id"),
                    rsMultichoiceQuiz.getString("question")
                );

                multichoiceAnswers.put(
                    rsMultichoiceQuiz.getInt("id"),
                    rsMultichoiceQuiz.getString("correctAnswer")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




}
