package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.DBAdmin;
import model.Admin;


public class Test01CreateAdmin {
	DBAdmin dbAdmin; 

	
	@Before
	public void init(){
		dbAdmin = new DBAdmin(); 	
		dbAdmin.connect();
		dbAdmin.deleteAll(Admin.class);
		dbAdmin.close();
	}
	
	//@Test
	public void testSbConection(){
		dbAdmin = new DBAdmin(); 	
		dbAdmin.connect();
		Assert.assertNotNull(dbAdmin.getEntityManager()); 		 	
		dbAdmin.close();
	}
	
	
	@Test
	public void TestCreateAdmin(){
		
		DBAdmin dbAdmin = new DBAdmin();

		Admin admin = new Admin();
		
		admin.setName("Admin1");
		
		dbAdmin.createAdmin(admin);
		
		dbAdmin.connect();
	    	Admin edited = dbAdmin.find(Admin.class, admin.getId());
	    dbAdmin.close();

	    Assert.assertEquals("Admin1",edited.getName());
		
	}

}
