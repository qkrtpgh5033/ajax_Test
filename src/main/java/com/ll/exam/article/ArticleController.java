package com.ll.exam.article;

import com.ll.exam.Rq;
import com.ll.exam.article.dto.ArticleDto;
import com.ll.exam.article.dto.ArticleModifyDto;
import com.ll.exam.util.Ut;

import java.util.ArrayList;
import java.util.List;

public class ArticleController {

    private ArticleService articleService = new ArticleService();

    public void showWrite(Rq rq){

        rq.view("/usr/article/write");
    }

    public void doWrite(Rq rq) {
        String title = rq.getParam("title", "");
        String body = rq.getParam("body", "");

        long id = articleService.write(title, body);

        rq.appendBody("%d번 게시물이 생성 되었습니다.".formatted(id));
        rq.appendBody("<div><a href=\"/usr/article/list/free\">리스트로 이동</a></div>");
    }

    public void showList(Rq rq){
        List<ArticleDto> lists = articleService.getList();

        rq.setAttr("articles", lists);
        rq.view("/usr/article/list");
    }

    public void showDetail(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);
        if( id == 0){
            rq.appendBody("번호를 입력해주세요");
            return;
        }
        ArticleDto findArticle = articleService.findById(id);

        if (findArticle == null) {
            rq.appendBody("해당 글이 존재하지 않습니다.");
            return;
        }

        rq.setAttr("article", findArticle);
        rq.view("/usr/article/detail");
    }

    public void doDelete(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);
        if( id == 0){
            rq.appendBody("번호를 입력해주세요");
            return;
        }
        articleService.articleDelete(id);
        rq.appendBody("<div>%d번 게시물이 삭제되었습니다.</div>".formatted(id));
        rq.appendBody("<div><a href=\"/usr/article/list/free\">리스트로 이동</a></div>");



    }

    public void doModify(Rq rq) {

        String title = rq.getParam("title", "");
        String body = rq.getParam("body", "");
        ArticleModifyDto articleModifyDto = new ArticleModifyDto(title, body);
        long id = rq.getLongPathValueByIndex(1, 0);
        if( id == 0){
            rq.appendBody("번호를 입력해주세요");
            return;
        }
        articleService.articleModify(id, articleModifyDto);
        rq.appendBody("<div>%d번 게시물이 수정되었습니다.</div>".formatted(id));
        rq.appendBody("<div><a href=\"/usr/article/list/free\">리스트로 이동</a></div>");
    }

    public void showModify(Rq rq) {
        long id = rq.getLongPathValueByIndex(1, 0);
        if( id == 0){
            rq.appendBody("번호를 입력해주세요");
            return;
        }
        ArticleDto findArticle = articleService.findById(id);

        if (findArticle == null) {
            rq.appendBody("해당 글이 존재하지 않습니다.");
            return;
        }
        rq.setAttr("article", findArticle);
        rq.view("/usr/article/modify");
    }

    public void getArticles(Rq rq) {
        List<ArticleDto> list = articleService.getList();
        String toJson = Ut.json.toJson(list, "");
        rq.appendBody(toJson);
    }
}
