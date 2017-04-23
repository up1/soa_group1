package com.hathome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Acer on 6/4/2560.
 */

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class BillController {

    private  final  BillRepository billRepository;

    @Autowired
    public BillController(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

//    get bill by bill id
    @GetMapping("/bill/{id}")
    public Bill getBillById(@PathVariable long id){
        System.out.println(id);
        return billRepository.findById(id);
    }

//    save bill
    @RequestMapping(value = "/bill", method =  RequestMethod.POST)
    public ResponseEntity<BillStatus> saveBill(@RequestBody BillStoreRequest request){
        BillStatus billStatus = billRepository.addBill(request.getUser_id());
        return new ResponseEntity<BillStatus>(billStatus, HttpStatus.CREATED);
    }
}
