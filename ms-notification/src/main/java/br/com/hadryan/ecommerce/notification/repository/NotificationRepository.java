package br.com.hadryan.ecommerce.notification.repository;

import br.com.hadryan.ecommerce.notification.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
}
