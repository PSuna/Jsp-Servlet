package com.yedam.emp.vo;

import lombok.Data;


//자동으로 getter와 setter을 생성해줌
//@Getter 
//@Setter
//@AllArgsConstructor // 생성자
//@ToString
//@NoArgsConstructor // 기본생성자
//@Data // equals , hash 등을 자동 오버라이딩해서 넣어줌

@Data 
public class EmpVO {

	private int employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String jobId;
	private String hireDate;
		
}
