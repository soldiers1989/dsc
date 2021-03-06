CREATE TABLE dsc_sys_log (
    `id` varchar(50) NOT NULL,
    `bz_id` varchar(50) DEFAULT NULL,
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `creator_department_id` bigint(20) DEFAULT NULL,
    `creator_department_name` varchar(255) DEFAULT NULL,
    `creator_id` varchar(255) DEFAULT NULL,
    `creator_name` varchar(255) DEFAULT NULL,
    `is_deleted` bit(1) DEFAULT NULL COMMENT '是否删除',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `updator_department_id` bigint(20) DEFAULT NULL,
    `updator_department_name` varchar(255) DEFAULT NULL,
    `updator_id` varchar(255) DEFAULT NULL,
    `updator_name` varchar(255) DEFAULT NULL,
    `version` int(11) DEFAULT NULL,
    `action` VARCHAR(50) NOT NULL COMMENT '操作动作',
    `params` TEXT DEFAULT NULL COMMENT '方法入参',
    `method` VARCHAR(50) NOT NULL COMMENT '方法名字',
    `name` VARCHAR(50) NOT NULL COMMENT '用户名字',
    `ip_addr` VARCHAR(50) DEFAULT NULL COMMENT '发送请求的ip地址',
    PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;