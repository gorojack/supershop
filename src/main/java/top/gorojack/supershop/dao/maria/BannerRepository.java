package top.gorojack.supershop.dao.maria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.gorojack.supershop.pojo.Banner;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {
}
