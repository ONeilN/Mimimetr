RDBMS: PostgreSQL
DB name: mimimetr_db
DB username: postgres
DB password: password

DB restore file: MimiMetr\mimimetr_db.backup 
or use MimiMetr\src\main\resources\static\sql\create_data.sql

Web Application start URL: "/"

Please do the following before running the application:

    1) deploy the database using the mimimetr_db.backup file;
    2) create a folder in a place convenient for you to store images;
    3) copy all images from resources\static\img\cats folder to created folder;
    4) set the path to the created folder as the value of the image.upload.path
       property in the application.properties file.