package com.user.service;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.dao.UserDao;
import com.user.exception.IncompleteDetailsException;
import com.user.exception.InvalidDateException;
import com.user.exception.InvalidRoleException;
import com.user.exception.UserExistsException;
import com.user.exception.UserNotFoundException;
import com.user.model.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao dao;
	
	
	
	public boolean addUser(User usr) throws UserExistsException {
		if(!((dao.findById(usr.getId())==null)))
		{
			throw new UserExistsException("USer Already Exists!!!!!!!!!!");
		}
		dao.save(usr);
		return true;
	}

	
	public boolean deleteUser(User usr) throws IncompleteDetailsException, InvalidRoleException, InvalidDateException, UserNotFoundException {
		if(!(dao.findById(usr.getId()).isPresent()))
		{
			throw new UserNotFoundException("User Doesn't Exists!!!!!!!!!!");
		}
		if(!(dao.findById(usr.getRolesId()).isPresent())) {
			throw new InvalidRoleException("User Role Invalid!!!!!!!");
		}
		
		if(usr.getCreationDate().equals(null)||usr.getUpdateDate().equals(null)) {
			throw new InvalidDateException("Please enter a date");
		}
		
		dao.delete(usr);
		return true;

	}

	
	public boolean updateUser(User usr)
			throws UserNotFoundException {
		// TODO Auto-generated method stub
		if(!(dao.findById(usr.getId()).isPresent()))
		{
			throw new UserNotFoundException("User Doesn't Exists!!!!!!!!!!");
		}
		User temp = dao.findById(usr.getId()).get();
		temp.setCreationDate(usr.getCreationDate());
		temp.setUpdateDate(usr.getUpdateDate());
		temp.setRolesId(usr.getRolesId());
		dao.save(temp);
		return true;	}

	
	public List<User> getallUser() throws UserNotFoundException {
		// TODO Auto-generated method stub
		return dao.findAll();	}


	public List<User> getById(User usr) throws UserNotFoundException {
		if(!(dao.findById(usr.getId()).isPresent()))
		{
			throw new UserNotFoundException("User Doesn't Exists!!!!!!!!!!");
		}
	
		return (List<User>) dao.findById(usr.getId()).get();
	}

}
