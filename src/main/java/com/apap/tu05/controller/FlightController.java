package com.apap.tu05.controller;

import com.apap.tu05.model.FlightModel;
import com.apap.tu05.model.PilotModel;
import com.apap.tu05.service.FlightService;
import com.apap.tu05.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class FlightController {

    private final FlightService flightService;
    private final PilotService pilotService;

    @Autowired
    public FlightController(FlightService flightService, PilotService pilotService) {
        this.flightService = flightService;
        this.pilotService = pilotService;
    }

    @GetMapping(value = "/flight/add/{licenseNumber}")
    private String add(@PathVariable(value = "licenseNumber") String license, Model model){

        FlightModel flightModel = new FlightModel();
        PilotModel pilotModel = pilotService.getPilotDetailByLicenseNumber(license);

        flightModel.setPilot(pilotModel);

        model.addAttribute("flight", flightModel);

        return "addFlight";

    }

    @PostMapping(value = "/flight/add")
    private String addFlightSubmit(@ModelAttribute FlightModel flightModel){
        flightService.addFlight(flightModel);
        return "redirect:/pilot/view/"+flightModel.getPilot().getLicenseNumber();
    }

    @GetMapping(value = "/flight/edit/{id}")
    private String edit(@PathVariable(value = "id") Long id, ModelMap model){

        FlightModel flightModel = flightService.getById(id);

        model.addAttribute("flight", flightModel);

        return "editFlight";

    }

    @PostMapping(value = "/flight/edit")
    private String doedit(@ModelAttribute FlightModel flightModel){

        flightService.addFlight(flightModel);
        return "redirect:/pilot/view/"+flightModel.getPilot().getLicenseNumber();

    }

    @GetMapping(value = "/flight/delete/{id}")
    private String delete(@PathVariable(value = "id") Long id, ModelMap model){

        FlightModel flightModel = flightService.getById(id);

        model.addAttribute("flight", flightModel);

        return "deleteFlight";

    }

    @PostMapping(value = "/flight/delete")
    private String dodelete(@ModelAttribute FlightModel flightModel){

        String license = flightModel.getPilot().getLicenseNumber();
        flightService.deleteFlight(flightModel);

        return "redirect:/pilot/view/"+license;
    }

    @GetMapping({"/flights"})
    public String showFlights(PilotModel pilot, @RequestParam(value = "licenseNumber") String lNumber, Model model) {

        pilot = pilotService.getPilotDetailByLicenseNumber(lNumber);
        model.addAttribute("pilot", pilot);

        model.addAttribute("title", "Daftar Penerbangan");
        return "flights";
    }



    @RequestMapping(value="/flights", params={"save"})
    public String saveFlights(PilotModel pilot, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "flights";
        }

        if (pilot.getPilotFlight() != null && !pilot.getPilotFlight().isEmpty()){
            pilot.getPilotFlight().stream().forEach(f-> f.setPilot(pilot));
        }

        pilotService.addPilot(pilot);
        model.clear();
        return "redirect:/pilot/view/"+pilot.getLicenseNumber();
    }



    @RequestMapping(value="/flights", params={"addRow"})
    public String addRow(PilotModel pilot, final BindingResult bindingResult, final ModelMap model) {
        pilot.getPilotFlight().add(new FlightModel());
        model.addAttribute("pilot", pilot);
        return "flights";
    }


    @RequestMapping(value="/flights", params={"removeRow"})
    public String removeRow(PilotModel pilot, final BindingResult bindingResult, Model model, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
        pilot.getPilotFlight().remove(rowId.intValue());

        model.addAttribute("pilot", pilot);
        return "flights";
    }
}
