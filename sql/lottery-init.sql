CREATE TABLE `shuang_se_qiu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `period` int(7) NOT NULL COMMENT '期数',
  `red1` varchar(2) NOT NULL COMMENT '红1',
  `red2` varchar(2) NOT NULL COMMENT '红2',
  `red3` varchar(2) NOT NULL COMMENT '红3',
  `red4` varchar(2) NOT NULL COMMENT '红4',
  `red5` varchar(2) NOT NULL COMMENT '红5',
  `red6` varchar(2) NOT NULL COMMENT '红6',
  `blue` varchar(2) NOT NULL COMMENT '蓝球',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 COMMENT='双色球';
