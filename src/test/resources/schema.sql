-- `mvn test`命令会自动寻找schema.sql和data.sql

/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : xschool

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2015-10-31 15:08:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_article`
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `title` varchar(200) NOT NULL COMMENT '标题',
  `author` varchar(50) DEFAULT NULL COMMENT '作者',
  `profile` varchar(100) DEFAULT NULL COMMENT '简介',
  `status` int(11) NOT NULL COMMENT '状态',
  `type` int(11) NOT NULL COMMENT '类型',
  `priority` int(11) unsigned NOT NULL COMMENT '优先级',
  `is_banner` char(1) NOT NULL COMMENT '是否Banner',
  `detail` varchar(8000) DEFAULT NULL COMMENT '详情',
  `organization_id` int(11) unsigned DEFAULT NULL COMMENT '所属机构',
  `URL` varchar(500) DEFAULT NULL COMMENT 'URL',
  `create_time` varchar(20) NOT NULL COMMENT '创建时间',
  `resource_id` int(11) unsigned NOT NULL COMMENT '资源',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_category`
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `priority` int(11) NOT NULL COMMENT '优先级',
  `is_home_show` char(1) NOT NULL COMMENT '是否首页展示',
  `explanation` varchar(50) DEFAULT NULL COMMENT '说明',
  `create_time` varchar(20) NOT NULL COMMENT '创建时间',
  `parent_category_id` int(11) DEFAULT NULL COMMENT '上级分类',
  `parent_category_name` varchar(20) NOT NULL DEFAULT '' COMMENT '已废弃',
  `resource_id` int(11) NOT NULL COMMENT '资源',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_comment`
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(11) NOT NULL COMMENT '用户',
  `organization_id` int(11) NOT NULL COMMENT '机构',
  `content` varchar(200) NOT NULL COMMENT '内容',
  `status` int(11) NOT NULL COMMENT '状态',
  `create_time` varchar(20) NOT NULL COMMENT '创建时间',
  `verify_time` varchar(20) DEFAULT NULL COMMENT '审核时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_course`
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `priority` int(11) unsigned NOT NULL COMMENT '优先级',
  `fit_age` varchar(10) NOT NULL COMMENT '适合年龄',
  `remark` varchar(500) DEFAULT NULL COMMENT '描述',
  `category_id` int(11) unsigned NOT NULL COMMENT '所属分类',
  `organization_id` int(11) unsigned NOT NULL COMMENT '所属机构',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_evaluation`
-- ----------------------------
DROP TABLE IF EXISTS `t_evaluation`;
CREATE TABLE `t_evaluation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `status` int(11) NOT NULL COMMENT '状态',
  `detail` varchar(2000) NOT NULL COMMENT '详情',
  `topic_num` int(11) NOT NULL COMMENT '题目数量',
  `click_num` int(11) NOT NULL COMMENT '点击量',
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_1_t_evaluation` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_evaluation_topic_store`
-- ----------------------------
DROP TABLE IF EXISTS `t_evaluation_topic_store`;
CREATE TABLE `t_evaluation_topic_store` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `evaluation_id` int(11) NOT NULL COMMENT '评测',
  `topic_store_id` int(11) NOT NULL COMMENT '题库',
  `topic_num` int(11) NOT NULL COMMENT '题数',
  `priority` int(11) NOT NULL COMMENT '优先级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_feedback`
-- ----------------------------
DROP TABLE IF EXISTS `t_feedback`;
CREATE TABLE `t_feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(11) NOT NULL COMMENT '用户',
  `status` int(11) NOT NULL COMMENT '状态',
  `info` varchar(200) NOT NULL COMMENT '反馈信息',
  `contact` varchar(50) NOT NULL COMMENT '联系方式',
  `create_time` varchar(20) NOT NULL COMMENT '反馈时间',
  `deal_time` varchar(20) DEFAULT NULL COMMENT '处理时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_location`
