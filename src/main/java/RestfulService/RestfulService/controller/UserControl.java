package RestfulService.RestfulService.controller;


import RestfulService.RestfulService.models.userDetails;
import RestfulService.RestfulService.models.userRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController //Annotation to make this class a rest controller
//binds the class to "http://localhost:8080/users"
@RequestMapping("users") // http://localhost:8080/users
public class UserControl {

    //GetMapping binds the code to the webpage
    @GetMapping()
    public String getUsers(
            //These are the parameters for the GET request.
            //There are two GET methods, one fetches N users where as the other fetches one record of desired user
            @RequestParam(value="page", defaultValue = "1") int page,
            @RequestParam(value="limit", defaultValue = "50") int limit,
            @RequestParam(value="sort", defaultValue = "desc", required = false) String sort)
    {
        return "getusers() was called with page = " + page + " and limit = " + limit + " and sort = " + sort;
    }

    @GetMapping(
            //Binds this function to "http://localhost:8080/users/USERID"
            path="/{userId}"
            , produces = {
            MediaType.APPLICATION_ATOM_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE}
                )
        //Produces defines the allowed return types by the GET function
        //PathVariable is used to fetch the parameter from the URL
    public ResponseEntity<userRest> getUser(@PathVariable String userId){
        userRest user = new userRest();
        user.setUserId(userId);
        user.setName("Manan");
        //We can return the details of the object as well as the HTTP status
        return new ResponseEntity<userRest>(user, HttpStatus.OK);
    }

    @PostMapping(
            consumes = {
                MediaType.APPLICATION_ATOM_XML_VALUE,
                MediaType.APPLICATION_JSON_VALUE
            },
            produces = {
                MediaType.APPLICATION_ATOM_XML_VALUE,
                MediaType.APPLICATION_JSON_VALUE
            }
    ) //used to submit data
    //@Valid annotation can be used before @RequestBody to enforce some constraints on the input
    //such as NotNull, size constraints, email etc
    public ResponseEntity<userRest> createUser(@RequestBody userDetails userDet){
        userRest user = new userRest();
        user.setUserId(userDet.getUserId());
        user.setName(userDet.getName());
        //We can return the details of the object as well as the HTTP status
        return new ResponseEntity<userRest>(user, HttpStatus.OK);
    }

    @PutMapping //used to update data
    public String updateUser(){
        return("updateUser() was called");
    }

    @DeleteMapping //used to delete data
    public String deleteUser(){
        return("deleteUser() was called");
    }

}
