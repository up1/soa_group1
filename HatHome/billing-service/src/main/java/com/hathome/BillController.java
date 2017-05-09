package com.hathome;

import com.hathome.adapter.CartAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

/**
 * Created by Acer on 6/4/2560.
 */

@RestController
@CrossOrigin
public class BillController {

    private  final  BillRepository billRepository;
    private EmailService emailService;

    @Autowired
    public BillController(BillRepository billRepository,EmailService emailService) {
        this.billRepository = billRepository;
        this.emailService = emailService;
    }

//    get bill by bill id
    @GetMapping("/bill/{id}")
    public Bill getBillById(@PathVariable long id){
        return billRepository.findById(id);
    }

//    save bill
    @RequestMapping(value = "/bill", method =  RequestMethod.POST)
    public ResponseEntity<BillStatus> saveBill(@RequestBody Bill bill) throws MessagingException {

        Bill billout = billRepository.addBill(bill);
        BillStatus billStatus = new BillStatus();
        billStatus.setStatus("success");
        billStatus.setId(billout.getId());
        emailService.sendEmailMessage(billout);
        CartAdapter cartAdapter = new CartAdapter();
        cartAdapter.checkOutFromCart(billout.getUserId());
        return new ResponseEntity<>(billStatus, HttpStatus.CREATED);
    }
}
