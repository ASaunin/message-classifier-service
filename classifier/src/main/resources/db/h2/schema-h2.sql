CREATE SCHEMA IF NOT EXISTS cls;

-- SET SCHEMA cls;

DROP TABLE IF EXISTS cls.SubCategory;

CREATE TABLE cls.SubCategory (
  SubCategoryId SMALLINT                              NOT NULL IDENTITY (1, 1),
  SubCategory   VARCHAR(20) UNIQUE                    NOT NULL,
  Category      VARCHAR(3)                            NOT NULL,
  Deleted       BIT                                   NOT NULL DEFAULT ((0)),
  UpdatedAt     DATETIME                              NOT NULL DEFAULT (CURRENT_TIMESTAMP()),
  CONSTRAINT PK_clsClass PRIMARY KEY (SubCategoryId)
);

DROP TABLE IF EXISTS cls.Rule;

CREATE TABLE IF NOT EXISTS cls.Rule (
  RuleId        INT      NOT NULL IDENTITY (1, 1),
  SubAccountUid INT      NOT NULL,
  Country       CHAR(2),
  SubCategoryId SMALLINT NOT NULL,
  Deleted       BIT      NOT NULL DEFAULT ((0)),
  UpdatedAt     DATETIME NOT NULL DEFAULT (CURRENT_TIMESTAMP()),
  CONSTRAINT PK_clsRule PRIMARY KEY (RuleId),
  CONSTRAINT FK_Rule_Category FOREIGN KEY (SubCategoryId) REFERENCES cls.SubCategory (SubCategoryId) ON DELETE RESTRICT ON UPDATE RESTRICT
);

DROP TABLE IF EXISTS cls.CustomPattern;

CREATE TABLE IF NOT EXISTS cls.CustomPattern (
  PatternId   INT      NOT NULL IDENTITY (1, 1),
  RuleId      INT      NOT NULL,
  SenderId    VARCHAR(500),
  BodyPattern NVARCHAR(500),
  Deleted     BIT      NOT NULL DEFAULT ((0)),
  UpdatedAt   DATETIME NOT NULL DEFAULT (CURRENT_TIMESTAMP()),
  CONSTRAINT PK_clsCustomPattern PRIMARY KEY (PatternId),
  CONSTRAINT FK_CustomPattern_Rule FOREIGN KEY (RuleId) REFERENCES cls.Rule (RuleId) ON DELETE RESTRICT ON UPDATE RESTRICT
);

DROP TABLE IF EXISTS cls.DefaultPattern;

CREATE TABLE IF NOT EXISTS cls.DefaultPattern (
  PatternId     INT      NOT NULL IDENTITY (1, 1),
  SubCategoryId SMALLINT NOT NULL,
  Country       CHAR(2),
  SenderId      VARCHAR(500),
  BodyPattern   NVARCHAR(500),
  Deleted       BIT      NOT NULL DEFAULT ((0)),
  UpdatedAt     DATETIME NOT NULL DEFAULT (CURRENT_TIMESTAMP()),
  CONSTRAINT PK_clsDefaultPattern PRIMARY KEY (PatternId),
  CONSTRAINT FK_DefaultPattern_Category FOREIGN KEY (SubCategoryId) REFERENCES cls.SubCategory (SubCategoryId) ON DELETE RESTRICT ON UPDATE RESTRICT
);