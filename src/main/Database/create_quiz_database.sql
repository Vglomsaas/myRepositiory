CREATE DATABASE quizDB;
/*
    Added UNIQUE to questions column in DB so that if this query
    is run more than once, it won't duplicate questions in
    the DB.
*/

CREATE USER 'user' IDENTIFIED BY 'password';
GRANT ALL ON QuizDB.* TO 'user';

USE quizDB;

CREATE TABLE multichoiceQuiz (
    id int,
    question VARCHAR(64) UNIQUE,
    answerA VARCHAR(64),
    answerB VARCHAR(64),
    answerC VARCHAR(64),
    answerD VARCHAR(64),
    correctAnswer VARCHAR(64)
);

INSERT INTO multichoiceQuiz (id, question, answerA, answerB, answerC, answerD, correctAnswer)
VALUES (0, 'Hvilke to farger kombinert skaper lilla?', 'Rød og Blå', 'Blå og Grønn', 'Gul og Rød', 'Rød og Grønn', 'Rød og Blå');

CREATE TABLE binaryQuiz (
    id int,
    question VARCHAR(64) UNIQUE,
    answerA VARCHAR(64),
    answerB VARCHAR(64),
    correctAnswer VARCHAR(64)
);

INSERT INTO binaryQuiz (id, question, answerA, answerB, correctAnswer)
VALUES (0, 'Kongen av Norge heter Halvord til fornavn. (j/n)', 'ja', 'nei', 'nei');