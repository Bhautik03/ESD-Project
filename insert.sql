-- Insert Domains
INSERT INTO domains (program, batch, capacity) VALUES
('MTech CSE', '2025-27', 150),
('MTech ECE', '2025-27', 100),
('MTech AIDS', '2025-27', 100);


-- Insert Courses
INSERT INTO Courses (name, course_code, capacity, credits, term, year) VALUES
('Advanced Algorithms', 'CSE511', 150, 4, 'Term I', '2025-26'),
('Machine Learning', 'AIT511', 50, 4, 'Term I', '2025-26'),
('Maths for Machine Learning', 'AIT512', 100, 4, 'Term I', '2025-26'),
('Digital Signal Processing', 'ECE501', 50, 4, 'Term I', '2025-26'),
('Deep Learning', 'AIT513', 50, 4, 'Term I', '2025-26'),
('Systems Software', 'CSE513-A', 150, 4, 'Term I', '2025-26'),
('Enterprise Software Development', 'CSE513-B', 150, 4, 'Term I', '2025-26');



-- Link Courses to Domains (Course_Domain)
INSERT INTO Course_Domain (course_id, domain_id) VALUES
(1, 1),  
(2, 3),  
(3, 3),  
(4, 2),  
(5, 3),  
(6, 1),  
(7, 1);


-- Insert Course Schedules
INSERT INTO course_schedule (course_id, day, time, room, building) VALUES
(1, 'Monday', '09:45:00', 'R203', 'Ramanujan Block'),
(1, 'Wednesday', '09:45:00', 'R203', 'Ramanujan Block'),
(2, 'Tuesday', '11:30:00', 'R203', 'Ramanujan Block'),
(2, 'Thursday', '11:30:00', 'R203', 'Ramanujan Block'),
(3, 'Friday', '14:00:00', 'R203', 'Ramanujan Block'),
(4, 'Monday', '11:30:00', 'R203', 'Ramanujan Block'),
(4, 'Wednesday', '11:30:00', 'R203', 'Ramanujan Block'),
(5, 'Tuesday', '14:00:00', 'R203', 'Ramanujan Block'),
(5, 'Thursday', '14:00:00', 'R203', 'Ramanujan Block'),
(6, 'Monday', '14:00:00', 'R203', 'Ramanujan Block'),
(6, 'Wednesday', '14:00:00', 'R203', 'Ramanujan Block'),
(7, 'Tuesday', '14:00:00', 'R203', 'Ramanujan Block'),
(7, 'Thursday', '14:00:00', 'R203', 'Ramanujan Block');


-- Insert Faculty/Employees
INSERT INTO Employees (first_name, last_name, email) VALUES
('Muralidhara V', 'N', 'murali@iiitb.ac.in'),
('Aswin', 'Kannan', 'aswin.kannan@iiitb.ac.in'),
('Sachit', 'Rao', 'sachit@iiitb.ac.in'),
('Raghavendra', 'Gowda', 'radhavendra.gowda@iiitb.ac.in'),
('Sachin', 'Kumar', 'sachin.kumar@iiitb.ac.in'),
('B', 'Thangaraju', 'b.thangaraju@iiitb.ac.in'),
('Chandrashekar', 'Ramanathan', 'rc@iiitb.ac.in');


-- Assign Faculty to Courses (Faculty_Courses)
INSERT INTO Faculty_Courses (course_id, faculty) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7);

