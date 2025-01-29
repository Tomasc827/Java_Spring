package lt.techin.h2_attempt.controller;


import lt.techin.h2_attempt.exceptions.MessageNotFoundException;
import lt.techin.h2_attempt.model.Message;
import lt.techin.h2_attempt.repository.MessageRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
  private MessageRepository messageRepository;

  public MessageController(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  @GetMapping
  public List<Message> getAllMessages() {
    return messageRepository.findAll();
  }

  @PostMapping
  public Message createMessage(@RequestBody Message message) {
    return messageRepository.save(message);
  }

  @GetMapping("/{id}")
  public Message getMessage(@PathVariable String id) {
    return messageRepository.findById(id)
            .orElseThrow(() -> new MessageNotFoundException(id));
  }

}
