CREATE TABLE student(
	student_id SERIAL PRIMARY KEY,
	email VARCHAR(50) NOT NULL,
	pass VARCHAR(50) NOT NULL,
	fullname VARCHAR(50) NOT NULL,
	age INT NOT NULL,
	gender CHAR NOT NULL,
	education TEXT,
	working_ex TEXT,
	personal_stat TEXT,
	contact_info TEXT
);

CREATE TABLE company (
	company_id SERIAL PRIMARY KEY,
	email VARCHAR(50) NOT NULL,
	pass VARCHAR(20) NOT NULL,
	company_name VARCHAR(20) NOT NULL,
	description TEXT
);

CREATE TABLE vacancy (
	vacancy_id SERIAL PRIMARY KEY,
	description TEXT NOT NULL,
	salary NUMERIC(2) NOT NULL,
	company_id INT NOT NULL REFERENCES company(company_id)
);

CREATE TABLE student_vacancy(
	student_id INT NOT NULL REFERENCES student(student_id),
	vacancy_id INT NOT NULL REFERENCES vacancy(vacancy_id),
	PRIMARY KEY(student_id, vacancy_id)
);