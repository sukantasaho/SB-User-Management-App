package com.main.service;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import com.main.dto.ActivateAccountDTO;
import com.main.dto.LoginDTO;
import com.main.dto.UserRequestAndResponseDTO;
import com.main.entity.UserMaster;
import com.main.mail.UserMailSystem;
import com.main.repository.IUserMasterRepository;
import com.main.util.ConverterUtils;
import com.main.util.PasswordEncoderUtil;
import com.main.util.PasswordGeneratorUtil;
import freemarker.template.TemplateException;
import jakarta.transaction.Transactional;

@Service("userService")
public class UserManagementService implements IUserManagementService {

	@Autowired
	private IUserMasterRepository repo;
	
	@Autowired
	private UserMailSystem mail;
	
	Logger logger = LoggerFactory.getLogger(UserManagementService.class);
	
	@Override
	public String userRegister(UserRequestAndResponseDTO dto) throws IOException, TemplateException {
		 
		String status = null;
		UserMaster userMaster1 = null;
		if(dto!=null)
		{
			
			try
			{
				UserMaster existOrNotEmail = repo.findByEmail(dto.getEmail());
				if(existOrNotEmail == null)
				{
					String tempPwd = PasswordGeneratorUtil.generateRandomPassword();
					System.out.println(tempPwd);
					 String encodedPassword = PasswordEncoderUtil.encoder(tempPwd);
					 //System.out.println(tempPwd);
					 System.out.println(encodedPassword);
					 UserMaster userMaster = ConverterUtils.convertDTOToDBO(dto);
					 userMaster.setPassword(encodedPassword);
					 userMaster.setCreatedBy(dto.getFullName());
					 userMaster.setModifiedBy(dto.getFullName());
					 userMaster1 = repo.save(userMaster);
					 status = "User Registration Success";
					 String fileName = "USER-REG-EMAIL-BODY.txt";
					 String body = readUserEmailBody(userMaster1.getFullName(),tempPwd, fileName);
					 String subject = "User Temporary Password Activation";
			         mail.userRegisterEmail(subject, userMaster1.getEmail(), body);
				}
				else
				{
					status = "Email Is Already Existed Try Register With Another Email";
				}
			}
			catch (Exception e) {
				 logger.error("Exception Occurred", e);
			}
		}
		
		return status;
	}
	private String readUserEmailBody(String fullName, String pwd, String fileName) throws IOException
	{
		
		String url = "https://www.w3school.com";
		String mailBody = null;
		Resource resource = null;
		File file = null;
		FileReader fr = null;
		BufferedReader br = null;
		try
		{
			resource = new ClassPathResource(fileName);
			file = resource.getFile();
			fr =new FileReader(file);
			br = new BufferedReader(fr);
			StringBuffer buffer = new StringBuffer();
			String line = br.readLine();
			while(line!=null)
			{
				buffer.append(line);
				line = br.readLine();
			}
			mailBody = buffer.toString();
			mailBody = mailBody.replace("{FULLNAME}", fullName);
			mailBody = mailBody.replace("{PWD}", pwd);
			mailBody = mailBody.replace("{URL}", url);
		}
		catch (Exception e) {
			logger.error("Exception Occurred", e);
		}
		finally
		{
			fr.close();
			br.close();
		}
		return mailBody;
	}

	@Override
	public String userLogin(LoginDTO dto) 
	{
		 
		String status = null;
		try
		{
			if(dto!=null)
			{
				     UserMaster user = repo.findByEmail(dto.getEmail());
				     
				  if(user!=null)
				  {
					  String pwd = PasswordEncoderUtil.decoder(user.getPassword());
						if(user.getEmail().equals(dto.getEmail()) && pwd.equals(dto.getPassword()))
						{
							if(user.getRecordStatus()!='D')
							{
								status = "Login Successfully Completed";
							}
							else
							{
									status = "User Account is In-Active! Please Activate Account By Reset Password";
							}
						}
				        else 
				        {
					       status = "Invalid Credential";
				        }
				  }
				  else
				  {
					  status = "User Not Found";
				  }
			}
			else
			{
				status = "User Email And Password Should Not Be Empty Or Null";
			}
		}
		catch (Exception e) {
			logger.error("Exception Occurred", e);
		}
		
		
		/*if(dto.getEmail()!=null && dto.getPassword()!=null)
		{
			UserMaster user = new UserMaster();
			user.setEmail(dto.getEmail());
			user.setPassword(dto.getPassword());
			
			Example<UserMaster> example = Example.of(user);
			List<UserMaster> findAll = repo.findAll(example);
			if(findAll.isEmpty())
			{
				status = "Invalid Credential";
			}
			else
			{
			   UserMaster userMaster =	findAll.get(0);
			   if(userMaster.getRecordStatus()=='A')
			   {
				   status = "Login Successfully Completed";
			   }
			   else
			   {
				   status = "User Account is In-Active! Please Activate Account By Reset Password";
			   }
			}
		}*/
		
		return status;
	}

