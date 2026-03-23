CREATE TABLE question_versions (
                                   question_id  UUID NOT NULL REFERENCES questions(id) ON DELETE CASCADE,
                                   version      INT NOT NULL,
                                   content_json JSONB NOT NULL,
                                   changed_by   VARCHAR(255),
                                   changed_at   TIMESTAMP NOT NULL DEFAULT now(),
                                   PRIMARY KEY (question_id, version)
);