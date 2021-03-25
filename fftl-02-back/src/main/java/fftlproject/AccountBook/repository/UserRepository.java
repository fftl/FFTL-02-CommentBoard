package fftlproject.AccountBook.repository;

import fftlproject.AccountBook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
