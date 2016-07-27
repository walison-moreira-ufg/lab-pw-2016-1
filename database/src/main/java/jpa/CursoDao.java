package jpa;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class CursoDao {

  private static EntityManagerFactory emf =
      Persistence.createEntityManagerFactory("gestao-academica");
  private static EntityManager em;

	public static void inclui(String codigo, String nome) {
	  //Obter "conexão".
	  em = emf.createEntityManager();
	  
	  em.getTransaction().begin();
	  
	  Curso curso = new Curso(codigo, nome);

	  //Grava o objeto no banco de dados.
	  em.persist(curso);

	  em.getTransaction().commit();

	  em.close();
	}

	public static void alterar(String codigo, String nome) {
		em = emf.createEntityManager();
		em.getTransaction().begin();

		Curso curso = new Curso(codigo, nome);
		// Atualizando curso
		em.merge(curso);
		
		em.getTransaction().commit();
		em.close();
	}

	public static void excluir(String codigo) {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Curso curso = pesquisarCodigo(codigo);			
		
		em.remove(curso);
		
		em.getTransaction().commit();
		em.close();
	} 

	public static Curso pesquisarCodigo(String codigo) {
		return em.find(Curso.class, codigo);
	}

	public static List<Curso> listar() throws SQLException {
	    em = emf.createEntityManager();
	
	    String jpql = "from Curso";
	    TypedQuery<Curso> query =
	      em.createQuery(jpql, Curso.class);
	    List<Curso> result = query.getResultList();
	
	    em.close();
	    return result;
	}
}
