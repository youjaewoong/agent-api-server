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
  `id` varchar(32) NOT null COMMENT '고유ID',
  `user_id` varchar(20) NOT null COMMENT '상담사ID',
  `title` varchar(20) NOT null COMMENT '제목',
  `contents` varchar(200) not null COMMENT '내용',
  `group_id`varchar(32) COMMENT 'MEMO_GROUP 고유ID',
  `group_name`varchar(20) COMMENT 'MEMO_GROUP 이름',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `create_user_id`varchar(20) COMMENT '생성자ID',
  `update_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user_id`varchar(20) COMMENT '수정자ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='메모';


CREATE TABLE `memo_group` (
  `id` varchar(32) NOT null COMMENT '고유ID',
  `user_id` varchar(20) NOT null COMMENT '상담사ID',
  `name` varchar(200) not null COMMENT '이름',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `create_user_id`varchar(20) COMMENT '생성자ID',
  `update_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user_id`varchar(20) COMMENT '수정자ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='메모그룹';

```