-- Insert Students
INSERT INTO Students (roll_number, first_name, last_name, email) VALUES
('MT2025001', 'Aayank', 'Singhai', 'aayank.singhai@iiitb.ac.in'),
('MT2025002', 'Abhash', 'Tiwari', 'abhash.tiwari@iiitb.ac.in'),
('MT2025003', 'Abhijeet', 'Gupta', 'abhijeet.gupta@iiitb.ac.in'),
('MT2025004', 'Abhijeet', 'Rai', 'abhijeet.rai@iiitb.ac.in'),
('MT2025005', 'Abhinav', 'Sharma', 'abhinav.sharma@iiitb.ac.in'),
('MT2025006', 'Abhishek', 'Mandal', 'abhishek.mandal@iiitb.ac.in'),
('MT2025007', 'Abhishek', 'Prasanna', 'abhishek.prasanna@iiitb.ac.in'),
('MT2025008', 'Abhishek', 'Singh', 'abhishek.singh@iiitb.ac.in'),
('MT2025009', 'Abhishek', 'Ranjan', 'abhishek.ranjan@iiitb.ac.in'),
('MT2025010', 'Abhishek', 'Khond', 'abhishek.khond@iiitb.ac.in'),
('MT2025011', 'Adithi', 'P', 'adithi.p@iiitb.ac.in'),
('MT2025012', 'Adithya', 'Udayan', 'adithya.udayan@iiitb.ac.in'),
('MT2025013', 'Aditya', 'Dave', 'aditya.dave@iiitb.ac.in'),
('MT2025014', 'Aditya', 'Lahkar', 'aditya.lahkar@iiitb.ac.in'),
('MT2025015', 'Aditya', 'Pareek', 'aditya.pareek@iiitb.ac.in'),
('MT2025016', 'Shaikh', 'Ahmed', 'shaikh.ahmed@iiitb.ac.in'),
('MT2025017', 'Aieshah', 'Nasir', 'aieshah.nasir@iiitb.ac.in'),
('MT2025018', 'Aman', 'Tiwari', 'aman.tiwari@iiitb.ac.in'),
('MT2025019', 'Aniket', 'Kumar', 'aniket.kumar@iiitb.ac.in'),
('MT2025021', 'Ankit', 'Karwasra', 'ankit.karwasra@iiitb.ac.in'),
('MT2025022', 'Anuja', 'Jose', 'anuja.jose@iiitb.ac.in'),
('MT2025023', 'Apoorva', 'Kharya', 'apoorva.kharya@iiitb.ac.in'),
('MT2025024', 'Ashwin', 'Suthar', 'ashwin.suthar@iiitb.ac.in'),
('MT2025025', 'Asutosh', 'Panda', 'asutosh.panda@iiitb.ac.in'),
('MT2025026', 'Atharva', 'Pingale', 'atharva.pingale@iiitb.ac.in'),
('MT2025027', 'Athira', 'Kallukaran', 'athira.kallukaran@iiitb.ac.in'),
('MT2025028', 'Ayush', 'Kumar', 'ayush.kumar@iiitb.ac.in'),
('MT2025029', 'Bhautik', 'Vekariya', 'bhautik.vekariya@iiitb.ac.in'),
('MT2025030', 'Bhavy', 'Gupta', 'bhavy.gupta@iiitb.ac.in'),
('MT2025031', 'Chahak', 'Agarwal', 'chahak.agarwal@iiitb.ac.in'),
('MT2025032', 'Chaitanya', 'Nemade', 'chaitanya.nemade@iiitb.ac.in'),
('MT2025033', 'Chavala', 'Vishnu', 'lakshmi.vishnu@iiitb.ac.in'),
('MT2025035', 'Chintha', 'Reddy', 'varun.reddy@iiitb.ac.in'),
('MT2025036', 'Daksh', 'Minda', 'daksh.minda@iiitb.ac.in'),
('MT2025038', 'Deeksha', 'Jain', 'deeksha.jain@iiitb.ac.in'),
('MT2025039', 'Patel', 'Dharmendrakumar', 'deep.dharmendrakumar@iiitb.ac.in'),
('MT2025040', 'Yuvraj', 'Deshmukh', 'yuvraj.deshmukh@iiitb.ac.in'),
('MT2025041', 'Devanshi', 'Bavaria', 'devanshi.bavaria@iiitb.ac.in'),
('MT2025042', 'Devdeep', 'Sarkar', 'devdeep.sarkar@iiitb.ac.in'),
('MT2025043', 'Dharani', 'S', 'dharani.s@iiitb.ac.in'),
('MT2025044', 'Dholu', 'Mohanlal', 'vishnu.mohanlal@iiitb.ac.in'),
('MT2025045', 'Diksha', 'Gupta', 'diksha.gupta@iiitb.ac.in'),
('MT2025046', 'Garv', 'Bansal', 'garv.bansal@iiitb.ac.in'),
('MT2025047', 'Gaurav', 'Rajpurohit', 'gaurav.rajpurohit@iiitb.ac.in'),
('MT2025048', 'Guduru', 'Somanath', 'veera.somanath@iiitb.ac.in'),
('MT2025049', 'Gumpu', 'Sunil', 'sunil.gumpu@iiitb.ac.in'),
('MT2025050', 'Harshal', 'Rane', 'harshal.rane@iiitb.ac.in'),
('MT2025051', 'Isha', 'Kumari', 'isha.kumari@iiitb.ac.in'),
('MT2025052', 'Ishita', 'Kar', 'ishita.kar@iiitb.ac.in'),
('MT2025054', 'Jeevesh', 'Rai', 'jeevesh.rai@iiitb.ac.in'),
('MT2025055', 'Jenish', 'Vekariya', 'jenish.vekariya@iiitb.ac.in'),
('MT2025056', 'John', 'Jose', 'john.jose@iiitb.ac.in'),
('MT2025058', 'Kakadiya', 'Aman', 'aman.kakadiya@iiitb.ac.in'),
('MT2025059', 'Patel', 'Nitinbhai', 'kartavyakumar.patel@iiitb.ac.in'),
('MT2025060', 'Kartik', 'Ahluwalia', 'kartik.ahluwalia@iiitb.ac.in'),
('MT2025061', 'Kartikey', 'Dubey', 'kartikey.dubey@iiitb.ac.in'),
('MT2025062', 'Kartikey', 'Rana', 'kartikey.rana@iiitb.ac.in'),
('MT2025063', 'Dhameliya', 'Samitbhai', 'kashyap.dhameliya@iiitb.ac.in'),
('MT2025064', 'Kautilya', 'Singh', 'kautilya.singh@iiitb.ac.in'),
('MT2025065', 'Keyur', 'Padiya', 'keyur.padiya@iiitb.ac.in'),
('MT2025066', 'Kirti', 'Nigam', 'kirti.nigam@iiitb.ac.in'),
('MT2025067', 'Lanka', 'Reddy', 'lakshmisivani.reddy@iiitb.ac.in'),
('MT2025068', 'M', 'Vinay', 'vinay.m@iiitb.ac.in'),
('MT2025069', 'Mayankkumar', 'Satapara', 'mayankkumar.satapara@iiitb.ac.in'),
('MT2025070', 'Md', 'Khan', 'maroof.khan@iiitb.ac.in'),
('MT2025072', 'Mihir', 'Bindal', 'mihir.bindal@iiitb.ac.in'),
('MT2025073', 'Mohammed', 'Haris', 'haris.mohammed@iiitb.ac.in'),
('MT2025074', 'Mohit', 'Khapekar', 'mohit.khapekar@iiitb.ac.in'),
('MT2025075', 'Mohit', 'Kumar', 'mohit.kumar@iiitb.ac.in'),
('MT2025076', 'Nikhil', 'Garg', 'nikhil.garg@iiitb.ac.in'),
('MT2025077', 'Shah', 'Niragkumar', 'niragkumar.shah@iiitb.ac.in'),
('MT2025079', 'Nitheesh', 'Vemula', 'nitheesh.vemula@iiitb.ac.in'),
('MT2025081', 'Pankaj', 'Deopa', 'pankaj.deopa@iiitb.ac.in'),
('MT2025082', 'Parag', 'Katoch', 'parag.katoch@iiitb.ac.in'),
('MT2025083', 'Parag', 'Piprewar', 'parag.piprewar@iiitb.ac.in'),
('MT2025085', 'Parva', 'Parmar', 'parva.parmar@iiitb.ac.in'),
('MT2025086', 'Patel', 'Vinaykumar', 'jils.patel@iiitb.ac.in'),
('MT2025087', 'Piyush', '', 'piyush@iiitb.ac.in'),
('MT2025088', 'Piyush', 'Singh', 'piyush.singh@iiitb.ac.in'),
('MT2025089', 'Poojan', 'Pandya', 'poojan.pandya@iiitb.ac.in'),
('MT2025090', 'Prasanna', 'Kumar', 'prasanna.kumar@iiitb.ac.in'),
('MT2025091', 'Prashant', 'Sharma', 'prashant.sharma@iiitb.ac.in'),
('MT2025092', 'Prateek', 'Kumar', 'prateek.kumar@iiitb.ac.in'),
('MT2025093', 'Prateek', 'Yadav', 'prateek.yadav@iiitb.ac.in'),
('MT2025094', 'Preet', 'Chandrakar', 'preet.chandrakar@iiitb.ac.in'),
('MT2025095', 'Prins', 'Mishra', 'prins.mishra@iiitb.ac.in'),
('MT2025096', 'Pulkit', 'Goyal', 'pulkit.goyal@iiitb.ac.in'),
('MT2025098', 'Raghav', 'Goyal', 'raghav.goyal@iiitb.ac.in'),
('MT2025099', 'Rahul', 'Dubey', 'rahul.dubey@iiitb.ac.in'),
('MT2025100', 'Rahul', 'Raman', 'rahul.raman@iiitb.ac.in'),
('MT2025101', 'Rao', 'Shruti', 'shruti.mohankumar@iiitb.ac.in'),
('MT2025102', 'Rinku', 'Pareta', 'rinku.pareta@iiitb.ac.in'),
('MT2025103', 'Rishabh', 'Dewangan', 'rishabh.dewangan@iiitb.ac.in'),
('MT2025104', 'Rishu', 'Agrawal', 'rishu.agrawal@iiitb.ac.in'),
('MT2025105', 'Rohit', 'Bansal', 'rohit.bansal@iiitb.ac.in'),
('MT2025106', 'Rohitangshu', 'Bose', 'rohitangshu.bose@iiitb.ac.in'),
('MT2025107', 'Khatri', 'Rushil', 'rushil.khatri@iiitb.ac.in'),
('MT2025108', 'Ruturaj', 'Wairkar', 'ruturaj.wairkar@iiitb.ac.in'),
('MT2025110', 'Sameer', '', 'sameer@iiitb.ac.in'),
('MT2025111', 'Sandiri', 'Rohith', 'sandiri.rohith@iiitb.ac.in'),
('MT2025112', 'Sarthak', 'Goyal', 'sarthak.goyal@iiitb.ac.in'),
('MT2025113', 'Saurabh', 'Pandey', 'saurabh.pandey@iiitb.ac.in'),
('MT2025114', 'Shikhar', 'Mutta', 'shikhar.mutta@iiitb.ac.in'),
('MT2025115', 'Shivam', 'Singhal', 'shivam.singhal@iiitb.ac.in'),
('MT2025116', 'Shrey', 'Somani', 'shrey.somani@iiitb.ac.in'),
('MT2025117', 'Shreyas', 'Gangurde', 'shreyas.gangurde@iiitb.ac.in'),
('MT2025118', 'Shruti', 'Verma', 'shruti.verma@iiitb.ac.in'),
('MT2025121', 'Siddhesh', 'Abhang', 'siddhesh.abhang@iiitb.ac.in'),
('MT2025122', 'Siddhesh', 'Mahajan', 'siddhesh.mahajan@iiitb.ac.in'),
('MT2025124', 'Suchir', 'Okram', 'suchir.okram@iiitb.ac.in'),
('MT2025125', 'Swarnendu', 'Das', 'swarnendu.das@iiitb.ac.in'),
('MT2025126', 'Swayam', 'Palrecha', 'swayam.palrecha@iiitb.ac.in'),
('MT2025127', 'Udit', 'Tiwari', 'udit.tiwari@iiitb.ac.in'),
('MT2025128', 'Uttkarsh', 'Ranjan', 'uttkarsh.ranjan@iiitb.ac.in'),
('MT2025129', 'Vamsi', 'Ganagalla', 'vamsi.ganagalla@iiitb.ac.in'),
('MT2025130', 'Vinay', 'Bhandare', 'vinay.bhandare@iiitb.ac.in'),
('MT2025133', 'Vivek', 'Joshi', 'vivek.joshi@iiitb.ac.in'),
('MT2025135', 'Yash', 'Parande', 'yash.parande@iiitb.ac.in'),
('MT2025501', 'Abhiram', 'Valakkottil', 'abhiram.valakkottil@iiitb.ac.in'),
('MT2025502', 'Abhishek', 'Bhardwaj', 'abhishek.bhardwaj@iiitb.ac.in'),
('MT2025503', 'Allen', 'Justy', 'allen.justy@iiitb.ac.in'),
('MT2025504', 'Alphin', 'Joseph', 'alphin.joseph@iiitb.ac.in'),
('MT2025505', 'Aniket', 'Vats', 'aniket.vats@iiitb.ac.in'),
('MT2025506', 'Annapureddy', 'Venkata Harsha Vardhan Reddy', 'annapureddy.venkataharshavardhanreddy@iiitb.ac.in'),
('MT2025507', 'CHUNDI', 'SAI MANEESH', 'chundi.saimaneesh@iiitb.ac.in'),
('MT2025508', 'Farhan', 'Sami', 'farhan.sami@iiitb.ac.in'),
('MT2025509', 'Gudisa', 'Dinesh Reddy', 'gudisa.dineshreddy@iiitb.ac.in'),
('MT2025510', 'JASIM', 'J', 'jasim.j@iiitb.ac.in'),
('MT2025511', 'LEKKALA', 'SHRAVAN KUMAR', 'lekkala.shravankumar@iiitb.ac.in'),
('MT2025512', 'MADINENI', 'BHAVYA SREE', 'madineni.bhavyasree@iiitb.ac.in'),
('MT2025513', 'Manish', 'Pokhriyal', 'manish.pokhriyal@iiitb.ac.in'),
('MT2025515', 'Nemili', 'Uday Kumar', 'nemili.udaykumar@iiitb.ac.in'),
('MT2025516', 'P', 'Hanish Goud', 'p.hanishgoud@iiitb.ac.in'),
('MT2025517', 'Penugolu', 'Dhanya Sai', 'penugolu.dhanyasai@iiitb.ac.in'),
('MT2025518', 'PERNETI', 'DAMODARA PADMA SASIDHAR', 'perneti.damodarapadmasasidhar@iiitb.ac.in'),
('MT2025519', 'Rakshendra', 'Singh', 'rakshendra.singh@iiitb.ac.in'),
('MT2025520', 'S.', 'Magesh', 's.magesh@iiitb.ac.in'),
('MT2025521', 'SHAIK', 'VAZEER AHAMED', 'shaik.vazeerahamed@iiitb.ac.in'),
('MT2025522', 'Shashank', 'S', 'shashank.s@iiitb.ac.in'),
('MT2025523', 'SHIVARANJINI', 'T P', 'shivaranjini.tp@iiitb.ac.in'),
('MT2025524', 'Shreya', 'P Manchala', 'shreya.pmanchala@iiitb.ac.in'),
('MT2025525', 'Soumya', 'Singh', 'soumya.singh@iiitb.ac.in'),
('MT2025526', 'Sreelakshmi', 'K Suresh', 'sreelakshmi.ksuresh@iiitb.ac.in'),
('MT2025528', 'Swaroop', 'S Tadkal', 'swaroop.stadkal@iiitb.ac.in'),
('MT2025529', 'V', 'KUMAR', 'v.kumar@iiitb.ac.in'),
('MT2025530', 'Vipin', 'Kumar Mishra', 'vipin.kumarmishra@iiitb.ac.in'),
('mt2025531', 'Nitin', 'P', 'nitin.p@iiitb.ac.in'),
('MT2025701', 'Abhay', 'Aggarwal', 'abhay.aggarwal@iiitb.ac.in'),
('MT2025702', 'Abhay', 'Gotmare', 'abhay.gotmare@iiitb.ac.in'),
('MT2025703', 'Abutalha', 'D Maniyar', 'abutalha.dmaniyar@iiitb.ac.in'),
('MT2025704', 'Akshat', 'Kumar Tiwari', 'akshat.kumartiwari@iiitb.ac.in'),
('MT2025705', 'Dawood', 'aziz Zargar', 'dawood.azizzargar@iiitb.ac.in'),
('MT2025706', 'Harshal', 'Rudra', 'harshal.rudra@iiitb.ac.in'),
('MT2025707', 'Jishnu', 'Satwik Kancherlapalli', 'jishnu.satwikkancherlapalli@iiitb.ac.in'),
('MT2025708', 'Kanishk', 'Sah', 'kanishk.sah@iiitb.ac.in'),
('MT2025709', 'Karthik', 'S Kumar', 'karthik.skumar@iiitb.ac.in'),
('MT2025710', 'Koringa', 'Yash Mansukhbhai', 'koringa.yashmansukhbhai@iiitb.ac.in'),
('MT2025711', 'Lomesh', 'Soni', 'lomesh.soni@iiitb.ac.in'),
('MT2025712', 'MAHENDRANATH', 'GULLA', 'mahendranath.gulla@iiitb.ac.in'),
('MT2025713', 'Manne', 'Sai Venkat', 'manne.saivenkat@iiitb.ac.in'),
('MT2025714', 'Manoj', 'Paul', 'manoj.paul@iiitb.ac.in'),
('MT2025715', 'Manzil', 'Baruah', 'manzil.baruah@iiitb.ac.in'),
('MT2025716', 'Mir', 'Mehraan Azhar', 'mir.mehraanazhar@iiitb.ac.in'),
('MT2025717', 'Moturi', 'Sathvik Teja', 'moturi.sathvikteja@iiitb.ac.in'),
('MT2025718', 'Naitik', 'Bhavsar', 'naitik.bhavsar@iiitb.ac.in'),
('MT2025719', 'NALLA', 'VAMSI', 'nalla.vamsi@iiitb.ac.in'),
('MT2025720', 'Ramireddy', 'Venunath Reddy', 'ramireddy.venunathreddy@iiitb.ac.in'),
('MT2025721', 'Ritu', 'Raj Sharma', 'ritu.rajsharma@iiitb.ac.in'),
('MT2025722', 'Saatvik', 'Sinha', 'saatvik.sinha@iiitb.ac.in'),
('MT2025723', 'SAIKAT', 'PAL', 'saikat.pal@iiitb.ac.in'),
('MT2025724', 'Shreya', 'Gupta', 'shreya.gupta@iiitb.ac.in'),
('MT2025725', 'Shwetank', 'Singh', 'shwetank.singh@iiitb.ac.in'),
('MT2025726', 'Sunil', 'Joshi', 'sunil.joshi@iiitb.ac.in'),
('MT2025727', 'SURE', 'UDAY KIRAN', 'sure.udaykiran@iiitb.ac.in'),
('MT2025728', 'Surya', 'Satish Ganiga', 'surya.satishganiga@iiitb.ac.in'),
('MT2025729', 'Vansh', 'NiravKumar Doshi', 'vansh.niravkumardoshi@iiitb.ac.in'),
('MT2025730', 'Varun', 'Kolluru', 'varun.kolluru@iiitb.ac.in'),
('MT2025731', 'Vibhuti', 'Jain', 'vibhuti.jain@iiitb.ac.in'),
('MT2025732', 'Anirudh', 'Sharma', 'anirudh.sharma@iiitb.ac.in');


-- Enroll Students in Courses (Student_Courses)
INSERT INTO student_courses (student_id, course_id)
SELECT student_id, 1 FROM students WHERE student_id BETWEEN 1 AND 118; 

INSERT INTO student_courses (student_id, course_id)
SELECT student_id, 6 FROM students WHERE student_id BETWEEN 1 AND 118; 

INSERT INTO student_courses (student_id, course_id)
SELECT student_id, 7 FROM students WHERE student_id BETWEEN 1 AND 118; 

INSERT INTO student_courses (student_id, course_id)
SELECT student_id, 4 FROM students WHERE student_id BETWEEN 119 AND 147; 

INSERT INTO student_courses (student_id, course_id)
SELECT student_id, 2 FROM students WHERE student_id >= 148; 

INSERT INTO student_courses (student_id, course_id)
SELECT student_id, 3 FROM students WHERE student_id >= 148; 

INSERT INTO student_courses (student_id, course_id)
SELECT student_id, 5 FROM students WHERE student_id >= 148;
