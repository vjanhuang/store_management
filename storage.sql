/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : localhost:3306
 Source Schema         : storage

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 09/07/2023 10:02:58

*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '物品名称',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '物品编号',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '物品F', '899651', 'http://localhost:9090/file/QQ%E6%88%AA%E5%9B%BE20230418223942.png');
INSERT INTO `goods` VALUES (2, '物品E', '899652', 'http://localhost:9090/file/QQ%E6%88%AA%E5%9B%BE20230418223956.png');
INSERT INTO `goods` VALUES (3, '物品D', '899653', 'http://localhost:9090/file/QQ%E6%88%AA%E5%9B%BE20230418224053.png');
INSERT INTO `goods` VALUES (4, '物品C', '899654', 'http://localhost:9090/file/QQ%E6%88%AA%E5%9B%BE20230418224031.png');
INSERT INTO `goods` VALUES (5, '物品B', '899655', 'http://localhost:9090/file/Rry-GtunLmHxNCFuMX7KbQ.jpg');
INSERT INTO `goods` VALUES (6, '物品A', '899656', 'http://localhost:9090/file/0be7ee38d992093f.jpg');

-- ----------------------------
-- Table structure for stash
-- ----------------------------
DROP TABLE IF EXISTS `stash`;
CREATE TABLE `stash`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '仓库名称',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '仓库编号',
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '仓库区域',
  `manager` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '仓库管理员',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stash
-- ----------------------------
INSERT INTO `stash` VALUES (1, '仓库A', '8559974', '厂房A区', '张芬');
INSERT INTO `stash` VALUES (2, '仓库B', '522896', '厂房B区', '李鑫');
INSERT INTO `stash` VALUES (3, '仓库C', '966823', '厂房Y区', '小黑');

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '仓库名称',
  `code` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '仓库编号',
  `goods` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '物品名称',
  `goodscode` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '物品编号',
  `num` int(11) NULL DEFAULT 0 COMMENT '库存量',
  `date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` VALUES (21, '仓库A', '8559974', '物品F', '899651', 9, '2023-07-09');
INSERT INTO `store` VALUES (22, '仓库B', '522896', '物品E', '899652', 10, NULL);

-- ----------------------------
-- Table structure for storein
-- ----------------------------
DROP TABLE IF EXISTS `storein`;
CREATE TABLE `storein`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '仓库名称',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '仓库编号',
  `goods` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '物品名称',
  `goodscode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '物品编号',
  `num` int(11) NULL DEFAULT 0 COMMENT '库存量',
  `date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of storein
-- ----------------------------
INSERT INTO `storein` VALUES (8, '仓库A', '8559974', '物品F', '899651', 10, '2023-07-09');
INSERT INTO `storein` VALUES (9, '仓库B', '522896', '物品E', '899652', 10, '2023-07-09');

-- ----------------------------
-- Table structure for storeout
-- ----------------------------
DROP TABLE IF EXISTS `storeout`;
CREATE TABLE `storeout`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '仓库名称',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '仓库编号',
  `goods` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '物品名称',
  `goodscode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '物品编号',
  `num` int(11) NULL DEFAULT 0 COMMENT '库存量',
  `date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of storeout
-- ----------------------------
INSERT INTO `storeout` VALUES (4, '仓库A', '8559974', '物品F', '899651', 1, '2023-07-09');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `birth` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '生日',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `disable` tinyint(1) NULL DEFAULT 0 COMMENT '禁用',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'admin', '管理员', NULL, NULL, NULL, NULL, 'http://localhost:9090/file/beer1.jpg', 0, 'ADMIN');
INSERT INTO `user` VALUES (2, 'gbb', '123', '小可爱587434号', '男', '1993-10-1', '13699885599', '安徽合肥', 'http://localhost:9090/file/%E5%BE%AE%E4%BF%A1%E6%88%AA%E5%9B%BE_20220706191248.png', 0, 'USER');
INSERT INTO `user` VALUES (3, 'acc', '123', '阿卡丽', '男', '1998-10-8', '13655228899', '北京1', NULL, 0, 'USER');

SET FOREIGN_KEY_CHECKS = 1;
