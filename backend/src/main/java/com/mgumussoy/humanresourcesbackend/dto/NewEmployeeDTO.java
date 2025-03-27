package com.mgumussoy.humanresourcesbackend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewEmployeeDTO extends EmployeeDTO {
    @NotNull(message = "CV cannot be null")
    private MultipartFile cv;
}