package lt.techin.Library.repositories;

import lt.techin.Library.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
  boolean existsByEmail(String email);

  boolean existsByPassword(String password);

  Optional<Member> findByEmail(String email);
}
