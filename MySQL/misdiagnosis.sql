/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50709
Source Host           : localhost:3306
Source Database       : misdiagnosis

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2017-08-16 11:52:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `disease`
-- ----------------------------
DROP TABLE IF EXISTS `disease`;
CREATE TABLE `disease` (
`diseaseid`  int(8) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '疾病的id' ,
`disease`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '疾病的名称' ,
PRIMARY KEY (`diseaseid`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='保存所有的疾病名称'
AUTO_INCREMENT=93521

;

-- ----------------------------
-- Table structure for `failpaper`
-- ----------------------------
DROP TABLE IF EXISTS `failpaper`;
CREATE TABLE `failpaper` (
`paperid`  int(8) NOT NULL AUTO_INCREMENT COMMENT '失败的文章' ,
`papertitle`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`paperid`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Table structure for `hospitals`
-- ----------------------------
DROP TABLE IF EXISTS `hospitals`;
CREATE TABLE `hospitals` (
`province`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '省市' ,
`hospitalid`  int(11) NOT NULL AUTO_INCREMENT COMMENT '医院编号' ,
`hospitalname`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '医院名称' ,
`address`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '医院地址' ,
`postcode`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`phone`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`administrator`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`bed`  int(11) NULL DEFAULT NULL ,
`dialyvolume`  int(11) NULL DEFAULT NULL COMMENT '日门诊量' ,
`grade`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '等级' ,
`specialized`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '特色专科' ,
`director`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '药房主任' ,
`equipment`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主要设备' ,
`webandemail`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网址和邮箱' ,
`busline`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '乘车路线' ,
PRIMARY KEY (`hospitalid`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=31639

;

-- ----------------------------
-- Table structure for `misdiagnosis`
-- ----------------------------
DROP TABLE IF EXISTS `misdiagnosis`;
CREATE TABLE `misdiagnosis` (
`misdiagnosisid`  int(8) NOT NULL AUTO_INCREMENT COMMENT '误诊事例ID' ,
`diseaseid`  int(8) NOT NULL COMMENT '被误诊疾病名称' ,
`misdiseaseid`  int(8) NOT NULL COMMENT '被误诊为的疾病名称' ,
`paperid`  int(8) NULL DEFAULT NULL COMMENT '对应文章的id' ,
`reason`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '误诊原因' ,
`suggestion`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '经验教训' ,
PRIMARY KEY (`misdiagnosisid`),
FOREIGN KEY (`paperid`) REFERENCES `paper` (`paperid`) ON DELETE CASCADE ON UPDATE CASCADE,
INDEX `paperid` (`paperid`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='被误诊疾病事例'
AUTO_INCREMENT=23

;

-- ----------------------------
-- Table structure for `paper`
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper` (
`paperid`  int(10) NOT NULL AUTO_INCREMENT COMMENT '论文的ID' ,
`papername`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`papercontent`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '论文的内容' ,
`paperkey`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关键词' ,
`summary`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '论文摘要' ,
`discuss`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '论文讨论' ,
`casenum`  int(4) NULL DEFAULT 0 COMMENT '一篇文章包含病例数' ,
`author`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '论文的作者' ,
`authorunit`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者单位' ,
PRIMARY KEY (`paperid`),
INDEX `authorID` (`author`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='论文信息'
AUTO_INCREMENT=23

;

-- ----------------------------
-- Table structure for `surname`
-- ----------------------------
DROP TABLE IF EXISTS `surname`;
CREATE TABLE `surname` (
`surname`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓氏' ,
PRIMARY KEY (`surname`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Auto increment value for `disease`
-- ----------------------------
ALTER TABLE `disease` AUTO_INCREMENT=93521;

-- ----------------------------
-- Auto increment value for `failpaper`
-- ----------------------------
ALTER TABLE `failpaper` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `hospitals`
-- ----------------------------
ALTER TABLE `hospitals` AUTO_INCREMENT=31639;

-- ----------------------------
-- Auto increment value for `misdiagnosis`
-- ----------------------------
ALTER TABLE `misdiagnosis` AUTO_INCREMENT=23;

-- ----------------------------
-- Auto increment value for `paper`
-- ----------------------------
ALTER TABLE `paper` AUTO_INCREMENT=23;
