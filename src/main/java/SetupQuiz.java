import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class SetupQuiz {

    //# FIELDS
    static Database database;

    private Boolean isFirstUser = true;

    private static ResultSet rsBinaryQuiz;
    private static ResultSet rsMultichoiceQuiz;
    private static ResultSet rsUsersList;

    private HashMap<Integer, String> binaryQuestions = new HashMap<>();
    private HashMap<Integer, String> binaryAnswers = new HashMap<>();

    private HashMap<Integer, String> multichoiceQuestions = new HashMap<>();
    private HashMap<Integer, String> multichoiceAnswers = new HashMap<>();

    private HashMap<Integer, String> usersName = new HashMap<>();
    private HashMap<Integer, String> usersScore = new HashMap<>();

    //# STATIC INITIALIZER
    static {

        database = new Database("quizDB", "user", "password");

    }

    //# METHODS
    public void init() {
        try (Connection connection = database.getConnection()) {
            String binaryQuery = "SELECT * FROM binaryQuiz";
            String multichoiceQuery = "SELECT * FROM multichoiceQuiz";
            String usersListQuery = "SELECT * FROM users";

            Statement statement = connection.createStatement();

            // SAVES DATA FROM THE RESULTSETS TO LOCAL HASHMAPS.

            rsBinaryQuiz = statement.executeQuery(binaryQuery);

            while (rsBinaryQuiz.next()) {
                binaryQuestions.put(
                    rsBinaryQuiz.getInt("id"), //# Get question ID
                    rsBinaryQuiz.getString("question") //# Get question String
                );

                binaryAnswers.put(
                        rsBinaryQuiz.getInt("id"), //# Get answer ID
                        rsBinaryQuiz.getString("correctAnswer") //# Get answer String
                );
            }

            rsMultichoiceQuiz = statement.executeQuery(multichoiceQuery);

            while (rsMultichoiceQuiz.next()) {
                multichoiceQuestions.put(
                    rsMultichoiceQuiz.getInt("id"),//# Get question ID
                    rsMultichoiceQuiz.getString("question") //# Get question String
                );

                multichoiceAnswers.put(
                    rsMultichoiceQuiz.getInt("id"), //# Get answer ID
                    rsMultichoiceQuiz.getString("correctAnswer") //# Get answer String
                );
            }

            rsUsersList = statement.executeQuery(usersListQuery);

            if (rsUsersList.isBeforeFirst()) {

                isFirstUser = false;

                while (rsUsersList.next()) {
                    usersName.put(
                            rsUsersList.getInt("id"),
                            rsUsersList.getString("name")
                    );

                    usersScore.put(
                            rsUsersList.getInt("id"),
                            rsUsersList.getString("highscore")
                    );
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };

    public HashMap<Integer, String> getBinaryQuestions() {
        return binaryQuestions;
    };
    public HashMap<Integer, String> getBinaryAnswers() {
        return binaryAnswers;
    };
    public HashMap<Integer, String> getMultichoiceQuestions() {
        return multichoiceQuestions;
    };
    public HashMap<Integer, String> getMultichoiceAnswers() {
        return multichoiceAnswers;
    };
    public HashMap<Integer, String> getUsersName(){ return usersName;};
    public Boolean isFirstUser() {
        return isFirstUser;
    }
}
