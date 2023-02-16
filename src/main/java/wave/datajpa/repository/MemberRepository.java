package wave.datajpa.repository;


import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import wave.datajpa.dto.MemberDto;
import wave.datajpa.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    //@NamedQuery
    @Query(name="Member.findByUsername")
    List<Member> findByUsername(@Param("username") String username);
    //@JPQL
    @Query("select m from Member m where m.username = :username and m.age > :age")
    List<Member> findUser(@Param("username") String username,@Param("age") int age);

    @Query("select m.username from Member m ")
    List<String> findUsernameList();

    @Query("select new wave.datajpa.dto.MemberDto(m.id,m.username,t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query(value="select m from Member m left join m.team t",countQuery = "select count(m.username) from Member m")
    Page<Member> findByAge(int age, Pageable pageable);

    @Modifying
    @Query("update Member m set m.age = m.age+1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    @Query("select m from Member m left join fetch m.team")
    List<Member> findMemberFetchJoin();

    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();

    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m")
    List<Member> findMemberEntityGraph();

    @EntityGraph("member.all")
    List<Member> findEntityGraphByUsername(@Param("username")String username);
    @QueryHints(value= @QueryHint(name="org.hibernate.readOnly",value = "true"))
    Member findReadOnlyByUsername(String username);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Member>findLockByUsername(String username);
}
