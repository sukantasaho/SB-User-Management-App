package com.main.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.main.dto.ActivateAccountDTO;
import com.main.dto.LoginDTO;
import com.main.dto.UserRequestAndResponseDTO;
import com.main.service.IUserManagementService;
import freemarker.template.TemplateException;

@RestController
@RequestMapping("/user")
public class UserManagementController {

	@Autowired
	private IUserManagementService service;
	
	@PostMapping("/saveUser")
	public ResponseEntity<Boolean> userRegister(@RequestBody UserRequestAndResponseDTO dto) throws IOException, TemplateException
	{
		 boolean status = service.userRegister(dto);
		return new ResponseEntity<Boolean>(status, HttpStatus.OK);
	}
	
	@PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody LoginDTO dto)
	{
		 String res = service.userLogin(dto);
		 return new ResponseEntity<String>(res, HttpStatus.OK);
	}
	@PostMapping("/activateAccount")
    public ResponseEntity<String>  activateAccountChangingPWD(@RequestBody ActivateAccountDTO dto)
    {
    	String res = service.activateAccountChangingPWD(dto);
    	return new ResponseEntity<String>(res, HttpStatus.OK);
    }
	@GetMapping("/recoverPassword")
    public ResponseEntity<String> recoverPassword(@RequestParam("email") String email) throws IOException, TemplateException
    {
    	 String res = service.recoverPassword(email);
    	 return new ResponseEntity<String>(res, HttpStatus.OK);
    }
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserRequestAndResponseDTO>> getAllUsers() 
	{
		List<UserRequestAndResponseDTO> resList = service.getAllUsers();
		return new ResponseEntity<List<UserRequestAndResponseDTO>>(resList, HttpStatus.OK);
	}
	@GetMapping("/getExistData")
	public ResponseEntity<UserRequestAndResponseDTO> edit(@RequestParam("userId") Integer userId)
	{
		UserRequestAndResponseDTO resDTO = service.edit(userId);
		return new ResponseEntity<UserRequestAndResponseDTO>(resDTO, HttpStatus.OK);
	}
	@DeleteMapping("/deleteById")
	public ResponseEntity<Boolean> deleteById(Integer userId)
	{
		boolean status = service.deleteById(userId);
		return new ResponseEntity<Boolean>(status, HttpStatus.OK);
	}
	@PutMapping("/softDelete")
	public ResponseEntity<Boolean> activeAndDeactiveUser(@RequestParam("userId") Integer userId, @RequestParam("status") Character status) 
	{
		boolean flag = service.activeAndDeactiveUser(userId, status);
		return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
	}
}
