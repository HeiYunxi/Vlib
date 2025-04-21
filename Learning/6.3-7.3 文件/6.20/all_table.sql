/*
Navicat MySQL Data Transfer

Source Server         : fzsx
Source Server Version : 50067
Source Host           : localhost:3306
Source Database       : fzsx

Target Server Type    : MYSQL
Target Server Version : 50067
File Encoding         : 65001

Date: 2024-06-20 11:25:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menu_id` int(11) NOT NULL,
  `cname` varchar(254) default NULL,
  `ename` varchar(254) default NULL,
  `path` varchar(254) default NULL,
  `component` varchar(254) default NULL,
  `icon` varchar(254) default NULL,
  `pid` int(11) default NULL,
  `sort` int(11) default NULL,
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  PRIMARY KEY  (`menu_id`)
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
  `menu_id` int(11) default NULL,
  `roleId` int(11) default NULL,
  PRIMARY KEY  (`id`)
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
  `roleName` varchar(254) default NULL,
  `description` varchar(254) default NULL,
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  PRIMARY KEY  (`roleId`)
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
  `userId` int(11) NOT NULL auto_increment,
  `userName` varchar(254) default NULL,
  `account` varchar(254) default NULL,
  `email` varchar(254) default NULL,
  `password` varchar(254) default NULL,
  `intro` varchar(254) default NULL,
  `phone` varchar(254) default NULL,
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  `type` int(11) default NULL,
  PRIMARY KEY  (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('3', '谢伟', 'xiewei2000', '38093071@qq.com', 'e10adc3949ba59abbe56e057f20f883e', '信息', '13547897970', '2024-06-17 08:59:15', null, '1');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL,
  `userId` int(11) default NULL,
  `roleId` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '3', '1');
