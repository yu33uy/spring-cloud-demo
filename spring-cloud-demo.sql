/*
 Navicat Premium Data Transfer

 Source Server         : 0.4
 Source Server Type    : MySQL
 Source Server Version : 50635
 Source Host           : 192.168.0.4
 Source Database       : spring-cloud-demo

 Target Server Type    : MySQL
 Target Server Version : 50635
 File Encoding         : utf-8

 Date: 06/19/2017 17:55:45 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `role`
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES ('1', 'ROLE_ADMIN'), ('2', 'ROLE_USER'), ('3', 'ACTUATOR');
COMMIT;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', 'frank', '$2a$10$Sn2ko4CbSGDtQjCydWTmluzpGIHvKDh6zcv5JIwwGscXPGZwLntau'), ('3', 'admin', '$2a$10$Ow8Y283h7sR2fQOtjeA1LuUfQgDKJVZ9t9bgvWJ43FTTtjzFYbK0u');
COMMIT;

-- ----------------------------
--  Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `user_role`
-- ----------------------------
BEGIN;
INSERT INTO `user_role` VALUES ('1', '1', '1'), ('2', '1', '2'), ('3', '2', '3');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
