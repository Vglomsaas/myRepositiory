import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

public class QuizGame {
    public static void main(String[] args) {

        // Prepare the quiz
        Scanner scanner = new Scanner(System.in);
        SetupQuiz setup = new SetupQuiz();

        HashMap<Integer, String> binaryQuestions = setup.getBinaryQuestions();
        HashMap<Integer, String> binaryAnswers = setup.getBinaryAnswers();
        HashMap<Integer, String> multichoiceQuestions = setup.getMultichoiceQuestions();
        HashMap<Integer, String> multichoiceAnswers = setup.getMultichoiceAnswers();

        setup.init();

        // Run the quiz.

        System.out.print("" +
                "\n\n------- Velkommen til 'Can You Quiz It?' -------\n\n\n" +

                "Hva er navnet ditt? (unikt)\n"
        );

        String name = scanner.nextLine();

        if (!setup.isFirstUser()) {

            System.out.println("Hei %s, vi ser du er ny her :)".formatted(name));

        } else {

            System.out.print(
                    "\n%s, vi har savnet deg!\n".formatted(name) +
                    "(0) Se min highscore\n"
            );
        }

        System.out.print(
                "\nVelg hvilken quiz du vil ta. (Svar ved å skrive tallet på alternativet)\n" +
                "\n(1) Binær Quiz (Sant / Usant)" +
                "\n(2) Flervalg Quiz (4 Alternativer)"
        );
    };
}
