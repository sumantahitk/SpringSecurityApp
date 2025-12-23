package com.SpringSecurityApp.SpringSecurityApp.dto;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDTO {
   private Long id;

    private String name;

   private  String email;

    private  Integer age;


   private String role;//ADMIN,USER

    private Double salary;

     private LocalDate dateOfJoining;

   private Boolean isActive;

//    public EmployeeDTO(){}
//    public EmployeeDTO(Long id,String name,String email,Integer age, LocalDate dateOfJoining,Boolean isActive) {
//        this.age = age;
//        this.dateOfJoining = dateOfJoining;
//        this.email = email;
//        this.id = id;
//        this.isActive = isActive;
//        this.name = name;
//    }
//
//    public Integer getAge() {
//        return age;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//
//    public LocalDate getDateOfJoining() {
//        return dateOfJoining;
//    }
//
//    public void setDateOfJoining(LocalDate dateOfJoining) {
//        this.dateOfJoining = dateOfJoining;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Boolean getActive() {
//        return isActive;
//    }
//
//    public void setActive(Boolean isActive) {
//        this.isActive = isActive;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
}
