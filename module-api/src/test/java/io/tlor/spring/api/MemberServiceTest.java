package io.tlor.spring.api;

import io.tlor.spring.api.repository.MemberRepository;
import io.tlor.spring.api.service.MemberService;
import io.tlor.spring.domain.config.CommonConfiguration;
import io.tlor.spring.domain.model.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CommonConfiguration.class})
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void joinTest() throws Exception {

        Member member = new Member();
        member.setName("KIMJONGIN");

        Long saveId = memberService.join(member);

        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    public void checkDuplicateTest() throws Exception {

        Member member1 = new Member();
        member1.setName("KIMJONGIN");

        Member member2 = new Member();
        member2.setName("KIMJONGIN");

        memberService.join(member1);
        memberService.join(member2);

        //Then
        fail("예외가 발생해야 한다.");
    }
}
