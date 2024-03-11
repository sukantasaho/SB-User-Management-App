package com.main.util;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import com.main.dto.UserRequestAndResponseDTO;
import com.main.entity.UserMaster;
public class ConverterUtils {

	public static List<UserRequestAndResponseDTO>  convertDBOListToDTOList(List<UserMaster> dboList)
	{
		List<UserRequestAndResponseDTO> dtoList = null;
    	if(!dboList.isEmpty() && dboList!=null)
    	{ 
    		  dtoList = dboList.stream()
                    .map(dbo->convertDBOToDTO(dbo))
                    .collect(Collectors.toList()); 
    	}
    	return dtoList;
	}
	public static UserRequestAndResponseDTO convertDBOToDTO(UserMaster dbo)
	{
		UserRequestAndResponseDTO user = new UserRequestAndResponseDTO();
		BeanUtils.copyProperties(dbo, user);
		return user;
	}
	public static UserMaster convertDTOToDBO(UserRequestAndResponseDTO dto)
	{
		UserMaster user = new UserMaster();
		BeanUtils.copyProperties(dto, user);
		return user;
	}
	
}