	@Override
	@Transactional
	public String activateAccountChangingPWD(ActivateAccountDTO dto) 
	{
		String status = null;
		try
		{
			if(dto!=null)
			{
			    UserMaster user = repo.findByEmail(dto.getEmail());
			    if(user!=null)
				{
			    	if(user.getRecordStatus()!='A')
			    	{
			    		 String pwd = PasswordEncoderUtil.decoder(user.getPassword());
			    		 System.out.println(pwd);
						 if(user.getEmail().equals(dto.getEmail()) && pwd.equals(dto.getTempPwd()))
						 {
							  if(dto.getNewPwd().equals(dto.getConfirmPwd()))
							  {
								  String newPassword = PasswordEncoderUtil.encoder(dto.getNewPwd());
								  repo.updatePasswordAndActivateAccount(newPassword, dto.getEmail());
								  status = "User Account Activated And Password Updated";
							  }
							  else
							  {
									  status = "New Password And Confirm Password Not Matched";
							  }
						 }
						 else
						 {
							 status = "Invalid Email Or Temp Password Entry";
						 }
			    	}
			    	else
			    	{
			    		status = "Your Account Already Is Activated And You can Login";
			    	}
			    	
				}
				else
				{
				   status = "User Account Not Found";
				}
			}
			else
			{
				status = "User Email And Temp Password is Mandatory";
			}
		}
		catch (Exception e) {
			logger.error("Exception Occurred", e);
		}
		
		  return status;
	}

	@Override
	public String recoverPassword(String email) throws IOException, TemplateException 
	{
		String status = null;
		try
		{
			if(email!=null && !email.equals(""))
			 {
				  UserMaster user = repo.findByEmail(email);
				  if(user!=null)
				  {
					  if(user.getRecordStatus() == 'A')
					  {
						     String recoverPassword = PasswordEncoderUtil.decoder(user.getPassword());
							 String fileName = "RECOVERY-EMAIL-BODY.txt";
							 String body = readUserRecoveryEmailBody(user.getFullName(), recoverPassword,fileName);
							 String subject = "Recovery Password Sent Successfully";
							 boolean mailStatus = mail.userRecoverEmailSending(subject, user.getEmail(), body);
							 if(mailStatus)
							   status = "Recovery Password Mail Sent Successfully Completed";
							 else
							   status = "Mail Send Faulure";
					  }
					  else
					  {
						  status = "User Account is In-Active! Please Activate Account";
					  }
				  }
				  else
				  {
					  status = "Account Is Not Found Of This Email";
				  }
			 }
			 else
			 {
				 status = "Email Should Not Be Empty";
			 }
		}
		catch (Exception e) {
			logger.error("Exception Occurred", e);
		}
		return status;
	}
	private String readUserRecoveryEmailBody(String fullName, String pwd, String fileName) throws IOException
	{
		
		String mailBody = null;
		Resource resource = null;
		File file = null;
		FileReader fr = null;
		BufferedReader br = null;
		try
		{
			 resource = new ClassPathResource(fileName);
			 file = resource.getFile();
			 fr = new FileReader(file);
			 br = new BufferedReader(fr);
			 StringBuffer buffer = new StringBuffer();
			 String line = br.readLine();
			while(line!=null)
			{
				buffer.append(line);
				line = br.readLine();
			}
			 
			mailBody = buffer.toString();
			mailBody = mailBody.replace("{FULLNAME}", fullName);
			mailBody = mailBody.replace("{PWD}", pwd);
		}
		catch (Exception e) {
			logger.error("Exception Occurred", e);
		}
		finally
		{
			 fr.close();
			 br.close();
		}
		return mailBody;
	}

	@Override
	public List<UserRequestAndResponseDTO> getAllUsers() 
	{
		List<UserRequestAndResponseDTO> resList = null;
		try
		{
			 List<UserMaster> userList =  repo.findAll();
			 resList = ConverterUtils.convertDBOListToDTOList(userList);
		}
		catch (Exception e) {
			logger.error("Exception Occurred", e);
		} 
		return resList;
	}

	@Override
	public UserRequestAndResponseDTO edit(Integer userId) {
		UserRequestAndResponseDTO resDTO = null;
		try
		{
			Optional<UserMaster> user = repo.findById(userId);
			 if(user.isPresent())
			 {
				 resDTO = ConverterUtils.convertDBOToDTO(user.get());
			 }
		}
		catch (Exception e) {
			logger.error("Exception Occurred", e);
		}
		 
		return resDTO;
	}

	@Override
	public boolean deleteById(Integer userId) {
		boolean status = false;
		 try
		 {
		     repo.deleteById(userId);
		     status = true;
		 }
		 catch (Exception e) {
			 logger.error("Exception Occurred", e);
		}
		return status;
	}

	@Override
	public boolean activeAndDeactiveUser(Integer userId, Character status) 
	{ 
		boolean flag = false;
		try
		{
			Optional<UserMaster> user = repo.findById(userId);
			if(user.isPresent())
			{
				UserMaster user1 = user.get(); 
				user1.setRecordStatus(status);
				repo.save(user1);
				flag = true;
			}
		}
		catch (Exception e) {
			logger.error("Exception Occurred", e);
		}
		return flag;
	}

}
