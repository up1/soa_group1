package com.hathome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Acer on 6/4/2560.
 */

@RestController
public class BillController {

    private  final  BillRepository billRepository;

    @Autowired
    public BillController(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @GetMapping("/bill/{id}")
    public Bill getBillById(@PathVariable long id){
        return billRepository.findById(id);
    }
}
