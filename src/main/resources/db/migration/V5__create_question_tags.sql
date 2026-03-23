CREATE TABLE question_tags (
                               question_id UUID NOT NULL REFERENCES questions(id) ON DELETE CASCADE,
                               tag         VARCHAR(255) NOT NULL,
                               PRIMARY KEY (question_id, tag)
);