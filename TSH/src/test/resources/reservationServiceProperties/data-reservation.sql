
INSERT INTO `bus` VALUES (1,60);
INSERT INTO `seat` VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10),(11,11),(12,12),(13,13),(14,14),(15,15),(16,16),(17,17),(18,18),(19,19),(20,20),(21,21),(22,22),(23,23),(24,24),(25,25),(26,26),(27,27),(28,28),(29,29),(30,30),(31,31),(32,32),(33,33),(34,34),(35,35),(36,36),(37,37),(38,38),(39,39),(40,40);


INSERT INTO `driver` VALUES (1,'Shofior','0895433211','0891235665'),(2,'Shofior2','0895430011','0891200665'),(3,'Djivko','0895430019','0891200669'),(4,'Zdravko','0895430111','0891200165');


INSERT INTO `bus_drivers` VALUES (1,1),(1,2),(1,3),(1,4);

INSERT INTO `reservation_city` VALUES (2,'BULGARIA','Blagoevgrad'),(3,'BULGARIA','Sandanski'),(1,'BULGARIA','Sofia'),(5,'GREECE','Atina'),(4,'GREECE','Solun');


INSERT INTO `scheduled_transition` VALUES (1,'2022-06-17 15:47:06.658859',1),(2,'2022-06-17 16:47:06.659891',2),(3,'2022-06-17 17:47:06.659891',3),(4,'2022-06-17 18:47:06.659891',4),(5,'2022-06-17 21:47:06.659891',5);
INSERT INTO `scheduled_trip` VALUES (1,1);


INSERT INTO `scheduled_trip_scheduled_transitions` VALUES (1,1),(1,2),(1,3),(1,4),(1,5);
INSERT INTO `scheduled_transition_seats` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(1,23),(1,24),(1,25),(1,26),(1,27),(1,28),(1,29),(1,30),(1,31),(1,32),(1,33),(1,34),(1,35),(1,36),(1,37),(1,38),(1,39),(1,40),(2,16),(2,17),(2,18),(2,19),(2,20),(5,31),(5,32),(5,33),(5,34),(5,35),(5,36),(5,37),(5,38),(5,39),(5,40),(3,21),(3,22),(3,23),(3,24),(3,25),(3,5),(3,20),(3,1),(3,2),(3,3),(3,4),(3,6),(3,7),(4,26),(4,27),(4,28),(4,29),(4,30),(4,20),(4,1),(4,2),(4,3),(4,4),(4,6),(4,7),(4,2),(4,33);

INSERT INTO `direction` VALUES (1,3,5),(2,5,3),(3,3,5),(4,3,5),(5,3,5),(6,3,5),(7,5,3),(8,5,3),(9,5,3),(10,3,5);

INSERT INTO `passenger` (age,email,first_name,last_name,middle_name,phone_number) VALUES (20,'an2111ikas19311@abv.bfg','Aanasrika','Kartselska','Petrova','0894617342'),(20,'an2d111ikas19431@abv.bg','Aanasrika','Kartselska','Petrova','0894617327'),(20,'an2d111ikas194311@abv.bg','Aanasrika','Kartselska','Petrova','0891734238'),(20,'1an2d111ikas194311@abv.bg','Aanasrika','Kartselska','Petrova','1089123733'),(20,'nsjknwejd@abv.bg','Aanasrika','Kartselska','Petrova','1089173555'),(20,'fsglers@abv.bg','Aanasrika','Kartselska','Petrova','1089217366'),(20,'kdfsvkdlld@abv.bg','Aanasrika','Kartselska','Petrova','1089221734'),(20,'kdfzdfvkdf@abv.bg','Aanasrika','Kartselska','Petrova','0895672323'),(20,'aksdkfkjn@abv.bg','Aanasrika','Kartselska','Petrova','0822922173'),(20,'nnnsjsjsn@abv.bg','Aanasrika','Kartselska','Petrova','0897777777'),(20,'ab2222x22@abv.bg','Aanasrika','Kartselska','Petrova','0894443332'),(20,'hcnsjwm@abv.bg','Aanasrika','Kartselska','Petrova','0899997772'),(20,'wposdkkk@abv.bg','Aanasrika','Kartselska','Petrova','0894443331'),(20,'ab2228dd1632@abv.bg','Aanasrika','Kartselska','Petrova','8729463729'),(20,'abkdfkvnk@abv.bg','Aanasrika','Kartselska','Petrova','0593224455'),(20,'ab22dyfe122816@abv.bg','Aanasrika','Kartselska','Petrova','1113334441'),(20,'ab22122jhfjs@abv.bg','Aanasrika','Kartselska','Petrova','1018223221'),(20,'bbbbb@abv.bg','Aanasrika','Kartselska','Petrova','0888777999'),(20,'ssaaaannnn@abv.bg','Aanasrika','Kartselska','Petrova','0891231234'),(20,'abkskdmf@abv.bg','Aanasrika','Kartselska','Petrova','0897775551'),(20,'abeeee@abv.bg','Aanasrika','Kartselska','Petrova','0892323239'),(20,'ab32221222281216dd32@abv.bg','Aanasrika','Kartselska','Petrova','0899999999'),(20,'a6b3722221632222v@abv.bg','Aanasrika','Kartselska','Petrova','0897777723'),(20,'222v@abv.bg','Aanasrika','Kartselska','Petrova','3332922173'),(20,'an2111ikas1sje9311@abv.bg','Aanasrika','Kartselska','Petrova','0894555555'),(20,'an211311@abv.bg','Anna-maria','Kartselska','Petrova','0894445558');

