create database ileiwe if not exists iwedb;

create user if not exists 'ileiwe_user'@'localhost'identified by 'ileiwe123';
grant all privileges on ileiwe.* to 'ileiwe_user'@'localhost';
flush privileges ;