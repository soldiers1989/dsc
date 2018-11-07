/*
Navicat MySQL Data Transfer

Source Server         : kepler-dev
Source Server Version : 50714
Source Host           : 192.168.145.160:3306
Source Database       : dsc

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2018-09-06 14:23:08
*/
-- ----------------------------
-- Table structure for k_asset_afterloan_task
-- ----------------------------

CREATE TABLE `k_asset_afterloan_task` (
  `id` varchar(50) NOT NULL,
  `bz_id` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator_department_id` bigint(20) DEFAULT NULL,
  `creator_department_name` varchar(255) DEFAULT NULL,
  `creator_id` varchar(255) DEFAULT NULL,
  `creator_name` varchar(255) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updator_department_id` bigint(20) DEFAULT NULL,
  `updator_department_name` varchar(255) DEFAULT NULL,
  `updator_id` varchar(255) DEFAULT NULL,
  `updator_name` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `financial_code` varchar(32) DEFAULT NULL COMMENT '资方code',
  `apply_no` varchar(32) DEFAULT NULL COMMENT '申请编号',
  `asset_code` varchar(32) DEFAULT NULL,
  `exec_state` int(12) DEFAULT NULL COMMENT '执行状态',
  `exec_times` int(12) DEFAULT NULL COMMENT '执行次数',
  `is_success` bit(1) DEFAULT NULL COMMENT '是否成功',
  `is_end` bit(1) DEFAULT NULL COMMENT '是否结束',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
