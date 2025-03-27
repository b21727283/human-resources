package com.mgumussoy.humanresourcesbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeDTO extends EmployeeDTO {
    private MultipartFile cv;
}