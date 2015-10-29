package com.aicang.domain;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * UserBasic entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.aicang.domain.UserBasic
 * @author MyEclipse Persistence Tools
 */
public class UserBasicDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(UserBasicDAO.class);
	// property constants
	public static final String USER_NAME = "userName";
	public static final String PASSWORD = "password";
	public static final String USER_TYPE = "userType";
	public static final String USER_QQ = "userQq";
	public static final String USER_PHONE = "userPhone";
	public static final String USER_EMAIL = "userEmail";
	public static final String USER_BIRTHDAY = "userBirthday";
	public static final String REMARK = "remark";

	protected void initDao() {
		// do nothing
	}

	public void save(UserBasic transientInstance) {
		log.debug("saving UserBasic instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UserBasic persistentInstance) {
		log.debug("deleting UserBasic instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserBasic findById(java.lang.Integer id) {
		log.debug("getting UserBasic instance with id: " + id);
		try {
			UserBasic instance = (UserBasic) getHibernateTemplate().get(
					"com.aicang.domain.UserBasic", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(UserBasic instance) {
		log.debug("finding UserBasic instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding UserBasic instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from UserBasic as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUserName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}

	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List findByUserType(Object userType) {
		return findByProperty(USER_TYPE, userType);
	}

	public List findByUserQq(Object userQq) {
		return findByProperty(USER_QQ, userQq);
	}

	public List findByUserPhone(Object userPhone) {
		return findByProperty(USER_PHONE, userPhone);
	}

	public List findByUserEmail(Object userEmail) {
		return findByProperty(USER_EMAIL, userEmail);
	}

	public List findByUserBirthday(Object userBirthday) {
		return findByProperty(USER_BIRTHDAY, userBirthday);
	}

	public List findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findAll() {
		log.debug("finding all UserBasic instances");
		try {
			String queryString = "from UserBasic";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UserBasic merge(UserBasic detachedInstance) {
		log.debug("merging UserBasic instance");
		try {
			UserBasic result = (UserBasic) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UserBasic instance) {
		log.debug("attaching dirty UserBasic instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserBasic instance) {
		log.debug("attaching clean UserBasic instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UserBasicDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UserBasicDAO) ctx.getBean("UserBasicDAO");
	}
}