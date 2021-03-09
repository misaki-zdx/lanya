/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : erp

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2020-07-08 11:24:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account_code
-- ----------------------------
DROP TABLE IF EXISTS `account_code`;
CREATE TABLE `account_code` (
  `id` varchar(64) NOT NULL,
  `account_code` varchar(20) DEFAULT NULL,
  `pwd` blob,
  `real_name` varchar(20) DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `resource_total` int(11) DEFAULT NULL,
  `funs_total` int(11) DEFAULT NULL,
  `download_total` int(11) DEFAULT NULL,
  `user_intro` varbinary(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for add_label
-- ----------------------------
DROP TABLE IF EXISTS `add_label`;
CREATE TABLE `add_label` (
  `id` varchar(64) NOT NULL,
  `resource_id` varchar(64) DEFAULT NULL,
  `lable_info_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for advertisment
-- ----------------------------
DROP TABLE IF EXISTS `advertisment`;
CREATE TABLE `advertisment` (
  `id` varchar(64) NOT NULL,
  `img_name` varchar(20) DEFAULT NULL,
  `img_url` varchar(100) DEFAULT NULL,
  `add_intro` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lable_info
-- ----------------------------
DROP TABLE IF EXISTS `lable_info`;
CREATE TABLE `lable_info` (
  `id` varchar(64) NOT NULL,
  `lable_name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for resouces
-- ----------------------------
DROP TABLE IF EXISTS `resouces`;
CREATE TABLE `resouces` (
  `id` varchar(64) NOT NULL,
  `resource_title` varchar(20) DEFAULT NULL,
  `resource_date` date DEFAULT NULL,
  `resource_intro` tinytext,
  `resource_detail` text,
  `picture_url` varchar(100) DEFAULT NULL,
  `account_id` varchar(64) DEFAULT NULL,
  `download_count` int(11) DEFAULT NULL,
  `view_count` int(11) DEFAULT NULL,
  `score` double DEFAULT NULL,
  `is_boutique` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for resource_comment
-- ----------------------------
DROP TABLE IF EXISTS `resource_comment`;
CREATE TABLE `resource_comment` (
  `id` varchar(64) NOT NULL,
  `account_id` int(11) DEFAULT NULL,
  `comment_date` date DEFAULT NULL,
  `comment_score` double DEFAULT NULL,
  `content` varchar(300) DEFAULT NULL,
  `resource_id` varchar(64) DEFAULT NULL,
  `parent_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
