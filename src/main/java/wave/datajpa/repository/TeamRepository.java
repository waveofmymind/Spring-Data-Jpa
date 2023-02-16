package wave.datajpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import wave.datajpa.entity.Member;
import wave.datajpa.entity.Team;

import java.util.List;
import java.util.Optional;

@Repository
public class TeamRepository {

    @PersistenceContext
    private EntityManager em;

    public Team saveTeam(Team team) {
        em.persist(team);
        return team;
    }


    public void delete(Team team) {
        em.remove(team);
    }

    public List<Team> findAll() {
        return em.createQuery("select t from Team t", Team.class)
                .getResultList();
    }

    public Optional<Team> findById(Long teamId) {
        Team team = em.find(Team.class,teamId);
        return Optional.ofNullable(team);
    }

    public Long count() {
        return em.createQuery("select count(t) from Team t", Long.class)
                .getSingleResult();
    }
    public Team find(Long id) {
        return em.find(Team.class,id);
    }

}
