insert into `users` (name, email, email_verified) values ('joe bloggs', 'joe.bloggs@aquaq.co.uk',true);
insert into `users` (name, email, email_verified) values ('mick mickerson', 'mick.mickerson@aquaq.co.uk',true);
insert into `users` (name, email, email_verified) values ('john johnson', 'john.johnson@aquaq.co.uk',true);

insert into `roles` (name) values ('admin');
insert into `roles` (name) values ('user');
insert into `roles` (name) values ('social_committee_member');

insert into `user_roles` (user_id, role_id) values ((select min(id) from `users` where email like 'joe.bloggs@aquaq.co.uk'), 1);
insert into `user_roles` (user_id, role_id) values ((select min(id) from `users` where email like 'joe.bloggs@aquaq.co.uk'), 2);
insert into `user_roles` (user_id, role_id) values ((select min(id) from `users` where email like 'joe.bloggs@aquaq.co.uk'), 3);
insert into `user_roles` (user_id, role_id) values ((select min(id) from `users` where email like 'mick.mickerson@aquaq.co.uk'), 2);
insert into `user_roles` (user_id, role_id) values ((select min(id) from `users` where email like 'john.johnson@aquaq.co.uk'), 2);
insert into `user_roles` (user_id, role_id) values ((select min(id) from `users` where email like 'john.johnson@aquaq.co.uk'), 3);

insert into `events` (name, date, details, created_by_user_id) values ('first event', current_date, 'an event', (select min(id) from `users` where email like 'joe.bloggs@aquaq.co.uk'));
insert into `events` (name, date, details, created_by_user_id) values ('second event', current_date, 'another event', (select min(id) from `users` where email like 'joe.bloggs@aquaq.co.uk'));

insert into `projects` (details, created_by_user_id) values ('a project', (select min(id) from `users` where email like 'joe.bloggs@aquaq.co.uk'));
insert into `projects` (details, created_by_user_id) values ('another project', (select min(id) from `users` where email like 'joe.bloggs@aquaq.co.uk'));

insert into `polls` (name, details) values ('first poll', 'a poll');
insert into `polls` (name, details) values ('second poll', 'another poll');

insert into `poll_options` (name, votes) values ('first poll option', 30);
insert into `poll_options` (name, votes) values ('second poll option', 25);

insert into `poll_poll_option` (poll_id, poll_option_id) values ((select min(id) from `polls` where name like 'first poll'), (select min(id) from `poll_options` where name like 'first poll option'));
insert into `poll_poll_option` (poll_id, poll_option_id) values ((select min(id) from `polls` where name like 'second poll'), (select min(id) from `poll_options` where name like 'first poll option'));
