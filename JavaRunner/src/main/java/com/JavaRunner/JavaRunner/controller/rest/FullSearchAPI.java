package com.JavaRunner.JavaRunner.controller.rest;

import com.JavaRunner.JavaRunner.domain.model.Administrator;
import com.JavaRunner.JavaRunner.domain.model.Race;
import com.JavaRunner.JavaRunner.domain.model.Ranking;
import com.JavaRunner.JavaRunner.domain.model.Route;
import com.JavaRunner.JavaRunner.domain.repository.AdministratorRepository;
import com.JavaRunner.JavaRunner.domain.repository.RaceRepository;
import com.JavaRunner.JavaRunner.domain.repository.RankingRepository;
import com.JavaRunner.JavaRunner.domain.repository.RouteRepository;
import com.JavaRunner.JavaRunner.utils.validations.Sex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class FullSearchAPI {
    @Autowired
    AdministratorRepository admins;
    @Autowired
    RankingRepository rankings;
    @Autowired
    RouteRepository routes;

    @GetMapping(value = "admins")
    public Iterable<Administrator> allAdmins() {
        return admins.findAll();
    }

    @GetMapping(value = "rankings")
    public Iterable<Ranking> allRankings() {
        return rankings.findAll();
    }

    @GetMapping(value = "routes")
    public Iterable<Route> allRoutes() {
        return routes.findAll();
    }

    @GetMapping(value = "sex")
    public Sex[] allSexs() {
        return Sex.values();
    }
}
