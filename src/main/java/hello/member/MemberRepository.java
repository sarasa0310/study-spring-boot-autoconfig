package hello.member;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final JdbcTemplate template;

    public void initTable() {
        template.execute("CREATE TABLE member(member_id VARCHAR PRIMARY KEY, name VARCHAR)");
    }

    public void save(Member member) {
        template.update("INSERT INTO member(member_id, name) VALUES(?, ?)",
                member.getMemberId(), member.getName());
    }

    public Member find(String memberId) {
        return template.queryForObject("SELECT * FROM member WHERE member_id=?",
                BeanPropertyRowMapper.newInstance(Member.class),
                memberId);
    }

    public List<Member> findAll() {
        return template.query("SELECT * FROM member",
                BeanPropertyRowMapper.newInstance(Member.class));
    }

}
