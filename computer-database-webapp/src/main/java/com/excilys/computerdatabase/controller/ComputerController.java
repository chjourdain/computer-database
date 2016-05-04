package com.excilys.computerdatabase.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.mapper.ComputerMapper;
import com.excilys.computerdatabase.mapper.PagerRequestMapper;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.impl.CompanyServiceImpl;
import com.excilys.computerdatabase.service.impl.ComputerServiceImpl;

@Controller
@RequestMapping("/computer")
public class ComputerController {
    @Autowired
    ComputerServiceImpl service;
    @Autowired
    CompanyServiceImpl companyService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView get(@RequestParam Map<String, String> param) {
        ModelAndView model = new ModelAndView("dashboard");
        PageRequest page = PagerRequestMapper.get(param);
        Page<ComputerDTO> pager = ComputerMapper.toDTO(service.findAll(page, param.get("search")), page);
        model.addObject("search", param.get("search"));
        model.addObject("pager", pager);
        return model;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add() {
        ModelAndView model = new ModelAndView("addComputer", "computerDTO", new ComputerDTO());
        model.addObject("map", companyService.getMap());
        return model;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addComputer(@Valid @ModelAttribute("computerDTO") ComputerDTO dto,
            BindingResult bindingResult) {
        ModelAndView model = new ModelAndView("addComputer");
        String result = "error.notadded";
        if (!bindingResult.hasErrors()) {
            dto = ComputerMapper.ToInternational(dto, LocaleContextHolder.getLocale().toString());
            Computer c1 = ComputerMapper.toComputer(dto);
            service.create(c1);
            result = "success.added";
        }
        model.addObject("map", companyService.getMap());
        model.addObject("result", result);
        return model;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView deleteComputer(@RequestParam Map<String, String> param) {
        List<Integer> deleteList = mapDelete(param);
        for (Integer i : deleteList) {
            service.delete(service.find(i));
        }
        // redirect to dashboard, fill the list of computer with a pager define
        // by the others parameters (page, nbByPage, search, order by)
        PageRequest page = PagerRequestMapper.get(param);
        Page<ComputerDTO> pager = ComputerMapper.toDTO(service.findAll(page, param.get("search")), page);
        ModelAndView model = new ModelAndView("redirect:dashboard");
        model.addObject("pager", pager);
        return model;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editView(@RequestParam Map<String, String> param) {
        // get the computer id, send back the user to dashboard.jsp if not
        // valid.
        String id = param.get("id");
        if (id == null || id.equals("0") || id.isEmpty()) {
            return new ModelAndView("redirect:dashboard");
        }
        // get the computer to send its data to the jsp.
        ComputerDTO computer = ComputerMapper.toDTO(service.find(Long.valueOf(id)));
        ModelAndView model = new ModelAndView("editComputer", "computerDTO", new ComputerDTO());
        model.addObject("computer", computer);
        // pass the list of companies in a hashmap as attribute.
        model.addObject("map", companyService.getMap());
        return model;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editComputer(@Valid @ModelAttribute("computerDTO") ComputerDTO dto,
            BindingResult bindingResult) {
        ModelAndView model;
        if (bindingResult.hasErrors()) {
            long i = Long.valueOf(dto.getId());
            ComputerDTO computer = ComputerMapper.toDTO(service.find(i));
            model = new ModelAndView("editComputer");
            model.addObject("result", "error.notupdated");
            model.addObject("computer", computer);
            // pass the list of companies in a hashmap as attribute.
            model.addObject("map", companyService.getMap());
            return model;
        } else {
            dto = ComputerMapper.ToInternational(dto, LocaleContextHolder.getLocale().toString());
            Computer c1 = ComputerMapper.toComputer(dto);
            service.update(c1);
            model = new ModelAndView("redirect:dashboard");
            return model;
        }
    }

    private static List<Integer> mapDelete(Map<String, String> param) {
        String selection = param.get("selection");
        LinkedList<Integer> deleteList = new LinkedList<>();
        for (String s : selection.split(",")) {
            deleteList.add(Integer.valueOf(s));
        }
        return deleteList;
    }
}