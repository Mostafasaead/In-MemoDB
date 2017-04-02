package com.mondiamedia.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;
 
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@Table(name = "Employee", uniqueConstraints = {@UniqueConstraint(columnNames = "ID")})
public class EmployeeEntity implements Serializable
{
   private static final long serialVersionUID = -1798070786993154676L;
   @Id
   @Column(name = "ID", unique = true, nullable = false)
   private Integer           employeeId;
   @Column(name = "Name", unique = false, nullable = true, length = 100)
   private String            name;
   @Column(name = "Designation", unique = false, nullable = true, length = 100)
   private String            designation;
   @Column(name = "Salary", unique = false, nullable = true, length = 100)
   private Double            salary;
 
   public Integer getEmployeeId()
   {
      return employeeId;
   }
 
   public void setEmployeeId(Integer employeeId)
   {
      this.employeeId = employeeId;
   }
 
   public String getName()
   {
      return name;
   }
 
   public void setName(String name)
   {
      this.name = name;
   }
 
   public String getDesignation()
   {
      return designation;
   }
 
   public void setDesignation(String designation)
   {
      this.designation = designation;
   }
 
   public Double getSalary()
   {
      return salary;
   }
 
   public void setSalary(Double salary)
   {
      this.salary = salary;
   }
}
