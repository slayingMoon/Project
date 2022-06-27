SET FOREIGN_KEY_CHECKS=0;  -- turn off foreign key checks
TRUNCATE TABLE PUBLIC.client;  -- truncate tables
TRUNCATE TABLE PUBLIC.card;
TRUNCATE TABLE PUBLIC.point_transaction;
SET FOREIGN_KEY_CHECKS=1;


-- This is supposed to generate the code upwards; if everything goes as planned, no one will see this
-- SET FOREIGN_KEY_CHECKS = 0;
-- SELECT @str :=
--     CONCAT('TRUNCATE TABLE ',
--             TABLE_SCHEMA,
--             '.',
--             TABLE_NAME,
--             ';') AS result
-- FROM
--     INFORMATION_SCHEMA.TABLES
-- WHERE
--     TABLE_SCHEMA IN ('PUBLIC');
-- prepare stmt from @str;
-- execute stmt;
-- deallocate prepare stmt;
-- SET FOREIGN_KEY_CHECKS = 1;