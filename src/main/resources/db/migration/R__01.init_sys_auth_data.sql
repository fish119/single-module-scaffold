-- 系统权限
INSERT INTO `sys_authority` (`id`, `created_by`, `created_date`, `updated_by`, `updated_date`, `description`, `method`, `name`, `sort`, `url`) VALUES (1, 'admin', '2021-01-29 10:38:04.000000', NULL, NULL, '登录', 'post', 'login', 0, '/auth/login');
INSERT INTO `sys_authority` (`id`, `created_by`, `created_date`, `updated_by`, `updated_date`, `description`, `method`, `name`, `sort`, `url`) VALUES (2, 'admin', '2021-01-29 10:38:04.000000', NULL, NULL, '测试', 'get', 'test', 1, '/test/hello');
INSERT INTO `sys_authority` (`id`, `created_by`, `created_date`, `updated_by`, `updated_date`, `description`, `method`, `name`, `sort`, `url`) VALUES (3, 'admin', '2021-01-29 10:38:04.000000', NULL, NULL, 'adminTest', 'get', 'adminTest', 2, '/api/adminTest');
INSERT INTO `sys_authority` (`id`, `created_by`, `created_date`, `updated_by`, `updated_date`, `description`, `method`, `name`, `sort`, `url`) VALUES (4, 'admin', '2021-01-29 10:38:04.000000', NULL, NULL, 'adminTest', 'get', 'test', 2, '/api/test');
INSERT INTO `sys_authority` (`id`, `created_by`, `created_date`, `updated_by`, `updated_date`, `description`, `method`, `name`, `sort`, `url`) VALUES (5, 'admin', '2021-01-29 10:38:04.000000', NULL, NULL, 'refreshToken', 'get', 'refreshToken', 4, '/auth/api/refreshToken');
-- 系统角色
INSERT INTO `sys_role` (`id`, `created_by`, `created_date`, `updated_by`, `updated_date`, `name`, `sort`) VALUES (1, 'admin', '2021-01-30 19:04:31.000000', NULL, NULL, 'guest', 0);
INSERT INTO `sys_role` (`id`, `created_by`, `created_date`, `updated_by`, `updated_date`, `name`, `sort`) VALUES (2, 'admin', '2021-01-30 19:04:46.000000', NULL, NULL, 'admin', 1);
INSERT INTO `sys_role` (`id`, `created_by`, `created_date`, `updated_by`, `updated_date`, `name`, `sort`) VALUES (3, 'admin', '2021-01-30 19:04:59.000000', NULL, NULL, 'user', 2);
-- 系统用户
INSERT INTO `sys_user` (`id`, `last_password_reset` , `created_by`, `created_date`, `updated_by`, `updated_date`, `is_account_non_expired`, `is_account_non_locked`, `is_credentials_non_expired`, `is_enabled`, `password`, `username`) VALUES (1, '2021-01-30 19:00:35.000000', NULL, '2021-01-30 19:00:35.000000', NULL, NULL, 1, 1, 1, 1, '{noop}21232f297a57a5a743894a0e4a801fc3', 'admin');
INSERT INTO `sys_user` (`id`,  `last_password_reset` , `created_by`, `created_date`, `updated_by`, `updated_date`, `is_account_non_expired`, `is_account_non_locked`, `is_credentials_non_expired`, `is_enabled`, `password`, `username`) VALUES (2, '2021-01-30 19:00:35.000000', 'admin', '2021-01-30 19:03:46.000000', NULL, NULL, 1, 1, 1, 1, '{bcrypt}$2a$10$H0AedsdZnYHdCT11KHfpB.4/NDj7dLb.3X9pvXm4.tHU0XSf22ABa', 'test');
-- 关系表
INSERT INTO `sys_role_authorities` (`role_id`, `authority_id`) VALUES (1, 1);
INSERT INTO `sys_role_authorities` (`role_id`, `authority_id`) VALUES (1, 2);
INSERT INTO `sys_role_authorities` (`role_id`, `authority_id`) VALUES (2, 3);
INSERT INTO `sys_role_authorities` (`role_id`, `authority_id`) VALUES (2, 4);
INSERT INTO `sys_role_authorities` (`role_id`, `authority_id`) VALUES (3, 4);
INSERT INTO `sys_role_authorities` (`role_id`, `authority_id`) VALUES (2, 5);
INSERT INTO `sys_role_authorities` (`role_id`, `authority_id`) VALUES (3, 5);
INSERT INTO `sys_user_roles` (`user_id`, `role_id`) VALUES (1, 2);
INSERT INTO `sys_user_roles` (`user_id`, `role_id`) VALUES (2, 3);
