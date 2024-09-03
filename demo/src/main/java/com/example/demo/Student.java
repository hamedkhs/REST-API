package com.example.demo;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Student")
public class Student {

    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    @Column(nullable = false, length = 30)
    @NotBlank(message = "The name cannot be empty. Please try again and enter your name correctly.")
    @NotNull(message = "null nabashad")
    @Size(min=5 , max=30 ,message = "Your name does not exist in the range of 5 to 30 characters, please edit it again.")
    @Pattern(regexp = "^[a-zA-Z\\s]+$" , message = "Your name can only contain alphabets.")
    private String name;

    @Column(unique = true, nullable  = false, length = 10)    
    @NotNull(message = "null nabashad")
    @NotBlank(message = "Your national code cannot be empty.")
    @Size(min=10 , max=10 , message = "Your national code must be exactly 10 digits.")
    @Pattern(regexp = "\\d+" , message = "Your national code can only contain numbers.")
    private String nationalCode;

    @Column(nullable = false)
    @NotNull(message = "Your age cannot be empty.")
    @Min(value=6 , message = "You have entered age less than 6 years, please correct it.")
    @Max(value = 25 , message = "You have entered age more than 25 years, please correct it.")
    @NotNull(message = "null nabashad")
    private Integer age;

    @Size(min=1 , max=20 ,message = "Please enter your occupation correctly between 1 and 20 characters.")
    @Pattern(regexp = "^[a-zA-Z\\s]+$" , message = "Your job can only contain alphabets.")
    @Column(length = 20)
    @NotNull(message = "null nabashad")
    private String job;

    @NotBlank(message = "Your phone cannot be empty.")
    @Size(min=11 , max=11 ,message = "The phone number must contain 11 digits.")
    @Pattern(regexp = "^09\\d{9}$" , message = "The phone number is not entered correctly. All characters must be numbers and start with (09..).")
    @Column(nullable = false, length = 11)
    @NotNull(message = "null nabashad")
    private String phone;

    @NotBlank(message = "Your field Of Class cannot be empty.")
    @Size(min=4 , max=20 ,message = "Your string name must be between 4 and 20 characters.")
    @Pattern(regexp = "^[a-zA-Z\\s]+$" , message = "Your field Of Class can only contain alphabets.")
    @Column(nullable = false, length = 20)
    @NotNull(message = "null nabashad")
    private String fieldOfClass;

    @NotNull(message = "Your class number cannot be empty.")
    @Min(value=101 , message = "Your class number must be at least 101.")
    @Max(value=102 , message = "Your class number must be at most 102.")
    @Column(nullable = false)
    @NotNull(message = "null nabashad")
    private Integer classNumber;
}