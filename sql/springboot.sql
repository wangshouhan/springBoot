/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : springboot

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-01-12 10:49:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `user_name` varchar(10) NOT NULL,
  `pass_word` varchar(100) NOT NULL,
  `color` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'wang', '$2a$10$EpgvrJnasmZNZo1hpMx6VOUiBBEP4oCr1t9nRj6eGKbCiXCodXqPK', '#ff4a87');
INSERT INTO `user` VALUES ('2', 'admin', '$2a$10$EpgvrJnasmZNZo1hpMx6VOUiBBEP4oCr1t9nRj6eGKbCiXCodXqPK', '#ff4a87');
