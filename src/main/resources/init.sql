CREATE TABLE `sys_seq` (
  `seq_name` varchar(64) NOT NULL,
  `current_val` bigint(20) unsigned NOT NULL,
  `increment` int(10) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`seq_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DELIMITER $
CREATE FUNCTION currval (var_seq_name VARCHAR(64))
RETURNS BIGINT
CONTAINS SQL
BEGIN
  DECLARE current BIGINT;
  SET current = 0;
  SELECT current_val INTO current
  FROM sys_seq
  WHERE seq_name = var_seq_name;
  RETURN current;
END$
DELIMITER ;


DELIMITER $
CREATE FUNCTION nextval (var_seq_name VARCHAR(64))
RETURNS BIGINT
CONTAINS SQL
BEGIN
   UPDATE sys_seq
   SET current_val = current_val + increment
   WHERE seq_name = var_seq_name;
   RETURN currval(var_seq_name);
END$
DELIMITER ;