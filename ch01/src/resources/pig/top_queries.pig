data =
    LOAD 'src/resources/data/input/top_sites_input'
    AS (query:CHARARRAY);
     
queries_group =
    GROUP data
    BY query; 
    
queries_count = 
    FOREACH queries_group 
    GENERATE 
        group AS query, 
        COUNT(data) AS total;
        
queries_ordered =
    ORDER queries_count
    BY total DESC, query;
            
queries_limit =
    LIMIT queries_ordered $n;

STORE queries_limit INTO 'src/resources/data/actual_output/top_sites_output' USING PigStorage(',');