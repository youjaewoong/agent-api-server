### swagger
http://localhost:8088/swagger-ui/index.html

### argument setup
```
-Dapp.home=D:\log
-Dspring.profiles.active=local
```

### DDL
```
CREATE TABLE `memo` (
  `id` varchar(32) NOT NULL COMMENT '고유ID',
  `agent_id` varchar(20) NOT NULL COMMENT '상담사ID',
  `title` varchar(20) NOT NULL COMMENT '제목',
  `contents` varchar(200) NOT NULL COMMENT '내용',
  `group_id`varchar(32) COMMENT 'MEMO_GROUP 고유ID',
  `created_ilsi` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_ilsi` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='메모';


CREATE TABLE `memo_group` (
  `id` varchar(32) NOT NULL COMMENT '고유ID',
  `agent_id` varchar(20) NOT NULL COMMENT '상담사ID',
  `title` varchar(200) NOT NULL COMMENT '그룹명',
  `basic_group_yn` CHAR(1) NOT NULL DEFAULT 'N' COMMENT '기본그룹여부',
  `created_ilsi` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `updated_ilsi` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='메모그룹';
```

```
CREATE TABLE `bookmark` (
  `ID` varchar(32) NOT NULL COMMENT '고유ID',
  `AGENT_ID` varchar(20) NOT NULL COMMENT '상담사ID',
  `TITLE` varchar(20) NOT NULL COMMENT '제목',
  `SUB_TITLE` varchar(20) COMMENT '부제목',
  `CONTENT` text NOT NULL COMMENT '내용',
  `CATEGORY` char(1) NULL NULL COMMENT 'K:지식저장소 S:스크립트',
  `GROUP_ID` varchar(32) DEFAULT NULL COMMENT 'BOOK_MARK_GROUP 고유ID',
  `REG_DT` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `MOD_DT` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='책갈피';



CREATE TABLE `bookmark_group` (
  `ID` varchar(32) NOT NULL COMMENT '고유ID',
  `AGENT_ID` varchar(20) NOT NULL COMMENT '상담사ID',
  `TITLE` varchar(200) NOT NULL COMMENT '제목',
  `BASIC_GROUP_YN` CHAR(1) NOT NULL DEFAULT 'N' COMMENT '기본그룹여부',
  `REG_DT` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
  `MOD_DT` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='책갈피 그룹';

```

```
특정 컬럼 앞에 컬럼 추가
ALTER TABLE `BOOK_MARK` ADD TYPE CHAR(1) AFTER CONTENTS;

특정 컬럼 변경
ALTER TABLE `BOOK_MARK` CHANGE USER_ID ADV_ID VARCHAR(20);
ALTER TABLE `BOOK_MARK_GROUP` CHANGE USER_ID ADV_ID VARCHAR(20);

특정 컬럼 삭제
ALTER TABLE `BOOK_MARK` DROP `MOD_ID`;
ALTER TABLE `BOOK_MARK` DROP `REG_ID`;
ALTER TABLE `BOOK_MARK_GROUP` DROP `MOD_ID`;
ALTER TABLE `BOOK_MARK_GROUP` DROP `REG_ID`;
```
