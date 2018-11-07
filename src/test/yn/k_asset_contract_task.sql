
##创建获取签章合同定时表
CREATE TABLE `k_asset_contract_task` (
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
  `apply_no` VARCHAR(32) NOT NULL COMMENT '申请编号',
  `venus_apply_no` VARCHAR(32)  NOT NULL COMMENT '贷款唯一标识',
  `file_type` VARCHAR(50) DEFAULT NULL COMMENT '文件类型',
  `attach_sub_class` VARCHAR(127) DEFAULT NULL COMMENT '附件小类',
  `file_name` VARCHAR(128) DEFAULT NULL COMMENT '源文件名称',
  `bank_file_type` VARCHAR(5) DEFAULT NULL COMMENT '合同文件类型对应的银行code',
  `file_url` VARCHAR(500) NOT NULL COMMENT '文件的原始url',
  `compress_file_id` VARCHAR(64) DEFAULT NULL COMMENT '压缩后的文件id',
  `compress_file_state` INT(2) DEFAULT NULL COMMENT '文件上传云信状态',
  `compress_no` VARCHAR(32) DEFAULT NULL COMMENT '压缩服务流水号',
  `sign_file_id` VARCHAR(64) DEFAULT NULL COMMENT '签章后的文件id',
  `sign_file_size` INT(11) DEFAULT 0 COMMENT '签章文件的大小',
  `sign_state` INT(2) DEFAULT NULL COMMENT '文件的签章状态',
  `exec_state` INT(2) DEFAULT 0 COMMENT '任务的执行状态',
  `exec_times` INT(2) DEFAULT NULL COMMENT '执行次数',
  `is_success` BIT(1) DEFAULT NULL COMMENT '是否成功',
  `is_end` BIT(1) DEFAULT NULL COMMENT '任务是否结束',
  `reserve1` VARCHAR(255) DEFAULT NULL COMMENT '保留字段1',
  `reserve2` VARCHAR(255) DEFAULT NULL COMMENT '保留字段2',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


##新增字段

ALTER TABLE k_asset_attachment_rule ADD COLUMN merger_attach_type VARCHAR (512) DEFAULT NULL AFTER attach_main_type;

ALTER TABLE k_asset_payment_task ADD COLUMN file_status TINYINT(1) DEFAULT 0 AFTER exec_times;

ALTER TABLE k_osb_file_log ADD COLUMN reserve1 text DEFAULT NULL;

ALTER TABLE k_asset_contract_task ADD COLUMN next_time datetime;

#文件规则配置
INSERT INTO `k_asset_attachment_rule` (`id`, `bz_id`, `create_time`, `creator_department_id`, `creator_department_name`, `creator_id`, `creator_name`, `is_deleted`, `update_time`, `updator_department_id`, `updator_department_name`, `updator_id`, `updator_name`, `version`, `attach_main_type`, `merger_attach_type`, `attach_name`, `attach_sub_type`, `fields`, `financial_code`, `financial_id`, `rule`, `rule_type`, `script`, `name_format`, `phase`) VALUES ('40281e8463e436a40163e436c01d0009', '', now(), '100', '大数据应用中心', 'admin', '系统', 0, now(), NULL, '', '', '', '0', 'MCLOUD_notice_type_11040', NULL, '贷款合同', 'pdf', 'dcsrq', 'YNTRUST', '40281e8363e42a820163e42a9dc50003', 'function toDo(attachmentNum){return attachmentNum > 0;}', 'js', '', '%s_%s_G_%d.jpg', 'sign_contract');
INSERT INTO `k_asset_attachment_rule` (`id`, `bz_id`, `create_time`, `creator_department_id`, `creator_department_name`, `creator_id`, `creator_name`, `is_deleted`, `update_time`, `updator_department_id`, `updator_department_name`, `updator_id`, `updator_name`, `version`, `attach_main_type`, `merger_attach_type`, `attach_name`, `attach_sub_type`, `fields`, `financial_code`, `financial_id`, `rule`, `rule_type`, `script`, `name_format`, `phase`) VALUES ('40281e8463e436a40163e436c01d0001', '', now(), '100', '大数据应用中心', 'admin', '系统', 0, now(), NULL, '', '', '', '0', 'applicantOrderIdPositive', NULL, '身份证正面', 'jpg', 'dcsrq', 'YNTRUST', '40281e8363e42a820163e42a9dc50003', 'function toDo(attachmentNum){return attachmentNum == 1;}', 'js', '', '%s_%s_A_%d.jpg', 'payment');
INSERT INTO `k_asset_attachment_rule` (`id`, `bz_id`, `create_time`, `creator_department_id`, `creator_department_name`, `creator_id`, `creator_name`, `is_deleted`, `update_time`, `updator_department_id`, `updator_department_name`, `updator_id`, `updator_name`, `version`, `attach_main_type`, `merger_attach_type`, `attach_name`, `attach_sub_type`, `fields`, `financial_code`, `financial_id`, `rule`, `rule_type`, `script`, `name_format`, `phase`) VALUES ('40281e8463e436a40163e436c01d0002', '', now(), '100', '大数据应用中心', 'admin', '系统', 0, now(), NULL, '', '', '', '0', 'applicantOrderIdReverse', NULL, '身份证反面', 'jpg', 'dcsrq', 'YNTRUST', '40281e8363e42a820163e42a9dc50003', 'function toDo(attachmentNum){return attachmentNum == 1;}', 'js', '', '%s_%s_B_%d.jpg', 'payment');
INSERT INTO `k_asset_attachment_rule` (`id`, `bz_id`, `create_time`, `creator_department_id`, `creator_department_name`, `creator_id`, `creator_name`, `is_deleted`, `update_time`, `updator_department_id`, `updator_department_name`, `updator_id`, `updator_name`, `version`, `attach_main_type`, `merger_attach_type`, `attach_name`, `attach_sub_type`, `fields`, `financial_code`, `financial_id`, `rule`, `rule_type`, `script`, `name_format`, `phase`) VALUES ('40281e8463e436a40163e436c01d0003', '', now(), '100', '大数据应用中心', 'admin', '系统', 0, now(), NULL, '', '', '', '0', 'applicantAssessmentXT', NULL, '评估报告', 'jpg', 'dcsrq', 'YNTRUST', '40281e8363e42a820163e42a9dc50003', 'function toDo(attachmentNum){return attachmentNum > 0;}', 'js', '', '%s_%s_V_%d.jpg', 'payment');
INSERT INTO `k_asset_attachment_rule` (`id`, `bz_id`, `create_time`, `creator_department_id`, `creator_department_name`, `creator_id`, `creator_name`, `is_deleted`, `update_time`, `updator_department_id`, `updator_department_name`, `updator_id`, `updator_name`, `version`, `attach_main_type`, `merger_attach_type`, `attach_name`, `attach_sub_type`, `fields`, `financial_code`, `financial_id`, `rule`, `rule_type`, `script`, `name_format`, `phase`) VALUES ('40281e8463e436a40163e436c01d0004', '', now(), '100', '大数据应用中心', 'admin', '系统', 0, now(), NULL, '', '', '', '0', 'firstYearCommercialInsurance', NULL, '商业保险单--增信文件', 'jpg', 'dcsrq', 'YNTRUST', '40281e8363e42a820163e42a9dc50003', 'function toDo(attachmentNum){return attachmentNum > 0;}', 'js', '', '%s_%s_X_%d.jpg', 'payment');
INSERT INTO `k_asset_attachment_rule` (`id`, `bz_id`, `create_time`, `creator_department_id`, `creator_department_name`, `creator_id`, `creator_name`, `is_deleted`, `update_time`, `updator_department_id`, `updator_department_name`, `updator_id`, `updator_name`, `version`, `attach_main_type`, `merger_attach_type`, `attach_name`, `attach_sub_type`, `fields`, `financial_code`, `financial_id`, `rule`, `rule_type`, `script`, `name_format`, `phase`) VALUES ('40281e8463e436a40163e436c01d0006', '', now(), '100', '大数据应用中心', 'admin', '系统', 0, now(), NULL, '', '', '', '0', 'mortgageRegistrationCertificate', 'mortgageRegistrationCertificate,mortgageRegistrationCertificateOtherPages', '抵押登记证', 'jpg', 'dcsrq', 'YNTRUST', '40281e8363e42a820163e42a9dc50003', 'function toDo(attachmentNum){return attachmentNum >= 0;}', 'js', '', '%s_%s_I_%d.jpg', 'payment');
INSERT INTO `k_asset_attachment_rule` (`id`, `bz_id`, `create_time`, `creator_department_id`, `creator_department_name`, `creator_id`, `creator_name`, `is_deleted`, `update_time`, `updator_department_id`, `updator_department_name`, `updator_id`, `updator_name`, `version`, `attach_main_type`, `merger_attach_type`, `attach_name`, `attach_sub_type`, `fields`, `financial_code`, `financial_id`, `rule`, `rule_type`, `script`, `name_format`, `phase`) VALUES ('40281e8463e436a40163e436c01d0007', '', now(), '100', '大数据应用中心', 'admin', '系统', 0, now(), NULL, '', '', '', '0', 'leaseMainContract', 'leaseMainContract,contractReverse', '融资租赁合同-消费合同文件', 'jpg', 'dcsrq', 'YNTRUST', '40281e8363e42a820163e42a9dc50003', 'function toDo(attachmentNum){return attachmentNum > 0;}', 'js', '', '%s_%s_T_%d.jpg', 'payment');
INSERT INTO `k_asset_attachment_rule` (`id`, `bz_id`, `create_time`, `creator_department_id`, `creator_department_name`, `creator_id`, `creator_name`, `is_deleted`, `update_time`, `updator_department_id`, `updator_department_name`, `updator_id`, `updator_name`, `version`, `attach_main_type`, `merger_attach_type`, `attach_name`, `attach_sub_type`, `fields`, `financial_code`, `financial_id`, `rule`, `rule_type`, `script`, `name_format`, `phase`) VALUES ('40281e8463e436a40163e436c01d0008', '', now(), '100', '大数据应用中心', 'admin', '系统', 0, now(), NULL, '', '', '', '0', 'leaseOrMortgageOrautoRetailOrSalesCarContracts', NULL, '抵押合同-授信合同', 'jpg', 'dcsrq', 'YNTRUST', '40281e8363e42a820163e42a9dc50003', 'function toDo(attachmentNum){return attachmentNum > 0;}', 'js', '', '%s_%s_W_%d.jpg', 'payment');
INSERT INTO `k_asset_attachment_rule` (`id`, `bz_id`, `create_time`, `creator_department_id`, `creator_department_name`, `creator_id`, `creator_name`, `is_deleted`, `update_time`, `updator_department_id`, `updator_department_name`, `updator_id`, `updator_name`, `version`, `attach_main_type`, `merger_attach_type`, `attach_name`, `attach_sub_type`, `fields`, `financial_code`, `financial_id`, `rule`, `rule_type`, `script`, `name_format`, `phase`) VALUES ('40281e8463e436a40163e436c01d0014', '', now(), '100', '大数据应用中心', 'admin', '系统', 0, now(), NULL, '', '', '', '0', 'mortgageRegistrationCertificate', 'mortgageRegistrationCertificate,mortgageRegistrationCertificateOtherPages', '抵押登记证', 'jpg', 'dcsrq', 'YNTRUST', '40281e8363e42a820163e42a9dc50003', 'function toDo(attachmentNum){return attachmentNum > 0;}', 'js', '', '%s_%s_I_%d.jpg', 'after_loan');
INSERT INTO `k_asset_attachment_rule` (`id`, `bz_id`, `create_time`, `creator_department_id`, `creator_department_name`, `creator_id`, `creator_name`, `is_deleted`, `update_time`, `updator_department_id`, `updator_department_name`, `updator_id`, `updator_name`, `version`, `attach_main_type`, `merger_attach_type`, `attach_name`, `attach_sub_type`, `fields`, `financial_code`, `financial_id`, `rule`, `rule_type`, `script`, `name_format`, `phase`) VALUES ('40281e8463e436a40163e436c01d0011', '', now(), '100', '大数据应用中心', 'admin', '系统', 0, now(), NULL, '', '', '', '0', 'agencyAgreement', NULL, '代扣授权书', 'jpg', 'dcsrq', 'YNTRUST', '40281e8363e42a820163e42a9dc50003', 'function toDo(attachmentNum){return attachmentNum > 0;}', 'js', '', '%s_%s_F_%d.jpg', 'payment');
INSERT INTO `k_asset_attachment_rule` (`id`, `bz_id`, `create_time`, `creator_department_id`, `creator_department_name`, `creator_id`, `creator_name`, `is_deleted`, `update_time`, `updator_department_id`, `updator_department_name`, `updator_id`, `updator_name`, `version`, `attach_main_type`, `merger_attach_type`, `attach_name`, `attach_sub_type`, `fields`, `financial_code`, `financial_id`, `rule`, `rule_type`, `script`, `name_format`, `phase`) VALUES ('40281e8463e436a40163e436c01d0012', '', now(), '100', '大数据应用中心', 'admin', '系统', 0, now(), NULL, '', '', '', '0', 'mortgageAcceptanceCertificate', NULL, '抵押受理证明', 'jpg', 'dcsrq', 'YNTRUST', '40281e8363e42a820163e42a9dc50003', 'function toDo(attachmentNum){return attachmentNum >= 0;}', 'js', '', '%s_%s_J_%d.jpg', 'payment');
INSERT INTO `k_asset_attachment_rule` (`id`, `bz_id`, `create_time`, `creator_department_id`, `creator_department_name`, `creator_id`, `creator_name`, `is_deleted`, `update_time`, `updator_department_id`, `updator_department_name`, `updator_id`, `updator_name`, `version`, `attach_main_type`, `merger_attach_type`, `attach_name`, `attach_sub_type`, `fields`, `financial_code`, `financial_id`, `rule`, `rule_type`, `script`, `name_format`, `phase`) VALUES ('40281e8463e436a40163e436c01d0013', '', now(), '100', '大数据应用中心', 'admin', '系统', 0, now(), NULL, '', '', '', '0', 'loanDocuments', NULL, '放款凭证', 'jpg', 'dcsrq', 'YNTRUST', '40281e8363e42a820163e42a9dc50003', 'function toDo(attachmentNum){return attachmentNum > 0;}', 'js', '', '%s_%s_S_%d.jpg', 'after_loan');
INSERT INTO `k_asset_attachment_rule` (`id`, `bz_id`, `create_time`, `creator_department_id`, `creator_department_name`, `creator_id`, `creator_name`, `is_deleted`, `update_time`, `updator_department_id`, `updator_department_name`, `updator_id`, `updator_name`, `version`, `attach_main_type`, `merger_attach_type`, `attach_name`, `attach_sub_type`, `fields`, `financial_code`, `financial_id`, `rule`, `rule_type`, `script`, `name_format`, `phase`) VALUES ('40281e8463e436a40163e436c01d0015', '', now(), '100', '大数据应用中心', 'admin', '系统', 0, now(), NULL, '', '', '', '0', 'threeSidesClaimTranserAggrement', NULL, '三方债权转让协议', 'jpg', 'dcsrq', 'YNTRUST', '40281e8363e42a820163e42a9dc50003', 'function toDo(attachmentNum){return attachmentNum == 1;}', 'js', '', '%s_%s_N_%d.jpg', 'payment');


##2018-11-02
UPDATE `k_asset_attachment_rule` SET rule = 'function toDo(attachmentNum){return attachmentNum == 1;}' WHERE id = '40281e8463e436a40163e436c01d0015';
INSERT INTO `k_asset_attachment_rule` (`id`, `bz_id`, `create_time`, `creator_department_id`, `creator_department_name`, `creator_id`, `creator_name`, `is_deleted`, `update_time`, `updator_department_id`, `updator_department_name`, `updator_id`, `updator_name`, `version`, `attach_main_type`, `merger_attach_type`, `attach_name`, `attach_sub_type`, `fields`, `financial_code`, `financial_id`, `rule`, `rule_type`, `script`, `name_format`, `phase`) VALUES ('40281e8463e436a40163e436c01d0016', '', now(), '100', '大数据应用中心', 'admin', '系统', 0, now(), NULL, '', '', '', '0', 'threeSidesClaimTranserAggrementInterviewPhoto', NULL, '三方债权转让协议面签照', 'jpg', 'dcsrq', 'YNTRUST', '40281e8363e42a820163e42a9dc50003', 'function toDo(attachmentNum){return attachmentNum > 0;}', 'js', '', '%s_%s_C_%d.jpg', 'payment');
