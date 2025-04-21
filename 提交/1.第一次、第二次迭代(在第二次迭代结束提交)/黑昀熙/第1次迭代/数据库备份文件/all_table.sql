/*
Navicat MySQL Data Transfer

Source Server         : vlib
Source Server Version : 50513
Source Host           : localhost:3306
Source Database       : vlib

Target Server Type    : MYSQL
Target Server Version : 50513
File Encoding         : 65001

Date: 2024-06-21 09:01:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menu_id` int(11) NOT NULL,
  `cname` varchar(254) DEFAULT NULL,
  `ename` varchar(254) DEFAULT NULL,
  `path` varchar(254) DEFAULT NULL,
  `component` varchar(254) DEFAULT NULL,
  `icon` varchar(254) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '用户管理', 'usermanager', '/user', null, null, '0', '0', '2024-06-19 16:01:36', '2024-06-19 16:01:39');

-- ----------------------------
-- Table structure for menu_role
-- ----------------------------
DROP TABLE IF EXISTS `menu_role`;
CREATE TABLE `menu_role` (
  `id` int(11) NOT NULL,
  `menu_id` int(11) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu_role
-- ----------------------------
INSERT INTO `menu_role` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleId` int(11) NOT NULL,
  `roleName` varchar(254) DEFAULT NULL,
  `description` varchar(254) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '学生', '学生', '2024-06-20 08:27:03', '2024-06-20 08:27:06');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(254) DEFAULT NULL,
  `account` varchar(254) DEFAULT NULL,
  `email` varchar(254) DEFAULT NULL,
  `password` varchar(254) DEFAULT NULL,
  `intro` varchar(254) DEFAULT NULL,
  `phone` varchar(254) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '张三', 'zhangsan3', 'zhangsan3', 'zhangsan3', '张三', '13333333331', '2024-06-18 17:06:12', null, null);
INSERT INTO `user` VALUES ('2', '李四', 'lisi4', 'lisi4', 'lisi4', 'lisi4', '14444444444', '2024-06-18 17:06:37', null, null);
INSERT INTO `user` VALUES ('3', '谢伟', 'xiewei2000', '38093071@qq.com', 'e10adc3949ba59abbe56e057f20f883e', '信息', '13547897970', '2024-06-17 08:59:15', null, '1');
INSERT INTO `user` VALUES ('4', 'admin', 'admin', 'admin@email.com', '21232f297a57a5a743894a0e4a801fc3', 'admin', '18563993663', '2024-06-20 16:34:36', null, '3');
INSERT INTO `user` VALUES ('5', 'test', 'test', 'test', '81dc9bdb52d04dc20036dbd8313ed055', 'test', '13333333333', '2024-06-20 16:45:44', null, '1');
INSERT INTO `user` VALUES ('6', '梧桐', 'wutong2002', '2075245546@qq.com', '81dc9bdb52d04dc20036dbd8313ed055', '梧桐', '18563993663', '2024-06-20 17:04:56', null, '3');
INSERT INTO `user` VALUES ('7', '', '1234', '1234', '81dc9bdb52d04dc20036dbd8313ed055', '1234', '1234', '2024-06-21 08:41:11', null, '2');
INSERT INTO `user` VALUES ('8', 'new', 'new', '11', '81dc9bdb52d04dc20036dbd8313ed055', '1234', '1234', '2024-06-21 08:42:37', null, '3');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '3', '1');
