/*
 Navicat Premium Data Transfer

 Source Server         : localhost：3306
 Source Server Type    : MySQL
 Source Server Version : 50717 (5.7.17-log)
 Source Host           : localhost:3306
 Source Schema         : room

 Target Server Type    : MySQL
 Target Server Version : 50717 (5.7.17-log)
 File Encoding         : 65001

 Date: 23/08/2023 14:04:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------

-- Table structure for blacklist

-- ----------------------------

DROP TABLE IF EXISTS `blacklist`;
CREATE TABLE `blacklist`  (
  `blackID` int(11) NOT NULL AUTO_INCREMENT COMMENT '黑名单编号',
  `userID` int(11) NOT NULL,
  `beginTime` datetime NULL DEFAULT NULL,
  `cause` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`blackID`) USING BTREE,
  INDEX `userID`(`userID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8011 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '黑名单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------

-- Records of blacklist

-- ----------------------------

INSERT INTO `blacklist` VALUES (8003, 1130, '2020-03-19 21:46:07', '故意损坏公共物资', '冻结');
INSERT INTO `blacklist` VALUES (8004, 1131, '2020-03-21 21:46:29', '长时间未取消预约', '冻结');
INSERT INTO `blacklist` VALUES (8005, 1136, '2020-03-21 22:31:41', '长时间未取消预约', '已解除');
INSERT INTO `blacklist` VALUES (8006, 1200, '2020-03-29 20:05:29', '故意损坏公共物资', '已解除');
INSERT INTO `blacklist` VALUES (8007, 1180, '2020-03-30 17:05:59', '经常打扰他人学习', '冻结');
INSERT INTO `blacklist` VALUES (8008, 1181, '2020-03-30 17:06:23', '长时间未取消预约', '冻结');
INSERT INTO `blacklist` VALUES (8009, 1182, '2020-03-27 17:06:38', '长时间未取消预约', '已解除');
INSERT INTO `blacklist` VALUES (8010, 1183, '2020-03-31 20:04:14', '长时间未取消预约', '冻结');

-- ----------------------------

-- Table structure for complain

-- ----------------------------

DROP TABLE IF EXISTS `complain`;
CREATE TABLE `complain`  (
  `cpID` int(11) NOT NULL AUTO_INCREMENT COMMENT '投诉编号',
  `userID` int(11) NOT NULL,
  `cpDate` datetime NULL DEFAULT NULL,
  `cpText` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ps` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`cpID`) USING BTREE,
  INDEX `userID`(`userID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7010 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '投诉表' ROW_FORMAT = DYNAMIC;

-- ----------------------------

-- Records of complain

-- ----------------------------

INSERT INTO `complain` VALUES (7001, 1001, '2020-03-19 21:07:22', '环境不好', '已处理');
INSERT INTO `complain` VALUES (7002, 1002, '2020-03-25 10:57:17', '很吵', '已处理');
INSERT INTO `complain` VALUES (7006, 1131, '2020-03-26 13:41:34', '空气不新鲜', '已处理');
INSERT INTO `complain` VALUES (7007, 1134, '2020-03-26 14:41:49', '环境较吵', '未处理');
INSERT INTO `complain` VALUES (7008, 1140, '2020-03-18 17:29:32', '环境差', '未处理');
INSERT INTO `complain` VALUES (7009, 1141, '2020-03-19 17:30:02', '声音嘈杂', '未处理');

-- ----------------------------

-- Table structure for feedback

-- ----------------------------

DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`  (
  `fdID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `cpID` int(11) NOT NULL,
  `fdTime` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `fdText` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`fdID`) USING BTREE,
  INDEX `cpID`(`cpID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11144 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '反馈表' ROW_FORMAT = DYNAMIC;

-- ----------------------------

-- Records of feedback

-- ----------------------------

INSERT INTO `feedback` VALUES (11115, 1001, 7001, '2020-04-22 21:07:56', '尽快处理');
INSERT INTO `feedback` VALUES (11126, 1001, 7001, '2020-04-22 21:09:30', '会采取相应措施');
INSERT INTO `feedback` VALUES (11127, 1131, 7006, '2020-04-22 21:09:48', '已采取措施');
INSERT INTO `feedback` VALUES (11128, 1131, 7006, '2020-04-22 21:09:50', '已采取措施');
INSERT INTO `feedback` VALUES (11129, 1134, 7007, '2020-04-22 21:09:52', '已采取措施');
INSERT INTO `feedback` VALUES (11130, 1001, 7001, '2020-04-22 21:10:07', '我们将尽快处理');
INSERT INTO `feedback` VALUES (11131, 1131, 7006, '2020-04-22 21:09:57', '已采取措施');
INSERT INTO `feedback` VALUES (11132, 1134, 7007, '2020-04-22 21:09:32', '会采取相应措施');
INSERT INTO `feedback` VALUES (11133, 1140, 7008, '2020-04-22 21:10:08', '我们将尽快处理');
INSERT INTO `feedback` VALUES (11134, 1140, 7008, '2020-04-22 21:09:54', '已采取措施');
INSERT INTO `feedback` VALUES (11135, 1002, 7002, '2020-04-22 21:09:34', '会采取相应措施');
INSERT INTO `feedback` VALUES (11136, 1140, 7008, '2020-04-22 21:10:10', '我们将尽快处理');
INSERT INTO `feedback` VALUES (11137, 1001, 7001, '2020-04-22 21:09:36', '会采取相应措施');
INSERT INTO `feedback` VALUES (11138, 1001, 7001, '2020-04-22 21:10:12', '我们将尽快处理');
INSERT INTO `feedback` VALUES (11139, 1002, 7002, '2020-04-01 20:55:06', '好的，会积极采取措施处理的！');
INSERT INTO `feedback` VALUES (11140, 1001, 7001, '2020-04-22 21:09:38', '会采取相应措施');
INSERT INTO `feedback` VALUES (11141, 1002, 7002, '2020-05-08 22:35:02', '尽快处理！！！');
INSERT INTO `feedback` VALUES (11142, 1001, 7001, '2023-05-13 02:32:22', '已经处理完毕');
INSERT INTO `feedback` VALUES (11143, 1131, 7006, '2023-05-13 02:32:35', '已处理');

-- ----------------------------

-- Table structure for manager

-- ----------------------------

DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager`  (
  `managerID` int(11) NOT NULL AUTO_INCREMENT,
  `managerName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `managerTel` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `managerPwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`managerID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1115 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员账号表' ROW_FORMAT = DYNAMIC;

-- ----------------------------

-- Records of manager

-- ----------------------------

INSERT INTO `manager` VALUES (1111, 'admin1', '13212333311', '123456');
INSERT INTO `manager` VALUES (1113, '2014490001', '13347164368', '123456');

-- ----------------------------

-- Table structure for room

-- ----------------------------

DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `roomID` int(11) NOT NULL AUTO_INCREMENT COMMENT '自习室编号',
  `seatCount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '座位总数',
  `searRate` float NULL DEFAULT NULL,
  PRIMARY KEY (`roomID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4007 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '自习室表' ROW_FORMAT = DYNAMIC;

-- ----------------------------

-- Records of room

-- ----------------------------

INSERT INTO `room` VALUES (4001, '8', NULL);
INSERT INTO `room` VALUES (4002, '8', NULL);
INSERT INTO `room` VALUES (4003, '8', NULL);
INSERT INTO `room` VALUES (4004, '8', NULL);
INSERT INTO `room` VALUES (4005, '8', NULL);
INSERT INTO `room` VALUES (4006, '1', NULL);

-- ----------------------------

-- Table structure for seat

-- ----------------------------

DROP TABLE IF EXISTS `seat`;
CREATE TABLE `seat`  (
  `seatID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `roomID` int(11) NULL DEFAULT NULL,
  `seatState` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`seatID`) USING BTREE,
  INDEX `roomID`(`roomID`) USING BTREE,
  CONSTRAINT `seat_ibfk_1` FOREIGN KEY (`roomID`) REFERENCES `room` (`roomID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3050 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '座位表' ROW_FORMAT = DYNAMIC;

-- ----------------------------

-- Records of seat

-- ----------------------------

INSERT INTO `seat` VALUES (3001, 4001, '2');
INSERT INTO `seat` VALUES (3002, 4002, '0');
INSERT INTO `seat` VALUES (3003, 4001, '1');
INSERT INTO `seat` VALUES (3004, 4002, '0');
INSERT INTO `seat` VALUES (3005, 4001, '1');
INSERT INTO `seat` VALUES (3006, 4002, '0');
INSERT INTO `seat` VALUES (3007, 4003, '0');
INSERT INTO `seat` VALUES (3008, 4004, '0');
INSERT INTO `seat` VALUES (3009, 4005, '0');
INSERT INTO `seat` VALUES (3010, 4005, '0');
INSERT INTO `seat` VALUES (3011, 4001, '0');
INSERT INTO `seat` VALUES (3012, 4001, '0');
INSERT INTO `seat` VALUES (3013, 4002, '0');
INSERT INTO `seat` VALUES (3014, 4003, '0');
INSERT INTO `seat` VALUES (3015, 4003, '0');
INSERT INTO `seat` VALUES (3016, 4004, '0');
INSERT INTO `seat` VALUES (3017, 4004, '0');
INSERT INTO `seat` VALUES (3018, 4005, '0');
INSERT INTO `seat` VALUES (3019, 4006, '0');
INSERT INTO `seat` VALUES (3020, 4006, '0');
INSERT INTO `seat` VALUES (3021, 4006, '0');
INSERT INTO `seat` VALUES (3022, 4006, '0');
INSERT INTO `seat` VALUES (3023, 4001, '0');
INSERT INTO `seat` VALUES (3024, 4001, '0');
INSERT INTO `seat` VALUES (3025, 4001, '0');
INSERT INTO `seat` VALUES (3026, 4002, '0');
INSERT INTO `seat` VALUES (3027, 4002, '0');
INSERT INTO `seat` VALUES (3028, 4002, '0');
INSERT INTO `seat` VALUES (3029, 4002, '0');
INSERT INTO `seat` VALUES (3030, 4003, '0');
INSERT INTO `seat` VALUES (3031, 4003, '0');
INSERT INTO `seat` VALUES (3032, 4003, '0');
INSERT INTO `seat` VALUES (3033, 4003, '0');
INSERT INTO `seat` VALUES (3034, 4003, '0');
INSERT INTO `seat` VALUES (3036, 4004, '0');
INSERT INTO `seat` VALUES (3037, 4004, '0');
INSERT INTO `seat` VALUES (3038, 4004, '0');
INSERT INTO `seat` VALUES (3039, 4004, '0');
INSERT INTO `seat` VALUES (3040, 4005, '0');
INSERT INTO `seat` VALUES (3041, 4005, '0');
INSERT INTO `seat` VALUES (3042, 4005, '0');
INSERT INTO `seat` VALUES (3043, 4006, '0');
INSERT INTO `seat` VALUES (3044, 4006, '0');
INSERT INTO `seat` VALUES (3045, 4006, '0');
INSERT INTO `seat` VALUES (3046, 4006, '0');
INSERT INTO `seat` VALUES (3047, 4005, '0');
INSERT INTO `seat` VALUES (3048, 4005, '0');
INSERT INTO `seat` VALUES (3049, 4004, '0');

-- ----------------------------

-- Table structure for seat_resver

-- ----------------------------

DROP TABLE IF EXISTS `seat_resver`;
CREATE TABLE `seat_resver`  (
  `resverID` int(11) NOT NULL AUTO_INCREMENT COMMENT '预约编号',
  `userID` int(11) NOT NULL,
  `seatID` int(11) NOT NULL,
  `bTime` datetime NULL DEFAULT NULL,
  `eTime` datetime NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`resverID`) USING BTREE,
  INDEX `userID`(`userID`) USING BTREE,
  INDEX `seatID`(`seatID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5065 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '座位记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------

-- Records of seat_resver

-- ----------------------------

INSERT INTO `seat_resver` VALUES (5063, 1001, 3003, '2023-05-15 05:01:54', '2023-05-05 06:01:58', '1');
INSERT INTO `seat_resver` VALUES (5064, 1861, 3005, '2023-08-17 16:00:00', '2023-08-30 16:00:00', '1');

-- ----------------------------

-- Table structure for seat_use

-- ----------------------------

DROP TABLE IF EXISTS `seat_use`;
CREATE TABLE `seat_use`  (
  `seatuseID` int(11) NOT NULL AUTO_INCREMENT COMMENT '座位使用编号',
  `userID` int(11) NOT NULL,
  `seatID` int(11) NOT NULL,
  `beginTime` datetime NOT NULL,
  `endTime` datetime NOT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`seatuseID`) USING BTREE,
  INDEX `userID`(`userID`) USING BTREE,
  INDEX `seatID`(`seatID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6041 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '座位使用表' ROW_FORMAT = DYNAMIC;

-- ----------------------------

-- Records of seat_use

-- ----------------------------

INSERT INTO `seat_use` VALUES (6003, 1171, 3011, '2020-03-28 12:00:09', '2020-03-29 16:25:01', '1');
INSERT INTO `seat_use` VALUES (6004, 1172, 3011, '2020-03-28 12:00:09', '2020-03-29 16:25:01', '1');
INSERT INTO `seat_use` VALUES (6005, 1172, 3011, '2020-03-29 16:27:20', '2020-03-29 16:28:01', '2');
INSERT INTO `seat_use` VALUES (6006, 1171, 3012, '2020-03-28 12:00:09', '2020-03-28 12:00:11', '2');
INSERT INTO `seat_use` VALUES (6007, 1173, 3023, '2020-03-29 16:24:59', '2020-03-29 16:25:01', '1');
INSERT INTO `seat_use` VALUES (6008, 1173, 3019, '2020-03-28 12:00:09', '2020-03-28 12:00:11', '2');
INSERT INTO `seat_use` VALUES (6009, 1174, 3002, '2020-03-30 14:17:11', '2020-03-30 14:17:14', '1');
INSERT INTO `seat_use` VALUES (6010, 1175, 3014, '2020-03-29 16:27:20', '2020-03-30 14:25:15', '1');
INSERT INTO `seat_use` VALUES (6011, 1176, 3007, '2020-03-30 14:28:05', '2020-03-30 14:28:06', '2');
INSERT INTO `seat_use` VALUES (6012, 1180, 3004, '2020-03-30 14:30:33', '2020-03-30 14:30:34', '1');
INSERT INTO `seat_use` VALUES (6013, 1181, 3006, '2020-03-30 15:54:39', '2020-03-30 22:00:00', '2');
INSERT INTO `seat_use` VALUES (6015, 1183, 3012, '2020-03-30 17:37:17', '2020-03-30 21:00:00', '1');
INSERT INTO `seat_use` VALUES (6016, 1185, 3013, '2020-03-29 16:24:59', '2020-03-30 17:47:39', '1');
INSERT INTO `seat_use` VALUES (6017, 1186, 3033, '2020-03-29 16:27:20', '2020-03-29 16:25:01', '1');
INSERT INTO `seat_use` VALUES (6018, 1187, 3023, '2020-03-30 22:51:14', '2020-03-30 22:51:16', '1');
INSERT INTO `seat_use` VALUES (6019, 1190, 3011, '2020-03-31 20:48:50', '2020-03-31 20:48:52', '2');
INSERT INTO `seat_use` VALUES (6020, 1171, 3026, '2020-04-01 14:00:00', '2020-04-01 18:30:00', '1');
INSERT INTO `seat_use` VALUES (6021, 1195, 3018, '2020-04-01 20:06:52', '2020-04-01 22:00:00', '2');
INSERT INTO `seat_use` VALUES (6022, 1196, 3020, '2020-04-02 11:51:00', '2020-04-02 11:51:05', '2');
INSERT INTO `seat_use` VALUES (6023, 1195, 3018, '2020-04-02 12:01:12', '2020-04-02 12:01:15', '1');
INSERT INTO `seat_use` VALUES (6024, 1197, 3017, '2020-04-02 12:02:29', '2020-04-02 12:02:34', '1');
INSERT INTO `seat_use` VALUES (6025, 1198, 3016, '2020-04-02 15:03:21', '2020-04-02 15:03:25', '2');

-- ----------------------------

-- Table structure for statistic

-- ----------------------------

DROP TABLE IF EXISTS `statistic`;
CREATE TABLE `statistic`  (
  `staID` int(11) NOT NULL AUTO_INCREMENT,
  `stadate` date NULL DEFAULT NULL,
  `seatU` int(11) NULL DEFAULT NULL,
  `seatR` int(11) NULL DEFAULT NULL,
  `oth1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `oth2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`staID`) USING BTREE,
  INDEX `seatuseID`(`stadate`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19651 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '自习室座位情况统计表' ROW_FORMAT = DYNAMIC;

-- ----------------------------

-- Records of statistic

-- ----------------------------

INSERT INTO `statistic` VALUES (9001, '2020-03-12', 10, 20, NULL, NULL);
INSERT INTO `statistic` VALUES (9002, '2020-03-13', 27, 20, NULL, NULL);
INSERT INTO `statistic` VALUES (9003, '2020-03-14', 32, 15, NULL, NULL);
INSERT INTO `statistic` VALUES (9004, '2020-03-15', 22, 17, NULL, NULL);
INSERT INTO `statistic` VALUES (9005, '2020-03-16', 40, 26, NULL, NULL);
INSERT INTO `statistic` VALUES (9006, '2020-03-17', 23, 14, NULL, NULL);
INSERT INTO `statistic` VALUES (9007, '2020-03-18', 45, 45, NULL, NULL);
INSERT INTO `statistic` VALUES (9008, '2020-03-19', 34, 22, NULL, NULL);
INSERT INTO `statistic` VALUES (9009, '2020-03-20', 33, 20, NULL, NULL);

-- ----------------------------

-- Table structure for user

-- ----------------------------

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userID` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `userAge` int(11) NULL DEFAULT NULL,
  `userGender` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userTel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userPwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`userID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1864 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------

-- Records of user

-- ----------------------------

INSERT INTO `user` VALUES (1001, 'user123', 24, 'M', '14545454545', '123456');
INSERT INTO `user` VALUES (1861, '123456', 14, 'M', '11111111111', '123456');
INSERT INTO `user` VALUES (1862, '2014490002', NULL, NULL, '11111111111', '123456');
INSERT INTO `user` VALUES (1863, '2014490007', NULL, NULL, '11111111111', '123456');

SET FOREIGN_KEY_CHECKS = 1;
