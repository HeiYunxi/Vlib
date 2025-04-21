/*
 Navicat Premium Data Transfer

 Source Server         : cqsx
 Source Server Type    : MySQL
 Source Server Version : 50067
 Source Host           : localhost:3306
 Source Schema         : fzsx

 Target Server Type    : MySQL
 Target Server Version : 50067
 File Encoding         : 65001

 Date: 12/07/2024 15:50:34
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for apply
-- ----------------------------
DROP TABLE IF EXISTS `apply`;
CREATE TABLE `apply`  (
  `applyId` int(11) NOT NULL AUTO_INCREMENT,
  `applyNum` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `applyDay` int(11) NULL DEFAULT NULL,
  `applyReason` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `applyStatus` int(11) NULL DEFAULT NULL,
  `applyDatetime` datetime NULL DEFAULT NULL,
  `reviewComments` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reviewDatetime` datetime NULL DEFAULT NULL,
  `userId` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`applyId`)
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of apply
-- ----------------------------
INSERT INTO `apply` VALUES (1, '12432', 11, '无', 1, '2012-01-02 00:00:00', '不同意', '2012-02-02 00:00:00', '5');
INSERT INTO `apply` VALUES (2, '13456', 2, '无', 3, '2012-02-23 00:00:00', '', '2012-03-04 00:00:00', '6');
INSERT INTO `apply` VALUES (3, '65454', 5, '无', 2, '2013-02-03 00:00:00', '同意', '2012-02-06 00:00:00', '6');
INSERT INTO `apply` VALUES (4, '12332', 3, '无', 1, '2024-07-09 08:42:11', '不同意', NULL, '7');
INSERT INTO `apply` VALUES (25, 'Wed Jul 10 10:20:49 CST 2024', 2, '实验', 2, '2024-07-09 00:00:00', '同意', '2024-07-12 10:23:58', '7');
INSERT INTO `apply` VALUES (26, 'Wed Jul 10 10:23:53 CST 2024', 1, '教学', 1, '2024-07-23 00:00:00', '不同意', '2024-07-10 15:35:58', '8');
INSERT INTO `apply` VALUES (27, 'Wed Jul 10 11:06:21 CST 2024', 1, '1', 3, '2024-07-09 00:00:00', '', NULL, '8');
INSERT INTO `apply` VALUES (28, 'Wed Jul 10 11:24:31 CST 2024', 1, '1', 1, '2024-07-26 00:00:00', '不同意', '2024-07-10 15:16:26', '9');
INSERT INTO `apply` VALUES (29, 'Wed Jul 10 11:24:53 CST 2024', 3, '3', 2, '2024-07-26 00:00:00', '同意', '2024-07-10 15:00:12', '9');
INSERT INTO `apply` VALUES (30, 'Wed Jul 10 14:28:03 CST 2024', 1, '1', 3, '2024-07-09 00:00:00', '', NULL, '9');
INSERT INTO `apply` VALUES (31, 'Thu Jul 11 19:36:00 CST 2024', 1, '1', 0, '2015-04-08 00:00:00', '', NULL, '9');
INSERT INTO `apply` VALUES (32, 'Thu Jul 11 19:36:11 CST 2024', 1, '1', 0, '2015-04-08 00:00:00', '', NULL, '9');
INSERT INTO `apply` VALUES (33, '1720751011621', 5, '学习', 3, '2023-07-12 00:00:00', '', NULL, '9');
INSERT INTO `apply` VALUES (34, '1720768621540', 4, '学习', 2, '2024-07-12 00:00:00', '同意', '2024-07-12 15:18:21', '11');

-- ----------------------------
-- Table structure for apply_lab
-- ----------------------------
DROP TABLE IF EXISTS `apply_lab`;
CREATE TABLE `apply_lab`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `applyId` int(11) NULL DEFAULT NULL,
  `labId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of apply_lab
-- ----------------------------
INSERT INTO `apply_lab` VALUES (1, 2, 2);
INSERT INTO `apply_lab` VALUES (2, 1, 1);
INSERT INTO `apply_lab` VALUES (3, 3, 1);
INSERT INTO `apply_lab` VALUES (5, 19, 1);
INSERT INTO `apply_lab` VALUES (6, 20, 1);
INSERT INTO `apply_lab` VALUES (7, 21, 1);
INSERT INTO `apply_lab` VALUES (11, 25, 3);
INSERT INTO `apply_lab` VALUES (12, 26, 3);
INSERT INTO `apply_lab` VALUES (13, 27, 3);
INSERT INTO `apply_lab` VALUES (14, 28, 3);
INSERT INTO `apply_lab` VALUES (15, 29, 3);
INSERT INTO `apply_lab` VALUES (16, 30, 1);
INSERT INTO `apply_lab` VALUES (17, 33, 1);
INSERT INTO `apply_lab` VALUES (18, 34, 3);

-- ----------------------------
-- Table structure for equipment
-- ----------------------------
DROP TABLE IF EXISTS `equipment`;
CREATE TABLE `equipment`  (
  `equipmentId` int(11) NOT NULL AUTO_INCREMENT,
  `equipmentName` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `equipmentNumber` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `software_system` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `software_version` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `supplier` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(1) NULL DEFAULT NULL,
  `purpose` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `thumbnail` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`equipmentId`)
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of equipment
-- ----------------------------
INSERT INTO `equipment` VALUES (3, '计算机', '001', 1, 'office', '10', '3', 1, '教学', '1', '2024-06-28 14:05:56', '2024-07-12 14:52:41');
INSERT INTO `equipment` VALUES (4, '数据库服务器', 'SJK1', 1, 'Windows', '3.0', '1', 2, '教学', '1', '2024-06-29 08:08:42', '2024-07-12 14:52:57');
INSERT INTO `equipment` VALUES (5, '台式电脑', 'TSDN1', 1, 'Linux', '5.1', '3', 2, '实验', '3', '2024-06-29 08:19:48', '2024-07-12 14:53:14');
INSERT INTO `equipment` VALUES (6, '一体机', 'YTJ123', 1, 'windows', 'v11', '1', 1, '学习', '', '2024-07-11 20:41:13', '2024-07-12 14:53:20');
INSERT INTO `equipment` VALUES (7, 'VR设备', '123456', 2, 'MAC', '10', '4', 1, 'VR', '', '2024-07-12 14:44:02', '2024-07-12 14:53:24');
INSERT INTO `equipment` VALUES (8, 'AR设备', '88888', 2, 'MAC', '20', '1', 1, 'AR', '', '2024-07-12 14:54:10', '2024-07-12 14:54:10');

-- ----------------------------
-- Table structure for equipment_lab
-- ----------------------------
DROP TABLE IF EXISTS `equipment_lab`;
CREATE TABLE `equipment_lab`  (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `labId` int(11) NULL DEFAULT NULL,
  `equipmentId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`Id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of equipment_lab
-- ----------------------------

-- ----------------------------
-- Table structure for equipment_supplier
-- ----------------------------
DROP TABLE IF EXISTS `equipment_supplier`;
CREATE TABLE `equipment_supplier`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `equipmentId` int(11) NULL DEFAULT NULL,
  `supplierId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of equipment_supplier
-- ----------------------------
INSERT INTO `equipment_supplier` VALUES (44, 3, 3);
INSERT INTO `equipment_supplier` VALUES (45, 4, 1);
INSERT INTO `equipment_supplier` VALUES (46, 5, 3);
INSERT INTO `equipment_supplier` VALUES (47, 6, 1);
INSERT INTO `equipment_supplier` VALUES (48, 7, 4);
INSERT INTO `equipment_supplier` VALUES (49, 8, 1);

-- ----------------------------
-- Table structure for lab
-- ----------------------------
DROP TABLE IF EXISTS `lab`;
CREATE TABLE `lab`  (
  `labId` int(11) NOT NULL AUTO_INCREMENT,
  `labNumber` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `labName` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `position` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `capacity` int(11) NULL DEFAULT NULL,
  `managerId` int(11) NULL DEFAULT NULL,
  `area` double NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`labId`)
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of lab
-- ----------------------------
INSERT INTO `lab` VALUES (1, '1000', '计算机实验室', 'A101', 40, 3, 100, '2024-07-08 15:19:12', '2024-07-12 10:49:12');
INSERT INTO `lab` VALUES (2, '1111', '保护伞实验室', 'B409', 30, 1, 11, '2024-07-09 15:52:11', '2024-07-12 10:59:23');
INSERT INTO `lab` VALUES (3, '2000', '软件开发实验室', 'B403', 30, 9, 70, '2024-07-10 10:15:50', '2024-07-10 10:15:50');
INSERT INTO `lab` VALUES (4, '40000', '防火墙实验室', 'A509', 50, 3, 1000, '2024-07-11 14:37:19', '2024-07-12 10:49:33');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menuId` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ename` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `path` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `component` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pid` int(11) NULL DEFAULT NULL,
  `sort` int(11) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`menuId`)
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '数据可视化', 'DataView', '/data/view', '/data/view', 'star', 6, 0, '2024-07-11 15:16:34', NULL);
INSERT INTO `menu` VALUES (2, '系统管理', 'usermanager', '/user', NULL, 'Setting', 6, 0, '2024-06-19 16:01:36', '2024-06-19 16:01:39');
INSERT INTO `menu` VALUES (3, '用户管理', 'usermg', '/user/list', '/用户', 'Setting', 2, 1, '2024-06-27 08:33:14', '2024-06-27 08:33:14');
INSERT INTO `menu` VALUES (4, '角色管理', 'JGGL', '/role/list', '/机构管理', 'house', 2, 2, '2024-06-25 11:23:12', '2024-06-25 11:23:12');
INSERT INTO `menu` VALUES (5, '菜单管理', 'menumg', '/menu/list', '/菜单', 'house', 2, 3, '2024-06-27 08:33:52', '2024-06-27 08:33:52');
INSERT INTO `menu` VALUES (6, '仿真系统', 'fzxt', '/', '/', 'Setting', 0, -1, '2024-06-27 10:14:40', '2024-06-27 10:14:40');
INSERT INTO `menu` VALUES (7, '资源管理', 'SimulationEquipment', '/equipment', '/', 'Setting', 6, 2, '2024-06-27 14:09:52', '2024-06-27 14:09:52');
INSERT INTO `menu` VALUES (10, '设备管理', 'SimulationEquipmentManage', '/equipment/list', '/', 'Setting', 7, 1, '2024-06-29 15:48:04', '2024-06-29 15:48:06');
INSERT INTO `menu` VALUES (11, '实验室管理', 'LabManage', '/lab/list', '/', 'house', 7, 2, '2024-06-29 15:48:39', '2024-06-29 15:48:42');
INSERT INTO `menu` VALUES (12, '供应商管理', 'SupplierManage', '/supplier/list', '/', 'house', 7, 3, '2024-07-01 14:53:06', '2024-07-01 14:53:08');
INSERT INTO `menu` VALUES (13, '实验室', 'Lab', '/lab', '/', 'house', 6, 1, NULL, NULL);
INSERT INTO `menu` VALUES (14, '实验室列表', 'Lablist', '/lab/labList', '/', NULL, 13, 2, NULL, NULL);
INSERT INTO `menu` VALUES (15, '申请', 'apply', '/lab/apply', '/', NULL, 13, 3, NULL, NULL);
INSERT INTO `menu` VALUES (16, '实验室借用审批', 'Review', '/lab/approve', '/', NULL, 7, 4, NULL, NULL);

-- ----------------------------
-- Table structure for menu_role
-- ----------------------------
DROP TABLE IF EXISTS `menu_role`;
CREATE TABLE `menu_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menuId` int(11) NULL DEFAULT NULL,
  `roleId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 166 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of menu_role
-- ----------------------------
INSERT INTO `menu_role` VALUES (78, 6, 8);
INSERT INTO `menu_role` VALUES (79, 7, 8);
INSERT INTO `menu_role` VALUES (80, 10, 8);
INSERT INTO `menu_role` VALUES (81, 11, 8);
INSERT INTO `menu_role` VALUES (82, 12, 8);
INSERT INTO `menu_role` VALUES (83, 9, 8);
INSERT INTO `menu_role` VALUES (137, 6, 3);
INSERT INTO `menu_role` VALUES (138, 1, 3);
INSERT INTO `menu_role` VALUES (139, 2, 3);
INSERT INTO `menu_role` VALUES (140, 3, 3);
INSERT INTO `menu_role` VALUES (141, 4, 3);
INSERT INTO `menu_role` VALUES (142, 5, 3);
INSERT INTO `menu_role` VALUES (143, 7, 3);
INSERT INTO `menu_role` VALUES (144, 10, 3);
INSERT INTO `menu_role` VALUES (145, 11, 3);
INSERT INTO `menu_role` VALUES (146, 12, 3);
INSERT INTO `menu_role` VALUES (147, 16, 3);
INSERT INTO `menu_role` VALUES (148, 13, 3);
INSERT INTO `menu_role` VALUES (149, 14, 3);
INSERT INTO `menu_role` VALUES (150, 15, 3);
INSERT INTO `menu_role` VALUES (151, 17, 3);
INSERT INTO `menu_role` VALUES (152, 6, 1);
INSERT INTO `menu_role` VALUES (153, 1, 1);
INSERT INTO `menu_role` VALUES (154, 13, 1);
INSERT INTO `menu_role` VALUES (155, 14, 1);
INSERT INTO `menu_role` VALUES (156, 15, 1);
INSERT INTO `menu_role` VALUES (157, 6, 2);
INSERT INTO `menu_role` VALUES (158, 1, 2);
INSERT INTO `menu_role` VALUES (159, 7, 2);
INSERT INTO `menu_role` VALUES (160, 10, 2);
INSERT INTO `menu_role` VALUES (161, 11, 2);
INSERT INTO `menu_role` VALUES (162, 12, 2);
INSERT INTO `menu_role` VALUES (163, 16, 2);
INSERT INTO `menu_role` VALUES (164, 13, 2);
INSERT INTO `menu_role` VALUES (165, 14, 2);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`roleId`)
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '学生', '学生', '2024-06-20 08:27:03', '2024-07-09 16:44:50');
INSERT INTO `role` VALUES (2, '教师', '教师', '2024-06-21 21:25:38', NULL);
INSERT INTO `role` VALUES (3, '管理员', '管理员', '2024-06-21 21:25:47', NULL);

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier`  (
  `supplierId` int(11) NOT NULL AUTO_INCREMENT,
  `supplierName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `contact` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`supplierId`)
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES (1, '学创杯公司', '小刘', '12345678900', '770121568@qq.com', ' 南山街道 崇文路21号 ', '2202-01-06 00:00:00', '2024-07-12 15:08:50');
INSERT INTO `supplier` VALUES (3, '韦子烨有限公司', '韦子烨', '17395280329', 'weiziye@heiyunxi.com', '韦子烨路12号3楼', '2024-07-08 14:29:56', '2024-07-12 15:09:04');
INSERT INTO `supplier` VALUES (4, '龙龙韦子烨有限公司', '龙越', '15086612846', 'longyue@ll.com', '南山街道 崇文路2号 重庆邮电大学', '2024-07-09 11:13:14', '2024-07-12 15:08:03');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `account` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `intro` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`userId`)
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三', 'zhangsan3', 'zhangsan3@163.com', '408c06609ccabfc09e76f1807156d01c', '张三', '13333333336', '2024-06-18 17:06:12', '2024-06-29 15:13:26', 2);
INSERT INTO `user` VALUES (2, '李四', 'lisi4', 'lisi4@163.com', '408c06609ccabfc09e76f1807156d01c', 'lisi4', '14444444444', '2024-06-18 17:06:37', NULL, 2);
INSERT INTO `user` VALUES (3, '谢伟', 'xiewei2000', '38093071@qq.com', '408c06609ccabfc09e76f1807156d01c', '信息', '13547897970', '2024-06-17 08:59:15', NULL, 3);
INSERT INTO `user` VALUES (4, '傅可豪', '2232725178', '2232725178@qq.com', '408c06609ccabfc09e76f1807156d01c', '小组成员', '18563993663', '2024-06-20 16:34:36', NULL, 1);
INSERT INTO `user` VALUES (5, '龙越', '2944381624', '2944381624@qq.com', '408c06609ccabfc09e76f1807156d01c', '小组成员', '12233334444', '2024-06-20 16:45:44', NULL, 1);
INSERT INTO `user` VALUES (6, '韦子烨', '770632299', '770632299@qq.com', '408c06609ccabfc09e76f1807156d01c', '小组成员', '12266667777', '2024-06-20 17:04:56', NULL, 1);
INSERT INTO `user` VALUES (7, '楚子琦', '1152175645', '1152175645@qq.com', '408c06609ccabfc09e76f1807156d01c', '小组成员', '14455556666', '2024-06-21 10:54:54', '2024-07-01 15:18:11', 1);
INSERT INTO `user` VALUES (8, '黑昀熙', '2075245546', '2075245546@qq.com', '408c06609ccabfc09e76f1807156d01c', '小组成员', '18563993663', '2024-06-24 16:16:08', NULL, 1);
INSERT INTO `user` VALUES (9, '1', '1', '1@1.com', 'c4ca4238a0b923820dcc509a6f75849b', '1', '13333333333', '2024-06-25 14:01:16', NULL, 3);
INSERT INTO `user` VALUES (10, '小明', 'xiaoming', 'xiaoming@163.com', '408c06609ccabfc09e76f1807156d01c', '我是小明', '15866668888', '2024-07-12 09:24:49', '2024-07-12 09:24:49', 0);
INSERT INTO `user` VALUES (11, '小红', 'xiaohong', 'xiaohong@163.com', '408c06609ccabfc09e76f1807156d01c', '我是小红', '18888888888', '2024-07-12 15:15:16', '2024-07-12 15:15:16', 1);
INSERT INTO `user` VALUES (12, '小兰', 'xiaolan', 'xiaolan@22.com', '408c06609ccabfc09e76f1807156d01c', '我是小兰', '13355556666', '2024-07-12 15:43:38', '2024-07-12 15:43:38', 1);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NULL DEFAULT NULL,
  `roleId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 179 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (160, 6, 1);
INSERT INTO `user_role` VALUES (168, 7, 1);
INSERT INTO `user_role` VALUES (169, 4, 1);
INSERT INTO `user_role` VALUES (170, 5, 1);
INSERT INTO `user_role` VALUES (171, 8, 1);
INSERT INTO `user_role` VALUES (172, 3, 3);
INSERT INTO `user_role` VALUES (173, 2, 2);
INSERT INTO `user_role` VALUES (174, 1, 2);
INSERT INTO `user_role` VALUES (175, 9, 3);
INSERT INTO `user_role` VALUES (176, 10, 1);
INSERT INTO `user_role` VALUES (177, 11, 1);
INSERT INTO `user_role` VALUES (178, 12, 1);

SET FOREIGN_KEY_CHECKS = 1;
