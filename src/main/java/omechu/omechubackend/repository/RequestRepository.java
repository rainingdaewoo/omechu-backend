package omechu.omechubackend.repository;

import omechu.omechubackend.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long>, RequestRepositoryCustom {
}
