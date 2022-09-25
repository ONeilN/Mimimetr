create table Cat (
    id int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    name varchar(30) NOT NULL,
    img_name varchar NOT NULL,
    voices int
);

create table Pair (
    id int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    left_cat_id int,
    right_cat_id int
);

create table Guest (
    id int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    cookie_value varchar NOT NULL UNIQUE
);

create table Pair_Guest (
    pair_id int REFERENCES Pair(id) ON DELETE CASCADE,
    guest_id int REFERENCES Guest(id) ON DELETE CASCADE,
    PRIMARY KEY (pair_id, guest_id)
);

INSERT INTO Cat (name, img_name, voices) VALUES ('Мурка', '2_Мурка.jpg', 0);
INSERT INTO Cat (name, img_name, voices) VALUES ('Себастьян', '3_Себастьян.jpg', 0);
INSERT INTO Cat (name, img_name, voices) VALUES ('Дурбей', '5_Дурбей.jpg', 0);
INSERT INTO Cat (name, img_name, voices) VALUES ('Барсик', '1_Барсик.jpg', 0);
INSERT INTO Cat (name, img_name, voices) VALUES ('Муся', '4_Муся.jpg', 0);

INSERT INTO Pair (left_cat_id, right_cat_id) VALUES (1, 2);
INSERT INTO Pair (left_cat_id, right_cat_id) VALUES (1, 3);
INSERT INTO Pair (left_cat_id, right_cat_id) VALUES (1, 4);
INSERT INTO Pair (left_cat_id, right_cat_id) VALUES (1, 5);
INSERT INTO Pair (left_cat_id, right_cat_id) VALUES (2, 3);
INSERT INTO Pair (left_cat_id, right_cat_id) VALUES (2, 4);
INSERT INTO Pair (left_cat_id, right_cat_id) VALUES (2, 5);
INSERT INTO Pair (left_cat_id, right_cat_id) VALUES (3, 4);
INSERT INTO Pair (left_cat_id, right_cat_id) VALUES (3, 5);
INSERT INTO Pair (left_cat_id, right_cat_id) VALUES (4, 5);