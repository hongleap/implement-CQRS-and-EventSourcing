-- Insert some account types
INSERT INTO account_types (account_type_id, account_type_code)
VALUES
    ('99d8bb7d-c47a-44a8-bee5-fe82057490b5', 'SAVING'),
    ('cb2a48f8-1034-4dc9-a0ce-75dc34fc8542', 'CHECKING'),
    ('e33e97ac-7891-4113-8403-8b55476bfe89', 'LOAN'),
    ('0147c5ec-7386-4137-9c25-9cfb57340461', 'FIXED_DEPOSIT');

SELECT * FROM account_types;