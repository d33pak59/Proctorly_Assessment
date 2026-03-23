CREATE TABLE domains (
                         id          BIGSERIAL PRIMARY KEY,
                         tenant_id   UUID NOT NULL,
                         name        VARCHAR(255) NOT NULL,
                         description TEXT,
                         created_by  VARCHAR(255),
                         created_at  TIMESTAMP NOT NULL DEFAULT now()
);