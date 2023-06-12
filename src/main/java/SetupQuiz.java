import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SetupQuiz {

    //# FIELDS
    static Database database;

    private Boolean isFirstUser = true;

    private static ResultSet rsBinaryQuiz;
    private static ResultSet rsMultichoiceQuiz;
    private static ResultSet rsUsersList;

    private ArrayList<BinaryRecordObject> binaryObjectsArraylist = new ArrayList<>();
    private ArrayList<MultichoiceRecordObject> multichoiceObjectsArraylist = new ArrayList<>();
    private ArrayList<UserRecordObject> userObjectsArraylist = new ArrayList<>();

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
                 BinaryRecordObject object = new BinaryRecordObject (
                         rsBinaryQuiz.getInt("id"),
                         rsBinaryQuiz.getString("question"),
                         rsBinaryQuiz.getString("answerA"),
                         rsBinaryQuiz.getString("answerB"),
                         rsBinaryQuiz.getString("correctAnswer")
                 );

                 binaryObjectsArraylist.add(object);
            }

            rsMultichoiceQuiz = statement.executeQuery(multichoiceQuery);

            while (rsMultichoiceQuiz.next()) {
                MultichoiceRecordObject object = new MultichoiceRecordObject (
                        rsMultichoiceQuiz.getInt("id"),
                        rsMultichoiceQuiz.getString("question"),
                        rsMultichoiceQuiz.getString("answerA"),
                        rsMultichoiceQuiz.getString("answerB"),
                        rsMultichoiceQuiz.getString("answerC"),
                        rsMultichoiceQuiz.getString("answerD"),
                        rsMultichoiceQuiz.getString("correctAnswer")
                );

                multichoiceObjectsArraylist.add(object);
            }

            rsUsersList = statement.executeQuery(usersListQuery);

            // Here we check if data exists in the users table,
            // to prevent that data is being gathered from an
            // empty ResultSet.
            if (rsUsersList.isBeforeFirst()) {

                isFirstUser = false;

                while (rsUsersList.next()) {

                    UserRecordObject object = new UserRecordObject(
                            rsUsersList.getInt("id"),
                            rsUsersList.getString("name"),
                            rsUsersList.getString("highscore")
                    );

                    userObjectsArraylist.add(object);
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };

    //# GETTERS
    public ArrayList<BinaryRecordObject> getBinaryObjectsArraylist() {

        return binaryObjectsArraylist;

    }
    public ArrayList<MultichoiceRecordObject> getMultichoiceObjectsArraylist() {

        return multichoiceObjectsArraylist;

    }
    public ArrayList<UserRecordObject> getUserObjectsArraylist() {

        if(!isFirstUser) {

            // Return highscores if users has been registered.
            return userObjectsArraylist;

        } else {

            //  Returns null if no users has been registered.
            return null;

        }

    }
    public Boolean isFirstUser() {

        return isFirstUser;

    }
}
