package com.fpt.swd391.fall2022.swd391.api_inventory_note;

import com.fpt.swd391.fall2022.swd391.entity.InventoryNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryNoteRepository extends JpaRepository<InventoryNote, Long> {
    @Query(value = "SELECT i.* from inventory_note i where i.stattus = true",nativeQuery = true)
    List<InventoryNote> getAllByStatus();
}