INSERT INTO `reservation` (reservation_date,reservation_status,from_id, passenger_id,  seat_id, to_id) VALUES
                                                                                                           ('2022-06-17 13:51:24.310583','DELETED',3,3,5,5),
                                                                                                           ('2022-06-17 13:53:11.288558','NEW',3,5,6,5),
                                                                                                           ('2022-06-17 13:56:54.820961','CONFIRMED',3,6,6,5),
                                                                                                           ('2022-06-17 13:59:44.999802','CONFIRMED',5,7,1,3),
                                                                                                           ('2022-06-17 14:39:39.387282','NEW',3,9,20,5),
                                                                                                           ('2022-06-17 14:43:48.964794','NEW',3,1,20,5),
                                                                                                           ('2022-06-17 14:44:43.309677','NEW',3,1,21,5),
                                                                                                           ('2022-06-17 14:55:56.255209','NEW',3,3,1,5),
                                                                                                           ('2022-06-17 14:56:53.497904','NEW',3,4,2,5),
                                                                                                           ('2022-06-17 14:58:31.335656','NEW',3,5,2,5),
                                                                                                           ('2022-06-17 15:24:58.738072','NEW',3,7,2,5),
                                                                                                           ('2022-06-17 15:25:39.252768','NEW',3,8,2,5),
                                                                                                           ('2022-06-17 15:50:09.549785','NEW',3,2,2,5),
                                                                                                           ('2022-06-20 12:15:37.368713','NEW',3,9,3,5),
                                                                                                           ('2022-06-20 12:16:14.114447','NEW',3,4,3,5),
                                                                                                           ('2022-06-20 12:23:49.056987','NEW',3,2,4,5),
                                                                                                           ('2022-06-20 12:30:57.581506','NEW',3,3,4,5),
                                                                                                           ('2022-06-20 12:31:46.611501','NEW',3,4,4,5),
                                                                                                           ('2022-06-20 12:35:30.020362','NEW',3,6,4,5),
                                                                                                           ('2022-06-20 12:35:54.545034','NEW',3,7,4,5),
                                                                                                           ('2022-06-20 12:41:20.624777','NEW',3,8,4,5),
                                                                                                           ('2022-06-20 12:44:06.490427','NEW',3,2,4,5),
                                                                                                           ('2022-06-20 12:48:46.954804','NEW',3,5,4,5),
                                                                                                           ('2022-06-20 12:58:43.187456','NEW',3,5,6,5),
                                                                                                           ('2022-06-21 12:29:27.999780','CONFIRMED',3,3,7,5),
                                                                                                           ('2022-06-21 14:35:40.113213','CONFIRMED',3,10,2,5),
                                                                                                           ('2022-06-17 13:59:44.999802','CONFIRMED',3,7,33,5);

INSERT INTO `ticket_number` VALUES ('A_00001'),('A_00002'),('A_00052'),('A_00152'),('A_00202'),('A_00252');

INSERT INTO `one_way_ticket` (go_to_reservation_id,ticket_number_id) VALUES (2,'A_00001'),(4,'A_00002'),(1,'A_00052'),(3,'A_00152'),(5,'A_00202'),(6,'A_00252');

INSERT INTO `double_way_ticket` (id,return_reservation_id) VALUES (1,1),(2,2),(3,3),(4,4);

INSERT INTO `ticket_seq` VALUES (351);











