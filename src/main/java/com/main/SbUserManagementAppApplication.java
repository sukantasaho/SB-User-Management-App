package com.main;
import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import freemarker.template.TemplateException;

@SpringBootApplication
public class SbUserManagementAppApplication {

	public static void main(String[] args) throws IOException, TemplateException {
		SpringApplication.run(SbUserManagementAppApplication.class, args);
		
		//IUserManagementService service = ctx.getBean("userService", IUserManagementService.class);
		
		/*UserRequestAndResponseDTO dto = new UserRequestAndResponseDTO();
		 dto.setDob(LocalDate.now());
		 dto.setEmail("ssukanta520@gmail.com");
		 dto.setFullName("Sanjib Sahoo");
		 dto.setGender('M');
		 dto.setMobile(9939999333l);
		 dto.setSsn(125525l);
		boolean status = service.userRegister(dto);
		 System.out.println(status);*/
		
		
		/*LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmail("ssukanta520@gmail.com");
		loginDTO.setPassword("Sahoo@1995");
		String msg = service.userLogin(loginDTO);
		System.out.println(msg);*/
		
		/*ActivateAccountDTO dto = new ActivateAccountDTO();
		dto.setEmail("ssukanta520@gmail.com");
		dto.setTempPwd("TEMP6222084");
		dto.setNewPwd("Sahoo@1995");
		dto.setConfirmPwd("Sahoo@1995");
		String msg = service.activateAccountChangingPWD(dto);
		System.out.println(msg);*/
		
		/*String status = service.recoverPassword("ssukanta520@gmail.com");
		System.out.println(status);*/
		
	  //((ConfigurableApplicationContext) ctx).close();
	}

}
