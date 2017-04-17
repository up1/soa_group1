package com.hathome;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hathome.adapter.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Acer on 6/4/2560.
 */

@RestController
@CrossOrigin(origins = "*")
public class BillController {

    private  final  BillRepository billRepository;

    @Autowired
    public BillController(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @GetMapping("/bill/{id}")
    public Bill getBillById(@PathVariable long id){
        System.out.println(id);
        return billRepository.findById(id);
    }

    @RequestMapping(value = "/bill", method =  RequestMethod.POST)
    public ResponseEntity<BillStatus> saveBill(@RequestBody Bill bill){
        BillStatus billStatus = billRepository.addBill(bill);
        return new ResponseEntity<BillStatus>(billStatus, HttpStatus.CREATED);
    }
}
