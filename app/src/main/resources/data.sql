/* APPLICANT */
INSERT into applicant(name, identify_number, create_at, update_at)
values ('duy', '111222333', EXTRACT(EPOCH from CURRENT_TIMESTAMP()), EXTRACT(EPOCH from CURRENT_TIMESTAMP()));

INSERT into applicant(name, identify_number, create_at, update_at)
values ('dat', '111222334', EXTRACT(EPOCH from CURRENT_TIMESTAMP()), EXTRACT(EPOCH from CURRENT_TIMESTAMP()));

/* CREDIT */
INSERT into credit(applicant_id, create_at, update_at, total_limit, currency, start_date, end_date, credit_type)
values (1,
        EXTRACT(EPOCH from CURRENT_TIMESTAMP()),
        EXTRACT(EPOCH from CURRENT_TIMESTAMP()),
        100000000,
        'VND',
        CURRENT_DATE(),
        CURRENT_DATE() + 100,
        'CAR');

INSERT into credit(applicant_id, create_at, update_at, total_limit, currency, start_date, end_date, credit_type)
values (1,
        EXTRACT(EPOCH from CURRENT_TIMESTAMP()),
        EXTRACT(EPOCH from CURRENT_TIMESTAMP()),
        200000000,
        'VND',
        CURRENT_DATE(),
        CURRENT_DATE() + 100,
        'HOME');

INSERT into credit(applicant_id, create_at, update_at, total_limit, currency, start_date, end_date, credit_type)
values (2,
        EXTRACT(EPOCH from CURRENT_TIMESTAMP()),
        EXTRACT(EPOCH from CURRENT_TIMESTAMP()),
        500000000,
        'VND',
        CURRENT_DATE(),
        CURRENT_DATE() + 100,
        'CAR');

/* LOAN */
INSERT into loan(credit_id, applicant_id, create_at, update_at, amount, currency, start_date, end_date, interest)
values (1,
        1,
        EXTRACT(EPOCH from CURRENT_TIMESTAMP()),
        EXTRACT(EPOCH from CURRENT_TIMESTAMP()),
        100000000,
        'VND',
        CURRENT_DATE(),
        CURRENT_DATE() + 100,
        16.0);

/* PAYMENT */
INSERT into payment(loan_id, create_at, update_at, payment_date, due_date, amount)
values (1,
        EXTRACT(EPOCH from CURRENT_TIMESTAMP()),
        EXTRACT(EPOCH from CURRENT_TIMESTAMP()),
        CURRENT_DATE(),
        CURRENT_DATE() + 100,
        1000000);