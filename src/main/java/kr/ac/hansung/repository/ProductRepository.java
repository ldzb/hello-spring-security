package kr.ac.hansung.repository;

import kr.ac.hansung.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // 1. 기존 기반 코드에 있던 품절 상품 카운트 메서드 (복구)
    long countByStockEquals(int stock);

    // 2. 방금 4.2 과제로 추가한 페이징 & 검색 메서드
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword%")
    Page<Product> findByNameContaining(@Param("keyword") String keyword, Pageable pageable);
}