package com.example.SpringJWt.Service;

import com.example.SpringJWt.Repo.UserInfoRepo;
import com.example.SpringJWt.dto.Product;
import com.example.SpringJWt.entity.UserInfo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {

    List<Product> productList=null;

        @Autowired
    private UserInfoRepo userInfoRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

        @PostConstruct
    public void loadProductfromDB(){
            productList= IntStream.rangeClosed(1,100)
                    .mapToObj(i -> Product.builder()
                            .productId(i)
                            .name("Product"+i)
                            .qty(new Random().nextInt(10))
                            .price(new Random().nextInt(5000)).build()

                    ).collect(Collectors.toList());
        }

        public List<Product> getProductList(){
            return productList;
        }

        public Product getProductById(int id){
            return productList.stream()
                    .filter(product -> product.getProductId()==id)
                    .findAny()
                    .orElseThrow(()-> new RuntimeException("Product Id Not Found"));
        }

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepo.save(userInfo);
        return "user added to system ";
    }

}
