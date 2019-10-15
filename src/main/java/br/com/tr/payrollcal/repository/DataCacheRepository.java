package br.com.tr.payrollcal.repository;

public interface DataCacheRepository<T> {
	public boolean add(String collection, String hkey, T object);
	public boolean delete(String collection, String hkey);
	public T find(String collection, String hkey, Class<T> tClass);
	public Boolean isAvailable();
}
