package jpabook.jpashop;

import hellojpa.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // 하나만 생성 애플리케이션 전체에서 공유
        EntityManager em = emf.createEntityManager(); // 요청에 따라 생성, 쓰레드간에 공유X

        // JPA 작업은 모두 트랜잭션 안에서 해야함.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //code
        try {
            System.out.println("japboo");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }
}