CREATE TABLE IF NOT EXISTS posts (
    id serial PRIMARY KEY,
    user_id INTEGER ,
	nama VARCHAR(250),
	alamat VARCHAR(250),
	kelurahan VARCHAR(250),
	kota VARCHAR(250),
	propinsi VARCHAR(250),
	hobi VARCHAR(250),
	makanan_favorit VARCHAR(250),
	minuman_favorit VARCHAR(250),
	image VARCHAR(250),
	video VARCHAR(250),
	document VARCHAR(250),
	created_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS master_users (
    id serial PRIMARY KEY,
	username VARCHAR ( 50 ) UNIQUE NOT NULL,
	password VARCHAR ( 255 ) NOT NULL,
	email VARCHAR ( 255 ) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS plans (
    id serial PRIMARY KEY,
	name VARCHAR ( 50 ) UNIQUE NOT NULL,
    limit_per_hour INTEGER
);

CREATE TABLE IF NOT EXISTS user_plans (
    id serial PRIMARY KEY,
    user_id INTEGER ,
    plan_id INTEGER ,
	is_active BOOLEAN DEFAULT true
);