# myRepository
<h2>Technologies Used</h2>
<ul><li>Spring Boot</li>
<li>Angular JS</li>
<li>Postgres</li>
<li>Html/Css</li></ul>
The Project is to build simple twitter type application,<br> This help me learning concepts of angular js and spring boot along with postgres...<br>
The following things are required to run this project...<br>
IdE used Intellij IDEA <br>
Database used Postges<br>
<h2>SetUp Instructions</h2><br> 
<ul>
<li>Edit applicaton.properties file accordingly,update username and password of database with your postgres username and password.</li>
<li>Type following in postgres command line <br>
CREATE DATABASE mydb;<br>
CREATE SEQUENCE hibernate_sequence INCREMENT 1 start 1;<br></li>
<li>
Create postgres tables client, follow and tweets with following details-  </li>
<p>
CREATE TABLE client ( email character varying(255) NOT NULL, follows character varying(255), gender character varying(255), name character varying(255), password character varying(255), phone character varying(255), image character varying(255), CONSTRAINT client_pkey PRIMARY KEY (email) ); </p>
<p>
CREATE TABLE follow ( id serial NOT NULL, email character varying(255), follows character varying(255), CONSTRAINT follow_pkey PRIMARY KEY (id) );</p><p>
CREATE TABLE tweets ( email character(255) NOT NULL, date timestamp without time zone NOT NULL, tweet character(5555), likes integer, CONSTRAINT tweets_pkey PRIMARY KEY (email, date) );</p>
