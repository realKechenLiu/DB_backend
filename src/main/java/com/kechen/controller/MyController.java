package com.kechen.controller;

import com.kechen.domain.*;
import com.kechen.repository.*;
import com.kechen.service.ProblemService;
import com.kechen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


@RestController
public class MyController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CodeRepository codeRepository;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProblemService problemService;


    @RequestMapping(value = "/api/problem/delete/{id}")
    @CrossOrigin
    public ResponseEntity<Iterable<Problem>> deleteProblem(@PathVariable int id){
        Problem p = problemRepository.findByProblemId(id);

        List<Integer> list = new LinkedList<>();
        for(Problem_Code pc:p.getPcodeSet()){
            System.out.println(111111);

            noteRepository.delete(pc.getCode().getNote());

            System.out.println(pc.getCode().getCodeId());
            list.add(new Integer(pc.getCode().getCodeId()));
            pc.setCode(null);
            pc.setProblem(null);
            pc.setUser(null);
        }

        problemService.deleteProblemById(p.getProblemId());
        for (int i:list)
            codeRepository.delete(codeRepository.findByCodeId(i));

        List<Problem> res = problemRepository.findAll();
        return new ResponseEntity<Iterable<Problem>>(res,HttpStatus.OK);
    }


    @RequestMapping(value = "/api/problem/delete",method = RequestMethod.POST,consumes="application/json;charset=UTF-8")
    @CrossOrigin
    public int delete(@RequestBody Map<String,Object> maps){
        int id = (int)maps.get("id");
        String description = (String)maps.get("description");
        String title = (String)maps.get("title");
        int difficulty = (int)maps.get("difficulty");
        String tag = (String)maps.get("tag");
        Problem p = problemRepository.findByProblemId(id);
        if(p==null) return 404;

        List<Integer> list = new LinkedList<>();
        for(Problem_Code pc:p.getPcodeSet()){
            System.out.println(111111);
            noteRepository.delete(pc.getCode().getNote());
            System.out.println(pc.getCode().getCodeId());
            list.add(new Integer(pc.getCode().getCodeId()));
            pc.setCode(null);
            pc.setProblem(null);
            pc.setUser(null);
        }

        problemService.deleteProblemById(p.getProblemId());
        for (int i:list)
            codeRepository.delete(codeRepository.findByCodeId(i));
        return 200;
    }


//    @RequestMapping(value = "/api/problem/insert/{id}/{description}/{title}/{difficulty}/{tag}")
//    @CrossOrigin
//    public int insertProblem(@PathVariable int id,@PathVariable String description,@PathVariable String title,@PathVariable int difficulty,@PathVariable String tag){
//
//        if(id == -1)
//            return problemService.Insert(new Problem(description,title,difficulty,tag))?200:400;
//        else
//            return problemService.Insert(new Problem(id,description,title,difficulty,tag))?200:400;
//    }

    @RequestMapping(value = "/api/problem/insert",method = RequestMethod.POST,consumes="application/json;charset=UTF-8")
    @CrossOrigin
    public String insertProblem(@RequestBody Map<String,Object> maps){
        int problemId = (int) maps.get("problemId");
        String description = (String) maps.get("description");
        String title = (String) maps.get("title");
        int difficulty = (int) maps.get("difficulty");
        String tag = (String) maps.get("tag");

        String companyName = (String)maps.get("companyName");
        Company company = companyRepository.findByCompanyName(companyName);
        Problem problem;

        if(problemId == -1)
            problem = new Problem(description,title,difficulty,tag);
        else{
            problem = problemRepository.findByProblemId(problemId);
            problem.setDescription(description);
            problem.setDifficulty(difficulty);
            problem.setTag(tag);
            problem.setTitle(title);
        }
        Problem_Company pc = new Problem_Company(problem,company);
        Set<Problem_Company> set = problem.getPcomSet();
        set.add(pc);
        problem.setPcomSet(set);

        return "{\n\"status\":"+(problemService.Insert(problem)?200:400)+"}";
    }

