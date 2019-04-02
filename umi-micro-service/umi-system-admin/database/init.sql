/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯云-mariadb
 Source Server Type    : MySQL
 Source Server Version : 50560
 Source Host           : 192.144.128.53:3306
 Source Schema         : umi-soft-admin

 Target Server Type    : MySQL
 Target Server Version : 50560
 File Encoding         : 65001

 Date: 27/03/2019 13:39:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for R_DEPT_ROLE
-- ----------------------------
DROP TABLE IF EXISTS `R_DEPT_ROLE`;
CREATE TABLE `R_DEPT_ROLE` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `DEPT_ID` varchar(255) NOT NULL COMMENT '部门ID',
  `ROLE_ID` varchar(255) NOT NULL COMMENT '角色ID',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门-角色，中间表';

-- ----------------------------
-- Table structure for R_MENU_ROLE
-- ----------------------------
DROP TABLE IF EXISTS `R_MENU_ROLE`;
CREATE TABLE `R_MENU_ROLE` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `MENU_ID` varchar(255) NOT NULL COMMENT '菜单ID',
  `ROLE_ID` varchar(255) NOT NULL COMMENT '角色ID',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单-角色中间表';

-- ----------------------------
-- Table structure for R_MENU_SECURITY
-- ----------------------------
DROP TABLE IF EXISTS `R_MENU_SECURITY`;
CREATE TABLE `R_MENU_SECURITY` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `MENU_ID` varchar(255) NOT NULL COMMENT '菜单ID',
  `SECURITY_ID` varchar(255) NOT NULL COMMENT '安全资源ID',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单-安全资源中间表';

-- ----------------------------
-- Table structure for R_ROLE_GROUP
-- ----------------------------
DROP TABLE IF EXISTS `R_ROLE_GROUP`;
CREATE TABLE `R_ROLE_GROUP` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `ROLE_ID` varchar(255) NOT NULL COMMENT '角色ID',
  `GROUP_ID` varchar(255) NOT NULL COMMENT '分组ID',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-分组中间表';

-- ----------------------------
-- Table structure for R_USER_DEPT
-- ----------------------------
DROP TABLE IF EXISTS `R_USER_DEPT`;
CREATE TABLE `R_USER_DEPT` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `USER_ID` varchar(255) NOT NULL COMMENT '用户ID',
  `DEPT_ID` varchar(255) NOT NULL COMMENT '部门ID',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-部门中间表';

-- ----------------------------
-- Table structure for R_USER_GROUP
-- ----------------------------
DROP TABLE IF EXISTS `R_USER_GROUP`;
CREATE TABLE `R_USER_GROUP` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `USER_ID` varchar(255) NOT NULL COMMENT '用户ID',
  `GROUP_ID` varchar(255) NOT NULL COMMENT '分组ID',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-分组中间表';

-- ----------------------------
-- Table structure for R_USER_GROUP_ROLE_GROUP
-- ----------------------------
DROP TABLE IF EXISTS `R_USER_GROUP_ROLE_GROUP`;
CREATE TABLE `R_USER_GROUP_ROLE_GROUP` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `USER_GROUP_ID` varchar(255) NOT NULL COMMENT '用户分组ID',
  `ROLE_GROUP_ID` varchar(255) NOT NULL COMMENT '角色角色ID',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户分组-角色分组，中间表';

-- ----------------------------
-- Table structure for R_USER_ROLE
-- ----------------------------
DROP TABLE IF EXISTS `R_USER_ROLE`;
CREATE TABLE `R_USER_ROLE` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `USER_ID` varchar(255) NOT NULL COMMENT '用户ID',
  `ROLE_ID` varchar(255) NOT NULL COMMENT '角色ID',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色中间表';

-- ----------------------------
-- Table structure for R_USER_ROLE_GROUP
-- ----------------------------
DROP TABLE IF EXISTS `R_USER_ROLE_GROUP`;
CREATE TABLE `R_USER_ROLE_GROUP` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `USER_ID` varchar(255) NOT NULL COMMENT '用户ID',
  `ROLE_GROUP_ID` varchar(255) NOT NULL COMMENT '角色角色ID',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色分组，中间表';

-- ----------------------------
-- Table structure for T_DEPT
-- ----------------------------
DROP TABLE IF EXISTS `T_DEPT`;
CREATE TABLE `T_DEPT` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `PARENT_ID` varchar(255) DEFAULT NULL COMMENT '上级部门',
  `SORT_NUM` varchar(255) DEFAULT NULL COMMENT '机构序号，可调整该值，用于排序',
  `TYPE` varchar(255) DEFAULT NULL COMMENT '机构类型，取自数据字典',
  `NAME` varchar(255) DEFAULT NULL COMMENT '机构名称',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '机构描述',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门信息表';

-- ----------------------------
-- Table structure for T_DICTIONARY
-- ----------------------------
DROP TABLE IF EXISTS `T_DICTIONARY`;
CREATE TABLE `T_DICTIONARY` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `PARENT_ID` varchar(255) DEFAULT NULL COMMENT '上级ID',
  `CATEGORY` varchar(255) DEFAULT NULL COMMENT '类型，枚举值： 1：字典分类, 2：单级业务字典, 3：多级业务字典',
  `TYPE` varchar(255) DEFAULT NULL COMMENT '字典分类ID',
  `SORT_NUM` varchar(255) DEFAULT NULL COMMENT '序号，可调整该值，用于排序',
  `NAME` varchar(255) DEFAULT NULL COMMENT '名称',
  `CODE` varchar(255) DEFAULT NULL COMMENT '规则码',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '描述',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务字典（含业务字典分类）表';

