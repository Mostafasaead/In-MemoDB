package com.mondiamedia.memory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import org.hibernate.Session;

import com.mondiamedia.entity.EmployeeEntity;
import com.mondiamedia.util.HibernateUtil;

public class DataBase {
	static Session session;
	static boolean running = true;

	public static void initSession() {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
	}
	//

	public static void main(String[] args) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		while (running) {
			try {
				System.out.println("Plesae Enter Your Command or type quit to exit");
				Scanner in = new Scanner(System.in);
				String input = in.nextLine();
				String command = input.substring(input.indexOf(" ") + 1);
				String splittChar = "-";
				if (command.contains("‐")) {
					splittChar = "‐";
				}
				String[] params = command.split(splittChar);

				if (input.startsWith("add"))
					add(Integer.valueOf(params[0]), params[1], params[2], Double.valueOf(params[3]));
				else if (input.toLowerCase().startsWith("del"))
					del(Integer.valueOf(params[0]));
				else if (input.toLowerCase().startsWith("update"))
					update(Integer.valueOf(params[0]), params[1], params[2]);
				else if (input.toLowerCase().startsWith("print") && !input.contains("printall"))
					print(Integer.valueOf(params[0]));
				else if (input.toLowerCase().startsWith("printall"))
					printAll(params[0]);
				else if (input.toLowerCase().startsWith("quit"))
					quit();

			} catch (Exception e) {
				System.err.println("Wrong Input, Please Try again!");
			}
		}
	}

	public static void add(int id, String name, String designation, double salary) {
		EmployeeEntity emp = new EmployeeEntity();
		emp.setEmployeeId(id);
		emp.setName(name);
		emp.setDesignation(designation);
		emp.setSalary(salary);
		emp = (EmployeeEntity) session.merge(emp);
		session.save(emp);
		session.flush();
		System.out.println("Employee " + emp.getName() + " added successfully. Total no of employees = "
				+ session.createCriteria(EmployeeEntity.class).list().size());
	}

	public static void del(int id) {
		if (session.get(EmployeeEntity.class, id) != null) {
			EmployeeEntity emp = new EmployeeEntity();
			emp.setEmployeeId(id);
			emp = (EmployeeEntity) session.merge(emp);
			session.delete(emp);
			System.out.println("Employee " + id + " deleted successfully");
		} else {
			System.out.println("Employee " + id + " not found, Please enter valid id");
		}

	}

	public static void update(int id, String updatedRecord, String value) {
		if (session.load(EmployeeEntity.class, id) != null) {
			if (updatedRecord.trim().equalsIgnoreCase("name")) {
				EmployeeEntity updatedEmp = (EmployeeEntity) session.load(EmployeeEntity.class, id);
				updatedEmp.setName(value);
				updatedEmp = (EmployeeEntity) session.merge(updatedEmp);
				session.update(updatedEmp);
				System.out.println("Employee " + id + " updated successfully. Name: " + updatedEmp.getName()
						+ ",Designation: " + updatedEmp.getDesignation() + ",Salary: " + updatedEmp.getSalary());
			} else if (updatedRecord.trim().equalsIgnoreCase("designation")) {
				EmployeeEntity updatedEmp = (EmployeeEntity) session.load(EmployeeEntity.class, id);
				updatedEmp.setDesignation(value);
				updatedEmp = (EmployeeEntity) session.merge(updatedEmp);
				session.update(updatedEmp);
				System.out.println("Employee " + id + " updated successfully. Name: " + updatedEmp.getName()
						+ ",Designation: " + updatedEmp.getDesignation() + ",Salary: " + updatedEmp.getSalary());
			} else if (updatedRecord.trim().equalsIgnoreCase("salary")) {
				EmployeeEntity updatedEmp = (EmployeeEntity) session.load(EmployeeEntity.class, id);
				updatedEmp.setSalary(Double.valueOf(value));
				updatedEmp = (EmployeeEntity) session.merge(updatedEmp);
				session.update(updatedEmp);
				System.out.println("Employee " + id + " updated successfully. Name: " + updatedEmp.getName()
						+ ",Designation: " + updatedEmp.getDesignation() + ",Salary: " + updatedEmp.getSalary());
			}
		} else {
			System.out.println("Employee " + id + " not found, Please enter valid id");
		}
	}

	public static void print(int id) {
		if (session.load(EmployeeEntity.class, id) != null) {
			EmployeeEntity emp = (EmployeeEntity) session.load(EmployeeEntity.class, id);
			System.out.println(emp.getName() + ", Designation " + emp.getDesignation() + ", Salary " + emp.getSalary());
		} else {
			System.out.println("Employee " + id + " not found, Please enter valid id");
		}
	}

	public static void printAll(String sort) {
		Map empsMap = new TreeMap<>();
		List<EmployeeEntity> empList = session.createCriteria(EmployeeEntity.class).list();
		if (empList.size() > 0) {
			if (sort.equalsIgnoreCase("asc")) {

				for (EmployeeEntity emp : empList) {
					empsMap.put(emp.getName(), emp.getDesignation());
				}
				Set<String> keys = empsMap.keySet();
				for (String key : keys) {
					System.out.println(key + " : " + empsMap.get(key));
				}
			} else if (sort.equalsIgnoreCase("des")) {
				empsMap = new TreeMap<>(Collections.reverseOrder());
				for (EmployeeEntity emp : empList) {
					empsMap.put(emp.getName(), emp.getDesignation());
				}
				Set set = empsMap.entrySet();
				Iterator i = set.iterator();
				// Display elements
				while (i.hasNext()) {
					Map.Entry me = (Map.Entry) i.next();
					System.out.println(me.getKey() + " : " + me.getValue());
				}
			}
		} else {
			System.out.println("Sorry! The Employee List is Empty, Please add new Employee");
		}

	}

	public static void quit() {
		System.out.println("The Application will exit");
		running = false;
		HibernateUtil.shutdown();
		System.exit(0);

	}

	public static void help() {
		System.err.println("add <employee id>‐<employee name>-<designation>‐<monthly salary>");
		System.err.println("del <employee id>");
		System.err.println("update <employee id>‐<NAME/DESIG/SALARY><New Value>");
		System.err.println("print <employee id>");
		System.err.println("printall <ASC/DESC>");
		System.err.println("quit");
	}
}