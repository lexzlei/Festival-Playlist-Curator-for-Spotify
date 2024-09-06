-- spotify user
CREATE TABLE IF NOT EXISTS spotify_user_details (
    id SERIAL PRIMARY KEY,
    access_token VARCHAR(2048),
    ref_id VARCHAR(255),
    refresh_token VARCHAR(2048),
    user_name VARCHAR(255)
);

-- festival
CREATE TABLE IF NOT EXISTS festivals (
    festivalID SERIAL PRIMARY KEY,
    festival_name VARCHAR(60),
    location VARCHAR(80),
    year INT,
    artists BYTEA, -- using BYTEA for binary data
    is_found BOOLEAN -- using BOOLEAN for bit(1)
);