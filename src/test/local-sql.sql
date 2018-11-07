
##将未初始化的资方code添加为cmbc
UPDATE `sys_dict` SET financial_code = 'CMBC' WHERE financial_code is null;

##将数据全部设置为有效
UPDATE sys_dict SET is_deleted = 0;

##将未用的数据的code设置为不为null
UPDATE sys_dict SET alix_dict_code = bank_dict_code WHERE alix_dict_code is null;

##创建表3个字段的唯一性索引
CREATE UNIQUE INDEX index_1 ON sys_dict(financial_code,alix_field_code,alix_dict_code);




## 结算配置数据表

DROP TABLE IF EXISTS `k_asset_settle_info`;
CREATE TABLE `k_asset_settle_info` (
  `id` varchar(50) NOT NULL,
  `bz_id` VARCHAR(50) DEFAULT NULL COMMENT '业务流水号',
  `create_time` datetime DEFAULT NULL,
  `creator_department_id` bigint(20) DEFAULT NULL,
  `creator_department_name` varchar(255) DEFAULT NULL,
  `creator_id` varchar(255) DEFAULT NULL,
  `creator_name` varchar(255) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT 0,
  `update_time` datetime DEFAULT NULL,
  `updator_department_id` bigint(20) DEFAULT NULL,
  `updator_department_name` varchar(255) DEFAULT NULL,
  `updator_id` varchar(255) DEFAULT NULL,
  `updator_name` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `financial_code` varchar(64) DEFAULT NULL COMMENT '资方id' ,
  `financial_id` varchar(64) DEFAULT NULL COMMENT '资方code',
  `early_repay_limit_period` int(11) DEFAULT 0 COMMENT '提前还款限制期数',
  `early_repay_min_day` int(11) DEFAULT 0 COMMENT '提前还款最小间隔天数',
  `grace_period` int(11) DEFAULT NULL COMMENT '逾期宽限期',
  `early_repay_penalty_prop` VARCHAR(32) DEFAULT 0 COMMENT '提前还款违约金收取比例',
  `penalty_rate_type` VARCHAR(32) DEFAULT NULL COMMENT '罚息比率类型',
  `penalty_rate` VARCHAR(32) DEFAULT NULL COMMENT '罚息率',
  `bank_cost_rate` VARCHAR(32) DEFAULT 0 COMMENT '银行资金成本费率',
  `cust_rate` VARCHAR(32) DEFAULT 0 COMMENT '客户费率',
  `bank_cost_interest_rate_new` VARCHAR(32) DEFAULT NULL COMMENT '新车银行资金成本利率',
  `bank_cost_interest_rate_old` VARCHAR(32) DEFAULT NULL COMMENT '二手车银行资金成本利率',
  `reserve1` VARCHAR(255) DEFAULT NULL COMMENT '保留字段1',
  `reserve2` VARCHAR(255) DEFAULT NULL COMMENT '保留字段2',
  `reserve3` VARCHAR(255) DEFAULT NULL COMMENT '保留字段3',
  PRIMARY KEY (`id`)
);

##ENGINE=MyISAM DEFAULT CHARSET=utf8;

INSERT INTO `k_asset_settle_info` (`id`, `bz_id`, `create_time`, `creator_department_id`, `creator_department_name`, `creator_id`, `creator_name`, `is_deleted`, `update_time`, `updator_department_id`, `updator_department_name`, `updator_id`, `updator_name`, `version`, `financial_code`, `financial_id`, `early_repay_limit_period`, `early_repay_min_day`, `grace_period`, `early_repay_penalty_prop`, `penalty_rate_type`, `penalty_rate`, `bank_cost_rate`, `cust_rate`, `bank_cost_interest_rate_new`, `bank_cost_interest_rate_old`, `reserve1`, `reserve2`, `reserve3`) VALUES ('6a0772c6-2240-4c52-ae19-7779a9923bb6', NULL, NOW(), NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 'WeBank', '40281e8363e42a820163e42a9dc50001', '0', '0', '3', '0', NULL, '0', '0', '0', '6.5', '7', NULL, NULL, NULL);
INSERT INTO `k_asset_settle_info` (`id`, `bz_id`, `create_time`, `creator_department_id`, `creator_department_name`, `creator_id`, `creator_name`, `is_deleted`, `update_time`, `updator_department_id`, `updator_department_name`, `updator_id`, `updator_name`, `version`, `financial_code`, `financial_id`, `early_repay_limit_period`, `early_repay_min_day`, `grace_period`, `early_repay_penalty_prop`, `penalty_rate_type`, `penalty_rate`, `bank_cost_rate`, `cust_rate`, `bank_cost_interest_rate_new`, `bank_cost_interest_rate_old`, `reserve1`, `reserve2`, `reserve3`) VALUES ('74c75253-ac63-4f42-87fe-b036b033dcd4', NULL, NOW(), NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, 'CMBC', '40281e8363e42a820163e42a9dc50000', '0', '0', '0', '0', NULL, '0', '0', '0', '6.5', '7', NULL, NULL, NULL);


