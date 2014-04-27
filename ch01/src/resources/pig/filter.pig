student_data =
    LOAD 'src/resources/data/input/filter_input'
    USING PigStorage(',')
    AS (STUDENT_ID:chararray, STUDENT_NAME:chararray, SCORE:int, SUBJECT:chararray);

filtered_data = 
	FILTER student_data
	BY ((STUDENT_ID == 'STUD0001'));
    
STORE 
	filtered_data 
	INTO 'src/resources/data/actual_output/filter_output' 
	USING PigStorage(',');