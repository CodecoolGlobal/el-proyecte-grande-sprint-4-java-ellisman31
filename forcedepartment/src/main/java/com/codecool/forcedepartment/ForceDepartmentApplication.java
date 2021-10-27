package com.codecool.forcedepartment;

import com.codecool.forcedepartment.dao.DatabaseManager;
import com.codecool.forcedepartment.model.User;
import com.codecool.forcedepartment.model.Worker;
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
		//System.out.println(databaseManager.getAllWorkObject());
		//System.out.println(databaseManager.getWorkersByNamePart("ha")); // had to refactor for lower case - does not work for Haynes
		//System.out.println(databaseManager.getAllProfession());
		//databaseManager.registerRegularUser(new User("Adam", "Goldberg", "2021-09-21", "1976-06-07", false, "USER", "goldber.adam@gmail.com"), "1234"); //HASHED PASSWORD NEED TO BE ADDED AS SECOND ARGUMENT
		//databaseManager.registerWorker(13, "+36205753420", "Lorem ipsum vertigo");
		//((Worker) databaseManager.getAllDataAboutUser(2));
		//System.out.println(databaseManager.checkIfEmailInUse("price.chase@gmail.com"));
		//System.out.println(databaseManager.checkValidLogin("price.chase@gmail.com", "1234"));
		//databaseManager.updateRegularUserData(1, "Aaron", "Rodgers", "1981-10-10", "aaron.rodgers@gmail.com", "NFL");
		//databaseManager.updateWorker(3, "Chester", "Lewis", "1997-11-10", "chester.lewis@gmail.com", "1234", "Ipsum lorem", "+36305754320", true);
		//System.out.println(databaseManager.getProfessionWithExperienceOfWorker(2));
	}
}
