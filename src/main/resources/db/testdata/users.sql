INSERT INTO `app_user` (`first_name`, `last_name`, `email`, `password`)
VALUES
    -- admin@example.com / admin
    ('Admin', 'Admin', 'admin@example.com', '{bcrypt}$2a$10$1rXMx0b4caUy/SN3Xg4j4u43gDqVJO/R.zXGCGWc/wr7bsmmSEk2C'),
    -- jankowalski@example.com / user
    ('Jan', 'Kowalski', 'jankowalski@example.com', '{bcrypt}$2a$10$HdTWfiLZkzlT9JXh/q3OhuPMsp972q0rp9oHba0wgaI0P.zRsLfb6');

INSERT INTO `app_user_role` (`name`, `description`)
VALUES ('ADMIN', 'Ma dostęp do wszystkiego'),
       ('USER', 'Dostęp ograniczony');

INSERT INTO `user_roles` (`user_id`, `role_id`)
VALUES (1, 1),
       (2, 2);