package w2l.inspired.web;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import w2l.inspired.dao.CustomerSimpleDao;
import w2l.inspired.logical.CustomerScoreCalc;
import w2l.inspired.logical.CustomersChecker;
import w2l.inspired.logical.DailyLogProcessor;
import w2l.inspired.model.CompletionStatus;
import w2l.inspired.model.Customer;
import w2l.inspired.model.DailyLog;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;


@Controller
public class CustomerController {
    @Autowired
    private CustomerSimpleDao customerSimpleDao;
    @Autowired
    private CustomersChecker checker;
    @Autowired
    private DailyLogProcessor logProcessor;
    @Autowired
    private CustomerScoreCalc calc;
    public static Logger LOGGER = LogManager.getLogger(CustomersChecker.class);
    @RequestMapping({"/"})
    public String index(){return "index";}
    @RequestMapping(path = "/today", method = RequestMethod.GET)
    public ModelAndView getCustomersForToday() {
        ModelAndView modelAndView = new ModelAndView("markCustomers");
        modelAndView.addObject("customers", customerSimpleDao.getCustomers());
        modelAndView.addObject("serverTime",LocalDate.now());
        return modelAndView;
    }
    @RequestMapping(path = "/today", method = RequestMethod.POST)
    public ModelAndView returnResults(@RequestBody(required = false) String payload) throws IOException {


        ModelAndView mv = new ModelAndView("result");
        mv.addObject("serverTime", LocalDate.now());
        mv.addObject("inputString", payload);
        List<DailyLog> list = readDailyLogFile();

        int initialBalance = calc.calculateScore(list);
        mv.addObject("yesterday", initialBalance);


        String[] requestParams = (payload == null || payload.isEmpty()) ? new String[0]
                : payload.split("&"); //1=on&2=on  , off  doesn't exist
        List<Customer> customers = customerSimpleDao.getCustomers();
        for(Customer c:customers){
            CompletionStatus s=CompletionStatus.FAILED;
            for(String pair:requestParams) {
                String[] split = pair.split("=");
                if (Integer.parseInt(split[0]) == c.getId())
                    s = CompletionStatus.DONE;
            }
            DailyLog newLogEntry = new DailyLog(c,s);
            list.add(newLogEntry);

        }
        mv.addObject("logList", list);
        logProcessor.reWriteLog(list);
        int balance = calc.calculateScore(list);
        mv.addObject("now", balance);
        return mv;
    }

private List<DailyLog> readDailyLogFile() {
    List<DailyLog> list = new LinkedList<>();
    try {
        list.addAll(logProcessor.getLog());
    } catch (IOException e) {
        LOGGER.warn("Customers are not read!");
    }
    return list;
}

}
