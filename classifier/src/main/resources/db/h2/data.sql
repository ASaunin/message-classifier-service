INSERT INTO cls.SubCategory (SubCategoryId, SubCategory, Category, Deleted, UpdatedAt)
VALUES
  (0, 'UNDEF', 'DEF', 0, '2017-01-01 00:00:01.0'),
  (1, 'GAMBLING', 'BLK', 0, '2017-01-01 00:00:01.0'),
  (2, 'DEFAULT', 'DEF', 0, '2017-01-01 00:00:01.0'),
  (3, 'MKT', 'NTS', 0, '2017-01-01 00:00:01.0'),
  (4, 'OTP', 'TS', 0, '2017-01-01 00:00:01.0'),
  (5, 'SPAM', 'BLK', 0, '2017-01-01 00:00:01.0'),
  (6, 'NOTIF', 'NTS', 0, '2017-01-01 00:00:01.0');

INSERT INTO cls.Rule (RuleId, SubAccountUid, Country, SubCategoryId, Deleted, UpdatedAt)
VALUES
  (2, 1, 'SG', 4, 0, '2017-01-01 00:00:01.0'),
  (3, 2, NULL, 4, 0, '2017-01-01 00:00:01.0');


INSERT INTO cls.CustomPattern (PatternId, RuleId, SenderId, BodyPattern, Deleted, UpdatedAt)
VALUES
  (1, 2, 'WHATSAPP', '.*', 0, '2017-01-01 00:00:01.0'),
  (2, 2, NULL, '(WhatsApp code)(.*)([0-9]{3}-[0-9]{3})', 0, '2017-01-01 00:00:01.0'),
  (3, 2, 'Viber', '.*', 0, '2017-01-01 00:00:01.0');

INSERT INTO cls.DefaultPattern (PatternId, SubCategoryId, Country, SenderId, BodyPattern, Deleted, UpdatedAt)
VALUES
  (1, 4, NULL, NULL, '(.*)(your code)(.*)([0-9]{4,8})(.*)', 0, '2017-01-01 00:00:01.0'),
  (2, 4, NULL, NULL, '(.*)(your pin)(.*)([0-9]{4,8})(.*)', 0, '2017-01-01 00:00:01.0'),
  (3, 4, NULL, NULL, '(.*)(pin code)(.*)([0-9]{4,8})(.*)', 0, '2017-01-01 00:00:01.0'),
  (4, 4, NULL, NULL, '(.*)(verification pin)(.*)([0-9]{4,8})(.*)', 0, '2017-01-01 00:00:01.0'),
  (5, 4, NULL, NULL, '(.*)(code is)(.*)([0-9]{4,8})(.*)', 0, '2017-11-16 07:50:01'),
  (6, 4, NULL, NULL, '(.*)(code of)(.*)([0-9]{4,8})(.*)', 0, '2017-01-01 00:00:01.0'),
  (7, 4, NULL, NULL, '(.*)(authentication code)(.*)([0-9]{4,8})(.*)', 0, '2017-01-01 00:00:01.0'),
  (8, 4, NULL, NULL, '(.*)(activation key)(.*)([0-9]{4,8})(.*)', 0, '2017-01-01 00:00:01.0'),
  (9, 4, NULL, NULL, '(.*)(activation code)(.*)([0-9]{4,8})(.*)', 0, '2017-01-01 00:00:01.0'),
  (10, 4, NULL, NULL, '(.*)(verification key)(.*)([0-9]{4,8})(.*)', 0, '2017-01-01 00:00:01.0'),
  (11, 4, NULL, NULL, '(.*)(number verification)(.*)([0-9]{4,8})(.*)', 0, '2017-01-01 00:00:01.0'),
  (12, 4, NULL, NULL, '(.*)(authentication code)(.*)([0-9]{4,8})(.*)', 0, '2017-01-01 00:00:01.0'),
  (13, 4, NULL, NULL, '(.*)(verification code)(.*)([0-9]{4,8})(.*)', 0, '2017-01-01 00:00:01.0'),
  (14, 4, NULL, NULL, '(.*)(activation key)(.*)([0-9]{4,8})(.*)', 0, '2017-01-01 00:00:01.0'),
  (15, 4, NULL, NULL, '(.*)(confirmation code)(.*)([0-9]{4,8})(.*)', 0, '2017-01-01 00:00:01.0'),
  (16, 4, NULL, NULL, '(.*)(activation code)(.*)([0-9]{4,8})(.*)', 0, '2017-01-01 00:00:01.0'),
  (17, 4, NULL, NULL, '(.*)(vtre code)(.*)([0-9]{4,8})(.*)', 1, '2017-02-01 00:00:01.0'),
  (18, 4, NULL, NULL, '(.*)(votre code)(.*)([0-9]{4,8})(.*)', 0, '2017-02-01 00:00:01.0');
-- ...