package com.mgumussoy.humanresourcesbackend.dto;

import com.mgumussoy.humanresourcesbackend.enums.MilitaryStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Surname cannot be blank")
    private String surname;

    @NotBlank(message = "Position cannot be blank")
    private String position;

    @NotNull(message = "Military status cannot be null")
    private MilitaryStatus militaryStatus;

    @NotBlank(message = "Notice period cannot be blank")
    private String noticePeriod;

    @NotBlank(message = "Phone cannot be blank")
    private String phoneNumber;

    @NotBlank(message = "Email cannot be blank")
    private String email;

}
