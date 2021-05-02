INSERT INTO file_system_object (id, name, owner_id, type)
SELECT * FROM (SELECT 'ROOT', 'root-folder', 'admin', 1) AS tmp
WHERE NOT EXISTS (
        SELECT name FROM file_system_object WHERE id = 'ROOT'
    ) LIMIT 1;