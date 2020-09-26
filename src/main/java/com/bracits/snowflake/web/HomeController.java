package com.bracits.snowflake.web;

import com.bracits.snowflake.entity.Right;
import com.bracits.snowflake.entity.Role;
import com.bracits.snowflake.security.auth.external.SSOService;
import com.bracits.snowflake.service.right.RightService;
import com.bracits.snowflake.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author Shaiful Islam Palash | kuvic16@gmail.com
 * @CreatedAt: 9/20/2020
 */
@Controller
public class HomeController {

    @Autowired
    private Environment env;

    @Autowired
    private SSOService ssoService;

    @Autowired
    private RightService rightService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MessageSource messageSource;


    @GetMapping("/")
    public Object index() {
        return "index";
    }

    @GetMapping("/home")
    public Object home(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);

        Locale bLocale = new Locale.Builder().setLanguage("bn").setRegion("BD").build();
        String t = messageSource.getMessage("exception.not_found",null, bLocale).toString();
        System.out.println(t);

        return "home";
    }

    @GetMapping("/sso")
    public Object sso() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(env.getProperty("sso.session-url"));
        return redirectView;
    }

    @GetMapping("/sso-logout")
    public Object ssoLogout() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(env.getProperty("sso.logout-url"));
        return redirectView;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/notallowed")
    public Object notallowed() {
        try {
            //rightService.create(new Right("Test", "This is test"));
            List<Right> list = rightService.findByJPA(0, 10, 3);
            System.out.println(list);
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
//        Right right = rightService.findByName("Test");
//        Set<Right> list = new HashSet<>();
//        list.add(right);
//        roleService.create(new Role("Admin", "Admin role", list));
        return "home";
    }

    @GetMapping("/disclosure/form")
    public Object disclosureForm() {
        return "disclosure_form";
    }

    @GetMapping("/next")
    public Object next() {
        return "home";
    }

    @GetMapping("/backdoor")
    public Object backdoor(@RequestParam(name="multipass", required=false, defaultValue="") String multipass, final HttpServletRequest request) {
        String key = env.getProperty("sso.key");
        ssoService.authenticate(multipass, key);
        return "redirect:/disclosure/form";
    }

}
