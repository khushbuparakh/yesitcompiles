CREATE TABLE `projects` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
	`start_date` DATE NOT NULL,
	`description` TEXT NOT NULL,
	`is_active` BOOLEAN NOT NULL,
	`estimated_time` INT,
	`team_lead` INT NOT NULL,
	`target_audience` VARCHAR(255) NOT NULL,
	`end_date` DATE,
	`created_at` DATETIME NOT NULL,
	`deleted_at` DATETIME,
	`estimated_funds` DECIMAL NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `users` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(255) NOT NULL,
	`email` VARCHAR(255) NOT NULL,
	`password` VARCHAR(255) NOT NULL,
	`first_name` VARCHAR(255) NOT NULL,
	`last_name` VARCHAR(255) NOT NULL,
	`Date_of_birth` DATE NOT NULL,
	`role` VARCHAR(255) NOT NULL,
	`gender` VARCHAR(255) NOT NULL,
	`city` VARCHAR(255),
	PRIMARY KEY (`id`)
);

CREATE TABLE `project_instances` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`project_id` INT NOT NULL,
	`city` VARCHAR(255) NOT NULL,
	`address` TEXT,
	PRIMARY KEY (`id`)
);

CREATE TABLE `project_to_do_list` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`project_id` INT NOT NULL,
	`task` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `project_instance_personnel` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`project_instance_id` INT NOT NULL,
	`user_id` INT NOT NULL,
	`role_project` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `funds_received` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`date` DATE NOT NULL,
	`amount(in rs)` DECIMAL NOT NULL,
	`project_id` INT NOT NULL,
	`source` TEXT,
	PRIMARY KEY (`id`)
);

CREATE TABLE `funds_spent` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`project_id` INT NOT NULL,
	`date` DATE NOT NULL,
	`amout_alotted` DATE NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE `projects` ADD CONSTRAINT `projects_fk0` FOREIGN KEY (`team_lead`) REFERENCES `users`(`id`);

ALTER TABLE `project_instances` ADD CONSTRAINT `project_instances_fk0` FOREIGN KEY (`project_id`) REFERENCES `projects`(`id`);

ALTER TABLE `project_to_do_list` ADD CONSTRAINT `project_to_do_list_fk0` FOREIGN KEY (`project_id`) REFERENCES `projects`(`id`);

ALTER TABLE `project_instance_personnel` ADD CONSTRAINT `project_instance_personnel_fk0` FOREIGN KEY (`project_instance_id`) REFERENCES `project_instances`(`id`);

ALTER TABLE `project_instance_personnel` ADD CONSTRAINT `project_instance_personnel_fk1` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`);

ALTER TABLE `funds_received` ADD CONSTRAINT `funds_received_fk0` FOREIGN KEY (`project_id`) REFERENCES `projects`(`id`);

ALTER TABLE `funds_spent` ADD CONSTRAINT `funds_spent_fk0` FOREIGN KEY (`project_id`) REFERENCES `projects`(`id`);