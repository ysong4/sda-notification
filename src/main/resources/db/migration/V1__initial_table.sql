CREATE TABLE `notifications` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `type` VARCHAR(64) NOT NULL DEFAULT 'CHAT',
    `content` TEXT NOT NULL,
    `sender_id` INT NOT NULL,
    `receiver_id` INT NOT NULL,
    `status` VARCHAR(64) NOT NULL DEFAULT 'CREATED',
    PRIMARY KEY (`id`)
);