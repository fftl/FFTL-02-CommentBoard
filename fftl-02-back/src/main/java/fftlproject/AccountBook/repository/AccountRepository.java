package fftlproject.AccountBook.repository;

import fftlproject.AccountBook.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
