package com.restudy.model;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    public ArticleDao(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/restudy",
                    "root",
                    "1234"
            );
            System.out.println("DB연동 성공");
        }catch (Exception e){
            System.out.println("DB연동 실패 : "+e);
        }
    }

    public ArticleDto createArticle(ArticleDto form){
        System.out.println("form = " + form);
        System.out.println("ArticleDao.createArticle");
        ArticleDto saved = new ArticleDto();
        try {
            String sql = "insert into article(title,content) values(?,?);";
            ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,form.getTitle());
            ps.setString(2,form.getContent());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                Long id = rs.getLong(1);
                saved.setId(id);
                return saved;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public ArticleDto show(Long id){
        try {
            String sql = "select * from article where id = ?;";
            ps = conn.prepareStatement(sql);
            ps.setLong(1,id);
            rs = ps.executeQuery();
            if(rs.next()){
                ArticleDto form = new ArticleDto(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3)
                );
                return form;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public List<ArticleDto> index(){
        try {
            String sql = "select * from article";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            List<ArticleDto> list = new ArrayList<>();
            while (rs.next()){
                ArticleDto form = new ArticleDto(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3)
                );
                list.add(form);
            }
            return list;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}
