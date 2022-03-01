/* harry brankin initial table create queries */

CREATE TABLE `users` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(155),
	`email` VARCHAR(155),
	`location` VARCHAR(55),
	`email_verified` BOOLEAN NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `events` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(155),
	`date` DATETIME,
	`details` VARCHAR(555),
    `created_by_user_id` INT NOT NULL,
    FOREIGN KEY (created_by_user_id) REFERENCES users(id),
	PRIMARY KEY (`id`)
);

CREATE TABLE `roles` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(155) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `user_roles` (
	`user_id` INT NOT NULL,
	`role_id` INT NOT NULL,
     FOREIGN KEY (user_id) REFERENCES users(id),
      FOREIGN KEY (role_id) REFERENCES roles(id),
	  PRIMARY KEY (role_id, user_id)
);

CREATE TABLE `polls` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(155) NOT NULL,
	`details` VARCHAR(555),
	PRIMARY KEY (`id`)
);

CREATE TABLE `poll_options` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`poll_id` INT NOT NULL,
	`name` VARCHAR(155) NOT NULL,
	FOREIGN KEY (poll_id) REFERENCES polls(id),
	PRIMARY KEY (`id`)
);

CREATE TABLE `projects` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`details` VARCHAR(555) NOT NULL,
    `created_by_user_id` INT NOT NULL,
    FOREIGN KEY (created_by_user_id) REFERENCES users(id),
	PRIMARY KEY (`id`)
);

CREATE TABLE `user_projects` (
	`user_id` INT NOT NULL,
	`project_id` INT NOT NULL,
     FOREIGN KEY (user_id) REFERENCES users(id),
      FOREIGN KEY (project_id) REFERENCES projects(id),
	  PRIMARY KEY (project_id, user_id)
);

CREATE TABLE `audit` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`table_changed` VARCHAR(55) NOT NULL,
	`timestamp` DATETIME NOT NULL,
	`changed_by_user_id` INT NOT NULL,
	`entity_id` INT NOT NULL,
	`change_desc` VARCHAR(55),
	`change_type` VARCHAR(55),
	PRIMARY KEY (`id`),
    FOREIGN KEY (changed_by_user_id) REFERENCES users(id)
);

CREATE TABLE `audit_child` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`audit_id` INT NOT NULL,
	`field_changed` VARCHAR(55) NOT NULL,
	`old_value` VARCHAR(255),
	`new_value` VARCHAR(255),
	PRIMARY KEY (`id`),
    FOREIGN KEY (audit_id) REFERENCES audit(id)
);

CREATE TABLE `post_categories` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(55) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `posts` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`content` VARCHAR(555) NOT NULL,
	`user_id_posted_by` INT NOT NULL,
	`post_category_id` INT NOT NULL,
	PRIMARY KEY (`id`),
	FOREIGN KEY (`post_category_id`) REFERENCES post_categories(id),
	FOREIGN KEY (`user_id_posted_by`) REFERENCES users(id)
);

CREATE TABLE `poll_option_vote` (
	`poll_option_id` INT NOT NULL,
	`user_id` INT NOT NULL,
     FOREIGN KEY (user_id) REFERENCES users(id),
      FOREIGN KEY (poll_option_id) REFERENCES poll_options(id),
	  PRIMARY KEY (poll_option_id, user_id)
);