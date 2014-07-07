student_data =
    LOAD 'src/resources/data/input/split_input'
    USING PigStorage(',')
    AS (STUDENT_ID:chararray, STUDENT_NAME:chararray, SCORE:int, SUBJECT:chararray);

SPLIT student_data INTO 
	english_students IF (SUBJECT == 'English'),
	mathematics_students IF (SUBJECT == 'Mathematics'),
	statistics_students IF (SUBJECT == 'Statistics'),
	other_students OTHERWISE;	
    
STORE 
	english_students 
	INTO 'src/resources/data/actual_output/english_students_output' 
	USING PigStorage(',');
	
STORE 
	mathematics_students 
	INTO 'src/resources/data/actual_output/mathematics_students_output' 
	USING PigStorage(',');
	
STORE 
	statistics_students 
	INTO 'src/resources/data/actual_output/statistics_students_output' 
	USING PigStorage(',');
	
STORE 
	other_students 
	INTO 'src/resources/data/actual_output/other_students_output' 
	USING PigStorage(',');