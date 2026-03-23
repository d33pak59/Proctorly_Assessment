CREATE TABLE topics (
                        id              BIGSERIAL PRIMARY KEY,
                        domain_id       BIGINT NOT NULL REFERENCES domains(id),
                        name            VARCHAR(255) NOT NULL,
                        parent_topic_id BIGINT REFERENCES topics(id),
                        created_by      VARCHAR(255),
                        created_at      TIMESTAMP NOT NULL DEFAULT now()
);