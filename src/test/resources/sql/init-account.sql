INSERT INTO `account` (login_id, password, name, status, allow_to_marketing_notification) VALUES
    ('admin', '$2a$10$.VovHAAomyWfjLzkudnV2.5f8oRCUbJqjXbFDH99NOOAS7OTirv0q', 'admin', 'ACTIVE', false), -- Password: admin
    ('member', '$2a$10$JtZ/Qb4VjL1KIvLMgNFKGOSWU.4LSFDpJZkqYOB4tM2A/wg1N/Vse', 'member', 'ACTIVE', false); -- Password: member



INSERT INTO `account_role` (account_id, role_id) VALUES
    (1, 1), (1, 2),
    (2, 2);