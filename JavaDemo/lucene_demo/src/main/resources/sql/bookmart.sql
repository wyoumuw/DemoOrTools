/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : bookmart

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-03-29 14:13:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', 'java编程思想', '《Java编程思想》是2007年由机械工业出版社出版的图书，该书作者是埃克尔，译者是陈昊鹏。');
INSERT INTO `book` VALUES ('2', '大话java', '《大话Java》是2009年出版的图书，作者是黄彬华。 内容介绍《大活Java:从零基础到数据库、Web开发》以漫画的形式，由浅入深、循序渐进地介绍Java编程的常用技术和方法，内容涵盖了Java基本语法结构、面向对象特征、集合框架体系、异常处理、GUI编程、MySQL数据库、JDBC数据库编程、Servlet、JSPWeb开...');
