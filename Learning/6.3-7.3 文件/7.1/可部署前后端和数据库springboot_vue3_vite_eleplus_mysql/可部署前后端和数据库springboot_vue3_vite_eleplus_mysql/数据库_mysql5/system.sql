/*
Navicat MySQL Data Transfer

Source Server         : fzsx
Source Server Version : 50067
Source Host           : localhost:3306
Source Database       : fzsx

Target Server Type    : MYSQL
Target Server Version : 50067
File Encoding         : 65001

Date: 2024-06-29 16:24:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menuId` int(11) NOT NULL auto_increment,
  `cname` varchar(254) default NULL,
  `ename` varchar(254) default NULL,
  `path` varchar(254) default NULL,
  `component` varchar(254) default NULL,
  `icon` varchar(254) default NULL,
  `pid` int(11) default NULL,
  `sort` int(11) default NULL,
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  PRIMARY KEY  (`menuId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '系统管理', 'usermanager', '/user', null, 'house', '6', '-1', '2024-06-19 16:01:36', '2024-06-19 16:01:39');
INSERT INTO `menu` VALUES ('3', '角色管理', 'rolemanage', '/role/list', '/机构管理', 'house', '1', '1', '2024-06-25 11:23:12', '2024-06-25 11:23:12');
INSERT INTO `menu` VALUES ('4', '用户管理', 'usermg', '/user/list', '/用户', 'Edit', '1', '2', '2024-06-27 08:33:14', '2024-06-27 08:33:14');
INSERT INTO `menu` VALUES ('5', '菜单管理', 'menumg', '/menu/list', '/菜单', 'house', '1', '0', '2024-06-27 08:33:52', '2024-06-27 08:33:52');
INSERT INTO `menu` VALUES ('6', '仿真系统', 'fzxt', '/', '/', 'Setting', '0', '-1', '2024-06-27 10:14:40', '2024-06-27 10:14:40');
INSERT INTO `menu` VALUES ('7', '设备管理', 'device', '/device', 'device', 'Setting', '6', '2', '2024-06-27 14:09:52', '2024-06-27 14:09:52');
INSERT INTO `menu` VALUES ('8', '设备新增', 'deviceAdd', '/menu/list', '/add', 'Setting', '7', '1', '2024-06-27 14:10:54', '2024-06-27 14:10:54');
INSERT INTO `menu` VALUES ('9', '首页', 'home', '/home', '/home', 'Setting', '6', '0', '2024-06-27 15:04:16', '2024-06-27 15:04:16');
INSERT INTO `menu` VALUES ('10', '设备删除', 'devicedel', '/devicel/list', '/devicel/list', 'Setting', '7', '2', '2024-06-27 16:53:23', '2024-06-27 16:53:23');

-- ----------------------------
-- Table structure for menu_role
-- ----------------------------
DROP TABLE IF EXISTS `menu_role`;
CREATE TABLE `menu_role` (
  `id` int(11) NOT NULL auto_increment,
  `menuId` int(11) default NULL,
  `roleId` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu_role
-- ----------------------------
INSERT INTO `menu_role` VALUES ('43', '7', '9');
INSERT INTO `menu_role` VALUES ('44', '8', '9');
INSERT INTO `menu_role` VALUES ('45', '10', '9');
INSERT INTO `menu_role` VALUES ('50', '1', '7');
INSERT INTO `menu_role` VALUES ('51', '3', '7');
INSERT INTO `menu_role` VALUES ('52', '4', '7');
INSERT INTO `menu_role` VALUES ('53', '5', '7');
INSERT INTO `menu_role` VALUES ('54', '7', '7');
INSERT INTO `menu_role` VALUES ('55', '8', '7');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleId` int(11) NOT NULL auto_increment,
  `roleName` varchar(254) default NULL,
  `description` varchar(254) default NULL,
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  PRIMARY KEY  (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('7', '学生', '管理员', '2024-06-26 09:50:44', '2024-06-26 09:50:44');
INSERT INTO `role` VALUES ('8', '老师', '后台管理', '2024-06-26 09:51:03', '2024-06-26 09:51:03');
INSERT INTO `role` VALUES ('9', '系统管理员', '后台管理', '2024-06-26 09:51:13', '2024-06-26 09:51:13');

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('3', '谢伟', 'xiewei2000', '38093071@qq.com', 'e10adc3949ba59abbe56e057f20f883e', '信息', '13547897970', '2024-06-17 08:59:15', null, '1');
INSERT INTO `user` VALUES ('4', '张三', 'zhangsan', 'zhangsan@qq.com', 'e10adc3949ba59abbe56e057f20f883e', 'zhangsanzhangsan', '13522222222', '2024-06-20 16:08:55', '2024-06-20 16:08:55', '0');
INSERT INTO `user` VALUES ('5', '李四', 'lisi', 'lisi@163.com', 'e10adc3949ba59abbe56e057f20f883e', 'lisilisi', '13666666666', '2024-06-20 16:09:52', '2024-06-20 16:09:52', '0');
INSERT INTO `user` VALUES ('6', 'lili', 'lili2000', 'lili2000@qq.com', 'e10adc3949ba59abbe56e057f20f883e', 'lili2000', '', '2024-06-20 16:14:12', '2024-06-20 16:14:12', '0');
INSERT INTO `user` VALUES ('7', 'xx', 'xx1', 'xx1', '4da803f3b74759f34efaf739b844e335', 'xx1', '', '2024-06-20 17:31:38', '2024-06-20 17:31:38', '0');
INSERT INTO `user` VALUES ('8', 'xx2', 'xx2', 'xx2', '4c4711ea06c811b55afe1017dc3f39dc', '', '', '2024-06-20 17:31:48', '2024-06-21 14:31:34', '1');
INSERT INTO `user` VALUES ('10', '管理员2', 'manager', 'manager@163.com', '63f1bc0912383e10b0a6dc0164eb40f5', '管理员', '13511111113', '2024-06-21 08:40:37', '2024-06-21 14:32:54', '1');
INSERT INTO `user` VALUES ('11', '1', '1', null, 'c4ca4238a0b923820dcc509a6f75849b', '', '', '2024-06-25 08:45:26', '2024-06-25 08:45:26', '0');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL auto_increment,
  `userId` int(11) default NULL,
  `roleId` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('3', '3', '7');
INSERT INTO `user_role` VALUES ('4', '3', '8');
INSERT INTO `user_role` VALUES ('5', '6', '7');
INSERT INTO `user_role` VALUES ('6', '11', '7');
