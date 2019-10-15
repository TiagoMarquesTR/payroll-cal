package br.com.tr.payrollcal.repository;

import java.util.TimeZone;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class EntityCacheRepository<T> implements DataCacheRepository<T> {

	private final RedisClient redisClient = RedisClient.create("redis://localhost:6379");
	private final StatefulRedisConnection<String, String> connection = redisClient.connect();
	private final RedisCommands<String, String> syncCommands = connection.sync();

	private static final ObjectMapper OBJECT_MAPPER;
	private static final TimeZone DEFAULT_TIMEZONE = TimeZone.getTimeZone("UTC");

	static {
		OBJECT_MAPPER = new ObjectMapper();
		OBJECT_MAPPER.setTimeZone(DEFAULT_TIMEZONE);
	}

	public boolean add(String collection, String hkey, T object) {
		try {
			String jsonObject = OBJECT_MAPPER.writeValueAsString(object);
			syncCommands.hset(collection, hkey, jsonObject);
			return true;
		} catch (Exception e) {
			System.out.printf("Unable to add object of key %s to cache collection '%s': %s", hkey, collection,
					e.getMessage());
			return false;
		}
	}

	public boolean delete(String collection, String hkey) {
		// TODO Auto-generated method stub
		return false;
	}

	public T find(String collection, String hkey, Class<T> tClass) {
		try {
			String jsonObject = String.valueOf(syncCommands.hget(collection, hkey));
			return OBJECT_MAPPER.readValue(jsonObject, tClass);
		} catch (Exception e) {
			if (e.getMessage() == null) {
				System.out.printf("Entry '%s' does not exist in cache", hkey);
			} else {
				System.out.printf("Unable to find entry '%s' in cache collection '%s': %s", hkey, collection,
						e.getMessage());
			}
			return null;
		}
	}

	public Boolean isAvailable() {
		// TODO Auto-generated method stub
		return null;
	}

}
