# swagger generated client

This module unpacks the openapi.json created in the web project and uses it to generate code for a client. 

The generated client uses the Jersey Client library and is usable code. 
However the model is also generated but doesn't directly match the model classes in the original project from which the openapi.json was created.

This generated code is packaged up in a zip file in the target directory.

You should treat the generated code as an example client with its own model and own tests.
It provides a pom.xml which builds and tests the sources. 
You will find however that the javdoc plugin in the pom.xml probably needs commented out because it doesn't generate javadoc correctly. 

Overall this package shows how you can generate clients to match youd design but work still ahd to be done to make generated code useful.