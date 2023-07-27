INSERT INTO "master_users" ("username", "email", "password") VALUES ('admin', 'admin@gmail.com', '$2a$10$FT9YslxDFdE8gG85WKJ/5uFaBpPQDa6aeTMon9AOFPSU4NCydRLRC');
INSERT INTO plans (limit_per_hour, name) VALUES (20, 'FREE');
INSERT INTO plans (limit_per_hour, name) VALUES (40, 'BUSINESS');
INSERT INTO plans (limit_per_hour, name) VALUES (100, 'PROFESSIONAL');
INSERT INTO user_plans (user_id, plan_id,is_active) VALUES (1, 1, true);