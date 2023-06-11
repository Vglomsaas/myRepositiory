CREATE DATABASE quizDB;
/*
    Added UNIQUE to questions column in DB so that if this query
    is run more than once, it won't duplicate questions in
    the DB.
*/

/* CREATING USER TO CONNECT TO THE DATABASE */
CREATE USER 'user' IDENTIFIED BY 'password';
GRANT ALL ON QuizDB.* TO 'user';

USE quizDB;

/* CREATING MULTICHOICE TABLE AND INSERTING QUESTIONS */
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

/* CREATING BINARY TABLE AND INSERTING QUESTIONS */
CREATE TABLE binaryQuiz (
    id int,
    question VARCHAR(64) UNIQUE,
    answerA VARCHAR(64),
    answerB VARCHAR(64),
    correctAnswer VARCHAR(64)
);

INSERT INTO binaryQuiz (id, question, answerA, answerB, correctAnswer)
VALUES (0, 'Kongen av Norge heter Halvord til fornavn. (j/n)', 'ja', 'nei', 'nei');

/* CREATING USER TABLE TO STORE HIGHSCORE */
CREATE TABLE users (
    id int,
    name VARCHAR(64),
    highscore int
);