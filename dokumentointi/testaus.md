# Test document

## Unit testing

Unit test classes were made for very class. Some of the more complex ExpressionEvaluator tests were created by inputting the same (or almost the same expression) into WolframAlpha and then manually copying the result.


## Integration testing


## System testing

The user interface was tested manually, and mainly just trying to cause errors. 

Additionally, the jar-file was opened by left-clicking its icon and also through command line. 

This test revealed that, if no config.properties file is present, then left-clicking might result in the database being created somewhere else than the directory where the jar file is (probably the root directory). On the contrary, command line execution of the jar creates the database inside the same directory.

Thus, it is possible that the program might access to different databases depending how the program is run, IF the config.properties file and the proper configuration input (i.e. the name of desired database file) is not present.
