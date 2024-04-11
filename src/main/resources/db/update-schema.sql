CREATE TABLE adresses
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    owner_id BIGINT NULL,
    main     BIT(1) NOT NULL,
    rua      VARCHAR(255) NULL,
    bairro   VARCHAR(255) NULL,
    cidade   VARCHAR(255) NULL,
    estado   VARCHAR(255) NULL,
    pais     VARCHAR(255) NULL,
    CONSTRAINT pk_adresses PRIMARY KEY (id)
);

CREATE TABLE areas
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    nome     VARCHAR(255) NULL,
    owner_id BIGINT NULL,
    CONSTRAINT pk_areas PRIMARY KEY (id)
);

CREATE TABLE areas_jobs
(
    area_id BIGINT NOT NULL,
    job_id  BIGINT NOT NULL
);

CREATE TABLE contacts
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    owner_id         BIGINT NOT NULL,
    geral            BIT(1) NOT NULL,
    main_candidatura BIT(1) NULL,
    email            VARCHAR(255) NULL,
    telefone         VARCHAR(255) NULL,
    CONSTRAINT pk_contacts PRIMARY KEY (id)
);

CREATE TABLE formats
(
    id   INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_formats PRIMARY KEY (id)
);

CREATE TABLE ies_received_jobs
(
    job_id BIGINT NOT NULL,
    org_id BIGINT NOT NULL
);

CREATE TABLE jobs
(
    id                    BIGINT AUTO_INCREMENT NOT NULL,
    owner_id              BIGINT NULL,
    address_id            BIGINT NULL,
    contact_id            BIGINT NULL,
    study_level_id        INT NULL,
    format_id             INT NULL,
    titulo                VARCHAR(255) NULL,
    id_externo_autor      VARCHAR(255) NULL,
    descricao             VARCHAR(255) NULL,
    imagem                VARCHAR(255) NULL,
    requisitos            VARCHAR(255) NULL,
    data_inicio           VARCHAR(255) NULL,
    data_final            VARCHAR(255) NULL,
    duracao_meses         INT   NOT NULL,
    remuneracao           FLOAT NOT NULL,
    carga_horaria_semanal INT   NOT NULL,
    criado_em             VARCHAR(255) NULL,
    atualizado_em         VARCHAR(255) NULL,
    CONSTRAINT pk_jobs PRIMARY KEY (id)
);

CREATE TABLE jobs_periods
(
    job_id    BIGINT NOT NULL,
    period_id INT    NOT NULL
);

CREATE TABLE levels
(
    id   INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_levels PRIMARY KEY (id)
);

CREATE TABLE orgs
(
    id                    BIGINT AUTO_INCREMENT NOT NULL,
    nome                  VARCHAR(255) NULL,
    cnpj                  VARCHAR(255) NULL,
    instituicao_de_ensino BIT(1) NULL,
    info                  VARCHAR(255) NULL,
    website               VARCHAR(255) NULL,
    redes_sociais         VARCHAR(255) NULL,
    criado_em             VARCHAR(255) NULL,
    atualizado_em         VARCHAR(255) NULL,
    CONSTRAINT pk_orgs PRIMARY KEY (id)
);

CREATE TABLE periods
(
    id   INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_periods PRIMARY KEY (id)
);

ALTER TABLE adresses
    ADD CONSTRAINT FK_ADRESSES_ON_OWNER FOREIGN KEY (owner_id) REFERENCES orgs (id);

ALTER TABLE areas
    ADD CONSTRAINT FK_AREAS_ON_OWNER FOREIGN KEY (owner_id) REFERENCES orgs (id);

ALTER TABLE contacts
    ADD CONSTRAINT FK_CONTACTS_ON_OWNER FOREIGN KEY (owner_id) REFERENCES orgs (id);

ALTER TABLE jobs
    ADD CONSTRAINT FK_JOBS_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES adresses (id);

ALTER TABLE jobs
    ADD CONSTRAINT FK_JOBS_ON_CONTACT FOREIGN KEY (contact_id) REFERENCES contacts (id);

ALTER TABLE jobs
    ADD CONSTRAINT FK_JOBS_ON_FORMAT FOREIGN KEY (format_id) REFERENCES formats (id);

ALTER TABLE jobs
    ADD CONSTRAINT FK_JOBS_ON_OWNER FOREIGN KEY (owner_id) REFERENCES orgs (id);

ALTER TABLE jobs
    ADD CONSTRAINT FK_JOBS_ON_STUDY_LEVEL FOREIGN KEY (study_level_id) REFERENCES levels (id);

ALTER TABLE areas_jobs
    ADD CONSTRAINT fk_arejob_on_area FOREIGN KEY (area_id) REFERENCES areas (id);

ALTER TABLE areas_jobs
    ADD CONSTRAINT fk_arejob_on_job FOREIGN KEY (job_id) REFERENCES jobs (id);

ALTER TABLE ies_received_jobs
    ADD CONSTRAINT fk_iesrecjob_on_job FOREIGN KEY (job_id) REFERENCES jobs (id);

ALTER TABLE ies_received_jobs
    ADD CONSTRAINT fk_iesrecjob_on_organization FOREIGN KEY (org_id) REFERENCES orgs (id);

ALTER TABLE jobs_periods
    ADD CONSTRAINT fk_jobper_on_job FOREIGN KEY (job_id) REFERENCES jobs (id);

ALTER TABLE jobs_periods
    ADD CONSTRAINT fk_jobper_on_period FOREIGN KEY (period_id) REFERENCES periods (id);