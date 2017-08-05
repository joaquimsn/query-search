package com.github.joaquimsn.querysearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.h2.tools.RunScript;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class BaseJpaTest {
	protected static EntityManagerFactory entityManagerFactory;
	protected static EntityManager entityManager;
	
	@BeforeClass
	public static void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory("query-search-unit");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	@Before
	public void initializeDatabase() {
		Session session = entityManager.unwrap(Session.class);
		session.doWork(new Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
				try {
					File script = new File(getClass().getResource("/data.sql").getFile());
					RunScript.execute(connection, new FileReader(script));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					throw new RuntimeException("Database initialize script error");
				}
			}
		});
	}
	
	@AfterClass
	public static void clearDatabase() {
		entityManager.clear();
		entityManager.close();
		entityManagerFactory.close();
	}
}
