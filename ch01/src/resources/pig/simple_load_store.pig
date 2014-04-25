data =
    LOAD 'src/resources/data/input/simple_load_store_input'
    AS (query:CHARARRAY);
    
STORE 
	data 
	INTO 'src/resources/data/actual_output/simple_load_store_output' 
	USING PigStorage(',');