package cn.user.servlet;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
public class CheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.在内存中生成图片(纸),没有背景颜色,画填充的矩形,并且和纸的大小相同,矩阵有颜色
        //2.获取笔的对象(设置颜色，设置字体，画字符串，画矩阵)
        //3.先准备好数据，随机生成4个字符，把字符画到纸上
        //4.画干扰线
        //5.把内容中的图片输出到客户端上
        //设置验证码图片的大小
        int width = 150;
        int height = 50;
        //在内存中生成图片
        BufferedImage image = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        //获取画笔对象
        Graphics2D g = (Graphics2D) image.getGraphics();
        //设置颜色
        g.setColor(Color.GRAY);
        //画一个填充的矩形
        g.fillRect(0,0,width,height);
        //画一个矩形的边框
        g.setColor(Color.BLACK);
        g.drawRect(0,0,width-1,height-1);//画到内边框
        //准备数据，随机获取4个字符。
        //定义字符串
        String words = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        //定义四个验证码字符串
        StringBuilder checkcode = new StringBuilder();
        g.setColor(Color.YELLOW); //设置字体颜色
        g.setFont(new Font("隶书",Font.BOLD,height/2));//设置字体
        //返回指定下标位置的字符，随机获取下标
        Random random = new Random();
        //设置字符串的位置
        int x=width/5;
        int y=height/2;
        for (int i=0;i<4;i++){
            //void rotate(double theta,double x,double y)
            //theta弧度 hudu = jiaodu*Math.PI/180
            //获取正负30之间的角度
            int jiaodu = random.nextInt(60)-30;
            double hudu = jiaodu*Math.PI/180;
            g.rotate(hudu,x,y);
            int index = random.nextInt(words.length());//随机获取一个下标
            Character c = words.charAt(index);
            checkcode.append(c);
            g.drawString(""+c,x,y);//获取随机字符，然后写到画布上
            //每次旋转会在上一次的基础上转动。
            //但是每个字符都需要在正负30度之间，因此要回转。
            g.rotate(-hudu,x,y);
            x += width/5;
        }
        //画干扰线
        g.setColor(Color.BLACK);//设置画笔颜色
        int x1,x2,y1,y2;
        for(int i=0;i<4;i++){
            x1 = random.nextInt(width);
            y1 = random.nextInt(height);
            x2 = random.nextInt(width);
            y2 = random.nextInt(height);
            g.drawLine(x1,y1,x2,y2);
        }
        //将验证码字符写入到session域对象中
        HttpSession session = request.getSession();
        session.setAttribute("checkcode",checkcode.toString());
        //输出到浏览器
        ImageIO.write(image,"jpg",response.getOutputStream());
    }
}
