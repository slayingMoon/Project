insert into trip_city (name) values ('Sofia');
insert into trip_city (name) values ('Kostenetz');
insert into trip_city (name) values ('Ihtiman');
insert into trip_city (name) values ('Belovo');
insert into trip_city (name) values ('Pazardjik');
insert into trip_city (name) values ('Plovdiv');
insert into trip_city (name) values ('Stara Zagora');
insert into trip_city (name) values ('Yambol');
insert into trip_city (name) values ('Sliven');
insert into trip_city (name) values ('Burgas');

insert into base_trip (departure_time, description) values ('09:00:00', 'Sofia-Plovdiv');
insert into base_trip (departure_time, description) values ('08:00:00', 'Plovdiv-Burgas');

insert into trip (day_of_week, base_trip_id) values ('WEDNESDAY', 1);
insert into trip (day_of_week, base_trip_id) values ('FRIDAY', 2);

insert into transition (travel_duration, city_id, trip_id) values (0,1,1);
insert into transition (travel_duration, city_id, trip_id) values (1,2,1);
insert into transition (travel_duration, city_id, trip_id) values (2,3,1);
insert into transition (travel_duration, city_id, trip_id) values (3,4,1);
insert into transition (travel_duration, city_id, trip_id) values (4,5,1);
insert into transition (travel_duration, city_id, trip_id) values (5,6,1);
insert into transition (travel_duration, city_id, trip_id) values (6,7,1);
insert into transition (travel_duration, city_id, trip_id) values (7,8,1);
insert into transition (travel_duration, city_id, trip_id) values (8,9,1);
insert into transition (travel_duration, city_id, trip_id) values (0,6,2);
insert into transition (travel_duration, city_id, trip_id) values (1,7,2);
insert into transition (travel_duration, city_id, trip_id) values (3,8,2);
insert into transition (travel_duration, city_id, trip_id) values (5,9,2);
insert into transition (travel_duration, city_id, trip_id) values (6,10,2);

insert into transition_options (transition_property) values ('GET_ON');
insert into transition_options (transition_property) values ('GET_OFF');
insert into transition_options (transition_property) values ('ONLINE');

insert into transition_options_mapping (transition_id, options_id) values (1,1);
insert into transition_options_mapping (transition_id, options_id) values (2,2);
insert into transition_options_mapping (transition_id, options_id) values (3,2);
insert into transition_options_mapping (transition_id, options_id) values (4,3);
insert into transition_options_mapping (transition_id, options_id) values (4,1);

insert into emergency_trip (date, base_trip_id) values ('2022-08-08', 1);