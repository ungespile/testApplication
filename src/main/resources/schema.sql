create table cards
(
    id          bigserial not null
        constraint cards_pkey
            primary key,
    created     timestamp(6),
    updated     timestamp(6),
    balance     numeric(38, 2),
    card_number varchar(255) unique,
    client_id   varchar(255),
    is_active   boolean
);

create index cards_clientId on cards(client_id);

alter table cards
    owner to postgres;


create table transfers
(
    id                 bigserial not null
        constraint transfers_pkey
            primary key,
    created            timestamp(6),
    updated            timestamp(6),
    recipient_card_num varchar(255),
    sender_card_num    varchar(255),
    value              numeric(38, 2)
);

alter table transfers
    owner to postgres;

