student_data =
    LOAD 'src/resources/data/input/filter_with_null_input'
    USING PigStorage(',')
    AS (STUDENT_ID:chararray, STUDENT_NAME:chararray, SCORE:int, SUBJECT:chararray);

filtered_data = 
	FILTER student_data
	BY (SCORE IS NULL);
    
STORE 
	filtered_data 
	INTO 'src/resources/data/actual_output/filter_with_null_output' 
	USING PigStorage(',');