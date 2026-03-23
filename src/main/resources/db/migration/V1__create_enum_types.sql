CREATE TYPE question_type_enum AS ENUM (
    'MCQ_SINGLE',
    'MCQ_MULTI',
    'CODING'
);

CREATE TYPE difficulty_enum AS ENUM (
    'EASY',
    'MEDIUM',
    'HARD',
    'EXPERT'
);

CREATE TYPE question_status_enum AS ENUM (
    'DRAFT',
    'SUBMITTED',
    'APPROVED',
    'PUBLISHED'
);