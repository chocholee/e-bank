/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost
 Source Database       : ebank-test

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : utf-8

 Date: 11/11/2016 13:19:40 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `cloudwalk_function`
-- ----------------------------
DROP TABLE IF EXISTS `cloudwalk_function`;
CREATE TABLE `cloudwalk_function` (
  `id` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `order` int(11) DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `uri` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `parent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8tiybqeydwfpxdkxj40esexpw` (`icon`),
  KEY `FKl167anypnvplsjiit022xu8sy` (`parent`),
  CONSTRAINT `FK8tiybqeydwfpxdkxj40esexpw` FOREIGN KEY (`icon`) REFERENCES `cloudwalk_icon` (`id`),
  CONSTRAINT `FKl167anypnvplsjiit022xu8sy` FOREIGN KEY (`parent`) REFERENCES `cloudwalk_function` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `cloudwalk_function`
-- ----------------------------
BEGIN;
INSERT INTO `cloudwalk_function` VALUES ('402883a758426fb8015842731e0d0000', '系统设置', '系统设置', '2000', 'FIRST', '', null, null), ('402883a758426fb801584278bc6b0002', '用户管理', '用户管理', '2010', 'SECOND', '/user/list', null, '402883a758426fb8015842731e0d0000'), ('402883a758426fb801584279b5b90003', '角色管理', '角色管理', '2020', 'SECOND', '/role/list', null, '402883a758426fb8015842731e0d0000'), ('402883a758426fb80158427a5fbe0004', '菜单管理', '菜单管理', '2030', 'SECOND', '/function/list', null, '402883a758426fb8015842731e0d0000'), ('402883a7584311ec01584312abee0000', '图标管理', '图标管理', '2040', 'SECOND', '/icon/list', null, '402883a758426fb8015842731e0d0000'), ('402883a7584d385f01584d3a490e0000', '基础设置', '基础设置', '1000', 'FIRST', '', null, null), ('402883a7584d385f01584d3aec8f0001', '公众号管理', '公众号管理', '1010', 'SECOND', '/weixin/account/list', null, '402883a7584d385f01584d3a490e0000'), ('402883a758518ba50158518cf56f0000', '公众号添加操作', '添加', '1', 'THIRD', '/weixin/account/add', null, '402883a7584d385f01584d3aec8f0001'), ('402883a758518ba50158518e04c00001', '公众号查看操作', '查看', '2', 'THIRD', '/weixin/account/view/*', null, '402883a7584d385f01584d3aec8f0001'), ('402883a758518ba50158518ed3b80002', '公众号编辑操作', '编辑', '3', 'THIRD', '/weixin/account/edit/*', null, '402883a7584d385f01584d3aec8f0001'), ('402883a758518ba50158518f99c20003', '公众号删除操作', '删除', '4', 'THIRD', '/weixin/account/delete/*', null, '402883a7584d385f01584d3aec8f0001'), ('402883a758518ba501585192efcf0004', '重置微信Token操作', '重置微信Token', '5', 'THIRD', '/weixin/account/token/*', null, '402883a7584d385f01584d3aec8f0001'), ('402883a758519405015851b258530001', '用户添加操作', '添加', '1', 'THIRD', '/user/add', null, '402883a758426fb801584278bc6b0002'), ('402883a758519405015851b2f0d70002', '用户查看操作', '查看', '2', 'THIRD', '/user/view/*', null, '402883a758426fb801584278bc6b0002'), ('402883a758519405015851b377900003', '用户编辑操作', '编辑', '3', 'THIRD', '/user/edit/*', null, '402883a758426fb801584278bc6b0002'), ('402883a758519405015851b416330004', '用户删除操作', '删除', '4', 'THIRD', '/user/delete/*', null, '402883a758426fb801584278bc6b0002'), ('402883a758519405015851b50c800005', '用户授权操作', '授权', '5', 'THIRD', '/user/authorize/*', null, '402883a758426fb801584278bc6b0002'), ('402883a758519405015851b5d22d0006', '角色添加操作', '添加', '1', 'THIRD', '/role/add', null, '402883a758426fb801584279b5b90003'), ('402883a758519405015851b663250007', '角色编辑操作', '编辑', '2', 'THIRD', '/role/edit/*', null, '402883a758426fb801584279b5b90003'), ('402883a758519405015851b6e1a80008', '角色删除操作', '删除', '3', 'THIRD', '/role/delete/*', null, '402883a758426fb801584279b5b90003'), ('402883a758519405015851b787510009', '角色授权操作', '授权', '4', 'THIRD', '/role/authorize/*', null, '402883a758426fb801584279b5b90003'), ('402883a758519405015851b882ee000a', '菜单添加操作', '添加', '1', 'THIRD', '/function/add', null, '402883a758426fb80158427a5fbe0004'), ('402883a758519405015851b91576000b', '菜单查看操作', '查看', '2', 'THIRD', '/function/view/*', null, '402883a758426fb80158427a5fbe0004'), ('402883a758519405015851b9beec000c', '菜单编辑操作', '编辑', '3', 'THIRD', '/function/edit/*', null, '402883a758426fb80158427a5fbe0004'), ('402883a758519405015851ba46ad000d', '菜单删除操作', '删除', '4', 'THIRD', '/function/delete/*', null, '402883a758426fb80158427a5fbe0004'), ('402883a758519405015851bb0044000e', '图标添加操作', '添加', '1', 'THIRD', '/icon/add', null, '402883a7584311ec01584312abee0000'), ('402883a758519405015851bb86c4000f', '图标查看操作', '查看', '2', 'THIRD', '/icon/view/*', null, '402883a7584311ec01584312abee0000'), ('402883a758519405015851bc2ae40010', '图标编辑操作', '编辑', '3', 'THIRD', '/icon/edit/*', null, '402883a7584311ec01584312abee0000'), ('402883a758519405015851bcd8e80011', '图标删除操作', '删除', '4', 'THIRD', '/icon/delete/*', null, '402883a7584311ec01584312abee0000');
COMMIT;

-- ----------------------------
--  Table structure for `cloudwalk_icon`
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `cloudwalk_icon`
-- ----------------------------
BEGIN;
INSERT INTO `cloudwalk_icon` VALUES ('40281b82584c616401584c680c180000', 'icon_20161110120355507.jpg', 'icon_20161110120351853.jpg', '1', '1', '.jpg', null);
COMMIT;

-- ----------------------------
--  Table structure for `cloudwalk_role`
-- ----------------------------
DROP TABLE IF EXISTS `cloudwalk_role`;
CREATE TABLE `cloudwalk_role` (
  `id` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `order` int(11) DEFAULT NULL,
  `parent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bwkumwv93tgc9rhooumyni0xr` (`name`),
  KEY `FKd2hxuue70na9k2yk2fnslm3n2` (`parent`),
  CONSTRAINT `FKd2hxuue70na9k2yk2fnslm3n2` FOREIGN KEY (`parent`) REFERENCES `cloudwalk_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `cloudwalk_role`
-- ----------------------------
BEGIN;
INSERT INTO `cloudwalk_role` VALUES ('402883a758426fb80158427aeb2a0005', '管理员', 'administrator', '0', null), ('402883a7584d432601584d5112990000', '微信角色', 'weixin', '1', null);
COMMIT;

-- ----------------------------
--  Table structure for `cloudwalk_role_function`
-- ----------------------------
DROP TABLE IF EXISTS `cloudwalk_role_function`;
CREATE TABLE `cloudwalk_role_function` (
  `role_id` varchar(255) NOT NULL,
  `function_id` varchar(255) NOT NULL,
  KEY `FKsf7129nirdw9ct6yo3bshpq1h` (`function_id`),
  KEY `FKnfe23569q633mwxwkl8sdo9qb` (`role_id`),
  CONSTRAINT `FKnfe23569q633mwxwkl8sdo9qb` FOREIGN KEY (`role_id`) REFERENCES `cloudwalk_role` (`id`),
  CONSTRAINT `FKsf7129nirdw9ct6yo3bshpq1h` FOREIGN KEY (`function_id`) REFERENCES `cloudwalk_function` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `cloudwalk_role_function`
-- ----------------------------
BEGIN;
INSERT INTO `cloudwalk_role_function` VALUES ('402883a758426fb80158427aeb2a0005', '402883a758519405015851b5d22d0006'), ('402883a758426fb80158427aeb2a0005', '402883a758519405015851b787510009'), ('402883a758426fb80158427aeb2a0005', '402883a758519405015851ba46ad000d'), ('402883a758426fb80158427aeb2a0005', '402883a758426fb801584279b5b90003'), ('402883a758426fb80158427aeb2a0005', '402883a758519405015851b2f0d70002'), ('402883a758426fb80158427aeb2a0005', '402883a758519405015851b50c800005'), ('402883a758426fb80158427aeb2a0005', '402883a758519405015851b882ee000a'), ('402883a758426fb80158427aeb2a0005', '402883a758519405015851bb86c4000f'), ('402883a758426fb80158427aeb2a0005', '402883a758426fb8015842731e0d0000'), ('402883a758426fb80158427aeb2a0005', '402883a758518ba50158518f99c20003'), ('402883a758426fb80158427aeb2a0005', '402883a758519405015851bc2ae40010'), ('402883a758426fb80158427aeb2a0005', '402883a758518ba50158518ed3b80002'), ('402883a758426fb80158427aeb2a0005', '402883a7584311ec01584312abee0000'), ('402883a758426fb80158427aeb2a0005', '402883a758518ba50158518cf56f0000'), ('402883a758426fb80158427aeb2a0005', '402883a758519405015851b258530001'), ('402883a758426fb80158427aeb2a0005', '402883a758519405015851bb0044000e'), ('402883a758426fb80158427aeb2a0005', '402883a758519405015851b6e1a80008'), ('402883a758426fb80158427aeb2a0005', '402883a758519405015851b9beec000c'), ('402883a758426fb80158427aeb2a0005', '402883a758519405015851b416330004'), ('402883a758426fb80158427aeb2a0005', '402883a758519405015851b377900003'), ('402883a758426fb80158427aeb2a0005', '402883a758426fb801584278bc6b0002'), ('402883a758426fb80158427aeb2a0005', '402883a758519405015851b91576000b'), ('402883a758426fb80158427aeb2a0005', '402883a758519405015851b663250007'), ('402883a758426fb80158427aeb2a0005', '402883a758426fb80158427a5fbe0004'), ('402883a758426fb80158427aeb2a0005', '402883a7584d385f01584d3a490e0000'), ('402883a758426fb80158427aeb2a0005', '402883a758518ba50158518e04c00001'), ('402883a758426fb80158427aeb2a0005', '402883a758519405015851bcd8e80011'), ('402883a758426fb80158427aeb2a0005', '402883a7584d385f01584d3aec8f0001'), ('402883a758426fb80158427aeb2a0005', '402883a758518ba501585192efcf0004'), ('402883a7584d432601584d5112990000', '402883a7584d385f01584d3a490e0000'), ('402883a7584d432601584d5112990000', '402883a7584d385f01584d3aec8f0001');
COMMIT;

-- ----------------------------
--  Table structure for `cloudwalk_user`
-- ----------------------------
DROP TABLE IF EXISTS `cloudwalk_user`;
CREATE TABLE `cloudwalk_user` (
  `id` varchar(255) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `last_login_date` datetime DEFAULT NULL,
  `login_count` int(11) DEFAULT NULL,
  `login_date` datetime DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `salt` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `updated_date` datetime DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `parent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_efxc8i6kyirkrk3johtjy7ix7` (`username`),
  KEY `FK4t2p04rp2p6ck85wml6hqvky7` (`parent`),
  CONSTRAINT `FK4t2p04rp2p6ck85wml6hqvky7` FOREIGN KEY (`parent`) REFERENCES `cloudwalk_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `cloudwalk_user`
-- ----------------------------
BEGIN;
INSERT INTO `cloudwalk_user` VALUES ('402883a7584280e20158428233700000', '2016-11-08 13:56:19', '411934049@qq.com', '2016-11-11 11:04:04', '0', '2016-11-11 13:14:29', '2afc67a103b602c1ca6a0df7188feda9', '18883871276', '李文禾', '李文禾最帅了', '898db3acb738476db301bf6509a1230e', 'ENABLE', '2016-11-11 13:14:29', 'liwenhe', null), ('402883a7584d432601584d5b8f470004', '2016-11-10 16:29:56', '411934049@qq.com', '2016-11-11 10:42:49', '0', '2016-11-11 10:58:21', '0f2f86c07cdab58581bb2b4dd440998e', '18883871276', '李文禾', '这是李文禾1', '7672e12bdd81446e83117a0fb98e7b1e', 'ENABLE', '2016-11-11 10:58:21', 'liwenhe1', null), ('402883a758514f8001585150d3ac0000', '2016-11-11 10:56:41', '', '2016-11-11 10:57:53', '0', '2016-11-11 13:14:14', '6d55720d85956641bfe2b26e748d65aa', '', '熊小军', '', '9c76ca5e67c44cb68b72a15596b32ca4', 'ENABLE', '2016-11-11 13:14:14', 'june', null);
COMMIT;

-- ----------------------------
--  Table structure for `cloudwalk_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `cloudwalk_user_role`;
CREATE TABLE `cloudwalk_user_role` (
  `user_id` varchar(255) NOT NULL,
  `role_id` varchar(255) NOT NULL,
  KEY `FKmt6vnfalbnotnekupgh8guqcd` (`role_id`),
  KEY `FKb22w5ff16gwuaus8lctk6xgom` (`user_id`),
  CONSTRAINT `FKb22w5ff16gwuaus8lctk6xgom` FOREIGN KEY (`user_id`) REFERENCES `cloudwalk_user` (`id`),
  CONSTRAINT `FKmt6vnfalbnotnekupgh8guqcd` FOREIGN KEY (`role_id`) REFERENCES `cloudwalk_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `cloudwalk_user_role`
-- ----------------------------
BEGIN;
INSERT INTO `cloudwalk_user_role` VALUES ('402883a7584280e20158428233700000', '402883a758426fb80158427aeb2a0005'), ('402883a7584d432601584d5b8f470004', '402883a7584d432601584d5112990000'), ('402883a758514f8001585150d3ac0000', '402883a7584d432601584d5112990000');
COMMIT;

-- ----------------------------
--  Table structure for `cloudwalk_weixin_account`
-- ----------------------------
DROP TABLE IF EXISTS `cloudwalk_weixin_account`;
CREATE TABLE `cloudwalk_weixin_account` (
  `id` varchar(255) NOT NULL,
  `access_token` varchar(255) DEFAULT NULL,
  `access_token_time` datetime DEFAULT NULL,
  `account_id` varchar(255) NOT NULL,
  `api_ticket` varchar(255) DEFAULT NULL,
  `api_ticket_time` datetime DEFAULT NULL,
  `app_id` varchar(255) NOT NULL,
  `app_secret` varchar(255) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `js_api_ticket` varchar(255) DEFAULT NULL,
  `js_api_ticket_time` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `number` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `token` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_lty7poe3we70cn6uhqsa0ekc8` (`user_id`) USING BTREE,
  CONSTRAINT `FKr8xa7sefrngkpirtkkqjk3x7m` FOREIGN KEY (`user_id`) REFERENCES `cloudwalk_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `cloudwalk_weixin_account`
-- ----------------------------
BEGIN;
INSERT INTO `cloudwalk_weixin_account` VALUES ('402883a7584d432601584d61a6710005', null, null, 'gh_fe03330455f1', null, null, 'wxf06423a37276c001', 'e822979c881d24076c5a6c9f5813eb69', '2016-11-10 16:36:35', '小熊的微信测试号', 'xiaoxiong@qq.com', null, null, '小熊的', 'gh_fe03330455f1', 'UNAUTHORIZED', 'xiaoxiong', 'SERVICE', '402883a758514f8001585150d3ac0000'), ('402883a7584d432601584d636db10006', null, null, 'gh_ad4593288265', null, null, 'wx4458e6569617498d', 'dbe4e50e8465023eee2a489216870375', '2016-11-10 16:38:32', '这是李文禾的测试号', '411934049@qq.com', null, null, '测试号', 'gh_ad4593288265', 'AUTHORIZED', 'douleha', 'SERVICE', '402883a7584d432601584d5b8f470004');
COMMIT;

-- ----------------------------
--  Table structure for `cloudwalk_weixin_keyword`
-- ----------------------------
DROP TABLE IF EXISTS `cloudwalk_weixin_keyword`;
CREATE TABLE `cloudwalk_weixin_keyword` (
  `id` varchar(255) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `msg_type` varchar(255) DEFAULT NULL,
  `template_id` varchar(255) DEFAULT NULL,
  `template_name` varchar(255) DEFAULT NULL,
  `account_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jmbig3vonans24l2kds6xvh4m` (`keyword`),
  KEY `FK8xrs9s4o8qchng19y7hnl6g31` (`account_id`),
  CONSTRAINT `FK8xrs9s4o8qchng19y7hnl6g31` FOREIGN KEY (`account_id`) REFERENCES `cloudwalk_weixin_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `cloudwalk_weixin_menu`
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
  `account_id` varchar(255) NOT NULL,
  `parent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKji7ub9bk0jp3b6kxw6pw9kr8l` (`account_id`),
  KEY `FKiuf68xt8todaebam074nqxsrc` (`parent`),
  CONSTRAINT `FKiuf68xt8todaebam074nqxsrc` FOREIGN KEY (`parent`) REFERENCES `cloudwalk_weixin_menu` (`id`),
  CONSTRAINT `FKji7ub9bk0jp3b6kxw6pw9kr8l` FOREIGN KEY (`account_id`) REFERENCES `cloudwalk_weixin_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `cloudwalk_weixin_receive`
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
  `account_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKep8hofh4hxntssn5amqhb2h1y` (`account_id`),
  CONSTRAINT `FKep8hofh4hxntssn5amqhb2h1y` FOREIGN KEY (`account_id`) REFERENCES `cloudwalk_weixin_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `cloudwalk_weixin_template_expand`
-- ----------------------------
DROP TABLE IF EXISTS `cloudwalk_weixin_template_expand`;
CREATE TABLE `cloudwalk_weixin_template_expand` (
  `id` varchar(255) NOT NULL,
  `classname` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `account_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhvltdrtt8jjhu6f0478ba9p4q` (`account_id`),
  CONSTRAINT `FKhvltdrtt8jjhu6f0478ba9p4q` FOREIGN KEY (`account_id`) REFERENCES `cloudwalk_weixin_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `cloudwalk_weixin_template_news`
-- ----------------------------
DROP TABLE IF EXISTS `cloudwalk_weixin_template_news`;
CREATE TABLE `cloudwalk_weixin_template_news` (
  `id` varchar(255) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `account_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2glr1563l8k0fkkcqtw3ux7a7` (`account_id`),
  CONSTRAINT `FK2glr1563l8k0fkkcqtw3ux7a7` FOREIGN KEY (`account_id`) REFERENCES `cloudwalk_weixin_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `cloudwalk_weixin_template_news_items`
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
  `account_id` varchar(255) NOT NULL,
  `news_template_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3ku00njoaxny7hidn1gogtb5p` (`account_id`),
  KEY `FKflbdth3qkiy0pjwxwqe9e51q` (`news_template_id`),
  CONSTRAINT `FK3ku00njoaxny7hidn1gogtb5p` FOREIGN KEY (`account_id`) REFERENCES `cloudwalk_weixin_account` (`id`),
  CONSTRAINT `FKflbdth3qkiy0pjwxwqe9e51q` FOREIGN KEY (`news_template_id`) REFERENCES `cloudwalk_weixin_template_news` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `cloudwalk_weixin_template_text`
-- ----------------------------
DROP TABLE IF EXISTS `cloudwalk_weixin_template_text`;
CREATE TABLE `cloudwalk_weixin_template_text` (
  `id` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `account_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK22i93w11su0u8is4yyxx6coix` (`account_id`),
  CONSTRAINT `FK22i93w11su0u8is4yyxx6coix` FOREIGN KEY (`account_id`) REFERENCES `cloudwalk_weixin_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
