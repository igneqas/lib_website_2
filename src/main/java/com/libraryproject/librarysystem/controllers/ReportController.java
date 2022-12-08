package com.libraryproject.librarysystem.controllers;

import com.libraryproject.librarysystem.domain.*;
import com.libraryproject.librarysystem.repositories.BooksRepository;
import com.libraryproject.librarysystem.repositories.OrdersRepository;
import com.libraryproject.librarysystem.repositories.UsersRepository;
import com.libraryproject.librarysystem.thymeleafTypes.ReportDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Controller
public class ReportController {

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @GetMapping("/generateReport")
    public String generateReport(Model model) {
        model.addAttribute("reportDates", new ReportDates());
        return "generateReport.html";
    }

    @PostMapping("/generateReportFull")
    public String generateReportFull(Model model, @RequestParam String fromDate, String toDate) throws ParseException {
        if(fromDate == null || toDate == null) {
            model.addAttribute("errorMessage", "Missing fromDate or toDate.");
            return "error.html";
        }
        List<Orders> orders = ordersRepository.findAll();
        List<Books> books = booksRepository.findAll();
        List<Users> users = usersRepository.findAll();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        fromDate = fromDate.replace('T',' ');
        toDate = toDate.replace('T',' ');
        Date formattedFromDate = formatter.parse(fromDate);
        Date formattedToDate = formatter.parse(toDate);

        HashMap<Integer, Integer> bookCount = new HashMap<>();
        HashMap<Integer, Integer> userBookCount = new HashMap<>();

        orders.removeIf(x -> x.getIssueDate().before(formattedFromDate) || x.getIssueDate().after(formattedToDate));

        int ordersStarted = orders.size();

        orders.forEach(order -> order.getBooksList().forEach(book -> {
            bookCount.merge(book.getId(), 1, Integer::sum);
        }));
        Map<Integer, Integer> sortedCount = bookCount.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        List<BooksWithCount> booksWithCount = new ArrayList<>();
        sortedCount.forEach( (k, v) -> booksWithCount.add(new BooksWithCount(books.stream().filter(book -> book.getId() == k).findFirst().orElse(null), v)));
        Collections.reverse(booksWithCount);
//        booksWithCount.forEach(x -> System.out.println(x));

        orders.forEach(order ->
            userBookCount.merge(order.getUser().getUserID(), order.getBooksList().size(), Integer::sum
        ));
        Map<Integer, Integer> sortedUserBookCount = userBookCount.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        List<UserWithCount> usersWithCount = new ArrayList<>();
        sortedUserBookCount.forEach( (k, v) -> usersWithCount.add(new UserWithCount(users.stream().filter(user -> user.getUserID() == k).findFirst().orElse(null), v)));
        Collections.reverse(usersWithCount);
//        usersWithCount.forEach(x -> System.out.println(x));

        ArrayList<Long> orderDurations = new ArrayList<>();
        orders.removeIf(x -> x.getReturnDate() == null);
        orders.forEach(x -> orderDurations.add(TimeUnit.DAYS.convert(x.getReturnDate().getTime() - x.getIssueDate().getTime(), TimeUnit.MILLISECONDS)));
        OptionalDouble averageOrderLength = orderDurations.stream().mapToDouble(a -> a).average();
        String formattedLength = String.format("%.2f", averageOrderLength.isPresent() ? averageOrderLength.getAsDouble() : 0);
//        System.out.println(orderDurations);
//        System.out.println(averageOrderLength.isPresent() ? averageOrderLength.getAsDouble() : "");

        List<Orders> orders2 = ordersRepository.findAll();
        orders2.removeIf(x -> x.getReturnDate() == null || x.getReturnDate().before(formattedFromDate) || x.getReturnDate().after(formattedToDate));
        int ordersFinished = orders2.size();


//        model.addAttribute("reportDates", new ReportDates(fromDate, toDate));
        model.addAttribute("booksWithCount", booksWithCount);
        model.addAttribute("usersWithCount", usersWithCount);
        model.addAttribute("averageOrderDuration", formattedLength + " days");
        model.addAttribute("ordersStarted", ordersStarted);
        model.addAttribute("ordersFinished", ordersFinished);
        return "generateReportFull.html";
    }
}
