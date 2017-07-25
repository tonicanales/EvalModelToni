package test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.DBAdmin;
import model.Admin;
import model.Gallery;

public class Test07UpdateGallery {
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
		Gallery gallery1 = new Gallery();
		Gallery gallery2 = new Gallery();
		
		gallery1.setName("Galeria 1");
		gallery1.setDescription("Descripcion Galeria 1");
		
		gallery2.setName("Galeria 2");
		gallery2.setDescription("Descripcion Galeria 2");
		
		
		Admin admin = new Admin();
		
		admin.setName("Admin1");
		
		gallery1.setAdmin(admin);
		gallery2.setAdmin(admin);
		admin.getGalleries().add(gallery1);
		admin.getGalleries().add(gallery2);

		dbAdmin.createAdmin(admin);
		
		dbAdmin.connect();
			Gallery edited = dbAdmin.find(Gallery.class, gallery2.getId());
		dbAdmin.close();
		
	    Assert.assertEquals("Galeria 2",edited.getName());
	    
	    gallery2.setName("Nuevo nombre");

		
		dbAdmin.update(gallery2);
		
		dbAdmin.connect();
			Gallery edited2 = dbAdmin.find(Gallery.class, gallery2.getId());
		dbAdmin.close();
	
		Assert.assertEquals("Nuevo nombre",edited2.getName());

	}


}