-- ----------------------------
DROP TABLE IF EXISTS `t_location`;
CREATE TABLE `t_location` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `status` int(11) NOT NULL COMMENT '状态',
  `priority` int(11) NOT NULL COMMENT '优先级',
  `province_id` int(11) NOT NULL COMMENT '所属省市区域ID[冗余字段]',
  `city_id` int(11) NOT NULL COMMENT '所属城市[冗余字段]',
  `region_id` int(11) NOT NULL COMMENT '所属区域',
  `city` varchar(20) NOT NULL COMMENT '所属城市名称[冗余字段]',
  `county` varchar(20) NOT NULL DEFAULT '' COMMENT '所属县区名称[冗余字段]',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_organization`
-- ----------------------------
DROP TABLE IF EXISTS `t_organization`;
CREATE TABLE `t_organization` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `status` int(11) NOT NULL COMMENT '状态',
  `heat` int(11) NOT NULL COMMENT '热度',
  `phone` varchar(20) NOT NULL COMMENT '电话',
  `longitude` varchar(30) NOT NULL COMMENT '经度',
  `latitude` varchar(30) NOT NULL COMMENT '纬度',
  `location_id` int(11) NOT NULL COMMENT '位置',
  `address_detail` varchar(200) NOT NULL COMMENT '详细地址',
  `share_num` int(11) NOT NULL COMMENT '分享数',
  `share_url` varchar(100) DEFAULT '' COMMENT '分享URL',
  `profile` varchar(50) DEFAULT NULL COMMENT '简介',
  `detail` varchar(5000) DEFAULT NULL COMMENT '详情',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_1_t_organization` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_organization_resource`
-- ----------------------------
DROP TABLE IF EXISTS `t_organization_resource`;
CREATE TABLE `t_organization_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `organization_id` int(11) NOT NULL COMMENT '机构',
  `resource_id` int(11) NOT NULL COMMENT '资源',
  `is_main` char(1) NOT NULL COMMENT '是否主图',
  `priority` int(11) NOT NULL COMMENT '优先级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `t_region` (
  `id` bigint(11) unsigned NOT NULL,
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '行政区域名字',
  `parent_id` bigint(11) unsigned DEFAULT NULL COMMENT '父级区域ID',
  `type` tinyint(1) NOT NULL COMMENT '类型',
  `zip` varchar(20) NOT NULL DEFAULT '',
  `is_opened` tinyint(1) NOT NULL COMMENT '是否开通：仅在市级数据进行标记即可',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_resource`
-- ----------------------------
DROP TABLE IF EXISTS `t_resource`;
CREATE TABLE `t_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `path` varchar(200) NOT NULL COMMENT '路径',
  `type` int(11) NOT NULL COMMENT '类型',
  `remark` varchar(20) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_task`
-- ----------------------------
DROP TABLE IF EXISTS `t_task`;
CREATE TABLE `t_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `status` int(11) NOT NULL COMMENT '状态',
  `hard_type` int(11) NOT NULL COMMENT '难度',
  `detail` varchar(2000) NOT NULL COMMENT '详情',
  `no_play_times` int(11) NOT NULL COMMENT '不想玩次数',
  `create_time` varchar(20) NOT NULL COMMENT '创建时间',
  `video_pic_resource_id` int(11) DEFAULT NULL COMMENT '视频封面资源',
  `video_resource_id` int(11) NOT NULL COMMENT '视频资源',
  `topic_store_id` int(11) NOT NULL COMMENT '所属题库',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_topic`
-- ----------------------------
CREATE TABLE `t_topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(500) NOT NULL COMMENT '名称',
  `type` int(11) NOT NULL COMMENT '类型',
  `options` varchar(1000) NOT NULL COMMENT '选项',
  `topic_store_id` int(11) NOT NULL COMMENT '题库',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_topic_store`
-- ----------------------------
DROP TABLE IF EXISTS `t_topic_store`;
CREATE TABLE `t_topic_store` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `topic_num` int(11) NOT NULL COMMENT '题目数量',
  `task_num` int(11) NOT NULL COMMENT '任务数量',
  `good_description` varchar(500) NOT NULL COMMENT '优势说明',
  `bad_description` varchar(500) NOT NULL COMMENT '劣势说明',
  `train_plan_name` varchar(200) NOT NULL COMMENT '培养方案名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_1_t_topic_store` (`name`),
  UNIQUE KEY `u_2_t_topic_store` (`train_plan_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `mobile` varchar(11) NOT NULL COMMENT '手机号',
  `pwd` varchar(60) NOT NULL COMMENT '密码',
  `status` int(11) NOT NULL COMMENT '状态',
  `type` int(11) NOT NULL COMMENT '类型',
  `nickname` varchar(20) DEFAULT NULL COMMENT '昵称',
  `sex` char(1) DEFAULT NULL COMMENT '性别',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `create_time` varchar(20) NOT NULL COMMENT '创建时间',
  `resource_id` int(11) DEFAULT NULL COMMENT '资源编号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_1_t_user` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_user_organization`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_organization`;
CREATE TABLE `t_user_organization` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `organization_id` int(11) NOT NULL COMMENT '机构编号',
  `create_time` varchar(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_1_t_user_organization` (`user_id`,`organization_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_user_train_plan_record`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_train_plan_record`;
CREATE TABLE `t_user_train_plan_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` int(11) NOT NULL COMMENT '用户',
  `good_topic_store_ids` varchar(200) DEFAULT NULL COMMENT '优势题库',
  `bad_topic_store_ids` varchar(200) DEFAULT NULL COMMENT '劣势题库',
  `good_topic_store_names` varchar(500) DEFAULT NULL COMMENT '优势题库名称',
  `bad_topic_store_names` varchar(500) DEFAULT NULL COMMENT '劣势题库名称',
  `evaluation_id` int(11) NOT NULL COMMENT '评测',
  `good_description` varchar(500) NOT NULL COMMENT '优势说明',
  `bad_description` varchar(500) NOT NULL COMMENT '劣势说明',
  `evaluation_name` varchar(50) NOT NULL COMMENT '评测名称',
  `complete_degree` varchar(20) NOT NULL COMMENT '完成度',
  `child_birthday` int(11) NOT NULL COMMENT '孩子生日',
  `child_top` int(11) NOT NULL COMMENT '孩子排行',
  `status` int(11) NOT NULL COMMENT '状态',
  `create_time` varchar(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `t_user_train_plan_task`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_train_plan_task`;
CREATE TABLE `t_user_train_plan_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_train_plan_record_id` int(11) NOT NULL COMMENT '用户培养方案记录',
  `task_id` int(11) NOT NULL COMMENT '任务',
  `is_good` char(1) NOT NULL COMMENT '是否优势',
  `create_time` varchar(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_1_t_user_train_plan_task` (`user_train_plan_record_id`,`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
