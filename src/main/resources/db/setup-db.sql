create database ileiwe;

create user 'ileiwe_user'@'localhost'identified by 'ileiwe123';
grant all privileges on ileiwe.* to 'ileiwe_user'@'localhost';
flush privileges ;