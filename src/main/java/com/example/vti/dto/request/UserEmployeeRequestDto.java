package com.example.vti.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEmployeeRequestDto {
    private String username;
    private String password;
    private String email;
    private Long roleId;  // Optional, but in your case, always "EMPLOYEE"
    
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String address;
    private BigDecimal salary;
    private Long departmentId;
    private Long positionId;
}
