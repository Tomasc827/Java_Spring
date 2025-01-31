package lt.techin.Library.controllers;

import jakarta.validation.Valid;
import lt.techin.Library.dtos.MemberDTO;
import lt.techin.Library.exceptions.MemberNotFoundException;
import lt.techin.Library.models.Member;
import lt.techin.Library.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {
  private MemberService memberService;

  @Autowired
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping
  public ResponseEntity<List<Member>> getAllMember() {
    List<Member> members = memberService.findAllMember();
    return ResponseEntity.ok(members);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Member> getMemberById(@PathVariable long id) {
    return memberService.findMemberById(id)
            .map(ResponseEntity::ok)
            .orElseThrow(() -> new MemberNotFoundException(id));
  }

  @PostMapping
  public ResponseEntity<Member> addMember(@Valid @RequestBody MemberDTO memberDTO, Member member) {
    return addMemberValidation(memberDTO, member);

  }

  @PostMapping("/{memberId}/rent-book/{bookId}")
  public ResponseEntity<String> rentBookToMember(@PathVariable long memberId, @PathVariable long bookId) {
    String response = memberService.rentBookToMember(memberId, bookId);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/{memberId}/return-book/{bookId}")
  public ResponseEntity<String> returnBookFromMember(@PathVariable long memberId, @PathVariable long bookId) {
    String response = memberService.returnBookFromMember(memberId, bookId);
    return ResponseEntity.ok(response);
  }

  private ResponseEntity<Member> addMemberValidation(@RequestBody @Valid MemberDTO memberDTO, Member member) {
    member.setName(memberDTO.name());
    member.setEmail(memberDTO.email());
    member.setPassword(memberDTO.password());

    memberService.saveMember(member);

    URI location = URI.create("/members/" + member.getId());

    return ResponseEntity.created(location).body(member);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Member> replaceMemberById(@Valid @RequestBody MemberDTO memberDTO, @PathVariable long id) {
    return memberService.findMemberById(id).map(member -> {
      return addMemberValidation(memberDTO, member);

    }).orElseThrow(() -> new MemberNotFoundException(id));
  }
}



