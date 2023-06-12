import java.util.ArrayList;
import java.util.Scanner;

public class QuizGame {

    //# METHODS
    private static void printStartMenu(
            Scanner scanner,
            SetupQuiz setup,
            ArrayList<BinaryRecordObject> binaryRecordObjects,
            ArrayList<MultichoiceRecordObject> multichoiceRecordObjects)
    {

        Boolean inputError = false;

        //# WELCOME MESSAGE
        System.out.print("" +
                "\n\n------- Velkommen til 'Can You Quiz It?' -------\n\n\n" +

                "Hva er navnet ditt? (unikt)\n"
        );

        String name = scanner.nextLine();

        if (setup.isFirstUser()) {
            System.out.println("Hei %s, vi ser du er ny her :)\n".formatted(name));

        } else {
            System.out.print(
                    "%s, vi har savnet deg!\n".formatted(name) +
                            "(0) Se min highscore\n"
            );
        }

        System.out.print(
                "Velg hvilken quiz du vil ta. (Svar ved å skrive tallet på alternativet)\n" +
                        "(1) Binær Quiz (Sant / Usant)\n" +
                        "(2) Flervalg Quiz (4 Alternativer)\n"
        );

        String menuChoice = scanner.nextLine();

        switch (menuChoice) {
            case "0":
                if (setup.isFirstUser()) {
                    System.out.println(
                            "Vi har ikke registrert en highscore på '%s' tidligere,\n" +
                                    "Spill for å få en score !\n");
                } else {
                    System.out.println("*Print Highscore*");
                }
                break;

            case "1":
                System.out.println("Du har valgt 'Binær Quiz'! Lykke til :)\n");
                printBinaryQuiz(scanner, binaryRecordObjects);

                break;

            case "2":
                System.out.println("Du har valgt 'Flervelg Quiz'! Lykke til :)\n\n");
                //Print multichoice quiz.

                break;

            default:
                System.out.println("Input ikke godkjent.\n\n");
                inputError = true;

                break;
        }

        // Calls printStartMenu() again, if input is not valid
        if (inputError) {
            inputError = false;
            printStartMenu(scanner, setup, binaryRecordObjects, multichoiceRecordObjects);
        }
    }
    private static void printBinaryQuiz(Scanner scanner, ArrayList<BinaryRecordObject> binaryRecordObjects) {
        System.out.print(
                binaryRecordObjects.get(0).question() + "\n" +
                binaryRecordObjects.get(0).answerA() + "\n" +
                binaryRecordObjects.get(0).answerB() + "\n"
                );

    };

    public static void main(String[] args) {
        // Prepare the quiz
        Scanner scanner = new Scanner(System.in);
        SetupQuiz setup = new SetupQuiz();

        ArrayList<BinaryRecordObject> binaryRecordObjects = setup.getBinaryQuestionObjects();
        ArrayList<MultichoiceRecordObject> multichoiceRecordObjects = setup.getMultichoiceQuestionObjects();
        ArrayList<UserRecordObject> userObjects = setup.getUserObjects();

        setup.init();

        // Run the quiz.
        printStartMenu(scanner, setup, binaryRecordObjects, multichoiceRecordObjects);
        printBinaryQuiz(scanner, binaryRecordObjects);

    };
}