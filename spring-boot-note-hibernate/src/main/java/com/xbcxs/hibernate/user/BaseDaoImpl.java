package com.xbcxs.hibernate.user;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 持久层实现
 *
 * @author Xiao
 */
@Component
public class BaseDaoImpl {

    @PersistenceContext
    EntityManager entityManager;
}
