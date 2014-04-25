data_alias =
    LOAD 'src/resources/data/input/simple_load_store_input'
    USING PigStorage(',')
    AS (STUDENT_ID:chararray, STUDENT_NAME:chararray, AGE:int);
    
STORE 
	data_alias 
	INTO 'src/resources/data/actual_output/simple_load_store_output' 
	USING PigStorage(',');