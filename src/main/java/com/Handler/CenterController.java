package com.Handler;

import com.model.Admin;
import com.model.Agent;
import com.model.DataResult;
import com.model.User;
import com.service.AdminService;
import com.service.AgentService;
import com.service.UserService;
import com.util.Image;
import com.util.ImageTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.util.Date;
import java.util.Map;

@Controller
public class CenterController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AgentService agentService;


    /**
     * 用户注册
     * @param user
     * @param code
     * @param session
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getImage")
    public void GetImg(HttpServletResponse response) throws IOException {
        ImageTwo img=new ImageTwo();
        OutputStream out = response.getOutputStream();
        ImageIO.write(img.getImage(),"png",out);
    }
    @ResponseBody
    @RequestMapping(value = "/userRegister",method = RequestMethod.POST)
    public DataResult userRegister(User user,String code,HttpSession session,
                               HttpServletRequest request,HttpServletResponse response){
        DataResult dataResult =new DataResult();
        //str.toLOwerCase() 把字符串全部转为小写
        if(code.toLowerCase().equals(session.getAttribute("code"))) {
            user.setCreate_time(new Date());
            user.setU_hash(user.getPhone().hashCode()+"");
            dataResult = userService.UserRigister(user);
            if(dataResult.getStatus()==200)
                dataResult.setData("success.html?h="+user.getU_hash());

        }else{
            dataResult.setStatus(401);
            dataResult.setMsg("验证码错误");
        }
        return dataResult;
    }

    /**
     * 返回 hash 值
     * @param hash
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/userInfo",method = RequestMethod.POST)
    public DataResult userInfo(String hash){
        DataResult dataResult;
        dataResult=userService.GetUserByUhash(hash);
        return  dataResult;
    }

    /**
     * 设置图片验证码
     *
     * @param response
     * @param request
     * @throws IOException
     */
    @RequestMapping("/getImg")
    public void GetImg(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Image img=new Image();
        request.getSession().setAttribute("code",img.getVcode());
        OutputStream out = response.getOutputStream();
        ImageIO.write(img.getImage(),"png",out);
        request.getSession().setAttribute("code",img.getVcode().toLowerCase());

    }




    /**
     * 登录验证
     * @param name
     * @param pass
     * @param phone
     * @param code
     * @param type
     * @return 数据
     */
    @ResponseBody
    @RequestMapping(value = "/Login")
    public DataResult AdminLogin(@RequestParam(required = false) String name,String pass,
                             @RequestParam(required = false) String phone,String code,
                             Map<String,Object>map,
                             @RequestParam(required = false) String type,HttpServletResponse response,
                                 HttpSession session)  {
             DataResult dataResult=new DataResult();
        if(session.getAttribute("code").equals(code.toLowerCase())) {
            Agent agent;
            Admin admin;
            Cookie cookie = new Cookie("type", type.hashCode()+"");//创建新的Cookie对象
            cookie.setPath("/");//设置作用域：本域名下ContextPath都可以访问该cookie。
            response.addCookie(cookie);//将cookie 添加到response 的Cookie 数组中返回给客户端
            if ("admin".equals(type)) {
                dataResult = adminService.AdminLogin(name, pass);
                if(dataResult.getStatus()==200) {
                    admin = (Admin) dataResult.getData();
                    session.setAttribute("adminData", admin);
                }
            } else if ("agent".equals(type)) {
                dataResult = agentService.Login(phone, pass);
                        if(dataResult.getStatus()==200) {
                            agent = (Agent) dataResult.getData();
                            session.setAttribute("agentData", agent);
                        }
            }
            map.put("cookie", cookie);
            if (dataResult.getStatus() == 200) {
                dataResult.setData("info.html?type="+type);
            }
        }else{
            dataResult.setStatus(401);
            dataResult.setMsg("验证码错误");
        }
         return dataResult;
    }

    /**
     * 获取管理员信息
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/adminInfo",method = RequestMethod.GET)
    public DataResult GetAdminInfo(HttpSession session, HttpServletRequest request){
        DataResult dataResult=new DataResult();
        Cookie[] cookies=request.getCookies();
        for(Cookie cookie:cookies){
            if("type".equals(cookie.getName())) {
                if (("admin".hashCode() + "").equals(cookie.getValue())) {
                    Admin admin = (Admin) session.getAttribute("adminData");
                    dataResult.setStatus(200);
                    dataResult.setMsg("Yes");
                    dataResult.setData(admin);
                }
                else {
                    dataResult.setStatus(401);
                    dataResult.setMsg("抱歉您未登录");
                }
            }
        }
        return dataResult;
    }

    /**
     * 管理员修改密码
     * @param name
     * @param oldpass
     * @param pass
     * @param code
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/setAdmin",method = RequestMethod.POST)
    public DataResult UpAdmin(String name,String oldpass,String pass,String code,HttpSession session){
        DataResult dataResult=new DataResult();
        if(session.getAttribute("code").equals(code)){
            dataResult=adminService.AdminUpdatePassword(name,oldpass,pass);
        }else{
            dataResult.setStatus(401);
            dataResult.setMsg("验证码错误");
        }
        return dataResult;
    }

    /**
     * 管理员查看所有注册人
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/adminUsers",method = RequestMethod.GET)
    public DataResult adminGetUsers(HttpServletRequest request){
        DataResult dataResult=new DataResult();
        Cookie [] cookies= request.getCookies();
        for(Cookie cookie:cookies){
            if("type".equals(cookie.getName()))
                if(("admin".hashCode()+"").equals(cookie.getValue()))
                    dataResult=adminService.GetAllUser();
                else {
                    dataResult.setMsg("抱歉您未登录");
                    dataResult.setStatus(401);
                }
        }
        return dataResult;
    }

    /**
     * 管理员添加邀请人
     * @param a_name
     * @param a_sex
     * @param unit
     * @param a_password
     * @param a_prove
     * @param a_phone
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addAgent",method = RequestMethod.POST)
    public DataResult adminAddAgent(String a_name,String a_sex,String unit,String a_password,String a_prove,String a_phone){
        Agent agent=new Agent();
        agent.setA_name(a_name);
        agent.setA_password(a_password);
        agent.setA_phone(a_phone);
        agent.setA_prove(a_prove);
        agent.setA_sex(a_sex);
        DataResult dataResult=adminService.AdminInsertAgent(agent,unit);
        return dataResult;
    }

    /**
     * 管理员查看所有邀请人
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/adminAgents",method = RequestMethod.GET)
    public DataResult adminGetAllAgent(HttpServletRequest request){
        DataResult dataResult=new DataResult();
        Cookie[] cookies=request.getCookies();
        for(Cookie cookie:cookies){
            if("type".equals(cookie.getName()))
                if(("admin".hashCode()+"").equals(cookie.getValue())) {
                    dataResult=adminService.GetAllAgent();
                }
                else {
                    dataResult.setStatus(401);
                    dataResult.setMsg("抱歉您未登录");
                }
        }
        return dataResult;
    }

    /**
     * 获取邀请人信息*
     * @param request
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/agentInfo",method = RequestMethod.GET)
    public DataResult getAgentInfo(HttpServletRequest request,HttpSession session){
        DataResult dataResult=new DataResult();
        Cookie[] cookies=request.getCookies();
        for(Cookie cookie:cookies){
            if("type".equals(cookie.getName()))
                if(("agent".hashCode()+"").equals(cookie.getValue())) {
                    Agent agent = (Agent) session.getAttribute("agentData");
                    dataResult.setStatus(200);
                    dataResult.setMsg("Yes");
                    dataResult.setData(agent);
                }
                else {
                    dataResult.setStatus(401);
                    dataResult.setMsg("抱歉您未登录");
                }
        }
        return dataResult;
    }

    /**
     * 邀请人查看自己邀请的用户
     * @param request
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/agentUsers",method = RequestMethod.GET)
    public DataResult agentGetAllUser(HttpServletRequest request,HttpSession session){
        DataResult dataResult=new DataResult();
        Cookie[] cookies=request.getCookies();
        for(Cookie cookie:cookies){
            if("type".equals(cookie.getName()))
                if(("agent".hashCode()+"").equals(cookie.getValue())) {
                    Agent agent  =(Agent)session.getAttribute("agentData");
                    dataResult = agentService.GetAllUser(agent.getId());
                }
                else {
                    dataResult.setStatus(401);
                    dataResult.setMsg("抱歉您未登录");
                }
        }
        return dataResult;
    }

    /**
     * 注销
     * @param session
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/exit",method = RequestMethod.GET)
    public DataResult outLogin(HttpSession session,HttpServletRequest request,HttpServletResponse response){
        Cookie [] cookies=request.getCookies();

        for(Cookie cookie:cookies){

            cookie.setMaxAge(0);

            cookie.setPath("/");

            response.addCookie(cookie);

        }
        session.invalidate();
        DataResult dataResult=new DataResult();
        dataResult.setStatus(200);
        dataResult.setMsg("OK");
        return dataResult;
    }
}
