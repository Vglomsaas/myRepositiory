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
    id int UNIQUE NOT NULL AUTO_INCREMENT,
    question VARCHAR(256) UNIQUE,
    answerA VARCHAR(64),
    answerB VARCHAR(64),
    answerC VARCHAR(64),
    answerD VARCHAR(64),
    correctAnswer VARCHAR(64)
);

INSERT INTO multichoiceQuiz (question, answerA, answerB, answerC, answerD, correctAnswer)
VALUES (
            'Hvilke to farger kombinert skaper lilla?',
            'Rød og Blå',
            'Blå og Grønn',
            'Gul og Rød',
            'Rød og Grønn',
            'Rød og Blå'
        );

INSERT INTO multichoiceQuiz (question, answerA, answerB, answerC, answerD, correctAnswer)
VALUES (
           'Hva er 1 + 1?',
           '3',
           '5',
           '1',
           '2',
           '2'
       );

INSERT INTO multichoiceQuiz (question, answerA, answerB, answerC, answerD, correctAnswer)
VALUES (
           'Hvilken karrakter ville jeg fått på denne eksamen dersom det var gradering?',
           'D',
           'A',
           'B',
           'F',
           'A'
       );

/* CREATING BINARY TABLE AND INSERTING QUESTIONS */
CREATE TABLE binaryQuiz (
    id int UNIQUE NOT NULL AUTO_INCREMENT,
    question VARCHAR(256) UNIQUE,
    answerA VARCHAR(64),
    answerB VARCHAR(64),
    correctAnswer VARCHAR(64)
);

INSERT INTO binaryQuiz (question, answerA, answerB, correctAnswer)
VALUES (
            'Kongen av Norge heter Halvord til fornavn.',
            'Sant',
            'Usant',
            'Usant'
        );

INSERT INTO binaryQuiz (question, answerA, answerB, correctAnswer)
VALUES (
           'Norge feirer nasjonaldag den 8 Mai.',
           'Sant',
           'Usant',
           'Usant'
        );

/* CREATING USER TABLE TO STORE HIGHSCORE */
CREATE TABLE users (
    id int UNIQUE NOT NULL AUTO_INCREMENT,
    name VARCHAR(3) UNIQUE,
    highscore int
);