package com.codecool.forcedepartment;

import com.codecool.forcedepartment.dao.DatabaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class ForceDepartmentApplication implements CommandLineRunner {

	private DatabaseManager databaseManager;

	@Autowired
	public ForceDepartmentApplication(DatabaseManager databaseManager) {
		this.databaseManager = databaseManager;
	}

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(ForceDepartmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//System.out.println(databaseManager.getWorkersByRating());
		//System.out.println(databaseManager.getWorkersByProfession("Heating and Cooling System Mechanic"));
		//System.out.println(databaseManager.getWorkersByWorkObject("Terrace"));
	}
}
