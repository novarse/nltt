SET DB_CLOSE_DELAY -1;         
;              
CREATE USER IF NOT EXISTS SA SALT 'fe88336efe2e145c' HASH '4b436e937e0844cf6532370dff036b854678de64313b49bd608ea258a8fc3ff8' ADMIN;            
CREATE MEMORY TABLE PUBLIC.PERSON(
    ID INT NOT NULL,
    SURNAME VARCHAR(30),
    FIRSTNAME VARCHAR(30),
    PHONE VARCHAR(30),
    EMAIL VARCHAR(50)
);    
ALTER TABLE PUBLIC.PERSON ADD CONSTRAINT PUBLIC.CONSTRAINT_8 PRIMARY KEY(ID);  
-- 4 +/- SELECT COUNT(*) FROM PUBLIC.PERSON;   
INSERT INTO PUBLIC.PERSON(ID, SURNAME, FIRSTNAME, PHONE, EMAIL) VALUES(101, 'Daily', 'John', '+61403456789', 'jd@gmail.com');  
INSERT INTO PUBLIC.PERSON(ID, SURNAME, FIRSTNAME, PHONE, EMAIL) VALUES(102, 'Jones', 'Phil', '+61404332679', 'pj@gmail.com');  
INSERT INTO PUBLIC.PERSON(ID, SURNAME, FIRSTNAME, PHONE, EMAIL) VALUES(103, 'Grill', 'Bill', '+61406756677', 'bg@gmail.com');  
INSERT INTO PUBLIC.PERSON(ID, SURNAME, FIRSTNAME, PHONE, EMAIL) VALUES(104, 'Clyde', 'Bonnie', '+61416756687', 'bc@gmail.com');  
CREATE MEMORY TABLE PUBLIC.BOOK(
    ID INT NOT NULL,
    TITLE VARCHAR(255),
    AUTHOR VARCHAR(60),
    ISBN LONG
);         
ALTER TABLE PUBLIC.BOOK ADD CONSTRAINT PUBLIC.CONSTRAINT_1 PRIMARY KEY(ID);    
-- 6 +/- SELECT COUNT(*) FROM PUBLIC.BOOK;     
INSERT INTO PUBLIC.BOOK(ID, TITLE, AUTHOR, ISBN) VALUES(300, 'The Conservative Mind', 'Russell Kirk', 9780895261717);          
INSERT INTO PUBLIC.BOOK(ID, TITLE, AUTHOR, ISBN) VALUES(301, 'The Road to Serfdom', 'F. A. Hayek', 9780226320618);             
INSERT INTO PUBLIC.BOOK(ID, TITLE, AUTHOR, ISBN) VALUES(302, 'Meditations', 'Marcus Aurelius', 9780486298238); 
INSERT INTO PUBLIC.BOOK(ID, TITLE, AUTHOR, ISBN) VALUES(303, 'I, Pencil', 'Leonard Read', 9781572462090);      
INSERT INTO PUBLIC.BOOK(ID, TITLE, AUTHOR, ISBN) VALUES(304, 'Conscience and Its Enemies', 'Robert P. George', 9781610170703); 
INSERT INTO PUBLIC.BOOK(ID, TITLE, AUTHOR, ISBN) VALUES(305, 'Econoclasts', 'Brian Domitrovic', 9781610170246);
INSERT INTO PUBLIC.BOOK(ID, TITLE, AUTHOR, ISBN) VALUES(306, 'Through the Looking-Glass', 'Lewis Carroll', 9785457280250);
INSERT INTO PUBLIC.BOOK(ID, TITLE, AUTHOR, ISBN) VALUES(307, 'Metamagical Themas', 'Douglas Hofstadter', 9780140179965);
CREATE MEMORY TABLE PUBLIC.BOOK_DETAIL(
    ID INT NOT NULL,
    BOOKID INT,
    DETAIL CLOB
);
ALTER TABLE PUBLIC.BOOK_DETAIL ADD CONSTRAINT PUBLIC.CONSTRAINT_F PRIMARY KEY(ID);             
-- 3 +/- SELECT COUNT(*) FROM PUBLIC.BOOK_DETAIL;              
INSERT INTO PUBLIC.BOOK_DETAIL(ID, BOOKID, DETAIL) VALUES(500, 300, STRINGDECODE('Russell Kirk''s The Conservative Mind is one of the greatest contributions to twentieth-century American conservatism.\nBrilliant in every respect, from its conception to its choice of significant figures representing the history of intellectual conservatism, The Conservative Mind launched the modern American Conservative Movement when it was first published in 1953 and has become an enduring classic of political thought.'));
INSERT INTO PUBLIC.BOOK_DETAIL(ID, BOOKID, DETAIL) VALUES(501, 304, STRINGDECODE('\"Many in elite circles yield to the temptation to believe that anyone who disagrees with them is a bigot or a religious fundamentalist. Reason and science, they confidently believe, are on their side. With this book, I aim to expose the emptiness of that belief.\"\nFrom the introduction: Assaults on religious liberty and traditional morality are growing fiercer. Here, at last, is the counterattack.\nShowcasing the talents that have made him one of America''s most acclaimed and influential thinkers, Robert P. George explodes the myth that the secular elite represents the voice of reason. In fact, George shows, it is on the elite side of the cultural divide where the prevailing views frequently are nothing but articles of faith. Conscience and Its Enemies reveals the bankruptcy of these too often smugly held orthodoxies while presenting powerfully reasoned arguments for classical virtues.'));     
INSERT INTO PUBLIC.BOOK_DETAIL(ID, BOOKID, DETAIL) VALUES(502, 305, 'The definitive history of supply-side economics the most consequential economic counterrevolution of the twentieth century and an incredibly timely work that reveals the foundations of America''s prosperity at a time when those very foundations are under attack.'); 
CREATE MEMORY TABLE PUBLIC.BORROW(
    PERSONID INT,
    BOOKID INT
);           
-- 6 +/- SELECT COUNT(*) FROM PUBLIC.BORROW;     
INSERT INTO PUBLIC.BORROW(PERSONID, BOOKID) VALUES(101, 300);    
INSERT INTO PUBLIC.BORROW(PERSONID, BOOKID) VALUES(101, 301);    
INSERT INTO PUBLIC.BORROW(PERSONID, BOOKID) VALUES(102, 302);    
INSERT INTO PUBLIC.BORROW(PERSONID, BOOKID) VALUES(102, 303);    
INSERT INTO PUBLIC.BORROW(PERSONID, BOOKID) VALUES(102, 304);    
INSERT INTO PUBLIC.BORROW(PERSONID, BOOKID) VALUES(103, 305); 
