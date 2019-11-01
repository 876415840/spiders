CREATE TABLE `house_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(256) NOT NULL COMMENT '标题',
  `code` varchar(16) NOT NULL COMMENT '房屋编码',
  `housing_estate` varchar(16) DEFAULT NULL COMMENT '小区',
  `specification` varchar(16) DEFAULT NULL COMMENT '规格',
  `size` int(11) NOT NULL COMMENT '房屋大小(平方分米)',
  `orientation` varchar(8) DEFAULT NULL COMMENT '朝向',
  `decoration_type` varchar(8) DEFAULT NULL COMMENT '装修类型',
  `elevator` tinyint(1) DEFAULT NULL COMMENT '是否有电梯',
  `storey_type` varchar(16) DEFAULT NULL COMMENT '楼层类型',
  `year` int(4) DEFAULT NULL COMMENT '年份',
  `tower_type` varchar(16) DEFAULT NULL COMMENT '楼类型',
  `area` varchar(16) DEFAULT NULL COMMENT '地区',
  `total_price` int(11) NOT NULL COMMENT '总价（元）',
  `type` varchar(16) DEFAULT NULL COMMENT '类型',
  `unit_price` int(11) DEFAULT NULL COMMENT '单价（元）',
  `subway` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否近地铁',
  `vr` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否VR防御',
  `taxfree` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否房本满五年',
  `any_time` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否随时看房',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_code` (`code`) COMMENT '房屋编码唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='二手房信息';

CREATE TABLE `price_change` (
	`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`house_code` varchar(16) NOT NULL COMMENT '房屋编码',
	`total_price` int(11) NOT NULL COMMENT '总价(元)',
	`unit_price` int(11) NOT NULL COMMENT '单价(元)',
	`create_time` datetime NOT NULL COMMENT '创建时间',
	`update_time` datetime NOT NULL COMMENT '更新时间',
	PRIMARY KEY (`id`)
) COMMENT='价格变动记录';