UPDATE `k_asset_attachment_rule` SET `rule`='function toDo(attachmentNum,isNew){if(isNew){return attachmentNum >= 0}else{return attachmentNum > 0}}' WHERE attach_main_type = 'carFullFacePhoto' AND financial_code = 'WeBank';
UPDATE `k_asset_attachment_rule` SET attach_main_type ='carPurchaseInvoice' WHERE attach_main_type = 'vehicleInvoice' AND financial_code = 'WeBank';


## 放开对购置税发票 的附件校验
UPDATE k_asset_attachment_rule 
SET rule = 'function toDo(attachmentNum,isNew){if(isNew){return attachmentNum >= 0}else{return attachmentNum >= 0}}',
	phase = 'after_loan' 
WHERE 
	attach_main_type = 'purchaseTaxInvoice' 
AND financial_code = 'WeBank';


UPDATE k_asset_attachment_rule
SET rule = 'function toDo(attachmentNum,isNew){if(isNew){return attachmentNum >= 0}else{return attachmentNum > 0}}'
WHERE
	financial_code = 'WeBank'
AND attach_main_type = 'vehicleRegistration';



##2018-08-29

INSERT INTO `k_asset_attachment_rule` (
	`id`,
	`bz_id`,
	`create_time`,
	`creator_department_id`,
	`creator_department_name`,
	`creator_id`,
	`creator_name`,
	`is_deleted`,
	`update_time`,
	`updator_department_id`,
	`updator_department_name`,
	`updator_id`,
	`updator_name`,
	`version`,
	`attach_main_type`,
	`attach_name`,
	`attach_sub_type`,
	`fields`,
	`financial_code`,
	`financial_id`,
	`rule`,
	`rule_type`,
	`script`,
	`name_format`,
	`phase`
)
VALUES
	(
		'40281e8363e436a40163e436c01d0124',
		'',
		'2018-06-09 19:03:03',
		'100',
		'大数据应用中心',
		'admin',
		'系统',
		0,
		'2018-06-09 19:03:03',
		NULL,
		'',
		'',
		'',
		'0',
		'carBuyingContract',
		'购车合同(放款)',
		'JPG',
		'dcsrq',
		'CMBC',
		'40281e8363e42a820163e42a9dc50000',
		'function toDo(attachmentNum){return attachmentNum > 0;}',
		'js',
		'',
		'%s_020_%d.jpg',
		'payment'
	);

UPDATE k_asset_attachment_rule SET name_format = '%s_021_%d.jpg',
    attach_name = '车辆登记本(贷后)'
WHERE
	financial_code = 'CMBC'
AND attach_main_type = 'vehicleRegistration' AND phase = 'after_loan';


UPDATE k_asset_attachment_rule
SET attach_name = '申请表面签照(终审)', attach_main_type = 'applyForSurfaceSign'
WHERE
	attach_main_type = 'applicantCreditInterviewPhoto' AND phase = 'last_trial' AND financial_code = 'CMBC';

UPDATE k_asset_attachment_rule
SET attach_name = '评估报告(终审)',rule = 'function toDo(attachmentNum,isNew){if(isNew){return attachmentNum >= 0;}else{return attachmentNum > 0;}}'
WHERE
	attach_main_type = 'applicantAssessmentXT' AND phase = 'last_trial' AND financial_code = 'CMBC';

UPDATE k_asset_attachment_rule
SET attach_name = '银行流水(终审)'
WHERE
	attach_main_type = 'alipayOrTenpayFlow' AND phase = 'last_trial' AND financial_code = 'CMBC';

