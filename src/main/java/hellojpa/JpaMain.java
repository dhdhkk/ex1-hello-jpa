package hellojpa;

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
           /* Member member = new Member();

            member.setId(1L);
            member.setName("HelloA");*/
            /*Member member = em.find(Member.class, 1L);
            System.out.println("member = " + member.getId());
            System.out.println("member = " + member.getName());

            member.setName("HelloJPA");

            em.persist(member);*/

            // JPQL
            // - 검색을 할때도 테이블이 아닌 엔티티 객체를 대상으로 검색
            // - 모든 DB 데이터를 객체로 변환해서 검색하는 것은 불가능
            // - 애플리케이션이 필요한 데이터만 DB에서 불러오려면 결국 검색 조건이 포함된 SQL이 필요
            // - JPA는 SQL을 추상화한 <<<JPQL이라는 객체 지향 쿼리 언어 제공>>>
            // - SQL과 문법 유사, SELECT, FROM, WHERE, GROUP BY, HAVING, JOIN 지원
            // - SQL은 데이터베이스 테이블을 대상으로 쿼리
            // - JPQL은 객체 지향 SQL이다!!
            List<Member> member = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();

            for (Member member1 : member) {
                System.out.println("member1 = " + member1.getUsername());

            }
            tx.commit();
        }
        catch (Exception e) {
            tx.rollback();
        }
        finally {
            em.close();
        }

        emf.close();

    }
}


// JPA에서 가장 중요한 2가지
