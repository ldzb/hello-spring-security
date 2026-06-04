package kr.ac.hansung.service;

import kr.ac.hansung.dto.ProductDto;
import kr.ac.hansung.entity.Product;
import kr.ac.hansung.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    // 전체 목록 페이징 (Pageable → findAll에 그대로 전달)
    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    // 키워드 검색 + 페이징
    public Page<Product> searchProducts(String keyword, Pageable pageable) {
        return productRepository.findByNameContaining(keyword, pageable);
    }

    // 상품 상세 조회 (컨트롤러에서 사용)
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품을 찾을 수 없습니다: " + id));
    }

    @Transactional
    public Product save(ProductDto dto) {
        Product product = new Product(
                dto.getName(), dto.getPrice(), dto.getDescription(), dto.getStock()
        );
        return productRepository.save(product);
    }

    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}