package com.JavaRunner.JavaRunner.controller.rest;

import com.JavaRunner.JavaRunner.domain.repository.RaceRepository;
import com.JavaRunner.JavaRunner.domain.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/admin/api")
public class AggregateRunRanking {
    @Autowired
    RaceRepository races;
    @Autowired
    RankingRepository rankings;

//    @PostMapping
//    public Race aggregate(@RequestBody Race race) {
//        Iterable<Ranking> rankings = this.rankings.saveAll(race.getRankings());
//        race.setRankings((Collection<Ranking>) rankings);
//        return races.save(race);
//}

    @GetMapping
    public String all(HttpServletRequest request, Model model) {
        model.addAttribute("hello", request.getAttribute("startTime"));
        return "fodac";
    }
}
