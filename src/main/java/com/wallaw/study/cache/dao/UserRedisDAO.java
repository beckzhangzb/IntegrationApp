package com.wallaw.study.cache.dao;

import com.wallaw.study.cache.model.User;

public interface UserRedisDAO {
	public void saveUser(final User user);
	public User getUser(final long id);
}
