CREATE TABLE `transaction_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(64) NOT NULL COMMENT '房屋编码',
  `transaction_date` varchar(64) DEFAULT NULL COMMENT '交易日期',
  `transaction_period` int(11) DEFAULT NULL COMMENT '成交周期（天）',
  `deal_price` varchar (64) DEFAULT NULL COMMENT '成交价（万元）',
  `sticker_price` int(11) NOT NULL COMMENT '挂牌价（元）',
  `adjust_count` tinyint(3) DEFAULT NULL COMMENT '调价次数',
  `look_count` int(11) DEFAULT NULL COMMENT '带看次数',
  `follow` int(11) DEFAULT NULL COMMENT '关注人数',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_code` (`code`) COMMENT '房屋编码唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='交易信息';