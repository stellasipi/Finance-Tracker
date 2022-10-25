create table "user"
(
    id                uuid         not null primary key,
    email             varchar(255) not null constraint uk_ob8kqyqqgmefl0aco34akdtpe unique,
    name              varchar(255) not null,
    password          varchar(255) not null,
    registration_date timestamp    not null,
    role              varchar(255) not null,
    username          varchar(255) not null constraint uk_sb8bbouer5wak8vyiiy4pf2bx unique
);

INSERT INTO "user"(id, email, name, password, registration_date, role, username)
    VALUES ('084891c6-5f32-4b89-a3cd-db03436da751','zsolt9999@gmail.com','Zsolt Németh','$2a$10$xr0gUlU2EFVBPT3su4LdJ.qHB/DSzJwyTmsEDJhwHjiGittU0hRse','2022-10-08 21:55:48.231817','USER','zsolt.nemeth');
INSERT INTO "user"(id, email, name, password, registration_date, role, username)
VALUES ('a2765a27-c225-48ba-8fd7-1494f691c3ea','stellasipi@gmail.com','Stella Tóth-Baranyi','$2a$10$hOake6jwX3evU/p59QAIdOwl8T2vqSkrsTPlS3Yn..tIRBcVli9oG','2022-10-08 21:54:24.462306','ADMIN','stella.toth-baranyi');