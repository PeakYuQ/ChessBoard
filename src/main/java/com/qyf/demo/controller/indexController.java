package com.qyf.demo.controller;

import com.qyf.demo.chessBroad;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.management.remote.rmi._RMIConnection_Stub;
import javax.servlet.http.HttpSession;
import java.util.Random;


@Controller
public class indexController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    //设置棋盘数据
    @PostMapping("/seting")
    public String setingBorad(@RequestParam("chessBroad-size") Integer size,
                              @RequestParam("chessBroad-speed") Integer speed, Model model,
                              HttpSession session){
        int i1 = (size * size - 1) / 3 +1;
        chessBroad a = new chessBroad();
        int [][] board =new int[size][size];
        int [][] active =new int[i1][7];
        a.setActive(active);
        a.setBoard(board);
        Random r = new Random(1);
        int x = r.nextInt(size);
        int y= r.nextInt(size);
        a.chessBoard(0, 0, x,y, size);
        board = a.getBoard();
        a.search(board,size);
        active=a.getActive();
        session.setAttribute("active",active);
        session.setAttribute("speed",speed);
        session.setAttribute("board",board);
        session.setAttribute("size",size);
        session.setAttribute("X",x);
        session.setAttribute("Y",y);
        session.setAttribute("total",i1);
        Integer red,yellow,pupple,green;
        red=a.searchColor(active,1,size);
        yellow=a.searchColor(active,3,size);
        pupple=a.searchColor(active,4,size);
        green=a.searchColor(active,2,size);
        session.setAttribute("red",red);
        session.setAttribute("yellow",yellow);
        session.setAttribute("pupper",pupple);
        session.setAttribute("green",green);
        return "Chessboard";
    }

    //动画演示
    @PostMapping("/active")
    public String activeBorad(@RequestParam(value = "current", defaultValue = "1") Integer current,Model model,
                              HttpSession session){
        Integer size =  (Integer) session.getAttribute("size");
        int [][] board = (int[][]) session.getAttribute("board");
        int [][] b = new int[size][size];//保存session的值

        for (int i = 0; i <size; i++) {
            for (int j = 0; j < size; j++) {
                b[i][j] = board[i][j];
                if(board[i][j]>current)
                    board[i][j]=-1;//当前用不到的设为-1
            }
        }

        session.setAttribute("board",b);    //保存session的值
        session.setAttribute("current",current);
        model.addAttribute("board",board);
        return "active";
    }

    //手动演示
    @PostMapping("/noactive")
    public String noactiveBorad(@RequestParam(value = "current", defaultValue = "1") Integer current,Model model,
                              HttpSession session){
        Integer size =  (Integer) session.getAttribute("size");
        int [][] board = (int[][]) session.getAttribute("board");
        int [][] b = new int[size][size];//保存session的值

        for (int i = 0; i <size; i++) {
            for (int j = 0; j < size; j++) {
                b[i][j] = board[i][j];
                if(board[i][j]>current)
                    board[i][j]=-1;//当前用不到的设为-1
            }
        }

        session.setAttribute("board",b);    //保存session的值
        session.setAttribute("current",current);
        model.addAttribute("board",board);
        return "noactive";
    }





}
