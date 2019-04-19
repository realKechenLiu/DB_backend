package com.kechen.controller;

import com.kechen.domain.Problem;
import com.kechen.domain.ProblemCode;
import com.kechen.domain.User;
import com.kechen.domain.User_Problem;
import com.kechen.repository.ProblemRepository;
import com.kechen.repository.UserRepository;
import com.kechen.service.ProblemService;
import com.kechen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@RestController
public class MyController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProblemService problemService;

    @RequestMapping(value = "/api/problem")
    public ResponseEntity<Iterable<ProblemCode>> getOneProblem(@RequestBody Map<String,Object>[] maps){
        int userId = (int)maps[0].get("user_id");
        int problemId = (int)maps[0].get("problem_id");
        
    }

    @RequestMapping(value = "/api/login",method = RequestMethod.POST)
    public String login(@RequestBody Map<String,Object>[] maps){
        String email = (String) maps[0].get("email");
        String password = (String) maps[0].get("password");
        User user = userRepository.findByEmailAndPassword(email,password);
        return "{\"status\":"+userService.loginByEmail(email,password)+",\n\"admin\""+user!=null?((user.getIsAdmin()==1)+""):false+"}";
    }

    @RequestMapping("/api/problems")
    public ResponseEntity<Iterable<Problem>> list(){
        List<Problem> list = problemService.getAllProblems();
        for(Problem problem:list)
            System.out.println(problem.getTitle());
        return new ResponseEntity<Iterable<Problem>>(list, HttpStatus.OK);
    }

    @RequestMapping("/api/companies/{CompanyName}")
    public ResponseEntity<Iterable<Problem>> listByCompany(@PathVariable String CompanyName){
        System.out.println(CompanyName);
        return new ResponseEntity<Iterable<Problem>>(problemService.getProblemByCompanyName(CompanyName), HttpStatus.OK);
    }

    @RequestMapping("/api/tags/{tag}")
    public ResponseEntity<Iterable<Problem>> listByTag(@PathVariable String tag){
        return new ResponseEntity<Iterable<Problem>>(problemService.getProblemByTag(tag), HttpStatus.OK);
    }



    @RequestMapping("/test")
    public String test(){

        User user = new User("kechen","password","email",1);
        Problem problem = new Problem("discription","title",1,"tag");

        userRepository.save(user);
//        problemRepository.save(problem);

        User_Problem up = new User_Problem(user,problem,1);
        user.getUpSet().add(up);
        problem.getUpSet().add(up);

//        userRepository.save(user);
        problemRepository.save(problem);

        Problem problem1 = problemRepository.findByProblemId(1);

        if(problem1.getUpSet().size()>0)
            return new LinkedList<>(problem1.getUpSet()).get(0).getIsFinished()+"true";
        else
            return new LinkedList<>(userRepository.findByEmail("email").getUpSet()).size()+"false";
    }
}

class ReturnType{
    int status;
    boolean isAdmin;

    public ReturnType(int status, boolean isAdmin) {
        this.status = status;
        this.isAdmin = isAdmin;
    }
}