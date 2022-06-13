INSERT INTO users (password, role, user_type, created_at, updated_at) VALUES ('$2a$12$T8Kw20YmyCMYicuVsLsTpeyiIqJk/m3McXk64KizlRZ//GXDaRwf.', 0, 'ADMIN', NOW(), NOW());
INSERT INTO users (password, role, user_type, created_at, updated_at) VALUES ('$2a$12$T8Kw20YmyCMYicuVsLsTpeyiIqJk/m3McXk64KizlRZ//GXDaRwf.', 1, 'ADMIN', NOW(), NOW());
INSERT INTO users (password, role, user_type, created_at, updated_at) VALUES ('$2a$12$T8Kw20YmyCMYicuVsLsTpeyiIqJk/m3McXk64KizlRZ//GXDaRwf.', 2, 'ADMIN', NOW(), NOW());
INSERT INTO users (password, role, user_type, created_at, updated_at) VALUES ('$2a$12$T8Kw20YmyCMYicuVsLsTpeyiIqJk/m3McXk64KizlRZ//GXDaRwf.', 3, 'CUSTOMER', NOW(), NOW());

INSERT INTO admin (user_id, username, name) VALUES (1, 'superadmin', 'Super Admin');
INSERT INTO admin (user_id, username, name) VALUES (2, 'supervisor', 'Supervisor');
INSERT INTO admin (user_id, username, name) VALUES (3, 'consultant', 'Consultant');
INSERT INTO customers (user_id, first_name, last_name, email) VALUES (4, 'Jhon', 'Doe', 'jhondoe@mail.com');

INSERT INTO complexes (name, description) VALUES ('Anggrek Permai', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse blandit a sem sit amet tincidunt. Donec sit amet leo sit amet urna viverra sagittis. Morbi neque purus, volutpat sit amet augue eget, finibus faucibus lacus. Sed sed metus sit amet turpis gravida vulputate.');

INSERT INTO buildings (complex_id, name, description) VALUES (1, 'BCA Tower', 'Enjoy the stunning view from from the 50th floor of BCA Tower, located across the street from the famous Bundaran Hotel Indonesia (Bundaran HI). Widely regarded as the center of Jakarta.');
INSERT INTO buildings (complex_id, name, description) VALUES (1, 'JB Tower', 'Enjoy the stunning view from from the 50th floor of BCA Tower, located across the street from the famous Bundaran Hotel Indonesia (Bundaran HI). Widely regarded as the center of Jakarta.');

INSERT INTO units (building_id, name, capacity, length, width, height, type, description) VALUES (1, 'ruang meeting', 10, 4, 3, 3.5, 2, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse blandit a sem sit amet tincidunt. Donec sit amet leo sit amet urna viverra sagittis.');

INSERT INTO unit_images (name, path, unit_id) VALUES ('photo 1', 'units/ExWmgjEJPD.jpg', 1);
INSERT INTO unit_images (name, path, unit_id) VALUES ('photo 2', 'units/Zekpch4ieq.jpg', 1);

INSERT INTO prices(price, type, unit_id) VALUES (50000, 0, 1);
INSERT INTO prices(price, type, unit_id) VALUES (250000, 1, 1);
INSERT INTO prices(price, type, unit_id) VALUES (1500000, 3, 1);
INSERT INTO prices(price, type, unit_id) VALUES (5000000, 3, 1);
INSERT INTO prices(price, type, unit_id) VALUES (2500000, 4, 1);