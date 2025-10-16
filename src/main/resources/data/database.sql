-- Таблица first_table (для справки)
CREATE TABLE IF NOT EXISTS first_table (
                             id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                             fio VARCHAR(255) NOT NULL,
                             email VARCHAR(255) UNIQUE NOT NULL,
                             fio_manager VARCHAR(255),
                             departments VARCHAR(255),
                             status VARCHAR(50),
                             url_photo VARCHAR(512),
                             about_employee TEXT
);

-- Таблица profiles: username уникален внутри profiles, FK к first_table(username)
CREATE TABLE IF NOT EXISTS managers (
                                        id SERIAL PRIMARY KEY,
                                        fio_manager VARCHAR(255) UNIQUE NOT NULL,
                                        department VARCHAR(255),
                                        bio TEXT,
                                        url_photo TEXT,
                                        created_at TIMESTAMPTZ DEFAULT now()
);

-- Таблица profiles: username уникален внутри profiles, FK к first_table(username)
CREATE TABLE IF NOT EXISTS status_employee (
                                               id SERIAL PRIMARY KEY,
                                               status VARCHAR(200) UNIQUE NOT NULL,
                                               created_at TIMESTAMPTZ DEFAULT now()
);

ALTER TABLE first_table
    ADD CONSTRAINT fk_managers_fio_manager
        FOREIGN KEY (fio_manager) REFERENCES managers(fio_manager);

ALTER TABLE first_table
    ADD CONSTRAINT fk_status_employee_status
        FOREIGN KEY (status) REFERENCES status_employee(status)
