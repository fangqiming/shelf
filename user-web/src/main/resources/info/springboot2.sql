/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50717
Source Host           : 127.0.0.1:3306
Source Database       : springboot2

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-04-25 12:19:32
*/

SET FOREIGN_KEY_CHECKS=0;


-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `topic_id` bigint(20) DEFAULT NULL,
  `user_code` varchar(50) DEFAULT NULL,
  `content` tinytext,
  `reply_id` bigint(20) DEFAULT NULL,
  `good_num` int(11) DEFAULT '0',
  `bad_num` int(11) DEFAULT '0',
  `is_deleted` tinyint(4) DEFAULT '0',
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `deletedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reply
-- ----------------------------
INSERT INTO `reply` VALUES ('1', '2', '123', 'h1', null, '6', '0', '0', null, null, null);
INSERT INTO `reply` VALUES ('2', null, '123', 'h2', '1', '0', '0', '0', null, null, null);
INSERT INTO `reply` VALUES ('3', null, '123', 'h3', '1', '0', '0', '0', null, null, null);
INSERT INTO `reply` VALUES ('4', null, '123', 'h4', '2', '0', '0', '0', null, null, null);
INSERT INTO `reply` VALUES ('5', null, '123', 'h5', '4', '0', '0', '0', null, null, null);
INSERT INTO `reply` VALUES ('6', '2', '123', 'h6', null, '0', '0', '0', null, null, null);
INSERT INTO `reply` VALUES ('7', null, '123', 'h7', '6', '0', '0', '0', null, null, null);
INSERT INTO `reply` VALUES ('8', '1', '123', 'Hello World', null, '0', '0', '0', '2018-04-25 11:29:43', null, null);
INSERT INTO `reply` VALUES ('9', null, '123', '我是评论内容', '1', '0', '0', '0', '2018-04-25 11:39:49', null, null);
INSERT INTO `reply` VALUES ('10', null, '123', '我是评论内容', '1', '0', '0', '0', '2018-04-25 11:42:14', null, null);

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` text,
  `title` varchar(255) DEFAULT NULL,
  `user_code` varchar(50) DEFAULT NULL,
  `click_num` int(11) DEFAULT '0',
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `deleted_time` datetime DEFAULT NULL,
  `is_deleted` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES ('1', '世界和平，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，，', '我是标题', '123456', null, null, null, null, '0');
INSERT INTO `topic` VALUES ('3', '文章内容', '测试文章', '测试码', '0', '2018-04-25 11:18:35', null, null, '0');
INSERT INTO `topic` VALUES ('4', '文章内容', '测试文章', '测试码', '0', '2018-04-25 11:18:38', null, null, '0');
