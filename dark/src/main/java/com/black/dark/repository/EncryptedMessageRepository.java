package com.black.dark.repository;

import com.black.dark.entity.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncryptedMessageRepository extends CrudRepository<Message, Long> {
}
