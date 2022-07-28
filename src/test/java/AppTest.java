import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ll.exam.article.dto.ArticleBodyDto;
import com.ll.exam.article.dto.ArticleDto;
import com.ll.exam.util.Ut;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
public class AppTest {
    private static final ObjectMapper om = new ObjectMapper();
    @Test
    void 테스트_asserThat() {
        int rs = 10 + 20;
        assertThat(rs).isEqualTo(30);
    }

    @Test
    void 실험_ObjectMapper() throws JsonProcessingException {
        ArticleDto articleDto = new ArticleDto(1L, "제목", "내용");

        String jsonStr = Ut.json.toJson(articleDto, "");
        assertThat(jsonStr).isNotBlank();
        assertThat(jsonStr).isEqualTo("""
                {"id":1,"title":"제목","body":"내용"}
                """.trim());
    }


    @Test
    void ObjectMapper__jsonStrToObj() {
        ArticleDto articleDtoOrigin = new ArticleDto(1L, "제목", "내용");
        String jsonStr = Ut.json.toJson(articleDtoOrigin, "");

        ArticleDto articleDtoFromJson = Ut.json.toObj(jsonStr, ArticleDto.class, null);

        assertThat(articleDtoOrigin).isEqualTo(articleDtoFromJson);
    }

    @Test
    void ObjectMapper__listToJson(){
        ArrayList<ArticleDto> list = new ArrayList<>();
        ArticleDto article1 = new ArticleDto(1L, "제목", "내용");
        ArticleDto article2 = new ArticleDto(2L, "제목", "내용");

        list.add(article1);
        list.add(article2);
        String jsonStr = Ut.json.toJson(list, "");
        System.out.println(jsonStr);

        assertThat(jsonStr).isNotBlank();
        assertThat(jsonStr).isEqualTo("""
              [{"id":1,"title":"제목","body":"내용"},{"id":2,"title":"제목","body":"내용"}]
                """.trim());
    }

    @Test
    void ObjectMapper__mapToJson(){
        HashMap<Long, ArticleBodyDto> hashMap = new HashMap<>();
        ArticleDto article1 = new ArticleDto(1L, "제목1", "내용1");
        ArticleDto article2 = new ArticleDto(2L, "제목2", "내용2");

        hashMap.put(article1.getId(), ArticleBodyDto.from(article1));
        hashMap.put(article2.getId(), ArticleBodyDto.from(article2));

        String jsonStr = Ut.json.toJson(hashMap, "");
        System.out.println(jsonStr);

        assertThat(jsonStr).isNotBlank();
        assertThat(jsonStr).isEqualTo("""
                {"1":{"title":"제목1","body":"내용1"},"2":{"title":"제목2","body":"내용2"}}
                """.trim());
    }

    @Test
    void ObjectMapper__listJsonToArticleDto(){
        ArrayList<ArticleDto> list = new ArrayList<>();
        ArticleDto article1 = new ArticleDto(1L, "제목", "내용");
        ArticleDto article2 = new ArticleDto(2L, "제목", "내용");
        list.add(article1);
        list.add(article2);
        String jsonStr = Ut.json.toJson(list, "");
        System.out.println(jsonStr);

        List<ArticleDto> toObj = Ut.json.toObj(jsonStr, new TypeReference<>() {
        }, null);
        List<ArticleDto> listFromJson = toObj;
        assertThat(list).isEqualTo(listFromJson);
    }

    @Test
    void ObjectMapper__mapJsonToArticleDtoMap(){
        HashMap<Long, ArticleBodyDto> hashMap = new HashMap<>();
        ArticleDto article1 = new ArticleDto(1L, "제목1", "내용1");
        ArticleDto article2 = new ArticleDto(2L, "제목2", "내용2");
        ArticleDto article3 = new ArticleDto(3L, "제목3", "내용3");
        hashMap.put(article1.getId(), ArticleBodyDto.from(article1));
        hashMap.put(article2.getId(), ArticleBodyDto.from(article2));
        hashMap.put(article3.getId(), ArticleBodyDto.from(article3));
        String jsonStr = Ut.json.toJson(hashMap, "");

        Map<Integer, ArticleBodyDto> map = Ut.json.toMap(jsonStr, new TypeReference<>() {
        }, null);

        System.out.println(map);

    }



}
