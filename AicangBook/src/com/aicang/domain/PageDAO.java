package com.aicang.domain;

import java.util.List;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class PageDAO extends HibernateDaoSupport {

	/**
	 * 返回查询的结果
	 * @param hql 查询语句
	 * @param beginIndex 当前页开始的索引
	 * @param pageSize 页的大小，即查询的记录数量
	 * @return
	 */
	public List queryByPage(String hql, int beginIndex, int pageSize) {

		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		Transaction transaction = null;
		try {
			getHibernateTemplate().getSessionFactory();
			transaction = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setFirstResult(beginIndex);
			query.setMaxResults(pageSize);
			List list = query.list();
			transaction.commit();
			return list;

		} catch (Exception e) {
			if (null != transaction) {
				transaction.rollback();
				e.printStackTrace();
			}
		} finally{
			session.close();
		}
		return null;
	}

	/**
	 * 得到总的数量
	 * @param hql
	 * @return
	 */
	public int queryRowCount(String hql) {
		Session session = getHibernateTemplate().getSessionFactory()
				.openSession();
		Transaction transaction = null;
		try {
			getHibernateTemplate().getSessionFactory();
			transaction = session.beginTransaction();
			Query query = session.createQuery(hql);
			int result = query.list().size();
			transaction.commit();
			
			return result;

		} catch (Exception e) {
			if (null != transaction) {
				transaction.rollback();
				e.printStackTrace();
			}
		} finally{
			session.close();
		}
		return 0;
	}

}
