CREATE TABLE questions (
                           id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                           tenant_id    UUID NOT NULL,
                           topic_id     BIGINT NOT NULL REFERENCES topics(id),
                           type         question_type_enum NOT NULL,
                           difficulty   difficulty_enum NOT NULL,
                           credit_cost  INT NOT NULL,
                           content_json JSONB NOT NULL,
                           version      INT NOT NULL DEFAULT 1,
                           status       question_status_enum NOT NULL DEFAULT 'DRAFT',
                           is_deleted   BOOLEAN NOT NULL DEFAULT false,
                           created_by   VARCHAR(255),
                           created_at   TIMESTAMP NOT NULL DEFAULT now(),
                           updated_at   TIMESTAMP NOT NULL DEFAULT now()
);