package com.techcareer.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techcareer.userservice.entity.User;
import com.techcareer.userservice.repository.UserRepository;
import com.techcareer.userservice.results.DataResult;
import com.techcareer.userservice.results.ErrorDataResult;
import com.techcareer.userservice.results.SuccessDataResult;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public DataResult<User> queryById(Long id)
	{
		User user = 	userRepository.findById(id)
	    		.orElseThrow(() -> new RuntimeException("ilgili id ye ait kayıt yok"));
	    return   new SuccessDataResult<User>(user)   ;   
	}

	 public DataResult<List<User>> tumKullanicilar() {
		 
		 return new DataResult<List<User>>(true, userRepository.findAll());
	 }

	
	 
	 public DataResult<User> register(User requestUser) {
		    User existingUser = userRepository.findByUsername(requestUser.getUsername());
		    
		    if (existingUser != null) {
		        return new ErrorDataResult<User>("Kullanıcı adı zaten kullanılıyor.") ; 
		    } else {
		        return new SuccessDataResult<User>( userRepository.save(requestUser))  ;  
		    }
		}
	 
	 public DataResult<User> login(String username,String password) {
		    User existingUser = userRepository.findByUsername(username);

		    if (existingUser != null) {
		        if (password.equals(existingUser.getPassword())) {
		        	return new SuccessDataResult<User>(existingUser)  ; 
		        } else {
		            return  new ErrorDataResult<User>("Şifre yanlış.") ; 
		        }
		    } else {
		        return   new ErrorDataResult<User>("Kullanıcı bulunamadı.") ; 
		    }
		}


	 
	 
	 

}