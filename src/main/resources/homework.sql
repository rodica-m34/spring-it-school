CREATE DATABASE Twitter;

CREATE TABLE Users (
                       user_id SERIAL PRIMARY KEY,
                       username VARCHAR(50) NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       password VARCHAR(100) NOT NULL
);

CREATE TABLE Addresses (
                           address_id SERIAL PRIMARY KEY,
                           user_id INT REFERENCES Users (user_id),
                           street_name VARCHAR(255) NOT NULL,
                           city VARCHAR(50) NOT NULL,
                           zip_code VARCHAR(10)
);

CREATE TABLE Messages (
                          message_id SERIAL PRIMARY KEY,
                          user_id INT REFERENCES Users(user_id),
                          context TEXT,
                          timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO Users (username, email, password)
VALUES ('ion_popescu','ion.popescu@gmail.com','parola1!'),
       ('maria_marin','maria.marin@gmail.com','maria123!'),
       ('mihai_ionascu','mihai.ionascu@gmail.com','mihaita99?');

-- 6. Specific select: Retrieve only the usernames and emails of all users from the Users table.
SELECT username, email FROM users;

INSERT INTO Users (username, email, password)
VALUES ('ada_joita','ada.joita@yahoo.com','blabla129');

-- 7. Conditional select: Retrieve users from the Users table who have email addresses ending with "@example.com".
SELECT username, email FROM users where email LIKE '%gmail.com';

-- 8. Insert with reference: Insert an address for one of the users you added.
-- Make sure to link it to the user using the user_id field in the Addresses table
INSERT INTO Addresses (user_id, street_name, city, zip_code)
VALUES (2, 'Garofitei','Bacau','1100');

SELECT *FROM Addresses;

SELECT u.username, u.email, a.street_name, a.city, a.zip_code FROM Users u
INNER JOIN Addresses on u.user_id = a.user_id;

-- 9.Update with condition: Change all messages in the Messages table with the word "hello"
-- in their content to "Hello World!".

ALTER TABLE Messages
    RENAME COLUMN context TO content;

INSERT INTO Messages (user_id, content)
VALUES (2, 'Hello!');

INSERT INTO Messages (user_id, content)
VALUES (2, 'Hello');

INSERT INTO Messages (user_id, content)
VALUES (3, 'Hello:)');

SELECT u.username, u.email, m.content, m.timestamp FROM Users u
INNER JOIN Messages on m.user_id = u.user_id;

UPDATE messages SET content = 'Hello World' where content LIKE 'Hello%';

-- 10.Delete with JOIN: Delete all addresses associated with users who have an email address ending with "@example.com".
-- (This one introduces them to using JOIN with DELETE).
INSERT INTO Addresses (user_id, street_name, city, zip_code)
VALUES (3, 'Zambilei','Vaslui','2200'),
       (4, 'Panselutei','Craiova','1300'),
       (5, 'Crinului','Constanta','5403');

DELETE FROM Addresses as a
WHERE user_id IN (SELECT user_id from USERS WHERE email LIKE '%@yahoo.com');

SELECT u.username, u.email, a.street_name, a.city, a.zip_code FROM Users u
INNER JOIN Addresses on u.user_id = a.user_id;

-- 11. Aggregate function: Count the number of messages each user has sent and order the
-- result by the number of messages in descending order.
INSERT INTO Messages (user_id, content)
VALUES
    (2, 'Have you seen the news?'),
    (3, 'It is raining'),
    (4, 'It is raining'),
    (5, 'Hello');

SELECT u.username,
COUNT(m.message_id) as meesage_count
FROM users u
LEFT JOIN messages m on u.user_id = m.user_id
GROUP BY u.username
ORDER BY meesage_count DESC;

-- 13. Find all messages sent in the last 7 days.
SELECT * FROM Messages m
WHERE m."timestamp">=CURRENT_DATE - INTERVAL '7 days'
ORDER BY m."timestamp";

-- 14. Find all users whose usernames start with 'john'.
SELECT u.username FROM users as u
WHERE u.username LIKE 'john%';

-- 15. Find the total number of users from each city using the Addresses table.
SELECT a.city,
COUNT(u.user_id) as user_count
FROM Addresses as a
LEFT JOIN users u on u.user_id = a.user_id
GROUP BY a.city
ORDER BY user_count DESC;

-- 18. Retrieve all addresses sorted by city in ascending order and then by street_name in descending order
SELECT *
FROM addresses a
ORDER BY a.city,
a.street_name DESC;

-- 19. Find all unique cities from the Addresses table.
SELECT DISTINCT city FROM addresses;

-- 21. Find users who have not sent any messages.
SELECT u.username FROM users as u
LEFT JOIN messages as m on u.user_id = m.user_id
WHERE m."content" IS NULL or m."content"=''

