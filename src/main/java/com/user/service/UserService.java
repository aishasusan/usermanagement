package com.user.service;

import java.util.List;





import com.user.exception.IncompleteDetailsException;
import com.user.exception.InvalidDateException;
import com.user.exception.InvalidRoleException;
import com.user.exception.UserExistsException;
import com.user.exception.UserNotFoundException;
import com.user.model.User;
import com.user.model.UserDetails;

public interface UserService  {

	public boolean addUser(User usr) throws UserExistsException;
	public boolean deleteUser(User usr) throws IncompleteDetailsException,InvalidRoleException,InvalidDateException,  UserNotFoundException;
	public boolean updateUser(User usr) throws UserNotFoundException;
	public List<User> getallUser() throws UserNotFoundException;
	public List<User> getById(User usr)throws UserNotFoundException;
	
	
}
