INSERT INTO users (password, role, user_type, created_at, updated_at) VALUES ('$2a$12$T8Kw20YmyCMYicuVsLsTpeyiIqJk/m3McXk64KizlRZ//GXDaRwf.', 0, 'ADMIN', NOW(), NOW());
INSERT INTO users (password, role, user_type, created_at, updated_at) VALUES ('$2a$12$T8Kw20YmyCMYicuVsLsTpeyiIqJk/m3McXk64KizlRZ//GXDaRwf.', 1, 'ADMIN', NOW(), NOW());
INSERT INTO users (password, role, user_type, created_at, updated_at) VALUES ('$2a$12$T8Kw20YmyCMYicuVsLsTpeyiIqJk/m3McXk64KizlRZ//GXDaRwf.', 2, 'ADMIN', NOW(), NOW());
INSERT INTO users (password, role, user_type, created_at, updated_at) VALUES ('$2a$12$T8Kw20YmyCMYicuVsLsTpeyiIqJk/m3McXk64KizlRZ//GXDaRwf.', 3, 'CUSTOMER', NOW(), NOW());

INSERT INTO admin (user_id, username, name) VALUES (1, 'superadmin', 'Super Admin');
INSERT INTO admin (user_id, username, name) VALUES (2, 'supervisor', 'Supervisor');
INSERT INTO admin (user_id, username, name) VALUES (3, 'consultant', 'Consultant');
INSERT INTO customers (user_id, first_name, last_name, email) VALUES (4, 'Jhon', 'Doe', 'jhondoe@mail.com');