UPDATE k_asset_attachment_rule
SET attach_name = '房产证(终审)'
WHERE
	attach_main_type = 'propertyOwnershipCertificate' AND phase = 'last_trial' AND financial_code = 'CMBC';


UPDATE k_asset_attachment_rule
SET attach_name = '户口本(终审)'
WHERE
	attach_main_type = 'applicantHouseholdRegister' AND phase = 'last_trial' AND financial_code = 'CMBC';

UPDATE k_asset_attachment_rule
SET attach_name = '工作证明(终审)'
WHERE
	attach_main_type = 'certificateOfEmployment' AND phase = 'last_trial' AND financial_code = 'CMBC';

UPDATE k_asset_attachment_rule
SET attach_name = '结婚证(终审)'
WHERE
	attach_main_type = 'marriageCertificate' AND phase = 'last_trial' AND financial_code = 'CMBC';

UPDATE k_asset_attachment_rule
SET attach_name = '身份证正反面(终审)'
WHERE
	attach_main_type = 'applicantOrderIdPositive' AND phase = 'last_trial' AND financial_code = 'CMBC';

UPDATE k_asset_attachment_rule
SET attach_name = '还款卡(放款)'
WHERE
	attach_main_type = 'applicantRepaymentCardPositive' AND phase = 'payment' AND financial_code = 'CMBC';

UPDATE k_asset_attachment_rule
SET attach_name = '融资租赁合同（放款）'
WHERE
	attach_main_type = 'leaseOrMortgageOrautoRetailOrSalesCarContracts' AND phase = 'payment' AND financial_code = 'CMBC';

UPDATE k_asset_attachment_rule
SET attach_name = '精品加装发票（放款）'
WHERE
	attach_main_type = 'fineGoodsPlusInvoice' AND phase = 'payment' AND financial_code = 'CMBC';

UPDATE k_asset_attachment_rule
SET attach_name = '商业险保单（放款）'
WHERE
	attach_main_type = 'commercialInsurance' AND phase = 'payment' AND financial_code = 'CMBC';

UPDATE k_asset_attachment_rule
SET attach_name = '车辆发票（放款）'
WHERE
	attach_main_type = 'carPurchaseInvoice' AND phase = 'payment' AND financial_code = 'CMBC';

UPDATE k_asset_attachment_rule
SET attach_name = '商业险保单(贷后)'
WHERE
	attach_main_type = 'commercialInsurance' AND phase = 'after_loan' AND financial_code = 'CMBC';


UPDATE k_asset_attachment_rule
SET attach_name = '车辆发票(贷后)'
WHERE
	attach_main_type = 'carPurchaseInvoice' AND phase = 'after_loan' AND financial_code = 'CMBC';


UPDATE k_asset_attachment_rule
SET attach_name = '精品加装发票(贷后)', rule = 'function toDo(attachmentNum){return attachmentNum >= 0;}'
WHERE
	attach_main_type = 'fineGoodsPlusInvoice' AND phase = 'after_loan' AND financial_code = 'CMBC';


UPDATE k_asset_attachment_rule
SET attach_name = '已签字的申请表（终审）' ,rule = 'function toDo(attachmentNum){return attachmentNum > 0;}'
WHERE
	attach_main_type = 'applicantOrderIdCustApply' AND phase = 'last_trial' AND financial_code = 'CMBC';



##2018-09-07

UPDATE k_asset_attachment_rule
SET rule = 'function toDo(attachmentNum,isNew){if(isNew){return attachmentNum > 0;}else{return attachmentNum >= 0;}}'
WHERE attach_main_type = 'carPurchaseInvoice' AND phase = 'after_loan' AND financial_code = 'CMBC';


##2018-09-10

UPDATE k_asset_attachment_rule
SET rule = 'function toDo(attachmentNum,isNew){if(isNew){return attachmentNum > 0;}else{return attachmentNum >= 0;}}'
WHERE attach_main_type = 'carBuyingContract' AND phase = 'payment' AND financial_code = 'CMBC';























##2018-09-01

ALTER TABLE k_asset_attachment_rule ADD COLUMN merger_attach_type VARCHAR (512) DEFAULT NULL AFTER attach_main_type;

CREATE UNIQUE INDEX index_1 ON k_asset_contract_task (`venus_apply_no`,`file_type`) ;