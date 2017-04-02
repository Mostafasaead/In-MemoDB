package com.mondiamedia.test;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import com.mondiamedia.entity.EmployeeEntity;
import com.mondiamedia.memory.DataBase;

public class TestDao {
	{
		DataBase.initSession();
	}
	@Test
	public void add() {
		EmployeeEntity emp=new EmployeeEntity();
		emp.setEmployeeId(1001);
		emp.setName("ali");
		emp.setDesignation("Senior Java Developer");
		emp.setSalary(14000.0);
		DataBase.add(emp.getEmployeeId(), emp.getName(), emp.getDesignation(), emp.getSalary());
	}
	@Test
	public void delete() {
		EmployeeEntity emp=new EmployeeEntity();
		emp.setEmployeeId(1001);
		DataBase.del(emp.getEmployeeId());
	}
	@Test
	public void update() {
		EmployeeEntity emp=new EmployeeEntity();
		emp.setEmployeeId(1001);
		emp.setName("ali");
		emp.setDesignation("Senior Java Developer");
		emp.setSalary(8000.0);
		DataBase.add(emp.getEmployeeId(), emp.getName(), emp.getDesignation(), emp.getSalary());
		EmployeeEntity emp1=new EmployeeEntity();
		emp1.setEmployeeId(1001);
		String record="salary";
		String salary="14000";
		DataBase.update(emp.getEmployeeId(),record, salary);
	}
	@Test
	public void print() {
		EmployeeEntity emp=new EmployeeEntity();
		emp.setEmployeeId(1001);
		DataBase.print(emp.getEmployeeId());
	}
	@Test
	public void printAll() {
		String sortType="ASC";
		DataBase.printAll(sortType);
	}
	@Test
	@Ignore
	public void quit() {
		DataBase.quit();
	}
}
