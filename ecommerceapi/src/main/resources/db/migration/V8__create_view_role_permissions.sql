CREATE VIEW vw_role_with_permissions AS
SELECT
    r.name AS role_name,
    p.name AS permission_name
FROM role_permissions rp
         JOIN role r ON rp.role_id = r.role_id
         JOIN permission p ON rp.permission_id = p.permission_id
ORDER BY r.name, p.name;