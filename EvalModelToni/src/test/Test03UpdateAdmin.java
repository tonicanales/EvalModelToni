package test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.DBAdmin;
import model.Admin;

public class Test03UpdateAdmin {
	DBAdmin dbAdmin;

	@Before
	public void init(){
		dbAdmin = new DBAdmin(); 	
		dbAdmin.connect();
		dbAdmin.deleteAll(Admin.class);
		dbAdmin.close();
	}
	
	@Test
	public void UpdateAdmin(){
		
		DBAdmin dbAdmin = new DBAdmin();

		Admin admin = new Admin();
		
		admin.setName("Admin1");
		
		dbAdmin.createAdmin(admin);
		
		dbAdmin.connect();
	    	Admin nuevo = dbAdmin.find(Admin.class, admin.getId());
	    dbAdmin.close();

	    Assert.assertEquals("Admin1",nuevo.getName());
	    
	    admin.setName("Nuevo nombre");

	    dbAdmin.updateAdmin(admin);
	    
	    
	    dbAdmin.connect();
    		Admin edited = dbAdmin.find(Admin.class, admin.getId());
    	dbAdmin.close();	

    	Assert.assertEquals("Nuevo nombre",edited.getName());

	}


}
