package top.gorojack.supershop.dao.maria;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.gorojack.supershop.pojo.Address;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAddressByUid(Long uid);

    @Modifying
    @Transactional
    @Query("UPDATE Address SET isDefault = 0 WHERE uid = :uid")
    void clearUserDefaultAddress(Long uid);

    Address findAddressByUidAndIsDefault(Long uid, Integer isDefault);
}
