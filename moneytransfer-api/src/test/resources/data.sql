INSERT INTO CUSTOMER (id, name, lastname) VALUES (123, 'Lorenzo', 'Gagliani');
INSERT INTO CUSTOMER (id, name, lastname) VALUES (222, 'Filippo', 'Alberti');
INSERT INTO CUSTOMER (id, name, lastname) VALUES (333, 'Mario', 'Rossi');
INSERT INTO CUSTOMER (id, name, lastname) VALUES (444, 'Federico', 'Verdi');
INSERT INTO BANK_ACCOUNT (id, account_number, available_balance, commissions, currency_code, iban, transfer_limit, customer_id) VALUES (1, '0001234566', 100000, 0, 'EUR', 'IT34H7895966685880001234566', 1000, 123);
INSERT INTO BANK_ACCOUNT (id, account_number, available_balance, commissions, currency_code, iban, transfer_limit, customer_id) VALUES (2, '0001234577', 800, 10, 'EUR', 'IT34H7895966685880001234577', 1000, 222);
INSERT INTO BANK_ACCOUNT (id, account_number, available_balance, commissions, currency_code, iban, transfer_limit, customer_id) VALUES (3, '0001004599', 1300, 3, 'EUR', 'IT34H7895966685880001004599', 1200, 333);
INSERT INTO BANK_ACCOUNT (id, account_number, available_balance, commissions, currency_code, iban, transfer_limit, customer_id) VALUES (4, '0004004444', 2500, 3, 'EUR', 'IT34H7895966685880004004444', 600, 444);