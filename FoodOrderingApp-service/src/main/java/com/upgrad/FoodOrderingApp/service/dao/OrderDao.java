package com.upgrad.FoodOrderingApp.service.dao;

import com.upgrad.FoodOrderingApp.service.entity.CouponEntity;
import com.upgrad.FoodOrderingApp.service.entity.OrderItemEntity;
import com.upgrad.FoodOrderingApp.service.entity.OrdersEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrderDao {

    @PersistenceContext
    private EntityManager entityManager;

    public CouponEntity getCouponByName(String couponName) {
        try {
            return entityManager.createNamedQuery("couponByName", CouponEntity.class).setParameter("couponName", couponName)
                    .getSingleResult();
        } catch(NoResultException nre) {
            return null;
        }
    }

    public List<OrdersEntity> getCustomerOrders(Long customerId) {
        try {
            return entityManager.createNamedQuery("ordersByCustomer", OrdersEntity.class).setParameter("customer", customerId)
                    .getResultList();
        } catch(NoResultException nre) {
            return null;
        }
    }

    public OrdersEntity saveOrder(OrdersEntity ordersEntity) {
        entityManager.persist(ordersEntity);
        return ordersEntity;
    }
}