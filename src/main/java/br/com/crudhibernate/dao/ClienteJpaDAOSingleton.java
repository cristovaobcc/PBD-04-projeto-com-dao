/**
 * 
 */
package br.com.crudhibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.crudhibernate.entities.Cliente;

/**
 * 
 * DAO (Data Access Object) que fará a comunicação com o banco de dados, realizando as operações básicas (CRUD).
 * 
 *
 */
public class ClienteJpaDAOSingleton {

	private static ClienteJpaDAOSingleton instanceClienteJpaDAO;
	protected EntityManager entityManager; // Responsável por realizar as operações CRUD no DB.
	
	/**
	 * Devolve uma instancia de {@link ClienteJpaDAOSingleton}.
	 * @return {@link ClienteJpaDAOSingleton}
	 */
	public static ClienteJpaDAOSingleton getInstance() {
		if(instanceClienteJpaDAO == null) {
			instanceClienteJpaDAO = new ClienteJpaDAOSingleton();
		}
		
		return instanceClienteJpaDAO;
	}
	
	private ClienteJpaDAOSingleton() {
		entityManager = getEntityManager("crudHibernatePU");
	}

	/**
	 * Devolve um {@link EntityManager} associado a unidade de persistencia persistenceUnit
	 * passada como argumento
	 * @param persistenceUnit String
	 * @return {@link EntityManager}
	 */
	private EntityManager getEntityManager(String persistenceUnit) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
		if (entityManager == null) {
			entityManager = emf.createEntityManager();			
		}
		return entityManager; 
	}
	
	/**
	 * Devolve um objeto do tipo Cliente contido no DB de acordo com o id passado.
	 * @param id 
	 * @return Cliente
	 */
	public Cliente getById(final int id) {
		// TODO: dúvida: será que devolve null caso o cliente não exista?
		return entityManager.find(Cliente.class, id);
		
	}
	
	/**
	 * Devolve uma lista de todos os Clientes no DB.
	 * @return List<Cliente> 
	 */
	@SuppressWarnings("unchecked")
	public List<Cliente> findAll(){
		// Aqui temos um exemplo do uso de JPQL no método createQuery:
		return entityManager.createQuery("FROM " + Cliente.class.getName()).getResultList();
	}
	
	/**
	 * Persiste um cliente no DB.
	 * @param cliente Cliente
	 */
	public void persist(Cliente cliente) {
		try {
			
			entityManager.getTransaction().begin();
			entityManager.persist(cliente);
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	/**
	 * Atualiza o cliente passado no DB. Caso o registro não exista, ele é inserido no
	 * DB.
	 * @param cliente {@link Cliente}
	 */
	public void merge(Cliente cliente) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(cliente);
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	/**
	 * Remove o cliente passado da DB.
	 * @param cliente {@link Cliente}
	 */
	public void remove(Cliente cliente) {
		try {
			entityManager.getTransaction().begin();
			cliente = this.getById(cliente.getId());
			entityManager.remove(cliente);
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	/**
	 * Remove um cliente da DB com base no id passado.
	 * @param id int
	 */
	public void removeById(final int id) {
		try {
			Cliente cliente = getById(id);
			remove(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
