package com.example.SpringJWt.Controller;

import com.example.SpringJWt.Service.JwtService;
import com.example.SpringJWt.Service.ProductService;
import com.example.SpringJWt.dto.Authrequest;
import com.example.SpringJWt.dto.Product;
import com.example.SpringJWt.entity.UserInfo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class Controller {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome to jwt classs";
    }

    @PostMapping("/add")
    public String addusers(@RequestBody UserInfo userInfo){
        return productService.addUser(userInfo);

    }
    @GetMapping("/api/all")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Product> getAllTheProducts() {
        return productService.getProductList();
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public Product getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }


    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody Authrequest authrequest){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authrequest.getUsername(),authrequest.getPassword()));

        if(authentication.isAuthenticated()){
            return  jwtService.generatetoken(authrequest.getUsername());
        }else{
            throw new UsernameNotFoundException("Invalid user request");
        }

    }

}
