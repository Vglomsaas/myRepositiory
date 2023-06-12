import java.util.ArrayList;
import java.util.Scanner;

public class QuizGame {

    //# METHODS
    private static int run(
            Scanner scanner,
            SetupQuiz setup)
    {
        //# PREPARE TO PRINT
        ArrayList<BinaryRecordObject> binaryObjectsArraylist = setup.getBinaryObjectsArraylist();
        ArrayList<MultichoiceRecordObject> multichoiceObjectsArraylist = setup.getMultichoiceObjectsArraylist();
        ArrayList<UserRecordObject> userObjectsArraylist = setup.getUserObjectsArraylist();

        Boolean validInput = false;
        Boolean finishedBinaryQuiz = false;
        Boolean finishedMultichoiceQuiz = false;
        String name = "";

        int binaryScore = 0;
        int multiChoiceScore = 0;

        //# START CONSOLE PRINTING
        System.out.print("" +
                "\n\n------- Velkommen til 'Can You Quiz It?' -------\n\n\n" +

                "Hva er kallenavnet ditt? (Maks 3 tegn)\n"
        );

        // We use a while-loop to easier handle incorrect user input.
        while (!validInput) {
            name = scanner.nextLine(); // Name is treated as username, has to be unique.

            if (name.length() > 3) {
                System.out.printf("Kallenavn kan maks, være 3 tegn.");
                validInput = false;

            } else {
                validInput = true;

            }
        }

        validInput = false;

        // This if-statement checks if users have been registered before,
        // in other words if this is first-time-boot or not.
        if (setup.isFirstUser()) {
            //TODO: Write welcome message for 1st time boot.
            System.out.println(
                    "Hei %s, takk for at du ville prøve ut 'Can You Quiz It!'\n".formatted(name) +
                    "Er du klar for å ta top plasseringen på rangerings-lista? :v)\n"
            );

        } else {
            System.out.print(
                    "Så bra du er tilbake %s, lykke til!\n".formatted(name)
            );

        }

        System.out.print(
                "Velg hvilken quiz du vil ta. (Svar ved å skrive tallet på alternativet)\n" +
                        "(0) Se min highscore\n" +
                        "(1) Binær Quiz (Sant / Usant)\n" +
                        "(2) Flervalg Quiz (4 Alternativer)\n"
        );

        while (!validInput) {
            String menuChoice = scanner.nextLine();

            switch (menuChoice) {
                case "0":
                    validInput = true;

                    if (setup.isFirstUser()) {
                        System.out.println(
                                "Vi har ikke registrert en highscore på '%s' tidligere,\n" +
                                        "Spill for å få en score !\n");
                    } else {

                        //TODO: Print users highscore to console

                    }
                break;

                case "1":
                    validInput = true;
                    System.out.println("Du har valgt 'Binær Quiz'! Lykke til :v)\n");
                    binaryScore = printBinaryQuiz(scanner, binaryObjectsArraylist);
                    finishedBinaryQuiz = true;

                break;

                case "2":
                    validInput = true;
                    System.out.println("Du har valgt 'Flervalg Quiz'! Lykke til :v)\n");
                    //TODO: Print multichoice quiz.
                    finishedMultichoiceQuiz = true;

                break;

                default:
                    System.out.println("Input ikke godkjent. (Velg '0 / 1 / 2' for alternativet du ønsker.)\n\n");
                    validInput = false;

                break;
            }

            validInput = false;

            if (finishedBinaryQuiz && finishedMultichoiceQuiz) {

                //TODO: Handle both quizzes finished scenario.

            } else if (!finishedBinaryQuiz || !finishedMultichoiceQuiz) {
                System.out.print("Du har fullført 1/2 quizzer! Vil du ta en til?\n");

                if (!finishedBinaryQuiz) {
                    System.out.println("" +
                            "(1) Start Binær Quiz.\n" +
                            "(2) Lagre nåværende score."
                    );

                }

                if (!finishedMultichoiceQuiz) {
                    System.out.println(
                            "(1) Start Flervalg Quiz.\n" +
                                    "(2) Lagre nåværende score."
                    );
                }

                while (!validInput) {
                    String input = scanner.nextLine();

                    switch (input) {
                        case "1":
                            if (!finishedBinaryQuiz) {
                                validInput = true;
                                binaryScore = printBinaryQuiz(scanner, binaryObjectsArraylist);

                            } else if (!finishedMultichoiceQuiz) {
                                //TODO: Print multichoice quiz;

                            }

                        break;

                        case "2":
                            validInput = true;
                            //TODO: Handle one quiz finished scenario.

                        break;

                        default:
                            validInput = false;
                            System.out.print("Input ikke godkjent. (Velg '1 / 2' for alternativet du ønsker.)\n\n");

                        break;
                    }
                }
            }
        }

        return binaryScore + multiChoiceScore;
    }

    private static int printBinaryQuiz(
            Scanner scanner,
            ArrayList<BinaryRecordObject> binaryObjectsArraylist)
    {
        int score = 0;
        Boolean validInput = false;

        // while-loops are used here to better handle wrong user input.
        while (!validInput) {
            System.out.print(
                    binaryObjectsArraylist.get(0).question() + "\n" +
                    "(1)" + binaryObjectsArraylist.get(0).answerA() + "\n" +
                    "(2)" + binaryObjectsArraylist.get(0).answerB() + "\n"
            );

            String input = scanner.nextLine();

            if (input == "2") {
                // Correct answer, increments score by 1.
                score++;
                validInput = true;

            } else if (input == "1") {
                // Wrong answer, prints next question.
                validInput = true;

            } else {
                // Not valid input, re-runs this loop.
                validInput = false;
                System.out.println("OBS!: '%s', er ikke et gyldig svar. (skriv '1' eller '2' for å velge alternativ.)\n");
            }
        }

        validInput = false;

        while (!validInput) {
            System.out.print(
                    binaryObjectsArraylist.get(1).question() + "\n" +
                            "(1)" + binaryObjectsArraylist.get(1).answerA() + "\n" +
                            "(2)" + binaryObjectsArraylist.get(1).answerB() + "\n"
            );

            String input = scanner.nextLine();

            if (input == "2") {
                // Correct answer, increments score by 1.
                score++;
                validInput = true;

            } else if (input == "1") {
                // Wrong answer, prints next question.
                validInput = true;

            } else {
                // Not valid input, re-runs this loop.
                validInput = false;
                System.out.println("OBS!: '%s', er ikke et gyldig svar. (skriv '1' eller '2' for å velge alternativ.)\n");
            }
        }

        return score;
    };

    public static void main(String[] args) {
        //# PREPARE QUIZ
        Scanner scanner = new Scanner(System.in);
        SetupQuiz setup = new SetupQuiz();

        int totalScore = 0;

        ArrayList<BinaryRecordObject> binaryObjectsArraylist = setup.getBinaryObjectsArraylist();
        ArrayList<MultichoiceRecordObject> multichoiceObjectsArraylist = setup.getMultichoiceObjectsArraylist();
        ArrayList<UserRecordObject> userObjectsArraylist = setup.getUserObjectsArraylist();

        setup.init();

        // RUN QUIZ
        totalScore = run(
                scanner, // reads input from user
                setup
        );

    };
}