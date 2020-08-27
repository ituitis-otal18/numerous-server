CREATE TABLE tweets (
    id UUID NOT NULL PRIMARY KEY,
    user_id UUID NOT NULL,
    user_number INT NOT NULL,
    text VARCHAR(150) NOT NULL,
    post_date VARCHAR(20) NOT NULL
)