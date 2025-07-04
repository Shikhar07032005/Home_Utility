package Home_Utility_Tracker.controller;

import Home_Utility_Tracker.model.UtilityBill;
import Home_Utility_Tracker.model.User;
import Home_Utility_Tracker.repository.UtilityBillRepository;
import Home_Utility_Tracker.repository.UtilityTypeRepository;
import Home_Utility_Tracker.repository.UserRepository;
import Home_Utility_Tracker.service.PdfGeneratorService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class UtilityBillController {

    @Autowired private UtilityBillRepository billRepo;
    @Autowired private UtilityTypeRepository typeRepo;
    @Autowired private UserRepository userRepo;
    @Autowired private PdfGeneratorService pdfService;

    @GetMapping("/home")
    public String homePage(Model model, Principal principal) {
        Optional<User> user = userRepo.findByEmail(principal.getName());
        user.ifPresent(u -> model.addAttribute("fullName", u.getName()));
        return "home";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        Optional<User> user = userRepo.findByEmail(principal.getName());
        List<UtilityBill> bills = billRepo.findByUser(user);
        model.addAttribute("bills", bills);
        return "dashboard";
    }

    @GetMapping("/add-utility")
    public String showUtilityForm(Model model) {
        model.addAttribute("utilityBill", new UtilityBill());
        model.addAttribute("utilityTypes", typeRepo.findAll());
        return "utility_form";
    }

    @PostMapping("/add-utility")
    public String saveUtility(@ModelAttribute UtilityBill utilityBill, Principal principal) {
        Optional<User> user = userRepo.findByEmail(principal.getName());
        utilityBill.setUser(user.get());

        if (utilityBill.isPaid() && utilityBill.getPaymentDate() == null) {
            utilityBill.setPaymentDate(LocalDate.now());
        }

        billRepo.save(utilityBill);
        return "redirect:/dashboard";
    }


    @GetMapping("/edit-bill/{id}")
    public String editBill(@PathVariable Long id, Model model) {
        UtilityBill bill = billRepo.findById(id).orElseThrow();
        model.addAttribute("utilityBill", bill);
        model.addAttribute("utilityTypes", typeRepo.findAll());
        return "utility_form";
    }

    @GetMapping("/delete-bill/{id}")
    public String deleteBill(@PathVariable Long id) {
        billRepo.deleteById(id);
        return "redirect:/dashboard";
    }

    @GetMapping("/mark-paid/{id}")
    public String markBillAsPaid(@PathVariable Long id) {
        UtilityBill bill = billRepo.findById(id).orElseThrow();
        bill.setPaid(true);
        bill.setPaymentDate(LocalDate.now());
        billRepo.save(bill);
        return "redirect:/dashboard";
    }

    @PostMapping("/generate-pdf")
    public void generatePdf(HttpServletResponse response, Principal principal) throws Exception {
        Optional<User> user = userRepo.findByEmail(principal.getName());

        List<UtilityBill> bills = billRepo.findByUser(user);

        String userName = user.map(User::getName).orElse("Unknown User");

        ByteArrayInputStream pdf = pdfService.generateUtilityPDF(bills, userName);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=utility_report.pdf");
        IOUtils.copy(pdf, response.getOutputStream());
    }
}