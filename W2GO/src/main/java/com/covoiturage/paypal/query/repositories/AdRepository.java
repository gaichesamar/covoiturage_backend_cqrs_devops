package com.covoiturage.paypal.query.repositories;



import com.covoiturage.paypal.query.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AdRepository extends JpaRepository<Ad,String> {

}