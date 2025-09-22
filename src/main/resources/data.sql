-- Car Models
INSERT INTO car_models (company_name, model_name) VALUES
                                                      ('Peugeot', '208'),
                                                      ('Peugeot', '208'),
                                                      ('Fiat', 'Argo'),
                                                      ('Fiat', 'Cronos'),
                                                      ('Toyota', 'Etios'),
                                                      ('Toyota', 'Yaris'),
                                                      ('Renault', 'Sandero'),
                                                      ('Renault', 'Logan'),
                                                      ('Volkswagen', 'Polo'),
                                                      ('Chevrolet', 'Onix');

-- Car Models Manufacturing Years
INSERT INTO car_model_manufacturing_years (car_model_id, manufacturing_year) VALUES
                                                                                 (1, 2023),
                                                                                 (1, 2024),
                                                                                 (1, 2025),
                                                                                 (2, 2021),
                                                                                 (2, 2022),
                                                                                 (2, 2023),
                                                                                 (3, 2021),
                                                                                 (3, 2022),
                                                                                 (4, 2021),
                                                                                 (4, 2022),
                                                                                 (5, 2023),
                                                                                 (5, 2024),
                                                                                 (6, 2023),
                                                                                 (6, 2024),
                                                                                 (7, 2020),
                                                                                 (7, 2021),
                                                                                 (8, 2020),
                                                                                 (8, 2021),
                                                                                 (9, 2024),
                                                                                 (9, 2025),
                                                                                 (10, 2024),
                                                                                 (10, 2025);

INSERT INTO car_model_colors (car_model_id, color) VALUES
                                                       (1, '#FF0000'), -- Red
                                                       (1, '#0000FF'), -- Blue
                                                       (1, '#000000'), -- Black
                                                       (2, '#FFFFFF'), -- White
                                                       (2, '#808080'), -- Gray
                                                       (3, '#C0C0C0'), -- Silver
                                                       (3, '#0000FF'), -- Blue
                                                       (4, '#FF0000'), -- Red
                                                       (4, '#000000'), -- Black
                                                       (5, '#FFFFFF'), -- White
                                                       (5, '#808080'), -- Gray
                                                       (6, '#C0C0C0'), -- Silver
                                                       (6, '#0000FF'), -- Blue
                                                       (7, '#FF0000'), -- Red
                                                       (7, '#000000'), -- Black
                                                       (8, '#FFFFFF'), -- White
                                                       (8, '#808080'), -- Gray
                                                       (9, '#C0C0C0'), -- Silver
                                                       (9, '#0000FF'), -- Blue
                                                       (10, '#FF0000'), -- Red
                                                       (10, '#000000'); -- Black
