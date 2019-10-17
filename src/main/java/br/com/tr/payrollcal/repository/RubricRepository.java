package br.com.tr.payrollcal.repository;

import java.util.List;
import java.util.UUID;

import br.com.tr.payrollcal.client.RubricClient;
import br.com.tr.payrollcal.model.Rubric;

public class RubricRepository {
	private final UUID clientId;
	private static final String RUBRIC_COLLECTION = "Rubrics";
	private final EntityCacheRepository<Rubric> rubricEntityCache;
	
	public RubricRepository(UUID clientId) {
		this.clientId = clientId;
		
		rubricEntityCache = new EntityCacheRepository<Rubric>();
		System.out.println("Total:");
		if(rubricEntityCache.count(this.getCollectionName()) == 0) {
			RubricClient rubricClient = new RubricClient();
		    List<Rubric> rubrics = rubricClient.getAll();
		    System.out.println("Obtive a lista de rubricas no PostgreSQL. Total: " + rubrics.size());
		    
		    for (Rubric rubric : rubrics) {
		    	rubricEntityCache.add(this.getCollectionName(), rubric.getName(), rubric);
			}
		}else {
			System.out.println("Obtive a lista de rubricas no Redis. Total:" + rubricEntityCache.count(this.getCollectionName()));
		}
	}
	
	public String getCollectionName() {
		return RUBRIC_COLLECTION + ":" + clientId;
	}
	
	public Rubric findByKey(String hkey) {
		Rubric rubric = rubricEntityCache.find(this.getCollectionName(), hkey, Rubric.class);
		
		if(rubric == null) {
			System.out.println("Rubrica nao encontrada! - " + hkey);
		}
		
		return rubric;
	}
}
