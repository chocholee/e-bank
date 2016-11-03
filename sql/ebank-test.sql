/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : ebank-test

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2016-11-03 18:25:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cloudwalk_function
-- ----------------------------
DROP TABLE IF EXISTS `cloudwalk_function`;
CREATE TABLE `cloudwalk_function` (
  `id` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `order` int(11) DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `uri` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `parent` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl167anypnvplsjiit022xu8sy` (`parent`),
  KEY `FK8tiybqeydwfpxdkxj40esexpw` (`icon`),
  CONSTRAINT `FK8tiybqeydwfpxdkxj40esexpw` FOREIGN KEY (`icon`) REFERENCES `cloudwalk_icon` (`id`),
  CONSTRAINT `FKl167anypnvplsjiit022xu8sy` FOREIGN KEY (`parent`) REFERENCES `cloudwalk_function` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cloudwalk_function
-- ----------------------------
INSERT INTO `cloudwalk_function` VALUES ('4028a8c457d05fe80157d07f37630002', '', '基础设置', '基础设置', '1000', 'FIRST', '', '0', null, null);
INSERT INTO `cloudwalk_function` VALUES ('4028a8c457d05fe80157d07f74020003', '', '用户管理', '用户管理', '2000', 'FIRST', '', '0', null, null);
INSERT INTO `cloudwalk_function` VALUES ('4028a8c457d05fe80157d07fa7130004', '', '微信统计', '微信统计', '3000', 'FIRST', '', '0', null, null);
INSERT INTO `cloudwalk_function` VALUES ('4028a8c457d05fe80157d07fd58f0005', '', '高级功能', '高级功能', '4000', 'FIRST', '', '0', null, null);
INSERT INTO `cloudwalk_function` VALUES ('4028a8c457d05fe80157d0800cdb0006', '', '消息管理', '消息管理', '5000', 'FIRST', '', '0', null, null);
INSERT INTO `cloudwalk_function` VALUES ('4028a8c457d05fe80157d08036eb0007', '', '系统管理', '系统管理', '6000', 'FIRST', '', '0', null, null);
INSERT INTO `cloudwalk_function` VALUES ('4028a8c457d05fe80157d0812b860008', '', '公众账号管理', '公众账号管理', '1010', 'SECOND', '/weixin/account/list', '0', '4028a8c457d05fe80157d07f37630002', null);
INSERT INTO `cloudwalk_function` VALUES ('4028a8c457d05fe80157d081742d0009', '', '关注欢迎语', '关注欢迎语', '1020', 'SECOND', '', '0', '4028a8c457d05fe80157d07f37630002', null);
INSERT INTO `cloudwalk_function` VALUES ('4028a8c457d05fe80157d081b3fe000a', '', '关键字管理', '关键字管理', '1030', 'SECOND', '', '0', '4028a8c457d05fe80157d07f37630002', null);
INSERT INTO `cloudwalk_function` VALUES ('4028a8c457d05fe80157d08208aa000b', '', '自定义菜单', '自定义菜单', '1040', 'SECOND', '', '0', '4028a8c457d05fe80157d07f37630002', null);
INSERT INTO `cloudwalk_function` VALUES ('4028a8c457d05fe80157d0828d21000c', '', '微信用户', '微信用户', '2010', 'SECOND', '', '0', '4028a8c457d05fe80157d07f74020003', null);
INSERT INTO `cloudwalk_function` VALUES ('4028a8c457d05fe80157d082df57000d', '', '微信分组', '微信分组', '2020', 'SECOND', '', '0', '4028a8c457d05fe80157d07f74020003', null);
INSERT INTO `cloudwalk_function` VALUES ('4028a8c457d05fe80157d083301e000e', '', '用户分析', '用户分析', '3010', 'SECOND', '', '0', '4028a8c457d05fe80157d07fa7130004', null);
INSERT INTO `cloudwalk_function` VALUES ('4028a8c457d05fe80157d0835e20000f', '', '图文分析', '图文分析', '3020', 'SECOND', '', '0', '4028a8c457d05fe80157d07fa7130004', null);
INSERT INTO `cloudwalk_function` VALUES ('4028a8c457d05fe80157d08386660010', '', '消息分析', '消息分析', '3030', 'SECOND', '', '0', '4028a8c457d05fe80157d07fa7130004', null);
INSERT INTO `cloudwalk_function` VALUES ('4028a8c457d05fe80157d083b8700011', '', '接口分析', '接口分析', '3040', 'SECOND', '', '0', '4028a8c457d05fe80157d07fa7130004', null);
INSERT INTO `cloudwalk_function` VALUES ('4028a8c457d05fe80157d0851b720012', '', '接受消息', '接受消息', '4010', 'SECOND', '', '0', '4028a8c457d05fe80157d07fd58f0005', null);
INSERT INTO `cloudwalk_function` VALUES ('4028a8c457d05fe80157d0857bd70013', '', '微信二维码', '微信二维码', '4020', 'SECOND', '', '0', '4028a8c457d05fe80157d07fd58f0005', null);
INSERT INTO `cloudwalk_function` VALUES ('4028a8c457d05fe80157d085af310014', '', '微信场景', '微信场景', '4030', 'SECOND', '', '0', '4028a8c457d05fe80157d07fd58f0005', null);
INSERT INTO `cloudwalk_function` VALUES ('4028a8c457d05fe80157d085deb70015', '', '扩展接口', '扩展接口', '4040', 'SECOND', '', '0', '4028a8c457d05fe80157d07fd58f0005', null);
INSERT INTO `cloudwalk_function` VALUES ('4028a8c457d05fe80157d086418e0016', '', '文本消息', '文本消息', '5010', 'SECOND', '', '0', '4028a8c457d05fe80157d0800cdb0006', null);
INSERT INTO `cloudwalk_function` VALUES ('4028a8c457d05fe80157d0867a6e0017', '', '图文消息', '图文消息', '5020', 'SECOND', '', '0', '4028a8c457d05fe80157d0800cdb0006', null);
INSERT INTO `cloudwalk_function` VALUES ('4028a8c457d05fe80157d086b3c10018', '', '微信群发', '微信群发', '5030', 'SECOND', '', '0', '4028a8c457d05fe80157d0800cdb0006', null);
INSERT INTO `cloudwalk_function` VALUES ('4028a8c457d05fe80157d086f3cb0019', '', '用户管理', '用户管理', '6010', 'SECOND', '/user/list', '0', '4028a8c457d05fe80157d08036eb0007', null);
INSERT INTO `cloudwalk_function` VALUES ('4028a8c457d05fe80157d087327d001a', '', '角色管理', '角色管理', '6020', 'SECOND', '/role/list', '0', '4028a8c457d05fe80157d08036eb0007', null);
INSERT INTO `cloudwalk_function` VALUES ('4028a8c457d05fe80157d0875dbe001b', '', '菜单管理', '菜单管理', '6030', 'SECOND', '/function/list', '0', '4028a8c457d05fe80157d08036eb0007', null);
INSERT INTO `cloudwalk_function` VALUES ('4028a8c457d05fe80157d0878d56001c', '', '数据字典', '数据字典', '6040', 'SECOND', '', '0', '4028a8c457d05fe80157d08036eb0007', null);
INSERT INTO `cloudwalk_function` VALUES ('4028a8c457d05fe80157d087cd08001d', '', '图标管理', '图标管理', '6050', 'SECOND', '', '0', '4028a8c457d05fe80157d08036eb0007', null);

-- ----------------------------
-- Table structure for cloudwalk_icon
-- ----------------------------
DROP TABLE IF EXISTS `cloudwalk_icon`;
CREATE TABLE `cloudwalk_icon` (
  `id` varchar(255) NOT NULL,
  `after_hover_path` varchar(255) DEFAULT NULL,
  `before_hover_path` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `suffix` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cloudwalk_icon
-- ----------------------------
INSERT INTO `cloudwalk_icon` VALUES ('0', '/resources/images/ico_Monitor_h.png', '/resources/images/ico_Monitor_n.png', '首页图标', '首页图标', 'png', 'SYS', '0');

-- ----------------------------
-- Table structure for cloudwalk_role
-- ----------------------------
DROP TABLE IF EXISTS `cloudwalk_role`;
CREATE TABLE `cloudwalk_role` (
  `id` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `order` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `parent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `FKasdf212312c112c11231` (`name`) USING BTREE,
  KEY `FKd2hxuue70na9k2yk2fnslm3n2` (`parent`),
  CONSTRAINT `FKd2hxuue70na9k2yk2fnslm3n2` FOREIGN KEY (`parent`) REFERENCES `cloudwalk_role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cloudwalk_role
-- ----------------------------
INSERT INTO `cloudwalk_role` VALUES ('4028a8c458295d1b015829b42b320000', '管理员', 'administrator', '0', '0', null);

-- ----------------------------
-- Table structure for cloudwalk_role_function
-- ----------------------------
DROP TABLE IF EXISTS `cloudwalk_role_function`;
CREATE TABLE `cloudwalk_role_function` (
  `role_id` varchar(255) NOT NULL,
  `function_id` varchar(255) NOT NULL,
  PRIMARY KEY (`role_id`,`function_id`),
  KEY `FKsf7129nirdw9ct6yo3bshpq1h` (`function_id`),
  CONSTRAINT `FKnfe23569q633mwxwkl8sdo9qb` FOREIGN KEY (`role_id`) REFERENCES `cloudwalk_role` (`id`),
  CONSTRAINT `FKsf7129nirdw9ct6yo3bshpq1h` FOREIGN KEY (`function_id`) REFERENCES `cloudwalk_function` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cloudwalk_role_function
-- ----------------------------
INSERT INTO `cloudwalk_role_function` VALUES ('4028a8c458295d1b015829b42b320000', '4028a8c457d05fe80157d07f37630002');
INSERT INTO `cloudwalk_role_function` VALUES ('4028a8c458295d1b015829b42b320000', '4028a8c457d05fe80157d07f74020003');
INSERT INTO `cloudwalk_role_function` VALUES ('4028a8c458295d1b015829b42b320000', '4028a8c457d05fe80157d07fa7130004');
INSERT INTO `cloudwalk_role_function` VALUES ('4028a8c458295d1b015829b42b320000', '4028a8c457d05fe80157d07fd58f0005');
INSERT INTO `cloudwalk_role_function` VALUES ('4028a8c458295d1b015829b42b320000', '4028a8c457d05fe80157d0800cdb0006');
INSERT INTO `cloudwalk_role_function` VALUES ('4028a8c458295d1b015829b42b320000', '4028a8c457d05fe80157d08036eb0007');
INSERT INTO `cloudwalk_role_function` VALUES ('4028a8c458295d1b015829b42b320000', '4028a8c457d05fe80157d0812b860008');
INSERT INTO `cloudwalk_role_function` VALUES ('4028a8c458295d1b015829b42b320000', '4028a8c457d05fe80157d081742d0009');
INSERT INTO `cloudwalk_role_function` VALUES ('4028a8c458295d1b015829b42b320000', '4028a8c457d05fe80157d081b3fe000a');
INSERT INTO `cloudwalk_role_function` VALUES ('4028a8c458295d1b015829b42b320000', '4028a8c457d05fe80157d08208aa000b');
INSERT INTO `cloudwalk_role_function` VALUES ('4028a8c458295d1b015829b42b320000', '4028a8c457d05fe80157d0828d21000c');
INSERT INTO `cloudwalk_role_function` VALUES ('4028a8c458295d1b015829b42b320000', '4028a8c457d05fe80157d082df57000d');
INSERT INTO `cloudwalk_role_function` VALUES ('4028a8c458295d1b015829b42b320000', '4028a8c457d05fe80157d083301e000e');
INSERT INTO `cloudwalk_role_function` VALUES ('4028a8c458295d1b015829b42b320000', '4028a8c457d05fe80157d0835e20000f');
INSERT INTO `cloudwalk_role_function` VALUES ('4028a8c458295d1b015829b42b320000', '4028a8c457d05fe80157d08386660010');
INSERT INTO `cloudwalk_role_function` VALUES ('4028a8c458295d1b015829b42b320000', '4028a8c457d05fe80157d083b8700011');
INSERT INTO `cloudwalk_role_function` VALUES ('4028a8c458295d1b015829b42b320000', '4028a8c457d05fe80157d0851b720012');
INSERT INTO `cloudwalk_role_function` VALUES ('4028a8c458295d1b015829b42b320000', '4028a8c457d05fe80157d0857bd70013');
INSERT INTO `cloudwalk_role_function` VALUES ('4028a8c458295d1b015829b42b320000', '4028a8c457d05fe80157d085af310014');
INSERT INTO `cloudwalk_role_function` VALUES ('4028a8c458295d1b015829b42b320000', '4028a8c457d05fe80157d085deb70015');
INSERT INTO `cloudwalk_role_function` VALUES ('4028a8c458295d1b015829b42b320000', '4028a8c457d05fe80157d086418e0016');
INSERT INTO `cloudwalk_role_function` VALUES ('4028a8c458295d1b015829b42b320000', '4028a8c457d05fe80157d0867a6e0017');
INSERT INTO `cloudwalk_role_function` VALUES ('4028a8c458295d1b015829b42b320000', '4028a8c457d05fe80157d086b3c10018');
INSERT INTO `cloudwalk_role_function` VALUES ('4028a8c458295d1b015829b42b320000', '4028a8c457d05fe80157d086f3cb0019');
INSERT INTO `cloudwalk_role_function` VALUES ('4028a8c458295d1b015829b42b320000', '4028a8c457d05fe80157d087327d001a');
INSERT INTO `cloudwalk_role_function` VALUES ('4028a8c458295d1b015829b42b320000', '4028a8c457d05fe80157d0875dbe001b');
INSERT INTO `cloudwalk_role_function` VALUES ('4028a8c458295d1b015829b42b320000', '4028a8c457d05fe80157d0878d56001c');
INSERT INTO `cloudwalk_role_function` VALUES ('4028a8c458295d1b015829b42b320000', '4028a8c457d05fe80157d087cd08001d');

-- ----------------------------
-- Table structure for cloudwalk_user
-- ----------------------------
DROP TABLE IF EXISTS `cloudwalk_user`;
CREATE TABLE `cloudwalk_user` (
  `id` varchar(255) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_login_date` datetime DEFAULT NULL,
  `login_date` datetime DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `salt` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `updated_date` datetime DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `parent` varchar(255) DEFAULT NULL,
  `login_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_efxc8i6kyirkrk3johtjy7ix7` (`username`),
  KEY `FK4t2p04rp2p6ck85wml6hqvky7` (`parent`),
  CONSTRAINT `FK4t2p04rp2p6ck85wml6hqvky7` FOREIGN KEY (`parent`) REFERENCES `cloudwalk_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cloudwalk_user
-- ----------------------------
INSERT INTO `cloudwalk_user` VALUES ('4028a8c4576f851c01576f86c5db0001', '2016-09-01 15:35:25', '2016-09-28 14:57:31', '2016-09-28 14:57:58', 'f64e9acaaed8d43bb5f19f17108e2b49', null, null, 'f5eaca2f0c9544d1beb30b42a955fb67', 'ENABLE', '2016-11-03 18:20:51', 'liwenhe', '153', null, null, null, '0');

-- ----------------------------
-- Table structure for cloudwalk_user_role
-- ----------------------------
DROP TABLE IF EXISTS `cloudwalk_user_role`;
CREATE TABLE `cloudwalk_user_role` (
  `user_id` varchar(255) NOT NULL,
  `role_id` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKmt6vnfalbnotnekupgh8guqcd` (`role_id`),
  CONSTRAINT `FKb22w5ff16gwuaus8lctk6xgom` FOREIGN KEY (`user_id`) REFERENCES `cloudwalk_user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKmt6vnfalbnotnekupgh8guqcd` FOREIGN KEY (`role_id`) REFERENCES `cloudwalk_role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cloudwalk_user_role
-- ----------------------------
INSERT INTO `cloudwalk_user_role` VALUES ('4028a8c4576f851c01576f86c5db0001', '4028a8c458295d1b015829b42b320000');

-- ----------------------------
-- Table structure for cloudwalk_weixin_account
-- ----------------------------
DROP TABLE IF EXISTS `cloudwalk_weixin_account`;
CREATE TABLE `cloudwalk_weixin_account` (
  `id` varchar(255) NOT NULL,
  `access_token` varchar(255) DEFAULT NULL,
  `access_token_time` datetime DEFAULT NULL,
  `account_id` varchar(255) DEFAULT NULL,
  `api_ticket` varchar(255) DEFAULT NULL,
  `api_ticket_time` datetime DEFAULT NULL,
  `app_id` varchar(255) DEFAULT NULL,
  `app_secret` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `js_api_ticket` varchar(255) DEFAULT NULL,
  `js_api_ticket_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_lty7poe3we70cn6uhqsa0ekc8` (`user_id`),
  CONSTRAINT `FKr8xa7sefrngkpirtkkqjk3x7m` FOREIGN KEY (`user_id`) REFERENCES `cloudwalk_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cloudwalk_weixin_account
-- ----------------------------
INSERT INTO `cloudwalk_weixin_account` VALUES ('1', '_9ie1_X3cVfRoiFy3oHZtGbHVFgn7HFP2f20ajcV8s7iQyU4eLZUnFtUq0Wq0zx0THHC8BHm-nZZm61oVYBrHh1W6Qv_t12gUedkT4rjqETQHBYOhIwLXRLa7b7gGtj0MLSeAEAUTA', '2016-10-19 18:02:43', 'gh_ad4593288265', null, null, 'wx4458e6569617498d', 'dbe4e50e8465023eee2a489216870375', '测试号', '411934049@qq.com', 'sM4AOVdWfPE4DxkXGEs8VJEOS0tX-gvHq5V0f7REBu5UlfCURh5LQ5GlapFmzls21nr9nN9O769OhB5UzNTy7Q', '2016-10-19 18:02:44', '测试号', 'gh_ad4593288265', 'douleha', 'SERVICE', '10', '4028a8c4576f851c01576f86c5db0001', null);
INSERT INTO `cloudwalk_weixin_account` VALUES ('4028a8c45779fa7101577a051da40000', 'gHNZMQXk1zDfo9L8oKIZ0-dc3i6xoQR9jr7FwRkf3TEhvty4MVMHzRPaTL0i-YR1EMfTY0vM2MV82pllaz-ncHl_oIbYqz7ksIRWkHFTrfPa8bBm4NpwPO9a12UFqPS3TQUfAEAWTX', '2016-10-31 10:18:12', 'gh_fe03330455f1', null, null, 'wxf06423a37276c001', 'e822979c881d24076c5a6c9f5813eb69', '小熊的', 'xiaoxiong@xx.com', 'kgt8ON7yVITDhtdwci0qeVCfoNIQgxWElZq6T9QBZghK8Ke_TwA5MI_EWCXAqts5NzYVAxpXcyyFHVSz5xtQDg', '2016-10-31 10:18:12', '小熊的', 'wxf06423a37276c001', 'xiaoxiong', 'SERVICE', '18', null, '2016-09-30 15:35:38');

-- ----------------------------
-- Table structure for cloudwalk_weixin_keyword
-- ----------------------------
DROP TABLE IF EXISTS `cloudwalk_weixin_keyword`;
CREATE TABLE `cloudwalk_weixin_keyword` (
  `id` varchar(255) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `msg_type` varchar(255) DEFAULT NULL,
  `template_id` varchar(255) DEFAULT NULL,
  `template_name` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `account_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jmbig3vonans24l2kds6xvh4m` (`keyword`),
  KEY `FK8xrs9s4o8qchng19y7hnl6g31` (`account_id`),
  CONSTRAINT `FK8xrs9s4o8qchng19y7hnl6g31` FOREIGN KEY (`account_id`) REFERENCES `cloudwalk_weixin_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cloudwalk_weixin_keyword
-- ----------------------------
INSERT INTO `cloudwalk_weixin_keyword` VALUES ('1', '2016-10-10 17:40:01', '你好', 'TEXT', '1', '你好', '0', '1');
INSERT INTO `cloudwalk_weixin_keyword` VALUES ('2', '2016-10-10 17:45:42', '中科云从', 'NEWS', '1', '中科云从', '0', '1');

-- ----------------------------
-- Table structure for cloudwalk_weixin_menu
-- ----------------------------
DROP TABLE IF EXISTS `cloudwalk_weixin_menu`;
CREATE TABLE `cloudwalk_weixin_menu` (
  `id` varchar(255) NOT NULL,
  `key` varchar(255) DEFAULT NULL,
  `msg_type` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `order` int(11) DEFAULT NULL,
  `template_id` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `account_id` varchar(255) NOT NULL,
  `parent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKji7ub9bk0jp3b6kxw6pw9kr8l` (`account_id`),
  KEY `FKiuf68xt8todaebam074nqxsrc` (`parent`),
  CONSTRAINT `FKiuf68xt8todaebam074nqxsrc` FOREIGN KEY (`parent`) REFERENCES `cloudwalk_weixin_menu` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FKji7ub9bk0jp3b6kxw6pw9kr8l` FOREIGN KEY (`account_id`) REFERENCES `cloudwalk_weixin_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cloudwalk_weixin_menu
-- ----------------------------
INSERT INTO `cloudwalk_weixin_menu` VALUES ('2', 'test', 'TEXT', '你说呢', '2000', '321321321', 'CLICK', null, '0', '4028a8c45779fa7101577a051da40000', null);
INSERT INTO `cloudwalk_weixin_menu` VALUES ('4028a8c457a3633b0157a36a53cd0002', 'nishuone111', 'TEXT', '你说呢111', '1010', '123123', 'CLICK', '', '0', '4028a8c45779fa7101577a051da40000', '2');
INSERT INTO `cloudwalk_weixin_menu` VALUES ('4028a8c457daf1610157db2887a20001', '123123', null, '用户管理', null, '', 'VIEW', 'http://www.baidu.com', '0', '1', null);

-- ----------------------------
-- Table structure for cloudwalk_weixin_receive
-- ----------------------------
DROP TABLE IF EXISTS `cloudwalk_weixin_receive`;
CREATE TABLE `cloudwalk_weixin_receive` (
  `id` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `from_user_name` varchar(255) DEFAULT NULL,
  `is_response` bit(1) DEFAULT NULL,
  `msg_id` varchar(255) DEFAULT NULL,
  `msg_type` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `response` varchar(255) DEFAULT NULL,
  `to_user_name` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `account_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKep8hofh4hxntssn5amqhb2h1y` (`account_id`),
  CONSTRAINT `FKep8hofh4hxntssn5amqhb2h1y` FOREIGN KEY (`account_id`) REFERENCES `cloudwalk_weixin_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cloudwalk_weixin_receive
-- ----------------------------
INSERT INTO `cloudwalk_weixin_receive` VALUES ('4028a8c457b173010157b1747a530000', '测试', '2016-10-11 09:56:23', 'oGOUTs98quXJxdTK96cN9FabD8EQ', '\0', '6340020123357929054', 'text', '里抽抽', null, 'gh_ad4593288265', '0', '1');

-- ----------------------------
-- Table structure for cloudwalk_weixin_template_expand
-- ----------------------------
DROP TABLE IF EXISTS `cloudwalk_weixin_template_expand`;
CREATE TABLE `cloudwalk_weixin_template_expand` (
  `id` varchar(255) NOT NULL,
  `classname` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `account_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhvltdrtt8jjhu6f0478ba9p4q` (`account_id`),
  CONSTRAINT `FKhvltdrtt8jjhu6f0478ba9p4q` FOREIGN KEY (`account_id`) REFERENCES `cloudwalk_weixin_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cloudwalk_weixin_template_expand
-- ----------------------------

-- ----------------------------
-- Table structure for cloudwalk_weixin_template_news
-- ----------------------------
DROP TABLE IF EXISTS `cloudwalk_weixin_template_news`;
CREATE TABLE `cloudwalk_weixin_template_news` (
  `id` varchar(255) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `account_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2glr1563l8k0fkkcqtw3ux7a7` (`account_id`),
  CONSTRAINT `FK2glr1563l8k0fkkcqtw3ux7a7` FOREIGN KEY (`account_id`) REFERENCES `cloudwalk_weixin_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cloudwalk_weixin_template_news
-- ----------------------------
INSERT INTO `cloudwalk_weixin_template_news` VALUES ('1', '2016-10-10 17:45:14', '中科云从', '0', '1');

-- ----------------------------
-- Table structure for cloudwalk_weixin_template_news_items
-- ----------------------------
DROP TABLE IF EXISTS `cloudwalk_weixin_template_news_items`;
CREATE TABLE `cloudwalk_weixin_template_news_items` (
  `id` varchar(255) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `order` int(11) DEFAULT NULL,
  `pic_url` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `account_id` varchar(255) NOT NULL,
  `news_template_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3ku00njoaxny7hidn1gogtb5p` (`account_id`),
  KEY `FKflbdth3qkiy0pjwxwqe9e51q` (`news_template_id`),
  CONSTRAINT `FK3ku00njoaxny7hidn1gogtb5p` FOREIGN KEY (`account_id`) REFERENCES `cloudwalk_weixin_account` (`id`),
  CONSTRAINT `FKflbdth3qkiy0pjwxwqe9e51q` FOREIGN KEY (`news_template_id`) REFERENCES `cloudwalk_weixin_template_news` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cloudwalk_weixin_template_news_items
-- ----------------------------
INSERT INTO `cloudwalk_weixin_template_news_items` VALUES ('0', '李文禾', null, '2016-10-10 17:46:24', '关于我们', '0', 'http://www.cloudwalk.cn/images/logo2.png', '关于我们', 'http://www.cloudwalk.cn/about_media.html', '0', '1', '1');
INSERT INTO `cloudwalk_weixin_template_news_items` VALUES ('1', '李文禾', null, '2016-10-10 17:48:34', '典型案例', '1', 'http://www.cloudwalk.cn/images/logo2.png', '典型案例', 'http://www.cloudwalk.cn/caseList.html', '0', '1', '1');
INSERT INTO `cloudwalk_weixin_template_news_items` VALUES ('2', '李文禾', null, '2016-10-10 17:49:20', '产品中心', '2', 'http://www.cloudwalk.cn/images/logo2.png', '产品中心', 'http://www.cloudwalk.cn/product.html', '0', '1', '1');

-- ----------------------------
-- Table structure for cloudwalk_weixin_template_text
-- ----------------------------
DROP TABLE IF EXISTS `cloudwalk_weixin_template_text`;
CREATE TABLE `cloudwalk_weixin_template_text` (
  `id` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `account_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK22i93w11su0u8is4yyxx6coix` (`account_id`),
  CONSTRAINT `FK22i93w11su0u8is4yyxx6coix` FOREIGN KEY (`account_id`) REFERENCES `cloudwalk_weixin_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cloudwalk_weixin_template_text
-- ----------------------------
INSERT INTO `cloudwalk_weixin_template_text` VALUES ('1', '你好，我们这里是云丛科技公众号', '2016-10-10 17:40:59', '你好', '0', '1');
