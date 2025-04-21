/*
Navicat MySQL Data Transfer

Source Server         : fzsx
Source Server Version : 50067
Source Host           : localhost:3306
Source Database       : fzsx

Target Server Type    : MYSQL
Target Server Version : 50067
File Encoding         : 65001

Date: 2024-06-28 09:25:21
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
INSERT INTO `menu` VALUES ('1', '系统管理', 'usermanager', '/user', null, 'Setting', '6', '0', '2024-06-19 16:01:36', '2024-06-19 16:01:39');
INSERT INTO `menu` VALUES ('3', '角色管理', 'JGGL', '/role/list', '/机构管理', 'house', '1', '1', '2024-06-25 11:23:12', '2024-06-25 11:23:12');
INSERT INTO `menu` VALUES ('4', '用户管理', 'usermg', '/user/list', '/用户', 'Setting', '1', '2', '2024-06-27 08:33:14', '2024-06-27 08:33:14');
INSERT INTO `menu` VALUES ('5', '菜单管理', 'menumg', '/menu/list', '/菜单', 'house', '1', '0', '2024-06-27 08:33:52', '2024-06-27 08:33:52');
INSERT INTO `menu` VALUES ('6', '仿真系统', 'fzxt', '/', '/', '', '0', '-1', '2024-06-27 10:14:40', '2024-06-27 10:14:40');
INSERT INTO `menu` VALUES ('7', '设备管理', 'device', '/device/list', 'device', '1', '6', '2', '2024-06-27 14:09:52', '2024-06-27 14:09:52');
INSERT INTO `menu` VALUES ('8', '设备新增', 'deviceAdd', '/device/add', '/add', '', '7', '1', '2024-06-27 14:10:54', '2024-06-27 14:10:54');
INSERT INTO `menu` VALUES ('9', '首页', 'home', '/home', '/home', '', '6', '0', '2024-06-27 15:04:16', '2024-06-27 15:04:16');
INSERT INTO `menu` VALUES ('10', '设备删除', 'devicedel', '/devicel/list', '/devicel/list', '', '7', '2', '2024-06-27 16:53:23', '2024-06-27 16:53:23');
