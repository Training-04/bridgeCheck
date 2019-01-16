package group.bridge.web.dao;


import group.bridge.web.entity.Bridge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BridgeRepository extends JpaRepository<Bridge, Integer> {
}
