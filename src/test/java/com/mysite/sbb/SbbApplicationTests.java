package com.mysite.sbb;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Optional;

@SpringBootTest
class SbbApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void questionInsertTest() {
        Question question = new Question();
        question.setSubject("sbb가 무엇인가요");
        question.setContent("sbb에 대해 알고 싶어요");
        question.setCreateDate(java.time.LocalDateTime.now());
        this.questionRepository.save(question);


        Question question2 = new Question();
        question2.setSubject("스프링부트 모델 질문입니다");
        question2.setContent("id는 자동 생성 되나요?");
        question2.setCreateDate(java.time.LocalDateTime.now());
        this.questionRepository.save(question2);
    }

    @Test
    void QuestionFindAllTest() {
        List<Question> all = this.questionRepository.findAll();
        assertEquals(2, all.size());

        Question q = all.get(0);
        assertEquals("sbb가 무엇인가요", q.getSubject());
    }

    @Test
    void questionFindByIdtest(){
        Optional<Question> oq = this.questionRepository.findById(1);
        if(oq.isPresent()) {
            Question q = oq.get();
            assertEquals("스프링부트 모델 질문입니다", q.getSubject());
        }
    }

    @Test
    void questionFindBysubjecttest(){
        Question q = this.questionRepository.findBySubject("sbb가 무엇인가요");
        assertEquals(1, q.getId());
    }

    @Test
    void questtionFindByandcontenttest(){
        Question q = this.questionRepository.findBySubjectAndContent(
                "sbb가 무엇인가요", "sbb에 대해 알고 싶어요");
        assertEquals(1, q.getId());
    }
}