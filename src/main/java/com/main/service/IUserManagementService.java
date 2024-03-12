package com.main.service;
import java.io.IOException;
import java.util.List;
import com.main.dto.ActivateAccountDTO;
import com.main.dto.LoginDTO;
import com.main.dto.UserRequestAndResponseDTO;
import freemarker.template.TemplateException;

public interface IUserManagementService 
{
       public String userRegister(UserRequestAndResponseDTO dto) throws IOException, TemplateException;
       public String userLogin(LoginDTO dto);
       public String activateAccountChangingPWD(ActivateAccountDTO dto);
       public String recoverPassword(String email) throws IOException, TemplateException;
       public List<UserRequestAndResponseDTO> getAllUsers();
       public UserRequestAndResponseDTO edit(Integer userId);
       public boolean deleteById(Integer userId);
       public boolean activeAndDeactiveUser(Integer userId, Character status);
}
