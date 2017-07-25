package test;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.DBAdmin;
import model.Admin;
import model.Gallery;

public class Test04GetGalleries {

	DBAdmin dbAdmin;

	@Before
	public void init(){
		dbAdmin = new DBAdmin(); 	
		dbAdmin.connect();
		dbAdmin.deleteAll(Admin.class);
		dbAdmin.deleteAll(Gallery.class);
		dbAdmin.close();
	}
	
	@Test
	public void GetGalleries(){
		
		DBAdmin dbAdmin = new DBAdmin();
		Gallery gallery1 = new Gallery();
		Gallery gallery2 = new Gallery();
		
		gallery1.setName("Galeria 1");
		gallery1.setDescription("Descripcion Galeria 1");
		
		gallery2.setName("Galeria 2");
		gallery2.setDescription("Descripcion Galeria 2");

		Admin admin = new Admin();
		
		admin.setName("Admin1");
			
		dbAdmin.connect();
		dbAdmin.getEntityManager().getTransaction().begin();
		dbAdmin.getEntityManager().persist(admin); 
		
		gallery1.setAdmin(admin);
		gallery2.setAdmin(admin);
		
		admin.getGalleries().add(gallery1);
		admin.getGalleries().add(gallery2);
			
		dbAdmin.getEntityManager().getTransaction().commit();
		dbAdmin.close();
	
		
		dbAdmin.connect();
 			Admin edited = dbAdmin.find(Admin.class, admin.getId());
 		dbAdmin.close();
 		
		HashSet<Gallery> lista = dbAdmin.getGalleries(edited.getId());
		

	    Assert.assertEquals(2,lista.size());
	    
	   

	}

}
