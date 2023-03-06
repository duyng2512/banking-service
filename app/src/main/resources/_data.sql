/* APPLICANT */
INSERT into applicant(name, identify_number, create_at, update_at)
values ('Henry', '111222333', EXTRACT(EPOCH from CURRENT_TIMESTAMP()), EXTRACT(EPOCH from CURRENT_TIMESTAMP()));

/* CREDIT */
INSERT into credit(applicant_id, create_at, update_at,
                   total_limit, currency, start_date,
                   end_date, credit_type, reg_number)
values (1,
        EXTRACT(EPOCH from CURRENT_TIMESTAMP()),
        EXTRACT(EPOCH from CURRENT_TIMESTAMP()),
        1000,
        'USD', CURRENT_DATE(), CURRENT_DATE() + 365,
        'CAR',
        '57292');

INSERT into credit(applicant_id, create_at, update_at,
                   total_limit, currency, start_date,
                   end_date, credit_type, reg_number)
values (1,
        EXTRACT(EPOCH from CURRENT_TIMESTAMP()),
        EXTRACT(EPOCH from CURRENT_TIMESTAMP()),
        2000,
        'USD',
        CURRENT_DATE(),
        CURRENT_DATE() + 365,
        'HOME',
        '71682');

/* LOAN */
INSERT into loan(applicant_id, create_at, update_at,
                 amount, currency, start_date, end_date,
                 interest, period_payment)
values (1,
        EXTRACT(EPOCH from CURRENT_TIMESTAMP()),
        EXTRACT(EPOCH from CURRENT_TIMESTAMP()),
        100000000,
        'VND',
        CURRENT_DATE(),
        CURRENT_DATE() + 365,
        16.0);

/* PAYMENT */
INSERT into payment(loan_id, create_at, update_at, payment_date, due_date, amount)
values (1,
        EXTRACT(EPOCH from CURRENT_TIMESTAMP()),
        EXTRACT(EPOCH from CURRENT_TIMESTAMP()),
        CURRENT_DATE(),
        CURRENT_DATE() + 100,
        1000000);

