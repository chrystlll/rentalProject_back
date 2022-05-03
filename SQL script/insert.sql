-- delete all tables

delete from manage_rental.scheduled_payment ;
delete from manage_rental.price;
delete from manage_rental.contract;
delete from manage_rental.main_tenant;

-- Add the main tenant

INSERT INTO manage_rental.main_tenant
(id, dob, email, first_name, gender, last_name, phone_number1, phone_number2, social_number, common_status)
VALUES(1, NULL, 'LOLO@GMAIL.COM', 'DUPOND', NULL, 'LOLO', NULL, NULL, NULL, 'ACTIF');

-- Add the main tenant contract

INSERT INTO manage_rental.contract
(id, common_status, contract_type, end_date, initial_amount, initial_duration_type, scheduled_payment_type, start_date, main_tenant_id, renter_id)
VALUES(1, 'ACTIF', 'LOGEMENT', NULL, 120.0, 'MOIS', 'ANNUEL', '2022-04-06 02:00:00', 1, NULL);

-- Add the prices (some are inactive)

INSERT INTO manage_rental.price
(id, amount, common_status, currency, duration_type, end_date, start_date)
VALUES(1, 120.0, 'ACTIF', '€', 'MOIS', NULL, '2022-04-06 02:00:00');

INSERT INTO manage_rental.price
(id, amount, common_status, currency, duration_type, end_date, start_date)
VALUES(2, 120.0, 'INACTIF', '€', 'MOIS', '2021-04-06 02:00:00', '2021-04-06 02:00:00');

-- Add the relative payments (some are inactive)

INSERT INTO manage_rental.scheduled_payment
(id, amount, currency, due_date, end_date, payment_date, payment_type, scheduled_payment_generation_date, start_date, contract_id, price_id,common_status)
VALUES(1, 1440.0, '€', '2021-04-06 02:00:00', '2022-04-06 02:00:00', NULL, NULL, '2021-04-06 02:00:00', '2021-04-06 02:00:00', 1, 2,'ACTIF');

INSERT INTO manage_rental.scheduled_payment
(id, amount, currency, due_date, end_date, payment_date, payment_type, scheduled_payment_generation_date, start_date, contract_id, price_id,common_status)
VALUES(2, 1440.0, '€', '2022-04-06 02:00:00', '2023-04-06 02:00:00', NULL, NULL, '2022-04-06 02:00:00', '2022-04-06 02:00:00',1, 1,'ACTIF');

INSERT INTO manage_rental.scheduled_payment
(id, amount, currency, due_date, end_date, payment_date, payment_type, scheduled_payment_generation_date, start_date, contract_id, price_id,common_status)
VALUES(3, 1440.0, '€', '2023-04-07 02:00:00', '2024-04-07 02:00:00', NULL, NULL, '2023-04-07 02:00:00', '2023-04-07 02:00:00', 1, 1,'ACTIF');


INSERT INTO manage_rental.place
(id, common_status, location_size, name, address_id, contract_id)
VALUES(0, 'ACTIF', 0, 'Lieu 1', null, null);

INSERT INTO manage_rental.place
(id, common_status, location_size, name, address_id, contract_id)
VALUES(1, 'INACTIF', 0, 'Lieu 2', null, null);