-- ----------------------------
-- Table structure for T_GROUP
-- ----------------------------
DROP TABLE IF EXISTS `T_GROUP`;
CREATE TABLE `T_GROUP` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `CATEGORY` varchar(255) NOT NULL COMMENT '分组类型，枚举值',
  `SORT_NUM` varchar(255) DEFAULT NULL COMMENT '序号，可调整该值，用于排序',
  `NAME` varchar(255) DEFAULT NULL COMMENT '名称',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '描述',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分组信息表';

-- ----------------------------
-- Table structure for T_MENU
-- ----------------------------
DROP TABLE IF EXISTS `T_MENU`;
CREATE TABLE `T_MENU` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `PARENT_ID` varchar(255) DEFAULT NULL COMMENT '上级ID',
  `SORT_NUM` varchar(255) DEFAULT NULL COMMENT '序号，可调整该值，用于排序',
  `NAME` varchar(255) DEFAULT NULL COMMENT '名称',
  `ICON` varchar(255) DEFAULT NULL COMMENT '图标名称',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '描述',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='前端路由菜单信息表';

-- ----------------------------
-- Table structure for T_ROLE
-- ----------------------------
DROP TABLE IF EXISTS `T_ROLE`;
CREATE TABLE `T_ROLE` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `SORT_NUM` varchar(255) DEFAULT NULL COMMENT '序号，可调整该值，用于排序',
  `NAME` varchar(255) DEFAULT NULL COMMENT '名称',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '描述',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Table structure for T_SECURITY
-- ----------------------------
DROP TABLE IF EXISTS `T_SECURITY`;
CREATE TABLE `T_SECURITY` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `SERVICE_ID` varchar(255) NOT NULL COMMENT '服务ID标识',
  `SECURITY_DEF` varchar(255) NOT NULL COMMENT '安全资源标识',
  `NAME` varchar(255) DEFAULT NULL COMMENT '名称',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '描述',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE KEY `UNIQUE_SECURITY_DEF` (`SECURITY_DEF`) USING BTREE COMMENT '安全资源定义不允许重复'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='安全资源定义信息表';

-- ----------------------------
-- Table structure for T_USER
-- ----------------------------
DROP TABLE IF EXISTS `T_USER`;
CREATE TABLE `T_USER` (
  `ID` varchar(255) NOT NULL COMMENT '主键',
  `DELETED` int(1) NOT NULL COMMENT '是否删除',
  `DISABLED` int(1) NOT NULL COMMENT '是否停用',
  `SORT_NUM` varchar(255) DEFAULT NULL COMMENT '序号，可调整该值，用于排序',
  `LOGIN_NAME` varchar(255) NOT NULL COMMENT '登陆用户名',
  `PASSWORD` varchar(255) NOT NULL COMMENT '登陆密码',
  `NAME` varchar(255) DEFAULT NULL COMMENT '名称',
  `NICK_NAME` varchar(255) DEFAULT NULL COMMENT '昵称',
  `AVATAR` varchar(255) DEFAULT NULL COMMENT '肖像地址',
  `ID_NUMBER` varchar(64) DEFAULT NULL COMMENT '证件号码',
  `GENDER` varchar(255) DEFAULT NULL COMMENT '性别，枚举值，字典值',
  `BIRTHDAY` varchar(255) DEFAULT NULL COMMENT '出生日期YYYY-MM-DD',
  `PHONE` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `EMAIL` varchar(255) DEFAULT NULL COMMENT '联系邮箱',
  `ADDRESS` varchar(255) DEFAULT NULL COMMENT '联系地址',
  `TAG` varchar(255) DEFAULT NULL COMMENT '用户标签',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '描述',
  `CREATED_BY` varchar(255) NOT NULL COMMENT '创建人',
  `CREATED_DATE` datetime NOT NULL COMMENT '创建时间',
  `MODIFIED_BY` varchar(255) DEFAULT NULL COMMENT '最后修改人，初始时与创建人相同',
  `MODIFIED_DATE` datetime DEFAULT NULL COMMENT '最后修改事件，初始时与创建时间相同',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

INSERT INTO `T_USER`(`ID`, `DELETED`, `DISABLED`, `SORT_NUM`, `LOGIN_NAME`, `PASSWORD`, `NAME`, `NICK_NAME`, `AVATAR`, `ID_NUMBER`, `GENDER`, `BIRTHDAY`, `PHONE`, `EMAIL`, `ADDRESS`, `TAG`, `REMARK`, `CREATED_BY`, `CREATED_DATE`, `MODIFIED_BY`, `MODIFIED_DATE`) VALUES ('466d9e02-2f44-4a90-88f6-91f61eaec5d5', 0, 0, '1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'admin', 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '2019-02-23 11:14:51', '466d9e02-2f44-4a90-88f6-91f61eaec5d5', '2019-03-10 03:40:59');
INSERT INTO `T_ROLE`(`ID`, `DELETED`, `SORT_NUM`, `NAME`, `REMARK`, `CREATED_BY`, `CREATED_DATE`, `MODIFIED_BY`, `MODIFIED_DATE`) VALUES ('admin', 0, 'ADMIN', 'ADMIN', '系统内置的超级角色', '466d9e02-2f44-4a90-88f6-91f61eaec5d5', '2019-03-09 23:43:50', '466d9e02-2f44-4a90-88f6-91f61eaec5d5', '2019-03-11 05:27:30');
INSERT INTO `R_USER_ROLE`(`ID`, `DELETED`, `USER_ID`, `ROLE_ID`, `CREATED_BY`, `CREATED_DATE`, `MODIFIED_BY`, `MODIFIED_DATE`) VALUES ('562b8fea5911b4ad80fe1c4402f5efa5', 0, '466d9e02-2f44-4a90-88f6-91f61eaec5d5', 'admin', '466d9e02-2f44-4a90-88f6-91f61eaec5d5', '2019-03-14 20:04:39', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
