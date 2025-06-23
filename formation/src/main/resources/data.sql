INSERT INTO `users` (`email`, `username`, `password`, `lastname`, `firstname`, `register_num`, `register_date`, `seniority_date`, `end_date`, `service`, `contract`, `is_active`) VALUES
('admin@email.com', 'admin', '$2a$10$zaiiueHY0DK4KzRriYs0/.NjWapsk9j7a.0D62R0GdgO4qxlOdfqa', 'ad', 'min', '000000', NOW(), '2010-01-01', '2099-12-31', 'HR_SERVICE', 'PM_CONTRACT', TRUE);

INSERT INTO `user_role` (`user_id`, `role_name`) VALUES (1, 'ADMIN');