//    @RequestMapping(value = "/api/user/signup/{userName}/{password}/{email}/{isAdmin}")
//    @CrossOrigin
//    public int signup(@PathVariable String userName, @PathVariable String password, @PathVariable String email, @PathVariable int isAdmin){
//
//        if(userService.register(userName,email,password,isAdmin))
//            return 200;
//        else
//            return 400;
//    }

    @RequestMapping(value = "/api/user/signup",method = RequestMethod.POST,consumes="application/json;charset=UTF-8")
    @CrossOrigin
    public String signup(@RequestBody Map<String,Object> maps){
        String userName = (String) maps.get("userName");
        String password = (String) maps.get("password");
        String email = (String) maps.get("email");
        int isAdmin = (int)maps.get("isAdmin");
        if(userService.register(userName,email,password,isAdmin)) {
            User user = userRepository.findByUserName(userName);
            return "{\"status\":" + 200 + ",\n\"admin\":" + false + ",\n\"userName\":\"" + userName + "\",\n\"userId\":"+user.getUserId()+"}";
        }
        else
            return "{\"status\":"+400+",\n\"admin\":"+false+",\n\"userName\":\""+userName+"\",\n\"userId\":"+-1+"}";
    }


    @RequestMapping(value = "/api/problem/update",method = RequestMethod.POST,consumes="application/json;charset=UTF-8")
    @CrossOrigin
    public int updateCode(@RequestBody Map<String,Object> maps) {
        int userId = (int) maps.get("user_id");
        int problemId = (int) maps.get("problem_id");

//        Code code = (Code)(maps.get("code"));
//        Note note = (Note)maps.get("note");

        Map<String, Object> map1 = (Map<String, Object>) maps.get("code");
        Map<String, Object> map2 = (Map<String, Object>) maps.get("note");

        Code code = codeRepository.findByCodeId((int)map1.get("code_id"));
        code.setIsAccepted(((boolean) map1.get("isAccepted")) == true ? 1 : 0);
        code.setCodeLanguage((String) map1.get("code_language"));
        code.setPerformance((double) map1.get("performance"));
        code.setContent((String) map1.get("content"));
        code.setCreateTime(Timestamp.valueOf((String) map1.get("time_created")));
        code.setCreateTime(Timestamp.valueOf((String) map1.get("time_modified")));

        Note note = code.getNote();
        note.setContent((String) map2.get("content"));
        note.setCreateTime(Timestamp.valueOf((String) map2.get("time_created")));
        note.setModifyTime(Timestamp.valueOf((String) map2.get("time_modified")));

        code.setNote(note);

        codeRepository.save(code);
        noteRepository.save(note);
        return 200;
    }

        @RequestMapping(value = "/api/problem/submission",method = RequestMethod.POST,consumes="application/json;charset=UTF-8")
    @CrossOrigin
    public int update(@RequestBody Map<String,Object> maps){
        int userId = (int)maps.get("user_id");
        int problemId = (int)maps.get("problem_id");

//        Code code = (Code)(maps.get("code"));
//        Note note = (Note)maps.get("note");

        Map<String,Object> map1 = (Map<String, Object>) maps.get("code");
        Map<String,Object> map2 = (Map<String, Object>) maps.get("note");
        Code code = new Code(((boolean)map1.get("isAccepted"))==true?1:0,(double)map1.get("performance"),(String)map1.get("code_language"),Timestamp.valueOf((String)map1.get("time_created")),
                (Timestamp.valueOf((String) map1.get("time_modified"))),(String)map1.get("content"));
        Note note = new Note((String)map2.get("content"),Timestamp.valueOf((String)map2.get("time_created")),
                (Timestamp.valueOf((String) map2.get("time_modified"))),code);

        codeRepository.save(code);
        noteRepository.save(note);

        User user = userService.findById(userId);
        if(user == null) return 404;

        for(Problem_Code p:user.getPcSet()) {
            if (p.getProblem().getId() == problemId) {
                Problem problem = p.getProblem();
                problem.getPcodeSet().add(new Problem_Code(problem,code,user));
                problemRepository.save(problem);
                return 200;
            }
        }
        Problem problem = problemRepository.findByProblemId(problemId);
        problem.getPcodeSet().add(new Problem_Code(problem,code,user));
        problemRepository.save(problem);

        Problem_Code pc = new Problem_Code(problem,code,user);
        Set<Problem_Code> set = user.getPcSet();
        set.add(pc);
        user.setPcSet(set);

        User_Problem user_problem = new User_Problem(user,problem,code.getIsAccepted());
        Set<User_Problem> set1 = user.getUpSet();
        set1.add(user_problem);
        user.setUpSet(set1);
        userRepository.save(user);

        return 200;
    }

    @RequestMapping(value = "/api/problem",method = RequestMethod.POST,consumes="application/json;charset=UTF-8")
    @CrossOrigin
    public ResponseEntity<Iterable<ProblemCode>> getOneProblem(@RequestBody Map<String,Object> maps){

//        System.out.println(maps);
        int userId = (int)maps.get("user_id");
        int problemId = (int)maps.get("problem_id");

//        System.out.println("user is "+userId);
//        System.out.println("problem is "+problemId);
        User user = userService.findById(userId);
        ProblemCode pc = new ProblemCode();

        for(Problem_Code p:user.getPcSet()){
            if(p.getProblem().getId() == problemId){
                pc.problemId = p.getProblem().getProblemId();
                pc.description = p.getProblem().getDescription();
                pc.difficulty = p.getProblem().getDifficulty();
                pc.tag = p.getProblem().getTag();
                pc.title = p.getProblem().getTitle();
                for(Problem_Code code:p.getProblem().getPcodeSet()){
                    System.out.println(1111111);
                    if(!pc.codeList.contains(code.getCode()))
                        pc.codeList.add(code.getCode());
                }
                for(Problem_Company pcom:p.getProblem().getPcomSet()){
                    if(!pc.companyList.contains(pcom.getCompany().getCompanyName()))
                        pc.companyList.add(pcom.getCompany().getCompanyName());
                }
            }
        }
        List<ProblemCode> list = new LinkedList<>();
        list.add(pc);
        return new ResponseEntity<Iterable<ProblemCode>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/problem/{userId}/{problemId}")
    @CrossOrigin
    public ResponseEntity<Iterable<ProblemCode>> getOneProblemq(@PathVariable int userId, @PathVariable int problemId){

        User user = userService.findById(userId);
        ProblemCode pc = new ProblemCode();

        for(Problem_Code p:user.getPcSet()){
//            System.out.println(p.getProblem().getId()+" "+problemId);
            if(p.getProblem().getId() == problemId){
                pc.problemId = p.getProblem().getProblemId();
                pc.description = p.getProblem().getDescription();
                pc.difficulty = p.getProblem().getDifficulty();
                pc.tag = p.getProblem().getTag();
                pc.title = p.getProblem().getTitle();
                for(Problem_Code code:p.getProblem().getPcodeSet()){
                    pc.codeList.add(code.getCode());
                }
                for(Problem_Company pcom:p.getProblem().getPcomSet()){
                    pc.companyList.add(pcom.getCompany().getCompanyName());
                }
            }
        }
        List<ProblemCode> list = new LinkedList<>();
        list.add(pc);
        return new ResponseEntity<Iterable<ProblemCode>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/login",method = RequestMethod.POST,consumes="application/json;charset=UTF-8")
    @CrossOrigin
//    public ResponseEntity<Iterable<ReturnType>> login(@RequestBody Map<String,Object> maps){
    public String login(@RequestBody Map<String,Object> maps){
        String email = (String) maps.get("email");
        String password = (String) maps.get("password");
        User user = userRepository.findByEmailAndPassword(email,password);
        List<ReturnType> list = new LinkedList<>();
        list.add(new ReturnType(userService.loginByEmail(email,password),user!=null?((user.getIsAdmin()==1)):false));
//        return new ResponseEntity<Iterable<ReturnType>>(list, HttpStatus.OK);
        return "{\"status\":"+userService.loginByEmail(email,password)+",\n\"admin\":"+(user!=null?((user.getIsAdmin()==1)):false)+",\n\"userName\":\""+user.getUserName()+"\",\n\"userId\":"+user.getUserId()+"}";
    }

    @RequestMapping("/api/problems")
    @CrossOrigin
    public ResponseEntity<Iterable<Problem>> list(){
        List<Problem> list = problemService.getAllProblems();
        for(Problem problem:list)
            System.out.println(problem.getTitle());
        return new ResponseEntity<Iterable<Problem>>(list, HttpStatus.OK);
    }

    @RequestMapping("/api/companies/{CompanyName}")
    @CrossOrigin
    public ResponseEntity<Iterable<Problem>> listByCompany(@PathVariable String CompanyName){
        System.out.println(CompanyName);
        return new ResponseEntity<Iterable<Problem>>(problemService.getProblemByCompanyName(CompanyName), HttpStatus.OK);
    }

    @RequestMapping("/api/tags/{tag}")
    @CrossOrigin
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

class ReturnType implements Serializable{
    int status;
    boolean isAdmin;

    public ReturnType(int status, boolean isAdmin) {
        this.status = status;
        this.isAdmin = isAdmin;
    }
}