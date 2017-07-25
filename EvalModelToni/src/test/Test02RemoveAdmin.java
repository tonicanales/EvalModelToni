package test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.DBAdmin;
import model.Admin;

public class Test02RemoveAdmin {
	DBAdmin dbAdmin;
	
	
	@Before
	public void init(){
		dbAdmin = new DBAdmin(); 	
		dbAdmin.connect();
		dbAdmin.deleteAll(Admin.class);
		dbAdmin.close();
	}
	
	@Test
	public void TestRemoveAdmin(){
		
		DBAdmin dbAdmin = new DBAdmin();

		Admin admin = new Admin();
		
		admin.setName("Admin1");
		
		dbAdmin.createAdmin(admin);
		
		dbAdmin.connect();
	    	Admin edited = dbAdmin.find(Admin.class, admin.getId());
	    dbAdmin.close();

	    Assert.assertEquals("Admin1",edited.getName());
	    
	    dbAdmin.removeAdmin(admin);
	    
	    dbAdmin.connect();
    		ArrayList<Admin> edited1 = dbAdmin.selectAll(Admin.class);
    	dbAdmin.close();	

    	Assert.assertEquals(0,edited1.size());

	}

}
