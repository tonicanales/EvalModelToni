package dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.management.RuntimeErrorException;

import model.Admin;
import model.Comment;
import model.Gallery;
import model.Item;



public class DBAdmin extends DBManager implements AdminServices{

	@Override
	public void createAdmin(Admin admin) {
		
		connect();
		
			entitymanager.getTransaction().begin();
			
			
				entitymanager.persist(admin);
			
			
			entitymanager.getTransaction().commit();
		close();
		
	}

	@Override
	public void removeAdmin(Admin admin) {

	connect();
		entitymanager.getTransaction().begin();
			Admin toDelete = getEntityManager().find(Admin.class, admin.getId());
			entitymanager.remove(toDelete);
		entitymanager.getTransaction().commit();
	close();
	}

	@Override
	public void updateAdmin(Admin admin) {
	
	connect();
		
		entitymanager.getTransaction().begin();
		
			Admin adminUpdate = getEntityManager().find(Admin.class, admin.getId());
			adminUpdate.setName(admin.getName());

			entitymanager.getTransaction().commit();
	
	close(); 		
	}

	@Override
	public HashSet<Gallery> getGalleries(int adminId) {
		
		HashSet<Gallery> galerias = new HashSet<Gallery>();
		
		connect();
		entitymanager.getTransaction().begin();
		Admin admin = getEntityManager().find(Admin.class,adminId);
		
		   galerias = new  HashSet<Gallery>(admin.getGalleries());  
		   
		entitymanager.getTransaction().commit();
		close(); 
		
		return galerias;
		
	}

	@Override
	public void createGallery(Admin admin, Gallery gallery) {

		if(gallery.getId()!=0)
			throw new RuntimeException("Error no puede tratar de insertar una galer�a con id diferente de cero");
		
			connect();
		
			entitymanager.getTransaction().begin();
			
			if(admin.getId()==0){
				entitymanager.persist(admin);
			} else {
				admin = entitymanager.find(Admin.class, admin.getId());
			}
			
			    
			if(gallery!=null) {
				admin.getGalleries().add(gallery);
				gallery.setAdmin(admin);

			}
			
		entitymanager.getTransaction().commit();
	close();
	}

	
	
	@Override
	public void removeGallery(Gallery gallery) {
		connect();
		entitymanager.getTransaction().begin();
			Admin admin = entitymanager.find(Admin.class, gallery.getAdmin().getId());
			Gallery toDelete = getEntityManager().find(Gallery.class, gallery.getId());
			
			admin.getGalleries().remove(toDelete); 
			
			//entitymanager.remove(toDelete);
		entitymanager.getTransaction().commit();
	close();		
	}

	
	
	@Override
	public void update(Gallery gallery) {
		
	connect();
		
		entitymanager.getTransaction().begin();
		
			Gallery adminUpdate = getEntityManager().find(Gallery.class, gallery.getId());
			adminUpdate.setName(gallery.getName());
			adminUpdate.setDescription(gallery.getDescription());

		entitymanager.getTransaction().commit();
	
	close(); 		
	}

	
	
	
	
	@Override
	public Set<Item> getItems(int galleryId) {
		
		HashSet<Item> items = new HashSet<Item>();
		
		connect();

			/*ArrayList<Item> listaItems = selectAll(Item.class);
			for (int i = 0; i<= listaItems.size()-1; i++){
				items.add(listaItems.get(i));
			}*/
		

		entitymanager.getTransaction().begin();
		 Gallery gallery = getEntityManager().find(Gallery.class,galleryId);
		
		  items = new  HashSet<Item>(gallery.getItems());  
		   
		entitymanager.getTransaction().commit();
		close(); 
			
		return items;		
		
	}

	@Override
	public void createItem(Gallery gallery, Item item) {
	
		if(gallery.getId()==0)
			throw new RuntimeException("Error no puede tratar de insertar un Item con id diferente de cero");
		
		if(item== null){
			throw new RuntimeException("El item no puede ser null"); 
		}
		
		if(item.getId()!=0){
			throw new RuntimeException("El item ya existe"); 
		}
			
		
		connect();
		
			entitymanager.getTransaction().begin();			
					gallery = entitymanager.find(Gallery.class, gallery.getId());
					gallery.getItems().add(item);
					item.setGallery(gallery); 
			entitymanager.getTransaction().commit();
		close();		
	}

	@Override
	public void removeItem(Item item) {
		// TODO Auto-generated method stub
		
	}

	//Creado para correr el test 
	public void updateItem(Item item) {	
		// TODO Auto-generated method stub
		
	}
	
	//Creado para correr el test 
	public Set<Comment> getComment(int id) { 
		// TODO Auto-generated method stub
		return null;
	}

}
