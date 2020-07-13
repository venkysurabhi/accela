Please git clone the project and run as a java application from an IDE or 
use the command 'mvn spring-boot:run' from the project's root directory.

Please use the below endpoints:

  '<localhost>/persons' to create(POST) a person or list(GET) all the available persons. Please use the same endpoint to get the number of persons
  too with request type as 'HEAD'.
  
  '<localhost>/persons/{id}' to get, update or delete a person.
  
  '<localhost>/persons/{personId}/addresses' to create and address and associate to a person.
  
  '<localhost>/persons/{personId}/addresses/{addressId}' to update and delete an address.
  
Points to remember:

 Please add 'id' to the json of person/address while updating or deleting it.
 I have added some basic validations like in an Address, postalcode should be between 10000 and 99999(5 digit zipcodes in US) and
 for a person, first name and last name are mandatory. 
 
JSON objects for your reference:

  Person JSON to create:
  
     {
	     "firstName": "test_first",
	     "lastName": "test_last"
     }
     
   Person JSON to UPDATE:
   
     {
	     "id": "<id_from GET_request>",
         "firstName": "test_first_updated",
	     "lastName": "test_last"
      }
      
   Address JSON:
   
    {
        "personId": "<person_id>",
	    "street": "test_street",
	    "city": "test_city",
	    "state": "test_state",
	    "postalCode": 77817
    }
    
    
Thanks for the opportunity.
