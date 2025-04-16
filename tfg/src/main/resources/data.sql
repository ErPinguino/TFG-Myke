-- Insertar datos en la tabla app_user (o "user" si escapaste el nombre)
INSERT INTO minecraft_user (id, name, email) VALUES
(1, 'Juan Pérez', 'juan.perez@example.com'),
(2, 'María Gómez', 'maria.gomez@example.com'),
(3, 'Carlos López', 'carlos.lopez@example.com');

-- Insertar datos en la tabla seed
INSERT INTO seed (id, seed_value, user_id) VALUES
(1, 'seed123', 1),
(2, 'seed456', 2),
(3, 'seed789', 3);

-- Insertar datos en la tabla biome
INSERT INTO biome (id, name) VALUES
(1, 'Forest'),
(2, 'Desert'),
(3, 'Tundra');

-- Insertar datos en la tabla resources
INSERT INTO resources (id, name) VALUES
(1, 'Iron Ore'),
(2, 'Gold Ore'),
(3, 'Coal');

-- Insertar datos en la tabla resource_location
INSERT INTO resource_location (coordinatex, coordinatey, coordinatez, seed_id, resource_id, biome_id) VALUES
(10, 20, 30, 1, 1, 1),
(40, 50, 60, 2, 2, 2),
(70, 80, 90, 3, 3, 3);

-- Insertar datos en la tabla recipe
INSERT INTO recipe (id, object_name, image_url) VALUES
(1, 'Iron Sword', 'https://example.com/iron_sword.png'),
(2, 'Gold Shield', 'https://example.com/gold_shield.png'),
(3, 'Coal Torch', 'https://example.com/coal_torch.png');

-- Insertar datos en la tabla material_recipe
INSERT INTO material_recipe (id, recipe_id, resource_id) VALUES
(1, 1, 1), -- Iron Sword requiere Iron Ore
(2, 2, 2), -- Gold Shield requiere Gold Ore
(3, 3, 3); -- Coal Torch requiere Coal