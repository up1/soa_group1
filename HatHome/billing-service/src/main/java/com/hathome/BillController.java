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
@CrossOrigin(origins = "http://localhost:8080")
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
    public Bill getBillById(@PathVariable long id) throws MessagingException {
        System.out.println(id);
        Bill bill = billRepository.findById(id);
        emailService.sendEmailMessage(bill);
        return bill;
    }

//    save bill
    @RequestMapping(value = "/bill", method =  RequestMethod.POST)
    public ResponseEntity<BillStatus> saveBill(@RequestBody BillStoreRequest request){
        BillStatus billStatus = billRepository.addBill(request);
        CartAdapter cartAdapter = new CartAdapter();
        cartAdapter.checkOutFromCart(request.getUser_id());
        return new ResponseEntity<>(billStatus, HttpStatus.CREATED);
    }
}
