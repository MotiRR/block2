--Создайте таблицу студенты (students): id, имя, серия паспорта, номер паспорта;
--Пара серия-номер паспорта должны быть уникальны в таблице Студенты;
CREATE TABLE students
(
    id bigserial NOT NULL,
    name text COLLATE pg_catalog."default",
    passport_serial integer,
    passport_number integer,
    CONSTRAINT students_pkey PRIMARY KEY (id),
    CONSTRAINT passport UNIQUE (passport_serial, passport_number)
);
--Заполнение таблицы студенты (students)
INSERT INTO public.students(name, passport_serial, passport_number) VALUES ('Alex', 1234, 123456);
INSERT INTO public.students(name, passport_serial, passport_number) VALUES ('Alice', 4321, 654321);
INSERT INTO public.students(name, passport_serial, passport_number) VALUES ('Vlad', 2134, 213456);
INSERT INTO public.students(name, passport_serial, passport_number) VALUES ('Diana', 3124, 312456);
INSERT INTO public.students(name, passport_serial, passport_number) VALUES ('Denis', 4123, 412356);

--Создайте таблицу Предметы (subjects): id, название предмета;
CREATE TABLE subjects
(
    id bigserial NOT NULL,
    name text COLLATE pg_catalog."default",
    CONSTRAINT subjects_pkey PRIMARY KEY (id)
);
--Заполнение таблицы Предметы (subjects)
INSERT INTO subjects(name) VALUES ('mathematics');
INSERT INTO subjects(name) VALUES ('physics');
INSERT INTO subjects(name) VALUES ('chemistry');
INSERT INTO subjects(name) VALUES ('biology');
INSERT INTO subjects(name) VALUES ('geography');



--Создайте таблицу Успеваемость (progress): id, студент, предмет, оценка;
--Оценка может находиться в пределах от 2 до 5;
--При удалении студента из таблицы, вся его успеваемость тоже должна быть удалена;
CREATE TABLE progress
(
    id bigserial NOT NULL,
    student bigint,
    subject bigint,
    mark smallint,
    CONSTRAINT progress_pkey PRIMARY KEY (id),
    CONSTRAINT student FOREIGN KEY (student)
        REFERENCES students (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT subject FOREIGN KEY (subject)
        REFERENCES subjects (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT mark CHECK (mark >= 2 AND mark <= 5)
);
--Заполнение таблицы Успеваемость (progress)
INSERT INTO progress(student, subject, mark) VALUES (2, 1, 2);
INSERT INTO progress(student, subject, mark) VALUES (2, 2, 5);
INSERT INTO progress(student, subject, mark) VALUES (2, 3, 5);
INSERT INTO progress(student, subject, mark) VALUES (2, 4, 4);

INSERT INTO progress(student, subject, mark) VALUES (3, 1, 5);
INSERT INTO progress(student, subject, mark) VALUES (3, 3, 5);
INSERT INTO progress(student, subject, mark) VALUES (3, 4, 3);
INSERT INTO progress(student, subject, mark) VALUES (3, 5, 2);

INSERT INTO progress(student, subject, mark) VALUES (4, 1, 4);
INSERT INTO progress(student, subject, mark) VALUES (4, 3, 4);
INSERT INTO progress(student, subject, mark) VALUES (4, 4, 5);
INSERT INTO progress(student, subject, mark) VALUES (4, 5, 5);

INSERT INTO progress(student, subject, mark) VALUES (5, 1, 3);
INSERT INTO progress(student, subject, mark) VALUES (5, 2, 2);
INSERT INTO progress(student, subject, mark) VALUES (5, 3, 5);
INSERT INTO progress(student, subject, mark) VALUES (5, 4, 5);

INSERT INTO progress(student, subject, mark) VALUES (7, 1, 3);
INSERT INTO progress(student, subject, mark) VALUES (7, 2, 4);
INSERT INTO progress(student, subject, mark) VALUES (7, 3, 2);
INSERT INTO progress(student, subject, mark) VALUES (7, 4, 2);

--Вывести список студентов, сдавших определенный предмет, на оценку выше 3;
SELECT students.id, students.name, progress.mark
	FROM students INNER JOIN progress ON students.id = progress.student
	INNER JOIN subjects ON progress.subject = subjects.id
	WHERE subjects.name = 'physics' and progress.mark > 3;


--Посчитать средний балл по определенному предмету;
SELECT s.name, avg(pr.mark)
	FROM progress as pr INNER JOIN subjects as s ON pr.subject = s.id
	WHERE s.name = 'physics'
	group by s.name;
--или
SELECT CAST(avg(pr.mark) as float4)
	FROM progress as pr INNER JOIN subjects as s ON pr.subject = s.id
	WHERE s.name = 'physics';

--Посчитать средний балл по определенному студенту;
SELECT st.name, avg(pr.mark)
	FROM progress as pr INNER JOIN students as st ON pr.student = st.id
	WHERE st.name = 'Alex'
	group by st.name;
--или
SELECT CAST(avg(pr.mark) as float4)
	FROM progress as pr INNER JOIN students as st ON pr.student = st.id
	WHERE st.name = 'Alex';

--Найти три предмета, которые сдали наибольшее количество студентов;
SELECT subjects.name, count(students.id)
	FROM students INNER JOIN progress ON students.id = progress.student
	INNER JOIN subjects ON progress.subject = subjects.id
	WHERE progress.mark > 2
	group by subjects.name
	ORDER BY count(students.id) DESC LIMIT 3;
