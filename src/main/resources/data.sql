INSERT INTO USER(name) VALUES('Ronaldo');
INSERT INTO USER(name) VALUES('Betano');
INSERT INTO USER(name) VALUES('Ciclano');
INSERT INTO USER(name) VALUES('Fulano');

INSERT INTO account (number, agency, purse, user_id) VALUES
    ( '13134','222', '10', (SELECT id from USER WHERE id=1) ),
    ( '13135','222', '10', (SELECT id from USER WHERE id=2) );