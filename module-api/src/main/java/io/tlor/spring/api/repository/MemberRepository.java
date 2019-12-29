package io.tlor.spring.api.repository;

import io.tlor.spring.domain.model.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository  {

    @PersistenceContext
    EntityManager entityManager;

    public void save(Member member) {
        entityManager.persist(member);
    }

    public Member findOne(Long id) {
        return entityManager.find(Member.class, id);
    }

    public List<Member> findAll() {
        return entityManager.createQuery("SELECT m FROM Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return entityManager.createQuery(" SELECT m FROM io.tlor.spring.domain.model.Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
