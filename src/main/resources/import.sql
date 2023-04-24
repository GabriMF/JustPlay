
-- INSERT INTO profiles VALUES (1, 'MIMIMI', 'LinkedIn', 'Gijon', 'Mari Carmen', 'Apellido')
-- INSERT INTO profiles VALUES (2, 'MIMIMI', 'LinkedIn', 'Murcia', 'Dieguuuu', 'Apellido')
-- INSERT INTO contacts VALUES (default,2)
INSERT INTO profiles (id, instagram, location, name, spotify, youtube) VALUES (1, 'insta', 'Asturias', 'Los Gatos de Chernobyl', 'spoti', 'youtube')
-- INSERT INTO contacts VALUES (,)

-- INSERT INTO users  VALUES (default, null, '$2a$10$GesR/t2ldE7wtXwh93Brd.txPEvLVbtE02LOwWmI9Rq40YWKWi4Ra', 'saul@gmail.com', 1)
-- INSERT INTO users VALUES (default, null, '$2a$10$GesR/t2ldE7wtXwh93Brd.txPEvLVbtE02LOwWmI9Rq40YWKWi4Ra', 'sa', 2)
INSERT INTO users (id, image, password, username, profile_id) VALUES (default, null, '$2a$10$4CYoRODUuhPwFFVtE770.O1ipllykAR7NhBqvCjBrOpiBG2LPr2ni', 'gobi@g', 1)


-- INSERT INTO profiles VALUES (default, 'Giiiii', 'Liiiii', 'Ubiiiii', 'Nomiiiii', 'Apiiiiii')
-- INSERT INTO posts VALUES (default, '2023-02-01','Recurso de prueba', 'Titulo de prueba', 1)
-- INSERT INTO users (id, username, password, profile_id) VALUES (default, "enol", "1234", 1)

INSERT INTO roles (id, role_name) VALUES (default, "ROLE_ADMIN")
INSERT INTO roles (id, role_name) VALUES (default, "ROLE_USER")

INSERT INTO user_roles (user_id, role_id) VALUES (1,2)
INSERT INTO profiles_contacts (profile_id, contact_id) VALUES (1,1)



-- INSERT INTO posts VALUES (default, '2023-02-01','Recurso de prueba', 'Titulo de prueba', 1)
-- INSERT INTO posts (id_post, title, date, description, image, profile_id) VALUES (default, 'publicacion', null, 'aplicacion java', 'null', 1)
-- INSERT INTO posts (id_post, title, date, description, image, profile_id) VALUES (default, 'VUE', null, 'aplicacion VUETIFY', 'null', 1)
-- INSERT INTO posts (id_post, title, date, description, image, profile_id) VALUES (default, 'mysql', null, 'aplicacion workbench', 'null', 1)
INSERT INTO posts (id_post, date, description, image, title, profile_id) VALUES (default,  null, 'Wall of sound incoming', 'Sleep.jpg', 'Sleep hermaaaaaaano', 1)
INSERT INTO posts (id_post, date, description, image, title, profile_id) VALUES (default,  null, 'Skate & Blunt, boy', 'Fu-Manchu-Band.jpg', 'Fu Manchu', 1)
INSERT INTO posts (id_post, date, description, image, title, profile_id) VALUES (default,  null, 'Just... Harsh Toke. Get ready', 'Harsh-Toke-Band.jpg', 'Harsh Toke', 1)
INSERT INTO posts (id_post, date, description, image, title, profile_id) VALUES (default,  null, 'Artilleria ionizada', 'losGatos.jpg', 'Los Fucking Gatos de Chernobyl', 1)
INSERT INTO posts (id_post, date, description, image, title, profile_id) VALUES (default,  null, 'El nombre lo dice todo... Earthless', 'Earthless.jpg', 'Earthless', 1)