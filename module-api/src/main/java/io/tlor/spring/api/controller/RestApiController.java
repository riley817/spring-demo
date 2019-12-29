package io.tlor.spring.api.controller;

import io.tlor.spring.api.service.MemberService;
import io.tlor.spring.api.vo.CommonRes;
import io.tlor.spring.api.vo.api.req.CreateMemberReq;
import io.tlor.spring.domain.model.Address;
import io.tlor.spring.domain.model.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RestApiController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/members", method = RequestMethod.GET)
    public ResponseEntity<CommonRes> list(HttpServletRequest request) {
        CommonRes res = _callService(request, () -> memberService.findMembers());
        return new ResponseEntity<>(res, res.getStatus());
    }

    @RequestMapping(value = "/members/new", method = RequestMethod.POST)
    public ResponseEntity<CommonRes> createMember(@RequestBody CreateMemberReq req) {

        Address address = new Address(req.getCity(), req.getStreet(), req.getZipcode());

        Member member = new Member();
        member.setName(req.getName());
        member.setAddress(address);

        CommonRes res = _callService(null, () -> memberService.join(member));
        return new ResponseEntity<>(res, res.getStatus());

    }

}