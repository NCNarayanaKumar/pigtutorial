student_data =
    LOAD 'src/resources/data/input/split_input'
    USING PigStorage(',')
    AS (STUDENT_ID:chararray, STUDENT_NAME:chararray, SCORE:int, SUBJECT:chararray);

english_students = FILTER student_data BY (SUBJECT == 'English');
mathematics_students = FILTER student_data BY (SUBJECT == 'Mathematics');
statistics_students = FILTER student_data BY (SUBJECT == 'Statistics');
other_students = FILTER student_data BY 
	(NOT ((SUBJECT == 'English') OR (SUBJECT == 'Mathematics') OR (SUBJECT == 'Statistics')));	
    
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