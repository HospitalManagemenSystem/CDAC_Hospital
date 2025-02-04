CREATE TABLE base_entity (
    id BIGINT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE user_base (
    id BIGINT PRIMARY KEY,
    username VARCHAR(30) UNIQUE NOT NULL,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    email VARCHAR(30) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    dob DATE,
    gender ENUM('MALE', 'FEMALE', 'OTHER'),
    mobile_number VARCHAR(10) NOT NULL,
    area VARCHAR(100),
    city VARCHAR(50),
    state VARCHAR(50),
    FOREIGN KEY (id) REFERENCES base_entity(id)
);

-- Admin table
CREATE TABLE admin_tbl (
    id BIGINT PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    email VARCHAR(30) NOT NULL,
    password VARCHAR(100) NOT NULL,
    FOREIGN KEY (id) REFERENCES base_entity(id)
);

-- Doctor table with inheritance from user
CREATE TABLE doctor_tbl (
    id BIGINT PRIMARY KEY,
    languages VARCHAR(30) NOT NULL,
    specialization VARCHAR(30) NOT NULL,
    qualification VARCHAR(30) NOT NULL,
    fees INT,
    FOREIGN KEY (id) REFERENCES user_base(id)
);

-- Doctor timetable
CREATE TABLE doctor_timetable_tbl (
    id BIGINT PRIMARY KEY,
    start_date DATE,
    end_date DATE,
    start_time TIME,
    end_time TIME,
    slot_duration INT,
    break_time TIME,
    doctor_id BIGINT UNIQUE,
    FOREIGN KEY (id) REFERENCES base_entity(id),
    FOREIGN KEY (doctor_id) REFERENCES doctor_tbl(id)
);

-- Element collection for doctor holidays
CREATE TABLE doctor_timetable_holidays (
    doctor_timetable_id BIGINT,
    holidays VARCHAR(50),
    FOREIGN KEY (doctor_timetable_id) REFERENCES doctor_timetable_tbl(id)
);

-- Element collection for available slots
CREATE TABLE doctor_timetable_available_slots (
    doctor_timetable_id BIGINT,
    slot_time DATETIME,
    is_available BOOLEAN,
    FOREIGN KEY (doctor_timetable_id) REFERENCES doctor_timetable_tbl(id)
);

-- Patient table with inheritance from user
CREATE TABLE patient_tbl (
    id BIGINT PRIMARY KEY,
    blood_group ENUM('A_POSITIVE', 'A_NEGATIVE', 'B_POSITIVE', 'B_NEGATIVE', 
                    'AB_POSITIVE', 'AB_NEGATIVE', 'O_POSITIVE', 'O_NEGATIVE'),
    FOREIGN KEY (id) REFERENCES user_base(id)
);

-- Appointment table
CREATE TABLE appointment_tbl (
    id BIGINT PRIMARY KEY,
    appointment_time DATETIME,
    appointment_type VARCHAR(20) DEFAULT 'CLINIC_VISIT',
    doctor_id BIGINT NOT NULL,
    patient_id BIGINT NOT NULL,
    FOREIGN KEY (id) REFERENCES base_entity(id),
    FOREIGN KEY (doctor_id) REFERENCES doctor_tbl(id),
    FOREIGN KEY (patient_id) REFERENCES patient_tbl(id)
);

-- Blood donor table
CREATE TABLE blood_donor_tbl (
    id BIGINT PRIMARY KEY,
    name VARCHAR(30),
    email VARCHAR(30),
    contact_number VARCHAR(10),
    blood_group ENUM('A_POSITIVE', 'A_NEGATIVE', 'B_POSITIVE', 'B_NEGATIVE', 
                    'AB_POSITIVE', 'AB_NEGATIVE', 'O_POSITIVE', 'O_NEGATIVE'),
    city VARCHAR(30),
    state VARCHAR(30),
    FOREIGN KEY (id) REFERENCES base_entity(id)
);
