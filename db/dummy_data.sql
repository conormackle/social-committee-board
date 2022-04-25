insert into `users` (name, email, email_verified) values ('joe bloggs', 'joe.bloggs@aquaq.co.uk',true);
insert into `users` (name, email, email_verified) values ('mick mickerson', 'mick.mickerson@aquaq.co.uk',true);
insert into `users` (name, email, email_verified) values ('john johnson', 'john.johnson@aquaq.co.uk',true);

insert into `roles` (name) values ('admin');
insert into `roles` (name) values ('user');
insert into `roles` (name) values ('social_committee_member');

insert into `user_roles` (user_id, role_id) values ((select min(id) from `users` where email like 'joe.bloggs@aquaq.co.uk'), (select min(id) from roles where name like 'admin'));
insert into `user_roles` (user_id, role_id) values ((select min(id) from `users` where email like 'joe.bloggs@aquaq.co.uk'), (select min(id) from roles where name like 'user'));
insert into `user_roles` (user_id, role_id) values ((select min(id) from `users` where email like 'joe.bloggs@aquaq.co.uk'), (select min(id) from roles where name like 'social_committee_member'));
insert into `user_roles` (user_id, role_id) values ((select min(id) from `users` where email like 'mick.mickerson@aquaq.co.uk'), (select min(id) from roles where name like 'user'));
insert into `user_roles` (user_id, role_id) values ((select min(id) from `users` where email like 'john.johnson@aquaq.co.uk'), (select min(id) from roles where name like 'user'));
insert into `user_roles` (user_id, role_id) values ((select min(id) from `users` where email like 'john.johnson@aquaq.co.uk'), (select min(id) from roles where name like 'social_committee_member'));

insert into `event_categories` (name) values ('Social');

insert into `events` (name, date, details, created_by_user_id, event_category_id) values ('first event', current_date, 'an event', (select min(id) from `users` where email like 'joe.bloggs@aquaq.co.uk'), (select min(id) from `event_categories` where name like 'Social'));
insert into `events` (name, date, details, created_by_user_id, event_category_id) values ('second event', current_date, 'another event', (select min(id) from `users` where email like 'joe.bloggs@aquaq.co.uk'), (select min(id) from `event_categories` where name like 'Social'));

insert into `projects` (details, created_by_user_id) values ('a project', (select min(id) from `users` where email like 'joe.bloggs@aquaq.co.uk'));
insert into `projects` (details, created_by_user_id) values ('another project', (select min(id) from `users` where email like 'joe.bloggs@aquaq.co.uk'));

insert into `user_projects` (user_id, project_id) values ((select min(id) from `users` where email like 'joe.bloggs@aquaq.co.uk'), (select min(id) from projects where details like 'a project'));
insert into `user_projects` (user_id, project_id) values ((select min(id) from `users` where email like 'joe.bloggs@aquaq.co.uk'), (select min(id) from projects where details like 'another project'));

insert into `polls` (name, details) values ('first poll', 'a poll');
insert into `polls` (name, details) values ('second poll', 'another poll');

insert into `poll_options` (poll_id, name) values ((select min(id) from `polls` where name like 'first poll'), 'first poll option');
insert into `poll_options` (poll_id, name) values ((select min(id) from `polls` where name like 'first poll'), 'second poll option');

insert into `poll_option_vote` (poll_option_id, user_id) VALUES ((select min(id) from `polls` where name like 'first poll'),(select min(id) from `users` where email like 'joe.bloggs@aquaq.co.uk'));
insert into `poll_option_vote` (poll_option_id, user_id) VALUES ((select min(id) from `polls` where name like 'first poll'),(select min(id) from `users` where email like 'mick.mickerson@aquaq.co.uk'));

insert into `audit` (table_changed, timestamp, changed_by_user_id, entity_id, change_desc, change_type) values ('users', current_timestamp, (select min(id) from `users` where email like 'joe.bloggs@aquaq.co.uk'), (select min(id) from `users` where email like 'john.johnson@aquaq.co.uk'), 'name updated', 'update');

insert into `audit_child` (audit_id, field_changed, old_value, new_value) values (1, 'name', 'john jonson', 'john johnson');

insert into post_categories (name) values ('Social commitee');

insert into `posts` (content, user_id_posted_by, post_category_id) values ('This is a post', (select min(id) from `users` where email like 'joe.bloggs@aquaq.co.uk'), (select min(id) from `post_categories` where name like 'Social commitee'))

insert into `event_images` (event_id, image_path, thumbnail) values ((select min(id) from `events` where name like 'first event'), 'image_path', false);
insert into `event_images` (event_id, image_path, thumbnail) values ((select min(id) from `events` where name like 'first event'), 'image_path_1', true);