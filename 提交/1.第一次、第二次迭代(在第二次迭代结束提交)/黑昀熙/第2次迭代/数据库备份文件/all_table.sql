/*
Navicat MySQL Data Transfer

Source Server         : vlib
Source Server Version : 50513
Source Host           : localhost:3306
Source Database       : vlib

Target Server Type    : MYSQL
Target Server Version : 50513
File Encoding         : 65001

Date: 2024-06-24 19:28:02
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
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(254) DEFAULT NULL,
  `description` varchar(254) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '学生', '学生', '2024-06-20 08:27:03', '2024-06-20 08:27:06');
INSERT INTO `role` VALUES ('2', '教师', '教师', '2024-06-21 21:25:38', null);
INSERT INTO `role` VALUES ('3', '管理员', '管理员', '2024-06-21 21:25:47', null);
INSERT INTO `role` VALUES ('6', '社会人士', '社会人士', '2024-06-24 08:16:17', null);
INSERT INTO `role` VALUES ('7', '12', '12', '2024-06-24 08:29:26', '2024-06-24 08:54:47');

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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '张三', 'zhangsan3', 'zhangsan3', 'zhangsan3', '张三', '13333333331', '2024-06-18 17:06:12', null, null);
INSERT INTO `user` VALUES ('2', '李四', 'lisi4', 'lisi4', 'lisi4', 'lisi4', '14444444444', '2024-06-18 17:06:37', null, null);
INSERT INTO `user` VALUES ('3', '谢伟', 'xiewei2000', '38093071@qq.com', 'e10adc3949ba59abbe56e057f20f883e', '信息', '13547897970', '2024-06-17 08:59:15', null, '1');
INSERT INTO `user` VALUES ('4', 'admin', 'admin', 'admin@email.com', '21232f297a57a5a743894a0e4a801fc3', 'admin', '18563993663', '2024-06-20 16:34:36', null, '3');
INSERT INTO `user` VALUES ('5', 'test', 'test', 'test', '81dc9bdb52d04dc20036dbd8313ed055', 'test', '13333333333', '2024-06-20 16:45:44', null, '1');
INSERT INTO `user` VALUES ('6', '梧桐', 'wutong2002', '2075245546@qq.com', '81dc9bdb52d04dc20036dbd8313ed055', '梧桐', '18563993663', '2024-06-20 17:04:56', null, '3');
INSERT INTO `user` VALUES ('8', 'new', 'new', '1122', '81dc9bdb52d04dc20036dbd8313ed055', '1234', '1234', '2024-06-21 08:42:37', '2024-06-21 14:36:56', '3');
INSERT INTO `user` VALUES ('9', '昀熙', 'YUNXIHEI', '11', '81dc9bdb52d04dc20036dbd8313ed055', '昀熙', '18563993663', '2024-06-21 10:54:54', '2024-06-21 14:37:51', '2');
INSERT INTO `user` VALUES ('10', '铃芽', 'ly123', 'ly123', '8b43bec391cda3d89b2db2d6e3a3a169', 'ly123', '15555555555', '2024-06-24 14:16:41', null, '1');
INSERT INTO `user` VALUES ('11', '学生1', 'xuesheng1', 'xuesheng1@email.com', '4a40093279b9a1f05ef1c03832091cb4', 'xuesheng1', '15555555555', '2024-06-24 14:17:53', null, '1');
INSERT INTO `user` VALUES ('12', '测试分页', 'ceshifenye', 'ceshifenye', '9f79aa444d0f6738ebc2f9e1a92335d6', 'ceshifenye', 'ceshifenye', '2024-06-24 15:29:31', null, '1');
INSERT INTO `user` VALUES ('13', '', 'xuesheng1', 'xuesheng1@email.com', '202cb962ac59075b964b07152d234b70', 'xuesheng1', '15555555555', '2024-06-24 16:15:00', null, '1');
INSERT INTO `user` VALUES ('14', '黑昀熙', '123', '', '202cb962ac59075b964b07152d234b70', '123', '123', '2024-06-24 16:16:08', null, '1');
INSERT INTO `user` VALUES ('15', 'as', 'test', '', '7815696ecbf1c96e6894b779456d330e', '', '', '2024-06-24 16:19:26', null, '1');

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
