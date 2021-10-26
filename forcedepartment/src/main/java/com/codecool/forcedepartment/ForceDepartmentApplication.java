package com.codecool.forcedepartment;

import com.codecool.forcedepartment.dao.DatabaseManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class ForceDepartmentApplication {

	public static void main(String[] args) throws SQLException {

		SpringApplication.run(ForceDepartmentApplication.class, args);
		DatabaseManager databaseManager = new DatabaseManager();
		databaseManager.asd();

	}

}
