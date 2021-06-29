package com.user.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.exception.UserNotFoundException;
import com.user.model.User;
import com.user.model.UserDetails;
import com.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api
@RequestMapping("/app/user")
public class HomeController {

	@Autowired
	UserService service;

	@Autowired
	public HomeController(UserService service) {
		this.service = service;
	}

	@GetMapping("/home")
	@ApiOperation(value = "welcome user")
	public String getMessage() {
		return "Spring Boot Rest for User MAnagement OPerations";
	}

	@PutMapping("/edit-user/{id}")
	@ApiOperation("value = add student")
	public ResponseEntity<String> addStudent(@RequestBody User usr) throws Exception {
		if (service.addUser(usr)) {
			if (!service.addUser(usr)) {
				throw new Exception();
			}
			return new ResponseEntity<String>("User is Added", HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("User is Added", HttpStatus.CREATED);
	}

	@DeleteMapping("/delete-user/{id}")
	@ApiOperation(value = "Delete User")
	public ResponseEntity<String> deleteUser(@RequestBody User usr) throws Exception {
		if (!service.deleteUser(usr)) {
			throw new Exception();
		}
		return new ResponseEntity<String>("User is Deleted", HttpStatus.OK);
	}

	@GetMapping("/retieve-user/{id}")
	@ApiOperation(value = "Get User")
	public ResponseEntity<List<User>> getAllUser() throws UserNotFoundException {

		return new ResponseEntity<List<User>>(service.getallUser(), HttpStatus.OK);

	}
	
//	@GetMapping("/retieve-user/")
//	@ApiOperation(value = "Get User")
//	public ResponseEntity<User> getById(@PathVariable Integer id) throws UserNotFoundException {
//
//		List<User> usr = service.getById(id);
//			if (usr.equals(null)){			
//				return new ResponseEntity<String>("The User was not found",HttpStatus.BAD_REQUEST); 
//			}
//		
//		return new ResponseEntity<User>(usr,HttpStatus.OK);
//		
//		//return new ResponseEntity<List<User>>(service.getallUser(), HttpStatus.OK);
//
//	}

	@PostMapping("/create-user")
	@ApiOperation(value = "Update User")
	public ResponseEntity<String> updateUser(@RequestBody User usr) throws Exception {
		if (!service.updateUser(usr)) {
			throw new Exception();
		}
		return new ResponseEntity<String>("User is Updated", HttpStatus.OK);
	}

}
