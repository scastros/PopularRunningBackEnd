package com.popular.running.service.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.popular.running.service.BaseService;

@SuppressWarnings("rawtypes")
public abstract class BaseServiceImpl extends HibernateDaoSupport implements BaseService {

	@Override
	public void shutdown() {
		getHibernateTemplate().getSessionFactory().openSession().createSQLQuery( "SHUTDOWN" ).executeUpdate();
	}
}
