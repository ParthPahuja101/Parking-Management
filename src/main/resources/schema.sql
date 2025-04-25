CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS parking_slots (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(255),
    is_occupied VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS parking_records (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    parking_slot_id BIGINT,
    vehicle_number VARCHAR(255),
    checkin_time VARCHAR(255),
    checkout_time VARCHAR(255)
);