package wave.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wave.datajpa.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
