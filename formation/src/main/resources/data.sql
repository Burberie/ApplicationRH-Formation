INSERT INTO `users` (`email`, `username`, `password`, `lastname`, `firstname`, `register_num`, `register_date`, `seniority_date`, `end_date`, `service`, `contract`, `is_active`) VALUES
('admin@email.com', 'admin', 'password', 'ad', 'min', '000000', NOW(), '2010-01-01', '2099-12-31', 'HR_SERVICE', 'PM_CONTRACT', TRUE);

INSERT INTO `user_role` (`user_id`, `role_name`) VALUES (1, 'ADMIN');