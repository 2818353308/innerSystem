/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50527
Source Host           : 127.0.0.1:3306
Source Database       : innersystem

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2017-07-16 14:04:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `simplecode`
-- ----------------------------
DROP TABLE IF EXISTS `simplecode`;
CREATE TABLE `simplecode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(6) DEFAULT NULL,
  `codetype` varchar(6) DEFAULT NULL,
  `value` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of simplecode
-- ----------------------------
INSERT INTO `simplecode` VALUES ('1', '100001', '100', '普通用户');
INSERT INTO `simplecode` VALUES ('2', '100002', '100', '管理员');
INSERT INTO `simplecode` VALUES ('3', '100003', '100', '超级管理员');

-- ----------------------------
-- Table structure for `sys_author_resources`
-- ----------------------------
DROP TABLE IF EXISTS `sys_author_resources`;
CREATE TABLE `sys_author_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `rorder` double DEFAULT NULL,
  `createTs` timestamp NULL DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_author_resources
-- ----------------------------
INSERT INTO `sys_author_resources` VALUES ('1', '权限管理', '', null, '1', '2017-07-07 17:13:01', '权限管理');
INSERT INTO `sys_author_resources` VALUES ('2', '资源管理', '/res/toList.htmlx', '1', '2', '2017-07-07 17:14:00', '资源管理');
INSERT INTO `sys_author_resources` VALUES ('3', '角色管理', '/role/toList.htmlx', '1', '3', '2017-07-07 17:14:55', '角色管理');
INSERT INTO `sys_author_resources` VALUES ('4', '用户管理', '/user/toList.htmlx', '1', '4', '2017-07-15 09:46:46', '用户角色管理');
INSERT INTO `sys_author_resources` VALUES ('5', '简单代码管理', '/code/toList.htmlx', null, '5', '2017-07-16 14:04:19', '简单代码管理');

-- ----------------------------
-- Table structure for `sys_author_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_author_role`;
CREATE TABLE `sys_author_role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(50) DEFAULT NULL,
  `createTs` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `orderBy` double DEFAULT NULL,
  `note` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_author_role
-- ----------------------------
INSERT INTO `sys_author_role` VALUES ('1', '权限管理员', '2017-07-07 17:15:24', '1', '配置权限');
INSERT INTO `sys_author_role` VALUES ('2', '角色管理员', '2017-07-07 17:16:33', '2', '角色管理');

-- ----------------------------
-- Table structure for `sys_author_role_resources`
-- ----------------------------
DROP TABLE IF EXISTS `sys_author_role_resources`;
CREATE TABLE `sys_author_role_resources` (
  `resourcesId` int(11) NOT NULL DEFAULT '0',
  `roleId` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`resourcesId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_author_role_resources
-- ----------------------------
INSERT INTO `sys_author_role_resources` VALUES ('1', '1');
INSERT INTO `sys_author_role_resources` VALUES ('2', '1');
INSERT INTO `sys_author_role_resources` VALUES ('3', '1');
INSERT INTO `sys_author_role_resources` VALUES ('3', '2');

-- ----------------------------
-- Table structure for `sys_author_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_author_user_role`;
CREATE TABLE `sys_author_user_role` (
  `userId` varchar(32) NOT NULL DEFAULT '',
  `roleId` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_author_user_role
-- ----------------------------
INSERT INTO `sys_author_user_role` VALUES ('1', '1');
INSERT INTO `sys_author_user_role` VALUES ('2', '1');
INSERT INTO `sys_author_user_role` VALUES ('2', '2');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` varchar(32) NOT NULL,
  `uname` varchar(21) DEFAULT NULL,
  `upass` varchar(32) DEFAULT NULL,
  `userType` varchar(6) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `state` varchar(6) DEFAULT NULL,
  `registerDate` datetime DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `province` varchar(6) DEFAULT NULL,
  `city` varchar(6) DEFAULT NULL,
  `area` varchar(6) DEFAULT NULL,
  `detail` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '11', '11', '100001', '2818353308@qq.com', '100001', '2017-07-07 09:06:58', '15225452111', '100001', '2222', '222', '邢台');
INSERT INTO `user` VALUES ('2', '22', '22', '100002', null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('3', '33', '33', '100003', null, null, null, null, null, null, null, null);
