# myRepository
The Project is to build simple twitter type application, This help me learning concepts of angular js and spring boot along with postgres...
The following things are required to run this project...
IdE used Intellij IDEA 
Database used Postges
SetUp Instructions Edit applicaton. properties file accordingly,update username and password of database with your postgres username and password.
Type following in postgres command line 
CREATE DATABASE mydb;
CREATE SEQUENCE hibernate_sequence INCREMENT 1 start 1;
Create postgres tables client, follow and tweets with following details- 
CREATE TABLE client ( email character varying(255) NOT NULL, follows character varying(255), gender character varying(255), name character varying(255), password character varying(255), phone character varying(255), image character varying(255), CONSTRAINT client_pkey PRIMARY KEY (email) ); 
CREATE TABLE follow ( id serial NOT NULL, email character varying(255), follows character varying(255), CONSTRAINT follow_pkey PRIMARY KEY (id) );
CREATE TABLE tweets ( email character(255) NOT NULL, date timestamp without time zone NOT NULL, tweet character(5555), likes integer, CONSTRAINT tweets_pkey PRIMARY KEY (email, date) );
